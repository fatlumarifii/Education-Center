/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.KomboBox;

import BL.Lenda;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Arbër Suka
 */
public class LendaComboBoxModel extends AbstractListModel<Lenda> implements ComboBoxModel<Lenda> {
    
    private Lenda sItem;
    private List<Lenda> data;
    
    public LendaComboBoxModel(List arrayList){
        data = arrayList;
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public Lenda getElementAt(int index) {
        return data.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        sItem = (Lenda)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return sItem;
    }
    
}
