package name.benshepley.SVMM3.controller;

import lombok.Getter;
import name.benshepley.SVMM3.repository.ApplicationSettingsRepository;
import name.benshepley.SVMM3.repository.OperatingSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {
    // Spring Beans:
    @Autowired
    private OperatingSystemRepository operatingSystemRepository;

    @Autowired
    private ApplicationSettingsRepository applicationSettingsRepository;

    // Events:
    public static class OpenNexusModsEvent extends ApplicationEvent {
        public OpenNexusModsEvent(Object source) {
            super(source);
        }
    }

    public static class PlayStardewEvent extends ApplicationEvent {
        public PlayStardewEvent(Object source) {
            super(source);
        }
    }

    @Getter
    public static class OpenExplorerEvent extends ApplicationEvent {
        private final String path;
        public OpenExplorerEvent(Object source, String path) {
            super(source);
            this.path = path;
        }
    }

    @EventListener
    public void onApplicationEvent(OpenNexusModsEvent ignoredEvent) {
        this.operatingSystemRepository.browse(OperatingSystemRepository.NEXUS_MODS_STARDEW_BASE_URL);
    }

    @EventListener
    public void onApplicationEvent(PlayStardewEvent ignoredEvent) {
        this.operatingSystemRepository.execute(this.applicationSettingsRepository.restoreApplicationSettings().getStardewPath());
    }

    @EventListener
    public void onApplicationEvent(OpenExplorerEvent openExplorerEvent) {
        this.operatingSystemRepository.openExplorer(openExplorerEvent.getPath());
    }

}
