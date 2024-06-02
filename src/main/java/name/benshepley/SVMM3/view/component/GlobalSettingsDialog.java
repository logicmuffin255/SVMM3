package name.benshepley.SVMM3.view.component;


import name.benshepley.SVMM3.model.application.settings.ApplicationSettingsModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class GlobalSettingsDialog extends javax.swing.JDialog {
    private final ApplicationSettingsModel applicationSettingsModel;

    public GlobalSettingsDialog(Frame parent, ApplicationSettingsModel applicationSettingsModel) {
        super(parent,"Stardew Mod Manager 3 Global Settings", true);
        super.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.applicationSettingsModel = applicationSettingsModel;

        JLabel stardewPathJLabel = new JLabel("Stardew Path");
        JTextField stardewPathTextField = new JTextField(this.applicationSettingsModel.getStardewValleyPath(), 70);
        JButton stardewPathBrowseButton = new JButton("Select");
        JFileChooser chooser = new JFileChooser();
        stardewPathBrowseButton.addActionListener(a -> chooser.showOpenDialog(this));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(a -> super.dispose());
        JButton saveButton = new JButton("Save");

        super.setLayout(new MigLayout("wrap 5", "[grow,fill]", "[grow,fill]"));

        super.add(stardewPathJLabel);
        super.add(stardewPathTextField, "span 3");
        super.add(stardewPathBrowseButton, "wrap");

        super.add(saveButton, "skip 1, span 2");
        super.add(cancelButton, "span 2");

        getRootPane().setDefaultButton(saveButton);

        super.pack();
    }

}
