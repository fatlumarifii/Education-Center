/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Profesori;
import BL.TelProfesori;
import java.util.List;

/**
 *
 * @author Fatlum
 */
public interface ProfesoriInterface {
    void create(Profesori p)throws ProfesoriException;
    void edit(Profesori p)throws ProfesoriException;
    void remove(Profesori p)throws ProfesoriException;
    List<Profesori> findAll();
    Profesori findByName(String name)throws ProfesoriException;
    Profesori findById(int Perdoruesi_ID)throws ProfesoriException;
    Profesori findByProfeosriId(int profID) throws ProfesoriException;
}
