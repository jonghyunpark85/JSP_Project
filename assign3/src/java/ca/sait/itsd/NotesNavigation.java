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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * NotesNavigation.java
 * 
 * @author john, Jonghyun Park
 * @version Nov 22, 2020
 */
@WebServlet(name = "NotesNavigation", urlPatterns = {"/NotesNavigation"})
public class NotesNavigation extends HttpServlet {

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

        //String page=(String) request.getAttribute("page");
        String page = request.getParameter("page");
        String register = request.getParameter("register");
        String cookieSet = request.getParameter("cookieset");

        //See if application-level user list is created, if not create it.
        ServletContext application = request.getServletContext();
        if (application.getAttribute("userList") == null) {
            application.setAttribute("userList", new ArrayList<User>());
        }
        
        //Go to user registeration page
        if (register != null) {
            request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
        } //If colour cookie was set, go back to notes.jsp
        else if (cookieSet != null) {
            request.setAttribute("message", "Background colour set");
            request.getRequestDispatcher("WEB-INF/notes.jsp").forward(request, response);
        } //No page specfified so go to login
        else if (page == null || page.equals("")) {
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
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
