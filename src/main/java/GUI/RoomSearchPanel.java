package GUI;


import javax.swing.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.*;
import java.awt.Color;

import Controllers.NotificationController;
import Controllers.RoomBookingController;
import Controllers.RoomController;
import Controllers.RoomController.RoomSearchQuery;
import Modules.Notification;
import Modules.Room;
import Modules.RoomBooking;

public class RoomSearchPanel extends ChildrenPanel {
    RoomController roomController;
    RoomBookingController roomBookingController;
    NotificationController notificationController;

    JLabel roomStyleLabel;
    JComboBox<Room.RoomStyle> roomStyleComboBox;

    JLabel dateLabel;
    JTextField dateField;

    JLabel durationLabel;
    JTextField durationField;

    JButton searchButton;
    JButton bookButton;
    JButton resetButton;

    JLabel warningLabel;

    public RoomSearchPanel(MainFrame f) {
        super(f);
        roomController = new RoomController();
        refreshTableScrollPane(roomController.getAll(), true);
        roomBookingController = new RoomBookingController();
        notificationController = new NotificationController();
        
        // Room style input
        roomStyleLabel = getFormattedLabel("Room style", 30, 30, 120, 30);
        add(roomStyleLabel);
        roomStyleComboBox = getFormattedComboBox(Room.RoomStyle.class, 130, 40, 120, 20);
        add(roomStyleComboBox);

        // Date input
        dateLabel = getFormattedLabel("Date", 30, 60, 120, 30);
        add(dateLabel);

        dateField = getFormattedTextField(130, 70, 120, 20, "10/02/2024");
        add(dateField);

        // Duration input
        durationLabel = getFormattedLabel("Duration", 30, 90, 120, 30);
        add(durationLabel);
        
        durationField = getFormattedTextField(130, 100, 40, 20, "2");
        add(durationField);

        // Search button
        searchButton = getFormattedButton("Search", 30, 160, 80, 24, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputDate = dateField.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date = LocalDate.parse(inputDate, formatter);

                RoomSearchQuery rsq = new RoomSearchQuery(
                    (Room.RoomStyle)roomStyleComboBox.getSelectedItem(), date, Integer.valueOf(durationField.getText()) 
                );

                RoomController.TableState tableState =  roomController.search(rsq);
                refreshTableScrollPane(tableState);
            }
        });
        add(searchButton);

        bookButton = getFormattedButton("Book", 30, 200, 80, 24, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (getSelectedRow() == null) return;
                
                RoomBooking roomBooking = new RoomBooking(-1,
                        getLocalDateFromString(dateField.getText(), "dd/MM/yyyy"), Integer.valueOf(durationField.getText()),
                        Integer.valueOf(mainFrame.account.getId()), getSelectedRow()[0]);
                roomBookingController.add(roomBooking);

                Notification notification = new Notification(roomBooking.getReservationNumber(), "Greetings, Sir/Miss with customer number: "+ roomBooking.getGuestId()+". You have successfully booked room "+ roomBooking.getRoomId()+" on " + LocalDate.now()+" with our app. Thank you for choosing us.");
                notificationController.add(notification);
            }
        });
        add(bookButton);

        warningLabel = new JLabel("Please choose a room before booking");
        warningLabel.setVerticalAlignment(JLabel.BOTTOM);
        warningLabel.setHorizontalAlignment(JLabel.CENTER);
        warningLabel.setBounds(30, 240, 80, 48);
        warningLabel.setForeground(Color.RED);
        warningLabel.setVisible(false);
        add(warningLabel);
    }

    @Override
    protected void scrollPaneValueChanged(String[] row) {
        
    }
}