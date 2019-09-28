package com.example.gracetoa.m10;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by GraceToa on 1/2/16.
 */
public class MoviesList {

    @SerializedName("Search")
    private ArrayList<Movie> listMovies;


    public ArrayList<Movie> getListMovies() {
        return listMovies;
    }

    public void setListMovies(ArrayList<Movie> listMovies) {
        this.listMovies = listMovies;
    }


}
