package name.benshepley.SVMM3.repository;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.annotation.PostConstruct;
import name.benshepley.SVMM3.model.application.settings.ApplicationSettingsModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Repository
public class ApplicationSettingsRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationSettingsRepository.class);

    @Value("${LOCALAPPDATA}")
    private String APPLICATION_SETTINGS_PATH;

    private ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        this.objectMapper = JsonMapper.builder().configure(
                    MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
            .build();
    }

    public ApplicationSettingsModel restoreApplicationSettings() {
        ApplicationSettingsModel applicationSettingsModel = null;
        try {
            applicationSettingsModel = this.objectMapper.readValue(new File(APPLICATION_SETTINGS_PATH + "\\SVMM3\\" + "applications-settings.json"), ApplicationSettingsModel.class);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return applicationSettingsModel;
    }

    public void storeApplicationSettings(ApplicationSettingsModel applicationSettingsModel) {
        try {
            String applicationSettings = this.objectMapper.writeValueAsString(applicationSettingsModel);
            Files.writeString(Paths.get(APPLICATION_SETTINGS_PATH + "\\SVMM3\\" + "applications-settings.json"), applicationSettings);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
