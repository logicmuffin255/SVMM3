package name.benshepley.SVMM3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class OperatingSystemController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OperatingSystemController.class);

    public void execute(String source) {
        try {
            new ProcessBuilder(source)
                    .start();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
