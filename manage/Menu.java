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
    private static RentalStore rental = new RentalStore();
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
            option = readInt();
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
            rental.addProduct(newProduct);
            System.out.println("\tProduct added to stock! :)");
        } while (option != 5);
        return 0;
    }

    public static int deleteProductMenu() {
        String name;
        int size = rental.getProducts().size();

        if (size == 0) {
            System.out.println("\tThere is no product to delete.\n");
            return 0;
        }

        System.out.println("Type the name of the product you want to delete:");
        name = input.nextLine().trim();
        Item item = null;
        for (Item i : rental.getProducts()) {
            if (i.getName() == name) {
                item = i;
                break;
            }
        }
        if (item == null) {
            System.out.println("There is no item with the name '" + name + "'");
        } else {
            rental.removeProduct(item);
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
                    + "\t\t[3] Back\n");
            option = Integer.parseInt(input.nextLine());
            switch (option) {
                case 1: // Add Product
                    addProductMenu();
                    break;
                case 2: // Delete product
                    deleteProductMenu();
                    break;
                case 3: // Back
                    break;
                default:
                    System.out.println("Invalid Option!\n" + "\t\t\t:(\n");
            }

        } while (option != 3);
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
        } while (option != 4);

        return 0;
    }

    private static boolean signIn() {
        System.out.println("Please enter your cpf: ");
        String cpf = input.nextLine();

        if (!rental.alreadySigned(cpf)) {
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
            if (validateCPF(cpf)) {
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

        int day = Integer.parseInt(birthday.substring(0, 1));
        Month month = Month.of(Integer.parseInt(birthday.substring(3, 4)));
        int year = Integer.parseInt(birthday.substring(6));
        LocalDate birthdate = LocalDate.of(day, month, year);

        // Instantiantes and inicializes Client object
        Client client = new Client(name, birthdate, cpf);
        // checks if this client is already signed up
        if (rental.alreadySigned(cpf)) {
            System.err.println("There is already an account with this cpf.");
            return false;
        }

        // adds Client to the rental Store ArrayList of clients signed up
        rental.addClient(client);

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

        Client client = rental.searchClient(userCPF);

        System.out.println("Please, enter the name of the item you would like to borrow: ");
        String product = input.nextLine();

        if (rental.getProducts().size() == 0) {
            return false;
        }

        for (Item item : rental.getProducts()) {
            if (item.getName().equals(product)) {
                if (item.getAvailableQuantity() > 0) {
                    // There is an available item
                    if (Loan.newLoan(client, item)) {
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

        Client client = rental.searchClient(userCPF);

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

    public static boolean validateCPF(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // CPF deve ter 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Cálculo do primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int firstDigit = 11 - (sum % 11);
        if (firstDigit > 9) {
            firstDigit = 0;
        }

        // Cálculo do segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int secondDigit = 11 - (sum % 11);
        if (secondDigit > 9) {
            secondDigit = 0;
        }

        // Verifica se os dígitos calculados são iguais aos fornecidos
        return (Character.getNumericValue(cpf.charAt(9)) == firstDigit) &&
                (Character.getNumericValue(cpf.charAt(10)) == secondDigit);
    }

}
