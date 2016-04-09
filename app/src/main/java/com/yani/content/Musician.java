package com.yani.content;

import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 *
 */
public class Musician {

    private int id;
    private String name;
    private List<String> genres;

    @SerializedName("tracks")
    private int numberOfTracks;

    @SerializedName("albums")
    private int numberOfAlbums;

    @SerializedName("link")
    private String linkToWebPage;

    private String description;

    private Cover cover;

    public Musician(int id, String name, List<String> genres, int numberOfTracks, int numberOfAlbums,
                    String linkToWebPage, String description, Cover cover) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.numberOfTracks = numberOfTracks;
        this.numberOfAlbums = numberOfAlbums;
        this.linkToWebPage = linkToWebPage;
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

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
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

    public String getLinkToWebPage() {
        return linkToWebPage;
    }

    public void setLinkToWebPage(String linkToWebPage) {
        this.linkToWebPage = linkToWebPage;
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

}
