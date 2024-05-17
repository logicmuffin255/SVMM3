package name.benshepley.SVMM3.application.configuration;

import name.benshepley.SVMM3.model.ProfileSettingsModel;
import name.benshepley.SVMM3.service.SettingsService;
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

    @Autowired
    private SettingsService settingsService;

    @EventListener
    public void onApplicationEvent(ApplicationStartedEvent ignoredEvent) {
        for (ProfileSettingsModel profileSettings : this.settingsService.getApplicationSettings().getProfileSettingsModelList()) {
            this.applicationEventPublisher.publishEvent(new MainProfileTabs.MainProfileTabsAddProfileEvent(this, profileSettings));
        }
    }
}
