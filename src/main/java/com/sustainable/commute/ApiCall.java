package com.sustainable.commute;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class ApiCall {

    @Value("${api.key}")
    private String apiKey;

    @GetMapping("/displayInfo")
    public String displayInfo(@RequestParam("station") String selectedStation, Model model) {
        String apiUrl = "https://api.tfl.gov.uk/StopPoint/" + selectedStation + "/Arrivals";

        // Setting up HTTP headers with the API key (secure way of authenticating API call)
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Creating a RestTemplate (Java class that enables CRUD operations) with the headers
        RestTemplate restTemplate = new RestTemplate();

        // Making an API call with the headers to get JSON data
        String jsonData = restTemplate.getForObject(apiUrl, String.class);

        // Processing the JSON data and extract information
        List<ArrivalInfo> arrivalInfoList = parseJson(jsonData);

        // Sorting the list based on timeToStationMinutes
        arrivalInfoList.sort(Comparator.comparingInt(ArrivalInfo::getTimeToStationMinutes));

        // Adding relevant information to the Thymeleaf model
        model.addAttribute("arrivalInfoList", arrivalInfoList);

        return "apiData";
    }

    // Create a new list to store ArrivalInfo objects parsed from JSON
    private List<ArrivalInfo> parseJson(String jsonData) {
        List<ArrivalInfo> arrivalInfoList = new ArrayList<>();

        try {
            // Create an instance of ObjectMapper from the Jackson library: convert JSON into Java objects.
            ObjectMapper objectMapper = new ObjectMapper();
            // Parse the JSON data string into a tree structure of JsonNode objects
            // Each JsonNode represents a node in the JSON tree and provides methods for accessing its data/children.
            JsonNode jsonNode = objectMapper.readTree(jsonData);

            for (JsonNode arrivalNode : jsonNode) {
                ArrivalInfo arrivalInfo = new ArrivalInfo();
                arrivalInfo.setLineName(arrivalNode.get("lineName").asText());
                arrivalInfo.setPlatformName(arrivalNode.get("platformName").asText());
                arrivalInfo.setTimeToStationMinutes(arrivalNode.get("timeToStation").asInt() / 60);
                arrivalInfo.setExpectedArrivalTime(Instant.parse(arrivalNode.get("expectedArrival").asText()));

                arrivalInfo.setDestinationName(arrivalNode.get("destinationName").asText());
                arrivalInfo.setModeName(arrivalNode.get("modeName").asText());

                // Adding the ArrivalInfo object to the list
                arrivalInfoList.add(arrivalInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arrivalInfoList;
    }
}
