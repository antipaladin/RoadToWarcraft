package TestingDraw2D.t_3th_addFog;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DrawThePanels{

    public void showThePaint() {


        /*
        ÍGY EGYMÁS MELLÉ KERÜLNEK AZ OBJEKTUMOK!!

// Az egész ablakkészítést átadjuk az EDT-nek (Event Dispatch Thread), mert a
// Swing nem szálbiztos: minden Swing-objektumot ezen a szálon kell létrehozni.
// A () -> { ... } egy lambda: egy "csomagba tett kódrészlet", amit az EDT
// később, a saját idejében futtat le.
SwingUtilities.invokeLater(() -> {

    // Egy lista, aminek az elemtípusa JPanel. Mivel a MyPanel és a MyPanel2
    // is "extends JPanel", mindkettő betehető (polimorfizmus).
    // A List.of(...) módosíthatatlan listát ad: utólag nem lehet bővíteni.
    List<JPanel> myList  = List.of(new MyPanel(), new MyPanel2());

    // Maga az ablak, a címsorban a "Teszt" felirattal.
    JFrame frame = new JFrame("Teszt");

    // Az X gomb bezárja az ablakot ÉS leállítja a programot.
    // Enélkül az ablak csak eltűnne, a program a háttérben tovább futna.
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Egy köztes "tároló" panel. Erre azért van szükség, mert a JFrame
    // BorderLayout-ot használ, és ha több panelt tennénk bele közvetlenül,
    // mind a CENTER-be kerülne, és csak az utolsó látszana.
    // A FlowLayout egymás mellé rakja a gyerekeit:
    //   FlowLayout.CENTER  -> a sor középre igazítva
    //   0, 0               -> 0 px vízszintes és függőleges térköz (alapból 5 lenne)

    JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));

    // Végigmegyünk a listán, és minden panelt betesszük a tárolóba.
    // A ciklus nem tudja és nem is kell tudnia, hogy melyik elem MyPanel és
    // melyik MyPanel2: mindegyiket JPanel-ként kezeli.
    for (JPanel p : myList){
        container.add(p);
    }

    // A tárolót (és vele együtt a benne lévő paneleket) betesszük az ablakba.
    // Ez egyetlen komponens, így a CENTER helyen nincs ütközés.
    frame.add(container);

    // Az ablakot akkorára méretezi, hogy a tartalma pont elférjen.
    // Megkérdezi a tárolótól a kért méretét, az pedig a gyerekeiétől
    // (a setPreferredSize értékeitől): két 400x300-as panel egymás mellett
    // = 800x300-as ablaktartalom.
    frame.pack();

    // Megjeleníti az ablakot. Ekkor hívja meg a Swing először a panelek
    // paintComponent metódusát: előbb a tárolóét, aztán sorban a gyerekekét.
    frame.setVisible(true);
});
        */


        // ÍGY PEDIG EGYMÁSRA!! + négyzetháló telepítése
        SwingUtilities.invokeLater(() -> {
            List<JPanel> myList  = List.of(new MyPanel(), new MyPanel2(),
                    new GridPanel(
                            400, 300,

                            new Rectangle(100, 50, 200, 160),
                            20,
                            new Color(255, 255, 255, 100)),
                    new FogPanel()

            );

            JFrame frame = new JFrame("Teszt");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel parent = myList.get(0);                   // az alsó réteg

            for (int i = 1; i < myList.size(); i++) {
                JPanel child = myList.get(i);
                parent.setLayout(new BorderLayout());        // a gyerek töltse ki a szülőt
                parent.add(child);                           // a következő réteg BELE
                parent = child;                              // a következő kör ebbe tesz bele
            }

            frame.add(myList.get(0));

            frame.pack();
            frame.setVisible(true);        // ← itt hívja meg a Swing ELŐSZÖR
        });

    }
}
