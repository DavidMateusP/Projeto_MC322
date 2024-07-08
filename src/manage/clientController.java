package manage;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import client.Client;
import products.Album;
import products.BoardGame;
import products.Book;
import products.Item;
import products.Movie;
import products.Track;
import client.Loan;

import client.Loan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class clientController {

    @FXML
    private DatePicker date;

    @FXML
    private Label date1;

    @FXML
    private Label date2;

    @FXML
    private Label date3;

    @FXML
    private Label date4;

    @FXML
    private Label date5;

    @FXML
    private MenuItem excluirContaOption;

    @FXML
    private TextField itemName;

    @FXML
    private Button load;

    @FXML
    private Label opt1; //TODO: set options to items (option1.setText(RentalStore.getCurrentClient().getLoan(0).getItem().getName()))

    @FXML
    private Label opt2;

    @FXML
    private Label opt3;

    @FXML
    private Label opt4;

    @FXML
    private Label opt5;

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
    private Button saveButton;

    @FXML
    private Button searchButton;

    @FXML
    private MenuButton select1;

    @FXML
    private MenuButton select2;

    @FXML
    private MenuButton select3;

    @FXML
    private MenuButton select4;

    @FXML
    private MenuButton select5;

    private Item itemSelected = null;
    private ArrayList<Loan> loans = ((Client)RentalStore.getCurrentClient()).getLoans();

    @FXML
    void defineDate(ActionEvent event, Loan option) {
        option.setDate(date.getValue());
    }

    @FXML
    void deleteAccount(ActionEvent event) {
        Client client = new Client(null, null, null);
        RentalStore.setCurrentClient(null);
        RentalStore.removeClient(client);
        showScene("loginMenu.fxml", event);

    }

    @FXML
    void exit(ActionEvent event) {
        RentalStore.setCurrentClient(null);
        try {
            Parent signInParent;
            signInParent = FXMLLoader.load(getClass().getResource("loginMenu.fxml"));
            Scene signInScene = new Scene(signInParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(signInScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void option1Selected(ActionEvent event) {
        option1.setSelected(true);
        option2.setSelected(false);
        option3.setSelected(false);
        itemSelected = RentalStore.searchItem(option1.getText()).get(0); //TODO: implement searchItem}
    }

    @FXML
    void option2Selected(ActionEvent event) {
        option1.setSelected(false);
        option2.setSelected(true);
        option3.setSelected(false);
        itemSelected = RentalStore.searchItem(option2.getText()).get(0); //TODO: implement searchItem
    }

    @FXML
    void option3Selected(ActionEvent event) {
        option1.setSelected(false);
        option2.setSelected(false);
        option3.setSelected(true);
        itemSelected = RentalStore.searchItem(option3.getText()).get(0); //TODO: implement searchItem
    }

    @FXML
    void rent(ActionEvent event) {
        if (itemSelected == null) {
            JOptionPane.showMessageDialog(null, "Please select an item.");
        } else {
            RentalStore.getCurrentClient().rentItem(itemSelected);
            String message = Loan.newLoan(RentalStore.getCurrentClient(), itemSelected);
            JOptionPane.showMessageDialog(null, message);
        }
    }

    @FXML
    void save(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {
        ArrayList<Item> items = RentalStore.searchItem(itemName.getText()); //TODO: implement searchItem
        if (items.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No items found.");
            option1.setText("");
            option2.setText("");
            option3.setText("");
        } else {
            option1.setText(items.get(0).getName());
            option2.setText(items.size() > 1 ? items.get(1).getName() : "");
            option3.setText(items.size() > 2 ? items.get(2).getName() : "");
        }

    }

    @FXML
    void showEmprestimos(ActionEvent event) {
        if (loans.size() > 0) {
            date1.setText(loans.get(0).getDeadline().toString());
            opt1.setText(loans.get(0).getItem().getName());
        }
        else{
            date1.setText("");
            opt1.setText("");
        }

        if (loans.size() > 1) {
            date2.setText(loans.get(1).getDeadline().toString());
            opt2.setText(loans.get(1).getItem().getName());
        }
        else{
            date2.setText("");
            opt2.setText("");
        }

        if (loans.size() > 2) {
            date3.setText(loans.get(2).getDeadline().toString());
            opt3.setText(loans.get(2).getItem().getName());
        }
        else{
            date3.setText("");
            opt3.setText("");
        }

        if (loans.size() > 3) {
            date4.setText(loans.get(3).getDeadline().toString());
            opt4.setText(loans.get(3).getItem().getName());
        }
        else{
            date4.setText("");
            opt4.setText("");
        }

        if (loans.size() > 4) {
            date5.setText(loans.get(4).getDeadline().toString());
            opt5.setText(loans.get(4).getItem().getName());
        }
        else{
            date5.setText("");
            opt5.setText("");
        }
    }

}

