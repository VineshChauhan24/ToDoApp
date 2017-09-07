package com.example.taimoortahir.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin, btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        btnSignup = (Button) findViewById(R.id.signup_btn);
        btnLogin = (Button) findViewById(R.id.login_btn);

        btnLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);

        DBHelper db = new DBHelper(this);

        List<Task> list =  db.getAllTask();
        list.toString();


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btnLogin.getId())
        {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
        else if (v.getId() == btnSignup.getId()) {
            Intent intent = new Intent(this, Signup.class);
            startActivity(intent);
        }
    }
}
