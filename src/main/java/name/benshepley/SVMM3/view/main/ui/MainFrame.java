package name.benshepley.SVMM3.view.main.ui;


import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame  {

    private final MainMenu mainMenu;
    private final MainProfileTabs mainProfileTabs;
    private final FooterPanel footerPanel;

    public MainFrame() {
        super("Stardew Mod Manager 3");

        this.mainMenu = new MainMenu();
        this.mainProfileTabs = new MainProfileTabs();
        this.footerPanel = new FooterPanel();

        super.setLayout(new BorderLayout());
        super.setSize(500, 500);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        super.setJMenuBar(this.mainMenu);
        super.add(this.mainProfileTabs, BorderLayout.NORTH);
        super.add(this.footerPanel, BorderLayout.SOUTH);

        super.setVisible(true);
    }

}
