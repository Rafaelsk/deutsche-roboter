package com.deutscheroboter;

import com.deutscheroboter.models.QuestionRequest;
import com.deutscheroboter.models.QuestionResponse;
import com.deutscheroboter.models.SheetIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Endpoints {

    @Autowired
    private Brain brain;

    @GetMapping("/sheetIds")
    public List<SheetIdResponse> index() {
        return brain.getIds();
    }


    @PostMapping(path = "/answer/{sheetId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public QuestionResponse answer(@RequestBody QuestionRequest questionRequest, @PathVariable String sheetId) {
        QuestionResponse result = brain.answer(sheetId, questionRequest.getQuestion());
        System.out.println(result.toString());
        return result;
    }
}