package com.example.android.bakingguide.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel
public class Ingredients {
    @SerializedName("ingredients")
    public final String name;
    public final double quantity;
    public final String measure;

    @ParcelConstructor
    public Ingredients(String name, double quantity, String measure) {
        this.name = name;
        this.quantity = quantity;
        this.measure = measure;
    }
}

/**

public class Ingredients implements Parcelable {

    private Double mQuantity;
    private String mMeasure;
    private String mIngredients;

    public Double getQuantity() {
        return mQuantity;
    }

    public void setQuantity(Double quantity) {
        this.mQuantity = quantity;
    }

    public String getMeasure() {
        return mMeasure;
    }

    public void setMeasure(String measure) {
        this.mMeasure = measure;
    }

    public String getIngredients() {
        return mIngredients;
        String.format("%s %s of %s", mIngredients.quantity, mIngredients.measure, mIngredients.name);
    }

    public void setIngredients(String ingredients) {
        this.mIngredients = ingredients;
    }


    protected Ingredients(Parcel in) {
        mQuantity = in.readByte() == 0x00 ? null : in.readDouble();
        mMeasure = in.readString();
        mIngredients = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (mQuantity == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(mQuantity);
        }
        dest.writeString(mMeasure);
        dest.writeString(mIngredients);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Ingredients> CREATOR = new Parcelable.Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };
}**/