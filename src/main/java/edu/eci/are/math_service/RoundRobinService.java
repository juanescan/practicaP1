package edu.eci.are.math_service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

@Service
public class RoundRobinService {
    private static final List<String> DOMAINS = List.of(
        "http://52.70.146.113:4567/math",
        "http://44.210.0.255:4568/math"
    );
    private int currentIndex = 0;
    private final RestTemplate restTemplate = new RestTemplate();

    public JsonObject getConnection(String endpoint, String list, String value) {
        String domain = DOMAINS.get(currentIndex);
        currentIndex = (currentIndex + 1) % DOMAINS.size();
        String url = domain + endpoint + "?list=" + list + "&value=" + value;
        String result = restTemplate.getForObject(url, String.class);
        return JsonParser.parseString(result).getAsJsonObject();
    }
}


