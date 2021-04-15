/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.itsd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author john
 */
@WebServlet(name = "NoteOps", urlPatterns = {"/NoteOps"})
public class NoteOps extends HttpServlet {

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
        
        String newNote = request.getParameter("newnote");
        String deleteNote = request.getParameter("deletenote");
        
        //Add new note to session-level data structure
        if (newNote!=null) {
            if (!newNote.equals("")) {
                HttpSession session = request.getSession();
                ArrayList<Note> noteList = (ArrayList<Note>)session.getAttribute("noteList");
                
                noteList.add(new Note(newNote, new Date().toString()));
                
                request.setAttribute("message", "New note added");
                request.getRequestDispatcher("WEB-INF/notes.jsp").forward(request, response);
            }
            else {
                request.setAttribute("message", "Note text is required!");
                request.getRequestDispatcher("WEB-INF/notes.jsp").forward(request, response);
            }
        }
        //Delete existing note
        else if (deleteNote!=null) {
            HttpSession session = request.getSession();
            ArrayList<Note> noteList = (ArrayList<Note>)session.getAttribute("noteList");
            
            noteList.remove(Integer.parseInt(deleteNote));
            
            request.setAttribute("message", "Note deleted");
            request.getRequestDispatcher("WEB-INF/notes.jsp").forward(request, response);
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
