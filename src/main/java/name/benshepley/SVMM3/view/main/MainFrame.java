package name.benshepley.SVMM3.view.main;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class MainFrame extends JFrame  {

    // Spring Beans:
    private final MainMenu mainMenu;
    private final MainProfileTabs mainProfileTabs;

    @Autowired
    public MainFrame(MainMenu mainMenu, MainProfileTabs mainProfileTabs) {
        super("Stardew Mod Manager 3");
        this.mainMenu = mainMenu;
        this.mainProfileTabs = mainProfileTabs;
    }

    @PostConstruct
    public void init() {
        super.setSize(500, 500);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        super.setJMenuBar(this.mainMenu);
        super.add(this.mainProfileTabs);
    }

}
