import java.util.ArrayList;

public class User {
    private String userId;
    private ArrayList<Book> borrowList;

    public User() {
    }

    public User(String userId) {
        this.userId = userId;
        this.borrowList = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<Book> getBorrowList() {
        return borrowList;
    }

}
