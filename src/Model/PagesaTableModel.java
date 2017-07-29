/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BL.Pagesa;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Arbër Suka
 */
public class PagesaTableModel extends AbstractTableModel {
    
    
    private final String [] columnNames = {"Studenti","Shuma paguar","Nr Fatures"};
    
    private List <Pagesa> data;
    
    public PagesaTableModel(List<Pagesa>data){
        this.data = data;
    }

    public PagesaTableModel() {
   
    }
    public void add(List<Pagesa>data){
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

    public Pagesa getPagesa(int index){
        return data.get(index);
    }
    
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pagesa p = (Pagesa)data.get(rowIndex);
        switch(columnIndex){
            
            case 0:
                return p.getStudentiID().toString();
            case 1:
                return p.getShumaEPaguar();
            case 2:
                return p.getFaturaID().getNumriFatures();                
            default:
                return null;
        }
    }
        
}