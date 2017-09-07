package com.example.taimoortahir.todoapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button login_btn;
    TextView mail, pasword;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Log in");

        mail = (TextView) findViewById(R.id.email_text);
        pasword = (TextView) findViewById(R.id.password_text);

        login_btn = (Button) findViewById(R.id.log_btn);
        login_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        db = new DBHelper(Login.this);

        String email = mail.getText().toString();
        String password = pasword.getText().toString();

        if(email.equals("") || password.equals("")){
            Toast.makeText(this, "Don't leave vaccant feilds !", Toast.LENGTH_SHORT).show();
        }

        else {
            String pas = db.getregister(email);

            if(!password.equals(pas)){
                Toast.makeText(this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                pasword.setText("");
            }

            else if (password.equals(pas)) {

                final ProgressDialog progressDialog = new ProgressDialog(this, R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                progressDialog.dismiss();
                            }
                        }, 1000);

                Toast.makeText(this, "Login Successful !", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }

        }
    }

//    public void onLoginSuccess() {
//        login_btn.setEnabled(true);
//        finish();
//    }
//
//    public void onLoginFailed() {
//        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
//        login_btn.setEnabled(true);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
