package name.benshepley.SVMM3.view.component.dialog;

import name.benshepley.SVMM3.controller.MainController;
import name.benshepley.SVMM3.model.application.PopupConfigurationModel;
import name.benshepley.SVMM3.model.application.settings.ApplicationSettingsModel;
import name.benshepley.SVMM3.model.application.settings.ProfileSettingsModel;
import name.benshepley.SVMM3.view.MainFrame;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class ProfileSettingsDialog extends javax.swing.JDialog {
    private final MainFrame parent;
    private final JTextField profileNameTextField;

    private final JButton saveButton;
    private final JButton deleteButton;
    private final JButton cancelButton;

    private ApplicationSettingsModel applicationSettingsModel;
    private ProfileSettingsModel profileSettingsModel;

    public ProfileSettingsDialog(MainFrame parent) {
        super(parent, "Profile Settings", true);
        super.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.parent = parent;

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

    public void loadSettings(ApplicationSettingsModel applicationSettingsModel, ProfileSettingsModel profileSettingsModel) {
        /* Setup Buttons: */
        this.cancelButton.addActionListener(a -> super.dispose());
        this.saveButton.addActionListener(a -> {
            if (this.validateForm()) {
                super.dispose();
            }
        });
        this.deleteButton.addActionListener(a -> {

        });

        this.applicationSettingsModel = applicationSettingsModel;

        if (this.profileSettingsModel == null) {
            this.profileSettingsModel = new ProfileSettingsModel();
            this.setupForFirstTime();
        } else {
            this.profileSettingsModel = profileSettingsModel;
            this.profileNameTextField.setText(this.profileSettingsModel.getName());
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
        if (this.profileSettingsModel.getName().isBlank()) {
            this.parent.getApplicationEventPublisher().publishEvent(new MainFrame.ShowPopupDialogEvent(this,
                    PopupConfigurationModel.builder()
                            .title("Profile Name Required")
                            .message("Profile name is required.")
                            .build()
            ));
            return false;
        }
        return true;
    }
}
