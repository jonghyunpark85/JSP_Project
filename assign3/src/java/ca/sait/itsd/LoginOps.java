/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.itsd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * LoginOps.java
 *
 * @author john, Jonghyun Park
 * @version Nov 22, 2020
 */
@WebServlet(name = "LoginOps", urlPatterns = {"/LoginOps"})
public class LoginOps extends HttpServlet {

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

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String newUsername = request.getParameter("newusername");
        String newPassword = request.getParameter("newpassword");

        String colour = request.getParameter("colour");

        String logout = request.getParameter("logout");

        DBoperation dbOps = new DBoperation();
        ArrayList<User> userList = new ArrayList<>();

        //Log in user
        if (username != null && password != null) {
            if (!username.equals("") && !password.equals("")) {
                boolean found = false;

                userList = dbOps.getUserList();

                for (User u : userList) {
                    if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    request.setAttribute("username", username);
                    request.getRequestDispatcher("NoteOps").forward(request, response);
                } else {
                    request.setAttribute("message", "Invalid username or password!");
                    request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
                }

            } else {
                request.setAttribute("message", "Both username and password are required!");
                request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
            }
        }
        
        //Logout user
        else if (logout != null) {
            request.setAttribute("message", "Logged out");
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        } 

        //Register New User
        else if (newUsername != null && newPassword != null) {
            if (!newUsername.equals("") && !newPassword.equals("")) {

                boolean found = false;

                userList = dbOps.getUserList();

                for (User u : userList) {
                    if (u.getUsername().equals(newUsername)) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    request.setAttribute("message", "Username is already taken, please select a different username");
                    request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
                } else {
                    if (dbOps.addUserList(newUsername, newPassword)) {
                        request.setAttribute("message", "User '" + newUsername + "' created successfully");
                        request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
                    }
                }
            } else {
                request.setAttribute("message", "Both username and password are required");
                request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
            }
        } 

        //Set colour cookie
        else if (colour != null) {
            Cookie cookie = new Cookie("colour", colour);
            cookie.setMaxAge(3600);
            response.addCookie(cookie);

            //Have to redirect here to see change immediately (send new cookie to browser)
            response.sendRedirect("NotesNavigation?cookieset=true");
        }

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
