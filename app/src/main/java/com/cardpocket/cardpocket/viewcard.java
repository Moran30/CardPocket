package com.cardpocket.cardpocket;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.client.android.encode.EncodeActivity;

import java.util.ArrayList;


public class viewcard extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcard);

        //Set variables
        String uriString;
        String uriString2;
        TextView username = (TextView) findViewById(R.id.tv_username);
        TextView cardname = (TextView) findViewById(R.id.tv_cardname);



        //get the cardname & username value & set the text boxes
        Intent i = getIntent();
        uriString = i.getStringExtra("cardname");
        uriString2 = i.getStringExtra("username");
         cardname.setText(uriString);
        username.setText(uriString2);

    }

public void onClick(View view){

    // QR code feature, will be coming soon
    if(view.getId()==R.id.bttn_qr){
        Toast.makeText(this, "Sorry,the QR feature isn't ready yet", Toast.LENGTH_LONG).show();
    }

    // Save the card with the new name
    if(view.getId() ==R.id.bttn_savename){

        EditText newcardname = (EditText) findViewById(R.id.tf_newcardname);
        TextView cardname = (TextView) findViewById(R.id.tv_cardname);

        String card = cardname.getText().toString();
        String newcard = newcardname.getText().toString();

        DBAdapter db = new DBAdapter(this);

        // Replace the old card name with the new one
        db.open();
        db.updatecard(card,newcard);
        Toast.makeText(this, "Card Updated", Toast.LENGTH_LONG).show();

        TextView userName = (TextView) findViewById(R.id.tv_username);
        String username = userName.getText().toString();

// Pass the username back to the mainactivity
Intent i = new Intent(this, mainactivity.class);
        i.putExtra("username", username);
        startActivity(i);

        // when the user presses back the app will end
        finish();
    }

    // Display the number in 128 format
    if (view.getId() == R.id.bttn_128) {


        TextView cardname = (TextView) findViewById(R.id.tv_cardname);


        // Match the username to one in the DB.
        DBAdapter db = new DBAdapter(this);

        db.open();
        Cursor c = db.viewCard(cardname.getText().toString());


        if (c.moveToFirst()) {
            do {
                // Get the cardnumber
                String data = c.getString(c.getColumnIndex("card_number"));

                // Encode the cardnumber to a CODE 128 barcode using ZXING!
                Intent intent = new Intent("com.google.zxing.client.android.ENCODE");

                intent.putExtra("ENCODE_FORMAT", "CODE_128");
                intent.putExtra("ENCODE_DATA", data);
                // Display the barcode
                startActivity(intent);


            } while (c.moveToNext());

        }
    }
            }



    }



