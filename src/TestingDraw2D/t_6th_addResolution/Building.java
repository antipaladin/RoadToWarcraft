package TestingDraw2D.t_6th_addResolution;

import TestingDraw2D.t_7th_addFloorTexture.Resolution;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Building extends JPanel {

    private final BufferedImage image;   // a kép, egyszer betöltve
    private final Rectangle rect;        // hol van a kép, és mekkora
    private final int viewDistance = 30;
    private int xPos;
    private int yPos;

    public Building(int xPos, int yPos) {
        setPreferredSize(new Dimension(Resolution.FIRST_RES.getWidth(), Resolution.FIRST_RES.getHeight()));
        setOpaque(false);
        // graphics\tilesets\summer\human\buildings
        image = loadImage("/graphics/tilesets/summer/human/buildings/barracks_for_testing.png");
        rect = new Rectangle(xPos, yPos, image.getWidth(), image.getHeight());  // a kép saját mérete
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

    public Rectangle getViewArea() {
        Rectangle view = new Rectangle(rect);
        view.grow(viewDistance, viewDistance);
        return view;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image, rect.x, rect.y, null);   // a kép a téglalap helyére
    }
}