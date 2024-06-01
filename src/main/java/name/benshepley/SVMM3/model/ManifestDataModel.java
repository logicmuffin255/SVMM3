package name.benshepley.SVMM3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManifestDataModel {
    private String name;
    private String author;
    private String version;
    private String minimumApiVersion;
    private String uniqueID;
    private String description;
    private String entryDLL;
    private List<String> updateKeys;
    private String minimumGameVersion;
    private ContentPackModel contentPackFor;
}
