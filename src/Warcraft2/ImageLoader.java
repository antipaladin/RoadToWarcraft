package Warcraft2;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Képek betöltése a classpath-ról, cache-eléssel.
 * Ha egy kép hiányzik, nem száll el a program: lila-fekete kockás
 * placeholdert ad vissza, és kiírja a konzolra, mi hiányzik.
 */
public final class ImageLoader {

    private static final Map<String, BufferedImage> CACHE = new HashMap<>();

    private ImageLoader() {
    }

    public static BufferedImage load(String path) {
        return CACHE.computeIfAbsent(path, ImageLoader::loadUncached);
    }

    private static BufferedImage loadUncached(String path) {
        try (InputStream in = ImageLoader.class.getResourceAsStream(path)) {
            if (in == null) {
                System.err.println("[ImageLoader] HIÁNYZIK: " + path);
                return placeholder(32, 32);
            }
            BufferedImage img = ImageIO.read(in);
            System.out.printf("[ImageLoader] %-40s %dx%d%n", path, img.getWidth(), img.getHeight());
            return img;
        } catch (IOException e) {
            System.err.println("[ImageLoader] HIBA: " + path + " – " + e.getMessage());
            return placeholder(32, 32);
        }
    }

    /** A klasszikus "missing texture": lila-fekete sakktábla. */
    private static BufferedImage placeholder(int w, int h) {
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        for (int y = 0; y < h; y += 8) {
            for (int x = 0; x < w; x += 8) {
                g.setColor(((x + y) / 8) % 2 == 0 ? Color.MAGENTA : Color.BLACK);
                g.fillRect(x, y, 8, 8);
            }
        }
        g.dispose();
        return img;
    }
}