package TestingDraw2D.t_8th_addBuildingEnum_addBuildingLayer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/** Átlátszó réteg: a listában lévő épületeket rajzolja ki. */
public class BuildingLayer extends JPanel {

    private final List<Building> buildings = List.of(
            new Building(BuildingData.BARRACK,  96, 64),
            new Building(BuildingData.BARRACK, 256, 256)
    );

    public BuildingLayer() {
        setPreferredSize(new Dimension(Resolution.FIRST_RES.getWidth(), Resolution.FIRST_RES.getHeight()));
        setOpaque(false);                       // hogy alatta a talaj látsszon
    }

    /** A köd ezen keresztül kéri el az épületeket a látott területükhöz. */
    public List<Building> getBuildings() {
        return buildings;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Building b : buildings) {
            b.draw(g2);                         // minden épület kirajzolja magát
        }
    }
}