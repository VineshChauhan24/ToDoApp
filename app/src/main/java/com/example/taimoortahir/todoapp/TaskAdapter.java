package com.example.taimoortahir.todoapp;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Taimoor Tahir on 23-Jul-17.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    private List<Task> taskList;

    Context context;

    private onBack ob;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView task, date;
        public CardView cv;
        public Button delete_btn_adapter;

        public MyViewHolder(View view) {
            super(view);
            task = (TextView) view.findViewById(R.id.task_txt);
            date = (TextView) view.findViewById(R.id.desc_txt);
            cv = (CardView) view.findViewById(R.id.card_view);
            delete_btn_adapter = (Button) view.findViewById(R.id.item_delete_button);
        }
    }


    public TaskAdapter(List<Task> taskList, onBack ob, Context context) {
        this.taskList = taskList;
        this.ob = ob;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Task task = taskList.get(position);
        holder.task.setText(task.getTask());
        holder.date.setText(task.getDate());

        holder.cv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
//                Toast.makeText(context, "Long clicked!", Toast.LENGTH_SHORT).show();
//                holder.fab_b.setVisibility(View.INVISIBLE);
                ob.myclicklistener(task, position);
                return true;
            }
        });

        holder.task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ob.myclicklistener(task, position);
            }
        });

        holder.delete_btn_adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                taskList.remove(task);
//                notifyItemRemoved(position);
                ob.myDelete(task, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public interface onBack{

        public void myclicklistener(Task m, int position);
        public void myDelete(Task d, int position);
    }
}
