/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.TelProfesori;
import java.util.List;

/**
 *
 * @author Fatlum
 */
public interface TelProfesoriInterface {
    void create(TelProfesori p)throws TelProfesoriException;
    void edit(TelProfesori p)throws TelProfesoriException;
    void remove(TelProfesori p)throws TelProfesoriException;
    List<TelProfesori> findAll();
   List<TelProfesori> findByProfesoriId(int studentiID) throws TelProfesoriException;
    TelProfesori findById(int Perdoruesi_ID)throws TelProfesoriException;
}
