package TestingDraw2D.t_2nd_addGrid;

import javax.swing.*;
import java.awt.*;

public class MyPanel2 extends JPanel {

    public MyPanel2() {
        setPreferredSize(new Dimension(400, 300));
        setOpaque(false);       // a super.paintComponent nem tölti ki a hátteret
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.BLUE);
        g2.fillRect(10, 10, 100, 50);
    }
}
