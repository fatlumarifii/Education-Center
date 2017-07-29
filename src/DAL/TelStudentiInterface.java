/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.TelStudenti;
import java.util.List;

/**
 *
 * @author Fatlum
 */
public interface TelStudentiInterface {
    void create(TelStudenti p)throws TelStudentiException;
    void edit(TelStudenti p)throws TelStudentiException;
    void remove(TelStudenti p)throws TelStudentiException;
    List<TelStudenti> findAll();
    List<TelStudenti> findByStudentiId(int studentiID) throws TelStudentiException;
    TelStudenti findById(int Perdoruesi_ID)throws TelStudentiException;
}
