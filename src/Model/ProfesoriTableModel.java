/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BL.Profesori;
import BL.TelProfesori;
import DAL.*;
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
public class ProfesoriTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Emri", "Mbiemri", "Kombesia", "Qyteti",
        "Zip Kodi", "Rruga","Gjinia" ,"Ditelindja", "Email","Statusi"};

    private List<Profesori> data1;

    public ProfesoriTableModel(List<Profesori> data) {
        this.data1 = data;
    }

    public ProfesoriTableModel() {

    }

    public void add(List<Profesori> data) {
        this.data1 = data;
    }

    @Override
    public int getRowCount() {
        return data1.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public void remove(int row) {
        data1.remove(row);
    }

    public Profesori getProfesori(int index) {
        return data1.get(index);
    }

    public String getDate(Date date) {
        DateFormat da = new SimpleDateFormat("dd/MM/yyyy");
        return da.format(date);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Profesori s = (Profesori) data1.get(rowIndex);
//        TelProfesoriInterface telR = new TelProfesoriRepository();
//        List<TelProfesori> tel = null;
//        try {
//            tel = telR.findByProfesoriId(s.getProfesoriID());
//        } catch (TelProfesoriException ex) {
//            Logger.getLogger(StudentiTableModel.class.getName()).log(Level.SEVERE, null, ex);
//        }
        switch (columnIndex) {
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
                return s.getGjiniaid().getLloji();
            case 7:
                return getDate(s.getDateLindja());
            case 8:
                return s.getEmail();
            case 9:
                return s.getStatusi() ? "Aktiv" : "Jo Aktiv";
//            case 10:
//                return tel.size() < 1? "Nuk ka Numer" : tel.get(0);
            default:
                return null;
        }
    }

}
