package GUI;


import javax.swing.*;
import Controllers.RoomBookingController;
import Modules.RoomBooking;

import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookingReviewPanel extends ChildrenPanel {
    RoomBookingController roomBookingController;

    JLabel reservationNumberLabel;
    JTextField reservationNumberField;

    JLabel startDateLabel;
    JTextField startDateField;

    JLabel durationLabel;
    JTextField durationField;

    JLabel roomNumberLabel;
    JTextField roomNumberField;

    JLabel accountIDLabel;
    JTextField accountIDField;

    JButton addButton;

    JButton updateButton;

    JButton deleteButton;

    JButton searchButton;

    JButton resetButton;

    public BookingReviewPanel() {
        super();
        roomBookingController = new RoomBookingController();
        refreshTableScrollPane(roomBookingController.getAll());

        reservationNumberLabel = getFormattedLabel("Reservation num.", 30, 30, 120, 30);
        add(reservationNumberLabel);
        reservationNumberField = getFormattedTextField(130, 40, 120, 20);
        add(reservationNumberField);

        startDateLabel = getFormattedLabel("Start date", 30, 60, 120, 30);
        add(startDateLabel);
        startDateField = getFormattedTextField(130, 70, 120, 20);
        add(startDateField);

        durationLabel = getFormattedLabel("Duration", 30, 90, 120, 30);
        add(durationLabel);
        durationField = getFormattedTextField(130, 100, 120, 20);
        add(durationField);

        roomNumberLabel = getFormattedLabel("Room number", 30, 120, 120, 30);
        add(roomNumberLabel);
        roomNumberField = getFormattedTextField(130, 130, 120, 20);
        add(roomNumberField);

        accountIDLabel = getFormattedLabel("Account ID", 30, 150, 120, 30);
        add(accountIDLabel);
        accountIDField = getFormattedTextField(130, 160, 120, 20);
        add(accountIDField);

        addButton = getFormattedButton("Add", 30, 210, 80, 24, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RoomBooking roomBooking = new RoomBooking(Integer.valueOf(reservationNumberField.getText()),
                        getLocalDateFromString(startDateField.getText()), Integer.valueOf(durationField.getText()),
                        Integer.valueOf(accountIDField.getText()), roomNumberField.getText());
                refreshTableScrollPane(roomBookingController.add(roomBooking));
            }
        });
        add(addButton);

        updateButton = getFormattedButton("Update", 30, 250, 80, 24, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RoomBooking roomBooking = new RoomBooking(Integer.valueOf(reservationNumberField.getText()),
                        getLocalDateFromString(startDateField.getText()), Integer.valueOf(durationField.getText()),
                        Integer.valueOf(accountIDField.getText()), roomNumberField.getText());
                refreshTableScrollPane(roomBookingController.update(roomBooking));
            }
        });
        add(updateButton);

        deleteButton = getFormattedButton("Delete", 30, 290, 80, 24, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RoomBooking roomBooking = new RoomBooking(Integer.valueOf(reservationNumberField.getText()),
                        getLocalDateFromString(startDateField.getText()), Integer.valueOf(durationField.getText()),
                        Integer.valueOf(accountIDField.getText()), roomNumberField.getText());
                refreshTableScrollPane(roomBookingController.delete(roomBooking));
            }
        });
        add(deleteButton);

        searchButton = getFormattedButton("Search", 30, 330, 80, 24);
        add(searchButton);

        resetButton = getFormattedButton("Reset", 30, 370, 80, 24);
        add(resetButton);
    }   

    private LocalDate getLocalDateFromString(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(s, formatter);
        return date;
    }
}