package com.bmw.rest;

/**
 * Created by clare on 2018/10/16.
 */
public class VehiclePosition implements Comparable<VehiclePosition> {
    private long timestamp;
    private double latitude;
    private double longitude;
    private int heading;
    private String session;
    private String vehicleId;

    public VehiclePosition() {
    }

    public VehiclePosition(long timestamp, double latitude, double longitude, int heading, String session, String vehicleId) {
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.heading = heading;
        this.session = session;
        this.vehicleId = vehicleId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getHeading() {
        return heading;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public int compareTo(VehiclePosition vhp) {
        return this.timestamp < vhp.timestamp ? -1 : this.timestamp > vhp.timestamp ? 1 : 0;
    }
}
