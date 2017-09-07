package com.example.taimoortahir.todoapp;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TaskInput extends AppCompatActivity implements View.OnClickListener, TaskAdapter.onBack {

    String data;
    TextView t_date, t_time, txt1, txt2, task;
    FloatingActionButton btn;
    Calendar dateTime = Calendar.getInstance();
    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    DBHelper db_helper;
    TaskAdapter tAdapter;
    RecyclerView recyclerView;
    ArrayList<Task> taskList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_input);

        getSupportActionBar().hide();

        task = (TextView) findViewById(R.id.task_txt);
        t_date = (TextView) findViewById(R.id.date_txt);
        t_time = (TextView) findViewById(R.id.time_txt);
        txt1 = (TextView) findViewById(R.id.date_edittext);
        txt2 = (TextView) findViewById(R.id.time_edittext);
        btn = (FloatingActionButton) findViewById(R.id.fab_b);

        t_date.setOnClickListener(this);
        t_time.setOnClickListener(this);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.date_txt: {
                updateDate();
                break;
            }
            case R.id.time_txt: {
                updateTime();
                break;
            }
            case R.id.fab_b: {
                db_helper = new DBHelper(this);
                Task t = new Task();
                data = task.getText().toString();
                t.setTask(data);
                t.setDate(txt1.getText().toString() + txt2.getText().toString());
                db_helper.addTask(t);

//                Intent alarmIntent = new Intent(this, BroadcastReciever.class);
//                pendingIntent = PendingIntent.getBroadcast(MyActivity.this, 0, alarmIntent, 0);

                taskList.addAll(db_helper.getAllTask());
                tAdapter = new TaskAdapter(taskList, TaskInput.this);
                recyclerView.setAdapter(tAdapter);
                tAdapter.notifyDataSetChanged();

                finish();

                break;
            }
            default: {
                break;
            }
        }
    }

    private void updateDate(){
        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH),
                dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateTime(){
        new TimePickerDialog(this, t, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE),
                true).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, month);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            txt1.setText(formatDateTime.format(dateTime.getTime()));
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

    public void start() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 8000;

//        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }
}
