package com.github.googletranslate.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LanguageCollection {

    AUTO("auto", "Auto Detect");

    final String abbreviation;
    final String description;

    @AllArgsConstructor
    @Getter
    public enum Italian {

        ITALIANO("it", "Italiano"),
        INGLESE("en", "Inglese");

        final String abbreviation;
        final String description;
    }
}
