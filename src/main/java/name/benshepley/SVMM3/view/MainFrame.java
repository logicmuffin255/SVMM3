package name.benshepley.SVMM3.view;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import name.benshepley.SVMM3.model.application.settings.ApplicationSettingsModel;
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
    private final MainPanel mainPanel;

    // Events:
    @Getter
    public static class ApplicationSettingsEvent extends ApplicationEvent {
        public final ApplicationSettingsModel applicationSettingsModel;
        public ApplicationSettingsEvent(Object source, ApplicationSettingsModel applicationSettingsModel) {
            super(source);
            this.applicationSettingsModel = applicationSettingsModel;
        }
    }

    // Listeners:
    @EventListener
    public void onApplicationEvent(ApplicationSettingsEvent applicationSettingsEvent) {
        GlobalSettingsDialog globalSettingsDialog = new GlobalSettingsDialog(this, applicationSettingsEvent.getApplicationSettingsModel());
        globalSettingsDialog.setVisible(true);
    }

    @Autowired
    public MainFrame(MainMenu mainMenu, MainPanel mainPanel) {
        super("Stardew Mod Manager 3");

        this.mainMenu = mainMenu;
        this.mainPanel = mainPanel;
    }

    @PostConstruct
    public void init() {
        super.setLayout(new BorderLayout());
        super.setSize(700, 700);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        super.setJMenuBar(this.mainMenu);
        super.add(this.mainPanel, BorderLayout.CENTER);

        super.setVisible(true);
    }

}
