/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Salla;
import java.util.List;

/**
 *
 * @author Fatlum
 */
public interface SallaInterface {
    void create(Salla p)throws SallaException;
    void edit(Salla p)throws SallaException;
    void remove(Salla p)throws SallaException;
    List<Salla> findAll();
    Salla findByName(String name)throws SallaException;
    Salla findById(int Perdoruesi_ID)throws SallaException; 
}
