package Modules;

import java.time.LocalDate;
import java.util.Date;

public class RoomBooking {
    private int reservationNumber;
    private LocalDate startDate;
    private int durationInDays;
    private int guestId;
    private String roomId;

    public RoomBooking(int reservationNumber, LocalDate startDate, int durationInDays, int guestId,  String roomId) {
        this.reservationNumber = reservationNumber;
        this.startDate = startDate;
        this.durationInDays = durationInDays;
        this.guestId = guestId;
        this.roomId = roomId;
    }

    public RoomBooking fectchDetails(String reservationNumber){return null;};

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoom(String room) {
        this.roomId = room;
    }

}