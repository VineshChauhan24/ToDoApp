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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.ArrayList;

/**
 * Created by TaimoorTahir on 22/09/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>{
    private Context mContext;
    private ArrayList<CategoryModel> categoryList;
    Dialog categoryDialog;
    TextView categoryText;
    CategoryModel categoryObj;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout category;
        public ImageView image;
        public TextView category_text;
        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            category_text = (TextView) itemView.findViewById(R.id.category_textview);
            category = (LinearLayout) itemView.findViewById(R.id.category_layout);
//            category.setOnClickListener(this);
        }
    }

    public CategoryAdapter(Context myContext, ArrayList<CategoryModel> cateList, Dialog dialog, TextView category){
        this.mContext = myContext;
        this.categoryList = cateList;
        categoryDialog = dialog;
        categoryText = category;
    }

    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_dialog_item, parent, false);

        final MyViewHolder holder = new MyViewHolder(itemView);

        holder.category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryText.setText(categoryList.get(holder.getAdapterPosition()).getCategory());
                categoryDialog.dismiss();
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.MyViewHolder holder, int position) {
        categoryObj = categoryList.get(position);

//        TextDrawable weekDayImage = TextDrawable.builder()
//                .buildRound(String.valueOf(day.getweekDay().charAt(0)), Color.RED); // radius in px

        holder.image.setImageResource(categoryObj.getImage());
        holder.category_text.setText(categoryObj.getCategory());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
