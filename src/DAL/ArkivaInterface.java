/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Arkiva;
import java.util.List;

/**
 *
 * @author Fatlum
 */
public interface ArkivaInterface {
    void create(Arkiva p)throws ArkivaException;
    void edit(Arkiva p)throws ArkivaException;
    void remove(Arkiva p)throws ArkivaException;
    List<Arkiva> findAll();
    Arkiva findById(int Perdoruesi_ID)throws ArkivaException;
}
