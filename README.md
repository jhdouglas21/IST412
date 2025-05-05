# CasinoAPI

Log in credentials:
Username: testUser
Password: password123



This week's assignments: refactoring code and adding 2 more use cases: Withdrawl funds and Deposit Funds

Main Code Refactoring Changes Made: 
- User money is not live updated when a transaction limit is set
- No error handle for a user attempting to apply a transaction cap while not having any money
- No back-out button in the games tab.
- Alignment issues in the User Profile UI
- Duplicate TransactionControllers

## Team Member/ID and Contribution for M05-A01 Final Project Implementation

#1 Nick Falletta - Pecentage of effort: 20%. 

#2 John Douglas - Pecentage of effort: 20%. Mostly just bug fixing certain functions, testing to ensure proper functionality with error handling, and ensuring that the balance for both Black Jack and the User Settings update to match the same value. Before, the balance would not update accurately unless the user pressed play again for Black Jack or deposit/withdraw for User Settings. To fix this, I simply did a component override listener to ensure when either view is loaded the jlabel is correctly showing the proper balance. 

#3 Taylor Smith - Pecentage of effort: 20%. Fixed some minor issues in code: changed starting bet amount to be $5 as 0 was interfering with our spending limit controls. Cleaned up some code by getting rid of unnessary tests in main method.

#4 Nicholas Boyle - Pecentage of effort: 20%.

#5 Sam Bender - Pecentage of effort: 20%. 
