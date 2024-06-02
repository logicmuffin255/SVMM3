package name.benshepley.SVMM3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import name.benshepley.SVMM3.model.application.settings.ModSettingsModel;
import name.benshepley.SVMM3.model.smapi.ModManifestDataModel;

@Data
@Builder
@AllArgsConstructor
public class ModModel {
    private String path;
    private ModSettingsModel modSettingsModel;
    private ModManifestDataModel modManifestDataModel;
    //TODO: ModConfiguration

}
