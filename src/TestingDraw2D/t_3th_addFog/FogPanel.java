package TestingDraw2D.t_3th_addFog;

import javax.swing.*;
import java.awt.*;

public class FogPanel extends JPanel {

    public FogPanel() {
        setPreferredSize(new Dimension(400, 300));
        setOpaque(false);                            // hogy alatta minden látsszon
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(new Color(0, 0, 0, 150));        // félig átlátszó fekete
        /*
        0,      // R – piros összetevő   (0–255): 0 = nincs benne piros
        0,      // G – zöld összetevő    (0–255): 0 = nincs benne zöld
        0,      // B – kék összetevő     (0–255): 0 = nincs benne kék
        150     // A – alfa, átlátszóság (0–255): 0 = teljesen átlátszó, 255 = teljesen fedő
         */

        g2.fillRect(0, 0, getWidth(), getHeight());  // az egészet befedi
    }
}