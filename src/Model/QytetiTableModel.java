/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BL.Qyteti;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fatlum
 */
public class QytetiTableModel  extends AbstractTableModel {

    private final String[] columnNames = {"Emri i Qytetit","Zip Kodi"};

    private List<Qyteti> data;

    public QytetiTableModel(List<Qyteti> data) {
        this.data = data;
    }

    public QytetiTableModel() {

    }

    public void add(List<Qyteti> data) {
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

    public Qyteti getQyteti(int index) {
        return data.get(index);
    }

    public String getDate(Date date) {
        DateFormat da = new SimpleDateFormat("dd/MM/yyyy");
        return da.format(date);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Qyteti q = (Qyteti) data.get(rowIndex);
        switch (columnIndex) {

            case 0:
                return q.getEmriQytetit();
            case 1:
                return q.getZipKodi();
//            case 2:
//                return q.getRruga();
            default:
                return null;
        }
    }

}

