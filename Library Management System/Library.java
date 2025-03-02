import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Book> bookList = new ArrayList<>();


    public void addNewBook() {
        //adding new book
        System.out.println("=========================================================================================");
        System.out.println("|\t\t\t\tADD NEW BOOK\t\t\t\t\t\t|");
        System.out.println("=========================================================================================");
        System.out.print("Enter ISBN : ");
        String isbn = scanner.nextLine();
        if (bookIsExist(isbn)) { //check if book exists
            System.out.println("This ISBN is already Exists...!");
        } else {
            System.out.print("Enter book title : ");
            String title = scanner.nextLine();
            System.out.print("Enter author's name : ");
            String author = scanner.nextLine();
            System.out.println("Book type\n :-press 1 for Educational Books\n :-press 2 for Other Books");
            System.out.print("Enter type Option : ");
            String type = scanner.nextLine();
            switch (type) {//create book objects according to the book type
                case "1":
                    bookList.add(new EducationalBooks(title, author, isbn, true));//store book object in the book list
                    System.out.println("Book Added Successfully....\n\n");
                    break;
                case "2":
                    bookList.add(new OtherBooks(title, author, isbn, true));//store book object in the book list
                    System.out.println("Book Added Successfully....\n\n");
                    break;
                default:
                    System.out.println("Wrong Type");
            }
        }
    }

    public void searchBook() {//search a book
        System.out.println("=========================================================================================");
        System.out.println("|\t\t\t\tSEARCH BOOK\t\t\t\t\t\t|");
        System.out.println("=========================================================================================");
        System.out.print("Enter ISBN : ");
        String isbn = scanner.nextLine();
        if (bookIsExist(isbn)) {//check if book exist
            for (Book book : bookList) {
                if (book.getIsbn().equals(isbn)) {//search book from book list using isbn
                    String availability = book.getAvailability() ? "Available" : "Not Available";
                    //print book details
                    System.out.println(book.getIsbn() + "\t" + book.getTitle() + "\tby," + book.getAuthor() + "\t(" + availability + ")\n\n");
                }
            }
        } else {
            System.out.println("No book exist for this ISBN...\n\n");//print when book is not exists
        }
    }

    public void getAllBooks() {
        //prints all the books in the list
        System.out.println("=========================================================================================");
        System.out.println("|\t\t\t\tGET ALL BOOKS\t\t\t\t\t\t|");
        System.out.println("=========================================================================================");
        if (bookList.size() == 0) {
            System.out.println("No books available...");
        } else {
            for (Book book : bookList) {
                String availability = book.getAvailability() ? "Available" : "Not Available";
                System.out.println(book.getIsbn() + "\t" + book.getTitle() + "\tby," + book.getAuthor() + "\t(" + availability + ")");
            }
        }
        System.out.println("\n\n");
    }

    public void getAllAvailableBooks() {
        //prints only available books in that time
        System.out.println("=========================================================================================");
        System.out.println("|\t\t\t\tALL AVAILABLE BOOKS\t\t\t\t\t|");
        System.out.println("=========================================================================================");
        if (bookList.size() == 0) {//check book list is empty
            System.out.println("No books available...");
        } else {
            for (Book book : bookList) {
                if(book.getAvailability()) {//check if book is available or not
                    System.out.println(book.getIsbn() + "\t" + book.getTitle() + "\tby," + book.getAuthor());
                }
            }
        }
        System.out.println("\n\n");
    }

    public void checkOutBook(UserController userController) {
        System.out.println("=========================================================================================");
        System.out.println("|\t\t\t\tBORROW BOOK\t\t\t\t\t\t|");
        System.out.println("=========================================================================================");
        System.out.print("Enter ISBN : ");
        String isbn = scanner.nextLine();
        if (bookIsExist(isbn)) {//check book is exist
            for (Book book : bookList) {
                if (book.getIsbn().equals(isbn)) {//get book from list
                    if (book.getAvailability()) {//check if book is available for borrow
                        System.out.print("Enter User id : ");//get user id
                        String id = scanner.nextLine();
                        if (userController.userIsExist(id)) {//check user is exists or not
                            book.setAvailability(false);//set book status to not available
                            for (User user : userController.userList) {
                                if (user.getUserId().equals(id)) {//select user from user list
                                    //add book to the user borrowed list
                                    user.getBorrowList().add(new Book(book.getTitle(), book.getAuthor(), book.getIsbn(), false));
                                    System.out.println("Borrowed Successfully...\n\n");
                                    return;
                                }
                            }
                        } else {
                            System.out.println("No user exist for this id...\n\n");
                            break;
                        }
                    } else {
                        System.out.println("This Book is not available...");
                        break;
                    }
                }
            }
        } else {
            System.out.println("No book exist for this ISBN...\n\n");
        }
    }

    public void checkInBook(UserController userController) {
        System.out.println("=========================================================================================");
        System.out.println("|\t\t\t\tRETURN BOOK\t\t\t\t\t\t|");
        System.out.println("=========================================================================================");
        System.out.print("Enter User Id : ");
        String id = scanner.nextLine();//get uer id
        if (userController.userIsExist(id)) {//check if valid user id
            System.out.print("Enter ISBN : ");
            String isbn = scanner.nextLine();//get isbn
            if (bookIsExist(isbn)) {//check if valid isbn
                for (User user : userController.userList) {
                    if (user.getUserId().equals(id)) {
                        ArrayList<Book> borrowList = user.getBorrowList();
                        for (Book borrowBook : borrowList) {
                            if (borrowBook.getIsbn().equals(isbn)) {//check if book is in or not in users borrowed list
                                borrowList.remove(borrowBook);//remove book from borrowed list
                                for (Book book : bookList) {
                                    if (book.getIsbn().equals(isbn)) {
                                        book.setAvailability(true);//set book avaliability to true
                                        break;
                                    }
                                }
                                System.out.println("Returned Successfully...\n\n");
                            }else {
                                System.out.println("This Book Cannot find in users borrow list\n\n");
                            }
                            return;
                        }
                    }
                }
            } else {
                System.out.println("This Book is not available...");
            }
        } else {
            System.out.println("No user exist for this id...\n\n");
        }
    }

    public boolean bookIsExist(String isbn) {//check if book is exists or not
        for (Book book : bookList) {
            if (book.getIsbn().equals(isbn)) {
                return true;
            }
        }
        return false;
    }


}
