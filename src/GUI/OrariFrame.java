/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BL.Grupi;
import BL.Lenda;
import BL.Orari;
import BL.Profesori;
import BL.Salla;
import DAL.GrupiInterface;
import DAL.GrupiRepository;
import DAL.LendaException;
import DAL.LendaInterface;
import DAL.LendaRepository;
import DAL.OrariException;
import DAL.OrariInterface;
import DAL.OrariRepository;
import DAL.ProfesoriInterface;
import DAL.ProfesoriRepository;
import DAL.SallaInterface;
import DAL.SallaRepository;
import Model.KomboBox.GrupiComboBoxModel;
import Model.KomboBox.LendaComboBoxModel;
import Model.KomboBox.ProfesoriComboBoxModel;
import Model.KomboBox.SallaComboBoxModel;
import Model.OrariTableModel;
import Regex.OraValidator;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JExcelApiExporter;

/**
 *
 * @author Fatlum
 */
public class OrariFrame extends javax.swing.JInternalFrame {

    /**
     * Creates new form OrariFrame
     */
    OrariInterface orariRepository = new OrariRepository();
    ProfesoriInterface profR = new ProfesoriRepository();
    GrupiInterface grupiR = new GrupiRepository();
    SallaInterface sallaR = new SallaRepository();
    LendaInterface lendaR = new LendaRepository();
    OrariTableModel orariTable = new OrariTableModel();
    ProfesoriComboBoxModel profesorimodelcombo;
    LendaComboBoxModel lendamodelcombo;
    GrupiComboBoxModel grupiCombo;
    SallaComboBoxModel sallaCombo;
    List<Profesori> profList;
    List<Salla> sallaList;
    List<Grupi> grupiList;

    public OrariFrame() {
        initComponents();
        tabelaLoad();
        orafillimitlbl.setVisible(false);
        orambarimitlbl.setVisible(false);
        populateChooserProfesori();
//        populateChooserLenda();
        populateChooserGrupi();
        populateChooserSalla();
    }

     public void populateChooserProfesori() {
        List<Profesori> profID = profR.findAll();
        profesorimodelcombo = new ProfesoriComboBoxModel(profID);
        ProfesoriChooser.setModel(profesorimodelcombo);
    }
     
//     public void populateChooserLenda() {
//        List<Lenda> lendaID = lendaR.findAll();
//        lendamodelcombo = new LendaComboBoxModel(lendaID);
//        LendaChooser.setModel(lendamodelcombo);
//    }
     
     public void populateChooserGrupi() {
        List<Grupi> grupiID = grupiR.findAll();
        grupiCombo = new GrupiComboBoxModel(grupiID);
        GrupiChooser.setModel(grupiCombo);
    }
     
      public void populateChooserSalla() {
        List<Salla> sallaID = sallaR.findAll();
        sallaCombo = new SallaComboBoxModel(sallaID);
        SallaChooser.setModel(sallaCombo);
    }

    public void emptyObject() {
        tabela.clearSelection();
        dataOtxt.setDate(null);
        orafillimittxt.setText("");
        orambarimittxt.setText("");
        populateChooserProfesori();
//        populateChooserLenda();
        populateChooserGrupi();
        populateChooserSalla();

    }

    private void tabelaLoad() {
        List<Orari> lista = orariRepository.findAll();
        orariTable.add(lista);
        tabela.setModel(orariTable);
        orariTable.fireTableDataChanged();
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
                    Orari orar = orariTable.getOrari(selectedIndex);
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    String oraFillimit = sdf.format(orar.getOraEFillimit());
                    String oraMbarimit = sdf.format(orar.getOraEMbarimit());
                    dataOtxt.setDate(orar.getDataOrarit());
                    orafillimittxt.setText(oraFillimit);
                    orambarimittxt.setText(oraMbarimit);
                    GrupiChooser.setSelectedItem(orar.getGrupiID());
                    SallaChooser.setSelectedItem(orar.getSallaID());
                    ProfesoriChooser.setSelectedItem(orar.getProfesoriID());
                    LendaChooser.setSelectedItem(orar.getLendaID());
                    GrupiChooser.repaint();
                    SallaChooser.repaint();
                    ProfesoriChooser.repaint();
                    LendaChooser.repaint();
                    
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        dataOtxt = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        savebtn = new javax.swing.JButton();
        deletebtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        orambarimittxt = new javax.swing.JTextField();
        orafillimittxt = new javax.swing.JTextField();
        anulobtn = new javax.swing.JButton();
        orambarimitlbl = new javax.swing.JLabel();
        orafillimitlbl = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        LendaChooser = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        ProfesoriChooser = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        GrupiChooser = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        SallaChooser = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jMenuItem3.setText("jMenuItem3");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Orari");
        setMinimumSize(new java.awt.Dimension(876, 364));
        setPreferredSize(new java.awt.Dimension(970, 450));

        jLabel1.setText("Data: ");

        jLabel2.setText("Ora fillimit:");

        jLabel3.setText("Ora Mbarimit: ");

        savebtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        savebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/save.png"))); // NOI18N
        savebtn.setText("Ruaj");
        savebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebtnActionPerformed(evt);
            }
        });

        deletebtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deletebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/delete.png"))); // NOI18N
        deletebtn.setText("Fshij");
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
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

        orambarimittxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                orambarimittxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                orambarimittxtFocusLost(evt);
            }
        });

        orafillimittxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                orafillimittxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                orafillimittxtFocusLost(evt);
            }
        });

        anulobtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        anulobtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/cancel.png"))); // NOI18N
        anulobtn.setText("Anulo");
        anulobtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anulobtnActionPerformed(evt);
            }
        });

        orambarimitlbl.setForeground(new java.awt.Color(255, 0, 0));
        orambarimitlbl.setText("Ora gabim !!!");

        orafillimitlbl.setForeground(new java.awt.Color(255, 0, 0));
        orafillimitlbl.setText("Ora gabim !!!");

        jLabel4.setText("Lenda: ");

        jLabel5.setText("Profesori : ");

        ProfesoriChooser.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ProfesoriChooserItemStateChanged(evt);
            }
        });

        jLabel6.setText("Grupi: ");

        jLabel7.setText("Salla: ");

        jMenu2.setText("Ruaj");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/excel.png"))); // NOI18N
        jMenuItem1.setText("Ruaj ne Excel");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/pdf.png"))); // NOI18N
        jMenuItem2.setText("Ruaj ne PDF");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/hp_printer.png"))); // NOI18N
        jMenuItem4.setText("Printo");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(orafillimitlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dataOtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(orafillimittxt)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(orambarimittxt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(GrupiChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(orambarimitlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(313, 313, 313))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(ProfesoriChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LendaChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SallaChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(anulobtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(anulobtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dataOtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(orafillimittxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(orambarimittxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(GrupiChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(orambarimitlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(orafillimitlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ProfesoriChooser)
                            .addComponent(SallaChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LendaChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        try {
            int row = tabela.getSelectedRow();
            if (dataOtxt.getDate() == null || orafillimittxt.getText().equals("") || orambarimittxt.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Ju lutem plotesoni çdo fushë !!!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (row == -1) {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    Date oraFillimit = null;
                    oraFillimit = sdf.parse(orafillimittxt.getText());
                    Date oraMbarimit = null;
                    oraMbarimit = sdf.parse(orambarimittxt.getText());

                    Orari orar = new Orari();
                    orar.setDataOrarit(dataOtxt.getDate());
                    orar.setOraEFillimit(oraFillimit);
                    orar.setOraEMbarimit(oraMbarimit);
                    orar.setGrupiID((Grupi)GrupiChooser.getSelectedItem());
                    orar.setLendaID((Lenda)LendaChooser.getSelectedItem());
                    orar.setProfesoriID((Profesori)ProfesoriChooser.getSelectedItem());
                    orar.setSallaID((Salla)SallaChooser.getSelectedItem());
                    orariRepository.create(orar);
                    JOptionPane.showMessageDialog(this, "E dhëna u ruajt me sukses !");
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    Date oraFillimit = null;
                    oraFillimit = sdf.parse(orafillimittxt.getText());
                    Date oraMbarimit = null;
                    oraMbarimit = sdf.parse(orambarimittxt.getText());

                    Orari orar = this.orariTable.getOrari(row);
                    orar.setDataOrarit(dataOtxt.getDate());
                    orar.setOraEFillimit(oraFillimit);
                    orar.setOraEMbarimit(oraMbarimit);
                    orar.setGrupiID((Grupi)GrupiChooser.getSelectedItem());
                    orar.setLendaID((Lenda)LendaChooser.getSelectedItem());
                    orar.setSallaID((Salla)SallaChooser.getSelectedItem());
                    orar.setProfesoriID((Profesori)ProfesoriChooser.getSelectedItem());
                    orariRepository.edit(orar);
                    JOptionPane.showMessageDialog(this, "E dhëna u editua me sukses !");
                }
                emptyObject();
                tabelaLoad();
            }

        } catch (OrariException pe) {
            JOptionPane.showMessageDialog(this, pe.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(AdministrataFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_savebtnActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
        try {
            int row = tabela.getSelectedRow();
            if (row > -1) {
                Object[] ob = {"Po", "Jo"};
                int i = javax.swing.JOptionPane.showOptionDialog(this, "A dëshironi ta fshini ?", "Fshirja", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, ob, ob[1]);
                if (i == 0) {
                    Orari o = this.orariTable.getOrari(row);
                    orariRepository.remove(o);
                    tabelaLoad();
                    emptyObject();
                    JOptionPane.showMessageDialog(this, "E dhëna është fshir me sukses !");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nuk keni selektuar asgjë për të fshir !");
            }
        } catch (OrariException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_deletebtnActionPerformed


    private void anulobtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anulobtnActionPerformed
        tabela.clearSelection();
        emptyObject();
    }//GEN-LAST:event_anulobtnActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked

    }//GEN-LAST:event_tabelaMouseClicked


    private void orafillimittxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_orafillimittxtFocusLost
        OraValidator v = new OraValidator();
        if (!v.validate(orafillimittxt.getText().trim())) {
            orafillimitlbl.setVisible(true);
        }
        else
            orafillimitlbl.setVisible(false);
    }//GEN-LAST:event_orafillimittxtFocusLost

    private void orambarimittxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_orambarimittxtFocusLost
        OraValidator v = new OraValidator();
        if (!v.validate(orambarimittxt.getText().trim())) {
            orambarimitlbl.setVisible(true);
        }
        else
            orambarimitlbl.setVisible(false);
    }//GEN-LAST:event_orambarimittxtFocusLost

    private void orafillimittxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_orafillimittxtFocusGained
        
    }//GEN-LAST:event_orafillimittxtFocusGained

    private void orambarimittxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_orambarimittxtFocusGained
        
    }//GEN-LAST:event_orambarimittxtFocusGained

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
         MessageFormat header = new MessageFormat("Tabela Dhëmbi");
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");

        try{
            tabela.print(JTable.PrintMode.NORMAL, header, footer);

        }
        catch(java.awt.print.PrinterException e) {
            System.err.format("Cannot print %s%n",e.getMessage());
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
           try{
           JFileChooser fch = new JFileChooser();
           fch.setFileSelectionMode(JFileChooser.FILES_ONLY);
           fch.setPreferredSize(new Dimension(700, 500));    
           fch.setDialogTitle("Ruaj raportin");
           int returnVal = fch.showSaveDialog(null);
           if (returnVal == JFileChooser.APPROVE_OPTION) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Ordinanca_Stomatologjike;user=sa;password=sa");
                String report="C:\\Users\\hp\\Documents\\NetBeansProjects\\appMain_8\\Dhembi.jrxml";
                JasperReport jr=JasperCompileManager.compileReport(report);
                JasperPrint jp=JasperFillManager.fillReport(jr, null,con);
                JasperExportManager.exportReportToPdfFile(jp,fch.getSelectedFile().getAbsolutePath()+".pdf");
 		JOptionPane.showMessageDialog(this, "Raporti u ruajt ne PDF");
           }
        }catch(ClassNotFoundException | SQLException | JRException e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
         try {
            JFileChooser fch = new JFileChooser();
            fch.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fch.setPreferredSize(new Dimension(700, 500));
            fch.setDialogTitle("Ruaj raportin");
            int returnVal = fch.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Ordinanca_Stomatologjike;user=sa;password=sa");
                String report = "C:\\Users\\hp\\Documents\\NetBeansProjects\\appMain_8\\Dhembi.jrxml";
                JasperReport jr = JasperCompileManager.compileReport(report);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
                JExcelApiExporter exporter = new JExcelApiExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fch.getSelectedFile().getAbsolutePath() + ".xls");
                exporter.exportReport();
                JOptionPane.showMessageDialog(this, "Raporti u ruajt ne Excel");
            }
        } catch (ClassNotFoundException | SQLException | JRException e) {

            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void ProfesoriChooserItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ProfesoriChooserItemStateChanged
        Profesori p = (Profesori) ProfesoriChooser.getSelectedItem();
        List<Lenda> listal = null;
        try {
            listal = lendaR.findByProfId(p.getProfesoriID());
        } catch (LendaException ex) {
            Logger.getLogger(NotaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        populateLendaChooser(listal);
    }//GEN-LAST:event_ProfesoriChooserItemStateChanged

    public void populateLendaChooser(List<Lenda> l) {
        lendamodelcombo = new LendaComboBoxModel(l);
        LendaChooser.setModel(lendamodelcombo);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Grupi> GrupiChooser;
    private javax.swing.JComboBox<Lenda> LendaChooser;
    private javax.swing.JComboBox<Profesori> ProfesoriChooser;
    private javax.swing.JComboBox<Salla> SallaChooser;
    private javax.swing.JButton anulobtn;
    private com.toedter.calendar.JDateChooser dataOtxt;
    private javax.swing.JButton deletebtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel orafillimitlbl;
    private javax.swing.JTextField orafillimittxt;
    private javax.swing.JLabel orambarimitlbl;
    private javax.swing.JTextField orambarimittxt;
    private javax.swing.JButton savebtn;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
