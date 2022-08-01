package Services;

import DAO.StockUnit;
import javax.swing.*;

public final class StockService {
    public static StockUnit buyStocks(JTable tblData) {
        int selectedRowIndex = tblData.getSelectedRow();

        if (tblData.getValueAt(selectedRowIndex,8) != null){
            String stockCompany = tblData.getValueAt(selectedRowIndex,0).toString();
            String stockSymbol = tblData.getValueAt(selectedRowIndex,1).toString();
            double stockPrice = Double.parseDouble(tblData.getValueAt(selectedRowIndex,2).toString().substring(2));
            int numberOfStocks = Integer.parseInt(tblData.getValueAt(selectedRowIndex,8).toString());
            return new StockUnit(stockCompany,stockSymbol,stockPrice,numberOfStocks,numberOfStocks * stockPrice);
        }

        return new StockUnit();
    }

    public static StockUnit sellStocks(JTable tblData) {
        int selectedRowIndex = tblData.getSelectedRow();

        if (tblData.getValueAt(selectedRowIndex,8) != null){
            String stockCompany = tblData.getValueAt(selectedRowIndex,0).toString();
            String stockSymbol = tblData.getValueAt(selectedRowIndex,1).toString();
            double stockPrice = Double.parseDouble(tblData.getValueAt(selectedRowIndex,2).toString().substring(2));
            int numberOfStocks = Integer.parseInt(tblData.getValueAt(selectedRowIndex,8).toString());
            return new StockUnit(stockCompany,stockSymbol,stockPrice,numberOfStocks,numberOfStocks * stockPrice);
        }



        return new StockUnit();
    }


}
