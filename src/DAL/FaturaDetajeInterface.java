/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.FaturaDetaje;
import java.util.List;

/**
 *
 * @author Arbër Suka
 */
public interface FaturaDetajeInterface {
    void create(FaturaDetaje p)throws FaturaDetajeException;
    void edit(FaturaDetaje p)throws FaturaDetajeException;
    void remove(FaturaDetaje p)throws FaturaDetajeException;
    List<FaturaDetaje> findAll();
    FaturaDetaje findById(int Perdoruesi_ID)throws FaturaDetajeException;
}
