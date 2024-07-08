package manage;

//import java.lang.classfile.instruction.ConstantInstruction.LoadConstantInstruction;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import client.Loan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;

import products.Album;
import products.BoardGame;
import products.Book;
import products.Item;
import products.Movie;

public class adminController implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private TextField age;

    @FXML
    private RadioButton album;

    @FXML
    private RadioButton board;

    @FXML
    private RadioButton book;

    @FXML
    private ChoiceBox<String> filterMenu;

    @FXML
    private TextField itemName1;

    @FXML
    private ListView<Loan> listLoansOutDated;

    @FXML
    private ListView<Loan> nextLoansLists;

    @FXML
    private RadioButton movie;

    @FXML
    private TextField priceValue;

    @FXML
    private TextField qtd;

    @FXML
    private Button searchButton;

    @FXML
    private ListView<Item> searchList;

    @FXML
    private TextField searchTextInput;

    @FXML
    private Tab windowChange;

    @FXML
    private Font x1;

    @FXML
    private Color x2;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    @FXML
    private TextField year;

    @FXML
    void saveText(InputMethodEvent event) {

    }

    public static void formatTextLoan(ListView<Loan> list) {
        list.setCellFactory(new Callback<ListView<Loan>, ListCell<Loan>>() {
            @Override
            public ListCell<Loan> call(ListView<Loan> param) {
                return new ListCell<Loan>() {
                    @Override
                    protected void updateItem(Loan loan, boolean empty) {
                        super.updateItem(loan, empty);
                        if (empty || loan == null) {
                            setText(null);
                        } else {
                            setText(loan.getClient().getName() + ", " + loan.getItem().getName());
                        }
                    }
                };
            }
        });
    }

    public static void formatTextItem(ListView<Item> list) {
        list.setCellFactory(new Callback<ListView<Item>, ListCell<Item>>() {
            @Override
            public ListCell<Item> call(ListView<Item> param) {
                return new ListCell<Item>() {
                    @Override
                    protected void updateItem(Item item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item.getName() + ", " + item.getClass().getName());
                        }
                    }
                };
            }
        });
    }

    @FXML
    void searchItem(ActionEvent event) {
        String textSearch = searchTextInput.getText();
        Item found = RentalStore.searchItem(textSearch);

        ObservableList<Item> search = FXCollections.observableArrayList();
        if (found != null) {
            search.add(found);
        }
        searchList.setItems(search);
        formatTextItem(searchList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Define os itens no ListView
        ArrayList<String> categories = new ArrayList<>(
                Arrays.asList("Sem Filtro", "Livros", "Filmes", "Álbuns", "BoardGames"));
        ObservableList<String> observableCategories = FXCollections.observableArrayList(categories);

        filterMenu.setItems(observableCategories);

        listLoansOutDated.setItems(DataProvider.getOutDated());
        listLoansOutDated.setCellFactory(new Callback<ListView<Loan>, ListCell<Loan>>() {
            @Override
            public ListCell<Loan> call(ListView<Loan> param) {
                return new ListCell<Loan>() {
                    @Override
                    protected void updateItem(Loan loan, boolean empty) {
                        super.updateItem(loan, empty);
                        if (empty || loan == null) {
                            setText(null);
                        } else {
                            setText(loan.getClient().getName() + ", " + loan.getItem().getName());
                        }
                    }
                };
            }
        });

        nextLoansLists.setItems(DataProvider.getNextLoans());

    }

    @FXML
    void add(ActionEvent event) {
        String name = itemName1.getText();
        int age = Integer.parseInt(this.age.getText());
        int year = Integer.parseInt(this.year.getText());
        int qtd = Integer.parseInt(this.qtd.getText());
        double price = Double.parseDouble(this.priceValue.getText());

        Item item = null;
        if (book.isSelected()) {
            item = new Book(qtd, name, age, year, price, 100);
        } else if (movie.isSelected()) {
            item = new Movie(qtd, name, age, year, price, "", new ArrayList<>(Arrays.asList("Portuguese")));
        } else if (album.isSelected()) {
            item = new Album(qtd, name, age, year, price);
        } else if (board.isSelected()) {
            item = new BoardGame(qtd, name, age, year, price, 0, 100);
        }

        RentalStore.addProduct(item);
    }
}
