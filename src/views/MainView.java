package views;

import interface_adapter.ViewModelManager;

import javax.swing.*;
import java.awt.*;

public class MainView extends JPanel {
    public static final String viewName = "MainView";

    private final JButton createChatButton;

    public MainView(ViewModelManager viewModelManager) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        JLabel title = new JLabel("Main", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        createChatButton = new JButton("Create Chat");
        createChatButton.addActionListener(e -> viewModelManager.setActiveView(CreateChatView.viewName));
        add(createChatButton, BorderLayout.SOUTH);
    }

}

