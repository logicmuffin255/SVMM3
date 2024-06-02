package name.benshepley.SVMM3.view.component;


import name.benshepley.SVMM3.controller.MainController;
import name.benshepley.SVMM3.model.application.settings.ApplicationSettingsModel;
import name.benshepley.SVMM3.view.MainFrame;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class GlobalSettingsDialog extends javax.swing.JDialog {
    private final ApplicationSettingsModel applicationSettingsModel;

    public GlobalSettingsDialog(MainFrame parent, ApplicationSettingsModel applicationSettingsModel) {
        super(parent,"Stardew Mod Manager 3 Global Settings", true);
        super.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.applicationSettingsModel = applicationSettingsModel;

        JLabel stardewPathJLabel = new JLabel("Stardew Valley Path:");
        JTextField stardewPathTextField = new JTextField(this.applicationSettingsModel.getStardewPath(), 70);
        JButton stardewPathBrowseButton = new JButton("Select");

        FileFilter smpiPathFilFilter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Stardew Valley Path";
            }
        };
        JFileChooser smapiPathChooser = new JFileChooser(this.applicationSettingsModel.getStardewPath());
        smapiPathChooser.setFileFilter(smpiPathFilFilter);
        smapiPathChooser.setAcceptAllFileFilterUsed(false);
        smapiPathChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        smapiPathChooser.addActionListener(a -> stardewPathTextField.setText(smapiPathChooser.getSelectedFile().getPath()));
        stardewPathBrowseButton.addActionListener(a -> smapiPathChooser.showOpenDialog(this));

        JLabel editorPathJLabel = new JLabel("Editor Path:");
        JTextField editorPathTextField = new JTextField(this.applicationSettingsModel.getEditorPath(), 70);
        JButton editorPathBrowseButton = new JButton("Select");

        FileFilter editorFileFilter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isFile() && f.getName().toLowerCase().endsWith(".exe");
            }

            @Override
            public String getDescription() {
                return "Editor Path:";
            }
        };
        JFileChooser editorPathChooser = new JFileChooser(this.applicationSettingsModel.getStardewPath());
        editorPathChooser.setFileFilter(editorFileFilter);
        editorPathChooser.setAcceptAllFileFilterUsed(false);
        editorPathChooser.addActionListener(a -> editorPathTextField.setText(editorPathChooser.getSelectedFile().getPath()));
        editorPathBrowseButton.addActionListener(a -> editorPathChooser.showOpenDialog(this));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(a -> super.dispose());

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(a -> {
            this.applicationSettingsModel.setStardewPath(stardewPathTextField.getText());
            this.applicationSettingsModel.setEditorPath(editorPathTextField.getText());
            parent.getApplicationEventPublisher().publishEvent(new MainController.SaveApplicationSettingsEvent(this, this.applicationSettingsModel));
            super.dispose();
        });

        super.setLayout(new MigLayout("wrap 5", "[grow,fill]", "[grow,fill]"));

        super.add(stardewPathJLabel);
        super.add(stardewPathTextField, "span 3");
        super.add(stardewPathBrowseButton, "wrap");

        super.add(editorPathJLabel);
        super.add(editorPathTextField, "span 3");
        super.add(editorPathBrowseButton, "wrap");

        super.add(saveButton, "skip 1, span 2");
        super.add(cancelButton, "span 2");

        getRootPane().setDefaultButton(saveButton);

        super.pack();
    }

}
