package GUI;

import java.text.Format;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;

import java.awt.event.*;

public class ChildrenPanel extends JPanel {
    public ChildrenPanel() {
        super();
        setLayout(null);
    }

    protected static <T extends Enum<T>> JComboBox<T> getFormattedComboBox(Class<T> type, int x, int y, int w, int h) {
        JComboBox<T> comboBox = new JComboBox<>(type.getEnumConstants());
        comboBox.setBounds(x, y, w, h);
        return comboBox;
    }

    protected JLabel getFormattedLabel(String text, int x, int y, int w, int h) {
        JLabel label = new JLabel(text);
        label.setVerticalAlignment(JLabel.BOTTOM);
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setBounds(x, y, w, h);
        return label;
    }

    protected JFormattedTextField getFormattedTextField(int x, int y, int w, int h) {
        JFormattedTextField textField = new JFormattedTextField();
        textField.setBounds(x, y, w, h);
        return textField;
    }

    protected JFormattedTextField getFormattedTextField(int x, int y, int w, int h, String defaulString) {
        JFormattedTextField textField = new JFormattedTextField();
        textField.setBounds(x, y, w, h);
        textField.setText(defaulString);
        return textField;
    }

    protected JFormattedTextField getFormattedTextField(Format format, int x, int y, int w, int h, String defaulString) {
        JFormattedTextField textField = new JFormattedTextField(format);
        textField.setBounds(x, y, w, h);
        textField.setText(defaulString);
        return textField;
    }

    protected JFormattedTextField getFormattedTextField(JFormattedTextField.AbstractFormatter format,
            int x, int y, int w, int h, String defaulString) {
        JFormattedTextField textField = new JFormattedTextField(format);
        textField.setBounds(x, y, w, h);
        textField.setText(defaulString);
        return textField;
    }

    protected JButton getFormattedButton(String text, int x, int y, int w, int h) {
        JButton button = new JButton(text);
        button.setBounds(x, y, w, h);
        return button;
    }

    protected JButton getFormattedButton(String text, int x, int y, int w, int h, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setBounds(x, y, w, h);
        button.addActionListener(actionListener);
        return button;
    }

    protected TableScrollPane getFormattedTableScrollPane(String[][] data, String[] columns, int x, int y, int w, int h,
            ListSelectionListener listSelectionListener) {
        JTable table = new JTable(data, columns) {
            private static final long serialVersionUID = 1L;
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(true);;
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener(listSelectionListener);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(x, y, w, h);

        return new TableScrollPane(table, scrollPane);
    }

    protected TableScrollPane getFormattedTableScrollPane(String[][] data, String[] columns, int x, int y, int w, int h) {
        JTable table = new JTable(data, columns) {
            private static final long serialVersionUID = 1L;
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(true);;
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(x, y, w, h);

        return new TableScrollPane(table, scrollPane);
    }

    public static class TableScrollPane {
        public JTable table;
        public JScrollPane scrollPane;

        public TableScrollPane(JTable t, JScrollPane sp) {
            table = t;
            scrollPane = sp;
        }
    }
}
