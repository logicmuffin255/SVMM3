package name.benshepley.SVMM3.application.configuration;

import name.benshepley.SVMM3.model.MainProfileTabModel;
import name.benshepley.SVMM3.view.MainProfileTabs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupManager {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @EventListener
    public void onApplicationEvent(ApplicationStartedEvent ignoredEvent) {
        MainProfileTabs.MainProfileTabsAddProfileEvent mainProfileTabsAddProfileEvent = new MainProfileTabs.MainProfileTabsAddProfileEvent(this, MainProfileTabModel.builder().title("Ben").build());
        this.applicationEventPublisher.publishEvent(mainProfileTabsAddProfileEvent);
    }
}
