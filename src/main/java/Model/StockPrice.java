package Model;

import com.google.gson.annotations.SerializedName;

public class StockPrice {
    @SerializedName("c")
    private final float price;
    @SerializedName("d")
    private final float change;
    @SerializedName("dp")
    private final float percent_change;
    @SerializedName("l")
    private final float lowOfTheDay;
    @SerializedName("h")
    private final float highOfTheDay;
    @SerializedName("pc")
    private final float previousClosePrice;

    public StockPrice(float price, float change, float percent_change, float lowofTheDay, float highOfTheDay, float previousClosePrice) {
        this.price = price;
        this.change = change;
        this.percent_change = percent_change;
        this.lowOfTheDay = lowofTheDay;
        this.highOfTheDay = highOfTheDay;
        this.previousClosePrice = previousClosePrice;
    }

    public StockPrice() {
        this.price = 0;
        this.change = 0;
        this.percent_change = 0;
        this.lowOfTheDay = 0;
        this.highOfTheDay = 0;
        this.previousClosePrice = 0;
    }

    @Override
    public String toString() {
        return "StockPrice{" +
                "price=" + price +
                ", change=" + change +
                ", percent_change=" + percent_change +
                ", lowofTheDay=" + lowOfTheDay +
                ", highOfTheDay=" + highOfTheDay +
                ", previousClosePrice=" + previousClosePrice +
                '}';
    }

    public float getPrice() {
        return price;
    }

    public float getChange() {
        return change;
    }

    public float getPercent_change() {
        return percent_change;
    }

    public float getLowOfTheDay() {
        return lowOfTheDay;
    }

    public float getHighOfTheDay() {
        return highOfTheDay;
    }

    public float getPreviousClosePrice() {
        return previousClosePrice;
    }
}
