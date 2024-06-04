package name.benshepley.SVMM3.controller;

import lombok.Getter;
import name.benshepley.SVMM3.model.application.settings.ApplicationSettingsModel;
import name.benshepley.SVMM3.repository.ApplicationSettingsRepository;
import name.benshepley.SVMM3.repository.OperatingSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {
    // Spring Beans:
    @Autowired
    private OperatingSystemRepository operatingSystemRepository;

    @Autowired
    private ApplicationSettingsRepository applicationSettingsRepository;

    // Events:
    @Getter
    public static class ExecuteProcessEvent extends ApplicationEvent {
        private final String executable;
        public ExecuteProcessEvent(Object source, String executable) {
            super(source);
            this.executable = executable;
        }
    }

    @Getter
    public static class StoreApplicationSettingsEvent extends ApplicationEvent {
        private final ApplicationSettingsModel applicationSettingsModel;
        public StoreApplicationSettingsEvent(Object source, ApplicationSettingsModel applicationSettingsModel) {
            super(source);
            this.applicationSettingsModel = applicationSettingsModel;
        }
    }

    @EventListener
    public void onApplicationEvent(String executable) {
        this.operatingSystemRepository.execute(executable);
    }

    @EventListener
    public void onApplicationEvent(StoreApplicationSettingsEvent storeApplicationSettingsEvent) {
        this.applicationSettingsRepository.storeApplicationSettings(storeApplicationSettingsEvent.getApplicationSettingsModel());
    }

}
