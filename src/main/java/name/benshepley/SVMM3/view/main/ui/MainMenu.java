package name.benshepley.SVMM3.view.main.ui;


import name.benshepley.SVMM3.SVMM3Application;
import org.springframework.context.ApplicationEvent;

import javax.swing.*;

public class MainMenu extends JMenuBar {

    // Events:
    public static class MainMenuGlobalSettingsActionEvent extends ApplicationEvent {
        public MainMenuGlobalSettingsActionEvent(Object source) {
            super(source);
        }
    }

    public MainMenu() {
        // File:
        JMenu fileMenu = new JMenu("File");

        JMenuItem fileGlobalSettingsItem = new JMenuItem("Global Settings");
        fileGlobalSettingsItem.addActionListener(a -> SVMM3Application.getContext().publishEvent(new MainMenuGlobalSettingsActionEvent(this)));
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