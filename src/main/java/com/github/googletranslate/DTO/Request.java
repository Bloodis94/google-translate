package com.github.googletranslate.DTO;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public record Request(String text, String from, String to) {

    public Request{

        text = text.trim();
    }

    public String getEncodedText(){

        try {
            return URLEncoder.encode(text, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
