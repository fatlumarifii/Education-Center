/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.ProfesorLenda;
import java.util.List;

/**
 *
 * @author Fatlum
 */
public interface ProfesorLendaInterface {
    void create(ProfesorLenda p)throws ProfesoriLendaException;
    void edit(ProfesorLenda p)throws ProfesoriLendaException;
    void remove(ProfesorLenda p)throws ProfesoriLendaException;
    List<ProfesorLenda> findAll();
    ProfesorLenda findById(int Perdoruesi_ID)throws ProfesoriLendaException;  
}
