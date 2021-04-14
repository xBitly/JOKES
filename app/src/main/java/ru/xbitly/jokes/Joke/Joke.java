package ru.xbitly.jokes.Joke;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Joke {
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("gifURL")
    @Expose
    private String gifURL;
    @SerializedName("gifSize")
    @Expose
    private Integer gifSize;
    @SerializedName("previewURL")
    @Expose
    private String previewURL;

    public String getDescription(){
        return description;
    }

    public String getGifURL(){
        return gifURL;
    }

    public Integer getGifSize(){
        return gifSize;
    }

    public String getPreviewURL(){
        return previewURL;
    }
}
