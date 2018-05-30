package com.example.android.bakingguide.interfaces;

public interface InstructionsModelInterface {

    int getId();

        String getDescription();
        String getShortDescription();
        String getVideoUrl();
        String getThumbnailUrl();
        Boolean hasVideo();
        Boolean hasThumbnail();
        //Uri getDefaultMediaPicture();

}
