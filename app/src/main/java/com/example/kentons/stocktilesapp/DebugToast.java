package com.example.kentons.stocktilesapp;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by KentonS on 8/2/2015.
 */
public class DebugToast {

    private Context context;

    public DebugToast(Context c)
    {
        this.context = c;
    }

    public void makeToast(String msg)
    {
        Toast.makeText(this.context, msg, Toast.LENGTH_SHORT).show();
    }

}
