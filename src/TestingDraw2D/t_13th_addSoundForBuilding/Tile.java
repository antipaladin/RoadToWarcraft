package TestingDraw2D.t_13th_addSoundForBuilding;

public class Tile {

    private static final int SIZE = 32;

    public static int toPx(int tiles){
        return tiles * SIZE;
    }

    public static int toTile(int px) {
        return Math.floorDiv(px, SIZE);
    }
}
