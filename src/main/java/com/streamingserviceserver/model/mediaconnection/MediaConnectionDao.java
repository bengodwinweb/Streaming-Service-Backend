package com.streamingserviceserver.model.mediaconnection;

import com.streamingserviceserver.database.ISqlHandler;
import com.streamingserviceserver.model.movie.Movie;
import com.streamingserviceserver.model.movie.MovieDAO;
import com.streamingserviceserver.model.person.Person;
import com.streamingserviceserver.model.show.Show;
import com.streamingserviceserver.model.show.ShowDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MediaConnectionDao {

    private final ISqlHandler _sqlHandler;
    private final ShowDAO showDao;
    private final MovieDAO movieDAO;

    public MediaConnectionDao(ISqlHandler sqlHandler) {
        this._sqlHandler = sqlHandler;
        this.showDao = new ShowDAO(sqlHandler);
        this.movieDAO = new MovieDAO(sqlHandler);
    }

    public List<MediaConnection> getShowsForPerson(Person p) {
        if (p == null) {
            return new ArrayList<>();
        }

        return getShowsForPerson(p.getPersonId());
    }

    public List<MediaConnection> getShowsForPerson(String personId) {
        if (personId == null) {
            return new ArrayList<>();
        }

        List<Show> list = showDao.getAllFromPerson(personId);
        return list.stream().map(x -> new MediaConnection(x.getId(), x.getName())).collect(Collectors.toList());
    }

    public List<MediaConnection> getMoviesForPerson(Person p) {
        if (p == null) {
            return new ArrayList<>();
        }

        return getMoviesForPerson(p.getPersonId());
    }

    public List<MediaConnection> getMoviesForPerson(String personId) {
        if (personId == null) {
            return new ArrayList<>();
        }

        List<Movie> list= movieDAO.getAllFromPerson(personId);
        return list.stream().map(x -> new MediaConnection(x.getId(), x.getName())).collect(Collectors.toList());
    }
}
