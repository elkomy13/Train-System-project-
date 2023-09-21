package com.example.databaseproject22;

import java.sql.*;

public class Route {
    String Arrival_time;
    String Departure_time;
    int stop_num;

    public Route() {
    }

    public String getArrival_time() {
        return this.Arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.Arrival_time = arrival_time;
    }

    public String getDeparture_time() {
        return this.Departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.Departure_time = departure_time;
    }

    public int getStop_num() {
        return this.stop_num;
    }

    public void setStop_num(int stop_num) {
        this.stop_num = stop_num;
    }


}
