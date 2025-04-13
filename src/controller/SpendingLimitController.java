package controller;


import model.SpendingLimit;
import view.SpendingLimitView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpendingLimitController {
    private SpendingLimit model;
    private SpendingLimitView view;
    private String currentUserId;

    public SpendingLimitController(SpendingLimit model, SpendingLimitView view, String userId) {
        this.model = model;
        this.view = view;
        this.currentUserId = userId;

        // Finish button
        view.getFinishButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applySpendingLimit();
            }
        });
    }


    // makes sure spending limit entered is valid/not negative
    private void applySpendingLimit() {
        try {
            double limit = view.getLimitAmount();
            if (limit < 0) {
                JOptionPane.showMessageDialog(view, "Limit cannot be negative!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            model.setSpendingLimit(currentUserId, limit);

            // Optional: Display confirmation or save other settings
            JOptionPane.showMessageDialog(view,
                    "Spending limit of $" + limit + " set successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


