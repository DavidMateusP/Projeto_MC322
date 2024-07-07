package manage;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

import javax.swing.JOptionPane;

import client.Client;
import products.Album;
import products.BoardGame;
import products.Book;
import products.Item;
import products.Movie;
import products.Track;
import client.Loan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class signUpController {

    @FXML
    private DatePicker birthdate;

    @FXML
    private TextField cpf;

    @FXML
    private Button doneButton;


    @FXML
    private TextField username;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    @FXML
    void signUp(ActionEvent event) {
        String name = this.username.getText();
        String cpf = this.cpf.getText();
        LocalDate birthdateValue = birthdate.getValue();
        Client client = new Client(name, birthdate, cpf);
        if (isCPFRegistered(cpf)) {
            JOptionPane.showMessageDialog(null, "CPF already exists in the database.");
        } else {
            RentalStore.addClient(client);
            JOptionPane.showMessageDialog(null, "Client account created successfully!");
            System.out.println("Name: " + name);
            System.out.println("CPF: " + cpf);
            System.out.println("Birthdate: " + birthdateValue);
        }
        Parent signInParent = FXMLLoader.load(getClass().getResource("loginMenu.fxml"));
        Scene signInScene = new Scene(signInParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }

    private boolean isCPFRegistered(String cpf) {
        try {
            File file = new File("clients.dat");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(cpf)) {
                    scanner.close();
                    return true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

}

