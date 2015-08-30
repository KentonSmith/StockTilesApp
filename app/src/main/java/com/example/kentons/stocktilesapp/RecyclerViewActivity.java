package com.example.kentons.stocktilesapp;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.brnunes.swipeablerecyclerview.SwipeableRecyclerViewTouchListener;

import java.util.ArrayList;


public class RecyclerViewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        GridLayoutManager glm = new GridLayoutManager(this, 2);
        glm.setOrientation(GridLayoutManager.VERTICAL);
        recList.setLayoutManager(glm);

        //getIntent().getExtras()
        //ArrayList<StockInfo> bob = (ArrayList<StockInfo>) getIntent().getSerializableExtra("stocks");

        final GlobalClassTest globalVariable = (GlobalClassTest) getApplicationContext();

        final ArrayList<StockInfo> bob = globalVariable.getStockList();

        if (bob == null) {
            Log.v("RecyclerViewActivity", "bob is null");
        } else {
            Log.v("RecyclerViewActivity", "the size of bob is = " + bob.size());
        }

        //                                  UNCOMMENT THIS WHEN WE ARE SURE BOB ISN'T NULL.
        for (int i = 0; i < bob.size(); i++) {
            StockInfo temp = bob.get(i);
            Log.v("RecyclerViewActivity", temp.getStockTicker());
        }


        final KentonsCardViewAdapter adapter = new KentonsCardViewAdapter(bob);
        recList.setAdapter(adapter);



        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(recList,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {
                            @Override
                            public boolean canSwipe(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    //Toast.makeText(RecyclerViewActivity.this, bob.get(position) + " swiped left", Toast.LENGTH_SHORT).show();

                                    //Delete in Database
                                    StockInfo temp = bob.get(position);
                                    String delete_this = temp.getStockTicker();
                                    SQLiteDatabase db;
                                    SQLiteOpenHelper stockDatabaseHelper = new StockSQLHelper(getApplicationContext());
                                    db = stockDatabaseHelper.getWritableDatabase();
                                    db.delete("STOCK_TABLE", "STOCK_TICKER = ?", new String[]{delete_this});
                                    //DOESN"T WORK Toast toast = Toast.makeText(getApplicationContext(), "Deleted \"" + delete_this+"\"from the database", Toast.LENGTH_SHORT);
                                    Toast.makeText(RecyclerViewActivity.this, "Deleted \"" + delete_this + "\"from the database", Toast.LENGTH_SHORT).show();


                                    bob.remove(position);
                                    adapter.notifyItemRemoved(position);
                                }
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    //Toast.makeText(RecyclerViewActivity.this, bob.get(position) + " swiped right", Toast.LENGTH_SHORT).show();

                                    //Delete in Database
                                    StockInfo temp = bob.get(position);
                                    String delete_this = temp.getStockTicker();
                                    SQLiteDatabase db;
                                    SQLiteOpenHelper stockDatabaseHelper = new StockSQLHelper(getApplicationContext());
                                    db = stockDatabaseHelper.getWritableDatabase();
                                    db.delete("STOCK_TABLE", "STOCK_TICKER = ?", new String[]{delete_this});
                                    //Toast toast = Toast.makeText(getApplicationContext(), "Deleted \"" + delete_this+"\"from the database", Toast.LENGTH_SHORT);
                                    Toast.makeText(RecyclerViewActivity.this, "Deleted \"" + delete_this + "\"from the database", Toast.LENGTH_SHORT).show();



                                    bob.remove(position);
                                    adapter.notifyItemRemoved(position);
                                }
                                adapter.notifyDataSetChanged();
                            }
                        });

        recList.addOnItemTouchListener(swipeTouchListener);
    }




        //KentonsCardViewAdapter adapter = new KentonsCardViewAdapter()



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recycler_view, menu);
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
}
