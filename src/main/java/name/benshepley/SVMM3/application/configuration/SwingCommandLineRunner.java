package name.benshepley.SVMM3.application.configuration;

import name.benshepley.SVMM3.view.main.MainFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SwingCommandLineRunner implements CommandLineRunner {
    // Spring Beans:
    private final MainFrame mainFrame;

    @Autowired
    public SwingCommandLineRunner(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void run(String... args) throws Exception {
        this.mainFrame.setVisible(true);
    }
}
