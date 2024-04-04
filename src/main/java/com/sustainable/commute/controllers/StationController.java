package com.sustainable.commute.controllers;

import com.sustainable.commute.models.Station;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Collections;
import java.util.Comparator;
// for sorting

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// Reading off the csv file to get the station/Naptan code pairs.
@Controller
public class StationController {
    @GetMapping("/")
    public String showStationSelector(Model model) {
        List<Station> stations = readCsvData();

        // Sorting the stations alphabetically by 'label'
        Collections.sort(stations, Comparator.comparing(Station::getLabel));
        model.addAttribute("stations", stations);
        return "stationSelector";
    }

    List<Station> readCsvData() {
        List<Station> stations = new ArrayList<>();
        try {
            ClassPathResource resource = new ClassPathResource("stations.csv");
            InputStream inputStream = resource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)); //

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String value = parts[0].trim();
                    String label = parts[1].trim();
                    stations.add(new Station(value, label));
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stations;
    }
}
