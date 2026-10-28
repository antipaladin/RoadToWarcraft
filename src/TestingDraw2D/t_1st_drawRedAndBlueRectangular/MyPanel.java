package TestingDraw2D.t_1st_drawRedAndBlueRectangular;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {

    public MyPanel() {
        setPreferredSize(new Dimension(400, 300));
        setBackground(Color.RED);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }


}
