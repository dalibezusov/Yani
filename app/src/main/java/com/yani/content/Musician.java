package com.yani.content;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


/**
 *
 */
public class Musician {

    private int id;
    private String name;
    private ArrayList<String> genres;

    @SerializedName("tracks")
    private int numberOfTracks;

    @SerializedName("albums")
    private int numberOfAlbums;

    @SerializedName("link")
    private String linkToMusicianWebPage;

    private String description;

    private Cover cover;

    public Musician(int id, String name, ArrayList<String> genres, int numberOfTracks, int numberOfAlbums,
                    String linkToMusicianWebPage, String description, Cover cover) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.numberOfTracks = numberOfTracks;
        this.numberOfAlbums = numberOfAlbums;
        this.linkToMusicianWebPage = linkToMusicianWebPage;
        this.description = description;
        this.cover = cover;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public int getNumberOfTracks() {
        return numberOfTracks;
    }

    public void setNumberOfTracks(int numberOfTracks) {
        this.numberOfTracks = numberOfTracks;
    }

    public int getNumberOfAlbums() {
        return numberOfAlbums;
    }

    public void setNumberOfAlbums(int numberOfAlbums) {
        this.numberOfAlbums = numberOfAlbums;
    }

    public String getLinkToMusicianWebPage() {
        return linkToMusicianWebPage;
    }

    public void setLinkToMusicianWebPage(String linkToMusicianWebPage) {
        this.linkToMusicianWebPage = linkToMusicianWebPage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }


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

}
