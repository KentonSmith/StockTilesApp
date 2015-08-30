//package com.example.kentons.stocktilesapp;
//
//import android.os.AsyncTask;
//import android.util.Log;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//
///**
// * Created by KentonS on 8/20/2015.
// */
//
//
//private class SimpleJsoupAsyncTask extends AsyncTask<String, Void, String[]> {
//
//
//    protected void onPreExecute()
//    {
//
//    }
//
//    protected String[] doInBackground(String... stock_tickers)
//    {
//
//        Log.v("SimpleJsoupAsyncTask", "At Beginning of doInBackground function");
//        //only pass in a single string at a time
//        String stock = stock_tickers[0];  //pass in only one parameter
//
//        String[] temp = new String[4];
//        temp[0] = stock;   //temp[0] is stock ticker.  temp[1] is current price   temp[2] is dollars up  temp[3] is percentage up
//
//        String url = "http://finance.yahoo.com/q?s=" + temp[0];
//        Log.v("SimpleJsoupAsyncTask", url);
//        Log.v("SimpleJsoupAsyncTask", "Right Before try/catch block with jsoup code");
//        try {
//            Document doc = Jsoup.connect(url).get();
//
//            /*
//            Log.v("SimpleJsoupAsyncTask", "After Jsoup.connect(url)");
//
//            Element dollar_value = doc.getElementById("yfs_l84_" + temp[0].toLowerCase());
//            temp[1] = dollar_value.text();
//
//            Element dollar_change = doc.getElementById("yfs_c63_" + temp[0].toLowerCase());
//            temp[2] = dollar_change.text();
//
//            Element percentage_content = doc.getElementById("yfs_p43_" + temp[0].toLowerCase());
//            temp[3] = percentage_content.text();
//            */
//
//        } catch (Exception e) {
//            Log.e("SimpleJsoupAsyncTask", e.toString());
//            //e.printStackTrace();
//        }
//
//        return temp;
//    }
//
//    protected void onProgressUpdate()
//    {
//
//    }
//
//    protected void onPostExecute(String[] results)
//    {
//
//    }
//
//
//}
//
