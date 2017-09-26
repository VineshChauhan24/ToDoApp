package com.example.taimoortahir.todoapp;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by TaimoorTahir on 22/09/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<CategoryModel> categoryList;
    Dialog categoryDialog;
    TextView categoryText;
    private OnBack ob;
    String categ;
    String MyPreferences = "MyPrefs";
    SharedPreferences sharedPreferences;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout category;
        public ImageView image;
        public TextView category_text;
        public ImageView image_tick_c;
        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            category_text = (TextView) itemView.findViewById(R.id.category_textview);
            category = (LinearLayout) itemView.findViewById(R.id.category_layout);
            image_tick_c = (ImageView) itemView.findViewById(R.id.imageView_c);
        }
    }

    public CategoryAdapter(Context myContext, ArrayList<CategoryModel> cateList, OnBack ob, String str){
        this.mContext = myContext;
        this.categoryList = cateList;
        this.ob = ob;
        this.categ = str;
//        categoryDialog = dialog;
//        categoryText = category;
    }

    public CategoryAdapter(Context myContext, ArrayList<CategoryModel> cateList, Dialog dialog, TextView category, OnBack ob){
        this.mContext = myContext;
        this.categoryList = cateList;
        this.ob = ob;
        categoryDialog = dialog;
        categoryText = category;
    }

    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_dialog_item, parent, false);
//        MyViewHolder holder = new MyViewHolder(itemView);
//        sharedPreferences = mContext.getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
//
//        String temp = sharedPreferences.getString("Category",null);
//        for(int i=0; i<categoryList.size(); i++){
//            if(categoryList.get(i).getCategory() == temp){
//                holder.image_tick.setVisibility(View.VISIBLE);
//            }
//        }
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryAdapter.MyViewHolder holder, int position) {
       final CategoryModel categoryObj = categoryList.get(position);

        if (categ == categoryObj.getCategory()){
            holder.image_tick_c.setVisibility(View.VISIBLE);
        }
        holder.image.setImageResource(categoryObj.getImage());
        holder.category_text.setText(categoryObj.getCategory());
        holder.category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("Category", categoryObj.getCategory());
//                editor.commit();
////                holder.image_tick_c.setVisibility(View.VISIBLE);
//                holder.category.setBackgroundColor(Color.BLACK);
                ob.CategoryClickListener(categoryObj.getCategory());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public interface OnBack{

        public void CategoryClickListener(String s);
    }
}
