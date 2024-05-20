package manage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int option = 0;
        System.out.println("Welcome to our rental store service!\n");
        do {
            System.out.println("\tChoose a profile:\n"
                    + "\t\t[1] Manager\n"
                    + "\t\t[2] Client\n"
                    + "\t\t[3] Exit\n");
            option = Integer.parseInt(input.nextLine());
            switch (option) {
                case 1:
                    Menu.managerMenu();
                    break;
                case 2:
                    Menu.clientMenu();
                    break;
                case 3:
                    System.out.println("Goodbye :)\n");
                    break;
            }
        } while (option != 3);
        input.close();
    }
}
