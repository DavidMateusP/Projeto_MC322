package manage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import client.*;
import products.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import products.Book;


// import manage.Menu;




public class App extends Application{
    public static void main(String[] args) throws Exception {
        Client client1 = new Client("Marc", LocalDate.of(2024, 7, 6), "54807100874");
        Client client2 = new Client("EU", LocalDate.of(2024, 7, 8), "54807100874");
        Book item1 = new Book(2, "LivroFoda", 1990, 10, 12.0, 200);
        Book item2 = new Book(2, "LivroComum", 1990, 10, 12.0, 200);

        Loan.newLoan(client2, item2);
        Loan.newLoan(client1, item1);
        DataProvider.updateData(new ArrayList<>(Arrays.asList(client1, client2)), new ArrayList<>(Arrays.asList(item1, item2)));
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginMenu.fxml"));
        Parent root = fxmlLoader.load();
        Scene tela = new Scene(root);
        
        primaryStage.setTitle("Locadora MC322");
        primaryStage.setScene(tela);
        primaryStage.show();
    }
}
