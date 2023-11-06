package GUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controllers.DatabaseManager;
import Controllers.RoomBookingController;
import Modules.RoomBooking;

import java.awt.event.*;
import java.time.LocalDate;

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

    public BookingReviewPanel(MainFrame f) {
        super(f, 0);
        roomBookingController = new RoomBookingController();
        refreshTableScrollPane(roomBookingController.getAll());

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
                RoomBooking roomBooking = new RoomBooking(-1,
                        getLocalDateFromString(startDateField.getText(), "dd/MM/yyyy"), Integer.valueOf(durationField.getText()),
                        Integer.valueOf(accountIDField.getText()), roomNumberField.getText());
                refreshTableScrollPane(roomBookingController.add(roomBooking));
            }
        });
        add(addButton);

        updateButton = getFormattedButton("Update", 30, 250, 80, 24, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (getSelectedRow() == null) return;

                RoomBooking roomBooking = new RoomBooking(Integer.valueOf(getSelectedRow()[0]),
                        getLocalDateFromString(startDateField.getText(), "dd/MM/yyyy"), Integer.valueOf(durationField.getText()),
                        Integer.valueOf(accountIDField.getText()), roomNumberField.getText());
                refreshTableScrollPane(roomBookingController.update(roomBooking));
            }
        });
        add(updateButton);

        deleteButton = getFormattedButton("Delete", 30, 290, 80, 24, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (getSelectedRow() == null) return;

                RoomBooking roomBooking = new RoomBooking(Integer.valueOf(getSelectedRow()[0]),
                        getLocalDateFromString(startDateField.getText(), "dd/MM/yyyy"), Integer.valueOf(durationField.getText()),
                        Integer.valueOf(accountIDField.getText()), roomNumberField.getText());
                refreshTableScrollPane(roomBookingController.delete(roomBooking));
            }
        });
        add(deleteButton);

        //searchButton = getFormattedButton("Search", 30, 330, 80, 24);
        //add(searchButton);

        resetButton = getFormattedButton("Reset", 30, 370, 80, 24, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshTableScrollPane(roomBookingController.getAll());
            }
        });
        add(resetButton);
    }   

    @Override
    protected void refreshTableScrollPane(DatabaseManager.TableState ts) {
        tableState = ts;

        for (int i = 0; i < tableState.data.length; i++) {
            LocalDate localDate = getLocalDateFromString(tableState.data[i][1].substring(0, 10), "yyyy-MM-dd");
            tableState.data[i][1] = getStringFromLocalDate(localDate);
        }

        DatabaseManager.TableState visibleTableState;
        if (pkIndex != -1) {
            visibleTableState = removePKFromTableState(ts);
        } else {
            visibleTableState = tableState;
        }

        TableScrollPane tableScrollPane = getFormattedTableScrollPane(
            visibleTableState.data, visibleTableState.columns, 280, 30, 630, 620, new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    String[] row = getSelectedRow();
                    scrollPaneValueChanged(row);
                }
        });
            
        if (panelScrollPane != null) {
            remove(panelScrollPane);
        }
        panelTable = tableScrollPane.table;
        panelScrollPane = tableScrollPane.scrollPane;
        add(panelScrollPane);

        revalidate();
        repaint();
    }

    @Override
    protected void scrollPaneValueChanged(String[] row) {
        startDateField.setText(row[1]);

        durationField.setText(row[2]);

        roomNumberField.setText(row[3]);

        accountIDField.setText(row[4]);
    }
}