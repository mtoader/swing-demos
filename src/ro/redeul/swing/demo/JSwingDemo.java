package ro.redeul.swing.demo;

import ro.redeul.swing.demo.table.BigTableModel;
import ro.redeul.swing.demo.table.BigTableRowSorter;
import ro.redeul.swing.demo.table.renderers.BooleanDataRenderer;
import ro.redeul.swing.demo.table.renderers.TextDataRenderer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Author: Toader Mihai Claudiu <mtoader@gmail.com>
 * <p/>
 * Date: 7/22/11
 * Time: 1:17 PM
 */
public class JSwingDemo extends JFrame {

//    private JTextField searchField;
    private JTable dataTable;
    private JSlider itemCount;

    private JLabel labelTitle = new JLabel();

    private BigTableModel dataModel;

    public JSwingDemo() throws HeadlessException {
        super("Simple JSwing demo");

        initComponents();
        initListeners();
    }

    private void initListeners() {

        dataModel = new BigTableModel();
        dataTable.setModel(dataModel);

        itemCount.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                dataModel.resizeModel(itemCount.getValue());
            }
        });

        dataModel.addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                adjustItemCountDisplay(dataModel.getRowCount());
            }
        });

        itemCount.setValue(100);

        dataTable.setRowSorter(new BigTableRowSorter(dataModel, 0, 1, 2));
        dataTable.getColumnModel().getColumn(3).setCellRenderer(new BooleanDataRenderer());
        dataTable.getColumnModel().getColumn(5).setCellRenderer(new TextDataRenderer());
    }

    private void adjustItemCountDisplay(int rowCount) {
        labelTitle.setText(String.format("Items table (%,d items)", rowCount));
    }

    protected void initComponents() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());

        final JLabel labelItemsCount = new JLabel();
        labelItemsCount.setFont(new Font(labelItemsCount.getFont().getName(), Font.BOLD, labelItemsCount.getFont().getSize()));
        labelItemsCount.setText("Items count:");


        labelTitle = new JLabel();
        labelTitle.setFont(new Font(labelTitle.getFont().getName(), Font.BOLD, labelTitle.getFont().getSize()));
        labelTitle.setText("Items:");

        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        contentPanel.add(labelItemsCount, gbc);

        itemCount = new JSlider();
        itemCount.setInverted(false);
        itemCount.setMajorTickSpacing(10000000);
        itemCount.setMaximum(100000000);
        itemCount.setMinimum(100);
        itemCount.setMinorTickSpacing(1000000);
        itemCount.setPaintLabels(false);
        itemCount.setPaintTicks(true);
        itemCount.setPaintTrack(true);
        itemCount.setSnapToTicks(false);
        itemCount.setValue(100000);
        itemCount.setValueIsAdjusting(true);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        contentPanel.add(itemCount, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        contentPanel.add(labelTitle, gbc);

        final JPanel tableHolderPanel = new JPanel();
        tableHolderPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 5;
        gbc.ipady = 5;
        gbc.insets = new Insets(5, 5, 5, 5);
        contentPanel.add(tableHolderPanel, gbc);

        final JScrollPane scrollPane = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        tableHolderPanel.add(scrollPane, gbc);

        dataTable = new JTable();
        scrollPane.setViewportView(dataTable);

        setContentPane(contentPanel);
    }

    public void run() {
        pack();
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
