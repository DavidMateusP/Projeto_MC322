package manage;

import client.Client;
import client.Loan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import products.Item;

import java.util.ArrayList;

public class DataProvider {

    private static ArrayList<Client> clientList = new ArrayList<>();
    private static ArrayList<Item> itemList = new ArrayList<>();
    private static ArrayList<Loan> loanlist = new ArrayList<>();

    public static void updateData(ArrayList<Client> clients, ArrayList<Item> items) {
        clientList = clients;
        itemList = items;
    }

    public static ObservableList<Loan> getOutDated() {
        ObservableList<Loan> outdate = FXCollections.observableArrayList(loanlist);

        return outdate;
    }

    public static ObservableList<Loan> getNextLoans() {
        ObservableList<Loan> nextLoan = FXCollections.observableArrayList();
        for (Client client : clientList) {
            System.out.println(client.getName());
            System.out.println(client.getLoan(0).getClient().getName());

            ArrayList<Loan> loan = new ArrayList<>(client.getLoans());
            for (Loan l : loan) {
                System.out.println(l.getClient().getName());
                if (l.isCurrentWeek()) {
                    nextLoan.add(l);
                }
            }
        }
        return nextLoan;
    }

    public static ObservableList<Item> findItem(String textSeach) {
        ObservableList<Item> search = FXCollections.observableArrayList();
        for(Item item : itemList) {
            // verifies if the item's name has the search text
            if(item.getName().equalsIgnoreCase(textSeach)) {
                search.add(item);
            }
        }
        return search;
    }
}
