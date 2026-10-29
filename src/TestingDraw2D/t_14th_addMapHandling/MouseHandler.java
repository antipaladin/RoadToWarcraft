package TestingDraw2D.t_14th_addMapHandling;



import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/** Az egérkattintások kezelése. */
public class MouseHandler extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        int px = e.getX() + Camera.getX();  // hova kattintottak a TÉRKÉPEN, pixelben
        int py = e.getY() + Camera.getY();
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