package name.benshepley.SVMM3.model.application;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import name.benshepley.SVMM3.model.filesystem.ProfileFileSystemModel;
import name.benshepley.SVMM3.model.application.settings.ApplicationSettingsModel;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationSyncStateModel {
    private ApplicationSettingsModel applicationSettings = new ApplicationSettingsModel();
    private List<ProfileFileSystemModel> profileFileSystem = new ArrayList<>();

}
