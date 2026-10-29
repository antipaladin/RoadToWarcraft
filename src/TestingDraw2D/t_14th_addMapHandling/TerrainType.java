package TestingDraw2D.t_14th_addMapHandling;


/**
 * Talajtípusok. A code a .wc2 fájlban szereplő szám,
 * a solidTile a summer.png-beli tiszta csempe sorszáma.
 */
public enum TerrainType {
    GROUND(0, 356),
    WATER(1, 328),
    COAST(2, 334);

    private final int code;
    private final int solidTile;

    TerrainType(int code, int solidTile) {
        this.code = code;
        this.solidTile = solidTile;
    }

    public int code() {
        return code;
    }

    public int solidTile() {
        return solidTile;
    }

    /** @throws IllegalArgumentException ha nincs ilyen kódú típus */
    public static TerrainType fromCode(int code) {
        for (TerrainType t : values()) {
            if (t.code == code) {
                return t;
            }
        }
        throw new IllegalArgumentException("ismeretlen talajkód: " + code);
    }
}