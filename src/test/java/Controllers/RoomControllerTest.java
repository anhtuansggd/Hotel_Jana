package Controllers;

import Modules.Room;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RoomControllerTest {

    @Test
    public void add() {
        RoomController controller = new RoomController();
        Room r = new Room("109", Room.RoomStyle.STANDARD, 1) ;
        controller.add(r);
    }

    @Test
    public void update() {
        RoomController controller = new RoomController();
        Room r = new Room("109", Room.RoomStyle.STANDARD, 0) ;
        controller.update(r);
    }

    @Test
    public void delete() {
        RoomController controller = new RoomController();
        Room r = new Room("109", Room.RoomStyle.STANDARD, 1) ;
        controller.delete(r);
    }



    @Test
    public void search( ){
        RoomController controller = new RoomController();
        LocalDate date = LocalDate.parse("2023-10-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //controller.search(new RoomController.RoomSearchQuery("Standard", date, 1));

    }

    @Test
    public void find() {
    }
}