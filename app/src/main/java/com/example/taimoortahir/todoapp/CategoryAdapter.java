package com.example.taimoortahir.todoapp;

import android.app.Dialog;
import android.content.Context;
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

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout category;
        public ImageView image;
        public TextView category_text;
        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            category_text = (TextView) itemView.findViewById(R.id.category_textview);
            category = (LinearLayout) itemView.findViewById(R.id.category_layout);
        }
    }

    public CategoryAdapter(Context myContext, ArrayList<CategoryModel> cateList, OnBack ob){
        this.mContext = myContext;
        this.categoryList = cateList;
        this.ob = ob;
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

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.MyViewHolder holder, int position) {
       final CategoryModel categoryObj = categoryList.get(position);

        holder.image.setImageResource(categoryObj.getImage());
        holder.category_text.setText(categoryObj.getCategory());
        holder.category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
