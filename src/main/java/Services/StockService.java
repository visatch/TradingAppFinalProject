package Services;

import DAO.StockDAO;
import Model.Stock;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.net.MalformedURLException;
import java.net.URL;

import static Utility.ApplicationProperties.stock_api_key;

public class StockService implements StockDAO {
//    @Override
//    public Stock getStockByCompanyStockName(Stock stock) {
//        Stock newStock = new Stock(stock.getStockName());
//        try {
//            String urlAsString = "https://finnhub.io/api/v1/quote?symbol=" + stock.getStockName() + "&token=" + stock_api_key;
//            URL url = new URL(urlAsString.toString());
//            HttpResponse<JsonNode> response = Unirest.get(url.toString())
//                    .asJson();
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            newStock = gson.fromJson(response.getBody().toString(),Stock.class);
//
//        } catch (MalformedURLException | UnirestException e) {
//            throw new RuntimeException(e);
//        }
//        return newStock;
//    }
//
//    @Override
//    public Stock getStockByCompanyName() {
//        return null;
//    }
}
