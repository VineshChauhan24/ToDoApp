package com.example.taimoortahir.todoapp;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TaimoorTahir on 15/09/2017.
 */

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<DayModel> dayList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.day_imageview);
        }
    }

    public DayAdapter(Context myContext, ArrayList<DayModel> mydayList){
        this.mContext = myContext;
        this.dayList = mydayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.day_dialog_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final DayModel day = dayList.get(position);
        holder.image.setImageResource(day.getImage());
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }
}
