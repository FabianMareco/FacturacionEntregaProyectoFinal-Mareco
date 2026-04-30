package com.facturacion.util;

import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final String API_URL = "http://worldclockapi.com/api/json/utc/now";

    public static LocalDateTime obtenerFechaActual() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(API_URL, String.class);
            // Parseo simple de la respuesta JSON (ejemplo: {"currentDateTime":"2025-03-28T10:30:00Z",...})
            String fechaStr = response.split("\"currentDateTime\":\"")[1].split("\"")[0];
            return LocalDateTime.parse(fechaStr, DateTimeFormatter.ISO_DATE_TIME);
        } catch (Exception e) {
            // Fallback a fecha local del sistema
            return LocalDateTime.now();
        }
    }
}