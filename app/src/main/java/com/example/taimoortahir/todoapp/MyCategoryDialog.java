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

public class MyCategoryDialog extends Dialog implements CategoryAdapter.OnBack {

    RecyclerView categoryRecycler;
    CategoryAdapter cAdapter;
    private OnBack ob;
    CategoryModel c_model = new CategoryModel();

    public MyCategoryDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.activity_category_dialog);
    }

    public MyCategoryDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected MyCategoryDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void myShow(){
        show();
    }

    public void setCategoryList(ArrayList<CategoryModel> list){
        setAdapter(list);
    }

    public void setCategoryList(ArrayList<CategoryModel> list, OnBack ob){

        onOptionSelected(ob);
        setAdapter(list);
    }

    public void setDefaultCategory(String s){
        c_model.setCategory(s);
    }

    public void onOptionSelected(OnBack ob){
        this.ob = ob;
    }

    public void setAdapter(ArrayList<CategoryModel> list){
        categoryRecycler = (RecyclerView) findViewById(R.id.recycler_category);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        categoryRecycler.setLayoutManager(mLayoutManager);
        cAdapter = new CategoryAdapter(getContext(), list, this, c_model.getCategory());
        categoryRecycler.setAdapter(cAdapter);
        cAdapter.notifyDataSetChanged();
    }

    @Override
    public void CategoryClickListener(String s) {
        ob.CategoryItemClickListener(s, this);
    }

    public interface OnBack{

        public void CategoryItemClickListener(String s, MyCategoryDialog ref);
    }
}
