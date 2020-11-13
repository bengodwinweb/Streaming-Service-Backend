package com.streamingserviceserver.server;

import com.streamingserviceserver.database.DatabaseHandler;
import com.streamingserviceserver.model.streamingservice.StreamingService;
import com.streamingserviceserver.model.streamingservice.StreamingServiceDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/service")
public class StreamingServiceController {

    private final StreamingServiceDAO dao = new StreamingServiceDAO(new DatabaseHandler());

    @GetMapping
    @ResponseBody
    public StreamingService getService(@RequestParam String id) {
        return dao.get(id).orElse(null);
    }
}
