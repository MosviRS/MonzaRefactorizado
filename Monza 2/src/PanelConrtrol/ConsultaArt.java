/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PanelConrtrol;
import LogIn.*;
import Entidades.Usuario;
import clases.Hash;
import clases.editTabletrue;
//import com.mysql.cj.Session;
//import com.mysql.cj.protocol.Message;
//import com.sun.jdi.connect.Transport;
import java.awt.Cursor;
//import java.lang.System.Logger;
//import java.lang.System.Logger.Level;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Properties;
import java.util.Vector;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import metodos.ManipulaDBC;
import metodos.PasswordGenerator;
import metodos.SqlUsuarios;
import metodos.Sqlproductos_notas;

/**
 *
 * @author Rasteck7
 */
public class ConsultaArt extends javax.swing.JFrame {
        Usuario mod;
        Sqlproductos_notas sqlproc= new Sqlproductos_notas();
        Connection con;
        String value;
         ArrayList<Object> arr= new ArrayList();
           DefaultTableModel atm;
    /**
     * Creates new form olv2
     */
    public ConsultaArt(String valor) {
        initComponents();
        this.value=valor;
        this.setLocationRelativeTo(null);
        this.setTitle("Recuperacion");
        ImageIcon icon = new ImageIcon("src/IMGM/LogoMonza.png");
        this.setIconImage(icon.getImage());
        
       
        
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Minimize = new javax.swing.JButton();
        Close = new javax.swing.JButton();
        jLnotapro = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        TablaPri = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(243, 240, 235));
        jPanel1.setPreferredSize(new java.awt.Dimension(577, 383));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(45, 150, 129));
        jLabel1.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 159, 28));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Informacion de Nota");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 330, 50));

        jLabel2.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        jLabel2.setText("Nombre del Cliente: xxxxx");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 470, 20));

        Minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/Minimize.png"))); // NOI18N
        Minimize.setBorder(null);
        Minimize.setBorderPainted(false);
        Minimize.setContentAreaFilled(false);
        Minimize.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Minimize.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/Minimize2.png"))); // NOI18N
        Minimize.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/Minimize2.png"))); // NOI18N
        Minimize.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/Minimize2.png"))); // NOI18N
        Minimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MinimizeActionPerformed(evt);
            }
        });
        jPanel1.add(Minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, -1, 40));

        Close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/close.png"))); // NOI18N
        Close.setBorder(null);
        Close.setBorderPainted(false);
        Close.setContentAreaFilled(false);
        Close.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Close.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/close2.png"))); // NOI18N
        Close.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/close2.png"))); // NOI18N
        Close.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/close2.png"))); // NOI18N
        Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseActionPerformed(evt);
            }
        });
        jPanel1.add(Close, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, -1, 40));

        jLnotapro.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        jLnotapro.setText("No. Nota: xxxxx");
        jPanel1.add(jLnotapro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 470, 20));

        TablaPri.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Cantidad", "Articulos", "Modelo", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaPri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaPriMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(TablaPri);
        if (TablaPri.getColumnModel().getColumnCount() > 0) {
            TablaPri.getColumnModel().getColumn(0).setMinWidth(80);
            TablaPri.getColumnModel().getColumn(0).setPreferredWidth(80);
            TablaPri.getColumnModel().getColumn(0).setMaxWidth(80);
            TablaPri.getColumnModel().getColumn(1).setMinWidth(300);
            TablaPri.getColumnModel().getColumn(1).setPreferredWidth(300);
            TablaPri.getColumnModel().getColumn(1).setMaxWidth(300);
            TablaPri.getColumnModel().getColumn(3).setPreferredWidth(8);
        }

        jPanel1.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 770, 350));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MinimizeActionPerformed
        this.setState(LogIn.ICONIFIED);
    }//GEN-LAST:event_MinimizeActionPerformed

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
//        int dialog = JOptionPane.YES_NO_OPTION;
//        int result = JOptionPane.showConfirmDialog(null, "Desea regresar al inicio?","Exit",dialog);
//        if (result==0){
//            java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new LogIn().setVisible(true);
//            }
//        });
//        }
        this.dispose();
    }//GEN-LAST:event_CloseActionPerformed

    private void TablaPriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaPriMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TablaPriMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        
         ManipulaDBC.desconectaDB(con);
         
    }//GEN-LAST:event_formWindowClosed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        
        con = ManipulaDBC.conectaDB();
        
         arr=sqlproc.vizualizar_tabla(TablaPri, con,value);
         System.out.println(arr.get(0));
         if(arr!=null){
              llenar(TablaPri);
         }else{
            
         }
      
    }//GEN-LAST:event_formWindowOpened
   public void llenar(JTable TablaPri){
        atm=new editTabletrue();
           String []titulo=new String[] {"Cantidad","Articulo","Modelo","Precio"};
           atm.setColumnIdentifiers(titulo);
       jLnotapro.setText("No. Nota: "+arr.get(0).toString());
       jLabel2.setText("Nombre del Cliente: "+arr.get(1).toString()+" "+arr.get(2).toString()+" "+arr.get(3).toString());
         for (int i =4; i < arr.size(); i=i+8) {
              atm.addRow(new Object[]{
               arr.get(i),arr.get(i+1),arr.get(i+2),arr.get(i+3)
           });
             
           }
             
         TablaPri.setRowHeight(16);
         TablaPri.setModel(atm);
   }
//        public  DefaultTableModel agregarProductos(String mod,String nom,Double preci, int exis,String cate,String provM,String im){
//      atm.addRow(new Object[]{
//          
//      });
//   }
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Windows".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(olv2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(olv2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(olv2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(olv2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new olv2().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Close;
    private javax.swing.JButton Minimize;
    private javax.swing.JTable TablaPri;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLnotapro;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane13;
    // End of variables declaration//GEN-END:variables
}
