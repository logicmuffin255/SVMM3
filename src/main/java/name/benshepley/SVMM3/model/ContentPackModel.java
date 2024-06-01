package name.benshepley.SVMM3.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContentPackModel {
    private String uniqueID;
    private String minimumVersion;
}
