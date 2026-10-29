package TestingDraw2D.t_13th_addSoundForBuilding;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/** Átlátszó réteg: a listában lévő épületeket rajzolja ki. */
public class BuildingLayer extends JPanel {

    private final List<Building> buildingList;

    public BuildingLayer(List<Building> buildingList) {
        this.buildingList = buildingList;
        setPreferredSize(new Dimension(Resolution.FIRST_RES.getWidth(), Resolution.FIRST_RES.getHeight()));
        setOpaque(false);                       // hogy alatta a talaj látsszon
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Building b : buildingList) {
            b.draw(g2);                         // minden épület kirajzolja magát
        }
    }
}