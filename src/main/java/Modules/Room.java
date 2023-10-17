package Modules;


public class Room {
    private String roomNumber;
    private RoomStyle style;
    private boolean isSmoking;

    public enum RoomStyle {
        STANDARD,
        DELUXE,
        FAMILY_SUITE,
        BUSINESS_SUITE
    }

    public boolean isRoomAvailable(){ return false; };
    public boolean checkin(){return false;};
    public boolean checkout(){return false;};

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomStyle getStyle() {
        return style;
    }

    public void setStyle(RoomStyle style) {
        this.style = style;
    }

    public void getStatus(boolean status) {
        /**
         * Implement Start Date + duration
         */

    }

    public boolean isSmoking() {
        return isSmoking;
    }

    public void setSmoking(boolean smoking) {
        isSmoking = smoking;
    }

}

