package name.benshepley.SVMM3.model.filesystem.smapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModManifestDataModel {
    private String name = "";
    private String author = "";
    private String version = "";
    private String minimumApiVersion = "";
    private String uniqueID = "";
    private String description = "";
    private String entryDLL = "";
    private List<String> updateKeys = Collections.emptyList();
    private String minimumGameVersion = "";
    private ContentPackModel contentPackFor = new ContentPackModel();
}
