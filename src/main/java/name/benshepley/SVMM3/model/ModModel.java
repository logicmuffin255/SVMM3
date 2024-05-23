package name.benshepley.SVMM3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ModModel {
    private Boolean enabled;
    private String name;
    private String installedVersion;
    private String notes;
    private String repository;
}
