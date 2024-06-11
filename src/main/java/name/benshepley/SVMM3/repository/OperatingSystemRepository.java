package name.benshepley.SVMM3.repository;

import name.benshepley.SVMM3.model.filesystem.ProfileFileSystemModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

@Repository
public class OperatingSystemRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(OperatingSystemRepository.class);

    private final ApplicationSettingsRepository applicationSettingsRepository;

    @Autowired
    public OperatingSystemRepository(ApplicationSettingsRepository applicationSettingsRepository) {
        this.applicationSettingsRepository = applicationSettingsRepository;
    }

    public void openBrowser(String url) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(URI.create(url));
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    public void openExplorer(String directory) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.OPEN)) {
            try {
                desktop.open(new File(directory));
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    public void execute(String executable) {
        try {
            new ProcessBuilder(executable)
                .start();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void createProfile(ProfileFileSystemModel profileFileSystemModel) {
        try {
            var applicationSettingsModel = this.applicationSettingsRepository.restoreApplicationSettings();

            var profileDirectory = Path.of(applicationSettingsModel.getModsPath() + "\\" + profileFileSystemModel.getName());
            Files.createDirectory(profileDirectory);
            Files.createDirectory(Path.of(profileDirectory + "\\enabled"));
            Files.createDirectory(Path.of(profileDirectory + "\\disabled"));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }



}
