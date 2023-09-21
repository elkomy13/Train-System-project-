package com.example.databaseproject22;

import java.sql.*;

public class Trip {
    int trip_ID;
    String trip_date;
    int available_seats;
    int trip_Capacity;
    String Arrival_time;
    String Departure_time;
    float trip_PRICE;
    String Source;
    String Destination;
    int adminID;
    int trainID;
    boolean Train_Avaliable;

    public Trip() {
    }

    public int getAvailable_seats() {
        return this.available_seats;
    }

    public void setAvailable_seats(int available_seats) {
        this.available_seats = available_seats;
    }

    public void setTrip_ID(int trip_ID) {
        this.trip_ID = trip_ID;
    }

    public void setTrip_date(String trip_date) {
        this.trip_date = trip_date;
    }

    public void setTrip_Capacity(int trip_Capacity) {
        this.trip_Capacity = trip_Capacity;
    }

    public void setArrival_time(String arrival_time) {
        this.Arrival_time = arrival_time;
    }

    public void setDeparture_time(String departure_time) {
        this.Departure_time = departure_time;
    }

    public void setTrip_PRICE(float trip_PRICE) {
        this.trip_PRICE = trip_PRICE;
    }

    public void setSource(String source) {
        this.Source = source;
    }

    public void setDestination(String destination) {
        this.Destination = destination;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public void setTrainID(int trainID) {
        this.trainID = trainID;
    }

    public void setTrain_Avaliable(boolean train_Avaliable) {
        this.Train_Avaliable = train_Avaliable;
    }

    public int getTrip_ID() {
        return this.trip_ID;
    }

    public String getTrip_date() {
        return this.trip_date;
    }

    public int getTrip_Capacity() {
        return this.trip_Capacity;
    }

    public String getArrival_time() {
        return this.Arrival_time;
    }

    public String getDeparture_time() {
        return this.Departure_time;
    }

    public float getTrip_PRICE() {
        return this.trip_PRICE;
    }

    public String getSource() {
        return this.Source;
    }

    public String getDestination() {
        return this.Destination;
    }

    public int getAdminID() {
        return this.adminID;
    }

    public int getTrainID() {
        return this.trainID;
    }

    public boolean isTrain_Avaliable() {
        return this.Train_Avaliable;
    }

    public boolean AddTrip(int ID, int tr_id, Trip trip) {
        try {
            Connection conn = null;
            String sql = "INSERT INTO Trip (Trip_id, Trip_date,Available_Seats , Trip_Available, Trip_Price,  Destination, Arrival_time,Departure_time, Source, AdminAdmin_ID,TrainTrain_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String jdbcUrl = "jdbc:sqlserver://LAPTOP-8G6P2TIL\\MSSQLSERVER:1433;database=TrainBooking;integratedSecurity=true; encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(jdbcUrl);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, trip.getTrip_ID());
            stmt.setString(2, trip.getTrip_date());
            stmt.setInt(3, trip.getAvailable_seats());
            stmt.setBoolean(4, trip.isTrain_Avaliable());
            stmt.setFloat(5, trip.getTrip_PRICE());
            stmt.setString(6, trip.getDestination());
            stmt.setString(7, trip.getArrival_time());
            stmt.setString(8, trip.getDeparture_time());
            stmt.setString(9, trip.getSource());
            stmt.setInt(10, trip.getAdminID());
            stmt.setInt(11, trip.getTrainID());
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {//add to route table the data
                System.out.println("A new Trip was inserted successfully!");
                String sql2 = "INSERT INTO Route (Arrival_time, Depature_time, Stop_no , Trip_id) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt2 = conn.prepareStatement(sql2);
                int Stop_no = 3;
                stmt2.setString(1, trip.getArrival_time());
                stmt2.setString(2, trip.getDeparture_time());
                stmt2.setInt(3, Stop_no);
                stmt2.setInt(4, trip.getTrip_ID());
                int rowsInserted2 = stmt2.executeUpdate();
                return true;
            } else {
                return false;
            }
        } catch (Exception var9) {
            System.out.println(var9);
            return false;
        }
    }

    public boolean UpdateTrip(int Admid, int Train_ID, Trip T) {
        try {
            System.out.println("UPDATE TRIP");
            Connection conn = null;
            String sql = "UPDATE Trip SET  Trip_date = ?, Trip_Available = ?, Trip_Price = ?,  Destination = ?, Arrival_time = ?,Departure_time = ?, Source = ?, TrainTrain_id = ? WHERE Trip_id = ? and AdminAdmin_ID = ? and TrainTrain_id = ?";
            String jdbcUrl = "jdbc:sqlserver://LAPTOP-8G6P2TIL\\MSSQLSERVER:1433;database=TrainBooking;integratedSecurity=true; encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(jdbcUrl);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, T.getTrip_date());
            stmt.setBoolean(2, T.isTrain_Avaliable());
            stmt.setFloat(3, T.getTrip_PRICE());
            stmt.setString(4, T.getDestination());
            stmt.setString(5, T.getArrival_time());
            stmt.setString(6, T.getDeparture_time());
            stmt.setString(7, T.getSource());
            stmt.setInt(8, T.getTrainID());
            stmt.setInt(9, T.getTrip_ID());
            stmt.setInt(10, Admid);
            stmt.setInt(11, Train_ID);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception var9)
        {
            System.out.println(var9);
            return false;
        }

    }
    public boolean DeleteTrip(int AdminId, int Trip_ID){
        try {
            Connection conn = null;
            String sql = "DELETE FROM Trip WHERE Trip_id = ? AND AdminAdmin_ID = ?";
            String jdbcUrl = "jdbc:sqlserver://LAPTOP-8G6P2TIL\\MSSQLSERVER:1433;database=TrainBooking;integratedSecurity=true; encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(jdbcUrl);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Trip_ID);
            stmt.setInt(2, AdminId);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception var9)
        {
            System.out.println(var9);
            return false;
        }
    }

    public boolean getAvailableSeats(String date, String time, String source, String destination) {
        try {
            Connection conn = null;
            String sql = "SELECT Train_id, Available_Seats FROM Trip INNER JOIN Train ON Train.Train_id = Trip.TrainTrain_id WHERE Trip.Trip_date = ? AND Trip.Departure_time >= ? AND Trip.Source = ? AND Trip.Destination = ? AND Trip.Trip_Available = 1 AND Train.Train_Available = 1 AND Train.Train_Capacity >= ?";
            String jdbcUrl = "jdbc:sqlserver://LAPTOP-8G6P2TIL\\MSSQLSERVER:1433;database=TrainBooking;integratedSecurity=true; encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(jdbcUrl);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, date);
            stmt.setString(2, time);
            stmt.setString(3, source);
            stmt.setString(4, destination);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                int trainId = rs.getInt("Train_id");
                int availableSeats = rs.getInt("Available_Seats");
                System.out.println("Train ID: " + trainId + ", Available Seats: " + availableSeats);
            }
            return true;
        } catch (Exception var13) {
            System.out.println(var13);
            return false;
        }

    }
}
