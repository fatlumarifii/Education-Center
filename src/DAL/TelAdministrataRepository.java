/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.TelAdministrata;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Fatlum
 */
public class TelAdministrataRepository extends EntMngClass implements TelAdministrataInterface{
     public void create(TelAdministrata useri) throws TelAdministrataException {
        try {
            em.getTransaction().begin();
            em.persist(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {

            if (thro.getMessage().contains("2627")) {

                throw new TelAdministrataException("E dhëna egziston në databazë!");
            } else {
                throw new TelAdministrataException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public void edit(TelAdministrata useri) throws TelAdministrataException {
        try {
            em.getTransaction().begin();
            em.merge(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                throw new TelAdministrataException("E dhëna egziston");
            } else {
                throw new TelAdministrataException("Update: " + thro.getClass() + " - " + thro.getMessage());
            }

        }
    }
    
    public void remove(TelAdministrata useri) throws TelAdministrataException {
        try {
            em.getTransaction().begin();
            em.remove(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("547")) {
                throw new TelAdministrataException("E dhëna është përdorur, nuk mund ta fshini!!");
            } else {
                throw new TelAdministrataException("Remove: " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
     @Override
    public List<TelAdministrata> findByAdministrataId(int adminID) throws TelAdministrataException {
        Query query = em.createQuery("SELECT t FROM TelAdministrata t WHERE t.administrataID.adminstrataID = :adminID");
        query.setParameter("adminID", adminID);
        try {
            return  query.getResultList();
        } catch (NoResultException nre) {
            throw new TelAdministrataException("E dhëna nuk egziston!");
        }
    }
    public List<TelAdministrata> findAll() {
        return em.createNamedQuery("TelAdministrata.findAll").getResultList();
    }
    
  
    
    public TelAdministrata findById(int perdoruesiID) throws TelAdministrataException {
        Query query = em.createQuery("SELECT t FROM TelAdministrata t WHERE t.nrTelefonitID = :perdoruesiID");
        query.setParameter("perdoruesiID", perdoruesiID);
        try {
            return (TelAdministrata) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new TelAdministrataException("E dhëna nuk egziston!");
        }
    }
}


