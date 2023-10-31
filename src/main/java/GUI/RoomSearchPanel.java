package GUI;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.NumberFormatter;
import java.awt.event.*;
import java.text.*;
import java.time.*;
import java.time.format.*;

import Controllers.RoomBookingController;
import Controllers.RoomController;
import Controllers.RoomController.RoomSearchQuery;
import Modules.Room;

public class RoomSearchPanel extends ChildrenPanel {
    RoomController roomController;
    RoomBookingController rommBookingController;

    JLabel roomStyleLabel;
    JComboBox<Room.RoomStyle> roomStyleComboBox;

    JLabel dateLabel;
    JTextField dateField;

    JLabel durationLabel;
    JTextField durationField;

    JButton searchButton;
    JButton bookButton;
    JButton resetButton;

    public RoomSearchPanel() {
        super();
        roomController = new RoomController();
        refreshTableScrollPane(roomController.getAll());
        rommBookingController = new RoomBookingController();
        
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

        bookButton =  getFormattedButton("Book", 30, 200, 80, 24, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = panelTable.getSelectedRow();
                panelTable.getValueAt(row, 0);
                
            //     RoomBooking roomBooking = new RoomBooking(69, LocalDate.now(), Integer.valueOf(durationField.getText()),
            //             Integer.valueOf(accountIDField.getText()), roomNumberField.getText());
            //     refreshTableScrollPane(roomBookingController.add(roomBooking));
            }
        });
        add(bookButton);
    }
}