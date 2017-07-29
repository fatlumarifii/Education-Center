/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Grupi;
import java.util.List;

/**
 *
 * @author Fatlum
 */
public interface GrupiInterface {
    void create(Grupi p)throws GrupiException;
    void edit(Grupi p)throws GrupiException;
    void remove(Grupi p)throws GrupiException;
    List<Grupi> findAll();
    Grupi findByName(String name)throws GrupiException;
    Grupi findById(int Perdoruesi_ID)throws GrupiException;
}
