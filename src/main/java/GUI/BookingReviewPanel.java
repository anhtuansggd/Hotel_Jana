package GUI;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controllers.RoomBookingController;

public class BookingReviewPanel extends ChildrenPanel {
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

    JTable bookingTable;
    JScrollPane bookingScrollPane;

    public BookingReviewPanel() {
        super(new RoomBookingController());

        reservationNumberLabel = getFormattedLabel("Reservation number", 30, 30, 120, 30);
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

        addButton = getFormattedButton("Add", 30, 210, 80, 24);
        add(addButton);

        updateButton = getFormattedButton("Update", 30, 250, 80, 24);
        add(updateButton);

        deleteButton = getFormattedButton("Delete", 30, 290, 80, 24);
        add(deleteButton);

        searchButton = getFormattedButton("Search", 30, 330, 80, 24);
        add(searchButton);

        resetButton = getFormattedButton("Reset", 30, 370, 80, 24);
        add(resetButton);
        
        String[][] data = {{"Alo", "Ola"}, {"Alo", "Ola"}};
        String[] columns = {"Alo", "Ola"};
        TableScrollPane tableScrollPane = getFormattedTableScrollPane(
            data, columns, 280, 30, 630, 200,
            new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    System.out.println("Row " + bookingTable.getSelectedRow() + " selected");
                }
            }
        );
        bookingTable = tableScrollPane.table;
        bookingScrollPane = tableScrollPane.scrollPane;
        add(bookingScrollPane);

    }   
}