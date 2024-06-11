package name.benshepley.SVMM3.view;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import name.benshepley.SVMM3.controller.ApplicationSettingsController;
import name.benshepley.SVMM3.controller.OperatingSystemController;
import name.benshepley.SVMM3.model.application.event.SyncWithFileSystemEvent;
import name.benshepley.SVMM3.view.service.UiComponentSpringPrototypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Getter
@Component
public class MainFrame extends JFrame  {
    // Spring Beans:
    public final UiComponentSpringPrototypeFactory uiComponentSpringPrototypeFactory;
    public final ApplicationSettingsController applicationSettingsController;
    public final OperatingSystemController operatingSystemController;

    // Swing Components:
    public final MainMenu mainMenu;
    public final MainTabbedPane mainTabbedPane;

    // Listeners:
    @EventListener
    public void onApplicationEvent(SyncWithFileSystemEvent syncWithFileSystemEvent) {
        /* Fill in profiles, etc. */
    }

    @Autowired
    public MainFrame(UiComponentSpringPrototypeFactory uiComponentSpringPrototypeFactory, ApplicationSettingsController applicationSettingsController, OperatingSystemController operatingSystemController, MainMenu mainMenu, MainTabbedPane mainTabbedPane) {
        super("Stardew Mod Manager 3");
        this.uiComponentSpringPrototypeFactory = uiComponentSpringPrototypeFactory;

        this.applicationSettingsController = applicationSettingsController;
        this.operatingSystemController = operatingSystemController;

        this.mainMenu = mainMenu;
        this.mainTabbedPane = mainTabbedPane;
    }

    @PostConstruct
    public void init() {
        super.setLayout(new BorderLayout());
        super.setSize(700, 700);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        super.setJMenuBar(this.mainMenu);
        super.add(this.mainTabbedPane, BorderLayout.CENTER);

        super.setVisible(true);
    }

}
