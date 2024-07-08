package manage;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;

import client.Client;
import products.Album;
import products.BoardGame;
import products.Book;
import products.Item;
import products.Movie;
import products.Track;
import client.Loan;

public class Menu {
    private static Scanner input = new Scanner(System.in);
    private static String userCPF;

    // Manager

    private static int readInt() {
        int res;
        while (true) {
            try {
                res = Integer.parseInt(input.nextLine());
                return res;
            } catch (NumberFormatException e) {
                System.out.println("The option must be an integer");
            }
        }
    }

    public static int addProductMenu() {
        int option = 0;
        String name = "";
        int quantity = 0, releaseYear = 0, recommendedAge = 0;
        double price = 0;
        Item newProduct;
        do {
            System.out.println("\tChoose a type of product:\n"
                    + "\t\t[1] Album\n"
                    + "\t\t[2] Board Game\n"
                    + "\t\t[3] Book\n"
                    + "\t\t[4] Movie\n"
                    + "\t\t[5] Back");
            try {
                option = readInt();
            } catch (NumberFormatException e) {
                System.out.println("The option must be an integer.");
            }

            if (option >= 1 && option <= 4) {
                System.out.println("\tEnter name:");
                name = input.nextLine();
                System.out.println("\tEnter quantity:");
                quantity = readInt();
                System.out.println("\tEnter year of release (YYYY):");
                releaseYear = readInt();
                System.out.println("\tEnter  appropriate age:");
                recommendedAge = readInt();
                System.out.println("\tEnter price (0.00):");
                price = Double.parseDouble(input.nextLine());
            }

            switch (option) {
                case 1:
                    newProduct = new Album(quantity, name, releaseYear, recommendedAge, price);

                    System.out.println("\tEnter number of tracks:");
                    int numTracks = readInt();
                    for (int i = 0; i < numTracks; i++) {
                        System.out.println("\tEnter name of track " + (i + 1) + ":");
                        String track = input.nextLine();
                        ((Album) newProduct).addTrack(new Track(track));
                    }
                    break;

                case 2:
                    System.out.println("\tEnter minimum number of players:");
                    int minPlayers = readInt();
                    System.out.println("\tEnter maximum number of players:");
                    int maxPlayers = readInt();

                    newProduct = new BoardGame(quantity, name, releaseYear, recommendedAge, price, minPlayers,
                            maxPlayers);
                    break;

                case 3:
                    System.out.println("\tEnter number of pages:");
                    int pages = readInt();

                    newProduct = new Book(quantity, name, releaseYear, recommendedAge, price, pages);
                    break;

                case 4:
                    System.out.println("\tEnter cast (Actor 1, Actor 2, Actor 3)");
                    String cast = input.nextLine();

                    ArrayList<String> subtitleLanguages = new ArrayList<String>();
                    System.out.println("\tEnter number of subtitle languages:");
                    int numSubtitles = readInt();
                    for (int i = 0; i < numSubtitles; i++) {
                        System.out.println("\tEnter language:");
                        String language = input.nextLine();
                        subtitleLanguages.add(language);
                    }

                    newProduct = new Movie(quantity, name, releaseYear, recommendedAge, price, cast, subtitleLanguages);
                    break;

                case 5:
                    return 0;

                default:
                    System.out.println("Invalid Option!\n" + "\t\t\t:(\n");
                    continue;
            }
            RentalStore.addProduct(newProduct);
            System.out.println("\tProduct added to stock! :)");
        } while (option != 5);
        return 0;
    }

    public static int deleteProductMenu() {
        String name;
        int size = RentalStore.getProducts().size();

        if (size == 0) {
            System.out.println("\tThere is no product to delete.\n");
            return 0;
        }

        System.out.println("Type the name of the product you want to delete:");
        name = input.nextLine().trim();
        Item item = null;
        for (Item i : RentalStore.getProducts()) {
            if (i.getName() == name) {
                item = i;
                break;
            }
        }
        if (item == null) {
            System.out.println("There is no item with the name '" + name + "'");
        } else {
            RentalStore.removeProduct(item);
            System.out.println("Item removed");
        }
        return 0;
    }

    public static int managerMenu() {
        int option = 0;
        System.out.println("Welcome!\n");
        do {
            System.out.println("\tChoose an action:\n"
                    + "\t\t[1] Add product\n"
                    + "\t\t[2] Delete product\n"
                    + "\t\t[3] Read Clients' Files\n"
                    + "\t\t[4] Read Products' Files\n"
                    + "\t\t[5] Back\n");
            option = Integer.parseInt(input.nextLine());
            switch (option) {
                case 1: // Add Product
                    addProductMenu();
                    break;
                case 2: // Delete product
                    deleteProductMenu();
                    break;
                case 3: // Reads clients' file
                    RentalStore.clientsFromFile();
                    break;
                case 4: // Reads products' file
                    RentalStore.productsFromFile();
                    break;
                case 5: // Back
                    break;
                default:
                    System.out.println("Invalid Option!\n" + "\t\t\t:(\n");
            }

        } while (option != 5);
        return 0;
    }

    // Client

    public static int clientMenu() {
        int option;
        System.out.println("Welcome!\n");
        do {
            System.out.println("\tChoose an action:\n"
                    + "\t\t[1] Sign In\n"
                    + "\t\t[2] Sign Up\n"
                    + "\t\t[3] Loan\n"
                    + "\t\t[4] Return\n"
                    + "\t\t[5] Back");

            option = Integer.parseInt(input.nextLine());

            switch (option) {
                case 1:
                    signIn();
                    break;
                case 2:
                    signUp();
                    break;
                case 3:
                    loan();
                    break;
                case 4:
                    returnItem();
                    break;
                case 5:
                    break;
                default:
                    System.err.println("Invalid Option!\n" + "\t\t\t:(\n");
            }
        } while (option != 5);

        return 0;
    }

    private static boolean signIn() {
        System.out.println("Please enter your cpf: ");
        String cpf = input.nextLine();

        if (!RentalStore.alreadySigned(cpf)) {
            System.err.println("You are not signed up yet. Please create an account!");
            return false;
        }
        System.out.println("Signed in successfully!");
        userCPF = cpf;
        return true;

    }

    private static boolean signUp() {
        System.out.println("\tPlease, enter your full name:");
        String name = input.nextLine();

        String cpf;
        int numInputs = 3, counter = 0;

        // The user may insert a cpf value up until 3 tries if not valid
        do {
            System.out.println("\tPlease, enter your cpf:");
            cpf = input.nextLine();

            // Method yet to implement -> labs
            if (RentalStore.validateCPF(cpf)) {
                break;
            } else {
                System.err.println("Invalid cpf, please enter it again:");
                counter++;
            }
        } while (counter < numInputs);
        if (counter == 3) {
            System.err.println("Sign up failed. Exceeded 3 attempts.");
            return false;
        }

        // Reads input for birthday date and format it for LocalDate
        System.out.println("\nPlease enter birthday (DD/MM/YYYY):");
        String birthday = input.nextLine();

        String[] parts = birthday.split("/");
        int day = Integer.parseInt(parts[0]);
        Month month = Month.of(Integer.parseInt(parts[1]));
        int year = Integer.parseInt(parts[2]);
        LocalDate birthdate = LocalDate.of(year, month, day);

        // Instantiantes and inicializes Client object
        Client client = new Client(name, birthdate, cpf);
        // checks if this client is already signed up
        if (RentalStore.alreadySigned(cpf)) {
            System.err.println("There is already an account with this cpf.");
            return false;
        }

        // adds Client to the rental Store ArrayList of clients signed up
        RentalStore.addClient(client);

        // Prints message of successful account creation
        System.out.println("Account successfully created!");

        return true;
    }

    private static boolean loan() {
        if (!signIn()) {
            // Not signed in
            System.out.println("You should sign in first, please.");
            return false;
        }

        Client client = RentalStore.searchClient(userCPF);

        System.out.println("Please, enter the name of the item you would like to borrow: ");
        String product = input.nextLine();

        if (RentalStore.getProducts().size() == 0) {
            return false;
        }

        for (Item item : RentalStore.getProducts()) {
            if (item.getName().equals(product)) {
                if (item.getAvailableQuantity() > 0) {
                    // There is an available item
                    if (Loan.newLoan(client, item).equals("Sucesso")) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    // The item is not available
                    System.err.println("Sorry, this item is currently out of stock.");
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean returnItem() {
        if (!signIn()) {
            // Not signed in
            System.out.println("You should sign in first, please.");
            return false;
        }

        Client client = RentalStore.searchClient(userCPF);

        if (client.getLoans().isEmpty()) {
            return false;
        }

        System.out.println("Please, enter the name of the item you would like to return: ");
        String product = input.nextLine().trim();

        for (Loan loan : client.getLoans()) {
            if (loan.getItem().getName() == product) {
                loan.returnItem(null);
                return true;
            }
        }
        return false;
    }

    

}
