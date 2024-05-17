package name.benshepley.SVMM3.controller;

import name.benshepley.SVMM3.repository.OperatingSystemRepository;
import name.benshepley.SVMM3.service.BrowserService;
import name.benshepley.SVMM3.service.GlobalSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {
    // Spring Beans:
    @Autowired
    private BrowserService browserService;

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

    @EventListener
    public void onApplicationEvent(OpenNexusModsEvent ignoredEvent) {
        this.browserService.openNexusModsAtStardewValley();
    }

    @EventListener
    public void onApplicationEvent(PlayStardewEvent ignoredEvent) {
        this.operatingSystemRepository.execute(this.globalSettingsService.getGlobalSettings().getStardewPath());
    }

}
