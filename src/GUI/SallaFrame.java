/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BL.Salla;
import DAL.SallaException;
import DAL.SallaInterface;
import DAL.SallaRepository;
import Model.SallaTableModel;
import com.sun.glass.events.KeyEvent;
import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isDigit;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Arbër Suka
 */
public class SallaFrame extends javax.swing.JInternalFrame {

    SallaTableModel sallaTable = new SallaTableModel();
    SallaInterface sallaR = new SallaRepository();
    /**
     * Creates new form SallaFrame
     */
    public SallaFrame() {
        initComponents();
        tabelaLoad();
    }

    private void tabelaLoad() {
        List<Salla> lista = sallaR.findAll();
        sallaTable.add(lista);
        tabela.setModel(sallaTable);
        sallaTable.fireTableDataChanged();
        tabelaSelectedIndexChange();

    }
    
    private void tabelaSelectedIndexChange() {
        final ListSelectionModel rowSM = tabela.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent Ise) {
                if (Ise.getValueIsAdjusting()) {
                    return;
                }
                ListSelectionModel rowSM = (ListSelectionModel) Ise.getSource();
                int selectedIndex = rowSM.getAnchorSelectionIndex();
                if (selectedIndex > -1) {
                    Salla salla = sallaTable.getSalla(selectedIndex);
                    salla.setEmertimi(salla.getEmertimi());
                    salla.setNrKapaciteteve(salla.getNrKapaciteteve());
                    salla.setPershkrim(salla.getPershkrim());
                }
            }
        });
    }
    
    public void emptyObject() {
        tabela.clearSelection();
        emritxt.setText("");
        kapacitetitxt.setText("");
        pershkrimitxt.setText("");

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        emritxt = new javax.swing.JTextField();
        pershkrimitxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        kapacitetitxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        ruajbtn = new javax.swing.JButton();
        FshijeBtn = new javax.swing.JButton();
        AnuloBtn = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Salla");

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Emri salles");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 11, 110, 25));

        emritxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                emritxtKeyTyped(evt);
            }
        });
        jPanel1.add(emritxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 43, 342, 30));

        pershkrimitxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pershkrimitxtKeyTyped(evt);
            }
        });
        jPanel1.add(pershkrimitxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 123, 343, 32));

        jLabel3.setText("Pershkrimi salles");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 92, 110, 25));

        kapacitetitxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kapacitetitxtKeyTyped(evt);
            }
        });
        jPanel1.add(kapacitetitxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(378, 42, 126, 32));

        jLabel2.setText("Kapaciteti");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(378, 11, 110, 25));

        ruajbtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ruajbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/save.png"))); // NOI18N
        ruajbtn.setText("Ruaj");
        ruajbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ruajbtnActionPerformed(evt);
            }
        });

        FshijeBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        FshijeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/delete.png"))); // NOI18N
        FshijeBtn.setText("Fshij");
        FshijeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FshijeBtnActionPerformed(evt);
            }
        });

        AnuloBtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        AnuloBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/cancel.png"))); // NOI18N
        AnuloBtn.setText("Anulo");
        AnuloBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnuloBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ruajbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FshijeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AnuloBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AnuloBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FshijeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ruajbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ruajbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ruajbtnActionPerformed
        try {

            int row = tabela.getSelectedRow();
            if (emritxt.getText().equals("") || kapacitetitxt.getText().equals("") ){
                JOptionPane.showMessageDialog(this, "Ju lutem plotesoni çdo fushë !!!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (row == -1) {
                    Salla s = new Salla();
                    s.setEmertimi(emritxt.getText());
                    s.setNrKapaciteteve(Integer.parseInt(kapacitetitxt.getText()));
                    s.setPershkrim(pershkrimitxt.getText());
                    sallaR.create(s);
                    
                    JOptionPane.showMessageDialog(this, "E dhëna u ruajt me sukses !");
                } else {
                    Salla s = this.sallaTable.getSalla(row);
                    s.setEmertimi(emritxt.getText());
                    s.setNrKapaciteteve(Integer.parseInt(kapacitetitxt.getText()));
                    s.setPershkrim(pershkrimitxt.getText());
                    sallaR.create(s);

                    sallaR.edit(s);
                    JOptionPane.showMessageDialog(this, "E dhëna u editua me sukses");
                }
                emptyObject();
                tabelaLoad();
            }

        } catch (SallaException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_ruajbtnActionPerformed

    private void FshijeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FshijeBtnActionPerformed
        try {
            int row = tabela.getSelectedRow();
            if (row > -1) {
                Object[] ob = {"Po", "Jo"};
                int i = javax.swing.JOptionPane.showOptionDialog(this, "A dëshironi ta fshini ?", "Fshirja", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, ob, ob[1]);
                if (i == 0) {
                    Salla s = this.sallaTable.getSalla(row);
                    sallaR.remove(s);
                    tabelaLoad();
                    emptyObject();
                    JOptionPane.showMessageDialog(this, "E dhëna është fshir me sukses !");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nuk keni selektuar asgjë për të fshir !");
            }
        } catch (SallaException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_FshijeBtnActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        int row = tabela.getSelectedRow();
        if (row > -1) {

            Salla s = sallaTable.getSalla(row);
            emritxt.setText(s.getEmertimi());
            kapacitetitxt.setText(""+s.getNrKapaciteteve());
            pershkrimitxt.setText(s.getPershkrim());
        }
    }//GEN-LAST:event_tabelaMouseClicked

    private void kapacitetitxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kapacitetitxtKeyTyped
        char c = evt.getKeyChar();
        if(!(isDigit(c)))
            evt.consume();
        String number = kapacitetitxt.getText();
        if(number.length()>2 && isDigit(c)){
            JOptionPane.showMessageDialog(this, "Keni tejakaluar numrin e lejuar (3-shifror)", "Gabim" , 0);
            evt.consume();
        }
                
    }//GEN-LAST:event_kapacitetitxtKeyTyped

    private void pershkrimitxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pershkrimitxtKeyTyped
        char c = evt.getKeyChar();
        if (!((isAlphabetic((int) c)) || c == KeyEvent.VK_BACKSPACE || c == KeyEvent.VK_SHIFT || c == KeyEvent.VK_SPACE || isDigit(c))) {
            evt.consume();
        }
        String number =  pershkrimitxt.getText();
        //vec nese eshte number dhe eshte 3 shifror e qet kete jOptionPane
        if(number.length()>29){
            JOptionPane.showMessageDialog(this, "Keni tejakaluar numrin e lejuar te shkronjave(30)", "Gabim" , 0);
            evt.consume();
        }
    }//GEN-LAST:event_pershkrimitxtKeyTyped

    private void emritxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emritxtKeyTyped
         char c = evt.getKeyChar();
        if (!((isAlphabetic((int) c)) || c == KeyEvent.VK_BACKSPACE || c == KeyEvent.VK_SHIFT || c == KeyEvent.VK_SPACE || isDigit(c))) {
            evt.consume();
        }
        String number =  emritxt.getText();
        //vec nese eshte number dhe eshte 3 shifror e qet kete jOptionPane
        if(number.length()>29){
            JOptionPane.showMessageDialog(this, "Keni tejakaluar numrin e lejuar te shkronjave(30)", "Gabim" , 0);
            evt.consume();
        }
    }//GEN-LAST:event_emritxtKeyTyped

    private void AnuloBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnuloBtnActionPerformed
        emptyObject();
    }//GEN-LAST:event_AnuloBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AnuloBtn;
    private javax.swing.JButton FshijeBtn;
    private javax.swing.JTextField emritxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kapacitetitxt;
    private javax.swing.JTextField pershkrimitxt;
    private javax.swing.JButton ruajbtn;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
