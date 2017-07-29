/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.KomboBox;

import BL.Profesori;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Arbër Suka
 */
public class ProfesoriComboBoxModel extends AbstractListModel<Profesori> implements ComboBoxModel<Profesori> {
    
    private Profesori sItem;
    private List<Profesori> data;
    
    public ProfesoriComboBoxModel(List arrayList){
        data = arrayList;
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public Profesori getElementAt(int index) {
        return data.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        sItem = (Profesori)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return sItem;
    }
    
}

