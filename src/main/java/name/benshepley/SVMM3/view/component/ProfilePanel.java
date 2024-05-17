package name.benshepley.SVMM3.view.component;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.BevelBorder;

public class ProfilePanel extends JPanel {
    public ProfilePanel() {
        super(new MigLayout("wrap 5", "[grow,fill]", "[grow,fill]"));
        super.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        JButton profileSettings = new JButton("Profile Settings");
        JButton browseToProfile = new JButton("Browse to Profile");

        JTable modsTable = new JTable();
        JScrollPane modsTableScrollPane = new JScrollPane(modsTable);

        JButton configureMod = new JButton("Configure Mod");
        JButton browseToMod = new JButton("Browse to Mod");
        JButton updateMod = new JButton("Update Mod(s)");
        JButton removeMod = new JButton("Remove Mod(s)");

        JButton updateAllMods = new JButton("Update All Mods");
        JButton addMod = new JButton("Add Mod");


        super.add(profileSettings);
        super.add(browseToProfile, "wrap");

        super.add(modsTableScrollPane, "span 4 4");

        super.add(configureMod);
        super.add(browseToMod);
        super.add(updateMod);
        super.add(removeMod, "wrap");

        super.add(updateAllMods);
        super.add(addMod, "wrap");
    }
}
