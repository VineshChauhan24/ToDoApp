package com.example.taimoortahir.todoapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity implements View.OnClickListener {

    TextView  fname, lname, email, password, cpassword;
    Button signup_btn;
    DBHelper db;
    String MyPreferences = "MyPrefs";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sign Up");

        sharedPreferences = this.getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);

        fname = (TextView) findViewById(R.id.f_name);
        lname = (TextView) findViewById(R.id.l_name);
        email = (TextView) findViewById(R.id.email_signup);
        password = (TextView) findViewById(R.id.pasword_signup);
        cpassword = (TextView) findViewById(R.id.c_pasword_signup);
        signup_btn = (Button) findViewById(R.id.signup_signup);

        signup_btn.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        String first = fname.getText().toString();
        String last = lname.getText().toString();
        String mail = email.getText().toString();
        String pasword = password.getText().toString();
        String c_pasword = cpassword.getText().toString();

        if (first.equals("") || last.equals("") || mail.equals("") || pasword.equals("") || c_pasword.equals("")) {
            Toast.makeText(this, "Don't leave vaccant feilds !", Toast.LENGTH_SHORT).show();
        }

        else if(!pasword.equals(c_pasword)){
            Toast.makeText(this, "Password doesnot match !", Toast.LENGTH_SHORT).show();
            password.setText("");
            cpassword.setText("");
        }

        else{
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("FirstName", first);
            editor.putString("LastName", last);
            editor.putString("Email", mail);
            editor.commit();

            db = new DBHelper(Signup.this);
            Task t = new Task();
            t.setFirstname(first);
            t.setLastname(last);
            t.setEmail(mail);
            t.setPassword(pasword);
            db.addTask(t);


            final ProgressDialog progressDialog = new ProgressDialog(this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating account...");
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 5000);

            Toast.makeText(this, "Registered !", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    //    public void onLoginSuccess() {
//        signup_btn.setEnabled(true);
//        finish();
//    }
//
//    public void onLoginFailed() {
//        Toast.makeText(getBaseContext(), "Account couldn't created..", Toast.LENGTH_LONG).show();
//        signup_btn.setEnabled(true);
//    }
}
