package com.bmw.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by clare on 2018/10/16.
 */
@RestController
@RequestMapping("vehicle")
public class VehiclePositionController {
    @RequestMapping("sessions/{vehicleId}")
    public List<VehiclePosition> getVehicleSessions(@PathVariable("vehicleId") String vehicleId) {
        System.out.println(vehicleId);
        List<VehiclePosition> vp = BmwRestApplication.vehicles.get(vehicleId);
        return vp == null ? null : vp;
    }

    @RequestMapping("session/{vehicleId}/{sessionId}")
    public VehiclePosition getSingleSessionByTS(@PathVariable("vehicleId") String vehicleId, @PathVariable("sessionId") String sessionId) {
        return null;
    }

    @RequestMapping("position/last/{vehicleId}")
    public VehiclePosition getLastPosition() {
        return null;
    }

}
