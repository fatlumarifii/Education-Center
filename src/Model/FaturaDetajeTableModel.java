/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BL.FaturaDetaje;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fatlum
 */
public class FaturaDetajeTableModel extends AbstractTableModel {
    
    private final String [] columnNames = {"Lenda","Shuma","Sasia"};
    
    private List <FaturaDetaje> data;
    
    public FaturaDetajeTableModel(List<FaturaDetaje>data){
        this.data = data;
    }

    public FaturaDetajeTableModel() {
   
    }
    public void add(List<FaturaDetaje>data){
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

    public FaturaDetaje getFaturaDetaje(int index){
        return data.get(index);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FaturaDetaje f = (FaturaDetaje)data.get(rowIndex);
        
        DateFormat da = new SimpleDateFormat(("yyyy/MM/dd HH:mm:ss"));
        switch(columnIndex){
            
            case 0:
                return f.getLendaID();
            case 1:
                return f.getShuma();
            case 2:
                return f.getSasia();
            default:
                return null;
        }
    }
        
}
