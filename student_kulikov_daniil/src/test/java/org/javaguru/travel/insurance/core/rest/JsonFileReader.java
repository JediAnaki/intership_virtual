package org.javaguru.travel.insurance.core.rest;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Component
public class JsonFileReader {
    public static String readJsonFromFile(String filePath) {
        ClassLoader classLoader = JsonFileReader.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new RuntimeException("Файл не найден в classpath: " + filePath);
            }
            try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
                return scanner.useDelimiter("\\A").next();
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при чтении JSON-файла: " + filePath, e);
        }
    }
}

