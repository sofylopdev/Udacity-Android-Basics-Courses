package com.example.android.miwok;

/**
 * Created by Sofia on 10/17/2017.
 */

public class Word {

    private String english;
    private String miwak;
    private int imageResource;
    private int soundResourceId;

    public Word(String english, String miwak, int soundResource) {
        this.english = english;
        this.miwak = miwak;
        this.soundResourceId = soundResource;
    }

    public Word(String english, String miwak, int imageResource, int soundResource) {
        this.english = english;
        this.miwak = miwak;
        this.imageResource = imageResource;
        this.soundResourceId = soundResource;
    }

    public String getDefaultTranslation() {
        return english;
    }

    public String getMiwakTranslation() {
        return miwak;
    }

    public int getImageResourceId() {
        return imageResource;
    }

    public int getSoundResourceId(){ return soundResourceId;}
}
