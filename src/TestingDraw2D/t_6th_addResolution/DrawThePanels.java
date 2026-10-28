package TestingDraw2D.t_6th_addResolution;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DrawThePanels {

    public void show() {
        SwingUtilities.invokeLater(() -> {
            // 1. A szereplők
            Building imagePanel = new Building(150, 150);
            Building imagePanel2 = new Building(350, 150);
            FogHoles fogHoles = new FogHoles();

            // 2. A kép átadja a látott területét a nyilvántartónak
            fogHoles.addHole(imagePanel.getViewArea());
            fogHoles.addHole(imagePanel2.getViewArea());

            // 3. A rétegek, alulról felfelé
            List<JPanel> myList = List.of(
                    new Floor(),              // piros háttér
                    imagePanel,                 // a kép
                    imagePanel2,
                    new GridPanel(),            // négyzetháló
                    new FogPanel(fogHoles)      // köd, lyukkal a kép körül
            );

            JFrame frame = new JFrame("Teszt");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel parent = myList.get(0);
            for (int i = 1; i < myList.size(); i++) {
                JPanel child = myList.get(i);
                parent.setLayout(new BorderLayout());
                parent.add(child);
                parent = child;
            }

            frame.add(myList.get(0));
            frame.pack();
            frame.setVisible(true);
        });
    }
}