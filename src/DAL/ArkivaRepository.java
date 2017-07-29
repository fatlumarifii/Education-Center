/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Arkiva;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Fatlum
 */
public class ArkivaRepository  extends EntMngClass implements ArkivaInterface{
     public void create(Arkiva useri) throws ArkivaException {
        try {
            em.getTransaction().begin();
            em.persist(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {

            if (thro.getMessage().contains("2627")) {

                throw new ArkivaException("E dhëna egziston në databazë!");
            } else {
                throw new ArkivaException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public void edit(Arkiva useri) throws ArkivaException {
        try {
            em.getTransaction().begin();
            em.merge(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                throw new ArkivaException("E dhëna egziston");
            } else {
                throw new ArkivaException("Update: " + thro.getClass() + " - " + thro.getMessage());
            }

        }
    }
    
    public void remove(Arkiva useri) throws ArkivaException {
        try {
            em.getTransaction().begin();
            em.remove(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("547")) {
                throw new ArkivaException("E dhëna është përdorur, nuk mund ta fshini!!");
            } else {
                throw new ArkivaException("Remove: " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public List<Arkiva> findAll() {
        return em.createNamedQuery("Arkiva.findAll").getResultList();
    }
    
  
    
    public Arkiva findById(int perdoruesiID) throws ArkivaException {
        Query query = em.createQuery("SELECT a FROM Arkiva a WHERE a.arkivaID = :perdoruesiID");
        query.setParameter("perdoruesiID", perdoruesiID);
        try {
            return (Arkiva) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new ArkivaException("E dhëna nuk egziston!");
        }
    }
}
