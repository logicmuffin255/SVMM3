package name.benshepley.SVMM3.view.component.dialog;

import name.benshepley.SVMM3.controller.ApplicationSettingsController;
import name.benshepley.SVMM3.controller.OperatingSystemController;
import name.benshepley.SVMM3.controller.ProfileController;
import name.benshepley.SVMM3.model.application.ui.PopupConfigurationModel;
import name.benshepley.SVMM3.model.filesystem.ProfileModel;
import name.benshepley.SVMM3.view.MainFrame;
import name.benshepley.SVMM3.view.service.UiComponentSpringPrototypeFactory;
import net.miginfocom.swing.MigLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProfileSettingsDialog extends javax.swing.JDialog {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileSettingsDialog.class);

    /* Spring Beans: */
    private final UiComponentSpringPrototypeFactory uiComponentSpringPrototypeFactory;
    private final ApplicationSettingsController applicationSettingsController;
    private final ProfileController profileController;
    private final OperatingSystemController operatingSystemController;

    /* Swing Components: */
    private final JTextField profileNameTextField;
    private final JButton saveButton;
    private final JButton deleteButton;
    private final JButton cancelButton;

    private ProfileModel profileModel;

    @Autowired
    public ProfileSettingsDialog(MainFrame parent, UiComponentSpringPrototypeFactory uiComponentSpringPrototypeFactory, ApplicationSettingsController applicationSettingsController, ProfileController profileController, OperatingSystemController operatingSystemController) {
        super(parent, "Profile Settings", true);
        super.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        this.uiComponentSpringPrototypeFactory = uiComponentSpringPrototypeFactory;
        this.applicationSettingsController = applicationSettingsController;
        this.operatingSystemController = operatingSystemController;
        this.profileController = profileController;

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

    public void loadSettings(ProfileModel profileModel) {
        this.profileModel = profileModel;

        if (this.profileModel.getName().isBlank()) {
            /* Setup Dialog: */
            this.profileNameTextField.setText("Initial Profile");

            /* Setup Buttons: */
            this.cancelButton.setEnabled(false);
            this.deleteButton.setEnabled(false);

            this.saveButton.addActionListener(a -> {
                this.profileModel.setName(this.profileNameTextField.getText());
                if (this.validateForm()) {
                    super.dispose();
                    this.profileController.createProfile(this.profileModel.getName());
                    this.operatingSystemController.sync();

                    if (this.modsDirectoryHasMods()) {
                        this.uiComponentSpringPrototypeFactory.showPopupDialog(
                                PopupConfigurationModel.builder()
                                        .title("Mods Detected in Mods directory")
                                        .message("Existing mods were detected. Do you want to move these mods into your starting profile (ok)?")
                                        .cancelVisible(true)
                                        .okButtonActionListener(e -> ProfileSettingsDialog.this.profileController.importExistingMods(this.profileModel.getName()))
                                        .build());
                        this.operatingSystemController.sync();
                    }
                }
            });

        } else {
            /* Setup Dialog: */
            this.profileNameTextField.setText(this.profileModel.getName());
            /* Setup Buttons: */
            this.cancelButton.addActionListener(a -> super.dispose());
            this.deleteButton.addActionListener(a -> {
                super.dispose();
                this.profileController.deleteProfile(this.profileModel.getName());
            });
            this.saveButton.addActionListener(a -> {
                if (this.validateForm()) {
                    super.dispose();
                    var profileDirectoryFromTextField = this.profileNameTextField.getText();
                    var profileDirectoryFromModel = this.profileModel.getName();

                    if (!profileDirectoryFromTextField.equals(profileDirectoryFromModel)) {
                        this.profileController.moveProfile(profileDirectoryFromModel, profileDirectoryFromTextField);
                    }
                }
            });
        }
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

    private boolean modsDirectoryHasMods() {
        try (Stream<Path> s = Files.list(Path.of(this.applicationSettingsController.restoreApplicationSettings().getModsPath()))) {
            return s.findAny().isPresent();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
