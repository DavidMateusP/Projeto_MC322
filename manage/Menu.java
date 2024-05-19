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
import client.Loan;

public class Menu {
    private RentalStore rental = new RentalStore();
    private Scanner input = new Scanner(System.in);
    private String userCPF;

    public static int addProductMenu(){
        //TODO: treat exceptions, check items' toString()
        int option = 0;
        String name = "";
        int quantity = 0, releaseYear = 0, recommendedAge = 0;
        double price = 0;
        Item newProduct;
        do{
            System.out.println("\tChoose a type of product:\n"
            + "\t\t[1] Album\n"
            + "\t\t[2] Board Game\n"
            + "\t\t[3] Book\n"
            + "\t\t[4] Movie\n"
            + "\t\t[5] Back");
            option = Integer.parseInt(input.nextLine());
            if (option >= 1 && option <= 4){
                System.out.println("\tEnter name:");
                name = input.nextLine();
                System.out.println("\tEnter quantity:");
                quantity = Integer.parseInt(input.nextLine());
                System.out.println("\tEnter year of release (YYYY):");
                releaseYear = Integer.parseInt(input.nextLine());
                System.out.println("\tEnter  appropriate age:");
                recommendedAge = Integer.parseInt(input.nextLine());
                System.out.println("\tEnter price (0.00):");
                price = Double.parseDouble(input.nextLine());
            }
    
            switch(option){
                case 1:
                    newProduct = new Album(quantity, name, releaseYear, recommendedAge, price);
                    
                    System.out.println("\tEnter number of tracks:");
                    int numTracks = Integer.parseInt(input.nextLine());
                    for (int i = 0; i < numTracks; i++){
                        System.out.println("\tEnter name of track " + (i + 1) + ":");
                        String track = input.nextLine();
                        ((Album)newProduct).addTrack(track);
                    }
                    
                    rental.addProduct(newProduct);
                    System.out.println("\tProduct added to stock! :)");
                    break;

                case 2:
                    System.out.println("\tEnter minimum number of players:");
                    int minPlayers = Integer.parseInt(input.nextLine());
                    System.out.println("\tEnter maximum number of players:");
                    int maxPlayers = Integer.parseInt(input.nextLine());
                    
                    newProduct = new BoardGame(quantity, name, releaseYear, recommendedAge, price, minPlayers, maxPlayers);
                    rental.addProduct(newProduct);
                    System.out.println("\tProduct added to stock! :)");
                    break;

                case 3:
                    System.out.println("\tEnter number of pages:");
                    int pages = Integer.parseInt(input.nextLine());
                    
                    newProduct = new Book(quantity, name, releaseYear, recommendedAge, price, pages);
                    rental.addProduct(newProduct);
                    System.out.println("\tProduct added to stock! :)");
                    break;

                case 4:

                    System.out.println("\tEnter cast (Actor 1, Actor 2, Actor 3)");
                    String cast = input.nextLine();
                    
                    ArrayList<String> subtitleLanguages = new ArrayList<String>();
                    System.out.println("\tEnter number of subtitle languages:");
                    int numSubtitles = Integer.parseInt(input.nextLine());
                    for (int i = 0; i < numSubtitles; i++){
                        System.out.println("\tEnter language:");
                        String language = input.nextLine();
                        subtitleLanguages.add(language);
                    }
                    
                    newProduct = new Movie(quantity, name, releaseYear, recommendedAge, price, cast, subtitleLanguages);
                    rental.addProduct(newProduct);
                    System.out.println("\tProduct added to stock! :)");
                    break;

                case 5:
                    break;

                default:
                    System.out.println("Invalid Option!\n" + "\t\t\t:(\n");
            }
        }while(option != 5);
        return 0;
    }

    public static int editProductMenu(){
        int option = 0;
        int size = rental.getProducts().size();

        if (size == 0){
            System.out.println("\tThere is no product to edit.\n"); 
            return 0;
        }

        do{
        System.out.println("\tChoose a product to edit:");
        for (int i = 0; i < size; i++){
            System.out.println("\t\t["+ (i + 1) + "] " + rental.getProducts().get(i).getClass().getName().substring(9) + " " + rental.getProducts().get(i).getName());
        }
        System.out.println("\t\t["+ (size + 1) + "] Back");
        option = Integer.parseInt(input.nextLine());
        Item product = rental.getProducts().get(option - 1);
        // TODO: menus for each one
        if (product.getClass().getName().substring(9) == "Album"){
            System.out.println("It is an Album");
        }
        else if (product.getClass().getName().substring(9) == "BoardGame"){
            System.out.println("It is a Board Game");
        }
        else if (product.getClass().getName().substring(9) == "Book"){
            System.out.println("It is a Book");
        }
        else if (product.getClass().getName().substring(9) == "Movie"){
            System.out.println("It is a Movie");
        }

        }  while (option != size + 1);
        return 0;
    }

    public static int managerMenu(){
        int option = 0;
        System.out.println("Welcome!\n");
        do{
        System.out.println("\tChoose an action:\n"
                            + "\t\t[1] Add product\n"
                            + "\t\t[2] Edit product\n"
                            + "\t\t[3] Back\n");
        option = Integer.parseInt(input.nextLine());
        switch(option){
            case 1: // Add Product
                addProductMenu();
                break;

            case 2: //Edit product
                editProductMenu();
                break;

            case 3: // Back
                break;

            default:
                System.out.println("Invalid Option!\n" + "\t\t\t:(\n");
        }
        
        } while (option != 3);
        return 0;
    }

    public static int clientMenu() {
        int option;
        System.out.println("Welcome!\n");
        do {
            System.out.println("\tChoose an action:\n"
                                + "\t\t[1] Sign In\n"
                                + "\t\t[2] Sign Up\n"
                                + "\t\t[3] Loan\n"
                                + "\t\t[4] Back");

            option = Integer.parseInt(input.nextLine());

            switch(option) {
                case 1:
                    signIn();
                    break;
                case 2:
                    signUp();
                    break;
                case 3:
                    loan();
                    break;
                default:
                    System.err.println("Invalid Option!\n" + "\t\t\t:(\n");
            }
        } while(option != 4);

        return 0;
    }

    private boolean signIn() {
        System.out.println("Please enter your cpf: ");
        String cpf = input.nextLine();

        if(!rental.alreadySigned(cpf)) {
            System.err.println("You are not signed up yet. Please create an account!");
            return false;
        } 
        System.out.println("Signed in successfully!");
        userCPF = cpf;
        return true;

    }

    private boolean signUp() {
        System.out.println("\tPlease, enter your full name:");
        String name = input.nextLine();

        String cpf;
        int numInputs = 3, counter = 0;

        

        // The user may insert a cpf value up until 3 tries if not valid
        while (counter < numInputs) {
            System.out.println("\tPlease, enter your cpf:");
            cpf = input.nextLine();
            
            // Method yet to implement -> labs
            if (ValidatesDocument.validCPF(cpf)) {
                break;
            } else {
                System.err.println("Invalid cpf, please enter it again:");
                counter++;
            }
        }
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
        if(rental.alreadySigned(cpf)) {
            System.err.println("There is already an account with this cpf.");
            return false;
        }

        // adds Client to the rental Store ArrayList of clients signed up
        rental.addClient(client);

        // Prints message of successful account creation
        System.out.println("Account successfully created!");

        return true;
    }   

    private boolean loan() {
        if(!signIn()) {
            // Not signed in
            System.out.println("You should sign in first, please.");
            return false;
        }

        Client client = rental.searchClient(userCPF);

        System.out.println("Please, enter the name of the item you would like to borrow: ");
        String product = input.nextLine();

        if(rental.getProducts().size() == 0) {
            return false;
        } 

        for(Item item : rental.getProducts()) {
            if(item.getName().equals(product)) {
                if(item.getAvailableQuantity() > 0) {
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

    

}
