/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.KomboBox;

import BL.Qyteti;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Arbër Suka
 */
public class QytetiComboBoxModel extends AbstractListModel<Qyteti> implements ComboBoxModel<Qyteti> {
    
    private Qyteti sItem;
    private List<Qyteti> data;
    
    public QytetiComboBoxModel(List arrayList){
        data = arrayList;
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public Qyteti getElementAt(int index) {
        return data.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        sItem = (Qyteti)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return sItem;
    }
    
}
