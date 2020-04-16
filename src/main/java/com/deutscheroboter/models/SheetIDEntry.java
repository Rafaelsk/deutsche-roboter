package com.deutscheroboter.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SheetIDEntry {
    private String name;
    private String sheetId;
    private String questionsRange;
    private String answersRange;
    private String defaultAnswer;

    public static SheetIDEntry fromStrings(List<String> data) {
        return new SheetIDEntry(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4));
    }
}
