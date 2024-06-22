package name.benshepley.SVMM3.view.component.dialog;

import name.benshepley.SVMM3.controller.ApplicationSettingsController;
import name.benshepley.SVMM3.model.application.settings.ApplicationSettingsModel;
import name.benshepley.SVMM3.model.application.ui.PopupConfigurationModel;
import name.benshepley.SVMM3.model.filesystem.ProfileFileSystemModel;
import name.benshepley.SVMM3.view.MainFrame;
import name.benshepley.SVMM3.view.service.UiComponentSpringPrototypeFactory;
import net.miginfocom.swing.MigLayout;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;


@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ApplicationSettingsDialog extends javax.swing.JDialog {

    /* Spring Beans: */
    private final UiComponentSpringPrototypeFactory uiComponentSpringPrototypeFactory;
    private final ApplicationSettingsController applicationSettingsController;


    /* Swing Components: */
    private final JTextField stardewPathTextField;
    private final JTextField textEditorPathTextField;
    private final JTextField modsPathTextField;
    private final JFileChooser stardewPathChooser;
    private final JFileChooser textEditorPathChooser;
    private final JFileChooser modsPathChooser;

    private final JButton saveButton;
    private final JButton cancelButton;

    /* Model: */
    private ApplicationSettingsModel applicationSettingsModel;

    public ApplicationSettingsDialog(MainFrame parent, UiComponentSpringPrototypeFactory uiComponentSpringPrototypeFactory, ApplicationSettingsController applicationSettingsController) {
        super(parent,"Application Settings", true);
        super.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        this.uiComponentSpringPrototypeFactory = uiComponentSpringPrototypeFactory;
        this.applicationSettingsController = applicationSettingsController;

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

        JLabel textEditorPathJLabel = new JLabel("Text Editor Path:");
        textEditorPathTextField = new JTextField( 50);
        JButton textEditorPathBrowseButton = new JButton("Select");

        FileFilter textEditorFileFilter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isFile() && f.getName().toLowerCase().endsWith(".exe");
            }

            @Override
            public String getDescription() {
                return "Editor Path:";
            }
        };
        this.textEditorPathChooser = new JFileChooser();
        this.textEditorPathChooser.setFileFilter(textEditorFileFilter);
        this.textEditorPathChooser.setAcceptAllFileFilterUsed(false);
        this.textEditorPathChooser.addActionListener(a -> {
            if (this.textEditorPathChooser.getSelectedFile() != null) {
                this.textEditorPathTextField.setText(this.textEditorPathChooser.getSelectedFile().getPath());
            }
        });
        textEditorPathBrowseButton.addActionListener(a -> this.textEditorPathChooser.showOpenDialog(this));

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

        super.add(textEditorPathJLabel);
        super.add(textEditorPathTextField, "span 3");
        super.add(textEditorPathBrowseButton, "wrap");

        super.add(modsPathJLabel);
        super.add(modsPathTextField, "span 3");
        super.add(modsPathBrowseButton, "wrap");

        super.add(saveButton, "skip 1, span 2");
        super.add(cancelButton, "span 2");

        getRootPane().setDefaultButton(saveButton);

    }

    public void loadSettings(ApplicationSettingsModel applicationSettingsModel) {
        this.applicationSettingsModel = applicationSettingsModel;
        if (applicationSettingsModel.getStardewPath().isBlank() || applicationSettingsModel.getTextEditorPath().isBlank() || applicationSettingsModel.getModsPath().isBlank()) {
            /* Setup Buttons: */
            this.cancelButton.setEnabled(false);
            this.saveButton.addActionListener(a -> {
                this.applicationSettingsModel.setStardewPath(this.stardewPathTextField.getText());
                this.applicationSettingsModel.setTextEditorPath(this.textEditorPathTextField.getText());
                this.applicationSettingsModel.setModsPath(this.modsPathTextField.getText());

                if (this.validateForm()) {
                    super.dispose();
                    this.applicationSettingsController.storeApplicationSettings(this.applicationSettingsModel);
                    this.uiComponentSpringPrototypeFactory.showProfileSettingsDialog(new ProfileFileSystemModel());
                }
            });
            this.setupForFirstTime();
        } else {
            /* Setup Buttons: */
            this.cancelButton.addActionListener(a -> super.dispose());
            this.saveButton.addActionListener(a -> {
                this.applicationSettingsModel.setStardewPath(this.stardewPathTextField.getText());
                this.applicationSettingsModel.setTextEditorPath(this.textEditorPathTextField.getText());
                this.applicationSettingsModel.setModsPath(this.modsPathTextField.getText());

                if (this.validateForm()) {
                    super.dispose();
                    this.applicationSettingsController.storeApplicationSettings(this.applicationSettingsModel);
                }
            });

            this.applicationSettingsModel = applicationSettingsModel;
            this.stardewPathTextField.setText(applicationSettingsModel.getStardewPath());
            this.textEditorPathTextField.setText(applicationSettingsModel.getTextEditorPath());
            this.modsPathTextField.setText(applicationSettingsModel.getModsPath());
            this.stardewPathChooser.setCurrentDirectory(new File(applicationSettingsModel.getStardewPath()));
            this.textEditorPathChooser.setCurrentDirectory(new File(applicationSettingsModel.getTextEditorPath()));
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
            this.modsPathTextField.setText(stardewGogLocation.getPath() + "\\Mods");
            this.stardewPathChooser.setCurrentDirectory(new File(this.applicationSettingsModel.getStardewPath()));
        } else if (stardewSteamLocation.exists()) {
            this.applicationSettingsModel.setTextEditorPath(stardewSteamLocation.getPath());
            this.stardewPathTextField.setText(stardewSteamLocation.getPath());
            this.modsPathTextField.setText(stardewGogLocation.getPath() + "\\Mods");
            this.stardewPathChooser.setCurrentDirectory(new File(this.applicationSettingsModel.getStardewPath()));
        }

        if (editorNotepadPlusPLusLocation.exists()) {
            this.applicationSettingsModel.setTextEditorPath(editorNotepadPlusPLusLocation.getPath());
            this.textEditorPathTextField.setText(editorNotepadPlusPLusLocation.getPath());
            this.textEditorPathChooser.setCurrentDirectory(new File(this.applicationSettingsModel.getTextEditorPath()));
        } else if (editorWindowsNotepadLocation.exists()) {
            applicationSettingsModel.setTextEditorPath(editorWindowsNotepadLocation.getPath());
            this.textEditorPathTextField.setText(editorWindowsNotepadLocation.getPath());
            this.textEditorPathChooser.setCurrentDirectory(new File(this.applicationSettingsModel.getTextEditorPath()));
        }

        if (!this.applicationSettingsModel.getStardewPath().isBlank()) {
            this.uiComponentSpringPrototypeFactory.showPopupDialog(
                    PopupConfigurationModel.builder()
                            .title("Stardew Valley (and mods) detected automatically")
                            .message("Path to Stardew Valley, a text editor, and mods were detected automatically. Please Verify.")
                        .build()
            );
        }
    }

    private boolean validateForm() {
        if (this.applicationSettingsModel.getStardewPath() == null || this.applicationSettingsModel.getStardewPath().isBlank()) {
            this.uiComponentSpringPrototypeFactory.showPopupDialog(
                    PopupConfigurationModel.builder()
                            .title("Path to Stardew Valley required")
                            .message("Path to Stardew Valley required.")
                            .build()
            );
            return false;
        } else if (this.applicationSettingsModel.getTextEditorPath() == null || this.applicationSettingsModel.getTextEditorPath().isBlank()){
            this.uiComponentSpringPrototypeFactory.showPopupDialog(
                    PopupConfigurationModel.builder()
                            .title("Path to text editor required")
                            .message("Path to text editor required.")
                            .build()
            );
            return false;
        } else if (this.applicationSettingsModel.getModsPath() == null || this.applicationSettingsModel.getModsPath().isBlank()) {
            this.uiComponentSpringPrototypeFactory.showPopupDialog(
                    PopupConfigurationModel.builder()
                            .title("Path to mods required")
                            .message("Path to mods required.")
                            .build()
            );
            return false;
        }

        var editorFile = new File(this.applicationSettingsModel.getTextEditorPath());
        if (!editorFile.isFile() || !editorFile.getName().toLowerCase().endsWith(".exe")) {
            this.uiComponentSpringPrototypeFactory.showPopupDialog(
                    PopupConfigurationModel.builder()
                            .title("Path to text editor must be an executable")
                            .message("Path to text editor must be an executable.")
                            .build()
            );
            return false;
        }

        return true;
    }

}
