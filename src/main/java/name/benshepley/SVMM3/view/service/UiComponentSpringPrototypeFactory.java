package name.benshepley.SVMM3.view.service;


import name.benshepley.SVMM3.model.application.PopupConfigurationModel;
import name.benshepley.SVMM3.model.application.settings.ApplicationSettingsModel;
import name.benshepley.SVMM3.model.application.settings.ProfileSettingsModel;
import name.benshepley.SVMM3.view.component.dialog.GlobalSettingsDialog;
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

    //popupDialog.setSize(400, 300);
    //popupDialog.setLocation((this.mainFrame.getWidth() - popupDialog.getWidth()) / 2, (this.mainFrame.getHeight() - popupDialog.getHeight()) / 2);

    public void showPopupDialog(PopupConfigurationModel popupConfigurationModel) {
        var popupDialog = this.applicationContext.getBean(PopupDialog.class);
        popupDialog.init(popupConfigurationModel);
        popupDialog.setVisible(true);
    }

    public void showGlobalSettingsDialog(ApplicationSettingsModel applicationSettingsModel) {
        var globalSettingsDialog = this.applicationContext.getBean(GlobalSettingsDialog.class);
        globalSettingsDialog.loadSettings(applicationSettingsModel);
        globalSettingsDialog.pack();
        globalSettingsDialog.setVisible(true);
    }

    public void showProfileSettingsDialog(ProfileSettingsModel profileSettingsModel) {
        var profileSettingsDialog = this.applicationContext.getBean(ProfileSettingsDialog.class);
        profileSettingsDialog.loadSettings(profileSettingsModel);
        profileSettingsDialog.pack();
        profileSettingsDialog.setVisible(true);
    }

    public ProfileTabPanel produceProfileTabPanel(ProfileSettingsModel profileSettingsModel) {
        var profileTabPanel = this.applicationContext.getBean(ProfileTabPanel.class);
        return profileTabPanel;
    }

}
