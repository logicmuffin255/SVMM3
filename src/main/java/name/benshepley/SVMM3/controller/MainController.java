package name.benshepley.SVMM3.controller;

import lombok.Getter;
import name.benshepley.SVMM3.repository.OperatingSystemRepository;
import name.benshepley.SVMM3.service.GlobalSettingsService;
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
    private GlobalSettingsService globalSettingsService;

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
        this.operatingSystemRepository.browseToNexusModsAtStardewValley();
    }

    @EventListener
    public void onApplicationEvent(PlayStardewEvent ignoredEvent) {
        this.operatingSystemRepository.openProcess(this.globalSettingsService.getGlobalSettings().getStardewPath());
    }

    @EventListener
    public void onApplicationEvent(OpenExplorerEvent openExplorerEvent) {
        this.operatingSystemRepository.openExplorer(openExplorerEvent.getPath());
    }

}
