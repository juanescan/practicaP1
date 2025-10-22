package edu.eci.are.math_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

@RestController
public class ProxyController {

    @Autowired
    private RoundRobinService roundRobinService;

    @GetMapping("/linearsearch")
    public ResponseEntity<JsonObject> linearSearch(@RequestParam String list, @RequestParam String value) {
        return ResponseEntity.ok(roundRobinService.getConnection("/math/linearsearch", list, value));
    }

    @GetMapping("/binarysearch")
    public ResponseEntity<JsonObject> binarySearch(@RequestParam String list, @RequestParam String value) {
        return ResponseEntity.ok(roundRobinService.getConnection("/math/binarysearch", list, value));
    }
}
