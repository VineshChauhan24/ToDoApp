package com.example.taimoortahir.todoapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class InputTask extends AppCompatActivity implements View.OnClickListener, TaskAdapter.onBack {

    String data;
    TextView t_date, t_time, txt1, txt2, task;
    FloatingActionButton btn;
    Calendar dateTime = Calendar.getInstance();
    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    DBHelper db_helper;
    TaskAdapter tAdapter;
    RecyclerView recyclerView;
    ArrayList<Task> taskList = new ArrayList<>();
    RelativeLayout r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_clear_white_24dp);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        task = (TextView) findViewById(R.id.task_txt);
        t_date = (TextView) findViewById(R.id.date_txt);
        t_time = (TextView) findViewById(R.id.time_txt);
        txt1 = (TextView) findViewById(R.id.date_edittext);
        txt2 = (TextView) findViewById(R.id.time_edittext);
        btn = (FloatingActionButton) findViewById(R.id.fab_b);
        r = (RelativeLayout) findViewById(R.id.fragmentB);

        t_date.setOnClickListener(this);
        t_time.setOnClickListener(this);
        btn.setOnClickListener(this);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.task_input_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_done) {
            if (task.getEditableText().toString().trim().length() == 0) {
                Toast.makeText(this, "Enter the Task first!", Toast.LENGTH_SHORT).show();
            } else {
                if (r != null) {
                    r.setVisibility(View.VISIBLE);
                }
            }
            return true;
        } else if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.date_txt) {
                updateDate();
            }
        else if (view.getId() == R.id.time_txt){
                updateTime();
            }
        else if (view.getId() == R.id.fab_b){
            if (txt1.getText().toString().trim().length() == 0 || txt2.getText().toString().trim().length() == 0){
                Toast.makeText(this, "Select both Date & Time!", Toast.LENGTH_SHORT).show();
            }
            else{
//                validateFields();
                db_helper = new DBHelper(this);
                Task t = new Task();
                data = task.getText().toString();
                t.setTask(data);
                t.setDate(txt1.getText().toString() + txt2.getText().toString());
                db_helper.addTask(t);

//                Intent alarmIntent = new Intent(this, BroadcastReciever.class);
//                pendingIntent = PendingIntent.getBroadcast(MyActivity.this, 0, alarmIntent, 0);

                taskList.addAll(db_helper.getAllTask());
                tAdapter = new TaskAdapter(taskList, InputTask.this, this);
                recyclerView.setAdapter(tAdapter);
                tAdapter.notifyDataSetChanged();

                finish();

            }
        }
    }

    private void validateFields(){
        if (txt1.getText().toString() == "" || txt2.getText().toString() == ""){
            Toast.makeText(this, "Select both Date & Time!", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateDate() {
        new DatePickerDialog(InputTask.this, d, dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH),
                dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateTime() {
        new TimePickerDialog(InputTask.this, t, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE),
                true).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, month);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                txt1.setText(formatDateTime.format(dateTime.getTime()));
            }
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);
            txt2.setText(formatDateTime.format(dateTime.getTime()));
        }
    };

    @Override
    public void myclicklistener(Task m) {

    }

    @Override
    public void myDelete(Task d, int position) {

    }
}
