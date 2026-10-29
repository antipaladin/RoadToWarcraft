package TestingDraw2D.t_14th_addMapHandling;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DrawThePanels {

    public void show() {
        SwingUtilities.invokeLater(() -> {

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

            // 6. Görgetés a nyilakkal: egy lenyomás = egy csempe
            JPanel root = myList.get(0);
            addScrollKey(root, "UP",    0, -1);
            addScrollKey(root, "DOWN",  0,  1);
            addScrollKey(root, "LEFT", -1,  0);
            addScrollKey(root, "RIGHT", 1,  0);
            myList.get(0).addMouseListener(new MouseHandler());
            frame.add(myList.get(0));
            frame.pack();
            frame.setVisible(true);
        });
    }

    private void addScrollKey(JPanel root, String key, int dxTiles, int dyTiles) {
        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), key);
        root.getActionMap().put(key, new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Camera.move(Tile.toPx(dxTiles), Tile.toPx(dyTiles));
                root.repaint();                  // a gyerekekkel (rétegekkel) együtt
            }
        });
    }
}