/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.TelProfesori;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Fatlum
 */
public class TelProfesoriRepository  extends EntMngClass implements TelProfesoriInterface{
     public void create(TelProfesori useri) throws TelProfesoriException {
        try {
            em.getTransaction().begin();
            em.persist(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {

            if (thro.getMessage().contains("2627")) {

                throw new TelProfesoriException("E dhëna egziston në databazë!");
            } else {
                throw new TelProfesoriException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public void edit(TelProfesori useri) throws TelProfesoriException {
        try {
            em.getTransaction().begin();
            em.merge(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                throw new TelProfesoriException("E dhëna egziston");
            } else {
                throw new TelProfesoriException("Update: " + thro.getClass() + " - " + thro.getMessage());
            }

        }
    }
    
    public void remove(TelProfesori useri) throws TelProfesoriException {
        try {
            em.getTransaction().begin();
            em.remove(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("547")) {
                throw new TelProfesoriException("E dhëna është përdorur, nuk mund ta fshini!!");
            } else {
                throw new TelProfesoriException("Remove: " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public List<TelProfesori> findAll() {
        return em.createNamedQuery("TelProfesori.findAll").getResultList();
    }
    
    public List<TelProfesori> findByProfesoriId(int studentiID) throws TelProfesoriException {
        Query query = em.createQuery("SELECT t FROM TelProfesori t WHERE t.profesoriID.profesoriID = :studentiID");
        query.setParameter("studentiID", studentiID);
        try {
            return query.getResultList();
        } catch (NoResultException nre) {
            throw new TelProfesoriException("E dhëna nuk egziston!");
        }
    }
    public TelProfesori findById(int perdoruesiID) throws TelProfesoriException {
        Query query = em.createQuery("SELECT t FROM TelProfesori t WHERE t.nrTelefonitID = :perdoruesiID");
        query.setParameter("perdoruesiID", perdoruesiID);
        try {
            return (TelProfesori) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new TelProfesoriException("E dhëna nuk egziston!");
        }
    }
}

