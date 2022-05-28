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
import modelo.Elecciones;
<<<<<<< Updated upstream
import modelo.Personas;
=======
import modelo.Partidos;
>>>>>>> Stashed changes

/**
 *
 * @author maral
 */
@Stateless
public class EleccionesFacade extends AbstractFacade<Elecciones> implements EleccionesFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EleccionesFacade() {
        super(Elecciones.class);
    }
    
    @Override
    public Elecciones getElecction(int i){
        String consulta = "FROM Elecciones e WHERE e.idElecciones=:param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", i);
        
        List<Elecciones> resultado = query.getResultList();
        
        return resultado.get(0); 
    }
<<<<<<< Updated upstream
=======
    
    @Override
    public List<Elecciones> buscarElecciones(Elecciones eleccion){
        String consulta = "FROM Elecciones e WHERE e.Tipo=:param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", eleccion.getTipo());
        
        List<Elecciones> listaElecciones = query.getResultList();
        return listaElecciones;
    }

>>>>>>> Stashed changes
}
