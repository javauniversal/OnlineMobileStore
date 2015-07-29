package com.example.poo_code.onlinemobilestore.Entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Poo_Code on 19/07/2015.
 */
public class MasterItem {

    @SerializedName("code")
    private int code;

    @SerializedName("value")
    private String value;

    @SerializedName("composition")
    private String composition;

    public int getCode() {
        return code;
    }

    public void setCode(int code) { this.code = code; }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getComposition() { return composition; }

    public void setComposition(String composition) { this.composition = composition; }

}
