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
    private final JTextField modsPathTextField;
    private final JFileChooser stardewPathChooser;
    private final JFileChooser editorPathChooser;
    private final JFileChooser modsPathChooser;

    private final JButton saveButton;
    private final JButton cancelButton;

    private ApplicationSettingsModel applicationSettingsModel;

    public GlobalSettingsDialog(MainFrame parent) {
        super(parent,"Application Settings", true);
        super.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.parent = parent;

        /* Setup UI Components: */
        JLabel stardewPathJLabel = new JLabel("Stardew Valley Path:");
        this.stardewPathTextField = new JTextField(50);
        JButton stardewPathBrowseButton = new JButton("Select");

        FileFilter stardewPathFileFilter = new FileFilter() {
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
        this.stardewPathChooser.setFileFilter(stardewPathFileFilter);
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

        JLabel modsPathJLabel = new JLabel("Mods Path:");
        this.modsPathTextField = new JTextField(50);
        JButton modsPathBrowseButton = new JButton("Select");

        FileFilter modsPathFileFilter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() && f.exists();
            }

            @Override
            public String getDescription() {
                return "Mods Path";
            }
        };
        this.modsPathChooser = new JFileChooser();
        this.modsPathChooser.setFileFilter(modsPathFileFilter);
        this.modsPathChooser.setAcceptAllFileFilterUsed(false);
        this.modsPathChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        this.modsPathChooser.addActionListener(a -> {
            if (this.modsPathChooser.getSelectedFile() != null) {
                this.modsPathTextField.setText(this.modsPathChooser.getSelectedFile().getPath());
            }
        });
        modsPathBrowseButton.addActionListener(a -> this.modsPathChooser.showOpenDialog(this));


        /* Layout: */
        this.saveButton = new JButton("Save");
        this.cancelButton = new JButton("Cancel");

        super.setLayout(new MigLayout("wrap 5", "[grow,fill]", "[grow,fill]"));

        super.add(stardewPathJLabel);
        super.add(stardewPathTextField, "span 3");
        super.add(stardewPathBrowseButton, "wrap");

        super.add(editorPathJLabel);
        super.add(editorPathTextField, "span 3");
        super.add(editorPathBrowseButton, "wrap");

        super.add(modsPathJLabel);
        super.add(modsPathTextField, "span 3");
        super.add(modsPathBrowseButton, "wrap");

        super.add(saveButton, "skip 1, span 2");
        super.add(cancelButton, "span 2");

        getRootPane().setDefaultButton(saveButton);

    }

    public void loadSettings(ApplicationSettingsModel applicationSettingsModel) {
        this.applicationSettingsModel = applicationSettingsModel;
        if (applicationSettingsModel.getStardewPath().isBlank() || applicationSettingsModel.getEditorPath().isBlank() || applicationSettingsModel.getModsPath().isBlank()) {
            /* Setup Buttons: */
            this.cancelButton.setEnabled(false);
            this.saveButton.addActionListener(a -> {
                if (this.validateForm()) {
                    super.dispose();
                    this.parent.getApplicationEventPublisher().publishEvent(new MainController.StoreApplicationSettingsEvent(this, this.applicationSettingsModel));
                    this.parent.getApplicationEventPublisher().publishEvent(new MainFrame.ShowProfileSettingsDialogEvent(this, this.applicationSettingsModel, null));
                }
            });
            this.setupForFirstTime();
        } else {
            /* Setup Buttons: */
            this.cancelButton.addActionListener(a -> super.dispose());
            this.saveButton.addActionListener(a -> {
                if (this.validateForm()) {
                    super.dispose();
                    this.parent.getApplicationEventPublisher().publishEvent(new MainController.StoreApplicationSettingsEvent(this, this.applicationSettingsModel));
                }
            });

            this.applicationSettingsModel = applicationSettingsModel;
            this.stardewPathTextField.setText(applicationSettingsModel.getStardewPath());
            this.editorPathTextField.setText(applicationSettingsModel.getEditorPath());
            this.modsPathTextField.setText(applicationSettingsModel.getModsPath());
            this.stardewPathChooser.setCurrentDirectory(new File(applicationSettingsModel.getStardewPath()));
            this.editorPathChooser.setCurrentDirectory(new File(applicationSettingsModel.getEditorPath()));
        }
    }

    public void setupForFirstTime() {
        final var stardewGogLocation = new File("C:\\Program Files (x86)\\GOG Galaxy\\Games\\Stardew Valley\\");
        final var stardewSteamLocation = new File("C:\\Program Files (x86)\\Steam\\steamapps\\Common\\Stardew Valley\\");
        final var editorNotepadPlusPLusLocation = new File("C:\\Program Files\\Notepad++\\notepad++.exe");
        final var editorWindowsNotepadLocation = new File("C:\\Windows\\notepad.exe");

        if (stardewGogLocation.exists()) {
            this.applicationSettingsModel.setStardewPath(stardewGogLocation.getPath());
            this.stardewPathTextField.setText(stardewGogLocation.getPath());
            this.modsPathTextField.setText(stardewGogLocation.getPath() + "\\mods");
            this.stardewPathChooser.setCurrentDirectory(new File(this.applicationSettingsModel.getStardewPath()));
        } else if (stardewSteamLocation.exists()) {
            this.applicationSettingsModel.setEditorPath(stardewSteamLocation.getPath());
            this.stardewPathTextField.setText(stardewSteamLocation.getPath());
            this.modsPathTextField.setText(stardewGogLocation.getPath() + "\\mods");
            this.stardewPathChooser.setCurrentDirectory(new File(this.applicationSettingsModel.getStardewPath()));
        }

        if (editorNotepadPlusPLusLocation.exists()) {
            this.applicationSettingsModel.setEditorPath(editorNotepadPlusPLusLocation.getPath());
            this.editorPathTextField.setText(editorNotepadPlusPLusLocation.getPath());
            this.editorPathChooser.setCurrentDirectory(new File(this.applicationSettingsModel.getEditorPath()));
        } else if (editorWindowsNotepadLocation.exists()) {
            applicationSettingsModel.setEditorPath(editorWindowsNotepadLocation.getPath());
            this.editorPathTextField.setText(editorWindowsNotepadLocation.getPath());
            this.editorPathChooser.setCurrentDirectory(new File(this.applicationSettingsModel.getEditorPath()));
        }

        if (!this.applicationSettingsModel.getStardewPath().isBlank()) {
            this.parent.getApplicationEventPublisher().publishEvent(new MainFrame.ShowPopupDialogEvent(this,
                    PopupConfigurationModel.builder()
                            .title("Stardew Valley (and mods) detected automatically")
                            .message("Path to Stardew Valley, an editor, and mods were detected automatically. Please Verify.")
                        .build()
            ));
        }
    }

    private boolean validateForm() {
        this.applicationSettingsModel.setStardewPath(this.stardewPathTextField.getText());
        this.applicationSettingsModel.setEditorPath(this.editorPathTextField.getText());
        this.applicationSettingsModel.setModsPath(this.modsPathTextField.getText());

        if (this.applicationSettingsModel.getStardewPath() == null || this.applicationSettingsModel.getStardewPath().isBlank()) {
            this.parent.getApplicationEventPublisher().publishEvent(new MainFrame.ShowPopupDialogEvent(this,
                    PopupConfigurationModel.builder()
                            .title("Path to Stardew Valley required")
                            .message("Path to Stardew Valley required.")
                            .build()
            ));
            return false;
        } else if (this.applicationSettingsModel.getEditorPath() == null || this.applicationSettingsModel.getEditorPath().isBlank()){
            this.parent.getApplicationEventPublisher().publishEvent(new MainFrame.ShowPopupDialogEvent(this,
                    PopupConfigurationModel.builder()
                            .title("Path to editor required")
                            .message("Path to editor required.")
                            .build()
            ));
            return false;
        } else if (this.applicationSettingsModel.getModsPath() == null || this.applicationSettingsModel.getModsPath().isBlank()) {
            this.parent.getApplicationEventPublisher().publishEvent(new MainFrame.ShowPopupDialogEvent(this,
                    PopupConfigurationModel.builder()
                            .title("Path to mods required")
                            .message("Path to mods required.")
                            .build()
            ));
            return false;
        }

        var editorFile = new File(this.applicationSettingsModel.getEditorPath());
        if (!editorFile.isFile() || !editorFile.getName().toLowerCase().endsWith(".exe")) {
            this.parent.getApplicationEventPublisher().publishEvent(new MainFrame.ShowPopupDialogEvent(this,
                    PopupConfigurationModel.builder()
                            .title("Path to editor must be an executable")
                            .message("Path to editor must be an executable.")
                            .build()
            ));
            return false;
        }

        return true;
    }

}
