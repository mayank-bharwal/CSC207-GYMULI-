package views;

import javax.swing.*;
import java.awt.*;

public class ChatView extends JPanel {
    public static final String viewName = "ChatView";

    public ChatView() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        JLabel chatLabel = new JLabel("Chat", SwingConstants.CENTER);
        chatLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(chatLabel, BorderLayout.CENTER);
    }
}