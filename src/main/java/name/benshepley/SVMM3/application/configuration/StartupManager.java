package name.benshepley.SVMM3.application.configuration;

import name.benshepley.SVMM3.model.application.settings.ApplicationSettingsModel;
import name.benshepley.SVMM3.model.application.ui.PopupConfigurationModel;
import name.benshepley.SVMM3.repository.ApplicationSettingsRepository;
import name.benshepley.SVMM3.view.service.UiComponentSpringPrototypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class StartupManager {

    @Autowired
    private UiComponentSpringPrototypeFactory uiComponentSpringPrototypeFactory;

    @Autowired
    private ApplicationSettingsRepository applicationSettingsRepository;

    @EventListener
    public void onApplicationEvent(ApplicationStartedEvent ignoredEvent) {
        ApplicationSettingsModel applicationSettingsModel = this.applicationSettingsRepository.restoreApplicationSettings();
        if (applicationSettingsModel.getStardewPath().isBlank() || applicationSettingsModel.getTextEditorPath().isBlank() || applicationSettingsModel.getModsPath().isBlank()) {
            this.uiComponentSpringPrototypeFactory.showPopupDialog(
                    PopupConfigurationModel.builder()
                            .title("First Time Setup")
                            .message("This is your first time running the mod manager. You need to configure the application before you can use it. You will be asked to select where you installed Stardew Valley, a text editor and where you would like to store the mods that mod manager manages.")
                            .okButtonActionListener(e -> StartupManager.this.uiComponentSpringPrototypeFactory.showGlobalSettingsDialog(applicationSettingsModel))
                        .build());
        } /*else if (applicationSettingsModel.getProfileSettings().isEmpty()) {
            this.uiComponentSpringPrototypeFactory.showProfileSettingsDialog(new ProfileFileSystemModel("Initial Profile", Collections.emptyList(), Collections.emptyList()));
        }*/
    }



}
