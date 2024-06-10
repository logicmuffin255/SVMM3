package name.benshepley.SVMM3.view;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class MainTabbedPane extends JTabbedPane {
 //   private final ApplicationEventPublisher applicationEventPublisher;

    // Events:
//    @Getter
//    public static class MainProfileTabsAddProfileEvent extends ApplicationEvent {
//        private final ProfileSettingsModel profileSettingsModel;
//        public MainProfileTabsAddProfileEvent(Object source, ProfileSettingsModel profileSettingsModel) {
//            super(source);
//            this.profileSettingsModel = profileSettingsModel;
//        }
//    }
//
//    // Listeners:
//    @EventListener
//    public void onApplicationEvent(MainProfileTabsAddProfileEvent mainProfileTabsAddProfileEvent) {
//        ProfileTabPanel profileTabPanel = new ProfileTabPanel(this.applicationEventPublisher, mainProfileTabsAddProfileEvent.getProfileSettingsModel());
//        super.insertTab(mainProfileTabsAddProfileEvent.getProfileSettingsModel().getName(), null, profileTabPanel, null, 0);
//        super.setSelectedIndex(super.getTabCount() -2);
//    }



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
