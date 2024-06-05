package name.benshepley.SVMM3.view;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import name.benshepley.SVMM3.model.application.PopupConfigurationModel;
import name.benshepley.SVMM3.model.application.settings.ApplicationSettingsModel;
import name.benshepley.SVMM3.view.component.dialog.GlobalSettingsDialog;
import name.benshepley.SVMM3.view.component.dialog.PopupDialog;
import name.benshepley.SVMM3.view.component.dialog.ProfileSettingsDialog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;


@Getter
@Component
public class MainFrame extends JFrame  {
    // Spring Beans:
    private final ApplicationEventPublisher applicationEventPublisher;
    private final MainMenu mainMenu;
    private final MainTabbedPane mainTabbedPane;

    // Events:
    @Getter
    public static class ShowPopupDialogEvent extends ApplicationEvent {
        public final PopupConfigurationModel popupConfigurationModel;
        public ShowPopupDialogEvent(Object source, PopupConfigurationModel popupConfigurationModel) {
            super(source);
            this.popupConfigurationModel = popupConfigurationModel;
        }
    }

    @Getter
    public static class ShowGlobalSettingsDialogEvent extends ApplicationEvent {
        public final ApplicationSettingsModel applicationSettingsModel;
        public ShowGlobalSettingsDialogEvent(Object source, ApplicationSettingsModel applicationSettingsModel) {
            super(source);
            this.applicationSettingsModel = applicationSettingsModel;
        }
    }

    @Getter
    public static class ShowProfileSettingsDialogEvent extends ApplicationEvent {
        public final ApplicationSettingsModel applicationSettingsModel;
        public ShowProfileSettingsDialogEvent(Object source, ApplicationSettingsModel applicationSettingsModel) {
            super(source);
            this.applicationSettingsModel = applicationSettingsModel;
        }
    }

    // Listeners:
    @EventListener
    public void onApplicationEvent(ShowPopupDialogEvent showPopupDialogEvent) {
        PopupDialog popupDialog = new PopupDialog(this, showPopupDialogEvent.getPopupConfigurationModel());
        popupDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        popupDialog.setSize(300, 200);
        popupDialog.setLocation((this.getWidth() - popupDialog.getWidth()) / 2, (this.getHeight() - popupDialog.getHeight()) / 2);
        popupDialog.setVisible(true);
    }

    @EventListener
    public void onApplicationEvent(ShowGlobalSettingsDialogEvent showGlobalSettingsDialogEvent) {
        GlobalSettingsDialog globalSettingsDialog = new GlobalSettingsDialog(this);
        globalSettingsDialog.loadSettings(showGlobalSettingsDialogEvent.getApplicationSettingsModel());
        globalSettingsDialog.pack();
        globalSettingsDialog.setVisible(true);
    }

    @EventListener
    public void onApplicationEvent(ShowProfileSettingsDialogEvent showProfileSettingsDialogEvent) {
        ProfileSettingsDialog profileSettingsDialog = new ProfileSettingsDialog(this);
        profileSettingsDialog.loadSettings(showProfileSettingsDialogEvent.applicationSettingsModel, null);
        profileSettingsDialog.pack();
        profileSettingsDialog.setVisible(true);
    }


    @Autowired
    public MainFrame(ApplicationEventPublisher applicationEventPublisher, MainMenu mainMenu, MainTabbedPane mainTabbedPane) {
        super("Stardew Mod Manager 3");

        this.applicationEventPublisher = applicationEventPublisher;
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
