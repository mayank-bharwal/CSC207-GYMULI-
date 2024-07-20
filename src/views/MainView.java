package views;

import javax.swing.*;
import java.awt.*;

public class MainView extends JPanel {
    public static final String viewName = "MainView";

    public MainView() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        JLabel chatLabel = new JLabel("Main", SwingConstants.CENTER);
        chatLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(chatLabel, BorderLayout.CENTER);
    }
}