package name.benshepley.SVMM3.controller;

import name.benshepley.SVMM3.view.main.MainMenu;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;

@Controller
public class MainController implements ApplicationListener<MainMenu.MainMenuGlobalSettingsActionEvent> {

    @Override
    public void onApplicationEvent(MainMenu.MainMenuGlobalSettingsActionEvent event) {
        System.out.println("I'm ALIVE!");
    }
}
