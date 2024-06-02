package name.benshepley.SVMM3.model.application.settings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ProfileSettingsModel {
    private String name;
    private List<ModSettingsModel> mods;
}
