package name.benshepley.SVMM3.view.component.panel;

import name.benshepley.SVMM3.controller.ApplicationSettingsController;
import name.benshepley.SVMM3.controller.OperatingSystemController;
import name.benshepley.SVMM3.model.application.settings.ApplicationSettingsModel;
import name.benshepley.SVMM3.model.filesystem.ModFileSystemModel;
import name.benshepley.SVMM3.model.filesystem.ProfileFileSystemModel;
import name.benshepley.SVMM3.view.service.UiComponentSpringPrototypeFactory;
import net.miginfocom.swing.MigLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProfileTabPanel extends JPanel {
    /* Spring Beans: */
    private final UiComponentSpringPrototypeFactory uiComponentSpringPrototypeFactory;
    private final ApplicationSettingsController applicationSettingsController;
    private final OperatingSystemController operatingSystemController;

    /* Properties: */
    @Value("${application.stardew.filename}")
    private String stardewFilename;

    @Value("${application.stardew.smpi.filename}")
    private String smapiFilename;

    // Components:
    private final JTable modsTable;

    private final JButton configureModButton;
    private final JButton viewModManifestButton;
    private final JButton browseToModFolderButton;
    private final JButton browseToModRepositoryButton;
    private final JButton copyModButton;
    private final JButton pasteModButton;
    private final JButton deleteModButton;

    private final JButton playStardewWithSMAPIButton;
    private final JButton playStardewWithoutSMAPIButton;

    // Model:
    private ProfileFileSystemModel profileFileSystemModel;
    private Object[] modsTableColumns = new Object[]{ "Enabled", "Name", "Version", "Notes" };

    @Autowired
    public ProfileTabPanel(UiComponentSpringPrototypeFactory uiComponentSpringPrototypeFactory, ApplicationSettingsController applicationSettingsController, OperatingSystemController operatingSystemController) {
        super(new MigLayout("wrap 4", "[grow, fill]", "[grow, fill]"));
        super.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        this.uiComponentSpringPrototypeFactory = uiComponentSpringPrototypeFactory;
        this.applicationSettingsController = applicationSettingsController;
        this.operatingSystemController = operatingSystemController;

        this.configureModButton = new JButton("Edit Mod Configuration");
        this.configureModButton.setEnabled(false);
        this.viewModManifestButton = new JButton("View Mod Manifest");
        this.viewModManifestButton.setEnabled(false);
        this.browseToModFolderButton = new JButton("Browse to Mod Folder");
        this.browseToModFolderButton.setEnabled(false);
        this.browseToModRepositoryButton = new JButton("Browse to Mod Repository");
        this.browseToModRepositoryButton.setEnabled(false);
        this.copyModButton = new JButton("Copy Mod(s)");
        this.copyModButton.setEnabled(false);
        this.pasteModButton = new JButton("Paste Mod(s)");
        this.pasteModButton.setEnabled(false);
        this.deleteModButton = new JButton("Delete Mod(s)");
        this.deleteModButton.setEnabled(false);

        Object[][] placeholderData = new Object[][]{};
        DefaultTableModel model = new DefaultTableModel(placeholderData, modsTableColumns);

        this.modsTable = new JTable(model);
        JScrollPane modsTableScrollPane = new JScrollPane(this.modsTable);

        this.modsTable.getColumnModel().getColumn(0).setPreferredWidth(25);
        this.modsTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        this.modsTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        this.modsTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                this.configureModButton.setEnabled(true);
                this.viewModManifestButton.setEnabled(true);
                this.browseToModFolderButton.setEnabled(true);
                this.browseToModRepositoryButton.setEnabled(true);
                this.copyModButton.setEnabled(true);
                this.pasteModButton.setEnabled(true);
                this.deleteModButton.setEnabled(true);
            }
        });

        this.playStardewWithSMAPIButton = new JButton("Play Stardew (With SMAPI)");
        this.playStardewWithoutSMAPIButton = new JButton("Play Stardew (Without SMAPI)");

        super.add(modsTableScrollPane, "span 3 7");

        super.add(configureModButton, "wrap");
        super.add(viewModManifestButton, "wrap");
        super.add(browseToModFolderButton, "wrap");
        super.add(browseToModRepositoryButton, "wrap");
        super.add(copyModButton, "wrap");
        super.add(pasteModButton, "wrap");
        super.add(deleteModButton, "wrap");

        super.add(playStardewWithSMAPIButton, "span 2");
        super.add(playStardewWithoutSMAPIButton, "span 2, wrap");
    }

    public void loadPanel(ProfileFileSystemModel profileFileSystemModel) {
        this.profileFileSystemModel = profileFileSystemModel;

        Object[][] data = Stream.concat(profileFileSystemModel.getEnabledMods().stream(), profileFileSystemModel.getDisabledMods().stream())
                .sorted(Comparator.comparing(ModFileSystemModel::getName))
                .map(d -> new Object[]{d.getEnabled(), d.getName(), d.getInstalledVersion(), d.getNotes()})
                .toArray(Object[][]::new);

        this.modsTable.setModel(new DefaultTableModel(data, modsTableColumns) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class;
                } else {
                    return String.class;
                }
            }
        });

        ApplicationSettingsModel applicationSettingsModel = this.applicationSettingsController.restoreApplicationSettings();

        this.playStardewWithSMAPIButton.addActionListener(a -> this.operatingSystemController.executeProcess(Path.of(applicationSettingsModel.getStardewPath() + "\\" + this.smapiFilename)));
        this.playStardewWithoutSMAPIButton.addActionListener(a -> this.operatingSystemController.executeProcess(Path.of(applicationSettingsModel.getStardewPath() + "\\" + this.stardewFilename)));
    }



}
