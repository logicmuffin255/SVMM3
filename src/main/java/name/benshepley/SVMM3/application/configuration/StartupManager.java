package name.benshepley.SVMM3.application.configuration;

import name.benshepley.SVMM3.model.application.PopupConfigurationModel;
import name.benshepley.SVMM3.model.application.settings.ApplicationSettingsModel;
import name.benshepley.SVMM3.model.application.settings.ProfileSettingsModel;
import name.benshepley.SVMM3.repository.ApplicationSettingsRepository;
import name.benshepley.SVMM3.view.MainFrame;
import name.benshepley.SVMM3.view.MainPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collections;

@Component
public class StartupManager {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApplicationSettingsRepository applicationSettingsRepository;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Value("${user.dir}")
    private String currentDir;

    @EventListener
    public void onApplicationEvent(ApplicationStartedEvent ignoredEvent) {
        ApplicationSettingsModel applicationSettingsModel = this.applicationSettingsRepository.restoreApplicationSettings();
        if (applicationSettingsModel == null) {
            this.applicationEventPublisher.publishEvent(new MainFrame.PopupDialogEvent(this,
                    PopupConfigurationModel.builder()
                            .title("First time running")
                            .message("You need to configure the application before you can use it. You will be asked to select where you installed Stardew Valley. The system will automatically import any existing mods you have installed.")
                            .okButtonActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    StartupManager.this.applicationEventPublisher.publishEvent(new MainFrame.ApplicationSettingsEvent(this,
                                            ApplicationSettingsModel.builder()
                                                    .version("1.0")
                                                    .stardewPath(StartupManager.this.detectStardewValleyLocation())
                                                    .editorPath(StartupManager.this.detectEditorLocation())
                                                .build()
                                    ));
                                }
                            })
                        .build()));

            this.applicationEventPublisher.publishEvent(new MainPanel.MainProfileTabsAddProfileEvent(this, ProfileSettingsModel.builder().name("Imported").mods(Collections.emptyList()).build()));
        }

    }

    private String detectStardewValleyLocation() {
        if (new File("C:\\Program Files (x86)\\GOG Galaxy\\Games\\Stardew Valley\\").exists()) {
            return "C:\\Program Files (x86)\\GOG Galaxy\\Games\\Stardew Valley";
        } else if (new File("C:\\Program Files (x86)\\Steam\\steamapps\\Common\\Stardew Valley\\").exists()) {
            return "C:\\Program Files (x86)\\Steam\\steamapps\\Common\\Stardew Valley\\";
        } else {
            return "";
        }
    }

    private String detectEditorLocation() {
        if (new File("C:\\Program Files\\Notepad++\\notepad++.exe").exists()) {
            return "C:\\Program Files\\Notepad++\\notepad++.exe";
        } else if (new File("C:\\Windows\\notepad.exe").exists()) {
            return "C:\\Windows\\notepad.exe";
        }
        return  "";
    }

}
