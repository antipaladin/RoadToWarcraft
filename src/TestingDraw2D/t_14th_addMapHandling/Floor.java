package TestingDraw2D.t_14th_addMapHandling;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/** A talaj: a térképfájl alapján csempénként rajzolja ki a tilesetből. */
public class Floor extends JPanel {

    private static final int TILES_PER_ROW = 16;   // a summer.png egy sorában ennyi csempe van

    private final BufferedImage tileset;            // az összes csempe egy képen
    private final TileMap map;                      // melyik csempén milyen talaj van

    public Floor() {
        setPreferredSize(new Dimension(Resolution.FIRST_RES.getWidth(), Resolution.FIRST_RES.getHeight()));
        tileset = loadImage("/graphics/tilesets/summer/terrain/summer.png");
        map = MapLoader.load("/maps/map1.wc2");
        Camera.setLimits(Tile.toPx(map.getWidth()), Tile.toPx(map.getHeight()),
        Resolution.FIRST_RES.getWidth(), Resolution.FIRST_RES.getHeight());
    }

    private BufferedImage loadImage(String path) {
        try (InputStream in = getClass().getResourceAsStream(path)) {
            if (in == null) {
                throw new IllegalStateException("Nem található: " + path);
            }
            return ImageIO.read(in);
        } catch (IOException e) {
            throw new RuntimeException("Nem sikerült betölteni: " + path, e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();        // MÁSOLAT: az eltolás ne örökölődjön a felső rétegekre
        g2.translate(-Camera.getX(), -Camera.getY());

        for (int row = 0; row < map.getHeight(); row++) {
            for (int column = 0; column < map.getWidth(); column++) {
                TerrainType terrain = map.getTerrain(column, row);
                drawTile(g2, terrain.solidTile(), column, row);
            }
        }
        g2.dispose();
    }

    /** Egy csempe kirajzolása: a tilesetből kivágja, és a térkép (column, row) helyére teszi. */
    private void drawTile(Graphics2D g2, int tileIndex, int column, int row) {
        int size = Tile.toPx(1);                                // 32

        // Honnan: a csempe helye a tileset képen
        int sx = (tileIndex % TILES_PER_ROW) * size;
        int sy = (tileIndex / TILES_PER_ROW) * size;

        // Hova: a csempe helye a panelen
        int dx = Tile.toPx(column);
        int dy = Tile.toPx(row);

        g2.drawImage(tileset,
                dx, dy, dx + size, dy + size,      // cél (bal felső és jobb alsó sarok)
                sx, sy, sx + size, sy + size,      // forrás (bal felső és jobb alsó sarok)
                null);
    }
}