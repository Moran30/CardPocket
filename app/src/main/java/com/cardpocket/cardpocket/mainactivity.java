package com.cardpocket.cardpocket;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;


import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;



public class mainactivity extends Activity {
    Button btn1, btn2, btn3,btn4,btn5,btn6;
    Button c1, c2, c3, c4, c5, c6;
    String selcard = "";
    String selectedcard;
    long deletecard;
    Integer confirm = 0;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        /* Called when the activity is first created*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainactivity);
        btn1 = (Button) findViewById(R.id.bttn_show);
        btn2 = (Button) findViewById(R.id.bttn_scan);
        btn3 = (Button) findViewById(R.id.bttn_setting);
        btn4 = (Button) findViewById(R.id.bttn_add);
        btn5 = (Button) findViewById(R.id.bttn_view);
        btn6 = (Button) findViewById(R.id.bttn_remove);

        c1 = (Button) findViewById(R.id.bttn_card1);
        c2 = (Button) findViewById(R.id.bttn_card2);
        c3= (Button) findViewById(R.id.bttn_card3);
        c4 = (Button) findViewById(R.id.bttn_card4);
        c5 = (Button) findViewById(R.id.bttn_card5);
        c6 = (Button) findViewById(R.id.bttn_card6);
        btn5.setVisibility(View.INVISIBLE);
        btn6.setVisibility(View.INVISIBLE);


        String uriString;
        EditText username = (EditText) findViewById(R.id.tf_username);

        //get the username value
        Intent i = getIntent();
        uriString = i.getStringExtra("username");
        username.setText(uriString);

    }



    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void onClick(View view) {
/* Once the selected button has been clicked, run the intent */

        if (view.getId() == R.id.bttn_setting)
        {
            EditText username = (EditText) findViewById(R.id.tf_username);
            String userName = username.getText().toString();

           // Pass the username value
            Intent i = new Intent(this, setting.class);
            i.putExtra("username", userName);
            startActivity(i);}

        if (view.getId() == R.id.bttn_add) {

            EditText username = (EditText) findViewById(R.id.tf_username);
            String userName = username.getText().toString();

           // Pass the username value
            Intent i = new Intent(this, card.class);
            i.putExtra("username", userName);
            startActivity(i);

        }

        if (view.getId() ==R.id.bttn_view){

  // Check which card has been selected and then view (Pass the cardname to the viewcard activity).
            if (selcard.equals("c1")){
                EditText username = (EditText) findViewById(R.id.tf_username);
                String userName = username.getText().toString();
            Intent i = new Intent(this, viewcard.class);
            i.putExtra("cardname", c1.getText().toString());
                i.putExtra("username", userName);
            startActivity(i);}

            if (selcard.equals("c2")){
                EditText username = (EditText) findViewById(R.id.tf_username);
                String userName = username.getText().toString();
                Intent i = new Intent(this, viewcard.class);
                i.putExtra("cardname", c2.getText().toString());
                i.putExtra("username", userName);
                startActivity(i);}

            if (selcard.equals("c3")){
                EditText username = (EditText) findViewById(R.id.tf_username);
                String userName = username.getText().toString();
                Intent i = new Intent(this, viewcard.class);
                i.putExtra("cardname", c3.getText().toString());
                i.putExtra("username", userName);
                startActivity(i);}

            if (selcard.equals("c4")){
                EditText username = (EditText) findViewById(R.id.tf_username);
                String userName = username.getText().toString();
                Intent i = new Intent(this, viewcard.class);
                i.putExtra("cardname", c4.getText().toString());
                i.putExtra("username", userName);
                startActivity(i);}

            if (selcard.equals("c5")){
                EditText username = (EditText) findViewById(R.id.tf_username);
                String userName = username.getText().toString();
                Intent i = new Intent(this, viewcard.class);
                i.putExtra("cardname", c5.getText().toString());
                i.putExtra("username", userName);
                startActivity(i);}

            if (selcard.equals("c6")){
                EditText username = (EditText) findViewById(R.id.tf_username);
                String userName = username.getText().toString();
                Intent i = new Intent(this, viewcard.class);
                i.putExtra("cardname", c6.getText().toString());
                i.putExtra("username", userName);
                startActivity(i);}

        }



// When a card has been selected display the view and delete buttons. ---------
        if (view.getId() ==R.id.bttn_card1){

            if (c1.getText().length() ==0);
                //do nothing
            else {
               // Selected cards text changes to blue, to indicate selection
               c1.setTextColor(Color.parseColor("#096BC1"));
               c2.setTextColor(Color.parseColor("#ff4f4f4f"));
               c3.setTextColor(Color.parseColor("#ff4f4f4f"));
               c4.setTextColor(Color.parseColor("#ff4f4f4f"));
               c5.setTextColor(Color.parseColor("#ff4f4f4f"));
               c6.setTextColor(Color.parseColor("#ff4f4f4f"));

                btn5.setVisibility(View.VISIBLE);
                btn6.setVisibility(View.VISIBLE);
                selcard = "c1";

            }

        }

        if (view.getId() ==R.id.bttn_card2){
            if (c2.getText().length() ==0);
                //do nothing
            else {
                // Selected cards text changes to blue, to indicate selection
                c1.setTextColor(Color.parseColor("#ff4f4f4f"));
                c2.setTextColor(Color.parseColor("#096BC1"));
                c3.setTextColor(Color.parseColor("#ff4f4f4f"));
                c4.setTextColor(Color.parseColor("#ff4f4f4f"));
                c5.setTextColor(Color.parseColor("#ff4f4f4f"));
                c6.setTextColor(Color.parseColor("#ff4f4f4f"));

                btn5.setVisibility(View.VISIBLE);
                btn6.setVisibility(View.VISIBLE);
                selcard = "c2";

            }
        }

        if (view.getId() ==R.id.bttn_card3){
            if (c3.getText().length() ==0);
                //do nothing
            else {
                // Selected cards text changes to blue, to indicate selection
                c1.setTextColor(Color.parseColor("#ff4f4f4f"));
                c2.setTextColor(Color.parseColor("#ff4f4f4f"));
                c3.setTextColor(Color.parseColor("#096BC1"));
                c4.setTextColor(Color.parseColor("#ff4f4f4f"));
                c5.setTextColor(Color.parseColor("#ff4f4f4f"));
                c6.setTextColor(Color.parseColor("#ff4f4f4f"));

                btn5.setVisibility(View.VISIBLE);
                btn6.setVisibility(View.VISIBLE);
                selcard = "c3";
            }
        }

        if (view.getId() ==R.id.bttn_card4){
            if (c4.getText().length() ==0);
                //do nothing
            else {
                // Selected cards text changes to blue, to indicate selection
                c1.setTextColor(Color.parseColor("#ff4f4f4f"));
                c2.setTextColor(Color.parseColor("#ff4f4f4f"));
                c3.setTextColor(Color.parseColor("#ff4f4f4f"));
                c4.setTextColor(Color.parseColor("#096BC1"));
                c5.setTextColor(Color.parseColor("#ff4f4f4f"));
                c6.setTextColor(Color.parseColor("#ff4f4f4f"));

                btn5.setVisibility(View.VISIBLE);
                btn6.setVisibility(View.VISIBLE);
                selcard = "c4";
            }
        }

        if (view.getId() ==R.id.bttn_card5){
            if (c5.getText().length() ==0);
                //do nothing
            else {
                // Selected cards text changes to blue, to indicate selection
                c1.setTextColor(Color.parseColor("#ff4f4f4f"));
                c2.setTextColor(Color.parseColor("#ff4f4f4f"));
                c3.setTextColor(Color.parseColor("#ff4f4f4f"));
                c4.setTextColor(Color.parseColor("#ff4f4f4f"));
                c5.setTextColor(Color.parseColor("#096BC1"));
                c6.setTextColor(Color.parseColor("#ff4f4f4f"));

                btn5.setVisibility(View.VISIBLE);
                btn6.setVisibility(View.VISIBLE);
                selcard = "c5";}
        }

        if (view.getId() ==R.id.bttn_card6){
            if (c6.getText().length() ==0);
                //do nothing
            else {
                // Selected cards text changes to blue, to indicate selection
                c1.setTextColor(Color.parseColor("#ff4f4f4f"));
                c2.setTextColor(Color.parseColor("#ff4f4f4f"));
                c3.setTextColor(Color.parseColor("#ff4f4f4f"));
                c4.setTextColor(Color.parseColor("#ff4f4f4f"));
                c5.setTextColor(Color.parseColor("#ff4f4f4f"));
                c6.setTextColor(Color.parseColor("#096BC1"));

                btn5.setVisibility(View.VISIBLE);
                btn6.setVisibility(View.VISIBLE);
                selcard = "c6";
            }
        }





        if (view.getId() == R.id.bttn_show) {
            EditText username = (EditText) findViewById(R.id.tf_username);

            Button btn1 = (Button)findViewById(R.id.bttn_card1);
            Button btn2 = (Button)findViewById(R.id.bttn_card2);
            Button btn3 = (Button)findViewById(R.id.bttn_card3);
            Button btn4 = (Button)findViewById(R.id.bttn_card4);
            Button btn5 = (Button)findViewById(R.id.bttn_card5);
            Button btn6 = (Button)findViewById(R.id.bttn_card6);


            String card1 = null;
            String card2 = null;
            String card3 = null;
            String card4 = null;
            String card5 = null;
            String card6 = null;

            DBAdapter db = new DBAdapter(this);
            //Open the db and get the users cards
            db.open();
            Cursor c = db.getCards(username.getText().toString());

            ArrayList<String> usersCards = new ArrayList<>();

            if (c.moveToFirst()) {
                do {
                    // Add the result to the arraylist
                    String aCard = c.getString(c.getColumnIndex("card_name"));
                    usersCards.add(aCard);


                } while (c.moveToNext());

            }

           // User has no cards
            if (usersCards.size() ==0)
               //do nothing
                Toast.makeText(this, "Please Add a card", Toast.LENGTH_LONG).show();

           // If the array is more than 0, then make the stored cards visible ------
            if (usersCards.size() ==1) {

               card1 = usersCards.get(0);
                btn1.setText(card1);
                btn1.setVisibility(View.VISIBLE);}

               if (usersCards.size() == 2)

               {
                   card1 = usersCards.get(0);
                   btn1.setText(card1);
                   btn1.setVisibility(View.VISIBLE);
                   card2 = usersCards.get(1);
                   btn2.setText(card2);
                   btn2.setVisibility(View.VISIBLE);
               }

                if (usersCards.size() == 3)
                {  card1 = usersCards.get(0);
                      btn1.setText(card1);
                    btn1.setVisibility(View.VISIBLE);
                  card2 = usersCards.get(1);
                  btn2.setText(card2);
                    btn2.setVisibility(View.VISIBLE);
                  card3 = usersCards.get(2);
                  btn3.setText(card3);
                    btn3.setVisibility(View.VISIBLE);
                }

                if (usersCards.size() == 4){
                    card1 = usersCards.get(0);
            btn1.setText(card1);
                    btn1.setVisibility(View.VISIBLE);
            card2 = usersCards.get(1);
            btn2.setText(card2);
                    btn2.setVisibility(View.VISIBLE);
            card3 = usersCards.get(2);
            btn3.setText(card3);
                    btn3.setVisibility(View.VISIBLE);
                card4 = usersCards.get(3);
                 btn4.setText(card4);
                    btn4.setVisibility(View.VISIBLE);
                }

                if (usersCards.size() == 5) {
                    card1 = usersCards.get(0);
            btn1.setText(card1);
                    btn1.setVisibility(View.VISIBLE);
            card2 = usersCards.get(1);
            btn2.setText(card2);
                    btn2.setVisibility(View.VISIBLE);
            card3 = usersCards.get(2);
            btn3.setText(card3);
                    btn3.setVisibility(View.VISIBLE);
            card4 = usersCards.get(3);
            btn4.setText(card4);
                    btn4.setVisibility(View.VISIBLE);
                   card5 = usersCards.get(4);
                  btn5.setText(card5);
                    btn5.setVisibility(View.VISIBLE);}

                if (usersCards.size() == 6) {
                    card1 = usersCards.get(0);
            btn1.setText(card1);
                    btn1.setVisibility(View.VISIBLE);
            card2 = usersCards.get(1);
            btn2.setText(card2);
                    btn2.setVisibility(View.VISIBLE);
            card3 = usersCards.get(2);
            btn3.setText(card3);
                    btn3.setVisibility(View.VISIBLE);
            card4 = usersCards.get(3);
            btn4.setText(card4);
                    btn4.setVisibility(View.VISIBLE);
            card5 = usersCards.get(4);
            btn5.setText(card5);
                    btn5.setVisibility(View.VISIBLE);
                   card6 = usersCards.get(5);
                  btn6.setText(card6);
                    btn6.setVisibility(View.VISIBLE);
                }
        }

        if (view.getId() ==R.id.bttn_remove) {

           // the user has clicked the delete button twice, delete the card
            if (confirm.equals(1)){
                // Remove the selected card ---------------------
                if (selcard.equals("c1"))
                    selectedcard = c1.getText().toString();

            if (selcard.equals("c2"))
                selectedcard = c2.getText().toString();


            if (selcard.equals("c3"))
                selectedcard = c3.getText().toString();


            if (selcard.equals("c3"))
                selectedcard = c3.getText().toString();


            if (selcard.equals("c4"))
                selectedcard = c4.getText().toString();


            if (selcard.equals("c5"))
                selectedcard = c5.getText().toString();


            if (selcard.equals("c6"))
                selectedcard = c6.getText().toString();


            DBAdapter db = new DBAdapter(this);
            db.open();
            Cursor c = db.viewCard(selectedcard);


            if (c.moveToFirst()) {
                // Create an array list
                ArrayList<String> cardID = new ArrayList<>();
                // get the cards row id ----
                String card = c.getString(c.getColumnIndex("_id"));
                cardID.add(card);

                // deletecard equals the cards row id in the array
                deletecard = Long.parseLong(cardID.get(0));

                // delete the selected cards row
                db.deleteCard(deletecard);

                // Display...
                Toast.makeText(this, "Card deleted", Toast.LENGTH_LONG).show();
                EditText username = (EditText) findViewById(R.id.tf_username);

                // Start the mainactivity again (Refresh)
                String userName = username.getText().toString();
                Intent i = new Intent(this, mainactivity.class);
                i.putExtra("username", userName);
                startActivity(i);
                // when the user presses back the app will end
                finish();

            }

        }    // When the user first clicks the delete button, display a confirmation message and make count 1
            if (confirm.equals(0)) {
                Toast.makeText(this, "To confirm deletion, please click the 'Delete card' button again", Toast.LENGTH_LONG).show();
                confirm = 1;}

        }


    }


        }


