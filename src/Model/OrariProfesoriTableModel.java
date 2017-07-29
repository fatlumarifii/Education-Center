/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BL.Orari;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Arbër Suka
 */
public class OrariProfesoriTableModel extends AbstractTableModel {
    
    private final String [] columnNames = {"Lenda","Data","Ora_Fillimit","Ora_Mbarimit","Grupi"};
    
    private List <Orari> data;
    
    public OrariProfesoriTableModel(List<Orari>data){
        this.data = data;
    }

    public OrariProfesoriTableModel() {
   
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
        switch(columnIndex){
            
            case 0:
                return o.getLendaID().getEmriILendes();
            case 1:
                return da.format(o.getDataOrarit());
            case 2:
                return d.format(o.getOraEFillimit());
            case 3:
                return d.format(o.getOraEMbarimit());
            case 4:
                return o.getGrupiID().getEmriGrupit();
            default:
                return null;
        }
    }
        
}
