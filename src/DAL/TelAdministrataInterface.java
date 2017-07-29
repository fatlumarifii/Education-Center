/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.TelAdministrata;
import java.util.List;

/**
 *
 * @author Fatlum
 */
public interface TelAdministrataInterface {
    void create(TelAdministrata p)throws TelAdministrataException;
    void edit(TelAdministrata p)throws TelAdministrataException;
    void remove(TelAdministrata p)throws TelAdministrataException;
    List<TelAdministrata> findAll();
    public List<TelAdministrata> findByAdministrataId(int adminID) throws TelAdministrataException;
    TelAdministrata findById(int Perdoruesi_ID)throws TelAdministrataException;
}
