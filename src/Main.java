import controller.UserAuthController;
import controller.NotificationController;
import model.User;
import view.CasinoUI;
import view.UserView;

public class Main {
    public static void main(String[] args) {

        CasinoUI.main(args);

        NotificationController controller = NotificationController.getInstance();

        controller.subscribe("player1");
        controller.setUserPreference("player1", "betting", true);
        controller.setUserPreference("player1", "banking", true);

        NotificationController.CasinoInvoker invoker = controller.new CasinoInvoker();
        invoker.addCommand(controller.new PlaceBetCommand("player1", 50));
        invoker.addCommand(controller.new WithdrawCommand("player1", 25));

        invoker.processCommands();
        
//        User testUser = new User("1", "testUser", "password123", "test@example.com", 100.0);
//        UserView authView = new UserView();
//        UserAuthController authController = new UserAuthController(testUser, authView);
//
//        System.out.println("Testing Authentication:");
//        System.out.println("Correct Login: " + authController.authenticate("testUser", "password123")); // expected to be true
//        System.out.println("Incorrect Login: " + authController.authenticate("testUser33", "pass444")); // expected to be false
//
//
//        String[] testPasswords = {
//                "password",            // Too weak
//                "Password1",           // Missing special character
//                "password1!",          // Missing uppercase
//                "PASSWORD1!",          // Missing lowercase
//                "Pass1!",              // Too short
//                "StrongPass1!"         // Valid
//        };
//
//        for (String pwd : testPasswords) {
//            boolean isValid = User.isValidPassword(pwd);
//            System.out.println("Password: " + pwd + " -> " + (isValid ? "Valid ✅" : "Invalid ❌"));
//        }
    }
}
