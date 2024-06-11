package name.benshepley.SVMM3.view;


import name.benshepley.SVMM3.model.application.event.SyncWithFileSystemEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class MainTabbedPane extends JTabbedPane {
 //   private final ApplicationEventPublisher applicationEventPublisher;


    // Listeners:
    @EventListener
    public void onApplicationEvent(SyncWithFileSystemEvent syncWithFileSystemEvent) {
        /* Fill in profiles, etc. */
    }


    public MainTabbedPane() {
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
