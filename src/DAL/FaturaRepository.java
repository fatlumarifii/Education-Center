/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Fatura;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Fatlum
 */
public class FaturaRepository extends EntMngClass implements FaturaInterface{
     public void create(Fatura useri) throws FaturaException {
        try {
            em.getTransaction().begin();
            em.persist(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {

            if (thro.getMessage().contains("2627")) {

                throw new FaturaException("E dhëna egziston në databazë!");
            } else {
                throw new FaturaException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public void edit(Fatura useri) throws FaturaException {
        try {
            em.getTransaction().begin();
            em.merge(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                throw new FaturaException("E dhëna egziston");
            } else {
                throw new FaturaException("Update: " + thro.getClass() + " - " + thro.getMessage());
            }

        }
    }
    
    public void remove(Fatura useri) throws FaturaException {
        try {
            em.getTransaction().begin();
            em.remove(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("547")) {
                throw new FaturaException("E dhëna është përdorur, nuk mund ta fshini!!");
            } else {
                throw new FaturaException("Remove: " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public List<Fatura> findAll() {
        return em.createNamedQuery("Fatura.findAll").getResultList();
    }
    
  
    
    public Fatura findById(int perdoruesiID) throws FaturaException {
        Query query = em.createQuery("SELECT f FROM Fatura f WHERE f.faturaID = :perdoruesiID");
        query.setParameter("perdoruesiID", perdoruesiID);
        try {
            return (Fatura) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new FaturaException("E dhëna nuk egziston!");
        }
    }
    
    public Fatura findByNumriFatures(String nrFatures) throws FaturaException {
        Query query = em.createQuery("SELECT f FROM Fatura f WHERE f.numriFatures LIKE :nrFatures");
        query.setParameter("nrFatures", nrFatures);
        try {
            return (Fatura) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new FaturaException("E dhëna nuk egziston!");
        }
    }
}

