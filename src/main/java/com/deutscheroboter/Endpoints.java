package com.deutscheroboter;

import org.springframework.web.bind.annotation.*;

@RestController
public class Endpoints {
    @PostMapping(path = "/answer", consumes = "application/json", produces = "application/json")
    @ResponseBody
    String answer(@RequestBody String question) {
        return "You question is: " + question;
    }
}
