package GUI;

import java.text.Format;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controllers.DatabaseManager;

import java.awt.event.*;

public class ChildrenPanel extends JPanel {
    protected MainFrame mainFrame;
    protected int pkIndex;
    protected DatabaseManager.TableState tableState;
    protected JTable panelTable;
    protected JScrollPane panelScrollPane;

    public ChildrenPanel(MainFrame f, int i) {
        super();

        mainFrame = f;
        setLayout(null);

        pkIndex = i;
    }

    public ChildrenPanel(MainFrame f) {
        super();

        mainFrame = f;
        setLayout(null);

        pkIndex = -1;
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

    protected JFormattedTextField getFormattedTextField(int x, int y, int w, int h, String defaultString) {
        JFormattedTextField textField = new JFormattedTextField();
        textField.setBounds(x, y, w, h);
        textField.setText(defaultString);
        return textField;
    }

    protected JFormattedTextField getFormattedTextField(Format format, int x, int y, int w, int h, String defaultString) {
        JFormattedTextField textField = new JFormattedTextField(format);
        textField.setBounds(x, y, w, h);
        textField.setText(defaultString);
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
    
    protected void refreshTableScrollPane(DatabaseManager.TableState ts) {
        tableState = ts;
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

    protected void refreshTableScrollPane(DatabaseManager.TableState ts, boolean isEmpty) {
        tableState = ts;
        DatabaseManager.TableState visibleTableState;
        if (pkIndex != -1) {
            visibleTableState = removePKFromTableState(ts);
        } else {
            visibleTableState = tableState;
        }

        if (isEmpty) {
            visibleTableState.data = new String[0][visibleTableState.columns.length];
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

    protected DatabaseManager.TableState removePKFromTableState(DatabaseManager.TableState ts) {
        String[] columns = new String[ts.columns.length - 1];
        System.arraycopy(ts.columns, 0, columns, 0, pkIndex); 
        System.arraycopy(ts.columns, pkIndex+1, columns, pkIndex, ts.columns.length - pkIndex-1);

        String[][] data = new String[ts.data.length][ts.columns.length - 1];
        for (int i = 0; i < ts.data.length; i++) {
            data[i] = new String[ts.columns.length - 1];
            System.arraycopy(ts.data[i], 0, data[i], 0, pkIndex); 
            System.arraycopy(ts.data[i], pkIndex+1, data[i], pkIndex, ts.data[i].length - pkIndex-1);
        }

        return new DatabaseManager.TableState(columns, data);
    }

    public String[] getSelectedRow() {
        int rowIndex = panelTable.getSelectedRow();
        if (rowIndex == -1) {
            return null;
        } else {
            return tableState.data[rowIndex];
        }
    }

    protected void scrollPaneValueChanged(String[] row) {}

    protected LocalDate getLocalDateFromString(String s, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate date = LocalDate.parse(s, formatter);
        return date;
    }

    protected String getStringFromLocalDate(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String s = localDate.format(formatter);
        return s;
    }

    // Messages
    public void onDataBaseChange() {

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
