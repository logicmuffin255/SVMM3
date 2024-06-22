package name.benshepley.SVMM3.service;

import name.benshepley.SVMM3.model.application.ApplicationSyncStateModel;
import name.benshepley.SVMM3.model.application.event.SyncWithFileSystemEvent;
import name.benshepley.SVMM3.model.filesystem.ModFileSystemModel;
import name.benshepley.SVMM3.model.filesystem.ProfileFileSystemModel;
import name.benshepley.SVMM3.repository.ApplicationSettingsRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileSystemSyncService {
    private final ApplicationSettingsRepository applicationSettingsRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public FileSystemSyncService(ApplicationSettingsRepository applicationSettingsRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.applicationSettingsRepository = applicationSettingsRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    //TODO: Resume work here.
    public void sync() {
        var applicationsSettingsModel = this.applicationSettingsRepository.restoreApplicationSettings();
        var modsDirectory = Path.of(applicationsSettingsModel.getModsPath());

        var applicationSyncStateModel = new ApplicationSyncStateModel();
        var profileFileSystemList = new ArrayList<ProfileFileSystemModel>();

        for (var modDirectory : modsDirectory.toFile().listFiles()) {
            if (!modDirectory.isDirectory()) {
                continue;
            }
            var profileFileSystemModel = new ProfileFileSystemModel();
            boolean enabledExisted = false, disabledExisted = false;
            profileFileSystemModel.setName(modDirectory.getName());
            for (var profileDirectory : modDirectory.listFiles()) {
                if (profileDirectory.getName().equals("enabled")) {
                    enabledExisted = true;

                    List<ModFileSystemModel> enabledModsList = new ArrayList<>();
                    //TODO: Get data from mod:
                    for (var enabledMod : profileDirectory.list()) {
                        var modFileSystemModel = new ModFileSystemModel();
                        modFileSystemModel.setName(enabledMod);
                        enabledModsList.add(modFileSystemModel);
                    }
                    profileFileSystemModel.setEnabledMods(enabledModsList);
                }

                if (profileDirectory.getName().equals("disabled")) {
                    disabledExisted = true;

                    List<ModFileSystemModel> disabledModList = new ArrayList<>();
                    for (var disabledMods : profileDirectory.list()) {
                        var modFileSystemModel = new ModFileSystemModel();
                        modFileSystemModel.setName(modDirectory.getName());
                        disabledModList.add(modFileSystemModel);
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
