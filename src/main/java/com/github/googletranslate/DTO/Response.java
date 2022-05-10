package com.github.googletranslate.DTO;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public record Response(ArrayList<LinkedHashMap<String, Object>> sentences) {

    public String getTranslatedPhrase(){

        StringBuilder result = new StringBuilder();

        for (LinkedHashMap<String, Object> sentence : sentences){

            result.append(sentence.get("trans"));
        }

        return result.toString();
    }
}
