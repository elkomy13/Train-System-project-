package com.example.databaseproject22;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Admin {
    private int admin_id;
    private String fname;
    private String lname;
    private String email;
    private String password;

    public Admin() {
    }

    public Admin(int admin_id, String fname, String lname, String email, String password) {
        this.admin_id = admin_id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
    }

    public int getAdmin_id() {
        return this.admin_id;
    }

    public String getFname() {
        return this.fname;
    }

    public String getLname() {
        return this.lname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean addAdmin(Admin user) {
        try {
            Connection conn = null;
            String sql = "INSERT INTO Admin (Admin_ID, Email, Password, Fname, Lname) VALUES (?, ?, ?, ?, ?)";
            String jdbcUrl = "jdbc:sqlserver://LAPTOP-8G6P2TIL\\MSSQLSERVER:1433;database=TrainBooking;integratedSecurity=true; encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(jdbcUrl);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, user.getAdmin_id());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getFname());
            stmt.setString(5, user.getLname());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0 ? true : false;
        } catch (Exception var7) {
            System.out.println(var7);
            return false;
        }
    }

    public boolean isAdminExists(int adminId, String password) {
        try {
            Connection conn = null;
            String sql = "SELECT COUNT(*) FROM Admin WHERE Admin_ID = ? AND Password = ?";
            String jdbcUrl = "jdbc:sqlserver://LAPTOP-8G6P2TIL\\MSSQLSERVER:1433;database=TrainBooking;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(jdbcUrl);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, adminId);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (Exception var9) {
            System.out.println(var9);
        }

        return false;
    }

    public boolean Update_adminData(int adminId, Admin ad) {
        try {
            Connection conn = null;
            String sql = "UPDATE Admin SET Fname = ?, Lname = ?, Email = ?, Password = ? WHERE Admin_ID = ?";
            String jdbcUrl = "jdbc:sqlserver://LAPTOP-8G6P2TIL\\MSSQLSERVER:1433;database=TrainBooking;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(jdbcUrl);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ad.getFname());
            stmt.setString(2, ad.getLname());
            stmt.setString(3, ad.getEmail());
            stmt.setString(4, ad.getPassword());
            stmt.setInt(5, ad.getAdmin_id());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception var8) {
            System.out.println(var8);
            return false;
        }
    }

}
