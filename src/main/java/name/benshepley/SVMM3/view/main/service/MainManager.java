package name.benshepley.SVMM3.view.main.service;

import name.benshepley.SVMM3.view.global_settings.ui.GlobalSettingsDialog;
import name.benshepley.SVMM3.view.main.model.MainProfileTab;
import name.benshepley.SVMM3.view.main.ui.MainFrame;
import name.benshepley.SVMM3.view.main.ui.MainMenu;
import name.benshepley.SVMM3.view.main.ui.MainProfileTabs;
import name.benshepley.SVMM3.view.main.ui.ProfilePanel;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class MainManager {
    private MainFrame mainFrame;
    private MainMenu mainMenu;
    private MainProfileTabs mainProfileTabs;

    private GlobalSettingsDialog globalSettingsDialog;

    public void startup() {
        this.mainFrame = new MainFrame();
        this.mainMenu = new MainMenu();
        this.mainProfileTabs = new MainProfileTabs();

        this.mainFrame.setJMenuBar(this.mainMenu);
        this.mainFrame.add(this.mainProfileTabs);

        JPanel addPanel = new JPanel();
        this.mainProfileTabs.insertTab("➕", null, addPanel, null, 0);
        this.mainProfileTabs.addChangeListener(e -> {
            if (mainProfileTabs.getSelectedIndex() == mainProfileTabs.getTabCount() -1 ) {
                System.out.println("IT is time, captain.");
            }
        });

        this.globalSettingsDialog = new GlobalSettingsDialog(mainFrame);
    }

    public void showGlobalSettings() {
        this.globalSettingsDialog.setVisible(true);
    }

    public void addProfile(MainProfileTab mainProfileTab) {
        ProfilePanel profilePanel = new ProfilePanel();
        this.mainProfileTabs.insertTab(mainProfileTab.getTitle(), null, profilePanel, null, 0);
    }
}
