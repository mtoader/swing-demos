package ro.redeul.swing.demo.table.renderers;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Author: Toader Mihai Claudiu <mtoader@gmail.com>
 * <p/>
 * Date: 7/22/11
 * Time: 3:04 PM
 */
public class BooleanDataRenderer extends JCheckBox implements TableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        if ( value instanceof Boolean ) {
            setSelected(Boolean.class.cast(value));
        }

        return this;
    }

}
