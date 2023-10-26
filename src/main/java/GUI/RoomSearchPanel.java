package GUI;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.NumberFormatter;
import java.awt.event.*;
import java.text.*;

import Controllers.Controller;
import Controllers.RoomController;
import Modules.Room;


public class RoomSearchPanel extends ChildrenPanel {
    Controller controller;

    JLabel roomStyleLabel;
    JComboBox<Room.RoomStyle> roomStyleComboBox;

    JLabel dateLabel;
    JFormattedTextField dateTextField;

    JLabel durationLabel;
    JFormattedTextField durationTextField;

    JButton searchButton;
    JButton bookButton;

    JTable roomTable;
    JScrollPane roomScrollPane;
    String roomTableColumns[] = {
        "ID", "NAME", "SALARY"
    };

    public RoomSearchPanel() {
        super();
        controller = new RoomController();
        
        // Room style input
        roomStyleLabel = getFormattedLabel("Room style", 30, 30, 120, 30);
        add(roomStyleLabel);
        roomStyleComboBox = getFormattedComboBox(Room.RoomStyle.class, 130, 40, 120, 20);
        add(roomStyleComboBox);

        // Date input
        dateLabel = getFormattedLabel("Date", 30, 60, 120, 30);
        add(dateLabel);

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        dateTextField = getFormattedTextField(df, 130, 70, 120, 20, "10/2/2024");
        add(dateTextField);

        // Duration input
        durationLabel = getFormattedLabel("Duration", 30, 90, 120, 30);
        add(durationLabel);

        NumberFormat numFormat = new DecimalFormat("#");
        NumberFormatter numFormatter  = new NumberFormatter(numFormat); 
        durationTextField = getFormattedTextField(numFormatter, 130, 100, 40, 20, "2");
        add(durationTextField);

        // Search button
        searchButton = getFormattedButton("Search", 30, 160, 80, 24, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // System.out.println((Date)dateTextField.getValue());
                System.out.printf("Duration: %d days\n", durationTextField.getValue());
            }
        });
        add(searchButton);

        bookButton =  getFormattedButton("Book", 30, 200, 80, 24, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // System.out.println((Date)dateTextField.getValue());
                if (roomTable.getSelectedRow() != -1) {
                    Object roomNumber = roomTable.getValueAt(roomTable.getSelectedRow(), 0);
                    System.out.printf("Book room number " + roomNumber + " for " + durationTextField.getValue() + " days\n");
                }
            }
        });
        add(bookButton);

        // Room table
        Controller.TableState tableState = controller.getAll();
        TableScrollPane tableScrollPane = getFormattedTableScrollPane(
            tableState.data, tableState.columns, 280, 30, 630, 200,
            new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    System.out.println("Row " + roomTable.getSelectedRow() + " selected");
                }
            }
        );
        roomTable = tableScrollPane.table;
        roomScrollPane = tableScrollPane.scrollPane;
        add(roomScrollPane);

        // Room info
            // Add labels that show selected room info
            // Add buttons to book the room
    }

    String[][] getRoomTableData() {
        String[][] data = {
            {"101", "Ami", "650"},
            {"102", "FSi", "730"},
        };
        return data;
    }
}