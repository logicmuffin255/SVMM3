package name.benshepley.SVMM3.service;

import name.benshepley.SVMM3.repository.OperatingSystemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.net.URI;

@Service
public class BrowserService {
    private static final String NEXUS_MODS_STARDEW_BASE_URL = "https://www.nexusmods.com/stardewvalley";
    private static final Logger LOGGER = LoggerFactory.getLogger(OperatingSystemRepository.class);

    // Exceptions:
    public static class BrowserException extends RuntimeException {
        public BrowserException(String message) {
            super(message);
        }
    }


    public void openNexusModsAtStardewValley() {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(URI.create(NEXUS_MODS_STARDEW_BASE_URL));
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                throw new BrowserException(e.getMessage());
            }
        }
    }

}
