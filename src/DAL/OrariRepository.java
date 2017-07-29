/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Orari;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Fatlum
 */
public class OrariRepository extends EntMngClass implements OrariInterface{
     public void create(Orari useri) throws OrariException {
        try {
            em.getTransaction().begin();
            em.persist(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {

            if (thro.getMessage().contains("2627")) {

                throw new OrariException("E dhëna egziston në databazë!");
            } else {
                throw new OrariException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public void edit(Orari useri) throws OrariException {
        try {
            em.getTransaction().begin();
            em.merge(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                throw new OrariException("E dhëna egziston");
            } else {
                throw new OrariException("Update: " + thro.getClass() + " - " + thro.getMessage());
            }

        }
    }
    
    public void remove(Orari useri) throws OrariException {
        try {
            em.getTransaction().begin();
            em.remove(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("547")) {
                throw new OrariException("E dhëna është përdorur, nuk mund ta fshini!!");
            } else {
                throw new OrariException("Remove: " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public List<Orari> findAll() {
        return em.createNamedQuery("Orari.findAll").getResultList();
    }
    
  
    
    public Orari findById(int perdoruesiID) throws OrariException {
        Query query = em.createQuery("SELECT o FROM Orari o WHERE o.orariID = :perdoruesiID");
        query.setParameter("perdoruesiID", perdoruesiID);
        try {
            return (Orari) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new OrariException("E dhëna nuk egziston!");
        }
    }
    
    public List<Orari> findByGrupi(int grupiID) throws OrariException {
        Query query = em.createQuery("SELECT o FROM Orari o WHERE o.grupiID.grupiID = :grupiID");
        query.setParameter("grupiID", grupiID);
        try {
            return query.getResultList();
        } catch (NoResultException nre) {
            throw new OrariException("E dhëna nuk egziston!");
        }
    }
    
    public List<Orari> findByProfesori(int profID) throws OrariException {
        Query query = em.createQuery("SELECT o FROM Orari o WHERE o.profesoriID.profesoriID = :profID");
        query.setParameter("profID", profID);
        try {
            return query.getResultList();
        } catch (NoResultException nre) {
            throw new OrariException("E dhëna nuk egziston!");
        }
    }
}

