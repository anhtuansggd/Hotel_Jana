package GUI;

import javax.swing.*;
import javax.swing.event.*;

import Controllers.AccountManagementController;
import Controllers.Controller.*;
import Modules.Account;

public class AccountManagementPanel extends ChildrenPanel {
    AccountManagementController controller;

    JLabel accountTypeLabel;
    JComboBox<Account.AccountType> accountTypeComboBox;

    JLabel usernameLabel;
    JTextField usernameField;

    JLabel passwordLabel;
    JTextField passwordField;

    JLabel nameLabel;
    JTextField nameField;

    JLabel raceLabel;
    JComboBox<Account.Race> raceComboBox;

    JButton addButton;

    JButton updateButton;

    JButton deleteButton;

    JButton searchButton;

    JButton resetButton;

    JTable accountTable;
    JScrollPane accountScrollPane;

    public AccountManagementPanel() {
        super();
        controller = new AccountManagementController();

        accountTypeLabel = getFormattedLabel("Account Type", 30, 30, 120, 30);
        add(accountTypeLabel);
        accountTypeComboBox = getFormattedComboBox(Account.AccountType.class, 130, 40, 120, 20);
        add(accountTypeComboBox);

        usernameLabel = getFormattedLabel("Username", 30, 60, 120, 30);
        add(usernameLabel);
        usernameField = getFormattedTextField(130, 70, 120, 20);
        add(usernameField);

        passwordLabel = getFormattedLabel("Password", 30, 90, 120, 30);
        add(passwordLabel);
        passwordField = getFormattedTextField(130, 100, 120, 20);
        add(passwordField);

        nameLabel = getFormattedLabel("Name", 30, 120, 120, 30);
        add(nameLabel);
        nameField = getFormattedTextField(130, 130, 120, 20);
        add(nameField);

        raceLabel = getFormattedLabel("Account Type", 30, 150, 120, 30);
        add(raceLabel);
        raceComboBox = getFormattedComboBox(Account.Race.class, 130, 160, 120, 20);
        add(raceComboBox);

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

        TableState tableState = controller.getAll();
        TableScrollPane tableScrollPane = getFormattedTableScrollPane(
            tableState.data, tableState.columns, 280, 30, 630, 200,
            new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    System.out.println("Row " + accountTable.getSelectedRow() + " selected");
                }
            }
        );
        accountTable = tableScrollPane.table;
        accountScrollPane = tableScrollPane.scrollPane;
        add(accountScrollPane);
    }
}