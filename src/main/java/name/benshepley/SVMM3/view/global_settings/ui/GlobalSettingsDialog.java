package name.benshepley.SVMM3.view.global_settings.ui;

import javax.swing.*;

public class GlobalSettingsDialog extends javax.swing.JDialog {

    public GlobalSettingsDialog(java.awt.Frame parent) {
        super(parent, true);
        JButton saveButton = new JButton("Save");
        super.add(saveButton);
        getRootPane().setDefaultButton( saveButton );
    }

}
