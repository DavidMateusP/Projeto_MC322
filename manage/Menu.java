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
}
