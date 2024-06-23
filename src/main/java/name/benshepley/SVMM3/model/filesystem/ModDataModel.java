package name.benshepley.SVMM3.model.filesystem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModDataModel {
    private String name = "";
    private String notes = "";
    private String repository = "";
    private Boolean enabled = false;
}
