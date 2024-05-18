package name.benshepley.SVMM3.view;

import jakarta.annotation.PostConstruct;
import name.benshepley.SVMM3.view.component.GlobalSettingsDialog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class MainFrame extends JFrame {
    // Spring Beans:
    private final MainMenu mainMenu;
    private final MainProfileTabs mainProfileTabs;
    private final HeaderPanel headerPanel;

    // Components:
    private final GlobalSettingsDialog globalSettingsDialog;

    // Events:
    public static class MainMenuGlobalSettingsEvent extends ApplicationEvent {
        public MainMenuGlobalSettingsEvent(Object source) {
            super(source);
        }
    }

    // Listeners:
    @EventListener
    public void onApplicationEvent(MainMenuGlobalSettingsEvent ignoredEvent) {
        this.globalSettingsDialog.setVisible(true);
    }

    @Autowired
    public MainFrame(MainMenu mainMenu, MainProfileTabs mainProfileTabs, HeaderPanel headerPanel) {
        super("Stardew Mod Manager 3");

        this.mainMenu = mainMenu;
        this.mainProfileTabs = mainProfileTabs;
        this.headerPanel = headerPanel;
        this.globalSettingsDialog = new GlobalSettingsDialog(this);
    }

    @PostConstruct
    public void init() {
        super.setLayout(new BorderLayout());
        super.setSize(700, 700);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        super.setJMenuBar(this.mainMenu);
        super.add(this.headerPanel, BorderLayout.NORTH);
        super.add(this.mainProfileTabs, BorderLayout.CENTER);

        super.setVisible(true);
    }

}
