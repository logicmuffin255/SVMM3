package name.benshepley.SVMM3.model.application.settings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileSettingsModel {
    private String name = "";
    private List<ModSettingsModel> enabledMods = Collections.emptyList();
    private List<ModSettingsModel> disabledMods = Collections.emptyList();
}
