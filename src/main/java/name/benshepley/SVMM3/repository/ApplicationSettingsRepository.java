package name.benshepley.SVMM3.repository;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.annotation.PostConstruct;
import name.benshepley.SVMM3.model.application.settings.ApplicationSettingsModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Repository
public class ApplicationSettingsRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationSettingsRepository.class);

    private ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        this.objectMapper = JsonMapper.builder()
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                .enable(SerializationFeature.INDENT_OUTPUT)
            .build();
    }

    public ApplicationSettingsModel restoreApplicationSettings() {
        ApplicationSettingsModel applicationSettingsModel = null;
        try {
            File applictionSettingsFile = new File("applications-settings.json");
            if (!applictionSettingsFile.exists()) {
                applicationSettingsModel = new ApplicationSettingsModel();
                String applicationSettings = this.objectMapper.writeValueAsString(applicationSettingsModel);
                Files.writeString(applictionSettingsFile.toPath(), applicationSettings);
            }
            applicationSettingsModel = this.objectMapper.readValue(applictionSettingsFile, ApplicationSettingsModel.class);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return applicationSettingsModel;
    }

    public void storeApplicationSettings(ApplicationSettingsModel applicationSettingsModel) {
        try {
            File applictionSettingsFile = new File("applications-settings.json");
            String applicationSettings = this.objectMapper.writeValueAsString(applicationSettingsModel);
            Files.writeString(applictionSettingsFile.toPath(), applicationSettings);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
