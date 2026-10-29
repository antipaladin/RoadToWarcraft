package TestingDraw2D.t_13th_addSoundForBuilding;



import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/** Az egérkattintások kezelése. */
public class MouseHandler extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        int px = e.getX();                  // hova kattintottak, pixelben
        int py = e.getY();
        int col = Tile.toTile(px);          // ... és csempében
        int row = Tile.toTile(py);

        System.out.println("Kattintás: csempe (" + col + ", " + row + ")");

        for (Building b : BuildingList.getBuildingFromList()) {
            if (b.contains(px, py)) {
                System.out.println("  → épület: " + b.getType().getDisplayName());
                b.playSound();
            }
        }
    }
}