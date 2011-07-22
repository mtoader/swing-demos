package ro.redeul.swing.demo.table;

import javax.swing.table.AbstractTableModel;

/**
 * Author: Toader Mihai Claudiu <mtoader@gmail.com>
 * <p/>
 * Date: 7/22/11
 * Time: 1:51 PM
 */
public class BigTableModel extends AbstractTableModel {

    int rowCount;

    public BigTableModel() {

    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: case 1:
                return String.format("Generated text %d", column);
            case 2:
                return String.format("Number (row %% 43)");
            case 3:
                return String.format("True/False");
            case 4:
                return String.format("Number (=rowIndex)");
            case 5:
                return String.format("Text");
        }

        return "";
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.format("Cell_%d_%d", columnIndex, rowIndex);
            case 1:
                return String.format("Cell_%d_%d", columnIndex, rowCount - 1 - rowIndex);
            case 2:
                return rowIndex % 43;
            case 3:
                return rowIndex % 3 == 0;
            case 4:
                return rowIndex;
            case 5:
                return "<static string>";
        }

        return "";
    }

    public void resizeModel(int value) {
        rowCount = value;
        fireTableDataChanged();
    }
}
