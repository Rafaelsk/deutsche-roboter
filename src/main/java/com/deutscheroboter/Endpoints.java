package com.deutscheroboter;

import com.deutscheroboter.models.QuestionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Endpoints {

    @Autowired
    private Brain brain;

    @GetMapping("/sheetIds")
    public List<String> index() {
        return brain.getIds();
    }


    @PostMapping(path = "/answer/{sheetId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String answer(@RequestBody QuestionRequest questionRequest, @PathVariable String sheetId) {
        return brain.answer(sheetId, questionRequest.getQuestion());
    }
}