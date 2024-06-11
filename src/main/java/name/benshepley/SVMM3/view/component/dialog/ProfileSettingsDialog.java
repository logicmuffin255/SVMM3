package name.benshepley.SVMM3.view.component.dialog;

import name.benshepley.SVMM3.controller.ApplicationSettingsController;
import name.benshepley.SVMM3.controller.OperatingSystemController;
import name.benshepley.SVMM3.model.application.settings.ApplicationSettingsModel;
import name.benshepley.SVMM3.model.application.ui.PopupConfigurationModel;
import name.benshepley.SVMM3.model.filesystem.ProfileFileSystemModel;
import name.benshepley.SVMM3.view.MainFrame;
import name.benshepley.SVMM3.view.service.UiComponentSpringPrototypeFactory;
import net.miginfocom.swing.MigLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.io.File;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProfileSettingsDialog extends javax.swing.JDialog {
    /* Spring Beans: */
    private final UiComponentSpringPrototypeFactory uiComponentSpringPrototypeFactory;
    private final ApplicationSettingsController applicationSettingsController;
    private final OperatingSystemController operatingSystemController;

    /* Swing Components: */
    private final JTextField profileNameTextField;
    private final JButton saveButton;
    private final JButton deleteButton;
    private final JButton cancelButton;

    private ApplicationSettingsModel applicationSettingsModel;
    private ProfileFileSystemModel profileFileSystemModel;

    @Autowired
    public ProfileSettingsDialog(MainFrame parent, UiComponentSpringPrototypeFactory uiComponentSpringPrototypeFactory, ApplicationSettingsController applicationSettingsController, OperatingSystemController operatingSystemController) {
        super(parent, "Profile Settings", true);
        super.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        this.uiComponentSpringPrototypeFactory = uiComponentSpringPrototypeFactory;
        this.applicationSettingsController = applicationSettingsController;
        this.operatingSystemController = operatingSystemController;

        /* Setup UI Components: */
        JLabel profileNameLabel = new JLabel("Profile Name");
        this.profileNameTextField = new JTextField(50);

        this.deleteButton = new JButton("Delete");
        this.saveButton = new JButton("Save");
        this.cancelButton = new JButton("Cancel");

        /* Layout: */
        super.setLayout(new MigLayout("wrap 3", "[grow,fill]", "[grow,fill]"));
        super.add(profileNameLabel);
        super.add(profileNameTextField, "span 2");

        super.add(deleteButton);
        super.add(saveButton);
        super.add(cancelButton);
    }

    public void loadSettings(ProfileFileSystemModel profileFileSystemModel) {
        this.applicationSettingsModel = this.applicationSettingsController.restoreApplicationSettings();
        this.profileFileSystemModel = profileFileSystemModel;

        if (this.profileFileSystemModel.getName().isBlank()) {
            /* Setup Buttons: */
            this.cancelButton.setEnabled(false);
            this.deleteButton.setEnabled(false);

            this.saveButton.addActionListener(a -> {
                this.profileFileSystemModel.setName(this.profileNameTextField.getText());
                if (this.validateForm()) {
                    super.dispose();
                    var profileDirectory = new File(this.applicationSettingsModel.getModsPath() + "\\" + this.profileFileSystemModel.getName());
                    if (!profileDirectory.exists()) {
                        this.operatingSystemController.createPath(profileDirectory);
                    }
                }
            });

            this.setupForFirstTime();
        } else {
            /* Setup Dialog: */
            this.profileNameTextField.setText(this.profileFileSystemModel.getName());
            /* Setup Buttons: */
            this.cancelButton.addActionListener(a -> super.dispose());
            this.deleteButton.addActionListener(a -> {
                super.dispose();
                var profileDirectoryFromTextField = new File(this.applicationSettingsModel.getModsPath() + "\\" + this.profileNameTextField.getText());
                this.operatingSystemController.deletePath(profileDirectoryFromTextField);
            });
            this.saveButton.addActionListener(a -> {
                if (this.validateForm()) {
                    super.dispose();
                    var profileDirectoryFromTextField = new File(this.applicationSettingsModel.getModsPath() + "\\" + this.profileNameTextField.getText());
                    var profileDirectoryFromModel = new File(this.applicationSettingsModel.getModsPath() + "\\" + this.profileFileSystemModel.getName());

                    if (!profileDirectoryFromTextField.getPath().equals(profileDirectoryFromModel.getPath())) {
                        if (!profileDirectoryFromTextField.exists() && profileDirectoryFromModel.exists()) {
                            this.operatingSystemController.movePath(profileDirectoryFromModel, profileDirectoryFromTextField);
                        }
                    }
                }
            });
        }
    }

    public void setupForFirstTime() {
        this.cancelButton.setEnabled(false);
        this.deleteButton.setEnabled(false);
        this.profileNameTextField.setText("Initial Profile");

        this.saveButton.addActionListener(e -> {

        });
    }

    private boolean validateForm() {
        if (this.profileNameTextField.getText() == null || this.profileNameTextField.getText().isBlank()) {
            this.uiComponentSpringPrototypeFactory.showPopupDialog(
                    PopupConfigurationModel.builder()
                            .title("Profile Name Required")
                            .message("Profile name is required.")
                            .build()
            );
            return false;
        }
        return true;
    }
}
