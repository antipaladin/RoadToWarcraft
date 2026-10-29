package TestingDraw2D.t_14th_addMapHandling;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Building {                                // ← nincs extends JPanel

    private final BuildingData type;
    private final int column;
    private final int row;
    private final BufferedImage image;
    private final Clip sound;                      // az épület saját lejátszója


    public Building(BuildingData type, int column, int row) {
        this.type = type;
        this.column = column;
        this.row = row;
        this.image = loadImage(type.getPath());
        this.sound = SoundPlayer.loadClip(type.getSoundPath());   // ← új: egyszer betölti
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

    /** Az épület által elfoglalt terület. */
    public Rectangle getArea() {
        return new Rectangle(Tile.toPx(column), Tile.toPx(row), Tile.toPx(type.getWidth()), Tile.toPx(type.getHeight()));
    }

    /** A látott terület: az épület, minden irányban a látótávval megnövelve. */
    public Rectangle getViewArea() {
        int range = Tile.toPx(type.getViewRange());
        Rectangle view = getArea();
        view.grow(range, range);
        return view;
    }

    public boolean contains(int px, int py) {
        return getArea().contains(px, py);
    }

    public BuildingData getType() {
        return type;
    }

    public void playSound() {
        if (sound == null) {
            return;                                // nincs hang (nem találta a fájlt)
        }
        sound.stop();                              // ha szól, leállítja
        sound.setFramePosition(0);                 // vissza az elejére
        sound.start();                             // újra indítja
    }


    /** Kirajzolja magát. A BuildingLayer hívja meg. */
    public void draw(Graphics2D g2) {
        g2.drawImage(image, Tile.toPx(column), Tile.toPx(row), null);
    }
}