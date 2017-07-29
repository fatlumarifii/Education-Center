/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Gjinia;
import java.util.List;

/**
 *
 * @author Fatlum
 */
public interface GjiniaInterface {
     void create(Gjinia q)throws GjiniaException;
    void edit(Gjinia q)throws GjiniaException;
    void remove(Gjinia q)throws GjiniaException;
    List<Gjinia> findAll();
    Gjinia findById(int Perdoruesi_ID)throws GjiniaException;
    
}
