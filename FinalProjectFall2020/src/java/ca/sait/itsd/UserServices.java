/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.itsd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * UserServices.java
 * 
 * @author Jonghyun Park
 * @version Dec 7, 2020
 */
@WebServlet(name = "UserServices", urlPatterns = {"/UserServices"})
public class UserServices extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Attribute for Login Page
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String usertype = "";

        // Attribute for Register Page
        String newUsername = request.getParameter("newUsername");
        String newPassword = request.getParameter("newPassword");
        String conPassword = request.getParameter("conPassword");

        // Attribute for Set Password 
        String setpassword = request.getParameter("setpassword");
        String setPass = request.getParameter("setPass");
        String conPass = request.getParameter("conPass");

        // Attribute for Logout
        String logout = request.getParameter("logout");

        // Attributes
        DBoperations dbOps = new DBoperations();
        ArrayList<User> userList = new ArrayList<User>();

        // Login User
        if (username != null && password != null) {
            if (!username.equals("") && !password.equals("")) {

                boolean found = false;

                userList = dbOps.getUserList();

                for (User u : userList) {
                    if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                        usertype = u.getUsertype(); // 0: Normal User 1: Admin User
                        found = true;
                        break;
                    }
                }
                // User and password correct 
                if (found) {

                    // Store data in session
                    HttpSession session = request.getSession();
                    session.setAttribute("userList", userList);
                    session.setAttribute("username", username);

                    // Select Page 
                    if (usertype.equals("1")) {
                        request.getRequestDispatcher("AdminServices").forward(request, response);
                    } else {
                        request.getRequestDispatcher("WEB-INF/Normal.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("message", "Invalid username or password!");
                    request.getRequestDispatcher("WEB-INF/Login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("message", "Both username and password are required!");
                request.getRequestDispatcher("WEB-INF/Login.jsp").forward(request, response);
            }
        }

        //Logout User
        if (logout != null) {
            request.setAttribute("message", "Logged out");
            request.getRequestDispatcher("WEB-INF/Login.jsp").forward(request, response);
        }

        //Register New User
        if (newUsername != null && newPassword != null && conPassword != null) {
            if (!newUsername.equals("") && !newPassword.equals("") && !conPassword.equals("")) {

                boolean found = false;

                userList = dbOps.getUserList();

                // Search for existing user
                for (User u : userList) {
                    if (u.getUsername().equals(newUsername)) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    request.setAttribute("message", "Username is already taken, please select a different username");
                    request.getRequestDispatcher("WEB-INF/Register.jsp").forward(request, response);
                } else {

                    // Confirm to password
                    if (!newPassword.equals(conPassword)) {
                        request.setAttribute("message", "Password do not match!");
                        request.getRequestDispatcher("WEB-INF/Register.jsp").forward(request, response);
                    } else {
                        dbOps.addUserList(newUsername, newPassword);
                        request.setAttribute("message", "New account created, please log in");
                        request.getRequestDispatcher("WEB-INF/Login.jsp").forward(request, response);
                    }
                }
            } else {
                request.setAttribute("message", "All values are required!");
                request.getRequestDispatcher("WEB-INF/Register.jsp").forward(request, response);
            }
        }

        // Show Password from
        if (setpassword != null) {
            request.setAttribute("setPassword", "true"); // Able to show the form
            request.getRequestDispatcher("WEB-INF/Normal.jsp").forward(request, response);
        }

        // Set Password
        if (setPass != null && conPass != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("userList", userList);
            
            if (!setPass.equals("") && !conPass.equals("")) {
                if (!setPass.equals(conPass)) {
                    request.setAttribute("setPassword", "true"); // Able to show the form
                    request.setAttribute("message", "Password do not match!");
                    request.getRequestDispatcher("WEB-INF/Normal.jsp").forward(request, response);
                } else {

                    dbOps.setPassword(username, setPass);
                    request.setAttribute("setPassword", "false"); // Disable to Show the form
                    request.setAttribute("message", "Password Reset");
                    request.getRequestDispatcher("WEB-INF/Normal.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("setPassword", "true"); // Able to show the form
                request.setAttribute("message", "Both password values are required");
                request.getRequestDispatcher("WEB-INF/Normal.jsp").forward(request, response);
            }
        }
        request.getRequestDispatcher("WEB-INF/Normal.jsp").forward(request, response);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
