package Controllers;

import Modules.Room;
import org.junit.Test;

public class RoomSearchControllerTest {

    @Test
    public void add() {
        RoomSearchController controller = new RoomSearchController();
        Room r = new Room("109", Room.RoomStyle.STANDARD, 1) ;
        controller.add(r);
    }

    @Test
    public void update() {
        RoomSearchController controller = new RoomSearchController();
        Room r = new Room("109", Room.RoomStyle.STANDARD, 0) ;
        //controller.update(r);
    }

    @Test
    public void delete() {
        RoomSearchController controller = new RoomSearchController();
        Room r = new Room("109", Room.RoomStyle.STANDARD, 1) ;
        //controller.delete(r);
    }

    @Test
    public void find() {
    }
}