package TestingDraw2D.t_14th_addMapHandling;

/** A kamera: a térkép melyik része látszik. Minden érték PIXELBEN. */
public class Camera {

    private static int x = 0;              // a látható rész bal felső sarka a térképen
    private static int y = 0;
    private static int maxX = 0;           // ennél tovább nem lehet görgetni
    private static int maxY = 0;

    /** Beállítja, meddig lehet görgetni: a térkép mérete mínusz a látható terület. */
    public static void setLimits(int mapWidthPx, int mapHeightPx, int viewWidthPx, int viewHeightPx) {
        maxX = Math.max(0, mapWidthPx - viewWidthPx);
        maxY = Math.max(0, mapHeightPx - viewHeightPx);
    }

    /** Elmozdítja a kamerát, de nem engedi ki a térképről. */
    public static void move(int dx, int dy) {
        x = Math.max(0, Math.min(maxX, x + dx));
        y = Math.max(0, Math.min(maxY, y + dy));
    }

    public static int getX() { return x; }
    public static int getY() { return y; }
}