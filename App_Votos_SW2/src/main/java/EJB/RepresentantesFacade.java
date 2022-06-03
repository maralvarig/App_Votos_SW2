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
import modelo.Partidos;
import modelo.Representantes;

/**
 *
 * @author maral
 */
@Stateless
public class RepresentantesFacade extends AbstractFacade<Representantes> implements RepresentantesFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RepresentantesFacade() {
        super(Representantes.class);
    }
    
    @Override
    public List<Representantes> encontrarRepresentantes(Partidos partido){
        System.out.println("PARTIDO:"+partido.getNombre());
        String consulta = "FROM Representantes r WHERE r.Partidos_idPartidos=:param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", partido);
        
        List<Representantes> listaRepresentantes = query.getResultList();
        return listaRepresentantes;
    }
    
}
