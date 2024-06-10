package name.benshepley.SVMM3.model.application.settings;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationSettingsModel {
    private String version = "";
    private String stardewPath = "";
    private String editorPath = "";
    private String modsPath = "";

    @JsonIgnore
    private List<ProfileSettingsModel> profileSettings = new ArrayList<>();
}
