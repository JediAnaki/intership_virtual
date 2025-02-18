package org.javaguru.travel.insurance.core.rest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonFileReaderTest {
    @Test
    void testReadJsonFromFile() {
        String json = JsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequest_success.json");
        assertNotNull(json, "Файл не должен быть пустым");
        System.out.println(json);
        assertTrue(json.contains("personFirstName"), "JSON должен содержать ключ 'personFirstName'");
    }
}

