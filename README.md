# CasinoAPI

Log in credentials:
Username: testUser
Password: password123



Different API classes needed to be implemented in order to complete our online casino complex Java system project.

This weeks assignments: implementing design patterns and implementing another use case

## Team Member/ID and Contribution for M03-A04 Design Patterns

#1 Nick Falletta - Implemented Strategy Pattern and MVP design pattern. The MVP pattern is used in SpendingLimitView and SpendingLimitPresenter to separate UI and logic. The Strategy Pattern is used in LimitStrategy, DailyLimitStrategy, PerTransactionLimitStrategy, LimitEnforcer, SpendingLimit, and TransactionController to flexibly enforce spending rules.

#2 John Douglas - Implemented Password Strength and Singleton design patterns. The password strength is implemented for the new user method that is accessed when creating a new account. The singleton design pattern is implemented for the notification controller which is called in the CasinoUI class. Created account creation functionality for new users. Created the UserManagement, NewUserController, and NewUserView.

#3 Taylor Smith - Implemented Observer and Wizard design patterns. Observer implemented in NotificationController, UserView, CasinoUI. Wizard implemented in SpendingLimit, SpendingLimitController, SpendingLimitView, CasinoUI. Changes seen in UI, in Set Spending Limit Panel on the Mainview and in User Settings, opting in and out of notfications

#4 Nicholas Boyle - Implemented the Command and Observer design patterns. Both patterns were added directly into the existing NotificationController class to maintain file simplicity. The Command pattern encapsulates user actions like placing a bet and withdrawing funds through PlaceBetCommand and WithdrawCommand, executed using a CasinoInvoker. The Observer pattern allows real-time notification updates to observers when user preferences or event changes occur. Changes are visible in both the business logic and the notification behavior during simulated command execution.

#5 Sam Bender - Implemented factory design patterns for both design patterns and within the object orientation side of implementation as well. Common game interfaces that are shared among a few different example games to show MVP implementation of factory usage. Models utilize the CasinoGame interface and individual factory classes to help reporoduce games without changing much code or hurting any previously existing code.

## Team Member/ID and Contribution for M03-A05 'Implementing Manage User Profile Data'

#1 Nick Falletta - Pecentage of effort: 20%. Made all spending limit logic (for the model) extremely flexible to handle any quick changes/updates we need to make to the API.

#2 John Douglas - Pecentage of effort: 20%. Implemented user account creation and authentication. Passwords will be graded on strength when creating an account. Implemented spendingLimit classes to dictate how much a user can spend.

#3 Taylor Smith - Pecentage of effort: 20%. Created LoginView screen and added userauthentication functionality through changes in User, UserAuthController, and CasinoUI.

#4 Nicholas Boyle - Pecentage of effort: 20%. Added new notification capabilities and updated UI.

#5 Sam Bender - Pecentage of effort: 20%. Working on improving the view classes and stylizing the GUI so it is better suited to users and easier to understand the limit setting process. Additionally checking on persistency to show up the setting limit info on users profile when checking account information (previously just user/pass and account bal but did not show spending limits)
