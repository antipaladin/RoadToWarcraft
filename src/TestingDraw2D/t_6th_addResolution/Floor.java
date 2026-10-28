package TestingDraw2D.t_6th_addResolution;

import javax.swing.*;
import java.awt.*;

public class Floor extends JPanel {

    public Floor() {
        setPreferredSize(new Dimension(Resolution.FIRST_RES.getWidth(), Resolution.FIRST_RES.getHeight()));
        setBackground(Color.GREEN);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
