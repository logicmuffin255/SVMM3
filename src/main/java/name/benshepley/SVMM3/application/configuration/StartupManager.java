package name.benshepley.SVMM3.application.configuration;

import name.benshepley.SVMM3.model.application.settings.ProfileSettingsModel;
import name.benshepley.SVMM3.repository.ApplicationSettingsRepository;
import name.benshepley.SVMM3.view.MainPanel;
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
    private ApplicationSettingsRepository applicationSettingsRepository;

    @EventListener
    public void onApplicationEvent(ApplicationStartedEvent ignoredEvent) {
        for (ProfileSettingsModel profileSettings : this.applicationSettingsRepository.restoreApplicationSettings().getProfileSettingsModelList()) {
            this.applicationEventPublisher.publishEvent(new MainPanel.MainProfileTabsAddProfileEvent(this, profileSettings));
        }

        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getenv("LOCALAPPDATA"));
    }
}
