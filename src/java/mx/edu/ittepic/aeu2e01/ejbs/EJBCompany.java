/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.aeu2e01.ejbs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;
//import mx.edu.ittepic.aeu2e01.entities.Category;
import mx.edu.ittepic.aeu2e01.entities.Company;
import mx.edu.ittepic.aeu2e01.entities.Rol;
import mx.edu.ittepic.aeu2e01.utils.Message;

/**
 *
 * @author Jorge Arellano
 */
@Stateless
public class EJBCompany {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext
    private EntityManager em;
    
    public String getCompanies(){
        Query q;
        List<Company> companies; 
        
        q = em.createNamedQuery("Company.findAll");
        companies = q.getResultList();
        
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        
        String result = gson.toJson(companies);
        return result;
       
    }
    
    public String getCompanyById(int id){
        String result = "";
        Query q;
        Company c;
        
        Message m = new Message();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        
        q = em.createNamedQuery("Company.findByIdcompany").setParameter("idcompany", id);
        
        try {
            c = (Company)q.getSingleResult();
            m.setCode(HttpServletResponse.SC_OK);
            m.setMessage("La consulta se ejecuto correctamente");
            m.setDetail(gson.toJson(c));
            
            return gson.toJson(m);
        } catch(NoResultException e){
            m.setCode(HttpServletResponse.SC_BAD_REQUEST);
            m.setMessage("No se encontraron resultados");
            m.setDetail(e.toString());
        } catch(NonUniqueResultException e){
            m.setCode(HttpServletResponse.SC_BAD_REQUEST);
            m.setMessage("Existe más de un resultado");
            m.setDetail(e.toString());
        }
        
        return gson.toJson(m);
    }
    
    public String createCompany(String companyName){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Message m = new Message();
        Company c = new Company();
        
        c.setName(companyName);
        
        try {
            
            // crear un nuevo registro en la bd
            em.persist(c);
            // obligar al conenedor a guardar en la bd
            em.flush();
            
            // guardar o actualizar
            // .merge   
            
            m.setCode(HttpServletResponse.SC_OK);
            m.setMessage("La compañía se creo correctamente con la clave " + c.getIdcompany());
            m.setDetail(gson.toJson(c));
        } catch (EntityExistsException e) {
            m.setCode(HttpServletResponse.SC_BAD_REQUEST);
            m.setMessage("No se pudo guardar el rol, intente nuevamente");
            m.setDetail(e.toString());
        }
        
        return gson.toJson(m);
    }
    
    public String updateCompany(int id,String companyName){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Message m = new Message();
        Company c = new Company();
        Query q = em.createNamedQuery("Company.findByIdcompany").setParameter("idcompany", id);
        try{
            c = (Company)q.getSingleResult();
            c.setName(companyName);  
            try{
                em.persist(c); //Crea el registro
                em.flush(); //Obligar al contenedor a guardar en la BD
                m.setCode(HttpServletResponse.SC_OK);
                m.setMessage("La compañía se actualizó correctamente con la clave "+c.getIdcompany()+"y el nombre "+c.getName());
                m.setDetail(gson.toJson(c));
            }catch(EntityExistsException e){
                m.setCode(HttpServletResponse.SC_BAD_REQUEST);
                m.setMessage("No se pudo actualizar el rol, intente de nuevo");
                m.setDetail(e.toString());
        }
        }catch(NoResultException e){
           m.setCode(HttpServletResponse.SC_BAD_REQUEST);
           m.setMessage("No se encontraron resultados");
           m.setDetail(e.toString());
       }
        return gson.toJson(m);
    }
}
