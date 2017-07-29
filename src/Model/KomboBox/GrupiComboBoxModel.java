/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.KomboBox;

/**
 *
 * @author Arbër Suka
 */
import BL.Grupi;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;


public class GrupiComboBoxModel extends AbstractListModel<Grupi> implements ComboBoxModel<Grupi> {
    
    private Grupi sItem;
    private List<Grupi> data;
    
    public GrupiComboBoxModel(List arrayList){
        data = arrayList;
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public Grupi getElementAt(int index) {
        return data.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        sItem = (Grupi)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return sItem;
    }
    
}

