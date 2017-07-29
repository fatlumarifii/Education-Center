/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Arbër Suka
 */
public class StudentiTableModelSugest extends AbstractTableModel {

    private final String[] columnNames = {"Emri Mbiemri","Email"};

    private List<Studenti> data;

    public StudentiTableModelSugest(List<Studenti> data) {
        this.data = data;
    }

    public StudentiTableModelSugest() {

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

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Studenti s = (Studenti) data.get(rowIndex);
        switch (columnIndex) {

            case 0:
                return s.getEmri()+" "+s.getMbiemri();
            case 1:
                return s.getEmail();
            default:
                return null;
        }
    }

}
