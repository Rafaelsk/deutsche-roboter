package com.deutscheroboter.googlesheets;

import com.deutscheroboter.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;


@Service
public class SheetsReader {

    @Value("${GOOGLE_API_KEY}")
    private String apiKey;

    @Autowired
    private RestService restService;

    public String read(String spreadSheetId, String range) throws HttpClientErrorException {
        String url = String.format(
                "https://sheets.googleapis.com/v4/spreadsheets/%s/values/%s?key=%s",
                spreadSheetId,
                range,
                apiKey
                );
        return restService.get(url);
    }
}