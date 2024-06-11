package name.benshepley.SVMM3.model.application.settings;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationSettingsModel {
    private String version = "";
    private String stardewPath = "";
    private String editorPath = "";
    private String modsPath = "";
}
