package com.streamingserviceserver.server;

import com.streamingserviceserver.database.DatabaseHandler;
import com.streamingserviceserver.database.ISqlHandler;
import com.streamingserviceserver.model.BaseMedia;
import com.streamingserviceserver.model.movie.Movie;
import com.streamingserviceserver.model.movie.MovieDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/movies")
public class MoviesController extends BaseMediasController<Movie> {

    public MoviesController() {
        this(new DatabaseHandler());
    }

    public MoviesController(ISqlHandler sqlHandler) {
        super(new MovieDAO(sqlHandler), sqlHandler);
    }

    @GetMapping
    @ResponseBody
    public List<BaseMedia> getMovies(@RequestParam Optional<List<String>> serviceId, @RequestParam Optional<String> personId, @RequestParam Optional<String> genre, @RequestParam Optional<String> text) {
        return this.get(serviceId, personId, genre, text);
    }
}
