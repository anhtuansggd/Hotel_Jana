package Modules;

public class Notification {
    private String id;
    private int reservation_number;
    private String message;

    public Notification(int reservation_number, String message){
        this.reservation_number = reservation_number;
        this.message = message;
    }

    public Notification() {

    }

    public String getId() {return id;}

    public int getReservation_number() {
        return reservation_number;
    }

    public void setReservation_number(int reservation_number) {
        this.reservation_number = reservation_number;
    }

    public String getMessage() {return message;}

    public void setMessage(String message) {this.message = message;}
}
