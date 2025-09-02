package com.scotiabank.service.student.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T readJsonFromClasspath(String path, Class<T> clazz) throws IOException {

        InputStream inputStream = JsonUtils.class.getClassLoader().getResourceAsStream(path);

        if (inputStream == null) {
            throw new FileNotFoundException("El archivo " + path + " no se encuentra en el classpath");
        }

        return objectMapper.readValue(inputStream, clazz);
    }
}
