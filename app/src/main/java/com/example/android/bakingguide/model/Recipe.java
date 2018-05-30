package com.example.android.bakingguide.model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.ArrayList;

@Parcel
public class Recipe {
    public final int id;
    public final String name;
    public final ArrayList<Ingredients> ingredients;
    public final ArrayList<Instructions> instructions;
    public final String imageURL;

    @ParcelConstructor
    public Recipe(
            int id,
            String name,
            ArrayList<Ingredients> ingredients,
            ArrayList<Instructions> instructions,
            String imageURL
    ) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.imageURL = imageURL;
    }
}

/**public class Recipe implements Parcelable {

    private Integer mId;
    private String mName;
    private List<Ingredients> mIngredients = null;
    private List<Instructions> mInstructions = null;
    private Integer mServings;
    private String mImage;

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public List<Ingredients> getIngredients() {
        return mIngredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.mIngredients = ingredients;
    }

    public List<Instructions> getInstructions() {
        return mInstructions;
    }

    public void setInstructions(List<Instructions> instructions) {
        this.mInstructions = instructions;
    }

    public Integer getServings() {
        return mServings;
    }

    public void setServings(Integer servings) {
        this.mServings = servings;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        this.mImage = image;
    }


    protected Recipe(Parcel in) {
        mId = in.readByte() == 0x00 ? null : in.readInt();
        mName = in.readString();
        if (in.readByte() == 0x01) {
            mIngredients = new ArrayList<>();
            in.readList(mIngredients, Ingredients.class.getClassLoader());
        } else {
            mIngredients = null;
        }
        if (in.readByte() == 0x01) {
            mInstructions = new ArrayList<>();
            in.readList(mInstructions, Instructions.class.getClassLoader());
        } else {
            mInstructions = null;
        }
        mServings = in.readByte() == 0x00 ? null : in.readInt();
        mImage = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (mId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(mId);
        }
        dest.writeString(mName);
        if (mIngredients == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(mIngredients);
        }
        if (mInstructions == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(mInstructions);
        }
        if (mServings == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(mServings);
        }
        dest.writeString(mImage);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

}**/
