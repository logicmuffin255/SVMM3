package name.benshepley.SVMM3.application.configuration;

import com.formdev.flatlaf.FlatLightLaf;
import name.benshepley.SVMM3.page.main.view.MainFrame;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SwingCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        FlatLightLaf.setup();
        new MainFrame();
    }
}
