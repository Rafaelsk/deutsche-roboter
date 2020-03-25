package com.deutscheroboter;

import com.deutscheroboter.googlesheets.SheetsReader;
import com.deutscheroboter.models.SheetIDEntry;
import com.deutscheroboter.models.SheetResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class Brain {

    private static final Gson gson = new Gson();
    private static final Type nestedLIstType = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
    private static final Random rand = new Random();

    @Autowired
    private SheetsReader sheetsReader;

    public String getSheetIds() {
        return sheetsReader.read("1CDBVrdywYWqNGddWYLObqxv1ta9cyIYwhbV4wlgqhGs", "Sheet1!A1:C4");
    }

    public List<SheetIDEntry> getSheetIdEntries() {
        List<SheetIDEntry> idEntries = new ArrayList<>();
        String sIds = getSheetIds();

        SheetResponse idsResponse = gson.fromJson(sIds, SheetResponse.class);
        for(List<String> data: idsResponse.getValues())
            idEntries.add(SheetIDEntry.fromStrings(data));
        return idEntries;
    }

    public List<String> getIds() {
        return getSheetIdEntries().stream()
                .map(SheetIDEntry::getSheetId)
                .collect(Collectors.toList());
    }

    public Map<String, String> getQAndA(String sheetId) {
        Map<String, String> map = new HashMap<>();
        List<SheetIDEntry> sheetIDEntries = getSheetIdEntries();
        for(SheetIDEntry entry: sheetIDEntries) {
            if (entry.getSheetId().equals(sheetId))

            try {
                String sheetQuestionsResponse = sheetsReader.read(entry.getSheetId(), entry.getQuestionsRange());
                SheetResponse questionsResponse = gson.fromJson(sheetQuestionsResponse, SheetResponse.class);
                List<List<String>> questionsList = questionsResponse.getValues();

                String sheetAnswerResponse = sheetsReader.read(entry.getSheetId(), entry.getAnswersRange());
                SheetResponse answersResponse = gson.fromJson(sheetAnswerResponse, SheetResponse.class);
                List<List<String>> answersList = answersResponse.getValues();

                for(int i=0; i<questionsList.size(); i++) {
                    List<String> questions = questionsList.get(i);
                    List<String> answers = answersList.get(i);
                    for (String question : questions) {
                        map.put(question, answers.get(rand.nextInt(answers.size())));
                    }
                }
            }
            catch(HttpClientErrorException e) {
                continue;
            }
        }
        return map;
    }

    public String answer(String sheetId, String question) {
        Map<String, String> qAndA = getQAndA(sheetId);
        System.out.println(question);
        System.out.println(qAndA);
        return qAndA.get(question);
    }

}
