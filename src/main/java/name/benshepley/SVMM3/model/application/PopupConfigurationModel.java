package name.benshepley.SVMM3.model.application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.awt.event.ActionListener;

@Data
@Builder
@AllArgsConstructor
public class PopupConfigurationModel {
    private String title;
    private String message;
    private ActionListener okButtonActionListener;
}
