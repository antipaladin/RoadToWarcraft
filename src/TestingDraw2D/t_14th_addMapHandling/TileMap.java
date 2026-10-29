package TestingDraw2D.t_14th_addMapHandling;

/**
 * A térkép modellje: csak azt tudja, hol milyen talaj van.
 * Nincs benne se Swing, se kép.
 *
 * Belül tiles[y][x] a tárolás (sor = y), de ezt kívülről senki nem látja:
 * mindenki a getTerrain(x, y)-t használja.
 */
public class TileMap {

    private final int width;
    private final int height;
    private final TerrainType[][] tiles;

    public TileMap(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("érvénytelen méret: " + width + "x" + height);
        }
        this.width = width;
        this.height = height;
        this.tiles = new TerrainType[height][width];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isInside(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    public TerrainType getTerrain(int x, int y) {
        checkInside(x, y);
        return tiles[y][x];
    }

    public void setTerrain(int x, int y, TerrainType type) {
        checkInside(x, y);
        tiles[y][x] = type;
    }

    private void checkInside(int x, int y) {
        if (!isInside(x, y)) {
            throw new IndexOutOfBoundsException(
                    "(" + x + "," + y + ") kívül esik a " + width + "x" + height + " térképen");
        }
    }
}