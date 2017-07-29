/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BL.Arkiva;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fatlum
 */
public class ArkivaTableModel extends AbstractTableModel {
    
    private final String [] columnNames = {"Emri_Fajllit","Data_Publikimit"};
    
    private List <Arkiva> data;
    
    public ArkivaTableModel(List<Arkiva>data){
        this.data = data;
    }

    public ArkivaTableModel() {
   
    }
    public void add(List<Arkiva>data){
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

    public Arkiva getArkiva(int index){
        return data.get(index);
    }
     public String getDate (Date date){
        DateFormat da = new SimpleDateFormat(("yyyy/MM/dd HH:mm:ss"));
        return da.format(date);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Arkiva a = (Arkiva)data.get(rowIndex);
        switch(columnIndex){
            
            case 0:
                return a.getEmriFajllit();
            case 1:
                return getDate(a.getDataPublikimit());
            default:
                return null;
        }
    }
}
