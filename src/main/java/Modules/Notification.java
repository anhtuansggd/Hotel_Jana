package Modules;

public class Notification {
    private String id;
    private int account_id;
    private String message;


    public Notification(String id, int account_id, String message) {
        this.id = id;
        this.account_id = account_id;
        this.message = message;
    }

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public int getAccount_id() {return account_id;}

    public void setAccount_id(int account_id) {this.account_id = account_id;}

    public String getMessage() {return message;}

    public void setMessage(String message) {this.message = message;}
}
