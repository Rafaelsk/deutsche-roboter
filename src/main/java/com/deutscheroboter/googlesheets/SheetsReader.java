package com.deutscheroboter.googlesheets;

import com.deutscheroboter.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;


@Service
public class SheetsReader {

    private static final String API_KEY = "AIzaSyC9zfFRlNLqFDvim3RqhvHFmn09qD8TGDw";

    @Autowired
    private RestService restService;

    public String read(String spreadSheetId, String range) throws HttpClientErrorException {
        String url = String.format(
                "https://sheets.googleapis.com/v4/spreadsheets/%s/values/%s?key=%s",
                spreadSheetId,
                range,
                API_KEY
                );
        return restService.get(url);
    }
}