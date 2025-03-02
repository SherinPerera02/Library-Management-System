import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();//create object from library class to access methods in it
        UserController userController = new UserController();//create object from UserController class to access methods in it
        String choice;
        do {
            displayMenu();
            System.out.print("Enter your choice : ");//prompt user to select option from list
            choice = scanner.nextLine();
            switch (choice) {//select functions according to the user input using switch statement
                case "0":
                    System.out.println("Exit from Application...");
                    break;
                case "1":
                    library.addNewBook();//add new book
                    break;
                case "2":
                    library.searchBook();//search book
                    break;
                case "3":
                    library.getAllAvailableBooks();//get all available books
                    break;
                case "4":
                    library.getAllBooks();//get all books
                    break;
                case "5":
                    userController.addUser();//add new user
                    break;
                case "6":
                    userController.searchUser();// search user
                    break;
                case "7":
                    library.checkOutBook(userController);//check out a book
                    break;
                case "8":
                    library.checkInBook(userController);//check in a book
                    break;
                default:
                    System.out.println("!!! Wrong selection !!!");
            }
        } while (!choice.equals("0"));
    }


    public static void displayMenu() {
        //use to display welcome menu
        System.out.println("\n");
        System.out.println("===================================WELCOME TO SHERIN'S LIBRARY===========================");
        System.out.println("|\t\t\t\tSELECT FROM FOLLOWING OPTIONS\t\t\t\t|");
        System.out.println("=========================================================================================");
        System.out.println("=========================================================================================");
        System.out.println("|\tPress 1 to Add new Book.\t\t\t\t\t\t\t|");
        System.out.println("|\tPress 2 to Search a Book.\t\t\t\t\t\t\t|");
        System.out.println("|\tPress 3 to Show All Available Books.\t\t\t\t\t\t|");
        System.out.println("|\tPress 4 to Show All Books.\t\t\t\t\t\t\t|");
        System.out.println("|\tPress 5 to Register user.\t\t\t\t\t\t\t|");
        System.out.println("|\tPress 6 to Search User.\t\t\t\t\t\t\t\t|");
        System.out.println("|\tPress 7 to Borrow a Book.\t\t\t\t\t\t\t|");
        System.out.println("|\tPress 8 to Return a Book.\t\t\t\t\t\t\t|");
        System.out.println("|\tPress 0 to Exit Application.\t\t\t\t\t\t\t|");
        System.out.println("=========================================================================================");
    }
}
