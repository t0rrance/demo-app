package com.example.demo.utils;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CsvParser<T> {

    private final Class<T> typeOfT;
    private final char separator;
    private final InputStream inputStream;

    public CsvParser(Class<T> type, InputStream inputStream) {
        this(type, inputStream, CsvConfiguration.DEFAULT_SEPARATOR);
    }

    public CsvParser(Class<T> typeOf, InputStream inputStream, char separator) {
        this.typeOfT = typeOf;
        this.separator = separator;
        this.inputStream = inputStream;
    }

    public List<T> parse() {

        return new CsvToBeanBuilder<T>(new InputStreamReader(inputStream))
                .withSeparator(separator)
                .withType(typeOfT)
                .withFilter(rows -> {
                    for (String row : rows) {
                        if (row != null && row.length() > 0) {
                            return true;
                        }
                    }
                    return false;
                })
                .build()
                .parse();
    }

}
