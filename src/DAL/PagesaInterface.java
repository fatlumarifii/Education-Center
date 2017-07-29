/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Pagesa;
import java.util.List;

/**
 *
 * @author Fatlum
 */
public interface PagesaInterface {
    void create(Pagesa p)throws PagesaException;
    void edit(Pagesa p)throws PagesaException;
    void remove(Pagesa p)throws PagesaException;
    List<Pagesa> findAll();
    Pagesa findById(int Perdoruesi_ID)throws PagesaException;
    List<Pagesa> findByStudentiID(int perdoruesiID) throws PagesaException ;
}
