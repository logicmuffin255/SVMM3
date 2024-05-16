package name.benshepley.SVMM3.application.configuration;

import name.benshepley.SVMM3.view.main.model.MainProfileTab;
import name.benshepley.SVMM3.view.main.service.MainManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupManager {

    @Autowired
    private MainManager mainManager;

    @EventListener
    public void onApplicationEvent(ApplicationStartedEvent ignoredEvent) {
        this.mainManager.startup();
        this.mainManager.addProfile(MainProfileTab.builder().title("Ben").build());

    }
}
