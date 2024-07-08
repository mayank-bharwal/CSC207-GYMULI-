package app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        // creates window
        JFrame frame = new JFrame("java swing test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JButton button = new JButton("click");

        // adds button to pane
        frame.getContentPane().add(button);

        frame.setVisible(true);
    }
}