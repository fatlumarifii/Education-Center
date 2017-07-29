/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import BL.Adminstrata;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;


/**
 *
 * @author Arbër Suka
 */
public class AdministrataRepository extends EntMngClass implements AdminstrataInterface{

    public void create(Adminstrata useri) throws AdministrataException {
        try {
            em.getTransaction().begin();
            em.persist(useri);
            em.getTransaction().commit();
        } catch (Throwable thro) {

            if (thro.getMessage().contains("2627")) {

                throw new AdministrataException("E dhëna egziston në databazë!");
            } else {
                throw new AdministrataException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    @Override
    public void edit(Adminstrata useri) throws AdministrataException {
        try {
            em.getTransaction().begin();//metod void qe e hap transaksionin mes db dhe bl;
            em.merge(useri);//Kthen objekt
            em.getTransaction().commit();//metod void qe e mbyll tranaksionin
        } catch (Throwable thro) { //Throwable eshte superklasa e Exception
            if (thro.getMessage().contains("2627")) { // getMessage kthen String dhe me contains e shikojm a permban mesazhin 2627 qe eshte mesazh i db
                throw new AdministrataException("E dhëna egziston");
            } else {
                throw new AdministrataException("Update: " + thro.getClass() + " - " + thro.getMessage());
            }

        }
    }
    
    public void remove(Adminstrata useri) throws AdministrataException {
        try {
            em.getTransaction().begin();
            em.remove(useri);// metod void qe e fshin ni objekt
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("547")) { //547 eshte mesazh qe hudhet kur objekti qe deshirojm me fshi gjindet diku si foreign key dhe nuk lejhet me fshi
                throw new AdministrataException("E dhëna është përdorur, nuk mund ta fshini!!");
            } else {
                throw new AdministrataException("Remove: " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }
    
    public List<Adminstrata> findAll() {//kthen list te objekteve te cilit e kemi definu na 
        return em.createNamedQuery("Adminstrata.findAll").getResultList();//createNamedQuery kthen query getResultList kthen listen e rezulateve qe i ka gjet ne db
    }
    
    public Adminstrata findById(int perdoruesiID) throws AdministrataException {
        Query query = em.createQuery("SELECT p FROM Adminstrata p WHERE p.adminstrataID = :perdoruesiID");
        query.setParameter("perdoruesiID", perdoruesiID);
        try {
            return (Adminstrata) query.getSingleResult();//kthen vetem ni rezultat edhe e bejm  typecast ne Adniminstrat
        } catch (NoResultException nre) {// exception qe quhet kur nuk gjan rezultat query
            throw new AdministrataException("E dhëna nuk egziston!");
        }
    }
}

