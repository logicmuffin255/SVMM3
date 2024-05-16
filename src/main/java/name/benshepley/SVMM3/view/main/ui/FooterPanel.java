package name.benshepley.SVMM3.view.main.ui;

import name.benshepley.SVMM3.SVMM3Application;
import net.miginfocom.swing.MigLayout;
import org.springframework.context.ApplicationEvent;

import javax.swing.*;

public class FooterPanel extends javax.swing.JPanel {
    // Components:
    private final JButton playStardewButton;
    private final JButton openNexusModsButton;

    // Events:
    public static class OpenNexusModsEvent extends ApplicationEvent {
        public OpenNexusModsEvent(Object source) {
            super(source);
        }
    }

    public static class PlayStardewEvent extends ApplicationEvent {
        public PlayStardewEvent(Object source) {
            super(source);
        }
    }

    public FooterPanel() {
        this.playStardewButton = new JButton("Play Stardew");
        this.playStardewButton.addActionListener(a -> SVMM3Application.getContext().publishEvent(new FooterPanel.PlayStardewEvent(this)));
        this.openNexusModsButton = new JButton("Open Nexus Mods");
        this.openNexusModsButton.addActionListener(a -> SVMM3Application.getContext().publishEvent(new FooterPanel.OpenNexusModsEvent(this)));

        super.setLayout(new MigLayout("wrap 4", "[grow,fill]", "[grow,fill]"));
        super.add(playStardewButton, "span 2");
        super.add(openNexusModsButton, "span 2");

    }
}
