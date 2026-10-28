package TestingDraw2D.t_7th_addFloorTexture;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Floor extends JPanel {

    private final BufferedImage image;

    public Floor() {
        setPreferredSize(new Dimension(Resolution.FIRST_RES.getWidth(), Resolution.FIRST_RES.getHeight()));
        image = loadImage("/graphics/tilesets/summer/terrain/test.png");
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
        Graphics2D g2 = (Graphics2D) g;

        int width = image.getWidth();
        int height = image.getHeight();
        for (int y = 0; y < getHeight(); y += width) {
            for (int x = 0; x < getWidth(); x += height) {
                g2.drawImage(image, x, y, null);
            }
        }
    }
}
