package name.benshepley.SVMM3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GlobalSettingsModel {
    private String stardewPath;
    private String nexusModsAPIKey;
}
