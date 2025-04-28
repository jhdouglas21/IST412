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

## Team Member/ID and Contribution for M04-A03 'Two Implemented Use Cases Integrated with First Usecase + Login/Authentication Implementation with Clean, Refactored Code'

#1 Nick Falletta - Pecentage of effort: 20%. Improved SpendingLimitView's and UserView's UI. Added the ability to change username, password, and credit card information in User Profile. Added the ability to deposit and withdraw money in User Profile.

#2 John Douglas - Pecentage of effort: 20%. Implemented new parameters for Spending Limit such as the user being unable to set a limit above the current user's balance nor below 0. Implemented test cases for both SpendingLimit and Authentication. 

#3 Taylor Smith - Pecentage of effort: 20%. Refactored Duplicate TransactionControllers, User Profile disprecencies, Adding back button to game view.

#4 Nicholas Boyle - Pecentage of effort: 20%. Helped clean and edit code where need be. Made sure project requirements were met.

#5 Sam Bender - Pecentage of effort: 20%. Helped refactor partial code such as system out logs to fit with refactoring from issue report and working on adding playable game that meets use case and satisfies system-wide based tests
