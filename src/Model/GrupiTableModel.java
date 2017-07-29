/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BL.Grupi;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fatlum
 */
public class GrupiTableModel extends AbstractTableModel {
    
    private final String [] columnNames = {"Emri Grupit","Nr i Studenteve"};
    
    private List <Grupi> data;
    
    public GrupiTableModel(List<Grupi>data){
        this.data = data;
    }

    public GrupiTableModel() {
   
    }
    public void add(List<Grupi>data){
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

    public Grupi getGrupi(int index){
        return data.get(index);
    }
     public String getDate (Date date){
        DateFormat da = new SimpleDateFormat("dd/MM/yyyy");
        return da.format(date);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Grupi g = (Grupi)data.get(rowIndex);
        DateFormat da = new SimpleDateFormat("dd/MM/yyyy");
        switch(columnIndex){
            
            case 0:
                return g.getEmriGrupit();
            case 1:
                return g.getNrStudenteveGrup();           
            default:
                return null;
        }
    }
}
