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
 * AdminServices.java
 * 
 * @author Jonghyun Park
 * @version Dec 7, 2020
 */
@WebServlet(name = "AdminServices", urlPatterns = {"/AdminServices"})
public class AdminServices extends HttpServlet {

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
        
        // Attributes
        String username = request.getParameter("username");
        String usertype = request.getParameter("usertype");
        
        // Attributes for action
        String delete = request.getParameter("delete");
        String toggle = request.getParameter("toggle");
        String reset = request.getParameter("reset");
        
        // Attributes
        DBoperations dbOps = new DBoperations();
        ArrayList<User> userList = new ArrayList<User>();
        ArrayList<User> adminUser = new ArrayList<User>();
        userList = dbOps.getUserList();
        
        // Action
        if (username != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userList", userList);

            //Toggle usertype
            if (toggle != null) {
                session.setAttribute("adminUser", adminUser);
                
                // Admin User List
                adminUser = dbOps.adminUser();

                if (adminUser.size() == 1) {
                    if (usertype.equals("Normal")) {
                        dbOps.setAdminUser(username);
                        userList = dbOps.getUserList();
                        session.setAttribute("userList", userList);
                        request.setAttribute("message", "Normal user changed to Admin user!!");
                        request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
                    } else {
                        request.setAttribute("message", "You can not change to Normal user!!");
                        request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
                    }
                } else {
                    if (usertype.equals("Normal")) {
                        dbOps.setAdminUser(username);
                        userList = dbOps.getUserList();
                        session.setAttribute("userList", userList);
                        request.setAttribute("message", "Normal user changed to Admin user!!");
                        request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
                    } else if (usertype.equals("Admin")) {
                        dbOps.setNormalUser(username);
                        userList = dbOps.getUserList();
                        session.setAttribute("userList", userList);
                        request.setAttribute("message", "Admin user changed to Normal user!!");
                        request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
                    }
                }
            }

            // Delete user
            if (delete != null) {
                session.setAttribute("adminUser", adminUser);
                
                // Admin User List
                adminUser = dbOps.adminUser();

                if (adminUser.size() == 1) {
                    if (usertype.equals("Admin")) {
                        request.setAttribute("message", "You do not delete this Admin user!!");
                        request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
                    }
                } else {
                    dbOps.deleteUsername(username);
                    userList = dbOps.getUserList();
                    session.setAttribute("userList", userList);
                    request.setAttribute("message", "You have successfully deleted a user!!");
                    request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
                }
            }

            // Reset Password
            if (reset != null) {
                dbOps.resetPassword(username);
                userList = dbOps.getUserList();
                session.setAttribute("userList", userList);
                request.setAttribute("message", "Password changed successfully!");
                request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
            }
            request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
        }
        request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
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
