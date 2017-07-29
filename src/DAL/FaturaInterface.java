/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Fatura;
import java.util.List;

/**
 *
 * @author Fatlum
 */
public interface FaturaInterface {
    void create(Fatura p)throws FaturaException;
    void edit(Fatura p)throws FaturaException;
    void remove(Fatura p)throws FaturaException;
    List<Fatura> findAll();
    Fatura findById(int Perdoruesi_ID)throws FaturaException;
    Fatura findByNumriFatures(String nrFatures) throws FaturaException;
}
