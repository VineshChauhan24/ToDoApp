package com.example.taimoortahir.todoapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Taimoor Tahir on 23-Jul-17.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    private List<Task> taskList;

    private onBack ob;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView task, date;

        public MyViewHolder(View view) {
            super(view);
            task = (TextView) view.findViewById(R.id.task_txt);
            date = (TextView) view.findViewById(R.id.desc_txt);
        }
    }


    public TaskAdapter(List<Task> taskList, onBack ob) {
        this.taskList = taskList;
        this.ob=ob;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Task task = taskList.get(position);
        holder.task.setText(task.getTask());
        holder.date.setText(task.getDate());

        holder.task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ob.myclicklistener(task);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public interface onBack{
        void myclicklistener( Task m);
    }
}
