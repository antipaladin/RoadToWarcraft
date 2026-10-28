package Warcraft2;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

/**
 * A HUD geometriája 640x480-as logikai felbontásra.
 * Csak adat, nincs benne rajzolás – a renderer ÉS (később) az input
 * kezelés is innen dolgozik, így a kettő sosem csúszik szét.
 *
 * Forrás: Wargus scripts/human/ui_pandora.lua
 */
public final class HudLayout {

    public static final int TILE_SIZE = 32;

    // --- A képernyő ---
    public final int width = 640;
    public final int height = 480;

    // --- Területek, ahová MI rajzolunk tartalmat ---
    /** Térképnézet: 14x14 csempe. */
    public final Rectangle mapArea = new Rectangle(176, 16, 448, 448);
    /** Minimap belseje (a keret képen belül). */
    public final Rectangle minimapArea = new Rectangle(24, 26, 128, 128);

    // --- HUD képdarabok helye ---
    public final Point menuButtonPos = new Point(0, 0);         // natív méret
    public final Point minimapFramePos = new Point(0, 24);      // natív méret
    public final Rectangle infoPanel = new Rectangle(0, 160, 176, 176);
    public final Rectangle buttonPanel = new Rectangle(0, 336, 176, 144);
    public final Rectangle resourceBar = new Rectangle(176, 0, 448, 16);
    public final Rectangle statusLine = new Rectangle(176, 464, 448, 16);
    public final Rectangle fillerRight = new Rectangle(624, 0, 16, 480);

    // --- Erőforrás kijelzés (gold, wood, oil) ---
    public static final int RESOURCE_ICON_SIZE = 14;
    public final List<Point> resourceIcons = List.of(
            new Point(176, 0),
            new Point(176 + 75, 0),
            new Point(176 + 150, 0));
    /** A szám az ikontól ennyivel jobbra kezdődik. */
    public final int resourceTextOffsetX = 18;

    // --- Státuszsor szöveg ---
    public final Point statusTextPos = new Point(176 + 2, 480 + 2 - 16);

    // --- Parancsgombok (3x3) bal felső sarkai ---
    public final List<Point> commandButtons = List.of(
            new Point(9, 340), new Point(65, 340), new Point(121, 340),
            new Point(9, 387), new Point(65, 387), new Point(121, 387),
            new Point(9, 434), new Point(65, 434), new Point(121, 434));

    /** Hány csempe fér ki a térképnézetbe vízszintesen / függőlegesen. */
    public int visibleTilesX() {
        return mapArea.width / TILE_SIZE;
    }

    public int visibleTilesY() {
        return mapArea.height / TILE_SIZE;
    }
}