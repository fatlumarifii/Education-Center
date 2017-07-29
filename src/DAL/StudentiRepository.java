/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Profesori;
import BL.Studenti;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Arbër Suka
 */
public class StudentiRepository extends EntMngClass implements StudentiInterface{

    public void create(Studenti useri) throws StudentiException {
        try {
            em.getTransaction().begin();
            em.persist(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {

            if (thro.getMessage().contains("2627")) {

                throw new StudentiException("E dhëna egziston në databazë!");
            } else {
                throw new StudentiException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public void edit(Studenti useri) throws StudentiException {
        try {
            em.getTransaction().begin();
            em.merge(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                throw new StudentiException("E dhëna egziston");
            } else {
                throw new StudentiException("Update: " + thro.getClass() + " - " + thro.getMessage());
            }

        }
    }
    
    public void remove(Studenti useri) throws StudentiException {
        try {
            em.getTransaction().begin();
            em.remove(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("547")) {
                throw new StudentiException("E dhëna është përdorur, nuk mund ta fshini!!");
            } else {
                throw new StudentiException("Remove: " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public List<Studenti> findAll() {
        return em.createNamedQuery("Studenti.findAll").getResultList();
    }
    
    public Studenti findByUsername(String username, String password) throws StudentiException {
        Query query = em.createQuery("SELECT p FROM Studenti p WHERE p.emriPerdoruesit = :username AND p.passwordiPerdoruesit = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            return (Studenti) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new StudentiException("Ky User nuk ekziston!");
        }
    }
    
    public Studenti findById(int perdoruesiID) throws StudentiException {
        Query query = em.createQuery("SELECT p FROM Studenti p WHERE p.studentiID = :perdoruesiID");
        query.setParameter("perdoruesiID", perdoruesiID);
        try {
            return (Studenti) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new StudentiException("E dhëna nuk egziston!");
        }
    }
}



