package TestingDraw2D.t_5th_addFogOfView;

public class Main {

    /*
    draw mypanel and after mypanel2 into mypanel

    solution:
    Amikor a Swing kirajzolja a MyPanel-t, ez a sorrend:
        1. MyPanel.paintComponent(g)    → piros háttér a teljes 400×300-on
        2. a MyPanel gyerekei: MyPanel2.paintComponent(g)   → kék téglalap, a pirosra rá

        C:\warcraft\project\dokumentation\Layout_managerek_FlowLayout_BorderLayout.pdf
     */

    public static void main(String[] args) {

    DrawThePanels newObjekt = new DrawThePanels();
    newObjekt.show();
    }
}
