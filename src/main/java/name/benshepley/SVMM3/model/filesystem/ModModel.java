package name.benshepley.SVMM3.model.filesystem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import name.benshepley.SVMM3.model.filesystem.smapi.ModManifestDataModel;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModModel {
    private String path = "";
    private ModDataModel modDataModel = new ModDataModel();
    private ModManifestDataModel modManifestDataModel = new ModManifestDataModel();

}
