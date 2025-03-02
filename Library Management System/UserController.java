import java.util.ArrayList;
import java.util.Scanner;

public class UserController {
    Scanner scanner = new Scanner(System.in);
    ArrayList<User> userList = new ArrayList<>();

    public void addUser() {//add new user
        System.out.println("=========================================================================================");
        System.out.println("|\t\t\t\tADD NEW USER\t\t\t\t\t\t|");
        System.out.println("=========================================================================================");
        System.out.print("Enter userId : ");
        String id = scanner.nextLine();
        if (userIsExist(id)) {//check if user already exists
            System.out.println("This User is already Exists...!");
        } else {
            userList.add(new User(id));//add user to user list
            System.out.println("User Added Successfully....\n\n");
        }
    }

    public void searchUser() {//search user
        System.out.println("=========================================================================================");
        System.out.println("|\t\t\t\tSEARCH USER\t\t\t\t\t\t|");
        System.out.println("=========================================================================================");
        System.out.print("Enter user id : ");
        String id = scanner.nextLine();
        if (userIsExist(id)) {
            for (User user : userList) {
                if (user.getUserId().equals(id)) {
                    System.out.println("USER : " + id);
                    System.out.println("*******************BORROWED LIST*******************");
                    ArrayList<Book> borrowList = user.getBorrowList();
                    if (borrowList.size() == 0) {
                        System.out.println("User not borrowed any books....\n\n");
                    } else {
                        for (Book book : borrowList) {
                            System.out.println(book.getIsbn() + "\t" + book.getTitle() + "\tby," + book.getAuthor());
                        }
                    }
                }
            }
        } else {
            System.out.println("No user exist for this id...\n\n");
        }
    }

    public boolean userIsExist(String id) {//check if user is exists or not
        for (User user : userList) {
            if (user.getUserId().equals(id)) {
                return true;
            }
        }
        return false;
    }

}
