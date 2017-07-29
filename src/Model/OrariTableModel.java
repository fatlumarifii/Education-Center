/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BL.Grupi;
import BL.Orari;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fatlum
 */
public class OrariTableModel extends AbstractTableModel {
    
    private final String [] columnNames = {"Data","Ora_Fillimit","Ora_Mbarimit"};
    
    private List <Orari> data;
    
    public OrariTableModel(List<Orari>data){
        this.data = data;
    }

    public OrariTableModel() {
   
    }
    public void add(List<Orari>data){
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

    public Orari getOrari(int index){
        return data.get(index);
    }
     public String getDate (Date date){
        DateFormat da = new SimpleDateFormat("dd/MM/yyyy");
        return da.format(date);
        
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Orari o = (Orari)data.get(rowIndex);
        DateFormat da = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat d = new SimpleDateFormat("HH:mm");
//        ArrayList<Grupi> grupi = (ArrayList<Grupi>) o.getGrupiCollection();
        //qysh me kthy cili grup ka nqat orar
        switch(columnIndex){
            
            case 0:
                return da.format(o.getDataOrarit());
            case 1:
                return d.format(o.getOraEFillimit());
            case 2:
                return d.format(o.getOraEMbarimit());
            default:
                return null;
        }
    }
        
}
