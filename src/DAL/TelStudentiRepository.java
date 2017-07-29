/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.TelStudenti;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Fatlum
 */
public class TelStudentiRepository extends EntMngClass implements TelStudentiInterface{
     public void create(TelStudenti useri) throws TelStudentiException {
        try {
            em.getTransaction().begin();
            em.persist(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {

            if (thro.getMessage().contains("2627")) {

                throw new TelStudentiException("E dhëna egziston në databazë!");
            } else {
                throw new TelStudentiException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public void edit(TelStudenti useri) throws TelStudentiException {
        try {
            em.getTransaction().begin();
            em.merge(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                throw new TelStudentiException("E dhëna egziston");
            } else {
                throw new TelStudentiException("Update: " + thro.getClass() + " - " + thro.getMessage());
            }

        }
    }
    
    public void remove(TelStudenti useri) throws TelStudentiException {
        try {
            em.getTransaction().begin();
            em.remove(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("547")) {
                throw new TelStudentiException("E dhëna është përdorur, nuk mund ta fshini!!");
            } else {
                throw new TelStudentiException("Remove: " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public List<TelStudenti> findAll() {
        return em.createNamedQuery("TelStudenti.findAll").getResultList();
    }
    
    public List<TelStudenti> findByStudentiId(int studentiID) throws TelStudentiException {
        Query query = em.createQuery("SELECT t FROM TelStudenti t WHERE t.studentiID.studentiID = :studentiID");
        query.setParameter("studentiID", studentiID);
        try {
            return query.getResultList();
        } catch (NoResultException nre) {
            throw new TelStudentiException("E dhëna nuk egziston!");
        }
    }
    
    public TelStudenti findById(int perdoruesiID) throws TelStudentiException {
        Query query = em.createQuery("SELECT t FROM TelStudenti t WHERE t.nrTelefonitID = :perdoruesiID");
        query.setParameter("perdoruesiID", perdoruesiID);
        try {
            return (TelStudenti) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new TelStudentiException("E dhëna nuk egziston!");
        }
    }
}


