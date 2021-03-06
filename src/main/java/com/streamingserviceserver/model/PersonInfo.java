package com.streamingserviceserver.model;

import com.streamingserviceserver.model.mediaconnection.MediaConnection;
import com.streamingserviceserver.model.movie.Movie;
import com.streamingserviceserver.model.person.Person;
import com.streamingserviceserver.model.show.Show;

import java.util.*;

public class PersonInfo {

    private final String id;
    private final Person person;
    private final Map<String, MediaConnection> movies;
    private final Map<String, MediaConnection> shows;

    public PersonInfo(String id, Person person) {
        this.id = id;
        this.person = person;
        movies = new HashMap<>();
        shows = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public List<MediaConnection> getMovies() {
        return new ArrayList<>(movies.values());
    }

    public List<MediaConnection>getShows() {
        return new ArrayList<>(shows.values());
    }

    public void setMovies(List<MediaConnection> movies) {
        movies.forEach(x -> this.movies.put(x.getMediaId(), x));
    }

    public void setShows(List<MediaConnection> shows) {
        shows.forEach(x -> this.shows.put(x.getMediaId(), x));
    }

}
