package com.example.kentons.stocktilesapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by KentonS on 8/21/2015.
 */
public class StockInfo {
    private String stockTicker;
    private String price;
    private String priceChange;
    private String percentageChange;

    public String getStockTicker() {
        return stockTicker;
    }

    public void setStockTicker(String stockTicker) {
        this.stockTicker = stockTicker;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(String priceChange) {
        this.priceChange = priceChange;
    }

    public String getPercentageChange() {
        return percentageChange;
    }

    public void setPercentageChange(String percentageChange) {
        this.percentageChange = percentageChange;
    }

    /*
    public StockInfo(String the_stockTicker, String the_price, String the_priceChange, String the_percentageChange)
    {
        this.stockTicker = the_stockTicker;
        setStockTicker(the_stockTicker);

        this.price = the_price;
        setPrice(the_price);

        this.priceChange = the_priceChange;
        setPriceChange(the_priceChange);

        this.percentageChange = the_percentageChange;
        setPercentageChange(the_percentageChange);

    }
    */
    /*
    public StockInfo(Parcel source)
    {
        this.stockTicker = source.;
        setStockTicker(the_stockTicker);

        this.price = the_price;
        setPrice(the_price);

        this.priceChange = the_priceChange;
        setPriceChange(the_priceChange);

        this.percentageChange = the_percentageChange;
        setPercentageChange(the_percentageChange);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(stockTicker);
        dest.writeString(price);
        dest.writeString(priceChange);
        dest.writeString(percentageChange);

    }
    */
    /*
    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public StockInfo createFromParcel(Parcel in) {
            return new StockInfo(in);
        }

        public StockInfo[] newArray(int size) {
            return new StockInfo[size];
        }
    };
    */

}
