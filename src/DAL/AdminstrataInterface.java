/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Adminstrata;
import java.util.List;

/**
 *
 * @author Fatlum
 */
public interface AdminstrataInterface {
    void create(Adminstrata p)throws AdministrataException;
    void edit(Adminstrata p)throws AdministrataException;
    void remove(Adminstrata p)throws AdministrataException;
    List<Adminstrata> findAll();
    Adminstrata findById(int Perdoruesi_ID)throws AdministrataException;
}
