package Controllers;

import Modules.Room;
import Modules.RoomBooking;

import java.text.ParseException;
import java.time.LocalDate;

public class RoomBookingControllerTest {

    @org.junit.Test
    public void add() throws ParseException {
        RoomBookingController controller = new RoomBookingController();
        Room r = new Room();
        r.setRoomNumber("101");
        RoomBooking roomBooking = new RoomBooking(1, LocalDate.of(2023, 10, 25), 1, 1,r.getRoomNumber());
        controller.add(roomBooking);
        System.out.println("works");
    }

    @org.junit.Test
    public void update() {
        RoomBookingController controller = new RoomBookingController();
        Room r = new Room();
        r.setRoomNumber("101");
        RoomBooking roomBooking = new RoomBooking(1, LocalDate.of(2023, 10, 22), 1, 1,r.getRoomNumber());
        controller.update(roomBooking);
    }

    @org.junit.Test
    public void delete() {
        RoomBookingController controller = new RoomBookingController();
        Room r = new Room();
        r.setRoomNumber("101");
        RoomBooking roomBooking = new RoomBooking(1, LocalDate.of(2023, 10, 25), 1, 1,r.getRoomNumber());
        controller.delete(roomBooking);
    }

    @org.junit.Test
    public void find() {
    }
}