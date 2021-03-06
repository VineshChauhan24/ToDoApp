package com.example.taimoortahir.todoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.taimoortahir.todoapp.R.id.fab;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TaskAdapter.onBack, View.OnClickListener {

    ArrayList<Task> taskList = new ArrayList<>();
    DBHelper dbhelper;
    RecyclerView recyclerView;
    TaskAdapter tAdapter;

    TextView first, last, mail;

    String MyPreferences = "MyPrefs";
    SharedPreferences sharedPreferences;

    LinearLayout layout_linear;
    FloatingActionButton fab_bb;

    Button delete_btn, cancel_btn;

    Task task_temp;
    int position_temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Daily Tasks");
        getSupportActionBar().setTitle("Daily Tasks");

        dbhelper = new DBHelper(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        layout_linear = (LinearLayout) findViewById(R.id.layout_options);

        delete_btn = (Button) findViewById(R.id.delete_button);
        cancel_btn = (Button) findViewById(R.id.cancel_button);
        cancel_btn.setOnClickListener(this);
        delete_btn.setOnClickListener(this);

        fab_bb = (FloatingActionButton) findViewById(R.id.fab);
        fab_bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, InputTask.class);
                startActivity(intent);

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        first =(TextView) navigationView.findViewById(R.id.name_first);
        last =(TextView) navigationView.findViewById(R.id.name_last);
        mail =(TextView) navigationView.findViewById(R.id.email_textView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

//        sharedPreferences = this.getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
//        String temp = sharedPreferences.getString("FirstName", null);
//        first.setText(temp);
//        String last_temp = sharedPreferences.getString("LastName", null);
//        last.setText(last_temp);
//        String mail_temp = sharedPreferences.getString("Email", null);
//        mail.setText(mail_temp);

        prepareTaskData();
    }


    private void prepareTaskData() {

        taskList.addAll(dbhelper.getAllTask());

        tAdapter = new TaskAdapter(taskList, this,this);
        recyclerView.setAdapter(tAdapter);

        tAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        prepareTaskData();
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void myclicklistener(Task t, int position) {
        layout_linear.setVisibility(View.VISIBLE);
        fab_bb.setVisibility(View.INVISIBLE);
        task_temp = t;
        position_temp = position;
    }

    @Override
    public void myDelete(Task d, int position) {
        taskList.remove(d);
        dbhelper.deleteTasksTable(d);
        tAdapter.notifyItemRemoved(position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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

//            Intent intent = new Intent(this, Login.class);
//            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.cancel_button){
            layout_linear.setVisibility(View.INVISIBLE);
            fab_bb.setVisibility(View.VISIBLE);
        }
        else if(view.getId() == R.id.delete_button) {
            myDelete(task_temp, position_temp);
            layout_linear.setVisibility(View.INVISIBLE);
            fab_bb.setVisibility(View.VISIBLE);
        }
    }
}
