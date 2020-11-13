package com.streamingserviceserver.server;

import com.streamingserviceserver.database.DatabaseHandler;
import com.streamingserviceserver.database.ISqlHandler;
import com.streamingserviceserver.model.Dao;
import com.streamingserviceserver.model.person.Person;
import com.streamingserviceserver.model.person.PersonDAO;
import com.streamingserviceserver.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/persons")
public class PersonsController {

    private final PersonDAO dao;

    public PersonsController() {
        this(new DatabaseHandler());
    }

    public PersonsController(ISqlHandler sqlHandler) {
        dao = new PersonDAO(sqlHandler);
    }

    @GetMapping
    @ResponseBody
    public List<Person> get(@RequestParam Optional<String> mediaId, Optional<String> search) {
        List<Person> list = mediaId.isPresent() ? dao.getAllFromMedia(mediaId.get()) : dao.getAll();

        if (search.isPresent()) {
            String[] queryStrings = StringUtil.normalizeStringForQuery(search.get()).split(" ");
            list = list.stream().filter(x -> Arrays.stream(queryStrings).anyMatch(y -> x.fullName().contains(y))).collect(Collectors.toList());
        }

        return list;
    }
}
