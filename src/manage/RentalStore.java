package manage;

import java.io.*;
import java.util.ArrayList;
import products.Item;
import client.Client;

public class RentalStore {
    private static ArrayList<Item> products = new ArrayList<Item>();;
    private static ArrayList<Client> clients = new ArrayList<Client>();
    private static final String productsFile = "products.dat";
    private static final String clientsFile = "clients.dat";
    private static boolean productsFileCreated = createsFile(productsFile);
    private static boolean clientsFileCreated = createsFile(clientsFile);

    public static ArrayList<Item> getProducts() {
        return products;
    }

    public static boolean addProduct(Item item) {
        boolean added = products.add(item);
        // if the product was propperly added to the array, it is included in the file
        productsToFile();
        return added;
    }

    public static Item removeProduct(Item item) {
        Item removed = null;
        try {
            int index = products.indexOf(item);
            if (index < 0) {
                throw new IllegalArgumentException("This product was not found.");
            } else {
                removed = products.remove(index);
                productsToFile();
                return removed;
            } 
            // return this.products.remove(this.products.indexOf(item));
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return removed;
        }  
    }

    public static ArrayList<Client> getClients() {
        return clients;
    }

    public static boolean addClient(Client client) {
        boolean added = clients.add(client);
        // if the client was propperly added to the array, it is included in the file
        clientsToFile();
        return added;
    }

    public static Client removeClient(Client client) {
        Client removed = null;
        try {
            int index = clients.indexOf(client);
            if (index < 0) {
                throw new IllegalArgumentException("This client was not found.");
            } else {
                removed = clients.remove(this.clients.indexOf(client));
                clientsToFile();
                return removed;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return removed;
        }
    }

    public static boolean alreadySigned(String cpf) {
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

    public static Client searchClient(String cpf) {
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
        for (int i = 0; i < products.size(); i++) {
            list += "\t\t- " + products.get(i).getName()
                    + " (" + products.get(i).getQuantity() + ") "
                    + products.get(i).getPrice() + "\n";
        }
        list += "\tClients:\n";
        for (int i = 0; i < clients.size(); i++) {
            list += "\t\t- " + clients.get(i).getName() + "\n";
        }
        return list;
    }

    protected static void productsToFile() {
        try {
            try(FileOutputStream out = new FileOutputStream(productsFile);
            ObjectOutputStream obj = new ObjectOutputStream(out)) {
                for(Item product : products) {
                    obj.writeObject(product);
                }
            }    
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    protected static void clientsToFile() {
        try {
            //garantees that the file is not overwriten everytime a new client is added
            try(
                FileOutputStream f = new FileOutputStream(clientsFile);
                ObjectOutputStream output = new ObjectOutputStream(f)) {
                    for(Client client : clients) {
                        output.writeObject(client);
                    }
                    
                    //output.close();
                }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    // reads the products from the file products_file into the array
    protected static void productsFromFile() {
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
                                        "\n" + "Recommended Age: " + p.getRecommendedAge() + "\n" + "Price: $" + p.getPrice() + "\n" +
                                        "Rating: " + p.getAverageRating() + "\n");
                    } catch (EOFException e) {
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
            products.clear();
            products.addAll(tempArray);
        }
        
    }

    // reads the products from the file products_file into the array
    protected static void clientsFromFile() {
        int i=0;
        ArrayList<Client> tempArray = new ArrayList<>();
        try {
            try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(clientsFile))) {
            
                while(true) {
                    try {
                        Client c = (Client) input.readObject();
                    tempArray.add(c);
                    System.out.println("Reading client "+ (++i));
                    System.out.println("Client: " + c.getName()+ "\n" + "CPF: " + c.getCpf()+ "\n" + 
                                            "Age: " + c.getAge() + "\n" + "Balance: $" + c.getBalance());
                    } catch (EOFException e) {
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
            clients.clear();
            clients.addAll(tempArray);
        }
    }

    protected static boolean createsFile(String name) {
        File file = new File(name);
        if (!file.exists()) {
            try {
                file.createNewFile();
                return true;
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return true;
    }
}
