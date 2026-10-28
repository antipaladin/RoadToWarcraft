package Warcraft2;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Warcraft");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.add(new GamePanel(Race.HUMAN));
            frame.pack();                       // a panel preferredSize-ához méretez
            frame.setLocationRelativeTo(null);  // képernyő közepére
            frame.setVisible(true);
        });
    }
}