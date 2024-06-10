package name.benshepley.SVMM3.controller;

import name.benshepley.SVMM3.model.application.settings.ApplicationSettingsModel;
import name.benshepley.SVMM3.repository.ApplicationSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationSettingsController {
    private final ApplicationSettingsRepository applicationSettingsRepository;

    @Autowired
    public ApplicationSettingsController(ApplicationSettingsRepository applicationSettingsRepository) {
        this.applicationSettingsRepository = applicationSettingsRepository;
    }

    public void storeApplicationSettings(ApplicationSettingsModel applicationSetting) {
        this.applicationSettingsRepository.storeApplicationSettings(applicationSetting);
    }

    public ApplicationSettingsModel restoreApplicationSettings() {
        return this.applicationSettingsRepository.restoreApplicationSettings();
    }
}
