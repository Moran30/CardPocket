package com.cardpocket.cardpocket;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class card extends Activity {
    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

       // Buttons equal the scan and save buttons.
        btn1 = (Button) findViewById(R.id.bttn_scan);
        btn2 = (Button) findViewById(R.id.bttn_save);


        {
        // get the username ----------
        EditText userName= (EditText)findViewById(R.id.tf_username);
        String uriString;

        //get the username from the previous activity and set it.
        Intent i = getIntent();
        uriString = i.getStringExtra("username");
        userName.setText(uriString);

        }

        //set the barcode font ---------
        TextView barcode = (TextView) findViewById(R.id.tv_barcode);
        //set the font of the text box to the Code 128 font
        Typeface BarcodeFontFace;
        //Located in the assets/Fonts folder
        BarcodeFontFace = Typeface.createFromAsset(getAssets(), "Fonts/IDAutomationSC128L.ttf");
        barcode.setTypeface(BarcodeFontFace);
    }


    public void onClick(View view) {
       // onClick button controls.


        if (view.getId() == R.id.bttn_save) {
            TextView barcode = (TextView)findViewById(R.id.tv_barcode);
            EditText cardname = (EditText)findViewById(R.id.tf_cardname);
            EditText usersname =(EditText)findViewById(R.id.tf_username);
            String c1 = "1";
            String c2 = "2";

            // Make sure that the user has scanned a barcode.
            if (barcode.getText().toString().equals("")){

                Toast.makeText(this, "Please scan a barcode before saving", Toast.LENGTH_LONG).show();
                c1 = "1";}
            else {

                c1 = "3";
            }

            // Make sure that they have given it a name.
            if (cardname.getText().toString().equals("")) {
                Toast.makeText(this, "Please name your card before saving", Toast.LENGTH_LONG).show();
                c2 = "2";
            } else {
                c2 = "3";
            }
            // Insert command ----------
            if (c1.equals(c2)){
            DBAdapter db = new DBAdapter(this);
            // Open the database and insert the fields.
            db.open();
            long id = db.insertCard(barcode.getText().toString(), cardname.getText().toString(), usersname.getText().toString());
            db.close();

            String username = usersname.getText().toString();

            {  //set the value and pass it to the mainactivity
                Intent i = new Intent(this, mainactivity.class);
                i.putExtra("username", username);
                startActivity(i);
                // when the user presses back the app will end
                finish();
            }


        }}

        if (view.getId() == R.id.bttn_scan) {
            // Start the scanner
            new IntentIntegrator(this).initiateScan();

        }


    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        //get the scanning results
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            String barcode;


            //get the result
            barcode = scanResult.getContents();

           //find the textboxs and set them (with the result).

            TextView  tv_barcode = (TextView) findViewById(R.id.tv_barcode);
            tv_barcode.setText(barcode);

            TextView  tv_barcodenum = (TextView) findViewById(R.id.tv_barcodenum);
            tv_barcodenum.setText(barcode);





        }


    }
}



