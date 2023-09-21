package com.example.databaseproject22;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Train {
    int train_id;
    String train_name;
    String train_type;
    int train_capacity;
    boolean train_Avaliable;
    int adminID;

    public Train() {
    }

    public int getTrain_id() {
        return this.train_id;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    public String getTrain_name() {
        return this.train_name;
    }

    public void setTrain_name(String train_name) {
        this.train_name = train_name;
    }

    public String getTrain_type() {
        return this.train_type;
    }

    public void setTrain_type(String train_type) {
        this.train_type = train_type;
    }

    public int getTrain_capacity() {
        return this.train_capacity;
    }

    public void setTrain_capacity(int train_capacity) {
        this.train_capacity = train_capacity;
    }

    public boolean isTrain_Avaliable() {
        return this.train_Avaliable;
    }

    public void setTrain_Avaliable(boolean train_Avaliable) {
        this.train_Avaliable = train_Avaliable;
    }

    public int getAdminID() {
        return this.adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public boolean addTrain(int id, Train tr) {
        try {
            Connection conn = null;
            String sql = "INSERT INTO Train (Train_id, Train_name, Train_type, Train_Available, AdminAdmin_ID) VALUES (?, ?, ?, ?, ?)";
            String jdbcUrl = "jdbc:sqlserver://LAPTOP-8G6P2TIL\\MSSQLSERVER:1433;database=TrainBooking;integratedSecurity=true; encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(jdbcUrl);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, tr.getTrain_id());
            stmt.setString(2, tr.getTrain_name());
            stmt.setString(3, tr.getTrain_type());
            stmt.setBoolean(4, tr.isTrain_Avaliable());
            stmt.setInt(5, tr.getAdminID());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
        catch (Exception var8)
        {
            System.out.println(var8);
            return false;
        }
    }

    public boolean UpdateTrain(int TrID, int AdmID, Train Tr) {
        try {
            Connection conn = null;
            String sql = "UPDATE Train SET Train_name = ?, Train_type = ?,  Train_Available = ? WHERE Train_id = ? and AdminAdmin_ID = ? ";
            String jdbcUrl = "jdbc:sqlserver://LAPTOP-8G6P2TIL\\MSSQLSERVER:1433;database=TrainBooking;integratedSecurity=true; encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(jdbcUrl);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Tr.getTrain_name());
            stmt.setString(2, Tr.getTrain_type());
            stmt.setBoolean(3, Tr.isTrain_Avaliable());
            stmt.setInt(4, TrID);
            stmt.setInt(5, AdmID);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception var9) {
            System.out.println(var9);
            return false;
        }
    }
}
