package com.cardpocket.cardpocket;

import android.app.Activity;
/*import android.support.v7.app.ActionBarActivity;*/
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
/*import android.view.Menu;
import android.view.MenuItem;*/




public class signup extends Activity{
    Button btn1,btn2;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        /* Called when the activity is first created*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btn1 = (Button)findViewById(R.id.bttn_signup);
        btn2 = (Button)findViewById(R.id.bttn_return);
    }

    public void onClick(View view){

       // on sign up click..
        if(view.getId() == R.id.bttn_signup) {

            EditText name = (EditText)findViewById(R.id.tf_email);
            EditText password = (EditText)findViewById(R.id.tf_password);
            EditText confirm = (EditText)findViewById(R.id.tf_confirm);
            String  email = name.getText().toString();
            String firstpassword = password.getText().toString();
            String secondpassword = confirm.getText().toString();

            DBAdapter db = new DBAdapter(this);

            db.open();
            // Check if user already exists ------------------
            Cursor c = db.checkUser(email);
            ArrayList<String> theUser = new ArrayList<>();

            if (c.moveToFirst()) {
                do {
                    // get the email address and put it in an array
                    String aUser = c.getString(c.getColumnIndex("email_address"));
                    theUser.add(aUser);


                } while (c.moveToNext());

            }  // If the array has the email entered then the username already exists
          if (theUser.contains(email)){
              Toast.makeText(this, "Email already exists", Toast.LENGTH_LONG).show();
          }

            else{

           // Check if the passwords entered match
            if (firstpassword.equals(secondpassword))
            {


            db.open();
                // Inserts the details
            long id = db.insertusers(name.getText().toString(), password.getText().toString());
            db.close();

            String username = name.getText().toString();
            {
            //set the value and pass to the mainactivity
                Intent i = new Intent(this, mainactivity.class);
                i.putExtra("username", username);
                startActivity(i);
                // when the user presses back the app will end
                finish();
            }


        }
        else {
                // Display..
                Toast.makeText(this, "The Passwords did not match", Toast.LENGTH_LONG).show();
            }

        }
         // Go back to the login page
        if(view.getId() == R.id.bttn_return) {
            startActivity(new Intent("com.cardpocket.cardpocket.login"));
        }
    }

        // Go back to the login page
        if(view.getId() == R.id.bttn_return) {
            startActivity(new Intent("com.cardpocket.cardpocket.login"));
        }
    }}

