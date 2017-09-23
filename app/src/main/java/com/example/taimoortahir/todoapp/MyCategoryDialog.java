package com.example.taimoortahir.todoapp;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by TaimoorTahir on 23/09/2017.
 */

public class MyCategoryDialog extends Dialog {

    RecyclerView categoryRecycler;
    CategoryAdapter cAdapter;

    public MyCategoryDialog(@NonNull Context context) {
        super(context);
    }

    public MyCategoryDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected MyCategoryDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void mySetContentView(int contentView){
        setContentView(contentView);
    }

    public void myShow(){
        show();
    }

    public void setCategoryList(ArrayList<CategoryModel> list){

        // set categoryRecycler here

        cAdapter = new CategoryAdapter(getContext(), list);
        categoryRecycler.setAdapter(cAdapter);
        cAdapter.notifyDataSetChanged();
    }

    public interface onBack{

        public void myClickListener();
    }
}
