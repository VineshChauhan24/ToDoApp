package com.example.taimoortahir.todoapp;

/**
 * Created by TaimoorTahir on 22/09/2017.
 */

public class CategoryModel {
    int image;
    String category;

    public CategoryModel(){
    }

    public CategoryModel(int image, String category) {
        this.image = image;
        this.category = category;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
