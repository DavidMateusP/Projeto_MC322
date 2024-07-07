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
        // if the product was propperly added to the array, it is included in the file
        if (added) {
            productsToFile(item);
        }
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
                productsToFile(item);
            } 
            // return this.products.remove(this.products.indexOf(item));
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;

        }
        return removed;
        
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public boolean addClient(Client client) {
        boolean added = this.clients.add(client);
        // if the client was propperly added to the array, it is included in the file
        if (added) {
            clientsToFile(client);
        }
        return added;
    }

    public Client removeClient(Client client) {
        Client removed = null;
        try {
            int index = this.clients.indexOf(client);
            if (index < 0) {
                throw new IllegalArgumentException("This client was not found.");
            } else {
                removed = this.clients.remove(this.clients.indexOf(client));
                clientsToFile(client);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return removed;
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

    protected void productsToFile(Item product) {
        try {
            try(FileOutputStream out = new FileOutputStream(productsFile, true);
            ObjectOutputStream obj = new ObjectOutputStream(out)) {
                obj.writeObject(product);
            }    
            //obj.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    protected void clientsToFile(Client client) {
        try {
            //garantees that the file is not overwriten everytime a new client is added
            try(
                FileOutputStream f = new FileOutputStream(clientsFile, true);
                ObjectOutputStream output = new ObjectOutputStream(f)) {
                    output.writeObject(client);
                    //output.close();
                }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    // reads the products from the file products_file into the array
    protected void productsFromFile() {
        int i = 0;
        try {
            try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(productsFile));) {
       
                while(true) {
                    Item p = (Item) input.readObject();
                    System.out.println("Lendo o produto "+ (++i));
                    System.out.println("Produto: " + p.getName()+ "\n" + "Quantidade: " + p.getQuantity()+ "\n" + 
                                        "Available: " + p.getAvailableQuantity() + "\n" + "Release Year: " + p.getReleaseYear() +
                                        "\n" + "Recommended Age: " + p.getRecommendedAge() + "\n" + "Price: $" + p.getPrice() + "\n" +
                                        "Rating: " + p.getAverageRating() + "\n");
                }
            } 
        } catch (EOFException endOfFileException) {
            return;
        } catch (ClassNotFoundException classNotFoundException) {
            System.exit(1);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        
    }

    // reads the products from the file products_file into the array
    protected void clientsFromFile() {
        int i=0;
        try {
            @SuppressWarnings("resource")
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(clientsFile));
            while(true) {
                Client c = (Client) input.readObject();
                System.out.println("Reading client "+ (++i));
                System.out.println("Client: " + c.getName()+ "\n" + "CPF: " + c.getCpf()+ "\n" + 
                                        "Age: " + c.getAge() + "\n" + "Balance: $" + c.getBalance());
            }
        } catch (EOFException endOfFileException) {
            return;
        } catch (ClassNotFoundException classNotFoundException) {
            System.exit(1);
        } catch(IOException ex) {
            ex.printStackTrace();
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
