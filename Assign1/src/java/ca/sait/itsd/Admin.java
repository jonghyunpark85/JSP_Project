/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.itsd;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jonghyun Park
 */
@WebServlet(name = "Admin", urlPatterns = {"/Admin"})
public class Admin extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Assignment 1</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Admin Page</h1>");
            out.println("<br />");
            out.println("<a href='Login?message=logout'>Logout</a>");
            out.println("<br />");
            out.println("<h2>Password Complexity Checker</h2>");
            out.println("<form action='Admin' method='POST'>");
            out.println("Enter Proposed Password: <input type='text' name='proposed'> <input type='submit' value='Check'>");
            out.println("</form>");
            out.print("Complexity: ");

            String proposed = request.getParameter("proposed");

            if (proposed.length() < 10) {
                out.print("Too weak!");
            } else {
                Boolean upperCase = false;
                Boolean lowerCase = false;
                Boolean digit = false;
                Boolean symbol = false;

                for (int i = 0; i < proposed.length(); i++) {
                    int text = (int) proposed.charAt(i);
                    if (text >= 65 && text <= 90) {
                        upperCase = true;
                    }
                    if (text >= 97 && text <= 122) {
                        lowerCase = true;
                    }
                    if (text >= 48 && text <= 57) {
                        digit = true;
                    }
                    if (text >= 33 && text <= 47) {
                        symbol = true;
                    }
                }

                if (upperCase && lowerCase && digit && symbol) {
                    out.print("Acceptable");
                } else {
                    out.print("Too weak!");
                }
            }

            out.println("</body>");
            out.println("</html>");
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
