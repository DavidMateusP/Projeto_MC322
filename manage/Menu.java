package manage;

import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;

import client.Client;
import products.Album;
import products.BoardGame;
import products.Book;
import products.Item;
import products.Movie;

public class Menu {
    private RentalStore rental = new RentalStore();
    private Scanner input = new Scanner(System.in);

    public int managerMenu(){
        int option = 0;
        System.out.println("Welcome!\n");
        do{
        System.out.println("\tChoose an action:\n"
                            + "\t\t[1] Add Client\n"
                            + "\t\t[2] Back\n");
        option = Integer.parseInt(input.nextLine());
        switch(option){
            case 1: // Add Product
                int optionProduct = 0;
                String name;
                int quantity, releaseYear, recommendedAge;
                double price;
                Item newProduct;
                do{
                    System.out.println("\tChoose a type of product:\n"
                    + "\t\t[1] Album\n"
                    + "\t\t[2] Board Game\n"
                    + "\t\t[3] Book\n"
                    + "\t\t[4] Movie\n"
                    + "\t\t[5] Back");
                    optionProduct = Integer.parseInt(input.nextLine());
                    if (optionProduct >= 1 && optionProduct <= 4){
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
                    // int quantity, String name, int releaseYear, int recommendedAge, double price
                    switch(optionProduct){
                        case 1:
                            newProduct = new Album(quantity, name, releaseYear, recommendedAge, price);
                            System.out.println("\tEnter number of tracks:");
                            int numTracks = Integer.parseInt(input.nextLine());
                            for (int i = 0; i < numTracks; i++){
                                System.out.println("\tEnter name of track " + (i + 1) + ":");
                                String track = input.nextLine();
                                ((Album)newProduct).addTrack(track);
                            }
                            break;
                        case 2:
                            newProduct = new BoardGame();
                            break;
                        case 3:
                            newProduct = new Book();
                            break;
                        case 4:
                            newProduct = new Movie();
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("Invalid Option!\n" + "\t\t\t:(\n");
                    }
                }while(optionProduct <= 0 || optionProduct > 5);
                break;
            case 2:
                break;
            default:
                System.out.println("Invalid Option!\n" + "\t\t\t:(\n");
        }
        
        } while (option != 1 && option != 2);
        return 0;
    }

    public int clientMenu() {
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
                    //signIn();
                    break;
                case 2:
                    signUp();
                    break;
                case 3:
                    // loan/addLoan();
                    break;
                default:
                    System.err.println("Invalid Option!\n" + "\t\t\t:(\n");
            }
        } while(option != 4);

        return 0;
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

}
