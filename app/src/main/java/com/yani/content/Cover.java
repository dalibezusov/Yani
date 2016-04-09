package com.yani.content;

import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class Cover {

    @SerializedName("small")
    private String linkToSmallCover;

    @SerializedName("big")
    private String linkToBigCover;

    public Cover(String linkToSmallCover, String linkToBigCover) {
        this.linkToSmallCover = linkToSmallCover;
        this.linkToBigCover = linkToBigCover;
    }

    public String getLinkToSmallCover() {
        return linkToSmallCover;
    }

    public void setLinkToSmallCover(String linkToSmallCover) {
        this.linkToSmallCover = linkToSmallCover;
    }

    public String getLinkToBigCover() {
        return linkToBigCover;
    }

    public void setLinkToBigCover(String linkToBigCover) {
        this.linkToBigCover = linkToBigCover;
    }

}
