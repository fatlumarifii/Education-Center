/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.KomboBox;

import BL.Studenti;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;


/**
 *
 * @author Arbër Suka
 */
public class StudentComboBoxModel extends AbstractListModel<Studenti> implements ComboBoxModel<Studenti> {
    
    private Studenti sItem;
    private List<Studenti> data;
    
    public StudentComboBoxModel(List arrayList){
        data = arrayList;
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public Studenti getElementAt(int index) {
        return data.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        sItem = (Studenti)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return sItem;
    }
    
}
