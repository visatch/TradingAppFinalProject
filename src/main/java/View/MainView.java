package View;

import Model.Stock;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.event.*;
import java.util.Locale;
import java.util.Vector;

import static Utility.ApplicationProperties.listOfDefaultStocks;

public class MainView extends JFrame {
    private JPanel rootPanel;
    private JTable tblData;
    private DefaultTableModel tblModel;
    private JButton btnAddStock;
    private JTextField txtnewStockSymbol;
    private JButton btnRemoveStock;

    public MainView() {
        setContentPane(rootPanel);
        setTitle("Trading Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
        btnAddStock.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
        btnRemoveStock.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
        initTable();

        btnAddStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtnewStockSymbol.getText().isEmpty()){
                    String newStockSymbol = txtnewStockSymbol.getText();
                    tblModel.addRow(new Stock(newStockSymbol.toUpperCase(Locale.ROOT)).getConvertToObject());
                    tblModel.fireTableDataChanged();
                }
            }
        });

        btnRemoveStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblData.getSelectedRow() > 0){
                    tblModel.removeRow(tblData.getSelectedRow());
                    tblModel.fireTableDataChanged();
                }
            }
        });
    }

    private void initTable(){
        String[] columnTable = {"Company","Current Price","Change","Percent Change","Low price of the day","High Price of the Day", "Previous Close Price","Number of Shares to Buy"};

        Vector<Stock> vecStocks = new Vector<>();
        for (String s : listOfDefaultStocks) {
            vecStocks.add(new Stock(s));
        }
        Object[] rowData = new Object[7];

        tblModel = new DefaultTableModel(null,columnTable){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7;
            }
        };

        JComboBox<Integer> comboBoxInJTable = new JComboBox<>();
        for (int i = 1; i < 11; i++) {
            comboBoxInJTable.addItem(i);
        }

        for (Stock stock : vecStocks) {
            rowData[0] = stock.getCompanyName();
            rowData[1] = "$ " + stock.getStockInfo().getPrice();
            rowData[2] = stock.getStockInfo().getChange();
            rowData[3] = stock.getStockInfo().getPercent_change();
            rowData[4] = "$ " + stock.getStockInfo().getLowOfTheDay();
            rowData[5] = "$ " + stock.getStockInfo().getHighOfTheDay();
            rowData[6] = "$ " + stock.getStockInfo().getPreviousClosePrice();
            tblModel.addRow(rowData);
        }
        tblData.setModel(tblModel);
        tblData.getColumnModel().getColumn(0).setPreferredWidth(150);

        TableColumn tblColumnCmb = tblData.getColumnModel().getColumn(7);
        tblColumnCmb.setCellEditor(new DefaultCellEditor(comboBoxInJTable));

        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tblColumnCmb.setCellRenderer(defaultTableCellRenderer);
    }

    public static void main(String[] args) {
        MainView mainView = new MainView();
    }
}
