package com.example.taimoortahir.todoapp;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TaimoorTahir on 15/09/2017.
 */

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<DayModel> dayList;
    Dialog dayDialog;
    TextView dayText;
    DayModel dayObj;
    private onBack ob;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            //image.setOnClickListener(this);
        }
    }

    public DayAdapter(Context myContext, ArrayList<DayModel> mydayList){
        this.mContext = myContext;
        this.dayList = mydayList;
//        dayDialog = dialog;
//        dayText = day;
    }

    public DayAdapter(Context myContext, ArrayList<DayModel> mydayList, TextView day, onBack ob){
        this.mContext = myContext;
        this.dayList = mydayList;
//        dayDialog = dialog;
        dayText = day;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.day_dialog_item, parent, false);
        final MyViewHolder holder =  new MyViewHolder(itemView);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dayText.setText(dayList.get(holder.getAdapterPosition()).getweekDay());
                dayDialog.dismiss();
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        dayObj = dayList.get(position);

        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color1 = generator.getRandomColor();

        TextDrawable.IBuilder builder = TextDrawable.builder()
                .beginConfig()
                .endConfig()
                .round();

//        TextDrawable ic1 = builder.build(dayObj.getweekDay().charAt(0)), color1);

        TextDrawable weekDayImage = builder
                .build(String.valueOf(dayObj.getweekDay().charAt(0)), color1); // radius in px

        ob.dayClickListener(dayObj.getweekDay());
        holder.image.setImageDrawable(weekDayImage);
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }

    public interface onBack{

        public void dayClickListener(String s);
    }
}
