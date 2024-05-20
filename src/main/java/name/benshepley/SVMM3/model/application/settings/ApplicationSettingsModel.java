package name.benshepley.SVMM3.model.application.settings;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import name.benshepley.SVMM3.model.ProfileModel;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ApplicationSettingsModel {
    private String stardewPath;
    private String nexusModsAPIKey;

    private List<ProfileModel> profileModelList;
}
