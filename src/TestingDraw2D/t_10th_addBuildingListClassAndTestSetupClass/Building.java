package TestingDraw2D.t_10th_addBuildingListClassAndTestSetupClass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Building {                                // ← nincs extends JPanel

    private final BuildingData type;
    private final int xPos;
    private final int yPos;
    private final BufferedImage image;

    public Building(BuildingData type, int xPos, int yPos) {
        this.type = type;
        this.xPos = xPos;
        this.yPos = yPos;
        this.image = loadImage(type.getPath());
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
        return new Rectangle(xPos, yPos, type.getWidth(), type.getHeight());
    }

    /** A látott terület: az épület, minden irányban a látótávval megnövelve. */
    public Rectangle getViewArea() {
        Rectangle view = getArea();
        view.grow(type.getViewRange(), type.getViewRange());
        return view;
    }

    /** Kirajzolja magát. A BuildingLayer hívja meg. */
    public void draw(Graphics2D g2) {
        g2.drawImage(image, xPos, yPos, null);
    }
}