package com.example.kentons.stocktilesapp;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by KentonS on 8/20/2015.
 */
public class JsoupAsyncTask extends AsyncTask<ArrayList<String>, Void, ArrayList<String[]>> {


    protected void onPreExecute()
    {

    }

    protected ArrayList<String[]> doInBackground(ArrayList<String>... stock_tickers)
    {
        ArrayList<String> stocks = stock_tickers[0];  //pass in only one parameter

        ArrayList<String[]> results = new ArrayList<String[]>();

        for(int i = 0; i < stocks.size(); i++)
        {
            String[] temp = new String[4];
            temp[0] = stocks.get(i);   //temp[0] is stock ticker.  temp[1] is current price   temp[2] is dollars up  temp[3] is percentage up

            String url = "http://finance.yahoo.com/q?s=" + temp[0];


            try {
                Document doc = Jsoup.connect(url).get();

                Element dollar_value = doc.getElementById("yfs_l84_" + temp[0].toLowerCase());
                temp[1] = dollar_value.text();

                Element dollar_change = doc.getElementById("yfs_c63_" + temp[0].toLowerCase());
                temp[2] = dollar_change.text();

                Element percentage_content = doc.getElementById("yfs_p43_" + temp[0].toLowerCase());
                temp[3] = percentage_content.text();

            } catch (IOException e) {
                Log.i("Kenton",e.toString());
                //e.printStackTrace();
            }

            results.add(temp);
        }


        return results;
    }

    protected void onProgressUpdate()
    {

    }

    protected void onPostExecute(ArrayList<String[]> results)
    {
        return;
    }


}
