/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Orari;
import java.util.List;

/**
 *
 * @author Fatlum
 */
public interface OrariInterface {
    void create(Orari p)throws OrariException;
    void edit(Orari p)throws OrariException;
    void remove(Orari p)throws OrariException;
    List<Orari> findAll();
    Orari findById(int Perdoruesi_ID)throws OrariException;
    List<Orari> findByProfesori(int profID) throws OrariException;
    List<Orari> findByGrupi(int grupiID) throws OrariException;
}
