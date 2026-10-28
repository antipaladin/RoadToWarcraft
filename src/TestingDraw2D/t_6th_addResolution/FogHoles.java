package TestingDraw2D.t_6th_addResolution;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/** Számon tartja, hol és mekkora lyukak vannak a ködben. Nem rajzol semmit. */
public class FogHoles {

    private final List<Rectangle> holes = new ArrayList<>();

    public void addHole(Rectangle hole) {
        holes.add(hole);
    }

    public List<Rectangle> getHoles() {
        return holes;
    }
}