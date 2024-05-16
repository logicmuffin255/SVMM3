package name.benshepley.SVMM3.controller;

import name.benshepley.SVMM3.repository.OperatingSystemRepository;
import name.benshepley.SVMM3.service.BrowserService;
import name.benshepley.SVMM3.service.GlobalSettingsService;
import name.benshepley.SVMM3.view.main.service.MainManager;
import name.benshepley.SVMM3.view.main.ui.FooterPanel;
import name.benshepley.SVMM3.view.main.ui.MainMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    @Autowired
    private MainManager mainManager;

    @Autowired
    private BrowserService browserService;

    @Autowired
    private OperatingSystemRepository operatingSystemRepository;

    @Autowired
    private GlobalSettingsService globalSettingsService;

    @EventListener
    public void onApplicationEvent(MainMenu.MainMenuGlobalSettingsActionEvent ignoredEvent) {
        this.mainManager.showGlobalSettings();
    }

    @EventListener
    public void onApplicationEvent(FooterPanel.OpenNexusModsEvent ignoredEvent) {
        this.browserService.openNexusModsAtStardewValley();
    }

    @EventListener
    public void onApplicationEvent(FooterPanel.PlayStardewEvent ignoredEvent) {
        this.operatingSystemRepository.execute(this.globalSettingsService.getGlobalSettings().getStardewPath());
    }

}
