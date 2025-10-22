package edu.eci.are.math_service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

@Service
public class RoundRobinService {
    private static final List<String> DOMAINS = List.of(
        "http://50.17.133.60:4567",
        "http://54.174.147.40:4568"
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


