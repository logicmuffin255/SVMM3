package name.benshepley.SVMM3.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;

@Repository
public class OperatingSystemRepository {
    public static final String NEXUS_MODS_STARDEW_BASE_URL = "https://www.nexusmods.com/stardewvalley";

    private static final Logger LOGGER = LoggerFactory.getLogger(OperatingSystemRepository.class);


    public void browse(String url) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(URI.create(url));
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    public void execute(String executable) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(executable);
            processBuilder.start();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void openExplorer(String directory) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.OPEN)) {
            try {
                desktop.open(new File(directory));
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

}
