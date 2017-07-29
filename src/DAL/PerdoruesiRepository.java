/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

/**
 *
 * @author Arbër Suka
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import BL.Perdoruesi;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;


/**
 *
 * @author Arbër Suka
 */
public class PerdoruesiRepository extends EntMngClass implements PerdoruesiInterface{

    public void create(Perdoruesi useri) throws PerdoruesiException {
        try {
            em.getTransaction().begin();
            em.persist(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {

            if (thro.getMessage().contains("2627")) {

                throw new PerdoruesiException("E dhëna egziston në databazë!");
            } else {
                throw new PerdoruesiException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public void edit(Perdoruesi useri) throws PerdoruesiException {
        try {
            em.getTransaction().begin();
            em.merge(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                throw new PerdoruesiException("E dhëna egziston");
            } else {
                throw new PerdoruesiException("Update: " + thro.getClass() + " - " + thro.getMessage());
            }

        }
    }
    
    public void remove(Perdoruesi useri) throws PerdoruesiException {
        try {
            em.getTransaction().begin();
            em.remove(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("547")) {
                throw new PerdoruesiException("E dhëna është përdorur, nuk mund ta fshini!!");
            } else {
                throw new PerdoruesiException("Remove: " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public List<Perdoruesi> findAll() {
        return em.createNamedQuery("Perdoruesi.findAll").getResultList();
    }
    
    public Perdoruesi findByUsername(String username, String password) throws PerdoruesiException {
        Query query = em.createQuery("SELECT p FROM Perdoruesi p WHERE p.emriPerdoruesit = :username AND p.passwordiPerdoruesit = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            return (Perdoruesi) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new PerdoruesiException("Ky User nuk ekziston!");
        }
    }
    
    public Perdoruesi findPerdoruesi(String email) throws PerdoruesiException {
        Query query = em.createQuery("SELECT p FROM Perdoruesi p WHERE p.emriPerdoruesit = :email");
        query.setParameter("email", email);
        try {
            return (Perdoruesi) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new PerdoruesiException("Ky User nuk ekziston!");
        }
    }
    
    public Perdoruesi findById(int perdoruesiID) throws PerdoruesiException {
        Query query = em.createQuery("SELECT p FROM Perdoruesi p WHERE p.perdoruesiID = :perdoruesiID");
        query.setParameter("perdoruesiID", perdoruesiID);
        try {
            return (Perdoruesi) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new PerdoruesiException("E dhëna nuk egziston!");
        }
    }
}

