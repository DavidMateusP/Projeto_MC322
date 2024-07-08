package manage;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import client.Client;
import client.Loan;
import client.Rates;
import client.Rating;
import products.Item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class clientController extends Controller {

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
    private ToggleButton dev1;

    @FXML
    private ToggleButton dev2;

    @FXML
    private ToggleButton dev3;

    @FXML
    private ToggleButton dev4;

    @FXML
    private ToggleButton dev5;

    @FXML
    private MenuItem excluirContaOption;

    @FXML
    private TextField itemName;

    @FXML
    private Button load;

    @FXML
    private Label opt1;
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
    private ToggleButton ren1;

    @FXML
    private ToggleButton ren2;

    @FXML
    private ToggleButton ren3;

    @FXML
    private ToggleButton ren4;

    @FXML
    private ToggleButton ren5;

    @FXML
    private Button rentButton;

    @FXML
    private MenuItem sairOption;

    @FXML
    private Button saveButton;

    @FXML
    private Button searchButton;

    private Item itemSelected = null;
    private ArrayList<Loan> loans = ((Client) RentalStore.getCurrentClient()).getLoans();

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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void option1Selected(ActionEvent event) {
        option1.setSelected(true);
        itemSelected = RentalStore.searchItem(option1.getText());
    }

    @FXML
    void removeloan1(ActionEvent event) {
        returnLoan(0);
    }

    @FXML
    void removeloan2(ActionEvent event) {
        returnLoan(1);
    }

    @FXML
    void removeloan3(ActionEvent event) {
        returnLoan(2);
    }

    @FXML
    void removeloan4(ActionEvent event) {
        returnLoan(3);
    }

    @FXML
    void removeloan5(ActionEvent event) {
        returnLoan(4);
    }

    @FXML
    void renew1(ActionEvent event) {
        renew(0);
    }

    @FXML
    void renew2(ActionEvent event) {
        renew(1);
    }

    @FXML
    void renew3(ActionEvent event) {
        renew(2);
    }

    @FXML
    void renew4(ActionEvent event) {
        renew(3);
    }

    @FXML
    void renew5(ActionEvent event) {
        renew(4);
    }

    @FXML
    void rent(ActionEvent event) {
        if (itemSelected == null) {
            JOptionPane.showMessageDialog(null, "Please select an item.");
        } else {
            Loan.newLoan(RentalStore.getCurrentClient(), itemSelected);
            String message = Loan.newLoan(RentalStore.getCurrentClient(), itemSelected);
            JOptionPane.showMessageDialog(null, message);
        }
    }

    @FXML
    void save(ActionEvent event) {
        showEmprestimos(event);
    }

    @FXML
    void search(ActionEvent event) {
        Item item = RentalStore.searchItem(itemName.getText());
        if (item == null) {
            JOptionPane.showMessageDialog(null, "No items found.");
            option1.setText("");

        } else {
            option1.setText(item.getName());
        }

    }

    @FXML
    void showEmprestimos(ActionEvent event) {
        if (loans.size() > 0) {
            date1.setText(loans.get(0).getDeadline().toString());
            opt1.setText(loans.get(0).getItem().getName());
        } else {
            date1.setText("");
            opt1.setText("");
        }

        if (loans.size() > 1) {
            date2.setText(loans.get(1).getDeadline().toString());
            opt2.setText(loans.get(1).getItem().getName());
        } else {
            date2.setText("");
            opt2.setText("");
        }

        if (loans.size() > 2) {
            date3.setText(loans.get(2).getDeadline().toString());
            opt3.setText(loans.get(2).getItem().getName());
        } else {
            date3.setText("");
            opt3.setText("");
        }

        if (loans.size() > 3) {
            date4.setText(loans.get(3).getDeadline().toString());
            opt4.setText(loans.get(3).getItem().getName());
        } else {
            date4.setText("");
            opt4.setText("");
        }

        if (loans.size() > 4) {
            date5.setText(loans.get(4).getDeadline().toString());
            opt5.setText(loans.get(4).getItem().getName());
        } else {
            date5.setText("");
            opt5.setText("");
        }
    }

    private boolean returnLoan(int id) {
        if (id < 0 || id >= loans.size()) {
            return false;
        }
        Loan loan = loans.get(id);
        String message = loan.returnItem(new Rating(Rates.VERY_GOOD));
        JOptionPane.showMessageDialog(null, message);
        return true;
    }

    private boolean renew(int id) {
        if (id < 0 || id >= loans.size()) {
            return false;
        }
        Loan loan = loans.get(id);
        if (loan.isRenewed()) {
            return false;
        }
        String message = loan.renewItem(15);
        JOptionPane.showMessageDialog(null, message);
        return true;
    }
}
