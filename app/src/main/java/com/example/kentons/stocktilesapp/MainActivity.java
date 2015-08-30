package com.example.kentons.stocktilesapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;


public class MainActivity extends Activity {


    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteOpenHelper stockDatabaseHelper = new StockSQLHelper(this);
        db = stockDatabaseHelper.getWritableDatabase();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onAddButtonClicked(View v)
    {
        EditText text = (EditText) findViewById(R.id.enter_ticker);
        String ticker = text.getText().toString().toUpperCase();
        Log.v("MainActivity.java", "Ticker being added = " + ticker);
        //Database add code goes here
        ContentValues stockValues = new ContentValues();
        stockValues.put("STOCK_TICKER", ticker);
        db.insert("STOCK_TABLE", null, stockValues);

        Toast toast = Toast.makeText(getApplicationContext(), "Added \""+ticker+"\"to the database", Toast.LENGTH_SHORT);
        toast.show();

        text.setText("");

        //Hide Keyboard
        //http://stackoverflow.com/questions/3400028/close-virtual-keyboard-on-button-press
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        //Update Current List
        onListButtonClicked(v);

    }

    public void onDeleteButtonClicked(View v)
    {
        EditText text = (EditText) findViewById(R.id.enter_ticker);
        String ticker = text.getText().toString().toUpperCase();
        Log.v("MainActivity.java", "Ticker being deleted = " + ticker);
        //Database delete code goes here
        db.delete("STOCK_TABLE", "STOCK_TICKER = ?", new String[]{ticker});

        Toast toast = Toast.makeText(getApplicationContext(), "Deleted \"" + ticker+"\"from the database", Toast.LENGTH_SHORT);
        toast.show();
        text.setText("");

        //Hide Keyboard
        //http://stackoverflow.com/questions/3400028/close-virtual-keyboard-on-button-press
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


        //Update Current List
        onListButtonClicked(v);

    }

    public void onListButtonClicked(View v)
    {
        ArrayList<String> stock_tickers = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select * from STOCK_TABLE", null);

        if(cursor.moveToFirst())
        {
            while (!cursor.isAfterLast()) {
                stock_tickers.add(cursor.getString(1));
                //array[i] = cursor.getString(0);
                cursor.moveToNext();
            }
        }

        String result_string = "";

        for(int i = 0; i < stock_tickers.size(); i++)
        {
            result_string += stock_tickers.get(i) + '\n';
        }

        TextView results = (TextView) findViewById(R.id.list_results);
        results.setText(result_string);

    }

    public void onComplexListButtonClicked(View v)
    {

        final ArrayList<String> stock_tickers = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select * from STOCK_TABLE", null);

        if(cursor.moveToFirst())
        {
            while (!cursor.isAfterLast()) {
                stock_tickers.add(cursor.getString(1));
                //array[i] = cursor.getString(0);
                cursor.moveToNext();
            }
        }

        //String[] results = new SimpleJsoupAsyncTask().doInBackground("GOOG");

        Log.v("MainActivity", "stock_tickers size = " + stock_tickers.size());

        int index = 0;
        final ArrayList<StockInfo> kenton_stocks = new ArrayList<StockInfo>();

        StockInfo[] kenton_stocks_array = new StockInfo[stock_tickers.size()];




        Thread downloadThread = new Thread() {
            public void run() {

                ArrayList<StockInfo> threadStockList = new ArrayList<StockInfo>();

                for(String s: stock_tickers)
                {
                    try {
                        Document doc = Jsoup.connect("http://finance.yahoo.com/q?s=" + s).get();

                        StockInfo temp = new StockInfo();

                        temp.setStockTicker(s);
                        temp.setPrice(doc.getElementById("priceLegend").text());
                        temp.setPriceChange(doc.getElementById("legendPriceChange").text());
                        temp.setPercentageChange(doc.getElementById("legendPctChange").text());


                        Log.v("MainActivity", temp.getStockTicker());
                        Log.v("MainActivity", temp.getPrice());
                        Log.v("MainActivity", temp.getPriceChange());
                        Log.v("MainActivity", temp.getPercentageChange());

                        threadStockList.add(temp);

                    } catch (Exception e) {
                        Log.e("MainActivity", e.toString());
                    }
                }

                final GlobalClassTest globalVariable = (GlobalClassTest) getApplicationContext();
                Log.v("MainActivity", "the size of threadStockList is = " + threadStockList.size());

                globalVariable.setStockList(threadStockList);


                ///THIS WAS PREVIOUSLY NOT IN THE THREAD AND BELOW IT BUT WAS NOT EXECUTING IN THE SAME ORDER
                Intent toGrid = new Intent(getBaseContext(), RecyclerViewActivity.class);
                startActivity(toGrid);

            }
        };
        downloadThread.start();

        //Intent toGrid = new Intent(this, RecyclerViewActivity.class);
        //startActivity(toGrid);

    }


    public void helloToast(View v)
    {
        DebugToast myToast = new DebugToast(this);
        myToast.makeToast("hello");
    }

}
