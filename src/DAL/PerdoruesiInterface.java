/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;


import BL.Perdoruesi;
import java.util.List;

/**
 *
 * @author Arbër Suka
 */
public interface PerdoruesiInterface {
    void create(Perdoruesi p)throws PerdoruesiException;
    void edit(Perdoruesi p)throws PerdoruesiException;
    void remove(Perdoruesi p)throws PerdoruesiException;
    List<Perdoruesi> findAll();
    public Perdoruesi findByUsername(String username, String password) throws PerdoruesiException;
    Perdoruesi findPerdoruesi(String email) throws PerdoruesiException;
    Perdoruesi findById(int Perdoruesi_ID)throws PerdoruesiException;
}
