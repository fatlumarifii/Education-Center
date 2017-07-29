/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BL.Adminstrata;
import BL.TelAdministrata;
import DAL.TelAdministrataException;
import DAL.TelAdministrataInterface;
import DAL.TelAdministrataRepository;
import DAL.TelStudentiRepository;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fatlum
 */
public class AdministrataTableModel extends AbstractTableModel {
    
    private final String [] columnNames = {"Emri","Mbiemri","Kombesia","Qyteti",
                "Zip Kodi","Rruga","Ditelindja","Gjinia","Email","Poziten","Statusi"};
    
    private List <Adminstrata> data;
    
    public AdministrataTableModel(List<Adminstrata>data){
        this.data = data;
    }

    public AdministrataTableModel() {
   
    }
    public void add(List<Adminstrata>data){
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

    public Adminstrata getAdminstrata(int index){
        return data.get(index);
    }
     public String getDate (Date date){
        DateFormat da = new SimpleDateFormat("dd/MM/yyyy");
        return da.format(date);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Adminstrata s = (Adminstrata)data.get(rowIndex);
//         TelAdministrataInterface telR = new TelAdministrataRepository();
//        List<TelAdministrata> tel = null;
//        try {
//            tel = telR.findByAdministrataId(s.getAdminstrataID());
//        } catch (TelAdministrataException ex) {
//            Logger.getLogger(StudentiTableModel.class.getName()).log(Level.SEVERE, null, ex);
//        }
        switch(columnIndex){
            
            case 0:
                return s.getEmri();
            case 1:
                return s.getMbiemri();
            case 2:
                return s.getKombesia();  
             case 3: 
                 return s.getQytetiid().getEmriQytetit();
             case 4:
                 return s.getQytetiid().getZipKodi();
             case 5: 
                 return s.getRruga();
             case 6:
                 return getDate(s.getDateLindja());
             case 7:
                 return s.getGjiniaid().getLloji();
             case 8:
                 return s.getEmail();
             case 9:
                 return s.getPozita();
             case 10:
                 return s.getStatusi() ? "Aktiv" : "Jo Aktiv";
//             case 11:
//                 return tel.get(0);
            
            default:
                return null;
        }
    }
        
    }


 