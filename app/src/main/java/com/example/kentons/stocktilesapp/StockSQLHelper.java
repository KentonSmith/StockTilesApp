package com.example.kentons.stocktilesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by KentonS on 8/20/2015.
 */
public class StockSQLHelper  extends SQLiteOpenHelper{

    private static final String DB_NAME = "STOCK_DATABASE";
    private static final int DB_VERSION = 1;

    StockSQLHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + "STOCK_TABLE" + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "STOCK_TICKER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<String> listStocks(SQLiteDatabase db)
    {
        ArrayList<String> stock_tickers = new ArrayList<String>();
        Cursor  cursor = db.rawQuery("select * from STOCK_TABLE",null);

        if(cursor.moveToFirst())
        {
            while (!cursor.isAfterLast()) {
                stock_tickers.add(cursor.getString(1));
                //array[i] = cursor.getString(0);
                cursor.moveToNext();
            }
        }

        return stock_tickers;
    }


    public void insertStock(SQLiteDatabase db, String stock_ticker) {
        ContentValues stockValues = new ContentValues();
        stockValues.put("STOCK_TICKER", stock_ticker);
        db.insert("STOCK_TABLE", null, stockValues);
    }

    public void deleteStock(SQLiteDatabase db, String stock_ticker)
    {
        db.delete("STOCK_TABLE","STOCK_TICKER = ?", new String[] {stock_ticker});
    }

}


