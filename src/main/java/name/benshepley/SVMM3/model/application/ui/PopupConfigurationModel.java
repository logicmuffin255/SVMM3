package name.benshepley.SVMM3.model.application.ui;

import lombok.Builder;
import lombok.Data;

import java.awt.event.ActionListener;

@Data
@Builder
public class PopupConfigurationModel {
    @Builder.Default
    private Integer positionX = 150;

    @Builder.Default
    private Integer positionY = 250;

    @Builder.Default
    private Integer width = 400;

    @Builder.Default
    private Integer height = 200;

    @Builder.Default
    private String title = "";

    @Builder.Default
    private String message = "";

    @Builder.Default
    private boolean cancelVisible = false;

    private ActionListener okButtonActionListener;
    private ActionListener cancelButtonActionListener;
}
