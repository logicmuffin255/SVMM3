package name.benshepley.SVMM3.view.component;


import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class GlobalSettingsDialog extends javax.swing.JDialog {

    public GlobalSettingsDialog(java.awt.Frame parent) {
        super(parent,"Stardew Mod Manager 3 Global Settings", true);
        super.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        super.setSize(400, 200);

        JLabel stardewPathJLabel = new JLabel("Stardew Path");
        JTextField stardewPathTextField = new JTextField();
        JButton stardewPathBrowseButton = new JButton("Select");
        JFileChooser chooser = new JFileChooser();
        stardewPathBrowseButton.addActionListener(a -> chooser.showOpenDialog(this));


        JLabel nexusModsApiKey = new JLabel("Nexus Mods API Key");
        JTextField nexusModsApiKeyTextField = new JTextField();
        JButton nextModsApiKeyLookupButton = new JButton("Lookup");

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(a -> super.dispose());
        JButton saveButton = new JButton("Save");

        super.setLayout(new MigLayout("wrap 5", "[grow,fill]", "[grow,fill]"));

        super.add(stardewPathJLabel);
        super.add(stardewPathTextField, "span 3");
        super.add(stardewPathBrowseButton, "wrap");

        super.add(nexusModsApiKey);
        super.add(nexusModsApiKeyTextField, "span 3");
        super.add(nextModsApiKeyLookupButton, "wrap");

        super.add(saveButton);
        super.add(cancelButton);

        getRootPane().setDefaultButton(saveButton);
    }

}
