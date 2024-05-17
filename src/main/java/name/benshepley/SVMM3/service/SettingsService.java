package name.benshepley.SVMM3.service;

import name.benshepley.SVMM3.model.ApplicationSettingsModel;
import name.benshepley.SVMM3.model.GlobalSettingsModel;
import name.benshepley.SVMM3.repository.ApplicationSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {

    @Autowired
    private ApplicationSettingsRepository applicationSettingsRepository;

    public void saveApplicationSettings(ApplicationSettingsModel applicationSettings) {
        this.applicationSettingsRepository.saveApplicationSettings(applicationSettings);
    }

    public ApplicationSettingsModel getApplicationSettings() {
        return this.applicationSettingsRepository.getApplicationSettings();
    }

    public GlobalSettingsModel getGlobalSettings() {
        return this.applicationSettingsRepository.getApplicationSettings().getGlobalSettingsModel();
    }

}
