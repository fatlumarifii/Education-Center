/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Grupi;
import BL.Qyteti;
import java.util.List;

/**
 *
 * @author Fatlum
 */
public interface QytetiInterface {
    void create(Qyteti q)throws QytetiException;
    void edit(Qyteti q)throws QytetiException;
    void remove(Qyteti q)throws QytetiException;
    List<Qyteti> findAll();
    Qyteti findByName(String name)throws QytetiException;
    Qyteti findById(int Perdoruesi_ID)throws QytetiException;
}
