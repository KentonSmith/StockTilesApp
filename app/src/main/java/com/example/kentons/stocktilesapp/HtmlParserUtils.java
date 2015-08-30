package com.example.kentons.stocktilesapp;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by KentonS on 8/2/2015.
 */
public final class HtmlParserUtils {

    //fields
    private static String percentage;
    private static String dollars;

    //getters and setters
    public static String getDollars() {
        return dollars;
    }

    public static void setDollars(String dollars) {
        HtmlParserUtils.dollars = dollars;
    }

    public static String getPercentage() {
        return percentage;
    }

    public static void setPercentage(String percentage) {
        HtmlParserUtils.percentage = percentage;
    }


    public static ArrayList<String> stockSearch(String stock_ticker)
    {
        ArrayList<String> returnValues = new ArrayList<String>();

        String url = "http://finance.yahoo.com/q?s=" + stock_ticker;
        Log.i("Kenton","Url we are looking at: " + url);

        try {
            Document doc = Jsoup.connect(url).get();
            Element percentage_content = doc.getElementById("yfs_p43_" + stock_ticker);

            returnValues.add(percentage_content.text());

            Element dollar_content = doc.getElementById("yfs_c63_" + stock_ticker);

            returnValues.add(dollar_content.text());


        } catch (IOException e) {
            Log.i("Kenton",e.toString());
            //e.printStackTrace();
        }

        return returnValues;

    }





}
