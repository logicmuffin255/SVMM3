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
        JMenu fileMenu = new JMenu("File");

        JMenuItem fileGlobalSettingsItem = new JMenuItem("Global Settings");
        fileGlobalSettingsItem.addActionListener(a -> this.applicationEventPublisher.publishEvent(new MainFrame.MainMenuGlobalSettingsEvent(this, this.applicationSettingsRepository.restoreApplicationSettings())));
        fileMenu.add(fileGlobalSettingsItem);

        JMenuItem fileExitItem = new JMenuItem("Exit");
        fileExitItem.addActionListener(a -> System.exit(0));
        fileMenu.add(fileExitItem);

        super.add(fileMenu);

        // Edit:
        JMenu editMenu = new JMenu("Edit");

        JMenuItem editCopyProfile = new JMenuItem("Copy Profile");
        editMenu.add(editCopyProfile);

        JMenuItem editDeleteProfile = new JMenuItem("Delete Profile");
        editMenu.add(editDeleteProfile);

        editMenu.addSeparator();

        JMenuItem editCopyMods = new JMenuItem("Copy Mod(s)");
        editMenu.add(editCopyMods);

        JMenuItem editPasteMods = new JMenuItem("Paste Mod(s)");
        editMenu.add(editPasteMods);

        JMenuItem editViewClipboard = new JMenuItem("View Clipboard");
        editMenu.add(editViewClipboard);

        super.add(editMenu);

        // About:
        JMenu helpMenu = new JMenu("Help");

        JMenuItem helpAbout = new JMenuItem("About");
        helpMenu.add(helpAbout);

        super.add(helpMenu);
    }

}