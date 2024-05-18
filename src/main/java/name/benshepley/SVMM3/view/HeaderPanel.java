package name.benshepley.SVMM3.view;

import jakarta.annotation.PostConstruct;
import name.benshepley.SVMM3.controller.MainController;
import net.miginfocom.swing.MigLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class HeaderPanel extends javax.swing.JPanel {
    // Spring Beans:
    private final ApplicationEventPublisher applicationEventPublisher;

    // Components:
    private JButton playStardewButton;
    private JButton openNexusModsButton;


    // Constructor
    @Autowired
    public HeaderPanel(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @PostConstruct
    public void init() {
        this.playStardewButton = new JButton("Play Stardew");
        this.playStardewButton.addActionListener(a -> this.applicationEventPublisher.publishEvent(new MainController.PlayStardewEvent(this)));
        this.openNexusModsButton = new JButton("Open Nexus Mods");
        this.openNexusModsButton.addActionListener(a -> this.applicationEventPublisher.publishEvent(new MainController.OpenNexusModsEvent(this)));

        super.setLayout(new MigLayout("wrap 4", "[grow,fill]", "[grow,fill]"));
        super.add(playStardewButton, "span 2");
        super.add(openNexusModsButton, "span 2");
    }
}
