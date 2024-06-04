package name.benshepley.SVMM3.model.application.settings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileSettingsModel {
    private String name;
    private List<ModSettingsModel> enabledMods;
    private List<ModSettingsModel> disabledMods;
}
