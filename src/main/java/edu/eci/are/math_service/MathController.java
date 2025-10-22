package edu.eci.are.math_service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    @GetMapping("/linearsearch")
    public Map<String, Object> linearSearch(@RequestParam String list, @RequestParam String value) {
        String[] inputlist = list.split(",");
        Map<String, Object> response = new HashMap<>();
        response.put("operation", "linearSearch");
        response.put("inputlist", list);
        response.put("value", value);

        for (int i = 0; i < inputlist.length; i++) {
            if (value.equals(inputlist[i])) {
                response.put("output", i);
                return response;
            }
        }
        response.put("output", -1);
        return response;
    }

    @GetMapping("/binarysearch")
    public Map<String, Object> binarySearch(@RequestParam String list, @RequestParam String value) {
        String[] inputlist = list.split(",");
        Map<String, Object> response = new HashMap<>();
        response.put("operation", "binarySearch");
        response.put("inputlist", list);
        response.put("value", value);

        int result = recursiveBinarySearch(inputlist, value, 0, inputlist.length - 1);
        response.put("output", result);
        return response;
    }

    private int recursiveBinarySearch(String[] inputlist, String value, int left, int right) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            if (value.equals(inputlist[mid])) return mid;
            if (Integer.parseInt(inputlist[mid]) > Integer.parseInt(value)) {
                return recursiveBinarySearch(inputlist, value, left, mid - 1);
            }
            return recursiveBinarySearch(inputlist, value, mid + 1, right);
        }
        return -1;
    }
}

