package name.benshepley.SVMM3.view.component.dialog;

import name.benshepley.SVMM3.model.application.PopupConfigurationModel;

import javax.swing.*;
import java.awt.*;

public class PopupDialog extends javax.swing.JDialog {

    public PopupDialog(java.awt.Frame parent, PopupConfigurationModel popupConfigurationModel) {
        super(parent, true);
        super.setTitle(popupConfigurationModel.getTitle());
        super.setLayout(new BorderLayout());

        JTextArea messageTextArea = new JTextArea(popupConfigurationModel.getMessage());
        messageTextArea.setEditable(false);
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);

        messageTextArea.setFont(new Font("Serif", Font.PLAIN, 16));

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            PopupDialog.this.dispose();
            if (popupConfigurationModel.getOkButtonActionListener() != null) {
                popupConfigurationModel.getOkButtonActionListener().actionPerformed(e);
            }
        });

        super.add(messageTextArea, BorderLayout.NORTH);
        super.add(okButton, BorderLayout.SOUTH);
    }
}
