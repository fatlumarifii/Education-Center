/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Gjinia;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Fatlum
 */
public class GjiniaRepository extends EntMngClass implements GjiniaInterface{
     public void create(Gjinia gjinia) throws GjiniaException {
        try {
            em.getTransaction().begin();
            em.persist(gjinia);
            em.getTransaction().commit();
        } catch (Throwable thro) {

            if (thro.getMessage().contains("2627")) {

                throw new GjiniaException("E dhëna egziston në databazë!");
            } else {
                throw new GjiniaException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public void edit(Gjinia gjinia) throws GjiniaException {
        try {
            em.getTransaction().begin();
            em.merge(gjinia);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                throw new GjiniaException("E dhëna egziston");
            } else {
                throw new GjiniaException("Update: " + thro.getClass() + " - " + thro.getMessage());
            }

        }
    }
    
    public void remove(Gjinia gjinia) throws GjiniaException {
        try {
            em.getTransaction().begin();
            em.remove(gjinia);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("547")) {
                throw new GjiniaException("E dhëna është përdorur, nuk mund ta fshini!!");
            } else {
                throw new GjiniaException("Remove: " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public List<Gjinia> findAll() {
        return em.createNamedQuery("Gjinia.findAll").getResultList();
    }
    
  
    
    public Gjinia findById(int id) throws GjiniaException {
        Query query = em.createQuery("SELECT n FROM Gjinia n WHERE n.gjiniaID = :id");
        query.setParameter("id", id);
        try {
            return (Gjinia) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new GjiniaException("E dhëna nuk egziston!");
        }
    }
}

