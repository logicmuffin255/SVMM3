package name.benshepley.SVMM3.view.component.dialog;

import name.benshepley.SVMM3.model.application.ui.PopupConfigurationModel;
import name.benshepley.SVMM3.view.MainFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PopupDialog extends javax.swing.JDialog {

    @Autowired
    public PopupDialog(MainFrame mainFrame) {
        super(mainFrame, true);
    }

    public void init(PopupConfigurationModel popupConfiguration) {
        super.setTitle(popupConfiguration.getTitle());
        super.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        super.setSize(popupConfiguration.getWidth(), popupConfiguration.getHeight());
        super.setLocation(popupConfiguration.getPositionX(), popupConfiguration.getPositionY());
        super.setLayout(new BorderLayout());

        JTextArea messageTextArea = new JTextArea(popupConfiguration.getMessage());
        messageTextArea.setEditable(false);
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);

        messageTextArea.setFont(new Font("Serif", Font.PLAIN, 16));

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            PopupDialog.this.dispose();
            if (popupConfiguration.getOkButtonActionListener() != null) {
                popupConfiguration.getOkButtonActionListener().actionPerformed(e);
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setVisible(popupConfiguration.isCancelVisible());
        cancelButton.addActionListener(e -> {
            PopupDialog.this.dispose();
            if (popupConfiguration.getCancelButtonActionListener() != null) {
                popupConfiguration.getCancelButtonActionListener().actionPerformed(e);
            }
        });

        Panel buttonPanel = new Panel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        super.add(messageTextArea, BorderLayout.NORTH);
        super.add(buttonPanel, BorderLayout.SOUTH);
    }

}
