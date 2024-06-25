package name.benshepley.SVMM3.view;


import name.benshepley.SVMM3.controller.ProfileController;
import name.benshepley.SVMM3.model.application.event.SyncWithFileSystemEvent;
import name.benshepley.SVMM3.model.application.ui.PopupConfigurationModel;
import name.benshepley.SVMM3.model.filesystem.ProfileModel;
import name.benshepley.SVMM3.view.service.UiComponentSpringPrototypeFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class MainTabbedPane extends JTabbedPane {

    // Spring Beans:
    private final UiComponentSpringPrototypeFactory uiComponentSpringPrototypeFactory;
    private final ProfileController profileController;

    // Listeners:
    @EventListener
    public void onApplicationEvent(SyncWithFileSystemEvent syncWithFileSystemEvent) {
        super.removeAll();
        this.addPlusProfile();
        for (ProfileModel profileModel : syncWithFileSystemEvent.getApplicationSyncStateModel().getProfileFileSystem()) {
            var profileTabPanel = this.uiComponentSpringPrototypeFactory.produceProfileTabPanel(profileModel);
            super.insertTab(profileModel.getName(), null, profileTabPanel, null, 0);
            super.setSelectedIndex(0);
        }
    }


    public MainTabbedPane(UiComponentSpringPrototypeFactory uiComponentSpringPrototypeFactory, ProfileController profileController) {
        super.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        this.uiComponentSpringPrototypeFactory = uiComponentSpringPrototypeFactory;
        this.profileController = profileController;

        this.addPlusProfile();
    }

    public void deleteOpenProfile() {
        if (super.getTabCount() >2) {
            this.profileController.deleteProfile(super.getTitleAt(this.getSelectedIndex()));
        } else {
            var popupConfigurationModel = PopupConfigurationModel.builder()
                    .title("Warning!")
                    .message("You can not delete your last profile.")
                    .build();
            this.uiComponentSpringPrototypeFactory.showPopupDialog(popupConfigurationModel);
        }
    }

    public void copyOpenProfile() {
        this.profileController.copyProfile(super.getTitleAt(this.getSelectedIndex()), "Copy");
    }

    private void addPlusProfile() {
        JPanel addPanel = new JPanel();
        super.insertTab("➕", null, addPanel, null, 0);
        super.addChangeListener(e -> {
            if (super.getSelectedIndex() == super.getTabCount() - 1) {
                super.setSelectedIndex(0);
                System.out.println("yay");
//                var profileModel = new ProfileModel();
//                profileModel.setName("New Panel");
//                var profileTabPanel = this.uiComponentSpringPrototypeFactory.produceProfileTabPanel(profileModel);
//                super.insertTab(profileModel.getName(), null, profileTabPanel, null, 0);
//                super.setSelectedIndex(0);
            }
        });
    }

}
