package com.example.android.bakingguide.model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel
public class Instructions {
    public final int id;
    public final String description;
    public final String shortDescription;
    public final String videoURL;
    public final String thumbnailURL;

    @ParcelConstructor
    public Instructions(
            int id,
            String description,
            String shortDescription,
            String videoURL,
            String thumbnailURL
    ) {
        this.id = id;
        this.description = description;
        this.shortDescription = shortDescription;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }
}

/**
import android.os.Parcel;
import android.os.Parcelable;

public class Instructions implements Parcelable {

    private Integer id;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }


    protected Instructions(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        shortDescription = in.readString();
        description = in.readString();
        videoURL = in.readString();
        thumbnailURL = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id);
        }
        dest.writeString(shortDescription);
        dest.writeString(description);
        dest.writeString(videoURL);
        dest.writeString(thumbnailURL);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Instructions> CREATOR = new Parcelable.Creator<Instructions>() {
        @Override
        public Instructions createFromParcel(Parcel in) {
            return new Instructions(in);
        }

        @Override
        public Instructions[] newArray(int size) {
            return new Instructions[size];
        }
    };
}**/