package name.benshepley.SVMM3.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ApplicationSettingsModel {
    private GlobalSettingsModel globalSettingsModel;
    private List<ProfileSettingsModel> profileSettingsModelList;
}
