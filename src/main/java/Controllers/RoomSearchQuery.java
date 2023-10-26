package Controllers;

import Modules.Room;

import java.time.LocalDate;

public class RoomSearchQuery {
    public Room.RoomStyle roomStyle;
    public LocalDate startDate;
    public int duration ;

    public RoomSearchQuery(Room.RoomStyle roomStyle, LocalDate startDate, int duration) {
        this.roomStyle = roomStyle;
        this.startDate = startDate;
        this.duration = duration;
    }

    public Room.RoomStyle getRoomStyle() {
        return roomStyle;
    }

    public void setRoomStyle(Room.RoomStyle roomStyle) {
        this.roomStyle = roomStyle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
