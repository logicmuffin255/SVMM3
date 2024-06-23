package name.benshepley.SVMM3.view.service;


import name.benshepley.SVMM3.model.application.settings.ApplicationSettingsModel;
import name.benshepley.SVMM3.model.application.ui.PopupConfigurationModel;
import name.benshepley.SVMM3.model.filesystem.ProfileModel;
import name.benshepley.SVMM3.view.component.dialog.ApplicationSettingsDialog;
import name.benshepley.SVMM3.view.component.dialog.PopupDialog;
import name.benshepley.SVMM3.view.component.dialog.ProfileSettingsDialog;
import name.benshepley.SVMM3.view.component.panel.ProfileTabPanel;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class UiComponentSpringPrototypeFactory implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void showPopupDialog(PopupConfigurationModel popupConfigurationModel) {
        var popupDialog = this.applicationContext.getBean(PopupDialog.class);
        popupDialog.init(popupConfigurationModel);
        popupDialog.setVisible(true);
    }

    public void showGlobalSettingsDialog(ApplicationSettingsModel applicationSettingsModel) {
        var globalSettingsDialog = this.applicationContext.getBean(ApplicationSettingsDialog.class);
        globalSettingsDialog.loadSettings(applicationSettingsModel);
        globalSettingsDialog.pack();
        globalSettingsDialog.setVisible(true);
    }

    public void showProfileSettingsDialog(ProfileModel profileModel) {
        var profileSettingsDialog = this.applicationContext.getBean(ProfileSettingsDialog.class);
        profileSettingsDialog.loadSettings(profileModel);
        profileSettingsDialog.pack();
        profileSettingsDialog.setVisible(true);
    }

    public ProfileTabPanel produceProfileTabPanel(ProfileModel profileModel) {
        var profileTabPanel = this.applicationContext.getBean(ProfileTabPanel.class);
        profileTabPanel.loadPanel(profileModel);
        return profileTabPanel;
    }

}
