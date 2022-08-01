package View;

import DAO.StockUnit;
import Model.Stock;
import Model.UserAccount;
import Services.StockService;
import Utility.UtilityClass;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.*;
import java.util.Locale;
import java.util.Vector;

import static Utility.ApplicationProperties.listOfDefaultStocks;
import static Utility.ApplicationProperties.verifyStockSymbol;

//TODO - validate the add new stock

public class MainView extends JFrame {
    private JPanel rootPanel;
    private JTable tblData;
    private DefaultTableModel tblModelTopTable, tblModelPortfolioTable;
    private JButton btnAddStock;
    private JTextField txtNewStockSymbol;
    private JButton btnRemoveStock;
    private JButton btnBuyStock;
    private JButton btnSellStock;
    private JTable tblPortfolio;
    private JLabel lblMoney;
    UserAccount userAccount;

    public MainView(String emailLoggedIn) {
        setContentPane(rootPanel);
        setTitle("Trading Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();

        userAccount = new UserAccount(emailLoggedIn);
        lblMoney.setText(String.valueOf(userAccount.getMoney()));

        setLocation(UtilityClass.getCenterXPosition(getSize()),UtilityClass.getCenterYPosition(getSize()));

        btnAddStock.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
        btnRemoveStock.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));

        tblData.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblPortfolio.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        initTable();

        Action addStockAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtNewStockSymbol.getText().isEmpty() && verifyStockSymbol(txtNewStockSymbol.getText())){
                    String newStockSymbol = txtNewStockSymbol.getText();
                    tblModelTopTable.addRow(new Stock(newStockSymbol.toUpperCase(Locale.ROOT)).getConvertToObject());
                    tblModelTopTable.fireTableDataChanged();
                }
                else{
                    JOptionPane.showMessageDialog(null,"You entered a wrong Stock Symbol!", "Error" ,JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        btnAddStock.addActionListener(addStockAction);
        txtNewStockSymbol.addActionListener(addStockAction);

        btnRemoveStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblData.getSelectedRow() > 0){
                    tblModelTopTable.removeRow(tblData.getSelectedRow());
                    tblModelTopTable.fireTableDataChanged();
                }
            }
        });

        btnBuyStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StockUnit stockUnit = StockService.buyStocks(tblData);

                if (stockUnit.getCompanyName() != null){
                    tblModelPortfolioTable.addRow(stockUnit.getConvertToObject());
                    double remainMoney = userAccount.getMoney() - stockUnit.getTotalPrice();
                    lblMoney.setText(String.valueOf(remainMoney));
                }
            }
        });

        btnSellStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void initTable() {
        String[] columnTable = {"Company", "Stock Symbol","Current Price", "Change", "Percent Change", "Low price of the day", "High Price of the Day", "Previous Close Price", "Number of Shares to Buy"};
        String[] columnPortfolioTable = {"Company", "Stock Symbol","Purchased Price","Number of Stocks Own","Total Price", "Number of Shares to Sell"};

        Vector<Stock> vecStocks = new Vector<>();
        for (String s : listOfDefaultStocks) {
            vecStocks.add(new Stock(s));
        }

        tblModelTopTable = new DefaultTableModel(null, columnTable) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 8;
            }
        };

        tblModelPortfolioTable = new DefaultTableModel(null,columnPortfolioTable) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5;
            }
        };

        JComboBox<Integer> comboBoxInJTable = new JComboBox<>();
        for (int i = 1; i < 11; i++) {
            comboBoxInJTable.addItem(i);
        }

        Object[] rowData = new Object[8];

        for (Stock stock : vecStocks) {
            rowData[0] = stock.getCompanyName();
            rowData[1] = stock.getStockSymbol();
            rowData[2] = "$ " + stock.getStockInfo().getPrice();
            rowData[3] = stock.getStockInfo().getChange();
            rowData[4] = stock.getStockInfo().getPercent_change();
            rowData[5] = "$ " + stock.getStockInfo().getLowOfTheDay();
            rowData[6] = "$ " + stock.getStockInfo().getHighOfTheDay();
            rowData[7] = "$ " + stock.getStockInfo().getPreviousClosePrice();
            tblModelTopTable.addRow(rowData);
        }

        tblData.setModel(tblModelTopTable);
        tblPortfolio.setModel(tblModelPortfolioTable);

        tblData.getColumnModel().getColumn(0).setPreferredWidth(150);
        tblPortfolio.getColumnModel().getColumn(0).setPreferredWidth(150);

        TableColumn tblColumnCmb = tblData.getColumnModel().getColumn(8);
        TableColumn tblColumnPortfolio = tblPortfolio.getColumnModel().getColumn(5);

        tblColumnCmb.setCellEditor(new DefaultCellEditor(comboBoxInJTable));
        tblColumnPortfolio.setCellEditor(new DefaultCellEditor(comboBoxInJTable));

        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tblColumnCmb.setCellRenderer(defaultTableCellRenderer);
    }
}
