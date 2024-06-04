package name.benshepley.SVMM3.model.application.settings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ModSettingsModel {
    private String name;
    private String installedVersion;
    private String notes;
    private String repository;
    private Boolean enabled;
}
