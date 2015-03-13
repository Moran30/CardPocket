package com.cardpocket.cardpocket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



public class setting extends Activity {
long row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        String uriString;
        TextView username = (TextView) findViewById(R.id.tv_currentemail);

        //get the username value
        Intent i = getIntent();
        uriString = i.getStringExtra("username");
        username.setText(uriString);
    }


    public void onClick(View view){
   //Update the old email with the new one
        if (view.getId() == R.id.bttn_upemail) {

            TextView username = (TextView) findViewById(R.id.tv_currentemail);
            String email = username.getText().toString();

            TextView newusername = (TextView) findViewById(R.id.tf_new_email);
            String newemail = newusername.getText().toString();

            DBAdapter db = new DBAdapter(this);

            db.open();
            // Replace the old email with the new one ----
            db.updateEmail(email,newemail);
            Toast.makeText(this, "Email updated", Toast.LENGTH_LONG).show();

            //set the value and pass to the mainactivity
            Intent i = new Intent(this, mainactivity.class);
            i.putExtra("username", newemail);
            startActivity(i);

        }
          //Update the old password with the new one
        if (view.getId() == R.id.bttn_uppassword) {

            TextView username = (TextView) findViewById(R.id.tv_currentemail);
            String email = username.getText().toString();

            TextView newpass = (TextView) findViewById(R.id.tf_new_password);
            String newpassword = newpass.getText().toString();

            DBAdapter db = new DBAdapter(this);

            // Replace the old password with the new one
            db.open();
            db.updatePassword(email,newpassword);
            Toast.makeText(this, "Password updated", Toast.LENGTH_LONG).show();

            //set the value and pass to the mainactivity
            Intent i = new Intent(this, mainactivity.class);
            i.putExtra("username", email);
            startActivity(i);
            // when the user presses back the app will end
            finish();

        }

        //Go back to the main activity
 if (view.getId()==R.id.bttn_return){

     TextView username = (TextView) findViewById(R.id.tv_currentemail);
     String email = username.getText().toString();

     //set the value and pass to the mainactivity
     Intent i = new Intent(this, mainactivity.class);
     i.putExtra("username", email);
     startActivity(i);

     // when the user presses back the app will end
     finish();
 }
    //Go to the about page
        if (view.getId()==R.id.bttn_about){
            // Go to the about page.
            startActivity(new Intent("com.cardpocket.cardpocket.about"));
        }
    }
}
