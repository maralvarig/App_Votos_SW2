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
import modelo.Voto;

/**
 *
 * @author maral
 */
@Stateless
public class VotoFacade extends AbstractFacade<Voto> implements VotoFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VotoFacade() {
        super(Voto.class);
    }
    
    public List<Voto> buscarVotos(Elecciones eleccion){
        String consulta = "FROM Voto v WHERE v.Elecciones_idElecciones=:param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", eleccion);
        
        List<Voto> listaVotos = query.getResultList();
        return listaVotos;
    }
    
}
