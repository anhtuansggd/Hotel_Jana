package GUI;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

import java.awt.Component;

public class WordWrapCellRenderer extends JTextArea implements TableCellRenderer {
    public WordWrapCellRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value != null ? value.toString() : "");
        setOpaque(true);
        setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
        setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());

        setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
        if (table.getRowHeight(row) != getPreferredSize().height) {
            table.setRowHeight(row, getPreferredSize().height);
        }

        return this;
    }
}