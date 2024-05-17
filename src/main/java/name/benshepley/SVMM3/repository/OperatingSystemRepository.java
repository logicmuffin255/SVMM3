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
    private static final Logger LOGGER = LoggerFactory.getLogger(OperatingSystemRepository.class);
    private static final String NEXUS_MODS_STARDEW_BASE_URL = "https://www.nexusmods.com/stardewvalley";


    public void browseToNexusModsAtStardewValley() {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(URI.create(NEXUS_MODS_STARDEW_BASE_URL));
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    public void openProcess(String executable) {
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
