/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BL.Adminstrata;
import BL.Gjinia;
import BL.Perdoruesi;
import BL.Qyteti;
import BL.TelAdministrata;
import DAL.AdministrataException;
import DAL.AdministrataRepository;
import DAL.AdminstrataInterface;
import DAL.GjiniaInterface;
import DAL.GjiniaRepository;
import DAL.QytetiInterface;
import DAL.QytetiRepository;
import DAL.TelAdministrataException;
import DAL.TelAdministrataInterface;
import DAL.TelAdministrataRepository;
import Model.AdministrataTableModel;
import Model.KomboBox.GjiniaComboBoxModel;
import Model.KomboBox.GrupiComboBoxModel;
import Model.KomboBox.QytetiComboBoxModel;
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
public class AdministrataFrame extends javax.swing.JInternalFrame {

    
    TelAdministrataInterface telR = new TelAdministrataRepository();//ne interface jane metodat kurse ne repositor jan te implementunu
    GjiniaInterface gjiniaR = new GjiniaRepository();
    QytetiInterface qytetiR = new QytetiRepository();
    TelAdministrata tel = new TelAdministrata();
    AdministrataTableModel administrataTable = new AdministrataTableModel();
    AdminstrataInterface administrataR = new AdministrataRepository();
    List<Adminstrata> lista;//nje list e that qe mperban objekte te administrates
    RowFilter<AdministrataTableModel, Adminstrata> rf = null;
    TableRowSorter sorter = null;
    GjiniaComboBoxModel gjiniamodelcombo;
    QytetiComboBoxModel qytetiModelKombo;
    GrupiComboBoxModel grupiModelKombo;
    boolean eshteAktiv;
    ButtonGroup b;

    /**
     * Creates new form AdministrataFrame
     */
    public AdministrataFrame() {
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
        this.GjiniaChooser.setModel(gjiniamodelcombo);
    }

    public void populateChooserQyteti() {
        List<Qyteti> qID = qytetiR.findAll();
        qytetiModelKombo = new QytetiComboBoxModel(qID);
        this.QytetiChooser.setModel(qytetiModelKombo);
    }
    
      private void filtro() {    
        sorter = new TableRowSorter<AdministrataTableModel>(administrataTable);    
        tabela.setRowSorter(sorter);      
        try {
            rf = RowFilter.regexFilter(filtrotxt.getText(),0);
           
        } 
        catch(java.util.regex.PatternSyntaxException pe) {
            return;
        }
        sorter.setRowFilter(rf);   
    }

    public void emptyObject() {//e thirrum kur e kryjm ni veprim edhe ose kur dojm me i bo texfield null 
        tabela.clearSelection();
        nrPersonaltxt.setEditable(true);
        emritxt.setText("");
        mbiemritxt.setText("");
        dobtxt.setDate(null);
        kombesiatxt.setText("");
        emailtxt.setText("");
        zipkoditxt.setText("");
        teltxt.setText("");
        rrugatxt.setText("");
        pozitatxt.setText("");
        nrPersonaltxt.setText("");
        populateChooserQyteti();
        populateChooserGjinia();
        b.clearSelection();
    }

    private void tabelaLoad() {//e thirrum kur haper jFrame dhe e bush tabelen me te dhena nga db
        lista = administrataR.findAll();
        administrataTable.add(lista);
        tabela.setModel(administrataTable);
        administrataTable.fireTableDataChanged();//njofton tabelen nqofse ka ndryshu naj qelzin dhe e bene update;
        tabelaSelectedIndexChange();

    }

    private void tabelaSelectedIndexChange() {
        final ListSelectionModel rowSM = tabela.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {//Klase anonime kur ta ndrrojm klikimin ne tabel me i mnush jfieldat me te dhena

            public void valueChanged(ListSelectionEvent Ise) {
                if (Ise.getValueIsAdjusting()) {
                    return;
                }
                ListSelectionModel rowSM = (ListSelectionModel) Ise.getSource();
                int selectedIndex = rowSM.getAnchorSelectionIndex();
                if (selectedIndex > -1) {
                    Adminstrata admin = administrataTable.getAdminstrata(selectedIndex);
                    emritxt.setText(admin.getEmri());
                    mbiemritxt.setText(admin.getMbiemri());
                    kombesiatxt.setText(admin.getKombesia());
                    dobtxt.setDate(admin.getDateLindja());
                    emailtxt.setText(admin.getEmail());
                    if (admin.getStatusi()) {
                        RadioAktiv.setSelected(true);
                    } else {
                        RadioJoAktiv.setSelected(true);
                    }
                    pozitatxt.setText(admin.getPozita());
                    nrPersonaltxt.setText("" + admin.getNrPersonal());
                    teltxt.setText("" + gjejTel(admin));
                    rrugatxt.setText(admin.getRruga());
                    QytetiChooser.setSelectedItem(admin.getQytetiid());
                    zipkoditxt.setText("" + ((Qyteti)QytetiChooser.getSelectedItem()).getZipKodi());
                    GjiniaChooser.setSelectedItem(admin.getGjiniaid());
                    QytetiChooser.setSelectedItem(admin.getQytetiid());
                    GjiniaChooser.repaint();
                    QytetiChooser.repaint();
                    nrPersonaltxt.setEditable(false);
                }
            }
        });
    }

    public TelAdministrata gjejTel(Adminstrata admin) {
        List<TelAdministrata> tel = null;
        try {
            tel = telR.findByAdministrataId(admin.getAdminstrataID());
        } catch (TelAdministrataException ex) {
            JOptionPane.showMessageDialog(this, "Nuk u gjet nr telefonit !!!", "Gabim", 0);
        }
        if(!(tel == null))
             return tel.get(0);
        return null;
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
        pozitatxt = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        nrPersonaltxt = new javax.swing.JTextField();
        filtrotxt = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        dobtxt = new com.toedter.calendar.JDateChooser();
        saveBtn = new javax.swing.JButton();
        anulobtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        deletebtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        exelbtn = new javax.swing.JMenuItem();
        pdfBtn = new javax.swing.JMenuItem();
        printoBtn = new javax.swing.JMenuItem();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Administrata");
        setToolTipText("");

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("Gjinia: ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 60, 30));

        telgabimlb.setForeground(new java.awt.Color(255, 0, 0));
        telgabimlb.setText("Numri i telefonit gabim !!!");
        jPanel1.add(telgabimlb, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, -1, -1));

        jLabel9.setText("Statusi: ");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 50, 65, 30));

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
        jPanel1.add(QytetiChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 100, 30));

        jPanel1.add(GjiniaChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 100, 30));

        RadioAktiv.setText("Aktiv");
        RadioAktiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioAktivActionPerformed(evt);
            }
        });
        jPanel1.add(RadioAktiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 50, -1, -1));

        kombesiatxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kombesiatxtKeyTyped(evt);
            }
        });
        jPanel1.add(kombesiatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 114, 30));

        RadioJoAktiv.setText("Jo Aktiv");
        RadioJoAktiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioJoAktivActionPerformed(evt);
            }
        });
        jPanel1.add(RadioJoAktiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 50, -1, -1));

        jLabel7.setText("Datelindja: ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, -1, 30));

        jLabel8.setText("Email:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, 55, 30));

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
        jPanel1.add(emailtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 50, 131, 30));

        jLabel11.setText("Zip Kodi: ");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, 90, 30));

        jLabel1.setText("Emri: ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 11, 50, 30));

        emritxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                emritxtKeyTyped(evt);
            }
        });
        jPanel1.add(emritxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 100, 30));

        jLabel2.setText("Mbiemri: ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 96, 30));

        mbiemritxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                mbiemritxtKeyTyped(evt);
            }
        });
        jPanel1.add(mbiemritxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 100, 30));

        jLabel3.setText("Telefoni:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 80, 30));

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
        jPanel1.add(teltxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 100, 30));

        jLabel4.setText("Qyteti: ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 46, 70, 30));

        jLabel5.setText("Kombesia: ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 10, 90, 30));

        zipkoditxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                zipkoditxtKeyTyped(evt);
            }
        });
        jPanel1.add(zipkoditxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 100, 30));

        jLabel12.setText("Rruga: ");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 96, 30));
        jPanel1.add(rrugatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 100, 30));

        jLabel10.setText("Pozita: ");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 70, 30));

        pozitatxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pozitatxtKeyTyped(evt);
            }
        });
        jPanel1.add(pozitatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 102, 30));

        jLabel13.setText("Nr Personal:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 110, 30));

        nrPersonaltxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nrPersonaltxtKeyTyped(evt);
            }
        });
        jPanel1.add(nrPersonaltxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 100, 30));

        filtrotxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtrotxtKeyReleased(evt);
            }
        });
        jPanel1.add(filtrotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 90, 130, 30));

        jLabel14.setText("Filtro: ");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 90, 80, 30));
        jPanel1.add(dobtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 130, -1));

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
        jScrollPane1.setViewportView(tabela);

        deletebtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deletebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/cancel.png"))); // NOI18N
        deletebtn.setText("Fshij");
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });

        jMenu1.setText("Ruaj ");

        exelbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/excel.png"))); // NOI18N
        exelbtn.setText("Ruaj ne Exel");
        exelbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exelbtnActionPerformed(evt);
            }
        });
        jMenu1.add(exelbtn);

        pdfBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/pdf.png"))); // NOI18N
        pdfBtn.setText("Ruaj ne PDF");
        pdfBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfBtnActionPerformed(evt);
            }
        });
        jMenu1.add(pdfBtn);

        printoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/hp_printer.png"))); // NOI18N
        printoBtn.setText("Printo");
        printoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printoBtnActionPerformed(evt);
            }
        });
        jMenu1.add(printoBtn);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1203, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(anulobtn, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void QytetiChooserItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_QytetiChooserItemStateChanged
        Qyteti q = (Qyteti) QytetiChooser.getSelectedItem();
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
    }//GEN-LAST:event_kombesiatxtKeyTyped

    private void RadioJoAktivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioJoAktivActionPerformed
        eshteAktiv = false;
    }//GEN-LAST:event_RadioJoAktivActionPerformed

    private void emailtxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailtxtFocusGained
        if (emritxt.getText().equals("") || mbiemritxt.getText().equals("")) {
            return;
        }
        String email = emritxt.getText().toLowerCase() + "-" + mbiemritxt.getText().toLowerCase() + "@ecenter.com";
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
        //vec nese eshte number dhe eshte 3 shifror e qet kete jOptionPane
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
        //vec nese eshte number dhe eshte 3 shifror e qet kete jOptionPane
        if(number.length()>29 ){
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
        evt.consume();
    }//GEN-LAST:event_zipkoditxtKeyTyped

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        try {
            int row = tabela.getSelectedRow();
            Date d = new Date();
            DateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
            Date dob = dobtxt.getDate();
            if(!(dob == null)){
                LocalDate start = dobtxt.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                LocalDate end = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                long years = ChronoUnit.YEARS.between(start, end);
                if(years < 18 || years > 65){
                    JOptionPane.showMessageDialog(this, "Mosha e administrates nuk është e lejueshme \nMosha lejuar > 18 dhe < 65", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (emritxt.getText().equals("") || mbiemritxt.getText().equals("") || kombesiatxt.getText().equals("") || QytetiChooser.getSelectedItem() == null
                        || dobtxt.getDate() == null || emailtxt.getText().equals("") || zipkoditxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Ju lutem plotesoni çdo fushë !!!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (row == -1) {
                        Qyteti q = (Qyteti) QytetiChooser.getSelectedItem();
                        Perdoruesi p = new Perdoruesi();
                        Adminstrata admin = new Adminstrata();
                        admin.setEmri(emritxt.getText());
                        admin.setRruga(rrugatxt.getText());
                        admin.setMbiemri(mbiemritxt.getText());
                        admin.setKombesia(kombesiatxt.getText());
                        admin.setDateLindja(dobtxt.getDate());
                        admin.setEmail(emailtxt.getText());
                        admin.setPozita(pozitatxt.getText());
                        admin.setNrPersonal(Long.parseLong(nrPersonaltxt.getText()));
                        admin.setQytetiid((Qyteti) QytetiChooser.getSelectedItem());
                        admin.setRoli("Administrat");
                        admin.setStatusi(eshteAktiv);
                        admin.setGjiniaid((Gjinia) GjiniaChooser.getSelectedItem());
                        admin.setQytetiid(q);
                        p.setEmriPerdoruesit(emailtxt.getText());
                        p.setPasswordiPerdoruesit("123456");
                        p.setRoli("Administrat");
                        if (!exsist()) {
                            administrataR.create(admin);
                            tel.setNrTel(teltxt.getText());
                            tel.setAdministrataID(admin);
                            telR.create(tel);
                            JOptionPane.showMessageDialog(this, "E dhëna u ruajt me sukses !");
                        } else {
                            JOptionPane.showMessageDialog(this, "Ky punonjes eshte i regjistruar !!!");
                        }

                    } else {
                        Qyteti q = (Qyteti) QytetiChooser.getSelectedItem();
                        Adminstrata admin = this.administrataTable.getAdminstrata(row);
                        admin.setRruga(rrugatxt.getText());
                        admin.setEmri(emritxt.getText());
                        admin.setMbiemri(mbiemritxt.getText());
                        admin.setKombesia(kombesiatxt.getText());
                        admin.setDateLindja(dobtxt.getDate());
                        admin.setEmail(emailtxt.getText());
                        admin.setPozita(pozitatxt.getText());
                        admin.setNrPersonal(Long.parseLong(nrPersonaltxt.getText()));
                        admin.setQytetiid((Qyteti) QytetiChooser.getSelectedItem());
                        admin.setRoli("Administrat");
                        admin.setStatusi(eshteAktiv);
                        admin.setGjiniaid((Gjinia) GjiniaChooser.getSelectedItem());
                        admin.setQytetiid(q);
                        tel.setNrTel(teltxt.getText());
                        tel.setAdministrataID(admin);
                        telR.edit(tel);

                        administrataR.edit(admin);
                        JOptionPane.showMessageDialog(this, "E dhëna u editua me sukses");
                    }
                    emptyObject();
                    tabelaLoad();
                    nrPersonaltxt.setEnabled(true);
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Data e lindjes gabim!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (AdministrataException | TelAdministrataException pe) {
            JOptionPane.showMessageDialog(this, pe.getMessage());
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    public boolean exsist() {
        List<Adminstrata> lista = administrataR.findAll();
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
        nrPersonaltxt.setEditable(true);
    }//GEN-LAST:event_anulobtnActionPerformed

    private void nrPersonaltxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nrPersonaltxtKeyTyped
        char c = evt.getKeyChar();
        if (!((isDigit(c)) || c == KeyEvent.VK_BACKSPACE || c == KeyEvent.VK_SHIFT)) {
            evt.consume();
        }
        String number =  nrPersonaltxt.getText();
        if(number.length()>9 ){
            JOptionPane.showMessageDialog(this, "Keni tejakaluar numrin e lejuar te shkronjave(10)", "Gabim" , 0);
            evt.consume();
        }
    }//GEN-LAST:event_nrPersonaltxtKeyTyped

    private void pozitatxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pozitatxtKeyTyped
        char c = evt.getKeyChar();
        if (!((isAlphabetic((int) c)) || c == KeyEvent.VK_BACKSPACE || c == KeyEvent.VK_SHIFT)) {
            evt.consume();
        }
        String number =  pozitatxt.getText();
        if(number.length()>29){
            JOptionPane.showMessageDialog(this, "Keni tejakaluar numrin e lejuar te shkronjave(30)", "Gabim" , 0);
            evt.consume();
        }
    }//GEN-LAST:event_pozitatxtKeyTyped

    private void teltxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_teltxtKeyTyped

    }//GEN-LAST:event_teltxtKeyTyped

    private void pdfBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdfBtnActionPerformed
       try{
           JFileChooser fch = new JFileChooser();
           fch.setFileSelectionMode(JFileChooser.FILES_ONLY);
           fch.setPreferredSize(new Dimension(700, 500));    
           fch.setDialogTitle("Ruaj raportin");
           int returnVal = fch.showSaveDialog(null);
           if (returnVal == JFileChooser.APPROVE_OPTION) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=EducationCenter;user=sa;password=Avdisuka12");
                String report="C:\\Users\\Arbër Suka\\Desktop\\Projekti LabCourse\\EducationCenter\\AdministrataReport.jrxml";
                JasperReport jr=JasperCompileManager.compileReport(report);
                JasperPrint jp=JasperFillManager.fillReport(jr, null,con);
                JasperExportManager.exportReportToPdfFile(jp,fch.getSelectedFile().getAbsolutePath()+".pdf");
 		JOptionPane.showMessageDialog(this, "Raporti u ruajt ne PDF");
           }
        }catch(ClassNotFoundException | SQLException | JRException e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_pdfBtnActionPerformed

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
                String report = "C:\\Users\\Arbër Suka\\Desktop\\Projekti LabCourse\\EducationCenter\\AdministrataReport.jrxml";
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

    private void printoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printoBtnActionPerformed
        MessageFormat header = new MessageFormat("Tabela Dhëmbi");
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");

        try{
            tabela.print(JTable.PrintMode.NORMAL, header, footer);

        }
        catch(java.awt.print.PrinterException e) {
            System.err.format("Cannot print %s%n",e.getMessage());
        }
    }//GEN-LAST:event_printoBtnActionPerformed

    private void filtrotxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtrotxtKeyReleased
        filtro();
    }//GEN-LAST:event_filtrotxtKeyReleased

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
         try{
            int row = tabela.getSelectedRow();
            if(row > -1){
                Object [] ob = {"Po","Jo"};
                int i = javax.swing.JOptionPane.showOptionDialog(this, "A dëshironi ta fshini ?", "Fshirja", JOptionPane.OK_OPTION,JOptionPane.QUESTION_MESSAGE, null, ob, ob[1]);
                if(i== 0){
                    Adminstrata admin = this.administrataTable.getAdminstrata(row);
                    administrataR.remove(admin);
                    tabelaLoad();
                    emptyObject();
                    JOptionPane.showMessageDialog(this, "E dhëna është fshir me sukses !");
                }
            }
            else{
                JOptionPane.showMessageDialog(this,"Nuk keni selektuar asgjë për të fshir !");
            }
        }catch(AdministrataException pe){
            JOptionPane.showMessageDialog(this, pe.getMessage());
        }
    }//GEN-LAST:event_deletebtnActionPerformed


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
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JMenuItem pdfBtn;
    private javax.swing.JTextField pozitatxt;
    private javax.swing.JMenuItem printoBtn;
    private javax.swing.JTextField rrugatxt;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTable tabela;
    private javax.swing.JLabel telgabimlb;
    private javax.swing.JTextField teltxt;
    private javax.swing.JTextField zipkoditxt;
    // End of variables declaration//GEN-END:variables
}
