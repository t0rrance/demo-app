package com.example.demo.utils;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CsvConfiguration {

    public static final char DEFAULT_SEPARATOR = ',';
    char separator;

}
