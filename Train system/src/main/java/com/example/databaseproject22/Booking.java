package com.example.databaseproject22;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Booking {
    int customerID;
    int Ticket_num;
    int seat_num;
    int tripID;

    public Booking() {
    }

    public int getSeat_num() {
        return this.seat_num;
    }

    public void setSeat_num(int seat_num) {
        this.seat_num = seat_num;
    }

    public int getTripID() {
        return this.tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public int getCustomerID() {
        return this.customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getTicket_num() {
        return this.Ticket_num;
    }

    public void setTicket_num(int ticket_num) {
        this.Ticket_num = ticket_num;
    }

    public boolean bookTicket(Booking b, int Trip_ID, int seat_no, int Customer_ID) {
        try {
            Connection conn = null;
            String sql = "INSERT INTO Booking (Customer_ID, Ticket_number, Seat_number, Trip_id) " +
                    "VALUES (?, ?, ?, ?) ";
            String jdbcUrl = "jdbc:sqlserver://LAPTOP-8G6P2TIL\\MSSQLSERVER:1433;database=TrainBooking;integratedSecurity=true; encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(jdbcUrl);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Customer_ID);
            stmt.setInt(2, b.getTicket_num());
            stmt.setInt(3, seat_no);
            stmt.setInt(4, Trip_ID);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {//when i book a ticket update trip table to reduce the number of available seats
                String sql2 = "UPDATE Trip SET Available_seats = Available_seats - 1 WHERE Trip_id = ?";
                PreparedStatement stmt2 = conn.prepareStatement(sql2);
                stmt2.setInt(1, Trip_ID);
                int rowsUpdated = stmt2.executeUpdate();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean CancelBooking(Booking b2, int Trip_ID,int tckt_num, int customerID) {
        try {
            Connection conn = null;
            String sql = "DELETE FROM Booking WHERE Ticket_number = ? AND Customer_ID = ?";
            String jdbcUrl = "jdbc:sqlserver://LAPTOP-8G6P2TIL\\MSSQLSERVER:1433;database=TrainBooking;integratedSecurity=true; encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(jdbcUrl);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, tckt_num);
            stmt.setInt(2, customerID);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {//when i book a ticket update trip table to add the number of available seats
                String sql2 = "UPDATE Trip SET Available_seats = Available_seats + 1 WHERE Trip_id = ?";
                PreparedStatement stmt2 = conn.prepareStatement(sql2);
                stmt2.setInt(1, Trip_ID);
                int rowsUpdated = stmt2.executeUpdate();
                return true;
            } else {
                return false;
            }
        } catch (Exception var9) {
            System.out.println(var9);
            return false;
        }

    }



}