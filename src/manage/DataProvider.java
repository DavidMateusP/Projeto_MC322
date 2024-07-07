package manage;
import client.Client;
import client.Loan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import products.Book;
import products.Item;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;


public class DataProvider {

    private static ArrayList<Client> clientList = new ArrayList<>();
    private static ArrayList<Item> itemList = new ArrayList<>();
    private static ArrayList<Loan> loanlist = new ArrayList<>();



    public static void updateData(ArrayList<Client> clients, ArrayList<Item> items){
        clientList = clients;
        itemList = items;
    }
    

    public static ObservableList<Loan> getOutDated() {
        ObservableList<Loan> outdate = FXCollections.observableArrayList(loanlist);
    
        return outdate;
    }

    public static ObservableList<Loan> getNextLoans(){
        ObservableList<Loan> nextLoan = FXCollections.observableArrayList();
        for(Client client: clientList){
            System.out.println(client.getName());
            System.out.println(client.getLoan(0).getClient().getName());
            
            ArrayList<Loan> loan = new ArrayList<>(client.getLoans());
            for (Loan l : loan) {
                System.out.println(l.getClient().getName());
                if(l.isCurrentWeek()){
                    nextLoan.add(l);
                }
            }
        }
        return nextLoan;
    }

    public static ObservableList<Item> findItem(String filter, String textSeach){
        ObservableList<Item> search = FXCollections.observableArrayList();
        if (!filter.equals("Sem Filtro")){
            for (Item i : itemList) {
                if(i.getName().contains(textSeach) && i.getClass().getName().equals(filter) ){
                    search.add(i);
                }
            }
        }else{
            for (Item i : itemList) {
                if(i.getName().contains(textSeach)){
                    search.add(i);
                }
            }
        }
        return search;
    }


}
