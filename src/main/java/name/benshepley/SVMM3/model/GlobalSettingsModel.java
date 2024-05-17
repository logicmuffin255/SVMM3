package name.benshepley.SVMM3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class GlobalSettingsModel {
    private String stardewPath;
    private boolean manageSaveGames;
    private String nexusModsAPIKey;
}
