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
import mx.edu.ittepic.aeu2e01.ejbs.EJBCompany;
import mx.edu.ittepic.aeu2e01.utils.Message;

/**
 *
 * @author Jorge Arellano
 */
@WebServlet(name = "ConsultaCompany", urlPatterns = {"/ConsultaCompany"})
public class ConsultaCompany extends HttpServlet {
    
    @EJB
    private EJBCompany ejb;

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ConsultaCompany</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConsultaCompany at " + request.getContextPath() + "</h1>");
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
         response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-control","no-store");
        
        PrintWriter p = response.getWriter();
        Message m = new Message();
        int i = 0;
        try{
           i = Integer.parseInt(request.getParameter("id"));    
        }
        
        catch(Exception e){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            m.setCode(HttpServletResponse.SC_BAD_REQUEST);
            m.setMessage("No se encontró la compañía");
            m.setDetail(e.toString());
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            p.print(gson.toJson(m));
        }
             p.write(ejb.getCompanyById(i));
            
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
