/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BL.Salla;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fatlum
 */
public class SallaTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Emri salles","Kapaciteti", "Pershkrimi"};

    private List<Salla> data;

    public SallaTableModel(List<Salla> data) {
        this.data = data;
    }

    public SallaTableModel() {

    }

    public void add(List<Salla> data) {
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

    public Salla getSalla(int index) {
        return data.get(index);
    }

    public String getDate(Date date) {
        DateFormat da = new SimpleDateFormat("dd/MM/yyyy");
        return da.format(date);

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Salla s = (Salla) data.get(rowIndex);
        switch (columnIndex) {

            case 0:
                return s.getEmertimi();
            case 1:
                return s.getNrKapaciteteve();
            case 2:
                return s.getPershkrim();
            default:
                return null;
        }
    }

}
