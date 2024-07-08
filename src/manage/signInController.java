package manage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class signInController extends Controller {

    @FXML
    private PasswordField cpf;

    @FXML
    private Button login;

    @FXML
    private Button signup;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    @FXML
    void showSignUp(ActionEvent event) {
        showScene("registerMenu.fxml", event);
    }

    @FXML
    void verifyLogin(ActionEvent event) {
        String cpf = this.cpf.getText();
        if (!isCPFRegistered(cpf)) {
            JOptionPane.showMessageDialog(null, "You are not signed up yet. Please create an account!");
        } else if (cpf.equalsIgnoreCase("admin")) {
            JOptionPane.showMessageDialog(null, "Admin signed in successfully!");
            showScene("adminMenu.fxml", event); //TODO: finish adminMenu.fxml
        } else {
            System.out.println("Signed in successfully!");
            JOptionPane.showMessageDialog(null, "Signed in successfully!");
            RentalStore.setCurrentClient(RentalStore.searchClient(cpf));
            showScene("clientMenu.fxml", event);
        }
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
