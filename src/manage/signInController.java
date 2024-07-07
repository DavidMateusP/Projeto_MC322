import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class signInController {

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
        Parent signUpParent = FXMLLoader.load(getClass().getResource("registerMenu.fxml"));
        Scene signUpScene = new Scene(signUpParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(signUpScene);
        window.show();
    }

    @FXML
    void verifyLogin(ActionEvent event) {
        String cpf = this.cpf.getText();
        if (!isCPFRegistered(cpf)) {
            System.err.println("You are not signed up yet. Please create an account!");
        }
        else if (cpf.equalsIgnoreCase("admin")) {
            System.out.println("Admin signed in successfully!");
            Parent adminParent = FXMLLoader.load(getClass().getResource("adminMenu.fxml"));
            Scene adminScene = new Scene(adminParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(adminScene);
            window.show();
        }

        else{
            System.out.println("Signed in successfully!");
            Parent alugarParent = FXMLLoader.load(getClass().getResource("clientMenuAlugar.fxml"));
            Scene alugarScene = new Scene(alugarParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(alugarScene);
            window.show();
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
