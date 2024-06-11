package name.benshepley.SVMM3.view;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import name.benshepley.SVMM3.view.service.UiComponentSpringPrototypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Getter
@Component
public class MainFrame extends JFrame  {
    // Spring Beans:
    public final UiComponentSpringPrototypeFactory uiComponentSpringPrototypeFactory;

    // Swing Components:
    public final MainMenu mainMenu;
    public final MainTabbedPane mainTabbedPane;


    @Autowired
    public MainFrame(UiComponentSpringPrototypeFactory uiComponentSpringPrototypeFactory, MainMenu mainMenu, MainTabbedPane mainTabbedPane) {
        super("Stardew Mod Manager 3");
        this.uiComponentSpringPrototypeFactory = uiComponentSpringPrototypeFactory;

        this.mainMenu = mainMenu;
        this.mainTabbedPane = mainTabbedPane;
    }

    @PostConstruct
    public void init() {
        super.setLayout(new BorderLayout());
        super.setSize(700, 700);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        super.setJMenuBar(this.mainMenu);
        super.add(this.mainTabbedPane, BorderLayout.CENTER);

        super.setVisible(true);
    }

}
