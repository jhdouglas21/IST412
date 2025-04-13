package view;

import controller.NotificationController;
import model.User;

import javax.swing.*;
import java.awt.*;

public class UserView extends JPanel {
    public UserView() {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));

        JLabel title = new JLabel("User Profile", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        JPanel userPanel = new JPanel();
        userPanel.setOpaque(false);

        JLabel firstNameLabel = new JLabel("FirstName: ");
        firstNameLabel.setForeground(Color.WHITE);
        JTextField firstNameTF = new JTextField(15);
        JButton saveBtn1 = new JButton("Save");
        saveBtn1.setBackground(new Color(50, 50, 50));
        saveBtn1.setForeground(Color.WHITE);

        JLabel lastNameLabel = new JLabel("LastName: ");
        lastNameLabel.setForeground(Color.WHITE);
        JTextField lastNameTF = new JTextField(15);

        userPanel.add(firstNameLabel);
        userPanel.add(firstNameTF);
        userPanel.add(saveBtn1);

        userPanel.add(lastNameLabel);
        userPanel.add(lastNameTF);
        userPanel.add(saveBtn1);

        add(title, BorderLayout.NORTH);
        add(userPanel, BorderLayout.CENTER);
        add(createBackButton(), BorderLayout.SOUTH);
    }

    public UserView(User currentUser, NotificationController notificationController) {
    }

    private JButton createBackButton() {
        JButton backBtn = new JButton("Back to Main");
        backBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        backBtn.setBackground(new Color(50, 50, 50));
        backBtn.setForeground(Color.WHITE);
        backBtn.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        return backBtn;
    }
}
