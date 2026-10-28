package TestingDraw2D.t_1st_drawRedAndBlueRectangular;

import javax.swing.*;
import java.awt.*;

public class DrawThePanels {

    public void showThePaint() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Teszt");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            MyPanel bottom = new MyPanel();
            bottom.setLayout(new BorderLayout());     // hogy a belső panel kitöltse
            bottom.add(new MyPanel2());               // a MyPanel2 a MyPanel BELSEJÉBE kerül
            frame.add(bottom);

            frame.pack();
            frame.setVisible(true);        // ← itt hívja meg a Swing ELŐSZÖR
        });
    }

}
