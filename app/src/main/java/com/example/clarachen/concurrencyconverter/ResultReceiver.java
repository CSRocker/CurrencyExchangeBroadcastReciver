package com.example.clarachen.concurrencyconverter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by ClaraChen on 2/29/16.
 */
public class ResultReceiver extends BroadcastReceiver {

    public ResultReceiver(){

    }
    @Override
    public void onReceive(Context context, Intent intent){
        //"Request Broadcast
        Log.d("Debug --->", "Exchange Reply - on Recieve");

        String action = intent.getAction();
        Log.d("Debug --->", "Broadcast recieved: "+action);
        Log.d("Debug -->", "Action: "+intent.getAction());

        if(intent.getAction().equals(MainActivity.CONVERT_REPLY)){
            MainActivity.apply= true;
        }
        else if(intent.getAction().equals(MainActivity.CURRENCY_CONVERT)){
            //do nothing
        }
    }
}
