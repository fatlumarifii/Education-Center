/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Studenti;
import java.util.List;

/**
 *
 * @author Fatlum
 */
public interface StudentiInterface {
     void create(Studenti p)throws StudentiException;
    void edit(Studenti p)throws StudentiException;
    void remove(Studenti p)throws StudentiException;
    List<Studenti> findAll();
    public Studenti findByUsername(String username, String password) throws StudentiException;
    Studenti findById(int Perdoruesi_ID)throws StudentiException;
}
