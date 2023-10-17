package GUI;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.NumberFormatter;

import java.awt.event.*;
import java.text.*;
import Modules.Room;


public class RoomSearchPanel extends JPanel {
    JLabel roomStyleLabel;
    JComboBox<Room.RoomStyle> roomStyleComboBox;

    JLabel dateLabel;
    JFormattedTextField dateTextField;

    JLabel durationLabel;
    JFormattedTextField durationTextField;

    JButton searchButton;
    
    JTable roomTable;

    public RoomSearchPanel() {
        super();
        
        // Room style input
        roomStyleLabel = new JLabel("Room style");
        roomStyleLabel.setBounds(30, 30, 100, 30);
        roomStyleLabel.setVerticalAlignment(JLabel.BOTTOM);
        roomStyleLabel.setHorizontalAlignment(JLabel.LEFT);
        add(roomStyleLabel);

        roomStyleComboBox = new JComboBox<Room.RoomStyle>(Room.RoomStyle.values());
        roomStyleComboBox.setBounds(130, 40, 100, 20);
        add(roomStyleComboBox);

        // Date input
        dateLabel = new JLabel("Date");
        dateLabel.setBounds(30, 60, 100, 30);
        dateLabel.setVerticalAlignment(JLabel.BOTTOM);
        dateLabel.setHorizontalAlignment(JLabel.LEFT);
        add(dateLabel);

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        dateTextField = new JFormattedTextField(df);
        dateTextField.setBounds(130, 70, 100, 20);
        dateTextField.setText("10/2/2024");
        add(dateTextField);

        // Duration input
        durationLabel = new JLabel("Duration");
        durationLabel.setBounds(30, 90, 100, 30);
        durationLabel.setVerticalAlignment(JLabel.BOTTOM);
        durationLabel.setHorizontalAlignment(JLabel.LEFT);
        add(durationLabel);

        NumberFormat numFormat = new DecimalFormat("#"); 
        NumberFormatter numFormatter  = new NumberFormatter(numFormat); 
        durationTextField = new JFormattedTextField(numFormatter);
        durationTextField.setBounds(130, 100, 40, 20);
        durationTextField.setText("2");
        add(durationTextField);

        // Search button
        searchButton = new JButton("Seach");
        searchButton.setBounds(50, 130, 80, 30);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // System.out.println((Date)dateTextField.getValue());
                System.out.printf("Duration: %d days\n", durationTextField.getValue());
                
            }
        });
        add(searchButton);

        // Room table
        String data[][] = {
            {"101", "Ami", "650"},
            {"102", "FSi", "730"},
        };
        String columns[] = {
            "ID", "NAME", "SALARY"
        };
        roomTable = new JTable(data, columns) {
            private static final long serialVersionUID = 1L;
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        roomTable.setCellSelectionEnabled(false);
        roomTable.setRowSelectionAllowed(true);;
        ListSelectionModel selectionModel = roomTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                System.out.println("Row " + roomTable.getSelectedRow() + " selected");
            }
        });
        JScrollPane sp = new JScrollPane(roomTable);
        sp.setBounds(260, 30, 650, 200);
        add(sp);

        // Room info
            // Add labels that show selected room info
            // Add buttons to book the room

        setLayout(null);
    }
}