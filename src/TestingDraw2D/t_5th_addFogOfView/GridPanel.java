package TestingDraw2D.t_5th_addFogOfView;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {

    private final Rectangle area = new Rectangle(0, 0, 800, 800);  // a négyzetháló területe a panelen belül
    private final int cellSize = 38;                                  // egy cella mérete pixelben
    private final Color lineColor = new Color(255, 255, 255, 100);    // a vonalak színe (alfával = átlátszóság)

    public GridPanel() {
        setPreferredSize(new Dimension(600, 600));
        setOpaque(false);            // a panel háttere átlátszó: alatta minden látszik
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);     // átlátszó panelnél ez nem fest hátteret
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(lineColor);

        int right  = area.x + area.width;
        int bottom = area.y + area.height;

        // Függőleges vonalak: balról jobbra, cellánként
        for (int x = area.x; x <= right; x += cellSize) {
            g2.drawLine(x, area.y, x, bottom);
        }
        // Vízszintes vonalak: fentről lefelé, cellánként
        for (int y = area.y; y <= bottom; y += cellSize) {
            g2.drawLine(area.x, y, right, y);
        }
    }
}