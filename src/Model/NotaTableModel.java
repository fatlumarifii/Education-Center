/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BL.Nota;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Arbër Suka
 */
public class NotaTableModel extends AbstractTableModel {
    
    private final String [] columnNames = {"Emri Studentit","Emri Profesorit","Emri Lendes", "Nota"};
    
    private List <Nota> data;
    
    public NotaTableModel(List<Nota>data){
        this.data = data;
    }

    public NotaTableModel() {
   
    }
    public void add(List<Nota>data){
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

    public Nota getNota (int index){
        return data.get(index);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Nota n = (Nota)data.get(rowIndex);
        switch(columnIndex){
            
            case 0:
                 return n.getStudentiID();
            case 1:
                return n.getProfesoriID();
            case 2:
                return n.getLendaID();
            case 3:
                return n.getNota();
            default:
                return null;
        }
    }
        
}
