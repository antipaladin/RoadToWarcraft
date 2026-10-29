package TestingDraw2D.t_14th_addMapHandling;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

public class FogPanel extends JPanel {

    private final FogHoles fogHoles;                         // innen tudja, hol legyen lyuk
    private final Color fogColor = new Color(0, 0, 0, 150);  // félig átlátszó fekete
    private final int cornerArcInTiles = 2;

    public FogPanel(FogHoles fogHoles) {
        setPreferredSize(new Dimension(Resolution.FIRST_RES.getWidth(), Resolution.FIRST_RES.getHeight()));
        setOpaque(false);
        this.fogHoles = fogHoles;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();        // MÁSOLAT: az eltolás ne örökölődjön a felső rétegekre
        g2.translate(-Camera.getX(), -Camera.getY());

        // 1. A köd kezdetben az egész panel
        Area fog = new Area(new Rectangle(Camera.getX(), Camera.getY(), getWidth(), getHeight()));

        // 2. Minden nyilvántartott lyukat kivágunk belőle
        int arc = Tile.toPx(cornerArcInTiles);   // csempe → pixel
        for (Rectangle hole : fogHoles.getHoles()) {
            RoundRectangle2D rounded = new RoundRectangle2D.Double(
                    hole.x, hole.y, hole.width, hole.height,
                    arc, arc);
            fog.subtract(new Area(rounded));
        }

        // 3. Ami maradt, azt befestjük
        g2.setColor(fogColor);
        g2.fill(fog);
        g2.dispose();
    }
}