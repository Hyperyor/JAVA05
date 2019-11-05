/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.GestionBD;
import Modelo.ConsultasLibros;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author alumno
 */
public class MainWindow extends javax.swing.JFrame {

    private PanelConexion panelConexion;
    private String actualUser;
    private PanelVisualizar pVisualizar;
    private Acerca ventanaAcerca;
   
    public MainWindow() {
        initComponents();
        
        conexionConLasBasesDeDatos();
        
        panelConexion = new PanelConexion(this);
        
        reset();
    }
    
    private void reset()
    {
        connectionMenu.setForeground(Color.red);
        acercaDeButton.setEnabled(false);
        disconnectionButton.setEnabled(false);
        this.setContentPane(panelConexion);
    }
    
    private void conexionConLasBasesDeDatos()
    {
        GestionBD.connectToDataBase();
    }
    
    public void conexionRealizada(String usuario)
    {
        actualUser = usuario;
        connectionButton.setEnabled(false);
        disconnectionButton.setEnabled(true);
        acercaDeButton.setEnabled(true);
        connectionMenu.setForeground(Color.GREEN);
        cambiarAVisualizar();
        
    }
    
    public void cambiarAVisualizar()
    {
        pVisualizar = new PanelVisualizar(actualUser, this);
        cambiarContenedor(pVisualizar);
    }
    
    private void cambiarContenedor(JPanel aux){
        this.setContentPane(aux);
        pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        connectionMenu = new javax.swing.JMenu();
        connectionButton = new javax.swing.JMenuItem();
        disconnectionButton = new javax.swing.JMenuItem();
        acercaDeButton = new javax.swing.JMenu();
        visualizarDatosButton = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JAVA05");
        setPreferredSize(new java.awt.Dimension(600, 600));

        connectionMenu.setText("Conexion");

        connectionButton.setText("Connect");
        connectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectionButtonActionPerformed(evt);
            }
        });
        connectionMenu.add(connectionButton);

        disconnectionButton.setText("Disconnect");
        disconnectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectionButtonActionPerformed(evt);
            }
        });
        connectionMenu.add(disconnectionButton);

        jMenuBar1.add(connectionMenu);

        acercaDeButton.setText("Main menu");

        visualizarDatosButton.setText("Visualizar Biblioteca");
        visualizarDatosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visualizarDatosButtonActionPerformed(evt);
            }
        });
        acercaDeButton.add(visualizarDatosButton);

        jMenuItem1.setText("Acerca de");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        acercaDeButton.add(jMenuItem1);

        jMenuBar1.add(acercaDeButton);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void disconnectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectionButtonActionPerformed
        if(GestionBD.getConnectionState())
        {
            int yes=JOptionPane.showConfirmDialog(null, "¿Desea desconectarse?");
            if(yes==0)
            {
                //GestionBD.closeConnectionToDataBase();
                //resetea la ventana
                actualUser = null;
                reset();
                //resetea el panel visualizar por si se encuentra en el panel insertar alumno
                //JPanelVisualizar.reset();
                
            }
        }
    }//GEN-LAST:event_disconnectionButtonActionPerformed

    private void connectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectionButtonActionPerformed
        cambiarContenedor(panelConexion);
    }//GEN-LAST:event_connectionButtonActionPerformed

    private void visualizarDatosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visualizarDatosButtonActionPerformed
        cambiarAVisualizar();
    }//GEN-LAST:event_visualizarDatosButtonActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        ventanaAcerca=new Acerca(this, true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu acercaDeButton;
    private javax.swing.JMenuItem connectionButton;
    private javax.swing.JMenu connectionMenu;
    private javax.swing.JMenuItem disconnectionButton;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem visualizarDatosButton;
    // End of variables declaration//GEN-END:variables
}
