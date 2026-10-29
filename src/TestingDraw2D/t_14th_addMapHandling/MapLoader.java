package TestingDraw2D.t_14th_addMapHandling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

/**
 * .wc2 térképfájl betöltése.
 *
 * Formátum:
 *   1. (nem üres) sor:  szélesség magasság
 *   utána pontosan "magasság" darab sor, mindegyikben "szélesség" darab talajkód
 *
 * Üres sorok és '#'-tal kezdődő megjegyzések bárhol lehetnek, ezeket átugorja.
 * Hiba esetén MapFormatException-t dob, a fájlnévvel és a sorszámmal.
 */
public final class MapLoader {

    private MapLoader() {
    }

    /** Betöltés a classpath-ról, pl. load("/maps/map1.wc2"). */
    public static TileMap load(String resourcePath) {
        InputStream in = MapLoader.class.getResourceAsStream(resourcePath);
        if (in == null) {
            throw new MapFormatException(resourcePath, 0, "a fájl nem található a classpath-on");
        }
        try (Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
            return parse(reader, resourcePath);
        } catch (IOException e) {
            throw new MapFormatException(resourcePath, 0, "olvasási hiba: " + e.getMessage());
        }
    }

    /** Maga a feldolgozás. Külön metódus, hogy fájl nélkül, egy StringReaderrel is tesztelhető legyen. */
    public static TileMap parse(Reader source, String name) throws IOException {
        BufferedReader reader = new BufferedReader(source);
        int lineNo = 0;
        String line;

        // --- Fejléc ---
        int width = -1;
        int height = -1;
        while ((line = reader.readLine()) != null) {
            lineNo++;
            String[] parts = tokens(line);
            if (parts == null) {
                continue; // üres sor vagy megjegyzés
            }
            if (parts.length != 2) {
                throw new MapFormatException(name, lineNo,
                        "a fejléc 'szélesség magasság' kell legyen, de " + parts.length + " érték van benne");
            }
            width = parseInt(parts[0], name, lineNo, "szélesség");
            height = parseInt(parts[1], name, lineNo, "magasság");
            if (width <= 0 || height <= 0) {
                throw new MapFormatException(name, lineNo, "érvénytelen méret: " + width + "x" + height);
            }
            break;
        }
        if (width < 0) {
            throw new MapFormatException(name, lineNo, "üres fájl, nincs fejléc");
        }

        // --- Sorok ---
        TileMap map = new TileMap(width, height);
        int y = 0;
        while ((line = reader.readLine()) != null) {
            lineNo++;
            String[] parts = tokens(line);
            if (parts == null) {
                continue;
            }
            if (y >= height) {
                throw new MapFormatException(name, lineNo,
                        "túl sok sor: a fejléc szerint " + height + " sor kellene");
            }
            if (parts.length != width) {
                throw new MapFormatException(name, lineNo,
                        parts.length + " érték van a sorban, " + width + " kellene");
            }
            for (int x = 0; x < width; x++) {
                int code = parseInt(parts[x], name, lineNo, (x + 1) + ". oszlop");
                try {
                    map.setTerrain(x, y, TerrainType.fromCode(code));
                } catch (IllegalArgumentException e) {
                    throw new MapFormatException(name, lineNo, (x + 1) + ". oszlop: " + e.getMessage());
                }
            }
            y++;
        }
        if (y < height) {
            throw new MapFormatException(name, lineNo,
                    "túl kevés sor: " + y + " van, a fejléc szerint " + height + " kellene");
        }
        return map;
    }

    /** A sor szavakra bontva, vagy null, ha üres / megjegyzés. Bármennyi szóköz vagy tab lehet köztük. */
    private static String[] tokens(String line) {
        String trimmed = line.strip();
        if (trimmed.isEmpty() || trimmed.startsWith("#")) {
            return null;
        }
        return trimmed.split("\\s+");
    }

    private static int parseInt(String s, String name, int lineNo, String what) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new MapFormatException(name, lineNo, what + ": '" + s + "' nem egész szám");
        }
    }

    /** Térképformátum-hiba, a fájlnévvel és a sorszámmal. */
    public static class MapFormatException extends RuntimeException {
        public MapFormatException(String file, int line, String message) {
            super(file + (line > 0 ? ", " + line + ". sor" : "") + ": " + message);
        }
    }
}