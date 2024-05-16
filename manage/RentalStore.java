package manage;

import java.util.ArrayList;
import products.Item;
import client.Client;

public class RentalStore {
    private ArrayList<Item> products;
    private ArrayList<Client> clients;
    
    public RentalStore() {
        this.products = new ArrayList<Item>();
        this.clients = new ArrayList<Client>();
    }

    public ArrayList<Item> getProducts() {
        return products;
    }
    
    public boolean addProduct(Item item) {
        return this.products.add(item);
    }

    public Item removeProduct(Item item) {
        return this.products.remove(this.products.indexOf(item));
    }
    
    public ArrayList<Client> getClients() {
        return clients;
    }
    
    public boolean addClient(Client client) {
        return this.clients.add(client);
    }

    public Client removeClient(Client client) {
        return this.clients.remove(this.clients.indexOf(client));
    }

    @Override
    public String toString() {
        String list = "Rental Store\n\tProducts:\t";
        for (int i = 0; i < this.products.size(); i++){
            list += "\t\t- " + this.products.get(i).getName() 
                    + " (" + this.products.get(i).getQuantity() + ") "
                    + this.products.get(i).getPrice() + "\n";
        }
        list += "\tClients:\n";
        for (int i = 0; i < this.clients.size(); i++){
            list += "\t\t- " + this.clients.get(i).getName() + "\n";
        }
        return list;
    }

    
    
}
