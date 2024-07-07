package manage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class rentController {

    @FXML
    private DatePicker date;

    @FXML
    private Menu emprestimos;

    @FXML
    private MenuItem excluirContaOption;

    @FXML
    private TextField itemName;

    @FXML
    private CheckBox option1;

    @FXML
    private CheckBox option2;

    @FXML
    private CheckBox option3;

    @FXML
    private Button rentButton;

    @FXML
    private MenuItem sairOption;

    @FXML
    private Button searchButton;

    @FXML
    private Label username;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    @FXML
    void defineDate(ActionEvent event) {

    }

    @FXML
    void deleteAccount(ActionEvent event) {
        RentalStore.setCurrentClient(null);
        RentalStore.removeClient(client);
        Parent signInParent = FXMLLoader.load(getClass().getResource("loginMenu.fxml"));
        Scene signInScene = new Scene(signInParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }

    @FXML
    void exit(ActionEvent event) {
        RentalStore.setCurrentClient(null);
        Parent signInParent = FXMLLoader.load(getClass().getResource("loginMenu.fxml"));
        Scene signInScene = new Scene(signInParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();

    }

    @FXML
    void opt1Selected(ActionEvent event) {

    }

    @FXML
    void opt2Selected(ActionEvent event) {

    }

    @FXML
    void opt3Selected(ActionEvent event) {

    }

    @FXML
    void rent(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }

    @FXML
    void showEmprestimos(ActionEvent event) {

    }

}

