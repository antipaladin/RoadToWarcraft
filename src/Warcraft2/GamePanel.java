package Warcraft2;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.image.BufferedImage;
public class GamePanel extends JPanel {      // JPanel: Swing-komponens, amire rajzolni lehet

    public static final int SCALE = 2;       // ennyiszeres nagyítás a képernyőn

    private final HudLayout layout = new HudLayout();  // csak koordináták
    private final HudRenderer hud;                     // a HUD kirajzolója
    private final BufferedImage frame;                 // a 640x480-as logikai vászon

    // Ideiglenes adatok, amíg nincs modell
    private final int[] resources = {0, 0, 0};          // gold, wood, oil
    private String statusText = "Warcraft – HUD teszt";

    public GamePanel(Race race) {
        this.hud = new HudRenderer(layout, race);       // a képeket EGYSZER, itt tölti be
        this.frame = new BufferedImage(layout.width, layout.height,
                BufferedImage.TYPE_INT_RGB);  // egyszer foglaljuk le
        setPreferredSize(new Dimension(layout.width * SCALE,
                layout.height * SCALE));     // 1280x960 -> pack() ehhez méretez
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {  // a Swing hívja, NEM mi
        super.paintComponent(g);                 // háttér törlése

        // 1. Rajzolás a logikai vászonra
        Graphics2D fg = frame.createGraphics();  // "toll" a frame képhez
        try {
            renderFrame(fg);                     // minden 640x480-ban
        } finally {
            fg.dispose();                        // a tollat MINDIG visszaadjuk
        }

        // 2. Felnagyítás a képernyőre, elmosás nélkül
        Graphics2D g2 = (Graphics2D) g;          // a Swing valójában Graphics2D-t ad
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);  // nincs elmosás
        g2.drawImage(frame, 0, 0,
                layout.width * SCALE, layout.height * SCALE, null); // 640x480 -> 1280x960
    }

    public void renderFrame(Graphics2D g) {      // public: tesztből is hívható
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, layout.width, layout.height);  // az előző képkocka letörlése

        renderMapPlaceholder(g);                 // 1. térkép (mapArea-ba vágva)
        hud.render(g, resources, statusText);    // 2. HUD keret + szövegek
        renderMinimapPlaceholder(g);             // 3. HUD tartalom: a keret UTÁN
    }

    private void renderMapPlaceholder(Graphics2D g) {
        Rectangle m = layout.mapArea;            // (176,16) 448x448
        Shape oldClip = g.getClip();             // elmentjük a régi vágást
        g.clipRect(m.x, m.y, m.width, m.height); // innentől csak ide lehet rajzolni

        g.setColor(new Color(20, 70, 20));
        g.fillRect(m.x, m.y, m.width, m.height); // sötétzöld alap

        g.setColor(new Color(40, 110, 40));
        for (int x = m.x; x <= m.x + m.width; x += HudLayout.TILE_SIZE) {
            g.drawLine(x, m.y, x, m.y + m.height);  // függőleges rácsvonalak 32 px-enként
        }
        for (int y = m.y; y <= m.y + m.height; y += HudLayout.TILE_SIZE) {
            g.drawLine(m.x, y, m.x + m.width, y);   // vízszintes rácsvonalak
        }

        g.setClip(oldClip);                      // visszaállítjuk, különben a HUD sem látszana
    }

    private void renderMinimapPlaceholder(Graphics2D g) {
        Rectangle mm = layout.minimapArea;       // (24,26) 128x128
        g.setColor(new Color(10, 40, 10));
        g.fillRect(mm.x, mm.y, mm.width, mm.height);
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
        repaint();                               // kérés: "rajzolj újra, amikor tudsz"
    }
}