package view;

import javax.swing.*;
import java.awt.*;

public class SpendingLimitView extends JPanel {
    private CardLayout cardLayout;
    private JPanel stepPanel;
    private int currentStep = 0;

    // Navigation buttons
    private JButton backButton, nextButton, finishButton;

    // Question 1
    private JTextField limitAmountField;

    // Question 2
    private JComboBox<String> timeFrameCombo;

    // Question 3
    private JCheckBox notifyCheckbox, blockCheckbox;

    public SpendingLimitView() {
        setLayout(new BorderLayout());
        setBackground(new Color(25, 25, 25));

        cardLayout = new CardLayout();
        stepPanel = new JPanel(cardLayout);

        stepPanel.add(createStep1(), "Step1");
        stepPanel.add(createStep2(), "Step2");
        stepPanel.add(createStep3(), "Step3");

        add(stepPanel, BorderLayout.CENTER);
        add(createNavPanel(), BorderLayout.SOUTH);

        showStep(0);
    }

    private JPanel createStep1() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(25, 25, 25));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Step 1: Enter Limit Amount ($)");
        label.setForeground(Color.WHITE);
        label.setAlignmentX(CENTER_ALIGNMENT);

        limitAmountField = new JTextField(10);
        limitAmountField.setMaximumSize(new Dimension(200, 30));
        limitAmountField.setAlignmentX(CENTER_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(limitAmountField);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JPanel createStep2() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(25, 25, 25));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Step 2: Select Time Frame");
        label.setForeground(Color.WHITE);
        label.setAlignmentX(CENTER_ALIGNMENT);

        timeFrameCombo = new JComboBox<>(new String[]{"Daily", "Weekly", "Monthly"});
        timeFrameCombo.setMaximumSize(new Dimension(200, 30));
        timeFrameCombo.setAlignmentX(CENTER_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(timeFrameCombo);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JPanel createStep3() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(25, 25, 25));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Step 3: Choose Enforcement Options");
        label.setForeground(Color.WHITE);
        label.setAlignmentX(CENTER_ALIGNMENT);

        notifyCheckbox = new JCheckBox("Notify me if near limit");
        blockCheckbox = new JCheckBox("Block spending over limit");
        notifyCheckbox.setForeground(Color.WHITE);
        blockCheckbox.setForeground(Color.WHITE);
        notifyCheckbox.setOpaque(false);
        blockCheckbox.setOpaque(false);

        notifyCheckbox.setAlignmentX(CENTER_ALIGNMENT);
        blockCheckbox.setAlignmentX(CENTER_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(notifyCheckbox);
        panel.add(blockCheckbox);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JPanel createNavPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(25, 25, 25));

        backButton = new JButton("Back");
        nextButton = new JButton("Next");
        finishButton = new JButton("Finish");
        JButton cancelButton = new JButton("Cancel");

        styleButton(backButton);
        styleButton(nextButton);
        styleButton(finishButton);
        styleButton(cancelButton);

        backButton.addActionListener(e -> showStep(currentStep - 1));
        nextButton.addActionListener(e -> showStep(currentStep + 1));
        finishButton.addActionListener(e -> showSummary());
        cancelButton.addActionListener(e -> CasinoUI.showView("MainView"));

        panel.add(backButton);
        panel.add(nextButton);
        panel.add(finishButton);
        panel.add(cancelButton);

        return panel;
    }

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(50, 50, 50));
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
    }

    private void showStep(int step) {
        currentStep = step;
        cardLayout.show(stepPanel, "Step" + (step + 1));

        backButton.setEnabled(currentStep > 0);
        nextButton.setVisible(currentStep < 2);
        finishButton.setVisible(currentStep == 2);
    }

    // Notification of changes/choices
    private void showSummary() {
        try {
            double limit = Double.parseDouble(limitAmountField.getText());
            String timeFrame = (String) timeFrameCombo.getSelectedItem();
            boolean notify = notifyCheckbox.isSelected();
            boolean block = blockCheckbox.isSelected();

            String summary = "Limit: $" + limit +
                    "\nTime Frame: " + timeFrame +
                    "\nNotify: " + (notify ? "Yes" : "No") +
                    "\nBlock: " + (block ? "Yes" : "No");

            JOptionPane.showMessageDialog(this, summary, "Spending Limit Set", JOptionPane.INFORMATION_MESSAGE);
            CasinoUI.showView("MainView");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric limit.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public double getLimitAmount() throws NumberFormatException {
        return Double.parseDouble(limitAmountField.getText());
    }

    public String getTimeFrame() {
        return (String) timeFrameCombo.getSelectedItem();
    }

    public JButton getFinishButton() {
        return finishButton;
    }

    public boolean shouldNotify() {
        return notifyCheckbox.isSelected();
    }

    public boolean shouldBlock() {
        return blockCheckbox.isSelected();
    }
}
