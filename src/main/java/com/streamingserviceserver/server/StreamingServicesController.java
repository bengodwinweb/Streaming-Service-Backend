package com.streamingserviceserver.server;

import com.streamingserviceserver.database.DatabaseHandler;
import com.streamingserviceserver.model.streamingservice.StreamingService;
import com.streamingserviceserver.model.streamingservice.StreamingServiceDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/services")
public class StreamingServicesController {

    private final StreamingServiceDAO dao = new StreamingServiceDAO(new DatabaseHandler());

    @GetMapping
    @ResponseBody
    public List<StreamingService> getAll() {
        return dao.getAll();
    }
}
