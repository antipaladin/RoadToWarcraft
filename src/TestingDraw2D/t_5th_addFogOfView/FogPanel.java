package TestingDraw2D.t_5th_addFogOfView;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;

public class FogPanel extends JPanel {

    private final FogHoles fogHoles;                         // innen tudja, hol legyen lyuk
    private final Color fogColor = new Color(0, 0, 0, 150);  // félig átlátszó fekete

    public FogPanel(FogHoles fogHoles) {
        setPreferredSize(new Dimension(600, 600));
        setOpaque(false);
        this.fogHoles = fogHoles;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // 1. A köd kezdetben az egész panel
        Area fog = new Area(new Rectangle(0, 0, getWidth(), getHeight()));

        // 2. Minden nyilvántartott lyukat kivágunk belőle
        for (Rectangle hole : fogHoles.getHoles()) {
            fog.subtract(new Area(hole));
        }

        // 3. Ami maradt, azt befestjük
        g2.setColor(fogColor);
        g2.fill(fog);
    }
}