package TestingDraw2D.t_8th_addBuildingEnum_addBuildingLayer;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DrawThePanels {

    public void show() {
        SwingUtilities.invokeLater(() -> {
            BuildingLayer buildingLayer = new BuildingLayer();
            // 1. A szereplők

            FogHoles fogHoles = new FogHoles();

            for (Building b : buildingLayer.getBuildings()) {
                fogHoles.addHole(b.getViewArea());
            }

            // 3. A rétegek, alulról felfelé
            List<JPanel> myList = List.of(
                    new Floor(),              // piros háttér
                    buildingLayer,
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