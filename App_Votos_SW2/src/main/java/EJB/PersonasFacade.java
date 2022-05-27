/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Personas;

/**
 *
 * @author maral
 */
@Stateless
public class PersonasFacade extends AbstractFacade<Personas> implements PersonasFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonasFacade() {
        super(Personas.class);
    }
    
    @Override
    public boolean existe(String DNI){
        String consulta = "FROM Personas p WHERE p.DNI=:param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", DNI);
        
        List<Personas> resultado = query.getResultList();
        
        return (resultado.size() != 0);
    }
    @Override
    public Personas getPersonaDNI(String DNI){
        String consulta = "FROM Personas p WHERE p.DNI=:param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", DNI);
        
        List<Personas> resultado = query.getResultList();
        
        return resultado.get(0);
    }
}
