/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Lenda;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Fatlum
 */
public class LendaRepository extends EntMngClass implements LendaInterface{
    
     public void create(Lenda useri) throws LendaException {
        try {
            em.getTransaction().begin();
            em.persist(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {

            if (thro.getMessage().contains("2627")) {

                throw new LendaException("E dhëna egziston në databazë!");
            } else {
                throw new LendaException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public void edit(Lenda useri) throws LendaException {
        try {
            em.getTransaction().begin();
            em.merge(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                throw new LendaException("E dhëna egziston");
            } else {
                throw new LendaException("Update: " + thro.getClass() + " - " + thro.getMessage());
            }

        }
    }
    
    public void remove(Lenda useri) throws LendaException {
        try {
            em.getTransaction().begin();
            em.remove(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("547")) {
                throw new LendaException("E dhëna është përdorur, nuk mund ta fshini!!");
            } else {
                throw new LendaException("Remove: " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public List<Lenda> findAll() {
        return em.createNamedQuery("Lenda.findAll").getResultList();
    }
    
  
    
    public Lenda findById(int perdoruesiID) throws LendaException {
        Query query = em.createQuery("SELECT l FROM Lenda l WHERE l.lendaID = :perdoruesiID");
        query.setParameter("perdoruesiID", perdoruesiID);
        try {
            return (Lenda) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new LendaException("E dhëna nuk egziston!");
        }
    }
    
    public List<Lenda> findByProfId(int profesiID) throws LendaException {
        Query query = em.createQuery("SELECT l FROM Lenda l WHERE l.profesoriID = :profesiID");
        query.setParameter("profesiID", profesiID);
        try {
            return query.getResultList();
        } catch (NoResultException nre) {
            throw new LendaException("E dhëna nuk egziston!");
        }
    }
}
