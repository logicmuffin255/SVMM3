package name.benshepley.SVMM3.view.component;

import name.benshepley.SVMM3.model.ProfileSettingsModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class ProfilePanel extends JPanel {
    ProfileSettingsModel profileSettings;

    public ProfilePanel() {
        super(new MigLayout("wrap 6", "[grow, fill]", "[grow, fill]"));
        super.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        JButton profileSettings = new JButton("Profile Settings");
        profileSettings.setMinimumSize(new Dimension(100, 60));
        JButton browseToProfile = new JButton("Browse to Profile");
        browseToProfile.setMinimumSize(new Dimension(100, 60));

        JTable modsTable = new JTable();
        JScrollPane modsTableScrollPane = new JScrollPane(modsTable);

        JButton configureMod = new JButton("Configure Mod");
        JButton browseToMod = new JButton("Browse to Mod");
        JButton updateMod = new JButton("Update Mod(s)");
        JButton removeMod = new JButton("Remove Mod(s)");

        JButton updateAllMods = new JButton("Update All Mods");
        JButton addMod = new JButton("Add Mod");

        super.add(profileSettings, "span 3");
        super.add(browseToProfile, "span 3, wrap");

        super.add(modsTableScrollPane, "span 5 4");

        super.add(configureMod, "wrap");
        super.add(browseToMod, "wrap");
        super.add(updateMod, "wrap");
        super.add(removeMod, "wrap");

        super.add(updateAllMods, "span 3");
        super.add(addMod, "span 3, wrap");
    }
}
