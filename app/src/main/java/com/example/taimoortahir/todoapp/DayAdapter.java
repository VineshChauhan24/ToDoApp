package com.example.taimoortahir.todoapp;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;

/**
 * Created by TaimoorTahir on 15/09/2017.
 */

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<DayModel> dayList;
    Dialog dayDialog;
    TextView dayText;
    private OnBack ob;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public DayAdapter(Context myContext, ArrayList<DayModel> mydayList, OnBack ob){
        this.mContext = myContext;
        this.dayList = mydayList;
        this.ob = ob;
    }

    public DayAdapter(Context myContext, ArrayList<DayModel> mydayList, TextView day, OnBack ob, MyDialog dialog){
        this.mContext = myContext;
        this.dayList = mydayList;
        this.ob = ob;
        dayDialog = dialog;
        dayText = day;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.day_dialog_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final DayModel dayObj = dayList.get(position);

        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color1 = generator.getRandomColor();
        TextDrawable.IBuilder builder = TextDrawable.builder()
                .beginConfig()
                .endConfig()
                .round();

        TextDrawable weekDayImage = builder
                .build(String.valueOf(dayObj.getweekDay().charAt(0)), color1); // radius in px

        holder.image.setImageDrawable(weekDayImage);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ob.DayClickListener(dayObj.getweekDay());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }

    public interface OnBack{

        public void DayClickListener(String s);
    }
}
