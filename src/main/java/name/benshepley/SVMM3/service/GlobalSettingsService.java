package name.benshepley.SVMM3.service;

import name.benshepley.SVMM3.model.GlobalSettingsModel;
import org.springframework.stereotype.Service;

@Service
public class GlobalSettingsService {
    public void saveGlobalSettings() {
    }

    public GlobalSettingsModel getGlobalSettings() {
        return GlobalSettingsModel.builder().stardewPath("C:\\Program Files (x86)\\GOG Galaxy\\Games\\Stardew Valley\\Stardew Valley.exe").build();
    }
}
