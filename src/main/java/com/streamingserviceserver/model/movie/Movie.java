package com.streamingserviceserver.model.movie;

import com.streamingserviceserver.model.BaseMedia;

import java.time.LocalDate;

public class Movie extends BaseMedia {

    public int runTimeMinutes;

    public Movie(String id, String name, String description, LocalDate releaseDate, int runTimeMinutes, String genre) {
        super(id, name, description, releaseDate, genre);
        this.runTimeMinutes = runTimeMinutes;
    }

    public int getRunTimeMinutes() {
        return runTimeMinutes;
    }

    public void setRunTimeMinutes(int runTimeMinutes) {
        this.runTimeMinutes = runTimeMinutes;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "ID: " + getId() +
                ", Name: " + getName() +
                ", Runtime: " + runTimeMinutes +
                '}';
    }
}
