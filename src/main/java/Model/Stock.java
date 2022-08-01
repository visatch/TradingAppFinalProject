package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.net.MalformedURLException;
import java.net.URL;
import static Utility.ApplicationProperties.*;

public class Stock {
    @SerializedName("name")
    private String companyName;
    private String stockSymbol;
    public StockPrice stockInfo;
    public String getStockSymbol() {
        return stockSymbol;
    }
    public String getCompanyName() {
        return companyName;
    }
    public StockPrice getStockInfo() {
        return stockInfo;
    }

    public Stock(String stockName) {
        this.stockSymbol = stockName;
        try {
            URL url = new URL(apiGetStockPrice(stockSymbol));
            HttpResponse<JsonNode> response = Unirest.get(url.toString()).asJson();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            stockInfo = gson.fromJson(response.getBody().toString(),StockPrice.class);

            url = new URL(apiGetCompanyName(stockSymbol));
            response = Unirest.get(url.toString()).asJson();

            this.companyName = response.getBody().getObject().getString("name");

        } catch (MalformedURLException | UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Stock{" +
                "companyName='" + companyName + '\'' +
                ", stockName='" + stockSymbol + '\'' +
                ", stockPrice=" + stockInfo.toString() +
                '}';
    }

    public Object[] getConvertToObject(){

        return new Object[]{companyName
                ,"$ " + stockInfo.getPrice()
                , stockInfo.getChange()
                , stockInfo.getPercent_change()
                ,"$ " + stockInfo.getLowOfTheDay()
                ,"$ " + stockInfo.getHighOfTheDay()
                ,"$ " + stockInfo.getPreviousClosePrice()
        };
    }


}

