/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.aeu2e01.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.edu.ittepic.aeu2e01.ejbs.EJBOperaciones;
import mx.edu.ittepic.aeu2e01.utils.Message;

/**
 *
 * @author Jorge Arellano
 */
@WebServlet(name = "ConsultaRoles", urlPatterns = {"/ConsultaRoles"})
public class ConsultaRoles extends HttpServlet {

@EJB
    private EJBOperaciones ejb;

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
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter p = response.getWriter();
        Message m = new Message();
        m.setCode(401);
        m.setMessage("Don't enter");
        m.setDetail("Not authorized method");
        GsonBuilder builder = new GsonBuilder();
        Gson  gson = builder.create();
        
        response.setStatus(401);
        p.print(gson.toJson(m));
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
        response.setContentType("application/json;charset=UTF-8");
        
        //Obligar al cliente HTTP a que no guarde el resultado de este servlet en caché
        response.setHeader("Cache-Control", "no-store");
        
        //Crear el objeto necesario para devolver la respuesta
        PrintWriter p = response.getWriter();
        
        //Devolver respuesta
        p.print(ejb.getRoles());
        
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
