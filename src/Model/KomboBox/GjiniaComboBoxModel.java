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
import BL.Gjinia;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;


public class GjiniaComboBoxModel extends AbstractListModel<Gjinia> implements ComboBoxModel<Gjinia> {
    
    private Gjinia sItem;
    private List<Gjinia> data;
    
    public GjiniaComboBoxModel(List arrayList){
        data = arrayList;
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public Gjinia getElementAt(int index) {
        return data.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        sItem = (Gjinia)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return sItem;
    }
    
}
