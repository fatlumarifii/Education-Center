/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Profesori;
import BL.TelProfesori;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Arbër Suka
 */
public class ProfesoriRepository extends EntMngClass implements ProfesoriInterface{

    @Override
    public void create(Profesori prof) throws ProfesoriException {
        try {
            em.getTransaction().begin();
            em.persist(prof);
            em.getTransaction().commit();
        } catch (Throwable thro) {

            if (thro.getMessage().contains("2627")) {

                throw new ProfesoriException("E dhëna egziston në databazë!");
            } else {
                throw new ProfesoriException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    @Override
    public void edit(Profesori prof) throws ProfesoriException {
        try {
            em.getTransaction().begin();
            em.merge(prof);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                throw new ProfesoriException("E dhëna egziston");
            } else {
                throw new ProfesoriException("Update: " + thro.getClass() + " - " + thro.getMessage());
            }

        }
    }
    
    @Override
    public void remove(Profesori prof) throws ProfesoriException {
        try {
            em.getTransaction().begin();
            em.remove(prof);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("547")) {
                throw new ProfesoriException("E dhëna është përdorur, nuk mund ta fshini!!");
            } else {
                throw new ProfesoriException("Remove: " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    @Override
    public List<Profesori> findAll() {
        return em.createNamedQuery("Profesori.findAll").getResultList();
    }
    
    public Profesori findById(int perdoruesiID) throws ProfesoriException {
        Query query = em.createQuery("SELECT p FROM Profesori p WHERE p.profesoriID = :perdoruesiID");
        query.setParameter("perdoruesiID", perdoruesiID);
        try {
            return (Profesori) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new ProfesoriException("E dhëna nuk egziston!");
        }
    }
    @Override
    public Profesori findByName(String name)throws ProfesoriException{
        Query query = em.createQuery("SELECT p FROM Profesori p WHERE p.emri  = :name");
        query.setParameter("name", name);
        try {
            return (Profesori) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new ProfesoriException("E dhëna nuk egziston!");
        }
    }

    public Profesori findByProfeosriId(int profID) throws ProfesoriException {
        Query query = em.createQuery("SELECT p FROM Profesori p WHERE p.profesoriID = :profID");
        query.setParameter("profID", profID);
        try {
            return (Profesori)query.getSingleResult();
        } catch (NoResultException nre) {
            throw new ProfesoriException("E dhëna nuk egziston!");
        }
    }
    
}


