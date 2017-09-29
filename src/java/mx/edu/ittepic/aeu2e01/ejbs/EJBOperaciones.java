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
import mx.edu.ittepic.aeu2e01.utils.Message;
import mx.edu.ittepic.aeu2e01.entities.Rol;

/**
 *
 * @author Jorge Arellano
 */
@Stateless
public class EJBOperaciones {

 // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext
    private EntityManager em;
    
    public String getRoles(){
        Query q;
        List<Rol> listRoles;
        
        q = em.createNamedQuery("Rol.findAll");
        
        listRoles = q.getResultList();
        
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        
        String result = gson.toJson(listRoles);
        
        return result;
    }
    
    public String getRolById(int rolid){
        String result = "";
        Query q;
        Rol rol;
        Message m = new Message();
        q = em.createNamedQuery("Rol.findByRolid").setParameter("rolid", rolid);
        GsonBuilder builder = new GsonBuilder();
       Gson gson = builder.create();
       try{ 
       rol = (Rol) q.getSingleResult();
       m.setCode(HttpServletResponse.SC_OK);
       m.setMessage("La consulta se ejecutó correctamente");
       m.setDetail(gson.toJson(rol));
       }catch(NoResultException e){
           m.setCode(HttpServletResponse.SC_BAD_REQUEST);
           m.setMessage("No se encontraron resultados");
           m.setDetail(e.toString());
       }
       catch(NonUniqueResultException e){
           m.setCode(HttpServletResponse.SC_BAD_REQUEST);
           m.setMessage("Se encontró más de un resultado");
           m.setDetail(e.toString());
       }
       
       
       result = gson.toJson(m);
       return result;
    }
    
    public String createRol(String rolename){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Message m = new Message();
        Rol r = new Rol();
        r.setRolname(rolename);
        try{
        em.persist(r); //Crea el registro
        em.flush(); //Obligar al contenedor a guardar en la BD
        m.setCode(HttpServletResponse.SC_OK);
        m.setMessage("El rol se creó correctamente con la clave "+r.getRolid());
        m.setDetail(gson.toJson(r));
        }catch(EntityExistsException e){
            m.setCode(HttpServletResponse.SC_BAD_REQUEST);
            m.setMessage("No se pudo guardar el rol, intente de nuevo");
            m.setDetail(e.toString());
        }
        
        return gson.toJson(m);
    
    }
    
    public String updateRol(int rolid, String rolename){
        
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Message m = new Message();
        Rol r = new Rol();
        Query q = em.createNamedQuery("Rol.findByRolid").setParameter("rolid", rolid);
        try{
            r = (Rol)q.getSingleResult();
            r.setRolname(rolename);  
            try{
                em.persist(r); //Crea el registro
                em.flush(); //Obligar al contenedor a guardar en la BD
                m.setCode(HttpServletResponse.SC_OK);
                m.setMessage("El rol se actualizó correctamente con la clave "+r.getRolid()+"y el nombre "+r.getRolname());
                m.setDetail(gson.toJson(r));
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
    
    public String deleteRol(int roleid){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Message m = new Message();
        Rol r;
        
        try{
            r = em.find(Rol.class, roleid);
            em.remove(r);
            m.setCode(HttpServletResponse.SC_OK);
            m.setMessage("El rol de eliminó correctamente");
            m.setDetail(gson.toJson(r));
            return gson.toJson(m);
        }catch(IllegalArgumentException e){
            m.setCode(HttpServletResponse.SC_BAD_REQUEST);
            m.setMessage("No se pudo eliminar el rol, intente de nuevo");
            m.setDetail(e.toString());
            return gson.toJson(m);
        }
    }
}