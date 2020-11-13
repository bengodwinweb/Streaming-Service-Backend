package com.streamingserviceserver.server;

import com.streamingserviceserver.database.DatabaseHandler;
import com.streamingserviceserver.database.ISqlHandler;
import com.streamingserviceserver.model.BaseMedia;
import com.streamingserviceserver.model.show.Show;
import com.streamingserviceserver.model.show.ShowDAO;
import com.streamingserviceserver.model.streamingservice.StreamingService;
import com.streamingserviceserver.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;

//@Controller
//@RequestMapping("/shows")
//public class ShowsController {
//
//    private static final DatabaseHandler db = new DatabaseHandler();
//
//    private ShowDAO dao = new ShowDAO(db);
//
//    @GetMapping
//    @ResponseBody
//    public List<Show> getShows(@RequestParam Optional<List<String>> serviceId, @RequestParam Optional<List<String>> personId, @RequestParam Optional<String> genre, @RequestParam Optional<String> text) {
//        List<Show> shows = serviceId.isEmpty() ? dao.getAll() : dao.getAllFromStreamingService(serviceId.get().toArray(String[]::new));
//
//        if (personId.isPresent()) {
//            Map<String, Show> allShowsForPerson = new HashMap<>();
//            for (var pId : personId.get()) {
//                dao.getAllFromPerson(pId).forEach(x -> allShowsForPerson.put(x.getId(), x));
//            }
//
//            shows = shows.stream().filter(x -> allShowsForPerson.containsKey(x.getId())).collect(Collectors.toList());
//        }
//
//        if (genre.isPresent()) {
//            shows = shows.stream().filter(x -> x.getGenre().toLowerCase().contains(genre.get().toLowerCase())).collect(Collectors.toList());
//        }
//
//        if (text.isPresent()) {
//            String queryText = StringUtil.normalizeStringForQuery(text.get());
//            Map<Show, Integer> temp = new HashMap<>();
//
//            for (var show : shows) {
//                String combinedTitleAndDescription = StringUtil.normalizeStringForQuery(show.getName() + show.getDescription());
//                int index = combinedTitleAndDescription.indexOf(queryText);
//                if (index >= 0) {
//                    temp.put(show, index);
//                }
//            }
//
//            shows = temp.entrySet().stream().sorted((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue())).map(x -> x.getKey()).collect(Collectors.toList());
//        }
//
//        return shows;
//    }
//}

@Controller
@RequestMapping("/shows")
public class ShowsController extends BaseMediasController<Show> {

    public ShowsController() {
        this(new DatabaseHandler());
    }

    public ShowsController(ISqlHandler sqlHandler) {
        super(new ShowDAO(sqlHandler), sqlHandler);
    }

    @GetMapping
    @ResponseBody
    public List<BaseMedia> getMedia(@RequestParam Optional<List<String>> serviceId, @RequestParam Optional<String> personId, @RequestParam Optional<String> genre, @RequestParam Optional<String> text) {
        return get(serviceId, personId, genre, text);
    }

}
