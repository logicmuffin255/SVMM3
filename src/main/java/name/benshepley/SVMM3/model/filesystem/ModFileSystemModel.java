package name.benshepley.SVMM3.model.filesystem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModFileSystemModel {
    private String name;
    private String installedVersion;
    private String notes;
    private String repository;
    private Boolean enabled;
}
