/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Lenda;
import java.util.List;

/**
 *
 * @author Fatlum
 */
public interface LendaInterface {
    void create(Lenda p)throws LendaException;
    void edit(Lenda p)throws LendaException;
    void remove(Lenda p)throws LendaException;
    List<Lenda> findAll();
    Lenda findById(int Perdoruesi_ID)throws LendaException;
    List<Lenda> findByProfId(int profesiID) throws LendaException;
}
