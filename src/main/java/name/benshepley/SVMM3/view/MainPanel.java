package name.benshepley.SVMM3.view;


import jakarta.annotation.PostConstruct;
import lombok.Getter;
import name.benshepley.SVMM3.model.application.settings.ProfileSettingsModel;
import name.benshepley.SVMM3.view.component.ProfilePanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class MainPanel extends JTabbedPane {
    private final ApplicationEventPublisher applicationEventPublisher;

    // Events:
    @Getter
    public static class MainProfileTabsAddProfileEvent extends ApplicationEvent {
        private final ProfileSettingsModel profileSettingsModel;
        public MainProfileTabsAddProfileEvent(Object source, ProfileSettingsModel profileSettingsModel) {
            super(source);
            this.profileSettingsModel = profileSettingsModel;
        }
    }

    // Listeners:
    @EventListener
    public void onApplicationEvent(MainProfileTabsAddProfileEvent mainProfileTabsAddProfileEvent) {
        ProfilePanel profilePanel = new ProfilePanel(this.applicationEventPublisher, mainProfileTabsAddProfileEvent.getProfileSettingsModel());
        super.insertTab(mainProfileTabsAddProfileEvent.getProfileSettingsModel().getName(), null, profilePanel, null, 0);
        super.setSelectedIndex(super.getTabCount() -2);
    }

    @Autowired
    public MainPanel(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }


    @PostConstruct
    public void init() {
        super.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        JPanel addPanel = new JPanel();
        super.insertTab("➕", null, addPanel, null, 0);
        super.addChangeListener(e -> {
            if (super.getSelectedIndex() == super.getTabCount() - 1) {
                System.out.println("IT is time, captain.");
            }
        });
    }

}
