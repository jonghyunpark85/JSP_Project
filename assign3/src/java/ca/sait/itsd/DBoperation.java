/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.itsd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DBoperation.java
 *
 * @author Jonghyun Park
 * @version Nov 22, 2020
 */
public class DBoperation {

    // Connect to DB
    private Connection getConnection() {

        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String connectionString = "jdbc:mysql://localhost:3306/assign3db";
            String username = "root";
            String password = "password";

            conn = DriverManager.getConnection(connectionString, username, password);
        } catch (SQLException e) {
            for (Throwable t : e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBoperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    // Get userList from ArrayList
    public ArrayList<User> getUserList() {

        ArrayList<User> userList = new ArrayList<>();
        String sql = "select * from users;";

        try {

            Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                userList.add(user);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException e) {
            for (Throwable t : e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

    // Get noteList from ArrayList by 
    public ArrayList<Note> getNoteList() {

        ArrayList<Note> noteList = new ArrayList<>();
        String sql = "select * from notes;";

        try {

            Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Note note = new Note();
                note.setNoteID(rs.getString(1));
                note.setfKusername(rs.getString(2));
                note.setNote(rs.getString(3));
                note.setNoteDateTime(rs.getString(4));
                noteList.add(note);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException e) {
            for (Throwable t : e) {
                e.printStackTrace();
            }
        }
        return noteList;
    }

    // Add new user to userList
    public boolean addUserList(String username, String password) {

        boolean result = false;
        String sql = "insert into users set username=?, password=?;";
        try {

            Connection conn = getConnection();
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

    // Add new note to noteList
    public boolean addNoteList(String fk_username, String note) {

        boolean result = false;
        String sql = "insert into notes set fk_username=?, note=?;";
        try {

            Connection conn = getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, fk_username);
            st.setString(2, note);

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

    // Delete existing note from noteList
    public boolean deleteNoteList(String noteID) {

        boolean result = false;
        String sql = "delete from notes where noteID=?;";
        try {

            Connection conn = getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, noteID);

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
