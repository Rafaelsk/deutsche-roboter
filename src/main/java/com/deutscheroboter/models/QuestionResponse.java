package com.deutscheroboter.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionResponse {
    private String question;
    private String answer;
}
