package name.benshepley.SVMM3.repository;

import name.benshepley.SVMM3.model.ApplicationSettingsModel;
import name.benshepley.SVMM3.model.GlobalSettingsModel;
import name.benshepley.SVMM3.model.ProfileSettingsModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApplicationSettingsRepository {
    private ApplicationSettingsModel applicationSettings;

    public ApplicationSettingsRepository() {
        this.applicationSettings = ApplicationSettingsModel.builder().
                globalSettingsModel(GlobalSettingsModel.builder()
                        .stardewPath("C:\\Program Files (x86)\\GOG Galaxy\\Games\\Stardew Valley\\")
                        .nexusModsAPIKey("JPJ3s7BvR38HgTgilb5LUyFOLgR814O2bP9kBu+hqfv0W0jVDWOLT1k=--3Q7Y+hAoO4pM2/V4--n/XNj1/1DylU9RxxeSUHEQ==")
                        .build())
                .profileSettingsModelList(List.of(ProfileSettingsModel.builder().name("Ben").build()))
                .build();
    }

    public ApplicationSettingsModel getApplicationSettings() {
        return this.applicationSettings;
    }

    public void saveApplicationSettings(ApplicationSettingsModel applicationSettings) {
        this.applicationSettings = applicationSettings;
    }
}
