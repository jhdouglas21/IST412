package view;

import model.SpendingLimit;

import javax.swing.*;
import java.awt.*;

public class SpendingLimitView extends JPanel {
    private CardLayout cardLayout;
    private JPanel stepPanel;
    private int currentStep = 0;

    private JButton backButton, nextButton, finishButton, backToMainButton;
    private JTextField limitAmountField;
    private JComboBox<String> timeFrameCombo;
    private JCheckBox notifyCheckbox, blockCheckbox;

    private Runnable onFinishAction;

    public SpendingLimitView() {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));

        cardLayout = new CardLayout();
        stepPanel = new JPanel(cardLayout);
        stepPanel.setBackground(new Color(30, 30, 30));

        stepPanel.add(createStep1(), "Step1");
        stepPanel.add(createStep2(), "Step2");
        stepPanel.add(createStep3(), "Step3");

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(30, 30, 30));
        centerPanel.add(createCenterNavPanel(), BorderLayout.SOUTH);
        centerPanel.add(stepPanel, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);

        showStep(0);
    }

    private JPanel createCenterNavPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panel.setBackground(new Color(30, 30, 30));

        backButton = new JButton("Back");
        nextButton = new JButton("Next");
        finishButton = new JButton("Finish");

        styleButton(backButton);
        styleButton(nextButton);
        styleButton(finishButton);

        backButton.addActionListener(e -> showStep(currentStep - 1));
        nextButton.addActionListener(e -> showStep(currentStep + 1));
        finishButton.addActionListener(e -> {
            if (onFinishAction != null) onFinishAction.run();
            CasinoUI.showView("MainView");
        });

        panel.add(backButton);
        panel.add(nextButton);
        panel.add(finishButton);

        return panel;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(30, 30, 30));

        backToMainButton = new JButton("Back to Main");
        backToMainButton.setPreferredSize(new Dimension(0, 50));
        backToMainButton.setFont(new Font("Arial", Font.BOLD, 18));
        backToMainButton.setBackground(new Color(50, 50, 50));
        backToMainButton.setForeground(Color.BLACK);

        backToMainButton.addActionListener(e -> CasinoUI.showView("MainView"));

        panel.add(backToMainButton, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createStep1() {
        JPanel panel = createStepPanel();
        JPanel content = (JPanel) panel.getComponent(1);

        JLabel label = createLabel("Enter Spending Limit ($)");

        limitAmountField = new JTextField(15);
        limitAmountField.setMaximumSize(new Dimension(300, 40));
        limitAmountField.setFont(new Font("Arial", Font.PLAIN, 18));
        limitAmountField.setHorizontalAlignment(JTextField.CENTER);

        content.add(label);
        content.add(Box.createRigidArea(new Dimension(0, 20)));
        content.add(limitAmountField);

        return panel;
    }

    private JPanel createStep2() {
        JPanel panel = createStepPanel();
        JPanel content = (JPanel) panel.getComponent(1);

        JLabel label = createLabel("Select Time Frame");

        timeFrameCombo = new JComboBox<>(new String[]{"Daily", "Weekly", "Monthly"});
        timeFrameCombo.setMaximumSize(new Dimension(300, 40));
        timeFrameCombo.setFont(new Font("Arial", Font.PLAIN, 18));

        content.add(label);
        content.add(Box.createRigidArea(new Dimension(0, 20)));
        content.add(timeFrameCombo);

        return panel;
    }

    private JPanel createStep3() {
        JPanel panel = createStepPanel();
        JPanel content = (JPanel) panel.getComponent(1);

        JLabel label = createLabel("Enforcement Options");

        notifyCheckbox = new JCheckBox("Notify if near limit");
        blockCheckbox = new JCheckBox("Block spending over limit");

        styleCheckbox(notifyCheckbox);
        styleCheckbox(blockCheckbox);

        content.add(label);
        content.add(Box.createRigidArea(new Dimension(0, 20)));
        content.add(notifyCheckbox);
        content.add(Box.createRigidArea(new Dimension(0, 10)));
        content.add(blockCheckbox);

        return panel;
    }

    private JPanel createStepPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(30, 30, 30));

        panel.add(Box.createVerticalGlue());
        panel.add(contentPanel());
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JPanel contentPanel() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);
        content.setAlignmentX(Component.CENTER_ALIGNMENT);
        return content;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(70, 70, 70));
        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("Arial", Font.PLAIN, 16));
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(120, 40));
    }

    private void styleCheckbox(JCheckBox box) {
        box.setForeground(Color.WHITE);
        box.setBackground(new Color(30, 30, 30));
        box.setFont(new Font("Arial", Font.PLAIN, 18));
        box.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void showStep(int step) {
        currentStep = step;
        cardLayout.show(stepPanel, "Step" + (step + 1));

        backButton.setEnabled(currentStep > 0);
        nextButton.setVisible(currentStep < 2);
        finishButton.setVisible(currentStep == 2);
    }

    public void setOnFinishAction(Runnable action) {
        this.onFinishAction = action;
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public double getLimitAmount() throws NumberFormatException {
        return Double.parseDouble(limitAmountField.getText());
    }

    public String getTimeFrame() {
        return (String) timeFrameCombo.getSelectedItem();
    }

    public boolean shouldNotify() {
        return notifyCheckbox.isSelected();
    }

    public boolean shouldBlock() {
        return blockCheckbox.isSelected();
    }

    public JButton getFinishButton() {
        return finishButton;
    }
}