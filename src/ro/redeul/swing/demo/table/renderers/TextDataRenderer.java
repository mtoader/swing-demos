package ro.redeul.swing.demo.table.renderers;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Author: Toader Mihai Claudiu <mtoader@gmail.com>
 * <p/>
 * Date: 7/22/11
 * Time: 3:26 PM
 */
public class TextDataRenderer extends JLabel implements TableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value.toString());
        setFont(getFont().deriveFont(Font.ITALIC));

        return this;
    }
}
