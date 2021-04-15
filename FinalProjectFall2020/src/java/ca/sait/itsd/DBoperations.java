/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.itsd;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * DBoperations.java
 * 
 * @author Jonghyun Park
 * @version Dec 7, 2020
 */
public class DBoperations {

    public ArrayList<User> getUserList() {

        ArrayList<User> userList = new ArrayList<>();
        String sql = "select * from users;";

        ConnectionPool cp = ConnectionPool.getInstance();

        try {
            try (Connection conn = cp.getConnection()) {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    User user = new User();
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setUsertype(rs.getString("usertype"));
                    userList.add(user);
                }
                rs.close();
                st.close();
            }
        } catch (SQLException e) {
            for (Throwable t : e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

    // Add new user to userList
    public boolean addUserList(String username, String password) {

        boolean result = false;
        String sql = "insert into users set username=?, password=?;";
        try {
            ConnectionPool cp = ConnectionPool.getInstance();

            Connection conn = cp.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, username);
            st.setString(2, password);

            int rowsAffected = st.executeUpdate();

            result = (rowsAffected > 0);

            st.close();
            conn.close();

        } catch (SQLException e) {
            for (Throwable t : e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // Set Password by User
    public boolean setPassword(String username, String newPassword) {

        boolean result = false;

        String sql = "update users set password=? where username=?";

        try {
            ConnectionPool cp = ConnectionPool.getInstance();

            Connection conn = cp.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, newPassword);
            st.setString(2, username);

            int rowsAffected = st.executeUpdate();

            result = (rowsAffected > 0);

            st.close();
            conn.close();

        } catch (SQLException e) {
            for (Throwable t : e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // Store Admin User
    public ArrayList<User> adminUser() {

        ArrayList<User> adminUser = new ArrayList<>();
        String sql = "select * from users where usertype=1";

        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            Connection conn = cp.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setUsertype(rs.getString("usertype"));
                adminUser.add(user);
            }

            rs.close();
            st.close();
        } catch (SQLException e) {
            for (Throwable t : e) {
                e.printStackTrace();
            }
        }

        return adminUser;

    }

    // Toggle to Admin User
    public boolean setAdminUser(String username) {

        boolean result = false;
        String sql = "update users set usertype=1 where username=?";

        try {
            ConnectionPool cp = ConnectionPool.getInstance();

            Connection conn = cp.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, username);

            int rowsAffected = st.executeUpdate();

            result = (rowsAffected > 0);

            st.close();
            conn.close();

        } catch (SQLException e) {
            for (Throwable t : e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // Toggle to Normal User
    public boolean setNormalUser(String username) {

        boolean result = false;
        String sql = "update users set usertype=0 where username=?";

        try {
            ConnectionPool cp = ConnectionPool.getInstance();

            Connection conn = cp.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, username);

            int rowsAffected = st.executeUpdate();

            result = (rowsAffected > 0);

            st.close();
            conn.close();

        } catch (SQLException e) {
            for (Throwable t : e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // Delete User
    public boolean deleteUsername(String username) {

        boolean result = false;

        String sql = "delete from users where username=?;";

        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            Connection conn = cp.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, username);

            int rowsAffected = st.executeUpdate();

            result = (rowsAffected > 0);

            st.close();
            conn.close();

        } catch (SQLException e) {
            for (Throwable t : e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    // Reset Password 
    public boolean resetPassword(String username) {

        boolean result = false;

        String sql = "update users set password='password' where username=?";

        try {
            ConnectionPool cp = ConnectionPool.getInstance();

            Connection conn = cp.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, username);

            int rowsAffected = st.executeUpdate();

            result = (rowsAffected > 0);

            st.close();
            conn.close();

        } catch (SQLException e) {
            for (Throwable t : e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
