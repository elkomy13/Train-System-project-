package com.example.databaseproject22;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Customer {
    private int cust_id;
    private String fname;
    private String lname;
    private String email;
    private String password;
    private String phone;

    public Customer() {
    }

    public Customer(int custId, String firstName, String lastName, String email, String password, String phoneNumber) {
        this.cust_id = custId;
        this.fname = firstName;
        this.lname = lastName;
        this.email = email;
        this.password = password;
        this.phone = phoneNumber;
    }

    public int getCust_id() {
        return this.cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public String getFname() {
        return this.fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return this.lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean addCustomer(Customer user) {
        try {
            Connection conn = null;
            String sql = "INSERT INTO Customer (Cust_ID, Email, Password, Fname, Lname, Phone_number) VALUES (?, ?, ?, ?, ?, ?)";
            String jdbcUrl = "jdbc:sqlserver://LAPTOP-8G6P2TIL\\MSSQLSERVER:1433;database=TrainBooking;integratedSecurity=true; encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(jdbcUrl);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, user.getCust_id());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getFname());
            stmt.setString(5, user.getLname());
            stmt.setString(6, user.getPhone());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception var7) {
            System.out.println(var7);
            return false;
        }
    }

    public boolean isCustomerExists(int CustId, String password) {
        try {
            Connection conn = null;
            String sql = "SELECT COUNT(*) FROM Customer WHERE Cust_ID = ? AND Password = ?";
            String jdbcUrl = "jdbc:sqlserver://LAPTOP-8G6P2TIL\\MSSQLSERVER:1433;database=TrainBooking;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(jdbcUrl);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, CustId);
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

    public boolean Update_Customer(int Customer_ID, Customer c) {
        try {
            Connection conn = null;
            String sql = "UPDATE Customer SET Fname = ?, Lname = ?, Email = ?, Password = ? ,Phone_number = ? WHERE Cust_ID = ?";
            String jdbcUrl = "jdbc:sqlserver://LAPTOP-8G6P2TIL\\MSSQLSERVER:1433;database=TrainBooking;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(jdbcUrl);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, c.getFname());
            stmt.setString(2, c.getLname());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getPassword());
            stmt.setString(5, c.getPhone());
            stmt.setInt(6, Customer_ID);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception var8) {
            System.out.println(var8);
            return false;
        }
    }

}
