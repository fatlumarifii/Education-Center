/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Grupi;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Fatlum
 */
public class GrupiRepository extends EntMngClass implements GrupiInterface{
     public void create(Grupi useri) throws GrupiException {
        try {
            em.getTransaction().begin();
            em.persist(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {

            if (thro.getMessage().contains("2627")) {

                throw new GrupiException("E dhëna egziston në databazë!");
            } else {
                throw new GrupiException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public void edit(Grupi useri) throws GrupiException {
        try {
            em.getTransaction().begin();
            em.merge(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                throw new GrupiException("E dhëna egziston");
            } else {
                throw new GrupiException("Update: " + thro.getClass() + " - " + thro.getMessage());
            }

        }
    }
    
    public void remove(Grupi useri) throws GrupiException {
        try {
            em.getTransaction().begin();
            em.remove(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("547")) {
                throw new GrupiException("E dhëna është përdorur, nuk mund ta fshini!!");
            } else {
                throw new GrupiException("Remove: " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public List<Grupi> findAll() {
        return em.createNamedQuery("Grupi.findAll").getResultList();
    }
    
  
    
    public Grupi findById(int perdoruesiID) throws GrupiException {
        Query query = em.createQuery("SELECT a FROM Grupi a WHERE a.grupiID = :perdoruesiID");
        query.setParameter("perdoruesiID", perdoruesiID);
        try {
            return (Grupi) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new GrupiException("E dhëna nuk egziston!");
        }
    }
    
    public Grupi findByName(String name)throws GrupiException{
        Query query = em.createQuery("SELECT a FROM Grupi a WHERE a.emriGrupit = :name");
        query.setParameter("name", name);
        try {
            return (Grupi) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new GrupiException("E dhëna nuk egziston!");
        }
    }
}

