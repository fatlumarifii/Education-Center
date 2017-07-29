/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Nota;
import java.util.List;

/**
 *
 * @author Fatlum
 */
public interface NotaInterface {
    void create(Nota p)throws NotaException;
    void edit(Nota p)throws NotaException;
    void remove(Nota p)throws NotaException;
    List<Nota> findAll();
    Nota findById(int Perdoruesi_ID)throws NotaException;
    public List<Nota> findByStudentiID(int perdoruesiID) throws NotaException ;
}
