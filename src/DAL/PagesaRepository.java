/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Pagesa;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Fatlum
 */
public class PagesaRepository extends EntMngClass implements PagesaInterface{
     public void create(Pagesa useri) throws PagesaException {
        try {
            em.getTransaction().begin();
            em.persist(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {

            if (thro.getMessage().contains("2627")) {

                throw new PagesaException("E dhëna egziston në databazë!");
            } else {
                throw new PagesaException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public void edit(Pagesa useri) throws PagesaException {
        try {
            em.getTransaction().begin();
            em.merge(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                throw new PagesaException("E dhëna egziston");
            } else {
                throw new PagesaException("Update: " + thro.getClass() + " - " + thro.getMessage());
            }

        }
    }
    
    public void remove(Pagesa useri) throws PagesaException {
        try {
            em.getTransaction().begin();
            em.remove(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("547")) {
                throw new PagesaException("E dhëna është përdorur, nuk mund ta fshini!!");
            } else {
                throw new PagesaException("Remove: " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public List<Pagesa> findAll() {
        return em.createNamedQuery("Pagesa.findAll").getResultList();
    }
    
  
    
    public Pagesa findById(int perdoruesiID) throws PagesaException {
        Query query = em.createQuery("SELECT p FROM Pagesa p WHERE p.pagesaID = :perdoruesiID");
        query.setParameter("perdoruesiID", perdoruesiID);
        try {
            return (Pagesa) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new PagesaException("E dhëna nuk egziston!");
        }
    }
    
    public List<Pagesa> findByStudentiID(int perdoruesiID) throws PagesaException {
        Query query = em.createQuery("SELECT n FROM Pagesa n WHERE n.studentiID.studentiID = :perdoruesiID");
        query.setParameter("perdoruesiID", perdoruesiID);
        try {
            return query.getResultList();
        } catch (NoResultException nre) {
            throw new PagesaException("E dhëna nuk egziston!");
        }
    }
}

