package GUI;

import javax.swing.*;
import javax.swing.event.*;

import Controllers.AccountController;
import Controllers.Controller.*;
import Modules.Account;

import java.awt.event.*;

public class AccountManagementPanel extends ChildrenPanel {
    AccountController accountController;

    JLabel idLabel;
    JTextField idField;

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

    public AccountManagementPanel() {
        super();
        accountController = new AccountController();
        refreshTableScrollPane(accountController.getAll());

        idLabel = getFormattedLabel("ID", 30, 30, 120, 30);
        add(idLabel);
        idField = getFormattedTextField(130, 40, 120, 24);
        add(idField);

        accountTypeLabel = getFormattedLabel("Account Type", 30, 60, 120, 30);
        add(accountTypeLabel);
        accountTypeComboBox = getFormattedComboBox(Account.AccountType.class, 130, 70, 120, 24);
        add(accountTypeComboBox);

        usernameLabel = getFormattedLabel("Username", 30, 90, 120, 30);
        add(usernameLabel);
        usernameField = getFormattedTextField(130, 100, 120, 24);
        add(usernameField);

        passwordLabel = getFormattedLabel("Password", 30, 120, 120, 30);
        add(passwordLabel);
        passwordField = getFormattedTextField(130, 130, 120, 24);
        add(passwordField);

        nameLabel = getFormattedLabel("Name", 30, 150, 120, 30);
        add(nameLabel);
        nameField = getFormattedTextField(130, 160, 120, 24);
        add(nameField);

        raceLabel = getFormattedLabel("Account Type", 30, 180, 120, 30);
        add(raceLabel);
        raceComboBox = getFormattedComboBox(Account.Race.class, 130, 190, 120, 24);
        add(raceComboBox);

        addButton = getFormattedButton("Add", 30, 240, 80, 24, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TableState tableState = accountController.add(new Account(idField.getText(), (Account.AccountType)accountTypeComboBox.getSelectedItem(),
                        usernameField.getText(), passwordField.getText(), nameField.getText(),
                        (Account.Race)raceComboBox.getSelectedItem()));
                refreshTableScrollPane(tableState);
            }
        });
        add(addButton);

        updateButton = getFormattedButton("Update", 30, 280, 80, 24, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TableState tableState = accountController.update(new Account(idField.getText(), (Account.AccountType)accountTypeComboBox.getSelectedItem(),
                        usernameField.getText(), passwordField.getText(), nameField.getText(),
                        (Account.Race)raceComboBox.getSelectedItem()));
                refreshTableScrollPane(tableState);
            }
        });;
        add(updateButton);

        deleteButton = getFormattedButton("Delete", 30, 320, 80, 24, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TableState tableState = accountController.delete(new Account(idField.getText(), (Account.AccountType)accountTypeComboBox.getSelectedItem(),
                        usernameField.getText(), passwordField.getText(), nameField.getText(),
                        (Account.Race)raceComboBox.getSelectedItem()));
                refreshTableScrollPane(tableState);
            }
        });;
        add(deleteButton);
/*
        searchButton = getFormattedButton("Search", 30, 360, 80, 24, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TableState tableState = accountController.search(new AccountController.AccountSearchQuery(idField.getText(),
                        (Account.AccountType)accountTypeComboBox.getSelectedItem(),
                        usernameField.getText(), nameField.getText(),
                        (Account.Race)raceComboBox.getSelectedItem()));
                refreshTableScrollPane(tableState);
            }
        });;
        add(searchButton);

        resetButton = getFormattedButton("Reset", 30, 400, 80, 24, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshTableScrollPane(accountController.getAll());
            }
        });;
        add(resetButton);

 */
    }
}