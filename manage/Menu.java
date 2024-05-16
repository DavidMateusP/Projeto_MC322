package manage;

import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;

import client.Client;

public class Menu {
    private RentalStore rental = new RentalStore();
    private Scanner input = new Scanner(System.in);

    public int managerMenu(){
        int option = 0;
        System.out.println("Welcome!\n");
        do{
        System.out.println("\tChoose an action:\n"
                            + "\t\t[1] Add Product\n" 
                            + "\t\t[2] Add Client\n"
                            + "\t\t[3] Back\n");
        option = Integer.parseInt(input.nextLine());
        switch(option){
            case 1: // Add Product
                //TODO: add product menu
                break;
            case 2: // Add Client
                System.out.println("Enter name:");
                String name = input.nextLine();

                //TODO: counter exceptions
                System.out.println("\nEnter birthday (DD/MM/YYYY):");
                String birthday = input.nextLine();

                int day = Integer.parseInt(birthday.substring(0, 1));
                Month month = Month.of(Integer.parseInt(birthday.substring(3, 4)));
                int year = Integer.parseInt(birthday.substring(6));
                LocalDate birthdate = LocalDate.of(day, month, year);

                Client newclient = new Client(name, birthdate);
                rental.addClient(newclient);
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid Option!\n" + "\t\t\t:(\n");
        }
        
        } while (option != 1 && option != 2 && option != 3);
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
