package com.streamingserviceserver.server;

import com.streamingserviceserver.database.DatabaseHandler;
import com.streamingserviceserver.model.BaseMedia;
import com.streamingserviceserver.model.MediaInfo;
import com.streamingserviceserver.model.movie.Movie;
import com.streamingserviceserver.model.movie.MovieDAO;
import com.streamingserviceserver.model.person.PersonDAO;
import com.streamingserviceserver.model.show.ShowDAO;
import com.streamingserviceserver.model.streamingservice.StreamingServiceDAO;
import com.streamingserviceserver.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

//@Controller
//@RequestMapping("/movie")
//public class MovieController {
//
//    private final DatabaseHandler db = new DatabaseHandler();
//    private final MovieDAO dao = new MovieDAO(db);
//    private final PersonDAO personDAO = new PersonDAO(db);
//    private final StreamingServiceDAO serviceDAO = new StreamingServiceDAO(db);
//
//    @GetMapping
//    @ResponseBody
//    public MediaInfo getMedia(@RequestParam Optional<String> id, @RequestParam Optional<String> name) {
//        Optional<Movie> media = Optional.empty();
//
//        if (id.isPresent()) {
//            media = dao.get(id.get());
//        } else if (name.isPresent()) {
//            media = dao.getAll().stream().filter(x -> StringUtil.normalizeStringForQuery(((BaseMedia) x).getName()).contains(StringUtil.normalizeStringForQuery(name.get()))).findFirst();
//        }
//
//        if (media.isPresent()) {
//            Movie found = media.get();
//            var info = new MediaInfo(found.getId(), found);
//            info.setDirectors(personDAO.getDirectorsFromMedia(found));
//            info.setActors(personDAO.getActorsFromMedia(found));
//            info.setStreamingServices(serviceDAO.getServicesForMedia(found));
//            return info;
//        }
//
//        return null;
//    }
//}

@Controller
@RequestMapping("/movie")
public class MovieController extends BaseMediaController<Movie> {

    public MovieController() {
        this(new DatabaseHandler());
    }

    public MovieController(DatabaseHandler db) {
        super(new MovieDAO(db), db);
    }

    @GetMapping
    @ResponseBody
    public MediaInfo getInfo(@RequestParam Optional<String> id, @RequestParam Optional<String> name) {
        return this.get(id, name);
    }
}
