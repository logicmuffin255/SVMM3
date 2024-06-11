package name.benshepley.SVMM3.model.filesystem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import name.benshepley.SVMM3.model.filesystem.smapi.ModManifestDataModel;

@Data
@Builder
@AllArgsConstructor
public class ModModel {
    private String path;
    private ModFileSystemModel modFileSystemModel;
    private ModManifestDataModel modManifestDataModel;

}
