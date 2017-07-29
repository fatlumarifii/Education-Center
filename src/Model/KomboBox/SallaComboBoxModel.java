/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.KomboBox;

import BL.Salla;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Arbër Suka
 */
public class SallaComboBoxModel extends AbstractListModel<Salla> implements ComboBoxModel<Salla> {
    
    private Salla sItem;
    private List<Salla> data;
    
    public SallaComboBoxModel(List arrayList){
        data = arrayList;
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public Salla getElementAt(int index) {
        return data.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        sItem = (Salla)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return sItem;
    }
    
}

