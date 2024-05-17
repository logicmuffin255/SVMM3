package name.benshepley.SVMM3.view;


import jakarta.annotation.PostConstruct;
import name.benshepley.SVMM3.model.MainProfileTabModel;
import name.benshepley.SVMM3.view.component.ProfilePanel;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class MainProfileTabs extends JTabbedPane {
    // Events:
    public static class MainProfileTabsAddProfileEvent extends ApplicationEvent {
        private MainProfileTabModel mainProfileTabModel;

        public MainProfileTabsAddProfileEvent(Object source) {
            super(source);
        }

        public void setMainProfileTab(MainProfileTabModel mainProfileTabModel) {
            this.mainProfileTabModel = mainProfileTabModel;
        }

        public MainProfileTabModel getMainProfileTab() {
            return this.mainProfileTabModel;
        }
    }

    // Listeners:
    @EventListener
    public void onApplicationEvent(MainProfileTabsAddProfileEvent mainProfileTabsAddProfileEvent) {
        ProfilePanel profilePanel = new ProfilePanel();
        super.insertTab(mainProfileTabsAddProfileEvent.getMainProfileTab().getTitle(), null, profilePanel, null, 0);
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
