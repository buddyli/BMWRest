package com.bmw.rest;

import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.*;

@SpringBootApplication
public class BmwRestApplication {
    private static final String dataFile = "static/data.csv";
    public static Map<String, List<VehiclePosition>> vehicles = new HashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(BmwRestApplication.class, args);
        loadPositions();
    }

    public static void loadPositions() {
        try {
            File file = new ClassPathResource(dataFile).getFile();
            List<String> lines = FileUtils.readLines(file);
            for (String line : lines.subList(1, lines.size() - 1)) {
                String[] array = line.split(",");
                String vehicleId = array[1];
                VehiclePosition vp = new VehiclePosition(Long.parseLong(array[0]), Double.parseDouble(array[3]), Double.parseDouble(array[4]), Integer.parseInt(array[5]), array[2], array[1]);

                if (vehicles.get(vehicleId) == null) {
                    List<VehiclePosition> vehicleList = new LinkedList<>();
                    vehicles.put(vehicleId, vehicleList);
                    vehicleList.add(vp);
                } else {
                    vehicles.get(vehicleId).add(vp);
                }
            }

            for (List<VehiclePosition> vehiclePositionList : vehicles.values()) {
                Collections.sort(vehiclePositionList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
