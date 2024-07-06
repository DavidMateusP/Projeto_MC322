package manage;

// import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


// import manage.Menu;


public class Main extends Application{
    public static void main(String[] args) throws Exception {

        launch(args);

        // Scanner input = new Scanner(System.in);
        // int option = 0;

        // // TODO: Interface
        // Demo d = new Demo();

        // System.out.println("Welcome to our rental store service!\n");
        // do {
        //     try {
        //         System.out.println("\tChoose a profile:\n"
        //                 + "\t\t[1] Manager\n"
        //                 + "\t\t[2] Client\n"
        //                 + "\t\t[3] Exit\n");
        //         option = Integer.parseInt(input.nextLine());
        //         switch (option) {
        //             case 1:
        //                 Menu.managerMenu();
        //                 break;
        //             case 2:
        //                 Menu.clientMenu();
        //                 break;
        //             case 3:
        //                 System.out.println("Goodbye :)\n");
        //                 break;
        //             default:
        //                 System.out.println("Invalid option, please try again.");
        //         }
        //     } catch (NumberFormatException e) {
        //         System.out.println("The option must be an integer.");
        //     }
        // } while (option != 3);
        // input.close();

        // Menu.rental.productsFromFile();

    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("test1.fxml"));
        Parent root = fxmlLoader.load();
        Scene tela = new Scene(root);

        primaryStage.setTitle("Locadora MC322");
        primaryStage.setScene(tela);
        primaryStage.show();
    }
}
