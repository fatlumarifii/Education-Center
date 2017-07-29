/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Nota;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Fatlum
 */
public class NotaRepository extends EntMngClass implements NotaInterface{
     public void create(Nota useri) throws NotaException {
        try {
            em.getTransaction().begin();
            em.persist(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {

            if (thro.getMessage().contains("2627")) {

                throw new NotaException("E dhëna egziston në databazë!");
            } else {
                throw new NotaException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public void edit(Nota useri) throws NotaException {
        try {
            em.getTransaction().begin();
            em.merge(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                throw new NotaException("E dhëna egziston");
            } else {
                throw new NotaException("Update: " + thro.getClass() + " - " + thro.getMessage());
            }

        }
    }
    
    public void remove(Nota useri) throws NotaException {
        try {
            em.getTransaction().begin();
            em.remove(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("547")) {
                throw new NotaException("E dhëna është përdorur, nuk mund ta fshini!!");
            } else {
                throw new NotaException("Remove: " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public List<Nota> findAll() {
        return em.createNamedQuery("Nota.findAll").getResultList();
    }
    
  
    
    public Nota findById(int notaID) throws NotaException {
        Query query = em.createQuery("SELECT n FROM Nota n WHERE n.notaID = :notaID");
        query.setParameter("notaID", notaID);
        try {
            return (Nota) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new NotaException("E dhëna nuk egziston!");
        }
    }
    
    public List<Nota> findByStudentiID(int studentiID) throws NotaException {
        Query query = em.createQuery("SELECT n FROM Nota n WHERE n.studentiID.studentiID = :studentiID");
        query.setParameter("studentiID", studentiID);
        try {
            return query.getResultList();
        } catch (NoResultException nre) {
            throw new NotaException("E dhëna nuk egziston!");
        }
    }
}