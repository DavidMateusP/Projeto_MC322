package manage;

import java.io.*;
import java.util.ArrayList;
import products.Item;
import client.Client;

public class RentalStore {
    private ArrayList<Item> products;
    private ArrayList<Client> clients;
    private static final String productsFile = "products.dat";
    private static final String clientsFile = "clients.dat";

    public RentalStore() {
        this.products = new ArrayList<Item>();
        this.clients = new ArrayList<Client>();
        createsFile(productsFile);
        createsFile(clientsFile);
    }

    public ArrayList<Item> getProducts() {
        return products;
    }

    public boolean addProduct(Item item) {
        boolean added = this.products.add(item);
        productsToFile();
        return added;
    }

    public Item removeProduct(Item item) {
        Item removed = null;
        try {
            int index = this.products.indexOf(item);
            if (index < 0) {
                throw new IllegalArgumentException("This product was not found.");
            } else {
                removed = this.products.remove(index);
                productsToFile();
                return removed;
            } 
            // return this.products.remove(this.products.indexOf(item));
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return removed;
        }
        
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public boolean addClient(Client client) {
        boolean added = this.clients.add(client);
        clientsToFile();
        return added;
    }

    public Client removeClient(Client client) {
        try {
            int index = this.clients.indexOf(client);
            if (index < 0) {
                throw new IllegalArgumentException("This client was not found.");
            } else {
                return this.clients.remove(this.clients.indexOf(client));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean alreadySigned(String cpf) {
        for (Client client : clients) {
            if (client.getCpf().equals(cpf)) {
                // client is already signed in the rental store
                return true;
            }
        }
        // if went through every object Client in the ArrayList, there is no
        // client with this cpf signed up yet
        return false;
    }

    public Client searchClient(String cpf) {
        for (Client client : clients) {
            if (client.getCpf().equals(cpf)) {
                // found the client by their CPF
                return client;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String list = "Rental Store\n\tProducts:\t";
        for (int i = 0; i < this.products.size(); i++) {
            list += "\t\t- " + this.products.get(i).getName()
                    + " (" + this.products.get(i).getQuantity() + ") "
                    + this.products.get(i).getPrice() + "\n";
        }
        list += "\tClients:\n";
        for (int i = 0; i < this.clients.size(); i++) {
            list += "\t\t- " + this.clients.get(i).getName() + "\n";
        }
        return list;
    }


    
    protected void productsToFile() {
        try {
            try(FileOutputStream out = new FileOutputStream(productsFile);
            ObjectOutputStream obj = new ObjectOutputStream(out)) {
                for(Item product : products) {
                    obj.writeObject(product);
                }
                
            }    
            //obj.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    protected void clientsToFile() {
        try {
            //garantees that the file is not overwriten everytime a new client is added
            try(
                FileOutputStream f = new FileOutputStream(clientsFile);
                ObjectOutputStream output = new ObjectOutputStream(f)) {
                    for (Client client : clients) {
                        output.writeObject(client);
                    }
                }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    // reads the products from the file products_file into the array
    protected void productsFromFile() {
        int i = 0;
        ArrayList<Item> tempArray = new ArrayList<>();
        try {
            try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(productsFile));) {
                
                while(true) {
                    try {
                        Item p = (Item) input.readObject();
                        tempArray.add(p);
                        System.out.println("Reading product "+ (++i));
                        System.out.println("Product: " + p.getName()+ "\n" + "Quantity: " + p.getQuantity()+ "\n" + 
                                        "Available: " + p.getAvailableQuantity() + "\n" + "Release Year: " + p.getReleaseYear() +
                                        "\n" + "Recommended Age: " + p.getRecommendedAge() + "\n" + "Price: " + p.getPrice() + "\n" +
                                        "Rating: " + p.getAverageRating() + "\n");
                    } catch(EOFException e) {
                        System.out.println(e.getMessage());  
                        break;  
                    }
                }
            } 
        } catch (ClassNotFoundException classNotFoundException) {
            System.exit(1);
        } catch(IOException ex) {
            ex.printStackTrace();
        } finally {
            this.products.clear();
            this.products.addAll(tempArray);
        }
        
    }

    // reads the products from the file products_file into the array
    protected void clientsFromFile() {
        int i=0;
        ArrayList<Client> tempArray = new ArrayList<>();
        try {
            
            try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(clientsFile))) {
                
                while(true) {
                    try {
                        Client c = (Client) input.readObject();
                        tempArray.add(c);
                        System.out.println("Reading client "+ (++i));
                        System.out.println("Client: "+ c.getName()+"\n" +"CPF: "+ c.getCpf()+ "\n" +"Age: "+ c.getAge()+"\n" + "Saldo: "+c.getBalance()+ "\n" +"Loans: "+ c.getLoans());
                    } catch(EOFException e) {
                        System.out.println(e.getMessage());  
                        break;
                    }
                }
            }
        } catch (ClassNotFoundException classNotFoundException) {
            System.exit(1);
        } catch(IOException ex) {
            ex.printStackTrace();
        } finally {
            this.clients.clear();
            this.clients.addAll(tempArray);
        }
        
    }

    protected void createsFile(String name) {
        File file = new File(name);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
