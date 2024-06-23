package name.benshepley.SVMM3.service;

import name.benshepley.SVMM3.model.application.ApplicationSyncStateModel;
import name.benshepley.SVMM3.model.application.event.SyncWithFileSystemEvent;
import name.benshepley.SVMM3.model.filesystem.ModModel;
import name.benshepley.SVMM3.model.filesystem.ModDataModel;
import name.benshepley.SVMM3.model.filesystem.ProfileModel;
import name.benshepley.SVMM3.repository.ApplicationSettingsRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileSystemSyncService {
    private final ApplicationSettingsRepository applicationSettingsRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ModJsonParserService modJsonParserService;

    public FileSystemSyncService(ApplicationSettingsRepository applicationSettingsRepository, ApplicationEventPublisher applicationEventPublisher, ModJsonParserService modJsonParserService) {
        this.applicationSettingsRepository = applicationSettingsRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.modJsonParserService = modJsonParserService;
    }

    public void sync() throws IOException {
        var applicationsSettingsModel = this.applicationSettingsRepository.restoreApplicationSettings();
        var modsDirectory = Path.of(applicationsSettingsModel.getModsPath());

        var applicationSyncStateModel = new ApplicationSyncStateModel();
        var profileFileSystemList = new ArrayList<ProfileModel>();

        for (var modDirectory : modsDirectory.toFile().listFiles()) {
            if (!modDirectory.isDirectory()) {
                continue;
            }
            var profileFileSystemModel = new ProfileModel();
            boolean enabledExisted = false, disabledExisted = false;
            profileFileSystemModel.setName(modDirectory.getName());
            for (var profileDirectory : modDirectory.listFiles()) {
                if (profileDirectory.getName().equals("enabled")) {
                    enabledExisted = true;

                    List<ModModel> enabledModsList = new ArrayList<>();
                    for (var enabledModDirectory : profileDirectory.list()) {
                        var modDataModel = new ModDataModel();
                        modDataModel.setName(enabledModDirectory);
                        modDataModel.setEnabled(true);
                        var modManifestDataModel = this.modJsonParserService.parseManifestJson(Files.readString(Path.of(profileDirectory + "\\" + enabledModDirectory + "\\" + "manifest.json")));
                        enabledModsList.add(new ModModel(enabledModDirectory, modDataModel, modManifestDataModel));
                    }
                    profileFileSystemModel.setEnabledMods(enabledModsList);
                }

                if (profileDirectory.getName().equals("disabled")) {
                    disabledExisted = true;

                    List<ModModel> disabledModList = new ArrayList<>();
                    for (var disabledModDirectory : profileDirectory.list()) {
                        var modDataModel = new ModDataModel();
                        modDataModel.setName(disabledModDirectory);
                        modDataModel.setEnabled(false);
                        var modManifestDataModel = this.modJsonParserService.parseManifestJson(Files.readString(Path.of(profileDirectory + "\\" + disabledModDirectory + "\\" + "manifest.json")));
                        disabledModList.add(new ModModel(disabledModDirectory, modDataModel, modManifestDataModel));
                    }
                    profileFileSystemModel.setEnabledMods(disabledModList);
                }

                if (enabledExisted && disabledExisted) {
                    profileFileSystemList.add(profileFileSystemModel);
                }
            }
        }

        applicationSyncStateModel.setApplicationSettings(applicationsSettingsModel);
        applicationSyncStateModel.setProfileFileSystem(profileFileSystemList);

        this.applicationEventPublisher.publishEvent(new SyncWithFileSystemEvent(this, applicationSyncStateModel));

    }
}
