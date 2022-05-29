/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Localidad;

/**
 *
 * @author maral
 */
@Stateless
public class LocalidadFacade extends AbstractFacade<Localidad> implements LocalidadFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LocalidadFacade() {
        super(Localidad.class);
    }
    
    @Override
    public List<Localidad> obtenerLocalidades(){
        List<Localidad> localidades = new ArrayList();
        Localidad l = null;
        String consulta = "SELECT * FROM Localidad";
        Query query = em.createQuery(consulta);
        List<Localidad> resultado = query.getResultList();
        return resultado;
    }
}
