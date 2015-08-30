package com.example.kentons.stocktilesapp;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.*;

import java.util.ArrayList;

/**
 * Created by KentonS on 8/21/2015.
 */
public class KentonsCardViewAdapter extends RecyclerView.Adapter<KentonsCardViewAdapter.KentonViewHolder> {

    private ArrayList<StockInfo> stockList;

    @Override
    public KentonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout, parent, false);
        return new KentonViewHolder(itemView);
    }

    public KentonsCardViewAdapter(ArrayList<StockInfo> si)
    {
        this.stockList = si;
    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }

    @Override
    public void onBindViewHolder(KentonViewHolder holder, int position) {
        final StockInfo si = stockList.get(position);
        holder.stockTicker.setText(si.getStockTicker());
        holder.stockPrice.setText(si.getPrice());
        holder.stockPriceChange.setText(si.getPriceChange());
        holder.stockPercentageChange.setText(si.getPercentageChange());

        //SET GREEN OR RED
        if(si.getPriceChange().contains("-"))
        {
            holder.reference.setCardBackgroundColor(Color.RED);
        }
        else
        {
            holder.reference.setCardBackgroundColor(Color.GREEN);

        }

        //Add Click Listener
        holder.reference.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://finance.yahoo.com/q?s=" + si.getStockTicker()));
                v.getContext().startActivity(browserIntent);
            }
        });

    }

    public static class KentonViewHolder extends RecyclerView.ViewHolder {
        protected TextView stockTicker;
        protected TextView stockPrice;
        protected TextView stockPriceChange;
        protected TextView stockPercentageChange;
        protected CardView reference;

        public KentonViewHolder(View v) {
            super(v);
            stockTicker =  (TextView) v.findViewById(R.id.stock_ticker_label);
            stockPrice = (TextView)  v.findViewById(R.id.stock_ticker_price);
            stockPriceChange = (TextView)  v.findViewById(R.id.stock_ticker_price_change);
            stockPercentageChange = (TextView) v.findViewById(R.id.stock_ticker_percentage_change);
            reference = (CardView) v.findViewById(R.id.card_view);

        }
    }



}
