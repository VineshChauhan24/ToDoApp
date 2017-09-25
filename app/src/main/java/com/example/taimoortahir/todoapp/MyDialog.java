package com.example.taimoortahir.todoapp;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by TaimoorTahir on 23/09/2017.
 */

public class MyDialog extends Dialog implements DayAdapter.OnBack {

    RecyclerView dayRecycler;
    DayAdapter dAdapter;
    private OnBack ob;

    public MyDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.activity_day_dialog);
    }

    public MyDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected MyDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setDayList(ArrayList<DayModel> list){
        setAdapter(list);
    }

    public void setDayList(ArrayList<DayModel> list, OnBack ob){

        onOptionSelected(ob);
        setAdapter(list);
    }

    public void onOptionSelected(OnBack ob){
        this.ob = ob;
    }

    public void setAdapter(ArrayList<DayModel> list){
        dayRecycler = (RecyclerView) findViewById(R.id.recycler_day);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        dayRecycler.setLayoutManager(mLayoutManager);
        dAdapter = new DayAdapter(getContext(), list, this);
        dayRecycler.setAdapter(dAdapter);
        dAdapter.notifyDataSetChanged();
    }

    @Override
    public void DayClickListener(String s){
        ob.DayItemClickListener(s, this);
    }

    public interface OnBack{
        public void DayItemClickListener(String s, MyDialog ref);
    }

}
