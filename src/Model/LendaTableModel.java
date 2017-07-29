/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BL.Lenda;
import BL.Profesori;
import DAL.ProfesoriException;
import DAL.ProfesoriInterface;
import DAL.ProfesoriRepository;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Arbër Suka
 */
public class LendaTableModel extends AbstractTableModel {
    
    private final String [] columnNames = {"Emri i lëndës","Emri profesorit","Kredit e lëndës"};
    
    private List <Lenda> data;
    
    public LendaTableModel(List<Lenda>data){
        this.data = data;
    }

    public LendaTableModel() {
   
    }
    public void add(List<Lenda>data){
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    public String getColumnName(int col){
        return columnNames[col];
    }
    public void remove(int row){
        data.remove(row);
    }

    public Lenda getLenda(int index){
        return data.get(index);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Lenda l = (Lenda)data.get(rowIndex);
        ProfesoriInterface profR = new ProfesoriRepository();
        Profesori p = null;
        try {
            p = profR.findByProfeosriId(l.getProfesoriID());
        } catch (ProfesoriException ex) {
            Logger.getLogger(LendaTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch(columnIndex){
            
            case 0:
                return l.getEmriILendes();
            case 1:
                return p.getEmri() + " " + p.getMbiemri();
            case 2:
                return l.getKredit();            
            default:
                return null;
        }
    }
}
