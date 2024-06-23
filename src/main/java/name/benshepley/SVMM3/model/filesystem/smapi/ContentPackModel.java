package name.benshepley.SVMM3.model.filesystem.smapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContentPackModel {
    private String uniqueID = "";
    private String minimumVersion = "";
}
