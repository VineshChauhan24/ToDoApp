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

public class MyDialog extends Dialog {

    RecyclerView dayRecycler;
    DayAdapter dAdapter;

    public MyDialog(@NonNull Context context) {
        super(context);
    }

    public MyDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected MyDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void mySetContentView(int contentView){
        setContentView(contentView);
    }

    public void myShow(){
        show();
    }

    public void setDayList(ArrayList<DayModel> list){

        // set dayRecycler here

        dAdapter = new DayAdapter(getContext(), list);
        dayRecycler.setAdapter(dAdapter);
        dAdapter.notifyDataSetChanged();
    }
    
    public interface onBack{
        
        public void myClickListener();
    }

}
