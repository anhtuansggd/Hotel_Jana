package GUI;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.NumberFormatter;
import java.awt.event.*;
import java.text.*;

import Controllers.Controller;
import Controllers.RoomBookingController;
import Controllers.RoomController;
import Modules.Room;

public class RoomSearchPanel extends ChildrenPanel {
    RoomController roomController;
    RoomBookingController rommBookingController;

    JLabel roomStyleLabel;
    JComboBox<Room.RoomStyle> roomStyleComboBox;

    JLabel dateLabel;
    JFormattedTextField dateField;

    JLabel durationLabel;
    JFormattedTextField durationField;

    JButton searchButton;
    JButton bookButton;

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

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        dateField = getFormattedTextField(df, 130, 70, 120, 20, "10/2/2024");
        add(dateField);

        // Duration input
        durationLabel = getFormattedLabel("Duration", 30, 90, 120, 30);
        add(durationLabel);

        NumberFormat numFormat = new DecimalFormat("#");
        NumberFormatter numFormatter  = new NumberFormatter(numFormat); 
        durationField = getFormattedTextField(numFormatter, 130, 100, 40, 20, "2");
        add(durationField);

        // Search button
        searchButton = getFormattedButton("Search", 30, 160, 80, 24, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.printf("Duration: %d days\n", durationField.getValue());
            }
        });
        add(searchButton);

        bookButton =  getFormattedButton("Book", 30, 200, 80, 24, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = panelTable.getSelectedRow();
                panelTable.getValueAt(row, 0);
                
                // RoomBooking roomBooking = new RoomBooking(69, LocalDate.now(), Integer.valueOf(durationField.getText()),
                //         Integer.valueOf(accountIDField.getText()), roomNumberField.getText());
                // refreshTableScrollPane(roomBookingController.add(roomBooking));
            }
        });
        add(bookButton);

        // Room table
        Controller.TableState tableState = roomController.getAll();
        TableScrollPane tableScrollPane = getFormattedTableScrollPane(
            tableState.data, tableState.columns, 280, 30, 630, 200,
            new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    System.out.println("Row " + panelTable.getSelectedRow() + " selected");
                }
            }
        );
        panelTable = tableScrollPane.table;
        panelScrollPane = tableScrollPane.scrollPane;
        add(panelScrollPane);
    }
}