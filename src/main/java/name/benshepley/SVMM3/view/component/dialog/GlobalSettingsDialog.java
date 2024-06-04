package name.benshepley.SVMM3.view.component.dialog;


import name.benshepley.SVMM3.controller.MainController;
import name.benshepley.SVMM3.model.application.PopupConfigurationModel;
import name.benshepley.SVMM3.model.application.settings.ApplicationSettingsModel;
import name.benshepley.SVMM3.view.MainFrame;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class GlobalSettingsDialog extends javax.swing.JDialog {
    private final MainFrame parent;
    private final JTextField stardewPathTextField;
    private final JTextField editorPathTextField;
    private final JFileChooser stardewPathChooser;
    private final JFileChooser editorPathChooser;

    private final JButton saveButton;
    private final JButton cancelButton;

    private ApplicationSettingsModel applicationSettingsModel;

    public GlobalSettingsDialog(MainFrame parent) {
        super(parent,"Application Settings", true);
        super.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.parent = parent;

        JLabel stardewPathJLabel = new JLabel("Stardew Valley Path:");
        this.stardewPathTextField = new JTextField(50);
        JButton stardewPathBrowseButton = new JButton("Select");

        FileFilter smpiPathFileFilter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() && f.exists();
            }

            @Override
            public String getDescription() {
                return "Stardew Valley Path";
            }
        };
        this.stardewPathChooser = new JFileChooser();
        this.stardewPathChooser.setFileFilter(smpiPathFileFilter);
        this.stardewPathChooser.setAcceptAllFileFilterUsed(false);
        this.stardewPathChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        this.stardewPathChooser.addActionListener(a -> {
            if (this.stardewPathChooser.getSelectedFile() != null) {
                this.stardewPathTextField.setText(this.stardewPathChooser.getSelectedFile().getPath());
            }
        });
        stardewPathBrowseButton.addActionListener(a -> this.stardewPathChooser.showOpenDialog(this));

        JLabel editorPathJLabel = new JLabel("Editor Path:");
        editorPathTextField = new JTextField( 50);
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
        this.editorPathChooser = new JFileChooser();
        this.editorPathChooser.setFileFilter(editorFileFilter);
        this.editorPathChooser.setAcceptAllFileFilterUsed(false);
        this.editorPathChooser.addActionListener(a -> {
            if (this.editorPathChooser.getSelectedFile() != null) {
                this.editorPathTextField.setText(this.editorPathChooser.getSelectedFile().getPath());
            }
        });
        editorPathBrowseButton.addActionListener(a -> this.editorPathChooser.showOpenDialog(this));

        this.saveButton = new JButton("Save");
        this.cancelButton = new JButton("Cancel");

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

    }

    public void loadSettings(ApplicationSettingsModel applicationSettingsModel) {
        if (applicationSettingsModel == null) {
            this.setupForFirstTime();
            return;
        }

        this.applicationSettingsModel = applicationSettingsModel;
        this.stardewPathTextField.setText(applicationSettingsModel.getStardewPath());
        this.editorPathTextField.setText(applicationSettingsModel.getEditorPath());
        this.stardewPathChooser.setCurrentDirectory(new File(applicationSettingsModel.getStardewPath()));
        this.editorPathChooser.setCurrentDirectory(new File(applicationSettingsModel.getEditorPath()));

        this.saveButton.addActionListener(a -> {
            if (!this.applicationSettingsModel.getStardewPath().isBlank()) {
                parent.getApplicationEventPublisher().publishEvent(new MainController.StoreApplicationSettingsEvent(this, this.applicationSettingsModel));
                super.dispose();
            } else {
                this.parent.getApplicationEventPublisher().publishEvent(new MainFrame.ShowPopupDialogEvent(this,
                        PopupConfigurationModel.builder()
                                .title("Path to Stardew Valley required")
                                .message("Path to Stardew Valley required.")
                                .build()
                ));
            }
        });
        this.cancelButton.addActionListener(a -> super.dispose());
    }

    public void setupForFirstTime() {
        this.applicationSettingsModel = new ApplicationSettingsModel();

        final File stardewGogLocation = new File("C:\\Program Files (x86)\\GOG Galaxy\\Games\\Stardew Valley\\");
        final File stardewSteamLocation = new File("C:\\Program Files (x86)\\Steam\\steamapps\\Common\\Stardew Valley\\");
        final File editorNotepadPlusPLusLocation = new File("C:\\Program Files\\Notepad++\\notepad++.exe");
        final File editorWindowsNotepadLocation = new File("C:\\Windows\\notepad.exe");

        if (stardewGogLocation.exists()) {
            applicationSettingsModel.setStardewPath(stardewGogLocation.getPath());
            this.stardewPathTextField.setText(stardewGogLocation.getPath());
            this.stardewPathChooser.setCurrentDirectory(new File(applicationSettingsModel.getStardewPath()));
        } else if (stardewSteamLocation.exists()) {
            applicationSettingsModel.setEditorPath(stardewSteamLocation.getPath());
            this.stardewPathTextField.setText(stardewSteamLocation.getPath());
            this.stardewPathChooser.setCurrentDirectory(new File(applicationSettingsModel.getStardewPath()));
        }

        if (editorNotepadPlusPLusLocation.exists()) {
            applicationSettingsModel.setEditorPath(editorNotepadPlusPLusLocation.getPath());
            this.editorPathTextField.setText(editorNotepadPlusPLusLocation.getPath());
            this.editorPathChooser.setCurrentDirectory(new File(applicationSettingsModel.getEditorPath()));
        } else if (editorWindowsNotepadLocation.exists()) {
            applicationSettingsModel.setEditorPath(editorWindowsNotepadLocation.getPath());
            this.editorPathTextField.setText(editorWindowsNotepadLocation.getPath());
            this.editorPathChooser.setCurrentDirectory(new File(applicationSettingsModel.getEditorPath()));
        }

        if (!this.applicationSettingsModel.getStardewPath().isBlank()) {
            this.parent.getApplicationEventPublisher().publishEvent(new MainFrame.ShowPopupDialogEvent(this,
                    PopupConfigurationModel.builder()
                            .title("Stardew Valley detected automatically")
                            .message("Path to Stardew Valley detected automatically. Please Verify.")
                        .build()
            ));
        }

        this.saveButton.addActionListener(a -> {
            if (!this.applicationSettingsModel.getStardewPath().isBlank()) {
                super.dispose();
                this.parent.getApplicationEventPublisher().publishEvent(new MainFrame.ShowProfileSettingsDialogEvent(this, this.applicationSettingsModel));
            } else {
                this.parent.getApplicationEventPublisher().publishEvent(new MainFrame.ShowPopupDialogEvent(this,
                        PopupConfigurationModel.builder()
                                .title("Path to Stardew Valley required")
                                .message("Path to Stardew Valley required.")
                                .build()
                ));
            }
        });
        this.cancelButton.setEnabled(false);
    }



    //this.applicationEventPublisher.publishEvent(new MainTabbedPane.MainProfileTabsAddProfileEvent(this, ProfileSettingsModel.builder().name("Imported").mods(Collections.emptyList()).build()));
}
