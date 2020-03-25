package com.deutscheroboter.models;

import lombok.Data;

import java.util.List;

@Data
public class SheetResponse {
    private String range;
    private String majorDimension;
    private List<List<String>> values;
}
