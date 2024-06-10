package name.benshepley.SVMM3.model.application;

import lombok.Builder;
import lombok.Data;

import java.awt.event.ActionListener;

@Data
@Builder
public class PopupConfigurationModel {
    @Builder.Default
    private Integer positionX = 200;

    @Builder.Default
    private Integer positionY = 400;

    @Builder.Default
    private Integer width = 400;

    @Builder.Default
    private Integer height = 200;

    @Builder.Default
    private String title = "";

    @Builder.Default
    private String message = "";

    private ActionListener okButtonActionListener ;
}
