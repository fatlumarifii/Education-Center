/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BL.Gjinia;
import BL.Grupi;
import BL.Perdoruesi;
import BL.Qyteti;
import BL.Studenti;
import BL.TelStudenti;
import DAL.GjiniaInterface;
import DAL.GjiniaRepository;
import DAL.GrupiInterface;
import DAL.GrupiRepository;
import DAL.PerdoruesiException;
import DAL.PerdoruesiInterface;
import DAL.PerdoruesiRepository;
import DAL.QytetiInterface;
import DAL.QytetiRepository;

import DAL.StudentiException;
import DAL.StudentiInterface;
import DAL.StudentiRepository;
import DAL.TelStudentiException;
import DAL.TelStudentiInterface;
import DAL.TelStudentiRepository;
import Model.KomboBox.GjiniaComboBoxModel;
import Model.KomboBox.GrupiComboBoxModel;
import Model.KomboBox.QytetiComboBoxModel;

import Model.StudentiTableModel;
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
import java.time.Period;
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
public class StudentiFrame extends javax.swing.JInternalFrame {

    StudentiTableModel studentiTable = new StudentiTableModel();

    PerdoruesiInterface perdoR = new PerdoruesiRepository();
    TelStudentiInterface telR = new TelStudentiRepository();
    StudentiInterface studentiR = new StudentiRepository();
    GrupiInterface grupiR = new GrupiRepository();
    GjiniaInterface gjiniaR = new GjiniaRepository();
    QytetiInterface qytetiR = new QytetiRepository();
    TelStudenti tel = new TelStudenti();
    List<Studenti> lista;
    RowFilter<StudentiTableModel, Studenti> rf = null;
    TableRowSorter sorter = null;
    GjiniaComboBoxModel gjiniamodelcombo;
    QytetiComboBoxModel qytetiModelKombo;
    GrupiComboBoxModel grupiModelKombo;
    boolean eshteAktiv;

    /**
     * Creates new form ShtoStudentin
     */
    public StudentiFrame() {
        initComponents();
        tabelaLoad();
        populateChooserGjinia();
        populateChooserGrupi();
        populateChooserQyteti();
        grupButton();
        telgabimlb.setVisible(false);
    }
    ButtonGroup b;

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

    public void populateChooserGrupi() {
        List<Grupi> gID = grupiR.findAll();
        grupiModelKombo = new GrupiComboBoxModel(gID);
        this.GrupiChooser.setModel(grupiModelKombo);
    }

    public void populateChooserQyteti() {
        List<Qyteti> qID = qytetiR.findAll();
        qytetiModelKombo = new QytetiComboBoxModel(qID);
        this.QytetiChooser.setModel(qytetiModelKombo);
    }

    private void filtro() {
        sorter = new TableRowSorter<StudentiTableModel>(studentiTable);
        tabela.setRowSorter(sorter);
        try {
            rf = RowFilter.regexFilter(filtrotxt.getText(), 0);

        } catch (java.util.regex.PatternSyntaxException pe) {
            return;
        }
        sorter.setRowFilter(rf);
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
        populateChooserGrupi();
        populateChooserGjinia();
        b.clearSelection();
    }

    private void tabelaLoad() {
        lista = studentiR.findAll();
        studentiTable.add(lista);
        tabela.setModel(studentiTable);
        studentiTable.fireTableDataChanged();
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
                    Studenti student = studentiTable.getStudenti(selectedIndex);
                    emritxt.setText(student.getEmri());
                    mbiemritxt.setText(student.getMbiemri());
                    dobtxt.setDate(student.getDitelindja());
                    kombesiatxt.setText(student.getKombesia());
                    Qyteti q = student.getQytetiid();
                    zipkoditxt.setText("" + q.getZipKodi());
                    rrugatxt.setText(student.getRruga());
                    emailtxt.setText(student.getEmail());
                    nrPersonaltxt.setText("" + student.getNrPersonal());

                    GjiniaChooser.setSelectedItem(student.getGjiniaid());
                    GrupiChooser.setSelectedItem(student.getGrupID());
                    QytetiChooser.setSelectedItem(q);
                    QytetiChooser.repaint();
                    GrupiChooser.repaint();
                    GjiniaChooser.repaint();
                    teltxt.setText("" + gjejTel(student));

                    if (student.getStatusi()) {
                        RadioAktiv.setSelected(true);
                    } else {
                        RadioJoAktiv.setSelected(true);
                    }
                }
            }
        });
    }

    public TelStudenti gjejTel(Studenti prof) {
        List<TelStudenti> tel = null;
        try {
            tel = telR.findByStudentiId(prof.getStudentiID());
        } catch (TelStudentiException ex) {
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

        saveBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        anulobtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        GrupiChooser = new javax.swing.JComboBox<>();
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
        jLabel10 = new javax.swing.JLabel();
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
        jLabel13 = new javax.swing.JLabel();
        nrPersonaltxt = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        filtrotxt = new javax.swing.JTextField();
        deletebtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        exelSave = new javax.swing.JMenuItem();
        pdfSave = new javax.swing.JMenuItem();
        printobtn = new javax.swing.JMenuItem();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Studenti");
        setMinimumSize(new java.awt.Dimension(1078, 515));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        saveBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        saveBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/save.png"))); // NOI18N
        saveBtn.setText("Ruaj");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
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

        anulobtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        anulobtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/cancel.png"))); // NOI18N
        anulobtn.setText("Anulo");
        anulobtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anulobtnActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("Gjinia: ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 60, 30));

        GrupiChooser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        GrupiChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GrupiChooserActionPerformed(evt);
            }
        });
        jPanel1.add(GrupiChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 84, 100, 30));

        telgabimlb.setForeground(new java.awt.Color(255, 0, 0));
        telgabimlb.setText("Numri i telefonit gabim !!!");
        jPanel1.add(telgabimlb, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 128, 206, -1));

        jLabel9.setText("Statusi: ");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 46, 65, 30));

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
        jPanel1.add(dobtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(621, 11, 131, 30));

        jPanel1.add(GjiniaChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 11, 100, 30));

        RadioAktiv.setText("Aktiv");
        RadioAktiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioAktivActionPerformed(evt);
            }
        });
        jPanel1.add(RadioAktiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(841, 50, -1, -1));

        kombesiatxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kombesiatxtKeyTyped(evt);
            }
        });
        jPanel1.add(kombesiatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(839, 11, 114, 30));

        RadioJoAktiv.setText("Jo Aktiv");
        RadioJoAktiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioJoAktivActionPerformed(evt);
            }
        });
        jPanel1.add(RadioJoAktiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 50, -1, -1));

        jLabel7.setText("Datelindja: ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(562, 11, -1, 30));

        jLabel8.setText("Email:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(562, 46, 55, 30));

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
        jPanel1.add(emailtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(621, 46, 131, 30));

        jLabel10.setText("Grupi:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 86, 50, 30));

        jLabel11.setText("Zip Kodi: ");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 60, 30));

        jLabel1.setText("Emri: ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 11, 50, 30));

        emritxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emritxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                emritxtKeyTyped(evt);
            }
        });
        jPanel1.add(emritxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 11, 100, 30));

        jLabel2.setText("Mbiemri: ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 11, 80, 30));

        mbiemritxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                mbiemritxtKeyTyped(evt);
            }
        });
        jPanel1.add(mbiemritxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 110, 30));

        jLabel3.setText("Telefoni:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 86, 80, 30));

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
        jPanel1.add(teltxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 110, 30));

        jLabel4.setText("Qyteti: ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 46, 50, 30));

        jLabel5.setText("Kombesia: ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 11, 65, 30));

        zipkoditxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                zipkoditxtKeyTyped(evt);
            }
        });
        jPanel1.add(zipkoditxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 46, 100, 30));

        jLabel12.setText("Rruga: ");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 48, 80, 30));

        rrugatxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rrugatxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rrugatxtKeyTyped(evt);
            }
        });
        jPanel1.add(rrugatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 110, 30));

        jLabel13.setText("Nr Personal: ");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, -1, 30));

        nrPersonaltxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nrPersonaltxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nrPersonaltxtKeyTyped(evt);
            }
        });
        jPanel1.add(nrPersonaltxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 85, 100, 32));

        jLabel14.setText("Filtro: ");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(562, 87, 40, 30));

        filtrotxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtrotxtKeyReleased(evt);
            }
        });
        jPanel1.add(filtrotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 87, 132, 30));

        deletebtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deletebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/cancel.png"))); // NOI18N
        deletebtn.setText("Fshij");
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });

        jMenu1.setText("Ruaj");

        exelSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/excel.png"))); // NOI18N
        exelSave.setText("Ruaj ne Exel");
        exelSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exelSaveActionPerformed(evt);
            }
        });
        jMenu1.add(exelSave);

        pdfSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/pdf.png"))); // NOI18N
        pdfSave.setText("Ruaj ne PDF");
        pdfSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfSaveActionPerformed(evt);
            }
        });
        jMenu1.add(pdfSave);

        printobtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/hp_printer.png"))); // NOI18N
        printobtn.setText("Printo ");
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(anulobtn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1216, Short.MAX_VALUE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
                if(years < 5 || years > 60){
                    JOptionPane.showMessageDialog(this, "Mosha e studentit nuk është e lejueshme \nMosha lejuar > 5 dhe < 60", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (emritxt.getText().equals("") || mbiemritxt.getText().equals("") || kombesiatxt.getText().equals("") || QytetiChooser.getSelectedItem() == null
                        || dobtxt.getDate() == null || emailtxt.getText().equals("") || zipkoditxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Ju lutem plotesoni çdo fushë !!!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    if (row == -1) {
                        Qyteti q = (Qyteti) QytetiChooser.getSelectedItem();
                        Studenti studenti = new Studenti();
                        Perdoruesi p = new Perdoruesi();
                        studenti.setRruga(rrugatxt.getText());
                        studenti.setEmri(emritxt.getText());
                        studenti.setMbiemri(mbiemritxt.getText());
                        studenti.setKombesia(kombesiatxt.getText());
                        studenti.setDitelindja(dobtxt.getDate());
                        studenti.setEmail(emailtxt.getText());
                        studenti.setQytetiid((Qyteti) QytetiChooser.getSelectedItem());
                        studenti.setRoli("Student");
                        studenti.setStatusi(eshteAktiv);
                        studenti.setNrPersonal(Long.parseLong(nrPersonaltxt.getText()));
                        studenti.setGjiniaid((Gjinia) GjiniaChooser.getSelectedItem());
                        studenti.setGrupID((Grupi) GrupiChooser.getSelectedItem());
                        studenti.setQytetiid(q);
                        p.setEmriPerdoruesit(emailtxt.getText());
                        p.setPasswordiPerdoruesit("123456");
                        p.setRoli("Student");
                        perdoR.create(p);
                        if (!exsist()) {
                            studentiR.create(studenti);
                            tel.setNrTel(teltxt.getText());
                            tel.setStudentiID(studenti);
                            telR.create(tel);
                            JOptionPane.showMessageDialog(this, "E dhëna u ruajt me sukses !");
                        } else {
                            JOptionPane.showMessageDialog(this, "Ky student eshte i regjistruar !!!");
                        }

                    } else {
                        Qyteti q = (Qyteti) QytetiChooser.getSelectedItem();
                        Studenti studenti = this.studentiTable.getStudenti(row);
                        studenti.setRruga(rrugatxt.getText());
                        studenti.setEmri(emritxt.getText());
                        studenti.setMbiemri(mbiemritxt.getText());
                        studenti.setKombesia(kombesiatxt.getText());
                        studenti.setDitelindja(dobtxt.getDate());
                        studenti.setEmail(emailtxt.getText());
                        studenti.setNrPersonal(Long.parseLong(nrPersonaltxt.getText()));
                        studenti.setQytetiid((Qyteti) QytetiChooser.getSelectedItem());
                        studenti.setGjiniaid((Gjinia) GjiniaChooser.getSelectedItem());
                        studenti.setGrupID((Grupi) GrupiChooser.getSelectedItem());
                        studenti.setRoli("Student");
                        studenti.setStatusi(eshteAktiv);
                        tel.setNrTel(teltxt.getText());
                        tel.setStudentiID(studenti);
                        telR.create(tel);
                        studentiR.edit(studenti);
                        JOptionPane.showMessageDialog(this, "E dhëna u editua me sukses");
                    }
                    emptyObject();
                    tabelaLoad();
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Data e lindjes gabim!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (StudentiException | TelStudentiException pe) {
            JOptionPane.showMessageDialog(this, pe.getMessage());
        } catch (PerdoruesiException ex) {
            Logger.getLogger(StudentiFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    public boolean exsist() {
        List<Studenti> lista = studentiR.findAll();
        long nrp = Long.parseLong(nrPersonaltxt.getText());
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNrPersonal() == nrp) {
                return true;
            }
        }
        return false;
    }

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked


    }//GEN-LAST:event_tabelaMouseClicked

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

    private void kombesiatxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kombesiatxtKeyTyped
        char c = evt.getKeyChar();
        if (!((isAlphabetic((int) c)) || c == KeyEvent.VK_BACKSPACE || c == KeyEvent.VK_SHIFT)) {
            evt.consume();
        }
        String number =  kombesiatxt.getText();
        if(number.length()>29 ){
            JOptionPane.showMessageDialog(this, "Keni tejakaluar numrin e lejuar te shkronjave(30)", "Gabim" , 0);
            evt.consume();
        }
    }//GEN-LAST:event_kombesiatxtKeyTyped

    private void zipkoditxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zipkoditxtKeyTyped
        char c = evt.getKeyChar();
        evt.consume();
    }//GEN-LAST:event_zipkoditxtKeyTyped

    private void emailtxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailtxtKeyTyped
        char c = evt.getKeyChar();
        evt.consume();
    }//GEN-LAST:event_emailtxtKeyTyped

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        tabela.clearSelection();

    }//GEN-LAST:event_formMouseClicked

    private void anulobtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anulobtnActionPerformed
        tabela.clearSelection();
        emptyObject();
    }//GEN-LAST:event_anulobtnActionPerformed

    private void emailtxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailtxtFocusGained
        if (emritxt.getText().equals("") || mbiemritxt.getText().equals("")) {
            return;
        }
        char[] emri = emritxt.getText().toLowerCase().toCharArray();
        char[] mbiemri = mbiemritxt.getText().toLowerCase().toCharArray();
        String email = "" + emri[0] + mbiemri[0] + (lista.size() + 1) + "@ecenter.com";
        emailtxt.setText(email);
    }//GEN-LAST:event_emailtxtFocusGained

    private void teltxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_teltxtFocusLost
        NumriTelValidator v = new NumriTelValidator();
        if (!v.validate(teltxt.getText().trim())) {
            telgabimlb.setVisible(true);
        }

    }//GEN-LAST:event_teltxtFocusLost

    private void teltxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_teltxtFocusGained
        telgabimlb.setVisible(false);
    }//GEN-LAST:event_teltxtFocusGained

    private void GrupiChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GrupiChooserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GrupiChooserActionPerformed

    private void RadioAktivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioAktivActionPerformed
        eshteAktiv = true;
    }//GEN-LAST:event_RadioAktivActionPerformed

    private void RadioJoAktivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioJoAktivActionPerformed
        eshteAktiv = false;
    }//GEN-LAST:event_RadioJoAktivActionPerformed

    private void teltxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teltxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teltxtActionPerformed

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

    private void teltxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_teltxtKeyTyped

    }//GEN-LAST:event_teltxtKeyTyped

    private void nrPersonaltxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nrPersonaltxtKeyTyped
        char c = evt.getKeyChar();
        if (!((isDigit(c)) || c == KeyEvent.VK_BACKSPACE || c == KeyEvent.VK_SHIFT)) {
            evt.consume();
        }
        String number =  nrPersonaltxt.getText();
        if(number.length()>9 && isDigit(c)){
            JOptionPane.showMessageDialog(this, "Keni tejakaluar numrin e lejuar te numrave(10)", "Gabim" , 0);
            evt.consume();
        }
    }//GEN-LAST:event_nrPersonaltxtKeyTyped

    private void filtrotxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtrotxtKeyReleased
        filtro();
    }//GEN-LAST:event_filtrotxtKeyReleased

    private void exelSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exelSaveActionPerformed
        try {
            JFileChooser fch = new JFileChooser();
            fch.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fch.setPreferredSize(new Dimension(700, 500));
            fch.setDialogTitle("Ruaj raportin");
            int returnVal = fch.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=EducationCenter;user=sa;password=Avdisuka12");
                String report = "C:\\Users\\Arbër Suka\\Desktop\\Projekti LabCourse\\EducationCenter\\StudentiRaporti.jrxml";
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
    }//GEN-LAST:event_exelSaveActionPerformed

    private void pdfSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdfSaveActionPerformed
        try {
            JFileChooser fch = new JFileChooser();
            fch.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fch.setPreferredSize(new Dimension(700, 500));
            fch.setDialogTitle("Ruaj raportin");
            int returnVal = fch.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=EducationCenter;user=sa;password=Avdisuka12");
                String report = "C:\\Users\\Arbër Suka\\Desktop\\Projekti LabCourse\\EducationCenter\\StudentiRaporti.jrxml";
                JasperReport jr = JasperCompileManager.compileReport(report);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
                JasperExportManager.exportReportToPdfFile(jp, fch.getSelectedFile().getAbsolutePath() + ".pdf");
                JOptionPane.showMessageDialog(this, "Raporti u ruajt ne PDF");
            }
        } catch (ClassNotFoundException | SQLException | JRException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_pdfSaveActionPerformed

    private void printobtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printobtnActionPerformed
        MessageFormat header = new MessageFormat("Tabela Dhëmbi");
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");

        try {
            tabela.print(JTable.PrintMode.NORMAL, header, footer);

        } catch (java.awt.print.PrinterException e) {
            System.err.format("Cannot print %s%n", e.getMessage());
        }
    }//GEN-LAST:event_printobtnActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
        try {
            int row = tabela.getSelectedRow();
            if (row > -1) {
                Object[] ob = {"Po", "Jo"};
                int i = javax.swing.JOptionPane.showOptionDialog(this, "A dëshironi ta fshini ?", "Fshirja", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, ob, ob[1]);
                if (i == 0) {
                    Studenti studenti = this.studentiTable.getStudenti(row);
                    studentiR.remove(studenti);
                    tabelaLoad();
                    emptyObject();
                    JOptionPane.showMessageDialog(this, "E dhëna është fshir me sukses !");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nuk keni selektuar asgjë për të fshir !");
            }
        } catch (StudentiException pe) {
            JOptionPane.showMessageDialog(this, pe.getMessage());
        }
    }//GEN-LAST:event_deletebtnActionPerformed

    private void emritxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emritxtKeyReleased
        String t = emritxt.getText();
        if(t.length() > 30){
            evt.consume();
            JOptionPane.showMessageDialog(this, "Keni tejkaluar numrin e lejuar te karaktereve (30)");
        }
    }//GEN-LAST:event_emritxtKeyReleased

    private void rrugatxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rrugatxtKeyReleased
        String t = rrugatxt.getText();
        if(t.length() > 30){
            evt.consume();
            JOptionPane.showMessageDialog(this, "Keni tejkaluar numrin e lejuar te karaktereve (30)");
        }
    }//GEN-LAST:event_rrugatxtKeyReleased

    private void nrPersonaltxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nrPersonaltxtKeyReleased
      
    }//GEN-LAST:event_nrPersonaltxtKeyReleased

    private void rrugatxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rrugatxtKeyTyped
        char c = evt.getKeyChar();
        String number =  kombesiatxt.getText();
        if(number.length()>29){
            JOptionPane.showMessageDialog(this, "Keni tejakaluar numrin e lejuar te shkronjave(30)", "Gabim" , 0);
            evt.consume();
        }
    }//GEN-LAST:event_rrugatxtKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Gjinia> GjiniaChooser;
    private javax.swing.JComboBox<Grupi> GrupiChooser;
    private javax.swing.JComboBox<Qyteti> QytetiChooser;
    private javax.swing.JRadioButton RadioAktiv;
    private javax.swing.JRadioButton RadioJoAktiv;
    private javax.swing.JButton anulobtn;
    private javax.swing.JButton deletebtn;
    private com.toedter.calendar.JDateChooser dobtxt;
    private javax.swing.JTextField emailtxt;
    private javax.swing.JTextField emritxt;
    private javax.swing.JMenuItem exelSave;
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
    private javax.swing.JMenuItem pdfSave;
    private javax.swing.JMenuItem printobtn;
    private javax.swing.JTextField rrugatxt;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTable tabela;
    private javax.swing.JLabel telgabimlb;
    private javax.swing.JTextField teltxt;
    private javax.swing.JTextField zipkoditxt;
    // End of variables declaration//GEN-END:variables
}
