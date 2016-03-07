package com.example.clarachen.concurrencyconverter;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.clarachen.concurrencyconverter.ResultReceiver;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    private EditText dollarAmount;
    private String edit_dollarAmt;
    private TextView convertResult;
    private ListView convertList;
    private String getCurrencyConvert;
    public static boolean apply = false;
    public float exchangeResult;
    public float dollarAmt;
    //private ExpandableListView Exp_convertList;
    //private List<String> convertListItem;


    public static final String CURRENCY_CONVERT="clara.chen.intent.convert";
    public static final String CONVERT_REPLY = "clara.chen.intent.reply";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup display and Buttons with their ID
        dollarAmount = (EditText)findViewById(R.id.dollarAmount);


      /*  Exp_convertList = (ExpandableListView) findViewById(R.id.convertList);
        convertListItem = new ArrayList<String>();
        convertListItem.add("British Pound");
        convertListItem.add("Euro");
        convertListItem.add("Indian Rupee");*/

        convertList=(ListView)findViewById(R.id.convertList);
        convertList.setAdapter(new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.convertListItems)));

        convertList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Log.d("Id", "position is "+position+ "id is"+ id);

                switch (position){
                    case 0:
                        getCurrencyConvert = "British Pound";
                        break;
                    case 1:

                        getCurrencyConvert = "Euro";
                        break;
                    case 2:
                        getCurrencyConvert = "Indian Rupee";
                        break;
                    default:
                        //show error message
                        break;
                }
                Log.d("The dollar amount is ", "converting to "+ getCurrencyConvert);

            }
        });

        Button btnConvert = (Button) findViewById(R.id.convert_btn);
        Button btnClose = (Button) findViewById(R.id.close_btn);

        //Setup textview of the currency conversion result
        convertResult = (TextView)findViewById(R.id.convertResult);



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

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onResume(){
        super.onResume();

        //Show the currency conversion
        exchangeRate();
        convertResult.setText("" + "Dollar Amount " + "'" + edit_dollarAmt + "' is converted to" + exchangeResult+ getCurrencyConvert);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }


    public void exchangeRate(){
        float value;
        if (getCurrencyConvert == "Indian rupees") {
            value = (float)68.47;
        } else if (getCurrencyConvert == "British Pound") {
            value = (float)0.72;
        } else {
            value = (float) 0.91;
        }
        Log.d("dollarAmt", "" +dollarAmt);
        exchangeResult = dollarAmt *value;
    }


    //function for Convert button
    public void convert(View view){
        // get text from textview
        edit_dollarAmt = dollarAmount.getText().toString();
        dollarAmt = Float.parseFloat(edit_dollarAmt);


        Log.d("Amount to Convert----->", "$" + dollarAmt + "to "+ getCurrencyConvert);


        Intent intent = new Intent(MainActivity.CURRENCY_CONVERT);
        intent.putExtra("Convert to",getCurrencyConvert); //String
        intent.putExtra("Dollar Amount",edit_dollarAmt); //int in String

        Log.d("Before sendBroadcast", ".....Sending");
        sendBroadcast(intent);
        Log.d("Broadcast", "Sent!");

    }



    /*finish the activity and close the app UI */
    public void finishMainActivity(View V){
        MainActivity.this.finish();
    }
}
