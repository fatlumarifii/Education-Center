/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.FaturaDetaje;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Arbër Suka
 */
public class FaturaDetajeRepository extends EntMngClass implements FaturaDetajeInterface{

    public void create(FaturaDetaje f) throws FaturaDetajeException {
        try {
            em.getTransaction().begin();
            em.persist(f);
            em.getTransaction().commit();
        } catch (Throwable thro) {

            if (thro.getMessage().contains("2627")) {

                throw new FaturaDetajeException("E dhëna egziston në databazë!");
            } else {
                throw new FaturaDetajeException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    @Override
    public void edit(FaturaDetaje f) throws FaturaDetajeException {
        try {
            em.getTransaction().begin();
            em.merge(f);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                throw new FaturaDetajeException("E dhëna egziston");
            } else {
                throw new FaturaDetajeException("Update: " + thro.getClass() + " - " + thro.getMessage());
            }

        }
    }
    
    public void remove(FaturaDetaje f) throws FaturaDetajeException {
        try {
            em.getTransaction().begin();
            em.remove(f);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("547")) {
                throw new FaturaDetajeException("E dhëna është përdorur, nuk mund ta fshini!!");
            } else {
                throw new FaturaDetajeException("Remove: " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public List<FaturaDetaje> findAll() {
        return em.createNamedQuery("FaturaDetaje.findAll").getResultList();
    }
    
    
    
    public FaturaDetaje findById(int perdoruesiID) throws FaturaDetajeException {
        Query query = em.createQuery("SELECT p FROM FaturaDetaje p WHERE p.fdId = :perdoruesiID");
        query.setParameter("perdoruesiID", perdoruesiID);
        try {
            return (FaturaDetaje) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new FaturaDetajeException("E dhëna nuk egziston!");
        }
    }
}


