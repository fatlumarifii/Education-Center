/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Qyteti;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Fatlum
 */
public class QytetiRepository extends EntMngClass implements QytetiInterface{
     public void create(Qyteti qyteti) throws QytetiException {
        try {
            em.getTransaction().begin();
            em.persist(qyteti);
            em.getTransaction().commit();
        } catch (Throwable thro) {

            if (thro.getMessage().contains("2627")) {

                throw new QytetiException("E dhëna egziston në databazë!");
            } else {
                throw new QytetiException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public void edit(Qyteti qyteti) throws QytetiException {
        try {
            em.getTransaction().begin();
            em.merge(qyteti);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                throw new QytetiException("E dhëna egziston");
            } else {
                throw new QytetiException("Update: " + thro.getClass() + " - " + thro.getMessage());
            }

        }
    }
    
    public void remove(Qyteti qyteti) throws QytetiException {
        try {
            em.getTransaction().begin();
            em.remove(qyteti);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("547")) {
                throw new QytetiException("E dhëna është përdorur, nuk mund ta fshini!!");
            } else {
                throw new QytetiException("Remove: " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public List<Qyteti> findAll() {
        return em.createNamedQuery("Qyteti.findAll").getResultList();
    }
    
  
    
    public Qyteti findById(int id) throws QytetiException {
        Query query = em.createQuery("SELECT n FROM Qyteti n WHERE n.qytetiID = :id");
        query.setParameter("id", id);
        try {
            return (Qyteti) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new QytetiException("E dhëna nuk egziston!");
        }
    }
    public Qyteti findByName(String name)throws QytetiException{
        Query query = em.createQuery("SELECT a FROM Qyteti a WHERE a.emriQytetit = :name");
        query.setParameter("name", name);
        try {
            return (Qyteti) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new QytetiException("E dhëna nuk egziston!");
        }
    }
}
