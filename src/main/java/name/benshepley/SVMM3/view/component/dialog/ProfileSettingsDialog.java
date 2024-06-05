package name.benshepley.SVMM3.view.component.dialog;

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
        super.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
        this.applicationSettingsModel = applicationSettingsModel;
        this.profileSettingsModel = profileSettingsModel;
        if (this.profileSettingsModel == null) {
            this.setupForFirstTime();

        }



    }

    public void setupForFirstTime() {
        this.cancelButton.setEnabled(false);
        this.deleteButton.setEnabled(false);
        this.profileNameTextField.setText("Initial Profile");

        this.saveButton.addActionListener(e -> {

        });
    }
}
