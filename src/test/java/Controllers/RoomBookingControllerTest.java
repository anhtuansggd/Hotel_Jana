package Controllers;

import Modules.Room;
import Modules.RoomBooking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class RoomBookingControllerTest {

    @org.junit.Test
    public void add() throws ParseException {
        RoomBookingController controller = new RoomBookingController();
        Room r = new Room();
        r.setRoomNumber("101");
        RoomBooking roomBooking = new RoomBooking(1, LocalDate.of(2023, 10, 25), 1, 1,r.getRoomNumber());
        controller.add(roomBooking);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").parse(roomBooking.getStartDate().toString()));
        System.out.println("works");
    }

    @org.junit.Test
    public void update() {
    }

    @org.junit.Test
    public void delete() {
    }

    @org.junit.Test
    public void find() {
    }
}