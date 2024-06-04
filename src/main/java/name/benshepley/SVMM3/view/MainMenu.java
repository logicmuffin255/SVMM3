package name.benshepley.SVMM3.view;


import jakarta.annotation.PostConstruct;
import name.benshepley.SVMM3.repository.ApplicationSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class MainMenu extends JMenuBar {
    // Spring Beans:
    private final ApplicationSettingsRepository applicationSettingsRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    // Constructor:
    @Autowired
    public MainMenu(ApplicationEventPublisher applicationEventPublisher, ApplicationSettingsRepository applicationSettingsRepository) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.applicationSettingsRepository = applicationSettingsRepository;
    }

    @PostConstruct
    public void init() {
        // File:
        JMenu applicationMenu = new JMenu("Application");

        JMenuItem applicationSettingsMenuItem = new JMenuItem("Application Settings");
        applicationSettingsMenuItem.addActionListener(a -> this.applicationEventPublisher.publishEvent(new MainFrame.ShowGlobalSettingsDialogEvent(this, this.applicationSettingsRepository.restoreApplicationSettings())));
        applicationMenu.add(applicationSettingsMenuItem);

        JMenuItem fileExitItem = new JMenuItem("Exit");
        fileExitItem.addActionListener(a -> System.exit(0));
        applicationMenu.add(fileExitItem);

        super.add(applicationMenu);

        // Edit:
        JMenu profileMenu = new JMenu("Profile");

        JMenuItem profileSettingsMenuItem = new JMenuItem("Profile Settings");
        profileMenu.add(profileSettingsMenuItem);

        JMenuItem copyProfileMenuItem = new JMenuItem("Copy Profile");
        profileMenu.add(copyProfileMenuItem);

        JMenuItem deleteProfileMenuItem = new JMenuItem("Delete Profile");
        profileMenu.add(deleteProfileMenuItem);

        super.add(profileMenu);

        // About:
        JMenu helpMenu = new JMenu("Help");

        JMenuItem helpAbout = new JMenuItem("About");
        helpMenu.add(helpAbout);

        super.add(helpMenu);
    }

}