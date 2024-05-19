package name.benshepley.SVMM3.view.component;

import name.benshepley.SVMM3.controller.MainController;
import name.benshepley.SVMM3.model.application.settings.ProfileSettingsModel;
import net.miginfocom.swing.MigLayout;
import org.springframework.context.ApplicationEventPublisher;

import javax.swing.*;
import javax.swing.border.BevelBorder;

public class ProfilePanel extends JPanel {
    private final ApplicationEventPublisher applicationEventPublisher;
    private ProfileSettingsModel profileSettingsModel;

    public ProfilePanel(ApplicationEventPublisher applicationEventPublisher, ProfileSettingsModel profileSettingsModel) {
        super(new MigLayout("wrap 4", "[grow, fill]", "[grow, fill]"));
        super.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        this.applicationEventPublisher = applicationEventPublisher;
        this.profileSettingsModel = profileSettingsModel;

        JButton profileSettingsButton = new JButton("Profile Settings");

        String[] columnNames = { "Name", "Author", "Installed Version" };
        String[][] data = {{"Billymod", "bill", "0.7"},
                           {"DiffrentMod", "Ben", "0.6"}};
        JTable modsTable = new JTable(data, columnNames);

        JScrollPane modsTableScrollPane = new JScrollPane(modsTable);

        JButton configureModButton = new JButton("Configure Mod");
        JButton browseToModButton = new JButton("Browse to Mod");
        JButton updateModButton = new JButton("Update Mod(s)");
        JButton removeModButton = new JButton("Remove Mod(s)");
        JButton addModButton = new JButton("Add Mod");

        JButton updateAllModsButton = new JButton("Update All Mods");
        JButton openNexusModsButton = new JButton("Open Nexus Mods");
        openNexusModsButton.addActionListener(a -> this.applicationEventPublisher.publishEvent(new MainController.OpenNexusModsEvent(this)));


        JButton playStardewWithSMAPIButton = new JButton("Play Stardew (With SMAPI)");
        playStardewWithSMAPIButton.addActionListener(a -> this.applicationEventPublisher.publishEvent(new MainController.PlayStardewEvent(this)));

        JButton playStardewWithoutSMAPIButton = new JButton("Play Stardew (Without SMAPI)");

        super.add(updateAllModsButton, "span 1");
        super.add(openNexusModsButton, "span 1");
        super.add(profileSettingsButton, "span 1, wrap");

        super.add(modsTableScrollPane, "span 3 5");

        super.add(addModButton, "wrap");
        super.add(configureModButton, "wrap");
        super.add(browseToModButton, "wrap");
        super.add(updateModButton, "wrap");
        super.add(removeModButton, "wrap");

        super.add(playStardewWithSMAPIButton, "span 2");
        super.add(playStardewWithoutSMAPIButton, "span 2, wrap");

    }

    //public void populateTable(String[][] data)
}
