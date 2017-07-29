package Model;

import BL.Studenti;
import BL.TelStudenti;
import DAL.TelStudentiException;
import DAL.TelStudentiInterface;
import DAL.TelStudentiRepository;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

public class StudentiTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Emri", "Mbiemri","Gjinia","Qyteti","Rruga","Zip Kodi",
         "Kombeisa", "Ditelindja", "Grupi", "Email", "Statusi"};

    private List<Studenti> data;

    public StudentiTableModel(List<Studenti> data) {
        this.data = data;
    }

    public StudentiTableModel() {

    }

    public void add(List<Studenti> data) {
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

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public void remove(int row) {
        data.remove(row);
    }

    public Studenti getStudenti(int index) {
        return data.get(index);
    }

    public String getDate(Date date) {
        DateFormat da = new SimpleDateFormat("dd/MM/yyyy");
        return da.format(date);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Studenti s = (Studenti) data.get(rowIndex);
//        TelStudentiInterface telR = new TelStudentiRepository();
//        List<TelStudenti> tel = null;
//        try {
//            tel = telR.findByStudentiId(s.getStudentiID());
//        } catch (TelStudentiException ex) {
//            Logger.getLogger(StudentiTableModel.class.getName()).log(Level.SEVERE, null, ex);
//        }
        switch (columnIndex) {

            case 0:
                return s.getEmri();
            case 1:
                return s.getMbiemri();
            case 2 :
                return s.getGjiniaid().getLloji();
            case 3 : 
                return s.getQytetiid().getEmriQytetit();
            case 4:
                return s.getRruga();
            case 5:
                return s.getQytetiid().getZipKodi();
            case 6:
                return s.getKombesia();
            case 7:
                return getDate(s.getDitelindja());
            case 8:
                return s.getGrupID().getEmriGrupit();
            case 9:
                return s.getEmail();
//            case 10: 
//                return tel.get(0);
            case 10:
                return s.getStatusi() ? "Aktiv" : "Jo Aktiv";
                
            default:
                return null;
        }
    }

}
