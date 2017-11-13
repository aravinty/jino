package com.jino.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Items {

    @SerializedName("field")
    @Expose
    private String field;

    @SerializedName("fieldtype")
    @Expose
    private String fieldtype;

    @SerializedName("fieldId")
    @Expose
    private String fieldId;

    @SerializedName("fromString")
    @Expose
    private String fromString;

    @SerializedName("toString")
    @Expose
    private String toString;



    public String getField() {
        return field;
    }

    public String getFieldtype() {
        return fieldtype;
    }

    public String getFieldId() {
        return fieldId;
    }

    public String getFromString() {
        if(fromString == null) {
            return "NONE";
        }
        return fromString;
    }

    public String getToString() {
        if(toString == null) {
            return "NONE";
        } else {
            return toString;
        }
    }
}
