package TestingDraw2D.t_10th_addBuildingListClassAndTestSetupClass;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DrawThePanels {

    public void show() {
        SwingUtilities.invokeLater(() -> {
            TestSetup.createStartingWorld();

            // 1. Az épületréteg: a közös listát rajzolja
            BuildingLayer buildingLayer = new BuildingLayer(BuildingList.getBuildingFromList());

            // 2. Minden épület látott területe lyuk lesz a ködben
            FogHoles fogHoles = new FogHoles();
            for (Building b : BuildingList.getBuildingFromList()) {
                fogHoles.addHole(b.getViewArea());
            }

            // 3. A rétegek, alulról felfelé
            List<JPanel> myList = List.of(
                    new Floor(),                // csempézett talaj
                    buildingLayer,              // épületek
                    new GridPanel(),            // négyzetháló
                    new FogPanel(fogHoles)      // köd, lyukakkal az épületek körül
            );

            // 4. Ablak, és a rétegek egymásba fűzése
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