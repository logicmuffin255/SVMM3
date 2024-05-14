package name.benshepley.SVMM3.view.main;

import javax.swing.*;

public class MainMenu extends JMenuBar {
    public MainMenu() {
        JMenu jMenu = new JMenu("File");
        JMenuItem fileGlobalSettingsItem = new JMenuItem("Global Settings");
        JMenuItem fileExitItem = new JMenuItem("Exit");
        jMenu.add(fileGlobalSettingsItem);
        jMenu.add(fileExitItem);
        super.add(jMenu);
    }
}