/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BL.Fatura;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fatlum
 */
public class FaturaTableModel extends AbstractTableModel {
    
    private final String [] columnNames = {"Numri Fatures","Totali"};
    
    private List <Fatura> data;
    
    public FaturaTableModel(List<Fatura>data){
        this.data = data;
    }

    public FaturaTableModel() {
   
    }
    public void add(List<Fatura>data){
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

    public Fatura getFatura(int index){
        return data.get(index);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Fatura f = (Fatura)data.get(rowIndex);
        switch(columnIndex){
            
            case 0:
                return f.getNumriFatures();
            case 1:
                return f.getTotali();
                
            default:
                return null;
        }
    }
        
}
