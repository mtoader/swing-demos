package ro.redeul.swing.demo.table;

import javax.swing.*;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * Author: Toader Mihai Claudiu <mtoader@gmail.com>
 * <p/>
 * Date: 7/22/11
 * Time: 2:05 PM
 */
public class BigTableRowSorter extends RowSorter<BigTableModel> {

    BigTableModel model;

    BitSet sortableColumns = new BitSet();

    int sortColumn = 0;
    SortOrder sortOrder = SortOrder.ASCENDING;


    public BigTableRowSorter(BigTableModel model, int ... sortableColumns) {
        super();
        this.model = model;
        for (int sortableColumn : sortableColumns) {
            this.sortableColumns.set(sortableColumn);
        }
    }

    @Override
    public BigTableModel getModel() {
        return model;
    }

    @Override
    public void toggleSortOrder(int column) {
        if ( sortableColumns.get(column) ) {
            sortColumn = column;
            sortOrder = sortOrder == SortOrder.ASCENDING ? SortOrder.DESCENDING : SortOrder.ASCENDING;

            fireSortOrderChanged();
        }
    }

    @Override
    public int convertRowIndexToModel(int index) {
        return sortOrder == SortOrder.DESCENDING ? getModelRowCount() - index - 1 : index;
    }

    @Override
    public int convertRowIndexToView(int index) {
        return sortOrder == SortOrder.DESCENDING ? getModelRowCount() - index - 1 : index;
    }

    @Override
    public void setSortKeys(List<? extends SortKey> keys) {
        if ( keys == null || keys.size() == 0 ) {
            sortColumn = -1;
            sortOrder = SortOrder.UNSORTED;
        } else {
            sortColumn = keys.get(0).getColumn();
            sortOrder = keys.get(0).getSortOrder();
        }
    }

    @Override
    public List<? extends SortKey> getSortKeys() {
        List<SortKey> sortKeys = new ArrayList<SortKey>();

        if ( sortColumn != -1) {
            sortKeys.add(new SortKey(sortColumn, sortOrder));
        }

        return sortKeys;
    }

    @Override
    public int getViewRowCount() {
        return getModelRowCount();
    }

    @Override
    public int getModelRowCount() {
        return model.getRowCount();
    }

    @Override
    public void modelStructureChanged() {
    }

    @Override
    public void allRowsChanged() {
    }

    @Override
    public void rowsInserted(int firstRow, int endRow) {
    }

    @Override
    public void rowsDeleted(int firstRow, int endRow) {
    }

    @Override
    public void rowsUpdated(int firstRow, int endRow) {
    }

    @Override
    public void rowsUpdated(int firstRow, int endRow, int column) {
    }
}

