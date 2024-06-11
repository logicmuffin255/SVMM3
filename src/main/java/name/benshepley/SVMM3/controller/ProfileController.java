package name.benshepley.SVMM3.controller;

import name.benshepley.SVMM3.repository.ApplicationSettingsRepository;
import name.benshepley.SVMM3.repository.FileSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.nio.file.Path;

@Controller
public class ProfileController {
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

        this.fileSystemRepository.createPath(Path.of(applicationSettingsModel.getModsPath() + "\\" + source));
        this.fileSystemRepository.createPath(Path.of(applicationSettingsModel.getModsPath() + "\\" + source + "\\" + "enabled"));
        this.fileSystemRepository.createPath(Path.of(applicationSettingsModel.getModsPath() + "\\" + source + "\\" + "disabled"));
    }

    public void moveProfile(String source, String destination) {
        var applicationSettingsModel = this.applicationSettingsRepository.restoreApplicationSettings();
        this.fileSystemRepository.movePath(Path.of(applicationSettingsModel.getModsPath() + "\\" + source), Path.of(applicationSettingsModel.getModsPath() + "\\" + destination));
    }

    public void copyProfile(String source, String destination) {
        var applicationSettingsModel = this.applicationSettingsRepository.restoreApplicationSettings();
        this.fileSystemRepository.copyPath(Path.of(applicationSettingsModel.getModsPath() + "\\" + source), Path.of(applicationSettingsModel.getModsPath() + "\\" + destination));
    }

    public void deleteProfile(String source) {
        var applicationSettingsModel = this.applicationSettingsRepository.restoreApplicationSettings();
        this.fileSystemRepository.deletePath(Path.of(applicationSettingsModel.getModsPath() + "\\" + source));
    }


}
