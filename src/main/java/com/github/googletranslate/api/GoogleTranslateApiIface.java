package com.github.googletranslate.api;

import com.github.googletranslate.DTO.Request;
import com.github.googletranslate.enums.LanguageCollection;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;

public interface GoogleTranslateApiIface {

    String DEFAULT_URL = "https://translate.google.com";
    String DEFAULT_URL_TOKEN = "/translate_a/single?client=it&dt=qca&dt=t&dt=rmt&dt=bd&dt=rms&dt=sos&dt=md&dt=gt&dt=ld&dt=ss&dt=ex&otf=3&dj=1&q=";
    String DEFAULT_TOKEN_TEXT = "&hl=id&ie=UTF-8&oe=UTF-8&sl=";
    String DEFAULT_TOKEN_FROM_TO = "&tl=";
    String DEFAULT_HEADER_ACCEPT_NAME = "accept";
    String DEFAULT_HEADER_ACCEPT_VALUE = "*/*";
    String DEFAULT_HEADER_USER_AGENT_NAME = "user-agent";
    String DEFAULT_HEADER_USER_AGENT_VALUE = "GoogleTranslate/6.31.59289 (iPhone; iOS 14.4; id; iPhone10,4)";

    default URI composeUrl(@Nullable Request request){

        if (request == null)
            request = new Request("", LanguageCollection.AUTO.getAbbreviation(), LanguageCollection.Italian.INGLESE.getAbbreviation());

        return URI.create(DEFAULT_URL + DEFAULT_URL_TOKEN + request.getEncodedText() + DEFAULT_TOKEN_TEXT + request.from() + DEFAULT_TOKEN_FROM_TO + request.to());
    }

    default HttpHeaders composeHeaders(){

        return composeHeaders(new HashMap<String, String>());
    }

    default HttpHeaders composeHeaders(HashMap<String, String> headersMap){

        HttpHeaders headers = new HttpHeaders();

        if (!headersMap.containsKey(DEFAULT_HEADER_ACCEPT_NAME))
            headersMap.put(DEFAULT_HEADER_ACCEPT_NAME, DEFAULT_HEADER_ACCEPT_VALUE);

        if (!headersMap.containsKey(DEFAULT_HEADER_USER_AGENT_NAME))
            headersMap.put(DEFAULT_HEADER_USER_AGENT_NAME, DEFAULT_HEADER_USER_AGENT_VALUE);

        headersMap.keySet().stream().sorted()
                .forEach( h -> {
                    
                    headers.set(h, headersMap.get(h));
                });

        return headers;
    }

    default RequestEntity composeRequest(Request request, @Nullable MultiValueMap<String, String> headers, @Nullable URI url) {

        return composeRequest(request, headers, HttpMethod.GET, url);
    }

    default RequestEntity composeRequest(Request request, @Nullable MultiValueMap<String, String> headers,
                                                @Nullable HttpMethod method, @Nullable URI url){

        if (url == null)
            url = composeUrl(request);

        return new RequestEntity<>(request, headers, method, url);
    }

    default <T> ResponseEntity<T> exchange(@Nullable RequestEntity<?> request, Class<T> responseType){

        if (request == null){

            request = composeRequest( new Request("", LanguageCollection.AUTO.getAbbreviation(), LanguageCollection.Italian.INGLESE.getAbbreviation()),
                    null,
                    null);
        }

        return new RestTemplate().exchange(request, responseType);
    }
}
