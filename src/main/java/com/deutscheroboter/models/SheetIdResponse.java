package com.deutscheroboter.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SheetIdResponse {
    private String name;
    private String id;

    public static SheetIdResponse fromSheetIdEntry(SheetIDEntry sheetIDEntry) {
        return new SheetIdResponse(sheetIDEntry.getName(), sheetIDEntry.getSheetId());
    }
}
