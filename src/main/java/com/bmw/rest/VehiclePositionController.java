package com.bmw.rest;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by clare on 2018/10/16.
 */
@RestController
@RequestMapping("vehicle")
public class VehiclePositionController {
    private static final String DATA_CSV = "static/data.csv";
    public static Map<String, List<VehiclePosition>> VEHICLES = new HashMap<>();

    static {
        try {
            ClassPathResource cpRes = new ClassPathResource(DATA_CSV);

            List<String> lines = IOUtils.readLines(cpRes.getInputStream());
            lines.subList(1, lines.size() - 1).stream().forEach((String line) -> {
                String[] array = line.split(",");
                String vehicleId = array[1];
                VehiclePosition vp = new VehiclePosition(Long.parseLong(array[0]), Double.parseDouble(array[3]), Double.parseDouble(array[4]), Integer.parseInt(array[5]), array[2], array[1]);

                if (VEHICLES.get(vehicleId) == null) {
                    List<VehiclePosition> vehicleList = new LinkedList<>();
                    VEHICLES.put(vehicleId, vehicleList);
                    vehicleList.add(vp);
                } else {
                    VEHICLES.get(vehicleId).add(vp);
                }
            });

            for (List<VehiclePosition> vehiclePositionList : VEHICLES.values()) {
                Collections.sort(vehiclePositionList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all positions of specified vehicle.
     *
     * @param vehicleId
     * @return Sorted position list, ordered by timestamp ascending.
     */
    @RequestMapping("sessions/{vehicleId}")
    public List<VehiclePosition> getVehicleSessions(@PathVariable("vehicleId") String vehicleId) {
        List<VehiclePosition> vp = VEHICLES.get(vehicleId);
        return vp == null ? null : vp;
    }

    /**
     * Get positions by vehicle and session ID.
     *
     * @param vehicleId
     * @param sessionId
     * @return Sorted position list, ordered by timestamp ascending.
     */
    @RequestMapping("session/{vehicleId}/{sessionId}")
    public List<VehiclePosition> getSingleSessionByTS(@PathVariable("vehicleId") String vehicleId, @PathVariable("sessionId") String sessionId) {
        List<VehiclePosition> vp = VEHICLES.get(vehicleId);

        return vp.stream().filter(position -> sessionId.equals(position.getSession())).collect(Collectors.toList());
    }

    /**
     * Get the last position of the specified vehicle.
     *
     * @param vehicleId
     * @return
     */
    @RequestMapping("position/last/{vehicleId}")
    public VehiclePosition getLastPosition(@PathVariable("vehicleId") String vehicleId) {
        List<VehiclePosition> vp = VEHICLES.get(vehicleId);
        return vp.isEmpty() ? null : vp.get(vp.size() - 1);
    }

}
