package name.benshepley.SVMM3.controller;

import name.benshepley.SVMM3.repository.ApplicationSettingsRepository;
import name.benshepley.SVMM3.repository.FileSystemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Controller
public class ProfileController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);

    // Spring Beans:
    private final ApplicationSettingsRepository applicationSettingsRepository;
    private final FileSystemRepository fileSystemRepository;

    @Autowired
    public ProfileController(ApplicationSettingsRepository applicationSettingsRepository, FileSystemRepository fileSystemRepository) {
        this.applicationSettingsRepository = applicationSettingsRepository;
        this.fileSystemRepository = fileSystemRepository;
    }

    public void createProfile(String source) {
        var applicationSettingsModel = this.applicationSettingsRepository.restoreApplicationSettings();

        this.fileSystemRepository.mkdir(Path.of(applicationSettingsModel.getModsPath() + "\\" + source));
        this.fileSystemRepository.mkdir(Path.of(applicationSettingsModel.getModsPath() + "\\" + source + "\\" + "enabled"));
        this.fileSystemRepository.mkdir(Path.of(applicationSettingsModel.getModsPath() + "\\" + source + "\\" + "disabled"));
    }

    public void moveProfile(String source, String destination) {
        var applicationSettingsModel = this.applicationSettingsRepository.restoreApplicationSettings();
        this.fileSystemRepository.mv(Path.of(applicationSettingsModel.getModsPath() + "\\" + source), Path.of(applicationSettingsModel.getModsPath() + "\\" + destination));
    }

    public void copyProfile(String source, String destination) {
        var applicationSettingsModel = this.applicationSettingsRepository.restoreApplicationSettings();
        this.fileSystemRepository.cp(Path.of(applicationSettingsModel.getModsPath() + "\\" + source), Path.of(applicationSettingsModel.getModsPath() + "\\" + destination));
    }

    public void deleteProfile(String source) {
        var applicationSettingsModel = this.applicationSettingsRepository.restoreApplicationSettings();
        this.fileSystemRepository.rm(Path.of(applicationSettingsModel.getModsPath() + "\\" + source));
    }

    public void importExistingMods(String profileName) {
        var applicationSettingsModel = this.applicationSettingsRepository.restoreApplicationSettings();
        try (Stream<Path> files = Files.list(Path.of(applicationSettingsModel.getModsPath()))) {
            for (Path file : files.toList()) {
                if (file.getFileName().endsWith(profileName)) {
                    continue;
                }
                this.fileSystemRepository.mv(file, Path.of(applicationSettingsModel.getModsPath() + "\\" + profileName + "\\" + "enabled" + "\\" ));
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }


}
