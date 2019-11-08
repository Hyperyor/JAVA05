/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Errores;
import Modelo.Autor;
import Modelo.ConsultasAutor;
import Modelo.ConsultasParticipantes;
import Modelo.Libro;
import Modelo.Participante;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hyperior
 */
public class VerParticipantes extends javax.swing.JDialog {

    /**
     * Creates new form VerParticipantes
     */
    private MainWindow venP;
    private Libro libroActual;
    
    private ConsultasParticipantes consulPartic;
    
    private ArrayList<Participante> listadoParticipantes;
    
    private DefaultTableModel model;
    
    private SeleccionarAutor seleccionAutor;
    
    private int selectedIndex;
    
    private ArrayList<Autor> listaAutoresDisponibles;
    
    public VerParticipantes(java.awt.Frame parent, boolean modal, Libro libroActual) {
        super(parent, modal);
        initComponents();
        
        this.libroActual = libroActual;
        
        venP=(MainWindow)parent;
        
        consulPartic = new ConsultasParticipantes();
        
        try
        {
            listadoParticipantes = consulPartic.getListado(libroActual);
        }
        catch(Errores er)
        {
            JOptionPane.showMessageDialog(null, 
                                er.showMessage(), "Error", 
                                JOptionPane.ERROR_MESSAGE);
        }
        
        
        model = (DefaultTableModel) jTableDatosParticipantes.getModel();
        
        if(listadoParticipantes.isEmpty())
        {
            JOptionPane.showMessageDialog(null, 
                                "No hay participantes", "Listado de participantes", 
                                JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            cargarDatos();
        }
        
        
        setResizable(false);
        Dimension jDialogTamaño=this.getPreferredSize();
        Dimension jFrameTamaño=venP.getSize();
        Point corrdenadasJDialog=this.getLocation();
        this.setLocation((jFrameTamaño.width-jDialogTamaño.width)/2+corrdenadasJDialog.x, (jFrameTamaño.height-jDialogTamaño.height)/2+corrdenadasJDialog.y);
        this.setVisible(true);
    }
    
//    public ArrayList<Participante> getListadoParticipantes()
//    {
//        return listadoParticipantes;
//    }
    
    private void cargarDatos()
    {
        Object[] rows = new Object[4];
        
        for (int i = 0; i < listadoParticipantes.size(); i++) {
            
            rows = getDatos(listadoParticipantes.get(i));

            model.addRow(rows);
        }
        
        jTableDatosParticipantes.setModel(model);
        
        //mostrar mensaje de error en caso de no tener alumnos
        if(listadoParticipantes.size() == 0)
        {
            JOptionPane.showMessageDialog(null, 
                                "Este profesor no tiene alumnos", "Error", 
                                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private Object[] getDatos(Participante al)
    {
        Object[] rows = new Object[6];
        
        rows[0] = al.getIsbn();
        rows[1] = al.getCodigoAutor();
        rows[2] = al.getNumero();
        rows[3] = al.getBeneficio();

        return rows;
    }
    
    private void resetTable()
    {
        model.setRowCount(0);
    }
    
    private ArrayList<Autor> getListaAutoresDisponibles()
    {
        try
        {
            ConsultasAutor consAut = new ConsultasAutor();
        
            ArrayList<Autor> listadoAutoresCompleto = consAut.getListadoAutores();

            ArrayList<Autor> listaAutoresDisponibles = new ArrayList<Autor>();

            boolean contiene = false;

            for (int i = 0; i < listadoAutoresCompleto.size(); i++) 
            {

                for (int j = 0; j < listadoParticipantes.size(); j++) 
                {
                    if(listadoParticipantes.get(j).getCodigoAutor() == listadoAutoresCompleto.get(i).getCodigo())
                    {
                        contiene = true; 
                        break;
                    }

                }

                if(!contiene)
                {
                    listaAutoresDisponibles.add(listadoAutoresCompleto.get(i));
                }

                contiene = false;
            }

            return listaAutoresDisponibles;
        }
        catch(Errores er)
        {
            JOptionPane.showMessageDialog(null, 
                                er.showMessage(), "Error", 
                                JOptionPane.ERROR_MESSAGE);
        }
        
        return new ArrayList<Autor>();
    }
    
    public void insertarElemento(int indice)
    {
        //cargamos el participante con datos
        Participante p = new Participante();
        
        p.setIsbn(libroActual.getIsbn());
        
        p.setCodigoAutor(listaAutoresDisponibles.get(indice).getCodigo());
        
        p.setNumero(listadoParticipantes.size() + 1);
        
        float numero = listaAutoresDisponibles.get(indice).getPorcentajeBeneficio() * libroActual.getPrecio() / 100;
        
        float num = (float) (Math.round(numero * Math.pow(10, 2)) / Math.pow(10, 2));
        
        p.setBeneficio(num);

        //insertamos la nueva fila en la BD
        try
        {
            int n = consulPartic.insertarParticipante(p);
        
        
            //aniadimos el participante a la lista
            listadoParticipantes.add(p);
            //reseteamos el jtable
            resetTable();
            cargarDatos();
        
        }
        catch(Errores er)
        {
            JOptionPane.showMessageDialog(null, 
                                er.showMessage(), "Error", 
                                JOptionPane.ERROR_MESSAGE);
        }
        
       
        
        //System.out.println("\nResultado de la insercion: " + n);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelPane = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        dataPane = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDatosParticipantes = new javax.swing.JTable();
        buttonsPane = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        insertarButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        volverButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Participantes");
        setPreferredSize(new java.awt.Dimension(500, 478));
        getContentPane().setLayout(new java.awt.BorderLayout());

        titleLabel.setText("Listado de participantes");
        labelPane.add(titleLabel);

        getContentPane().add(labelPane, java.awt.BorderLayout.NORTH);

        dataPane.setLayout(new java.awt.BorderLayout());

        jTableDatosParticipantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISBN", "Codigo Autor", "Numero", "Beneficio (€)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableDatosParticipantes);

        dataPane.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(dataPane, java.awt.BorderLayout.CENTER);

        buttonsPane.setPreferredSize(new java.awt.Dimension(400, 50));
        buttonsPane.setLayout(new java.awt.GridLayout(2, 4));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 214, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        buttonsPane.add(jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 214, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        buttonsPane.add(jPanel2);

        insertarButton.setText("Insertar nuevo elemento");
        insertarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarButtonActionPerformed(evt);
            }
        });
        buttonsPane.add(insertarButton);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 214, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        buttonsPane.add(jPanel3);

        volverButton.setText("Volver");
        volverButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverButtonActionPerformed(evt);
            }
        });
        buttonsPane.add(volverButton);

        getContentPane().add(buttonsPane, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void volverButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_volverButtonActionPerformed

    private void insertarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarButtonActionPerformed
        listaAutoresDisponibles = getListaAutoresDisponibles();
        
        if(listaAutoresDisponibles.isEmpty())
        {
            JOptionPane.showMessageDialog(null, 
                                "No hay autores disponibles", "Error", 
                                JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            seleccionAutor = new SeleccionarAutor(venP, true, listaAutoresDisponibles, this);
        }
        
    }//GEN-LAST:event_insertarButtonActionPerformed

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
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(VerParticipantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(VerParticipantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(VerParticipantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(VerParticipantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                VerParticipantes dialog = new VerParticipantes(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPane;
    private javax.swing.JPanel dataPane;
    private javax.swing.JButton insertarButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDatosParticipantes;
    private javax.swing.JPanel labelPane;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JButton volverButton;
    // End of variables declaration//GEN-END:variables
}
