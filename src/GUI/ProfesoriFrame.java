/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BL.Gjinia;
import BL.Perdoruesi;
import BL.Profesori;
import BL.Qyteti;
import BL.TelProfesori;
import DAL.GjiniaInterface;
import DAL.GjiniaRepository;
import DAL.ProfesoriException;
import DAL.ProfesoriInterface;
import DAL.ProfesoriRepository;
import DAL.QytetiInterface;
import DAL.QytetiRepository;

import DAL.TelProfesoriException;
import DAL.TelProfesoriInterface;
import DAL.TelProfesoriRepository;
import Model.KomboBox.GjiniaComboBoxModel;
import Model.KomboBox.QytetiComboBoxModel;
import Model.ProfesoriTableModel;
import Regex.NumriTelValidator;
import com.sun.glass.events.KeyEvent;
import java.awt.Dimension;
import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isDigit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
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
 * @author Arbër Suka
 */
public class ProfesoriFrame extends javax.swing.JInternalFrame {

    TelProfesoriInterface telR = new TelProfesoriRepository();
    GjiniaInterface gjiniaR = new GjiniaRepository();
    QytetiInterface qytetiR = new QytetiRepository();
    ProfesoriInterface profR = new ProfesoriRepository();
    TelProfesori tel = new TelProfesori();
    ProfesoriTableModel profesoriTable = new ProfesoriTableModel();
    
    RowFilter<ProfesoriTableModel, Profesori> rf = null;
    TableRowSorter sorter = null;
    List<Profesori> lista;
//    RrugaComboBoxModel rrugamodelcombo;
    GjiniaComboBoxModel gjiniamodelcombo;
    QytetiComboBoxModel qytetiModelKombo;
    boolean eshteAktiv;
    ButtonGroup b;

    /**
     * Creates new form ProfesoriFrame
     */
    public ProfesoriFrame() {
        initComponents();
        tabelaLoad();
        populateChooserGjinia();
        populateChooserQyteti();
        grupButton();
        telgabimlb.setVisible(false);
    }

    public void grupButton() {
        b = new ButtonGroup();
        b.add(RadioAktiv);
        b.add(RadioJoAktiv);

    }

    public void populateChooserGjinia() {
        List<Gjinia> gjID = gjiniaR.findAll();
        gjiniamodelcombo = new GjiniaComboBoxModel(gjID);
        GjiniaChooser.setModel(gjiniamodelcombo);
    }

    public void populateChooserQyteti() {
        List<Qyteti> qID = qytetiR.findAll();
        qytetiModelKombo = new QytetiComboBoxModel(qID);
        QytetiChooser.setModel(qytetiModelKombo);
    }

    public void emptyObject() {
        tabela.clearSelection();
        emritxt.setText("");
        mbiemritxt.setText("");
        dobtxt.setDate(null);
        kombesiatxt.setText("");
        emailtxt.setText("");
        zipkoditxt.setText("");
        teltxt.setText("");
        rrugatxt.setText("");
        nrPersonaltxt.setText("");
        populateChooserQyteti();
        populateChooserGjinia();
        b.clearSelection();
    }

    private void tabelaLoad() {
        lista = profR.findAll();
        profesoriTable.add(lista);
        tabela.setModel(profesoriTable);
        profesoriTable.fireTableDataChanged();
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
                    Profesori prof = profesoriTable.getProfesori(selectedIndex);
                    emritxt.setText(prof.getEmri());
                    mbiemritxt.setText(prof.getMbiemri());
                    dobtxt.setDate(prof.getDateLindja());
                    kombesiatxt.setText(prof.getKombesia());
                    Qyteti q = prof.getQytetiid();
                    zipkoditxt.setText("" + q.getZipKodi());
                    rrugatxt.setText(prof.getRruga());
                    emailtxt.setText(prof.getEmail());
                    nrPersonaltxt.setText("" + prof.getNrPersonal());
                    teltxt.setText("" + gjejTel(prof));
                    GjiniaChooser.setSelectedItem(prof.getGjiniaid());
                    QytetiChooser.setSelectedItem(prof.getQytetiid());
                    GjiniaChooser.repaint();
                    QytetiChooser.repaint();
                    if (prof.getStatusi()) {
                        RadioAktiv.setSelected(true);
                    } else {
                        RadioJoAktiv.setSelected(true);
                    }
                }
            }
        });
    }

    public TelProfesori gjejTel(Profesori prof) {
        List<TelProfesori> tel = null;
        try {
            tel = telR.findByProfesoriId(prof.getProfesoriID());
        } catch (TelProfesoriException ex) {
            Logger.getLogger(AdministrataFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tel.get(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        telgabimlb = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        QytetiChooser = new javax.swing.JComboBox<>();
        dobtxt = new com.toedter.calendar.JDateChooser();
        GjiniaChooser = new javax.swing.JComboBox<>();
        RadioAktiv = new javax.swing.JRadioButton();
        kombesiatxt = new javax.swing.JTextField();
        RadioJoAktiv = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        emailtxt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        emritxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        mbiemritxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        teltxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        zipkoditxt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        rrugatxt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        nrPersonaltxt = new javax.swing.JTextField();
        filtrotxt = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        saveBtn = new javax.swing.JButton();
        anulobtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        deletebtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        exelbtn = new javax.swing.JMenuItem();
        pdfbtn = new javax.swing.JMenuItem();
        printobtn = new javax.swing.JMenuItem();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Profesori");
        setMinimumSize(new java.awt.Dimension(1250, 705));
        setPreferredSize(new java.awt.Dimension(1350, 705));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("Gjinia: ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 11, 60, 30));

        telgabimlb.setForeground(new java.awt.Color(255, 0, 0));
        telgabimlb.setText("Numri i telefonit gabim !!!");
        jPanel1.add(telgabimlb, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 139, -1));

        jLabel9.setText("Statusi: ");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(735, 46, 65, 30));

        QytetiChooser.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                QytetiChooserItemStateChanged(evt);
            }
        });
        QytetiChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QytetiChooserActionPerformed(evt);
            }
        });
        jPanel1.add(QytetiChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 47, 100, 30));

        dobtxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dobtxtFocusLost(evt);
            }
        });
        jPanel1.add(dobtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 11, 131, 30));

        jPanel1.add(GjiniaChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(409, 11, 100, 30));

        RadioAktiv.setText("Aktiv");
        RadioAktiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioAktivActionPerformed(evt);
            }
        });
        jPanel1.add(RadioAktiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(806, 50, -1, -1));

        kombesiatxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kombesiatxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kombesiatxtKeyTyped(evt);
            }
        });
        jPanel1.add(kombesiatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(804, 11, 114, 30));

        RadioJoAktiv.setText("Jo Aktiv");
        RadioJoAktiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioJoAktivActionPerformed(evt);
            }
        });
        jPanel1.add(RadioJoAktiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(855, 50, -1, -1));

        jLabel7.setText("Datelindja: ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(527, 11, -1, 30));

        jLabel8.setText("Email:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(527, 46, 55, 30));

        emailtxt.setEditable(false);
        emailtxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailtxtFocusGained(evt);
            }
        });
        emailtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                emailtxtKeyTyped(evt);
            }
        });
        jPanel1.add(emailtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 46, 131, 30));

        jLabel11.setText("Zip Kodi: ");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 46, 60, 30));

        jLabel1.setText("Emri: ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 11, 50, 30));

        emritxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                emritxtKeyTyped(evt);
            }
        });
        jPanel1.add(emritxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 11, 100, 30));

        jLabel2.setText("Mbiemri: ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 11, 61, 30));

        mbiemritxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mbiemritxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                mbiemritxtKeyTyped(evt);
            }
        });
        jPanel1.add(mbiemritxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 11, 100, 30));

        jLabel3.setText("Telefoni:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 50, 30));

        teltxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                teltxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                teltxtFocusLost(evt);
            }
        });
        teltxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teltxtActionPerformed(evt);
            }
        });
        teltxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                teltxtKeyTyped(evt);
            }
        });
        jPanel1.add(teltxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 90, 100, 30));

        jLabel4.setText("Qyteti: ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 46, 50, 30));

        jLabel5.setText("Kombesia: ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(735, 11, 65, 30));

        zipkoditxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                zipkoditxtKeyTyped(evt);
            }
        });
        jPanel1.add(zipkoditxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(409, 46, 100, 30));

        jLabel12.setText("Rruga: ");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 48, 61, 30));

        rrugatxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rrugatxtKeyTyped(evt);
            }
        });
        jPanel1.add(rrugatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 48, 100, 30));

        jLabel10.setText("Nr Personal:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 89, 70, 30));

        nrPersonaltxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nrPersonaltxtKeyTyped(evt);
            }
        });
        jPanel1.add(nrPersonaltxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 90, 100, 30));

        filtrotxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtrotxtKeyReleased(evt);
            }
        });
        jPanel1.add(filtrotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, 132, 30));

        jLabel14.setText("Filtro: ");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 50, 30));

        saveBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        saveBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/save.png"))); // NOI18N
        saveBtn.setText("Ruaj");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
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

        deletebtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deletebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/delete.png"))); // NOI18N
        deletebtn.setText("Fshij");
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });

        jMenu1.setText("Ruaj");

        exelbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/excel.png"))); // NOI18N
        exelbtn.setText("Ruaj ne Excel");
        exelbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exelbtnActionPerformed(evt);
            }
        });
        jMenu1.add(exelbtn);

        pdfbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/pdf.png"))); // NOI18N
        pdfbtn.setText("Ruaj ne PDF");
        pdfbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfbtnActionPerformed(evt);
            }
        });
        jMenu1.add(pdfbtn);

        printobtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/hp_printer.png"))); // NOI18N
        printobtn.setText("Printo");
        printobtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printobtnActionPerformed(evt);
            }
        });
        jMenu1.add(printobtn);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1309, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(anulobtn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(anulobtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void QytetiChooserItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_QytetiChooserItemStateChanged
        Qyteti q = (Qyteti) QytetiChooser.getSelectedItem();
        //        List<Rruga> rrugaList;
        //        try {
        //            rrugaList = rrugaR.findByQytetiId(q.getQytetiID());
        //            populateChooserRruga(rrugaList);
        //        } catch (RrugaException ex) {
        //            Logger.getLogger(ShtoStudentin.class.getName()).log(Level.SEVERE, null, ex);
        //        }
        zipkoditxt.setText("" + q.getZipKodi());
    }//GEN-LAST:event_QytetiChooserItemStateChanged

    private void QytetiChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QytetiChooserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_QytetiChooserActionPerformed

    private void RadioAktivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioAktivActionPerformed
        eshteAktiv = true;
    }//GEN-LAST:event_RadioAktivActionPerformed

    private void kombesiatxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kombesiatxtKeyTyped
        char c = evt.getKeyChar();
        if (!((isAlphabetic((int) c)) || c == KeyEvent.VK_BACKSPACE || c == KeyEvent.VK_SHIFT)) {
            evt.consume();
        }
        String number =  kombesiatxt.getText();
        if(number.length()>29){
            JOptionPane.showMessageDialog(this, "Keni tejakaluar numrin e lejuar te shkronjave(30)", "Gabim" , 0);
            evt.consume();
        }
    }//GEN-LAST:event_kombesiatxtKeyTyped

    private void RadioJoAktivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioJoAktivActionPerformed
        eshteAktiv = false;
    }//GEN-LAST:event_RadioJoAktivActionPerformed

    private void emailtxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailtxtFocusGained
        if (emritxt.getText().equals("") || mbiemritxt.getText().equals("")) {
            return;
        }
        String email = emritxt.getText().toLowerCase() + "." + mbiemritxt.getText().toLowerCase() + "@ecenter.com";
        emailtxt.setText(email);
    }//GEN-LAST:event_emailtxtFocusGained

    private void emailtxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailtxtKeyTyped

    }//GEN-LAST:event_emailtxtKeyTyped

    private void emritxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emritxtKeyTyped
        char c = evt.getKeyChar();
        if (!((isAlphabetic((int) c)) || c == KeyEvent.VK_BACKSPACE || c == KeyEvent.VK_SHIFT)) {
            evt.consume();
        }
        String number =  emritxt.getText();
        if(number.length()>29){
            JOptionPane.showMessageDialog(this, "Keni tejakaluar numrin e lejuar te shkronjave(30)", "Gabim" , 0);
            evt.consume();
        }
    }//GEN-LAST:event_emritxtKeyTyped

    private void mbiemritxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mbiemritxtKeyTyped
        char c = evt.getKeyChar();
        if (!((isAlphabetic((int) c)) || c == KeyEvent.VK_BACKSPACE || c == KeyEvent.VK_SHIFT)) {
            evt.consume();
        }
        String number =  mbiemritxt.getText();
        if(number.length()>29){
            JOptionPane.showMessageDialog(this, "Keni tejakaluar numrin e lejuar te shkronjave(30)", "Gabim" , 0);
            evt.consume();
        }
    }//GEN-LAST:event_mbiemritxtKeyTyped

    private void teltxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_teltxtFocusGained
        telgabimlb.setVisible(false);
    }//GEN-LAST:event_teltxtFocusGained

    private void teltxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_teltxtFocusLost
        NumriTelValidator v = new NumriTelValidator();
        if (!v.validate(teltxt.getText().trim())) {
            telgabimlb.setVisible(true);
        }
    }//GEN-LAST:event_teltxtFocusLost

    private void teltxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teltxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teltxtActionPerformed

    private void zipkoditxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zipkoditxtKeyTyped
        char c = evt.getKeyChar();
        if (!((isDigit(c)) || c == KeyEvent.VK_BACKSPACE || c == KeyEvent.VK_SHIFT)) {
            evt.consume();
        }
    }//GEN-LAST:event_zipkoditxtKeyTyped

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        try {
            int row = tabela.getSelectedRow();
            Date d = new Date();
            DateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
            Date dob = dobtxt.getDate();
            if(!(dob == null)){
                LocalDate start = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate end = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                long years = ChronoUnit.YEARS.between(start, end);
                if(years < 21 || years > 65){
                    JOptionPane.showMessageDialog(this, "Mosha e profesorit nuk është e lejueshme \nMosha lejuar > 21 dhe < 65", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (emritxt.getText().equals("") || mbiemritxt.getText().equals("") || kombesiatxt.getText().equals("") || QytetiChooser.getSelectedItem() == null
                        || dobtxt.getDate() == null || emailtxt.getText().equals("") || zipkoditxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Ju lutem plotesoni çdo fushë !!!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (row == -1) {
                        Qyteti q = (Qyteti) QytetiChooser.getSelectedItem();
                        Perdoruesi p = new Perdoruesi();
                        Profesori prof = new Profesori();
                        prof.setRruga(rrugatxt.getText());
                        prof.setEmri(emritxt.getText());
                        prof.setMbiemri(mbiemritxt.getText());
                        prof.setKombesia(kombesiatxt.getText());
                        prof.setDateLindja(dobtxt.getDate());
                        prof.setEmail(emailtxt.getText());
                        prof.setQytetiid((Qyteti) QytetiChooser.getSelectedItem());
                        prof.setRoli("Profesor");
                        prof.setStatusi(eshteAktiv);
                        prof.setNrPersonal(Long.parseLong(nrPersonaltxt.getText()));
                        prof.setGjiniaid((Gjinia) GjiniaChooser.getSelectedItem());
                        prof.setQytetiid(q);
                        p.setEmriPerdoruesit(emailtxt.getText());
                        p.setPasswordiPerdoruesit("123456");
                        p.setRoli("Profesor");
                        if (!exsist()) {
                            profR.create(prof);
                            tel.setNrtel(teltxt.getText());
                            tel.setProfesoriID(prof);
                            telR.create(tel);
                            JOptionPane.showMessageDialog(this, "E dhëna u ruajt me sukses !");
                        } else {
                            JOptionPane.showMessageDialog(this, "Profesori eshte regjistruar me heret!");
                        }
                    } else {
                        Qyteti q = (Qyteti) QytetiChooser.getSelectedItem();
                        Profesori prof = this.profesoriTable.getProfesori(row);
                        prof.setRruga(rrugatxt.getText());
                        prof.setEmri(emritxt.getText());
                        prof.setMbiemri(mbiemritxt.getText());
                        prof.setKombesia(kombesiatxt.getText());
                        prof.setDateLindja(dobtxt.getDate());
                        prof.setEmail(emailtxt.getText());
                        prof.setQytetiid((Qyteti) QytetiChooser.getSelectedItem());
                        prof.setRoli("Profesor");
                        prof.setStatusi(eshteAktiv);
                        prof.setNrPersonal(Long.parseLong(nrPersonaltxt.getText()));
                        prof.setGjiniaid((Gjinia) GjiniaChooser.getSelectedItem());
                        prof.setQytetiid(q);
                        tel.setNrtel(teltxt.getText());
                        tel.setProfesoriID(prof);
                        telR.create(tel);

                        profR.edit(prof);
                        JOptionPane.showMessageDialog(this, "E dhëna u editua me sukses");
                    }
                    emptyObject();
                    tabelaLoad();
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Data e lindjes gabim!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ProfesoriException | TelProfesoriException pe) {
            JOptionPane.showMessageDialog(this, pe.getMessage());
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    public boolean exsist() {
        List<Profesori> lista = profR.findAll();
        long nrp = Long.parseLong(nrPersonaltxt.getText());
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNrPersonal() == nrp) {
                return true;
            }
        }
        return false;
    }

    private void anulobtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anulobtnActionPerformed
        tabela.clearSelection();
        emptyObject();
    }//GEN-LAST:event_anulobtnActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked

    }//GEN-LAST:event_tabelaMouseClicked

    private void nrPersonaltxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nrPersonaltxtKeyTyped
        char c = evt.getKeyChar();
        if (!((isDigit(c)) || c == KeyEvent.VK_BACKSPACE || c == KeyEvent.VK_SHIFT)) {
            evt.consume();
        }
        String number =  nrPersonaltxt.getText();
        //vec nese eshte number dhe eshte 3 shifror e qet kete jOptionPane
        if(number.length()>9 && isDigit(c)){
            JOptionPane.showMessageDialog(this, "Keni tejakaluar numrin e lejuar te shkronjave(10)", "Gabim" , 0);
            evt.consume();
        }
    }//GEN-LAST:event_nrPersonaltxtKeyTyped

    private void teltxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_teltxtKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_teltxtKeyTyped

    private void exelbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exelbtnActionPerformed
       try {
            JFileChooser fch = new JFileChooser();
            fch.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fch.setPreferredSize(new Dimension(700, 500));
            fch.setDialogTitle("Ruaj raportin");
            int returnVal = fch.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=EducationCenter;user=sa;password=Avdisuka12");
                String report = "C:\\Users\\Arbër Suka\\Desktop\\Projekti LabCourse\\EducationCenter\\ProfesoriReport.jrxml";
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
    }//GEN-LAST:event_exelbtnActionPerformed

    private void pdfbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdfbtnActionPerformed
       try{
           JFileChooser fch = new JFileChooser();
           fch.setFileSelectionMode(JFileChooser.FILES_ONLY);
           fch.setPreferredSize(new Dimension(700, 500));    
           fch.setDialogTitle("Ruaj raportin");
           int returnVal = fch.showSaveDialog(null);
           if (returnVal == JFileChooser.APPROVE_OPTION) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=EducationCenter;user=sa;password=Avdisuka12");
                String report="C:\\Users\\Arbër Suka\\Desktop\\Projekti LabCourse\\EducationCenter\\ProfesoriReport.jrxml";
                JasperReport jr=JasperCompileManager.compileReport(report);
                JasperPrint jp=JasperFillManager.fillReport(jr, null,con);
                JasperExportManager.exportReportToPdfFile(jp,fch.getSelectedFile().getAbsolutePath()+".pdf");
 		JOptionPane.showMessageDialog(this, "Raporti u ruajt ne PDF");
           }
        }catch(ClassNotFoundException | SQLException | JRException e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_pdfbtnActionPerformed

    private void printobtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printobtnActionPerformed
        MessageFormat header = new MessageFormat("Tabela Profesori");
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");

        try{
            tabela.print(JTable.PrintMode.FIT_WIDTH, header, footer);

        }
        catch(java.awt.print.PrinterException e) {
            System.err.format("Cannot print %s%n",e.getMessage());
        }
    }//GEN-LAST:event_printobtnActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
         try{
            int row = tabela.getSelectedRow();
            if(row > -1){
                Object [] ob = {"Po","Jo"};
                int i = javax.swing.JOptionPane.showOptionDialog(this, "A dëshironi ta fshini ?", "Fshirja", JOptionPane.OK_OPTION,JOptionPane.QUESTION_MESSAGE, null, ob, ob[1]);
                if(i== 0){
                    Profesori prof = this.profesoriTable.getProfesori(row);
                    profR.remove(prof);
                    tabelaLoad();
                    emptyObject();
                    JOptionPane.showMessageDialog(this, "E dhëna është fshir me sukses !");
                }
            }
            else{
                JOptionPane.showMessageDialog(this,"Nuk keni selektuar asgjë për të fshir !");
            }
        }catch(ProfesoriException pe){
            JOptionPane.showMessageDialog(this, pe.getMessage());
        }
    }//GEN-LAST:event_deletebtnActionPerformed

    private void filtro() {    
        sorter = new TableRowSorter<ProfesoriTableModel>(profesoriTable);    
        tabela.setRowSorter(sorter);      
        try {
            rf = RowFilter.regexFilter(filtrotxt.getText(),0);
           
        } 
        catch(java.util.regex.PatternSyntaxException pe) {
            return;
        }
        sorter.setRowFilter(rf);   
    }
    
    private void filtrotxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtrotxtKeyReleased
        filtro();
    }//GEN-LAST:event_filtrotxtKeyReleased

    private void mbiemritxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mbiemritxtKeyReleased
        String t =  mbiemritxt.getText();
        if(t.length() > 30){
            evt.consume();
            JOptionPane.showMessageDialog(this, "Keni tejkaluar numrin e lejuar te karaktereve (30)");
        }
    }//GEN-LAST:event_mbiemritxtKeyReleased

    private void kombesiatxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kombesiatxtKeyReleased
        String t = kombesiatxt.getText();
        if(t.length() > 20){
            evt.consume();
            JOptionPane.showMessageDialog(this, "Keni tejkaluar numrin e lejuar te karaktereve (20)");
        }
    }//GEN-LAST:event_kombesiatxtKeyReleased

    private void rrugatxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rrugatxtKeyTyped
        char c = evt.getKeyChar();
        String number =  rrugatxt.getText();
        if(number.length()>29){
            JOptionPane.showMessageDialog(this, "Keni tejakaluar numrin e lejuar te shkronjave(30)", "Gabim" , 0);
            evt.consume();
        }
    }//GEN-LAST:event_rrugatxtKeyTyped

    private void dobtxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dobtxtFocusLost
        
    }//GEN-LAST:event_dobtxtFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Gjinia> GjiniaChooser;
    private javax.swing.JComboBox<Qyteti> QytetiChooser;
    private javax.swing.JRadioButton RadioAktiv;
    private javax.swing.JRadioButton RadioJoAktiv;
    private javax.swing.JButton anulobtn;
    private javax.swing.JButton deletebtn;
    private com.toedter.calendar.JDateChooser dobtxt;
    private javax.swing.JTextField emailtxt;
    private javax.swing.JTextField emritxt;
    private javax.swing.JMenuItem exelbtn;
    private javax.swing.JTextField filtrotxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kombesiatxt;
    private javax.swing.JTextField mbiemritxt;
    private javax.swing.JTextField nrPersonaltxt;
    private javax.swing.JMenuItem pdfbtn;
    private javax.swing.JMenuItem printobtn;
    private javax.swing.JTextField rrugatxt;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTable tabela;
    private javax.swing.JLabel telgabimlb;
    private javax.swing.JTextField teltxt;
    private javax.swing.JTextField zipkoditxt;
    // End of variables declaration//GEN-END:variables
}
