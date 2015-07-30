package com.example.poo_code.onlinemobilestore.Entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Poo_Code on 19/07/2015.
 */
public class Category {

    @SerializedName("idcategory")
    private int idCategory;

    @SerializedName("description")
    private String description;

    @SerializedName("state")
    private int state;

    public Category(int idCategory, String description, int state) {
        this.idCategory = idCategory;
        this.description = description;
        this.state = state;
    }

    public int getIdCategory() { return idCategory; }

    public void setIdCategory(int idCategory) { this.idCategory = idCategory; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public int getState() { return state; }

    public void setState(int state) { this.state = state; }

}
