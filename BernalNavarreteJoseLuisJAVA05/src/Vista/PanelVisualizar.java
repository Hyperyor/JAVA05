/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.ConsultasLibros;
import Modelo.Libro;
import com.aeat.valida.Validador;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author alumno
 */
public class PanelVisualizar extends javax.swing.JPanel {

    /**
     * Creates new form PanelVisualizar
     */
    
    private ConsultasLibros consulLibros;
    private MainWindow ventanaPrincipal;
    private Libro libroActual;
    
    private VerParticipantes ventanaParticipantes;
    
    private JFileChooser fileChooser;
    
    private File imagenTemporal;
    
    private boolean imageChanged;
    private boolean fechaActualizada;
    
    public PanelVisualizar(String usuario, MainWindow princip) {
        initComponents();
        ventanaPrincipal = princip;
        consulLibros = new ConsultasLibros(usuario);
        
        libroActual = consulLibros.getFirstBook();
        
        //si hay al menos un libro
        if(libroActual.getIsbn() != null)
        {
            panelMensajeVacio.setVisible(false);
            rellenarDatos(libroActual);
            controlDeBotones();
        }
        else
        {
            panelMensajeVacio.setVisible(true);
            panelDatos.setVisible(false);
            
            jButtonAnterior.setEnabled(false);
            jButtonSiguiente.setEnabled(false);
            
            this.revalidate();
            this.repaint();
            
            
        }
        
        //createFileChooser();
        
        
    }
    
    public ConsultasLibros getConsultaLibros()
    {
        return consulLibros;
    }
    
    private void controlDeBotones()
    {
        if(consulLibros.isFirstBook())
        {
            jButtonAnterior.setEnabled(false);
            
            if(consulLibros.isLastBook())
            {
                jButtonSiguiente.setEnabled(false);
            }
        }
        else
        {
            if(consulLibros.isLastBook())
            {
                jButtonSiguiente.setEnabled(false);
            }
            else
            {
                jButtonAnterior.setEnabled(true);
                jButtonSiguiente.setEnabled(true);
            }
        }
    }
    
    private void rellenarDatos(Libro l)
    {
        jTextFieldISBN.setText(l.getIsbn());
        jTextFieldNifAutor.setText(l.getNifPrincAutor());
        jTextFieldPropietario.setText(l.getPropietario());
        jTextFieldTitulo.setText(l.getTitulo());
        jTextFieldPrecio.setText(""+l.getPrecio());
        jDatePickerFechaPubli.getFormattedTextField().setText(""+l.getFechaPublicacion().get(Calendar.DAY_OF_MONTH)+
                                                                "/"  +(l.getFechaPublicacion().get(Calendar.MONTH)+1)+
                                                                "/"  +l.getFechaPublicacion().get(Calendar.YEAR));
        
        insertarImagen("./"+l.getPortada());
        

    }
    
    private void insertarImagen(String portada)
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(portada));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
        Image dimg = img.getScaledInstance(125, 150,
        Image.SCALE_SMOOTH);
        //poner la foto
        portadaImagen.setIcon(new ImageIcon(dimg));
    }
    
    private void createFileChooser()
    {
        //crea un filechooser
        fileChooser = new JFileChooser();
        //establece un filtro para los archivos .dat
        String[] fil = {"jpg", "png", "jpeg"};
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagenes", fil);
        fileChooser.setFileFilter(filtro);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(filtro);
        
        //lo ocultamos
        //lo haremos visible cuando lo necesitemos
        //fileChooser.setVisible(false);
    }
    
    private void copiarImagenTemporal()
    {
        String ext = FilenameUtils.getExtension(imagenTemporal.getPath());
        //System.out.println("\nExtension: " + ext);
        String dest = "./imagenes/temp."+ ext;
        
        Path destino = Paths.get(dest);
        
        String origen = imagenTemporal.getPath();
        
        Path orig = Paths.get(origen);
        
        //libroActual.setPortada("imagenes/"+libroActual.getIsbn()+ "."+ ext);
        
        try
        {
            Files.copy(orig, destino, REPLACE_EXISTING);   
            insertarImagen("./imagenes/temp."+ ext);
            imageChanged = true;
            
        }
        catch(IOException ex)
        {
            
        }
        
        
    }
    
    private void confirmarCambioImagen()
    {
        String ext = FilenameUtils.getExtension(imagenTemporal.getPath());
        //System.out.println("\nExtension: " + ext);
        String dest = "./imagenes/" + libroActual.getIsbn() +"."+ ext;
        
        Path destino = Paths.get(dest);
        
        String origen = imagenTemporal.getPath();
        
        Path orig = Paths.get(origen);
        
        libroActual.setPortada("imagenes/"+libroActual.getIsbn()+ "."+ ext);
        
        try
        {
            Files.copy(orig, destino, REPLACE_EXISTING);   
            insertarImagen("./imagenes/" + libroActual.getIsbn() +"."+ ext);
            
        }
        catch(IOException ex)
        {
            
        }
    }
    
    private GregorianCalendar obtenerNuevaFecha() 
    {
        return new GregorianCalendar
        (  jDatePickerFechaPubli.getModel().getYear(), 
          (jDatePickerFechaPubli.getModel().getMonth()),//no necesito quitarle 1 
           jDatePickerFechaPubli.getModel().getDay() 
        );
      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMensajeVacio = new javax.swing.JPanel();
        labelMensajeNoHayDatos = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        panelOtrasOpciones = new javax.swing.JPanel();
        verParticipantesButton = new javax.swing.JButton();
        changeConfirmation = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        panelRelleno = new javax.swing.JPanel();
        dataPanel = new javax.swing.JPanel();
        jLabelISBN = new javax.swing.JLabel();
        jTextFieldISBN = new javax.swing.JTextField();
        jLabelTitulo = new javax.swing.JLabel();
        jTextFieldTitulo = new javax.swing.JTextField();
        jLabelPrecio = new javax.swing.JLabel();
        jTextFieldPrecio = new javax.swing.JTextField();
        jLabelAutor = new javax.swing.JLabel();
        jTextFieldNifAutor = new javax.swing.JTextField();
        jLabelPropietario = new javax.swing.JLabel();
        jTextFieldPropietario = new javax.swing.JTextField();
        jLabelFechaPubli = new javax.swing.JLabel();
        jDatePickerFechaPubli = new org.jdatepicker.JDatePicker();
        tituloLabel = new javax.swing.JLabel();
        panelBotonesMovimiento = new javax.swing.JPanel();
        jButtonAnterior = new javax.swing.JButton();
        jButtonSiguiente = new javax.swing.JButton();
        panelImagen = new javax.swing.JPanel();
        portadaImagen = new javax.swing.JLabel();
        changeImagePanel = new javax.swing.JPanel();
        changeImageButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        panelMensajeVacio.setLayout(new java.awt.BorderLayout());

        labelMensajeNoHayDatos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensajeNoHayDatos.setText("No existen libros para este usuario");
        panelMensajeVacio.add(labelMensajeNoHayDatos, java.awt.BorderLayout.CENTER);

        add(panelMensajeVacio, java.awt.BorderLayout.CENTER);

        panelDatos.setLayout(new java.awt.BorderLayout());

        panelOtrasOpciones.setLayout(new java.awt.GridLayout(2, 2));

        verParticipantesButton.setText("Ver Participantes");
        verParticipantesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verParticipantesButtonActionPerformed(evt);
            }
        });
        panelOtrasOpciones.add(verParticipantesButton);

        changeConfirmation.setText("Confirmar cambios");
        changeConfirmation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeConfirmationActionPerformed(evt);
            }
        });
        panelOtrasOpciones.add(changeConfirmation);

        jButton1.setText("Validar NIF");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panelOtrasOpciones.add(jButton1);

        panelDatos.add(panelOtrasOpciones, java.awt.BorderLayout.SOUTH);

        javax.swing.GroupLayout panelRellenoLayout = new javax.swing.GroupLayout(panelRelleno);
        panelRelleno.setLayout(panelRellenoLayout);
        panelRellenoLayout.setHorizontalGroup(
            panelRellenoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 344, Short.MAX_VALUE)
        );
        panelRellenoLayout.setVerticalGroup(
            panelRellenoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        panelDatos.add(panelRelleno, java.awt.BorderLayout.PAGE_START);

        dataPanel.setLayout(new java.awt.GridLayout(6, 2));

        jLabelISBN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelISBN.setText("ISBN");
        dataPanel.add(jLabelISBN);

        jTextFieldISBN.setEditable(false);
        dataPanel.add(jTextFieldISBN);

        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Titulo");
        dataPanel.add(jLabelTitulo);

        jTextFieldTitulo.setEditable(false);
        dataPanel.add(jTextFieldTitulo);

        jLabelPrecio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPrecio.setText("Precio");
        dataPanel.add(jLabelPrecio);

        jTextFieldPrecio.setEditable(false);
        dataPanel.add(jTextFieldPrecio);

        jLabelAutor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAutor.setText("Nif autor P.");
        dataPanel.add(jLabelAutor);

        jTextFieldNifAutor.setEditable(false);
        dataPanel.add(jTextFieldNifAutor);

        jLabelPropietario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPropietario.setText("Propietario");
        dataPanel.add(jLabelPropietario);

        jTextFieldPropietario.setEditable(false);
        dataPanel.add(jTextFieldPropietario);

        jLabelFechaPubli.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFechaPubli.setText("Fecha publicación");
        dataPanel.add(jLabelFechaPubli);

        jDatePickerFechaPubli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDatePickerFechaPubliActionPerformed(evt);
            }
        });
        dataPanel.add(jDatePickerFechaPubli);

        panelDatos.add(dataPanel, java.awt.BorderLayout.CENTER);

        add(panelDatos, java.awt.BorderLayout.CENTER);

        tituloLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloLabel.setText("Listado de libros");
        add(tituloLabel, java.awt.BorderLayout.PAGE_START);

        panelBotonesMovimiento.setLayout(new java.awt.GridLayout(1, 2));

        jButtonAnterior.setText("Anterior");
        jButtonAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnteriorActionPerformed(evt);
            }
        });
        panelBotonesMovimiento.add(jButtonAnterior);

        jButtonSiguiente.setText("Siguiente");
        jButtonSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSiguienteActionPerformed(evt);
            }
        });
        panelBotonesMovimiento.add(jButtonSiguiente);

        add(panelBotonesMovimiento, java.awt.BorderLayout.SOUTH);

        panelImagen.setPreferredSize(new java.awt.Dimension(150, 260));
        panelImagen.setLayout(new java.awt.GridLayout(2, 1));

        portadaImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        portadaImagen.setMaximumSize(new java.awt.Dimension(50, 50));
        portadaImagen.setMinimumSize(new java.awt.Dimension(50, 50));
        portadaImagen.setPreferredSize(new java.awt.Dimension(50, 50));
        panelImagen.add(portadaImagen);

        changeImageButton.setText("Cambiar imagen");
        changeImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeImageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout changeImagePanelLayout = new javax.swing.GroupLayout(changeImagePanel);
        changeImagePanel.setLayout(changeImagePanelLayout);
        changeImagePanelLayout.setHorizontalGroup(
            changeImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
            .addGroup(changeImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(changeImagePanelLayout.createSequentialGroup()
                    .addComponent(changeImageButton)
                    .addGap(0, 2, Short.MAX_VALUE)))
        );
        changeImagePanelLayout.setVerticalGroup(
            changeImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
            .addGroup(changeImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, changeImagePanelLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(changeImageButton)
                    .addContainerGap(92, Short.MAX_VALUE)))
        );

        panelImagen.add(changeImagePanel);

        add(panelImagen, java.awt.BorderLayout.LINE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnteriorActionPerformed
        libroActual = consulLibros.previousBook();
        rellenarDatos(libroActual);
        controlDeBotones();
    }//GEN-LAST:event_jButtonAnteriorActionPerformed

    private void jButtonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSiguienteActionPerformed
        libroActual = consulLibros.nextBook();
        rellenarDatos(libroActual);
        controlDeBotones();
    }//GEN-LAST:event_jButtonSiguienteActionPerformed

    private void verParticipantesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verParticipantesButtonActionPerformed
        ventanaParticipantes = new VerParticipantes(ventanaPrincipal, true, libroActual);
    }//GEN-LAST:event_verParticipantesButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Validador val = new Validador();
        
        int result = val.checkNif(libroActual.getNifPrincAutor());
        
        if(result > 0)
        {
            JOptionPane.showMessageDialog(null, 
                                "DNI correcto", "Validacion DNI", 
                                JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null, 
                                "DNI incorrecto", "Validacion DNI", 
                                JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void changeImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeImageButtonActionPerformed
        
        createFileChooser();
        
        //selecciona el dialos para guardar
        int seleccion = fileChooser.showSaveDialog(this);
        
        //si acepta
        if(seleccion == JFileChooser.APPROVE_OPTION)
        {
            imagenTemporal = fileChooser.getSelectedFile();
            
            if(imagenTemporal != null)
            {
                copiarImagenTemporal();
            }

        }
    }//GEN-LAST:event_changeImageButtonActionPerformed

    private void changeConfirmationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeConfirmationActionPerformed
        
        if(imageChanged || fechaActualizada)
        {
            if(imageChanged)
            {
                confirmarCambioImagen();
            }

            int n = consulLibros.updateBook(libroActual);
            
            if(n == 1)
            {
                JOptionPane.showMessageDialog(null, 
                                "Actualizacion correcta", "Actualizacion de datos", 
                                JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, 
                                "Actualizacion incorrecta", "Actualizacion de datos", 
                                JOptionPane.ERROR_MESSAGE);
            }
            
            imageChanged = false;
            fechaActualizada = false;
        }
        
        
        //hacer los updates en la base de datos
    }//GEN-LAST:event_changeConfirmationActionPerformed

    private void jDatePickerFechaPubliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDatePickerFechaPubliActionPerformed
        GregorianCalendar fechaActualizada=obtenerNuevaFecha();
        
        
        //comprobar correccion de la fecha (que no sea posterior a la fecha actual)
        
        libroActual.setFechaPublicacion(fechaActualizada);

        this.fechaActualizada=true;
    }//GEN-LAST:event_jDatePickerFechaPubliActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton changeConfirmation;
    private javax.swing.JButton changeImageButton;
    private javax.swing.JPanel changeImagePanel;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAnterior;
    private javax.swing.JButton jButtonSiguiente;
    private org.jdatepicker.JDatePicker jDatePickerFechaPubli;
    private javax.swing.JLabel jLabelAutor;
    private javax.swing.JLabel jLabelFechaPubli;
    private javax.swing.JLabel jLabelISBN;
    private javax.swing.JLabel jLabelPrecio;
    private javax.swing.JLabel jLabelPropietario;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JTextField jTextFieldISBN;
    private javax.swing.JTextField jTextFieldNifAutor;
    private javax.swing.JTextField jTextFieldPrecio;
    private javax.swing.JTextField jTextFieldPropietario;
    private javax.swing.JTextField jTextFieldTitulo;
    private javax.swing.JLabel labelMensajeNoHayDatos;
    private javax.swing.JPanel panelBotonesMovimiento;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelImagen;
    private javax.swing.JPanel panelMensajeVacio;
    private javax.swing.JPanel panelOtrasOpciones;
    private javax.swing.JPanel panelRelleno;
    private javax.swing.JLabel portadaImagen;
    private javax.swing.JLabel tituloLabel;
    private javax.swing.JButton verParticipantesButton;
    // End of variables declaration//GEN-END:variables
}
