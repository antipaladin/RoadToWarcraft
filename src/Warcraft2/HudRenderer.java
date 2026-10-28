package Warcraft2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

/**
 * A HUD keret és a rá kerülő szövegek kirajzolása.
 * Nem tud semmit a játéklogikáról: amit ki kell írni, azt paraméterben kapja.
 */
public class HudRenderer {

    private static final String UI_ROOT = "/graphics/ui/";

    private final HudLayout layout;

    private final BufferedImage menuButton;
    private final BufferedImage minimapFrame;
    private final BufferedImage infoPanel;
    private final BufferedImage buttonPanel;
    private final BufferedImage resourceBar;
    private final BufferedImage statusLine;
    private final BufferedImage fillerRight;
    private final BufferedImage resourceIcons; // gold,wood,oil,mana – 14x14-es képkockák

    private final Font hudFont = new Font(Font.SANS_SERIF, Font.BOLD, 10);
    private final Color textColor = new Color(255, 255, 255);

    /** Hogy a méreteltérést csak egyszer írjuk ki, ne minden frame-ben. */
    private final Set<BufferedImage> reportedMismatch = new HashSet<>();

    public HudRenderer(HudLayout layout, Race race) {
        this.layout = layout;
        String raceDir = UI_ROOT + race.folder() + "/";

        menuButton    = ImageLoader.load(raceDir + "menubutton.png");
        minimapFrame  = ImageLoader.load(raceDir + "minimap.png");
        infoPanel     = ImageLoader.load(raceDir + "infopanel.png");
        buttonPanel   = ImageLoader.load(raceDir + "buttonpanel.png");
        resourceBar   = ImageLoader.load(raceDir + "resource.png");
        statusLine    = ImageLoader.load(raceDir + "statusline.png");
        fillerRight   = ImageLoader.load(raceDir + "filler-right.png");
        resourceIcons = ImageLoader.load(UI_ROOT + "gold,wood,oil,mana.png");
    }

    /**
     * @param resources  gold, wood, oil – ebben a sorrendben
     * @param statusText az alsó státuszsor szövege (lehet üres)
     */
    public void render(Graphics2D g, int[] resources, String statusText) {
        // 1. Keret darabok
        drawFitted(g, fillerRight, layout.fillerRight);
        drawFitted(g, resourceBar, layout.resourceBar);
        drawFitted(g, statusLine, layout.statusLine);
        drawFitted(g, buttonPanel, layout.buttonPanel);
        drawFitted(g, infoPanel, layout.infoPanel);

        // Ezek natív méretben, a többire rá
        g.drawImage(menuButton, layout.menuButtonPos.x, layout.menuButtonPos.y, null);
        g.drawImage(minimapFrame, layout.minimapFramePos.x, layout.minimapFramePos.y, null);

        // 2. Erőforrások
        g.setFont(hudFont);
        for (int i = 0; i < layout.resourceIcons.size() && i < resources.length; i++) {
            Point p = layout.resourceIcons.get(i);
            drawFrame(g, resourceIcons, i, HudLayout.RESOURCE_ICON_SIZE, p.x, p.y);
            drawText(g, String.valueOf(resources[i]), p.x + layout.resourceTextOffsetX, p.y + 12);
        }

        // 3. Státuszsor
        if (statusText != null && !statusText.isEmpty()) {
            Point s = layout.statusTextPos;
            drawText(g, statusText, s.x, s.y + 11);
        }
    }

    /**
     * Ha a kép pontosan akkora, mint a hely: egyszerűen kirajzolja.
     * Ha nem: csempézi (ismétli) a területen belül. Pixel artnál ez szebb,
     * mint a nyújtás, ami eltorzítja a pixeleket.
     */
    private void drawFitted(Graphics2D g, BufferedImage img, Rectangle r) {
        if (img.getWidth() == r.width && img.getHeight() == r.height) {
            g.drawImage(img, r.x, r.y, null);
            return;
        }
        if (reportedMismatch.add(img)) {
            System.out.printf("[HudRenderer] méreteltérés: kép %dx%d, hely %dx%d @(%d,%d) -> csempézés%n",
                    img.getWidth(), img.getHeight(), r.width, r.height, r.x, r.y);
        }
        Shape oldClip = g.getClip();
        g.clipRect(r.x, r.y, r.width, r.height);
        for (int y = r.y; y < r.y + r.height; y += img.getHeight()) {
            for (int x = r.x; x < r.x + r.width; x += img.getWidth()) {
                g.drawImage(img, x, y, null);
            }
        }
        g.setClip(oldClip);
    }

    /**
     * Egy képkocka kirajzolása egy sprite sheetből.
     * A képkockák balról jobbra, majd soronként lefelé következnek.
     */
    private void drawFrame(Graphics2D g, BufferedImage sheet, int index, int size, int dx, int dy) {
        int perRow = Math.max(1, sheet.getWidth() / size);
        int sx = (index % perRow) * size;
        int sy = (index / perRow) * size;
        if (sy + size > sheet.getHeight()) {
            return; // nincs ilyen képkocka (pl. placeholder képnél)
        }
        g.drawImage(sheet,
                dx, dy, dx + size, dy + size,   // cél
                sx, sy, sx + size, sy + size,   // forrás
                null);
    }

    /** Szöveg 1 px-es fekete árnyékkal, hogy a mintás háttéren is olvasható legyen. */
    private void drawText(Graphics2D g, String text, int x, int baselineY) {
        g.setColor(Color.BLACK);
        g.drawString(text, x + 1, baselineY + 1);
        g.setColor(textColor);
        g.drawString(text, x, baselineY);
    }
}