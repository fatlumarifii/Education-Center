/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import BL.Studenti;
import BL.TelStudenti;
import DAL.TelStudentiException;
import DAL.TelStudentiInterface;
import DAL.TelStudentiRepository;
import java.awt.Graphics;
import java.awt.Image;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Arbër Suka
 */
public class StudentiProfilFrame extends javax.swing.JFrame {

    TelStudentiInterface telR = new TelStudentiRepository();
    
    private static Studenti studentiLoguar;
    
    /** Creates new form StudentiFramee */
    public StudentiProfilFrame() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    public void setStudenti(Studenti s){
        studentiLoguar = s;
    }
    
    public static Studenti getStudenti(){
        return studentiLoguar;
    }
    
    public void shtoTeDhenat(Studenti s){
        emritxt.setText(" "+s.getEmri());
        mbiemritxt.setText(" "+s.getMbiemri());
        idtxt.setText(" "+s.getStudentiID());
        DateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        ditelindjatxt.setText(" "+sd.format(s.getDitelindja()));
        qytetitxt.setText(" "+s.getQytetiid().getEmriQytetit());
        gjiniatxt.setText(" "+s.getGjiniaid().getLloji());
        List<TelStudenti> tel = null;
        try {
            tel = telR.findByStudentiId(s.getStudentiID());
        } catch (TelStudentiException ex) {
            Logger.getLogger(StudentiProfilFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        teltxt.setText(" "+tel.get(0));
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem4 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        gjiniatxt = new javax.swing.JTextField();
        idtxt = new javax.swing.JTextField();
        emritxt = new javax.swing.JTextField();
        mbiemritxt = new javax.swing.JTextField();
        ditelindjatxt = new javax.swing.JTextField();
        teltxt = new javax.swing.JTextField();
        qytetitxt = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        ImageIcon icon = new ImageIcon(getClass().getResource("/Regex/Background.jpg"));
        Image image = icon.getImage();
        DesktopPane = new javax.swing.JDesktopPane(){
            public void paintComponent(Graphics g){
                g.drawImage(image, 0, 0, getWidth(), getHeight(),this);
            }
        };
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jMenuItem4.setText("jMenuItem4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Studenti Profil");

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/sTUDENT.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 170, 210));

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 80, 30));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Emri");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 80, 30));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Mbiemri");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 80, 30));

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Gjinia");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 80, 30));

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nr. Tel ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 80, 30));

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Ditelindja");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 80, 30));

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Qyteti");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 80, 30));

        gjiniatxt.setEditable(false);
        gjiniatxt.setBackground(new java.awt.Color(34, 149, 186));
        gjiniatxt.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        gjiniatxt.setForeground(new java.awt.Color(255, 255, 255));
        gjiniatxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel1.add(gjiniatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 560, 120, 30));

        idtxt.setEditable(false);
        idtxt.setBackground(new java.awt.Color(34, 149, 186));
        idtxt.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        idtxt.setForeground(new java.awt.Color(255, 255, 255));
        idtxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel1.add(idtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, 120, 30));

        emritxt.setEditable(false);
        emritxt.setBackground(new java.awt.Color(34, 149, 186));
        emritxt.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        emritxt.setForeground(new java.awt.Color(255, 255, 255));
        emritxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel1.add(emritxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, 120, 30));

        mbiemritxt.setEditable(false);
        mbiemritxt.setBackground(new java.awt.Color(34, 149, 186));
        mbiemritxt.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        mbiemritxt.setForeground(new java.awt.Color(255, 255, 255));
        mbiemritxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel1.add(mbiemritxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, 120, 30));

        ditelindjatxt.setEditable(false);
        ditelindjatxt.setBackground(new java.awt.Color(34, 149, 186));
        ditelindjatxt.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        ditelindjatxt.setForeground(new java.awt.Color(255, 255, 255));
        ditelindjatxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel1.add(ditelindjatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 410, 120, 30));

        teltxt.setEditable(false);
        teltxt.setBackground(new java.awt.Color(34, 149, 186));
        teltxt.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        teltxt.setForeground(new java.awt.Color(255, 255, 255));
        teltxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel1.add(teltxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 460, 120, 30));

        qytetitxt.setEditable(false);
        qytetitxt.setBackground(new java.awt.Color(34, 149, 186));
        qytetitxt.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        qytetitxt.setForeground(new java.awt.Color(255, 255, 255));
        qytetitxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel1.add(qytetitxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 510, 120, 30));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 220, 10));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 220, 10));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, 220, 10));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 220, 10));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 220, 10));
        jPanel1.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 220, 10));
        jPanel1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 220, 10));

        javax.swing.GroupLayout DesktopPaneLayout = new javax.swing.GroupLayout(DesktopPane);
        DesktopPane.setLayout(DesktopPaneLayout);
        DesktopPaneLayout.setHorizontalGroup(
            DesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1180, Short.MAX_VALUE)
        );
        DesktopPaneLayout.setVerticalGroup(
            DesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 910, Short.MAX_VALUE)
        );

        jPanel1.add(DesktopPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 1180, 910));

        jMenu1.setBackground(new java.awt.Color(51, 153, 255));
        jMenu1.setText("Profili");

        jMenuItem6.setText("GjenoTranskripten");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem2.setText("Shiko Pagesat");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Shiko Orarin");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem5.setText("Arkiva");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Menu");

        jMenuItem1.setText("Shkyqu");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        DesktopPane.removeAll();
        PagesaStudnetiFrame p = new PagesaStudnetiFrame();
        DesktopPane.add(p);
        p.show();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        DesktopPane.removeAll();
        TranskriptaFrame t = new TranskriptaFrame();
        DesktopPane.add(t);
        t.show();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        DesktopPane.removeAll();
        LoginFrame l = new LoginFrame();
        l.setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        DesktopPane.removeAll();
        OrariStudentiFrame os = new OrariStudentiFrame();
        DesktopPane.add(os);
        os.show();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        DesktopPane.removeAll();
        ArkivaFrame a = new ArkivaFrame();
        a.Ngarkobtn.setVisible(false);
        a.Zgjedhbtn.setVisible(false);
        a.deletebtn.setVisible(false);
        a.filetxt.setVisible(false);
        a.jLabel1.setVisible(false);
        a.emritxt.setVisible(false);
        DesktopPane.add(a);
        a.show();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentiProfilFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentiProfilFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentiProfilFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentiProfilFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentiProfilFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane DesktopPane;
    private javax.swing.JTextField ditelindjatxt;
    private javax.swing.JTextField emritxt;
    private javax.swing.JTextField gjiniatxt;
    private javax.swing.JTextField idtxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTextField mbiemritxt;
    private javax.swing.JTextField qytetitxt;
    private javax.swing.JTextField teltxt;
    // End of variables declaration//GEN-END:variables

}
