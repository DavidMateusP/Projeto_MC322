package manage;

public class Main {
    public static void main(String[] args) {

        java.util.Scanner input = new java.util.Scanner(System.in);
        int option = 0;
        System.out.println("Welcome to our rental store service!\n");
        do {
            try{
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
                    default:
                        System.out.println("Invalid option, please try again.");
                }
            } catch(NumberFormatException e) {
                System.out.println("The option must be an integer.");
            } 
        } while (option != 3);
        input.close();

    }
}
