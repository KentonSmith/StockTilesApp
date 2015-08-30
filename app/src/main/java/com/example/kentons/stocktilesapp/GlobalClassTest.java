package com.example.kentons.stocktilesapp;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by KentonS on 8/22/2015.
 */
public class GlobalClassTest extends Application {
    public ArrayList<StockInfo> getStockList() {
        Log.v("GlobalClassTest", "Getter Method is being accessed for GlobalClassTest");
        return stockList;
    }

    public void setStockList(ArrayList<StockInfo> stockList) {

        Log.v("GlobalClassTest", "Setter Method is being accessed for GlobalClassTest");
        this.stockList = stockList;
    }

    private ArrayList<StockInfo> stockList;


}
