package com.example.android.bakingguide.model;

import android.text.TextUtils;

import com.example.android.bakingguide.interfaces.InstructionsModelInterface;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;
import org.parceler.ParcelProperty;

@Parcel(Parcel.Serialization.BEAN)
public class InstructionsModel implements InstructionsModelInterface {

    @ParcelProperty("instructions")
   public final Instructions mInstructions;

    @ParcelConstructor
    public InstructionsModel(@ParcelProperty("instructions")Instructions instructions) {
        this.mInstructions = instructions;
    }

    @Override
    public int getId() { return mInstructions.id;}

    public String getDescription() {
        return mInstructions.description;
    }

    @Override
    public String getShortDescription() {
        return mInstructions.shortDescription;
    }

    @Override
    public String getVideoUrl() {return mInstructions.videoURL;}



    @Override
    public Boolean hasVideo() {
        return !TextUtils.isEmpty(mInstructions.videoURL);
    }

    @Override
    public Boolean hasThumbnail() {
        return !TextUtils.isEmpty(mInstructions.thumbnailURL);
    }

    @Override
    public String getThumbnailUrl() {return mInstructions.thumbnailURL;}

   }
