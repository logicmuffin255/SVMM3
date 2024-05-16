package name.benshepley.SVMM3.service;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.net.URI;

@Service
public class BrowserService {
    public static final String NEXUS_MODS_STARDEW_BASE_URL = "https://www.nexusmods.com/stardewvalley";

    public static class BrowserException extends RuntimeException {
        public BrowserException(String message) {
            super(message);
        }
    }

    public void openNexusModsAtStardewValley() {
        this.openWebpage(URI.create(NEXUS_MODS_STARDEW_BASE_URL));
    }

    //TODO: LOGGING
    private void openWebpage(URI uri) throws BrowserException {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                throw new BrowserException(e.getMessage());
            }
        }
    }

}
