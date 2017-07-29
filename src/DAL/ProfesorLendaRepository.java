/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.ProfesorLenda;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Fatlum
 */
public class ProfesorLendaRepository extends EntMngClass implements ProfesorLendaInterface{
     public void create(ProfesorLenda useri) throws ProfesoriLendaException {
        try {
            em.getTransaction().begin();
            em.persist(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {

            if (thro.getMessage().contains("2627")) {

                throw new ProfesoriLendaException("E dhëna egziston në databazë!");
            } else {
                throw new ProfesoriLendaException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public void edit(ProfesorLenda useri) throws ProfesoriLendaException {
        try {
            em.getTransaction().begin();
            em.merge(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                throw new ProfesoriLendaException("E dhëna egziston");
            } else {
                throw new ProfesoriLendaException("Update: " + thro.getClass() + " - " + thro.getMessage());
            }

        }
    }
    
    public void remove(ProfesorLenda useri) throws ProfesoriLendaException {
        try {
            em.getTransaction().begin();
            em.remove(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("547")) {
                throw new ProfesoriLendaException("E dhëna është përdorur, nuk mund ta fshini!!");
            } else {
                throw new ProfesoriLendaException("Remove: " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public List<ProfesorLenda> findAll() {
        return em.createNamedQuery("ProfesorLenda.findAll").getResultList();
    }
    
  
    
    public ProfesorLenda findById(int perdoruesiID) throws ProfesoriLendaException {
        Query query = em.createQuery("SELECT p FROM ProfesorLenda p WHERE p.plId = :perdoruesiID");
        query.setParameter("perdoruesiID", perdoruesiID);
        try {
            return (ProfesorLenda) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new ProfesoriLendaException("E dhëna nuk egziston!");
        }
    }
}
