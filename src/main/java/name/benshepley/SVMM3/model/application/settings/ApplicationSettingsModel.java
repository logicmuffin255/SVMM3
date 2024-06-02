package name.benshepley.SVMM3.model.application.settings;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationSettingsModel {
    private String version;
    private String stardewPath;
    private String editorPath;

    private List<ProfileSettingsModel> profileSettingsList = new ArrayList<>();
}
