package name.benshepley.SVMM3.view.main;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class MainProfileTabs extends JTabbedPane {

    public MainProfileTabs() {
    }

    @PostConstruct
    public void init() {
        super.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        for (int i = 0; i < 10; i++) {
            JPanel jPanel = new JPanel();
            JLabel jLabel = new JLabel(Integer.toString(i));
            jPanel.add(jLabel);
            super.addTab(i + "Components", jPanel);
        }

    }

}
