package name.benshepley.SVMM3.view.main;

import javax.swing.*;

public class MainFrame extends JFrame  {

    public MainFrame() {
        super("Stardew Mod Manager 3");
        super.add(new MainMenu());
        super.setSize(500, 500);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }

}
