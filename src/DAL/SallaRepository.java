/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Salla;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Fatlum
 */
public class SallaRepository extends EntMngClass implements SallaInterface{
    
     public void create(Salla useri) throws SallaException {
        try {
            em.getTransaction().begin();
            em.persist(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {

            if (thro.getMessage().contains("2627")) {

                throw new SallaException("E dhëna egziston në databazë!");
            } else {
                throw new SallaException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public void edit(Salla useri) throws SallaException {
        try {
            em.getTransaction().begin();
            em.merge(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                throw new SallaException("E dhëna egziston");
            } else {
                throw new SallaException("Update: " + thro.getClass() + " - " + thro.getMessage());
            }

        }
    }
    
    public void remove(Salla useri) throws SallaException {
        try {
            em.getTransaction().begin();
            em.remove(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("547")) {
                throw new SallaException("E dhëna është përdorur, nuk mund ta fshini!!");
            } else {
                throw new SallaException("Remove: " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public List<Salla> findAll() {
        return em.createNamedQuery("Salla.findAll").getResultList();
    }
    
  
    
    public Salla findById(int perdoruesiID) throws SallaException {
        Query query = em.createQuery("SELECT s FROM Salla s WHERE s.sallaID = :perdoruesiID");
        query.setParameter("perdoruesiID", perdoruesiID);
        try {
            return (Salla) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new SallaException("E dhëna nuk egziston!");
        }
    }
    public Salla findByName(String name)throws SallaException{
        Query query = em.createQuery("SELECT a FROM Salla a WHERE a.emertimi = :name");
        query.setParameter("name", name);
        try {
            return (Salla) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new SallaException("E dhëna nuk egziston!");
        }
    }
}

