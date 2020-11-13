package com.streamingserviceserver.server;

import com.streamingserviceserver.database.DatabaseHandler;
import com.streamingserviceserver.model.MediaInfo;
import com.streamingserviceserver.model.person.PersonDAO;
import com.streamingserviceserver.model.show.Show;
import com.streamingserviceserver.model.show.ShowDAO;
import com.streamingserviceserver.model.streamingservice.StreamingServiceDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Optional;

//@Controller
//@RequestMapping("/show")
//public class ShowController {
//
//    private final DatabaseHandler db = new DatabaseHandler();
//
//    private final ShowDAO dao = new ShowDAO(db);
//    private final PersonDAO personDAO = new PersonDAO(db);
//    private final StreamingServiceDAO serviceDAO = new StreamingServiceDAO(db);
//
//    @GetMapping
//    @ResponseBody
//    public MediaInfo getShow(@RequestParam Map<String, String> params) {
//        Optional<Show> show = Optional.empty();
//        if (params.containsKey("id")) {
//            show = dao.get(params.get("id"));
//        } else if (params.containsKey("name")) {
//            show = dao.getAll().stream().filter(x -> x.getName().toLowerCase().contains(params.get("name").toLowerCase())).findFirst();
//        }
//
//        if (show.isPresent()) {
//            Show found = show.get();
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
@RequestMapping("/show")
public class ShowController extends BaseMediaController<Show> {

    public ShowController() {
        this(new DatabaseHandler());
    }

    public ShowController(DatabaseHandler db) {
        super(new ShowDAO(db), db);
    }

    @GetMapping
    @ResponseBody
    public MediaInfo getInfo(@RequestParam Optional<String> id, @RequestParam Optional<String> name) {
        return this.get(id, name);
    }
}
