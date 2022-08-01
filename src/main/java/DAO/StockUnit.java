package DAO;

public class StockUnit {
    private String companyName;
    private String stockSymbol;
    private double stockPrice;
    private int numberOfStocks;
    private double totalPrice;

    public String getCompanyName() {
        return companyName;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
    }

    public int getNumberOfStocks() {
        return numberOfStocks;
    }

    public void setNumberOfStocks(int numberOfStocks) {
        this.numberOfStocks = numberOfStocks;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public StockUnit(String companyName, String stockSymbol, double stockPrice, int numberOfStocks, double totalPrice) {
        this.companyName = companyName;
        this.stockSymbol = stockSymbol;
        this.stockPrice = stockPrice;
        this.numberOfStocks = numberOfStocks;
        this.totalPrice = totalPrice;
    }

    public StockUnit() {
        this.companyName = null;
        this.stockSymbol = null;
        this.stockPrice = 0;
        this.numberOfStocks = 0;
        this.totalPrice = 0.0;
    }

    @Override
    public String toString() {
        return "StockUnit{" +
                "companyName='" + companyName + '\'' +
                ", stockSymbol='" + stockSymbol + '\'' +
                ", stockPrice=" + stockPrice +
                ", numberOfStocks=" + numberOfStocks +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public Object[] getConvertToObject(){
        return new Object[]{companyName
                , stockSymbol
                , stockPrice
                , numberOfStocks
                ,"$ " + totalPrice
        };
    }


}
