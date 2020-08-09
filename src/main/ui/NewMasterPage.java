package ui;

import javax.swing.*;
import java.awt.*;

public class NewMasterPage {
    JFrame frame;
    JPanel panel;

    public NewMasterPage() {
        frame = new JFrame();
        panel = new JPanel();
        JLabel welcomeText = new JLabel("Name Your Project");
        welcomeText.setHorizontalAlignment(JLabel.CENTER);

        panel.setBorder(BorderFactory.createEmptyBorder(
                40, 40, 40, 40
        ));
        panel.setLayout(new GridLayout(3, 1));
        panel.add(welcomeText);

        frame.setSize(600, 600);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("TwentyFour7: Name your MasterTask");
        frame.pack();
        frame.setVisible(true);
    }
}
