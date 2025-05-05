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

        NotificationController.CasinoInvoker invoker = new NotificationController.CasinoInvoker();
        invoker.addCommand(controller.new PlaceBetCommand("player1", 50));
        invoker.addCommand(controller.new WithdrawCommand("player1", 25));

        invoker.processCommands();
    }
}
