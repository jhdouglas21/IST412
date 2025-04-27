package controller;


import model.SpendingLimit;
import model.User;
import view.SpendingLimitView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpendingLimitController {
    private SpendingLimit model;
    private SpendingLimitView view;
    private String currentUserId;
    private User currentUser; // <-- Add this

    public SpendingLimitController(SpendingLimit model, SpendingLimitView view, String userId, User user) {
        this.model = model;
        this.view = view;
        this.currentUserId = userId;
        this.currentUser = user; // <-- Save the user reference

        view.getFinishButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applySpendingLimit();
            }
        });
    }

    private void applySpendingLimit() {
        try {
            // **Check if user has money**
            if (currentUser.getBalance() <= 0) {
                JOptionPane.showMessageDialog(view,
                        "You cannot set a spending limit with a zero balance!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            double limit = view.getLimitAmount();
            if (limit < 0) {
                JOptionPane.showMessageDialog(view, "Limit cannot be negative!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(limit > currentUser.getBalance())
            {
                JOptionPane.showMessageDialog(view, "Limit cannot be greater than user balance", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            model.setSpendingLimit(currentUserId, limit);

            JOptionPane.showMessageDialog(view,
                    "Spending limit of $" + limit + " set successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}



