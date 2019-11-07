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
import java.util.Date;
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
public class PanelInsertar extends javax.swing.JPanel {

    /**
     * Creates new form PanelVisualizar
     */
    
    private MainWindow ventanaPrincipal;
    
    private JFileChooser fileChooser;
    
    private File imagenTemporal;
    
    private PanelVisualizar pVisualizar;
    
    private boolean fechaSeleccionada;
    
    private Libro nuevoLibro;
    
    private String user;
    
    public PanelInsertar(String usuario, MainWindow princip, PanelVisualizar pVisual) {
        initComponents();
        ventanaPrincipal = princip;
        pVisualizar = pVisual;
        nuevoLibro = new Libro();
        user = usuario;
        
        jTextFieldPropietario.setText(user);
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
        
    }
    
    private void copiarImagenTemporal()
    {
        String ext = FilenameUtils.getExtension(imagenTemporal.getPath());
        
        String dest = "./imagenes/temp."+ ext;
        
        Path destino = Paths.get(dest);
        
        String origen = imagenTemporal.getPath();
        
        Path orig = Paths.get(origen);
        
        try
        {
            Files.copy(orig, destino, REPLACE_EXISTING);   
            insertarImagen("./imagenes/temp."+ ext);
            
        }
        catch(IOException ex)
        {
            
        }
        
        
    }
    
    private void confirmarCambioImagen()
    {
        String ext = FilenameUtils.getExtension(imagenTemporal.getPath());
        //System.out.println("\nExtension: " + ext);
        String dest = "./imagenes/" + nuevoLibro.getIsbn() +"."+ ext;
        
        Path destino = Paths.get(dest);
        
        String origen = imagenTemporal.getPath();
        
        Path orig = Paths.get(origen);
        
        nuevoLibro.setPortada("imagenes/"+nuevoLibro.getIsbn()+ "."+ ext);
        
        try
        {
            Files.copy(orig, destino, REPLACE_EXISTING);   
            insertarImagen("./imagenes/" + nuevoLibro.getIsbn() +"."+ ext);
            
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
    
    private boolean datosCorrectos()
    {
        if(imagenTemporal != null)
        {
            
            if(fechaSeleccionada == true && fechaCorrecta())
            {
               
                if(correccionIsbn())
                {
                    if(correccionPrecio())
                    {
                        if(correccionTitulo())
                        {
                            if(correccionDni())
                            {
                                return true;

                            }
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
    private boolean correccionIsbn()
    {
        if(jTextFieldISBN.getText() != "" && jTextFieldISBN.getText().length() <= 13)
        {
            return true;
        }
        return false;
    }
    
    private boolean correccionDni()
    {
        if(jTextFieldNifAutor.getText() != "" && jTextFieldNifAutor.getText().length() <= 9)
        {
            return true;
        }
        return false;
    }
    
    private boolean correccionTitulo()
    {
        if(jTextFieldTitulo.getText() != "" && jTextFieldTitulo.getText().length() <= 100)
        {
            return true;
        }
        return false;
    }
    
    private boolean correccionPrecio()
    {
        if(jTextFieldPrecio.getText() != "" && convertirFloat(jTextFieldPrecio.getText()) > 0)
        {
            return true;
        }
        return false;
    }
    
    //falta comprobar que la fecha no sea posterior a la actual
    private boolean fechaCorrecta()
    {
        if(correctDate(jDatePickerFechaPubli.getModel().getDay(), 
          jDatePickerFechaPubli.getModel().getMonth(), 
           jDatePickerFechaPubli.getModel().getYear()) && !fechaPosterior() )
        {
            return true;
        }
        return false;
    }
    
    private boolean fechaPosterior()
    {
        GregorianCalendar fechaInsertada = obtenerNuevaFecha();
        
        Date d = fechaInsertada.getTime();
        
        GregorianCalendar fechaActual = new GregorianCalendar();
        
        Date d2 = fechaActual.getTime();
        
        if(d.before(d2) || d.equals(d2))
        {
            //System.out.println("\nLa fecha es anterior a la actual");
            return false;
        }
        
        //System.out.println("\nLa fecha es posterior a la actual");
        return true;
    }
    
    private boolean correctDate(int day, int month, int year)
    {
        if(year > 0)
        {
            if(month >= 1 && month <= 12)
            {
                if(day >= 1 && day <= checkMonthsTotalDays(month, year))
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    private int checkMonthsTotalDays(int month, int year)
    {
        switch(month)
        {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12: return 31;
        case 4:
        case 6:
        case 9:
        case 11: return 30;
        case 2: 
               if(comprobarBisiesto(year))
               {
                   return 29;
               }
               else
               {
                   return 28;
               }
     
        }   
        
        return 1;
    }
    
    private boolean comprobarBisiesto(int year)
    {
        if((year % 4 == 0) && !((year % 100 == 0) && (year % 400 != 0)))
        {
            return true;
        }
        return false;
    }
    
    private float convertirFloat(String texto)
    {
        float f = 0;
         
        try
        {
          f =  Float.parseFloat(texto);
        }
        catch(NumberFormatException e)
        {
          //return Float.NaN; // No es un número (valor float)
        }
         
        return f;
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
        panelBotones = new javax.swing.JPanel();
        jButtonAceptar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        panelImagen = new javax.swing.JPanel();
        portadaImagen = new javax.swing.JLabel();
        changeImagePanel = new javax.swing.JPanel();
        selectImageButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        panelMensajeVacio.setLayout(new java.awt.BorderLayout());

        labelMensajeNoHayDatos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensajeNoHayDatos.setText("No existen libros para este usuario");
        panelMensajeVacio.add(labelMensajeNoHayDatos, java.awt.BorderLayout.CENTER);

        add(panelMensajeVacio, java.awt.BorderLayout.CENTER);

        panelDatos.setLayout(new java.awt.BorderLayout());

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
        dataPanel.add(jTextFieldISBN);

        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Titulo");
        dataPanel.add(jLabelTitulo);
        dataPanel.add(jTextFieldTitulo);

        jLabelPrecio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPrecio.setText("Precio");
        dataPanel.add(jLabelPrecio);
        dataPanel.add(jTextFieldPrecio);

        jLabelAutor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAutor.setText("Nif autor P.");
        dataPanel.add(jLabelAutor);
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
        tituloLabel.setText("Inserción de libro");
        add(tituloLabel, java.awt.BorderLayout.PAGE_START);

        panelBotones.setLayout(new java.awt.GridLayout(1, 2));

        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAceptarActionPerformed(evt);
            }
        });
        panelBotones.add(jButtonAceptar);

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        panelBotones.add(jButtonCancelar);

        add(panelBotones, java.awt.BorderLayout.SOUTH);

        panelImagen.setPreferredSize(new java.awt.Dimension(150, 260));
        panelImagen.setLayout(new java.awt.GridLayout(2, 1));

        portadaImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        portadaImagen.setMaximumSize(new java.awt.Dimension(50, 50));
        portadaImagen.setMinimumSize(new java.awt.Dimension(50, 50));
        portadaImagen.setPreferredSize(new java.awt.Dimension(50, 50));
        panelImagen.add(portadaImagen);

        selectImageButton.setText("Select Image");
        selectImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectImageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout changeImagePanelLayout = new javax.swing.GroupLayout(changeImagePanel);
        changeImagePanel.setLayout(changeImagePanelLayout);
        changeImagePanelLayout.setHorizontalGroup(
            changeImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, changeImagePanelLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(selectImageButton)
                .addContainerGap())
        );
        changeImagePanelLayout.setVerticalGroup(
            changeImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeImagePanelLayout.createSequentialGroup()
                .addComponent(selectImageButton)
                .addGap(0, 105, Short.MAX_VALUE))
        );

        panelImagen.add(changeImagePanel);

        add(panelImagen, java.awt.BorderLayout.LINE_END);
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAceptarActionPerformed
        
        if(datosCorrectos())
        {
            nuevoLibro.setIsbn(jTextFieldISBN.getText());
            nuevoLibro.setNifPrincAutor(jTextFieldNifAutor.getText());
            
            nuevoLibro.setPropietario(user);
            
            nuevoLibro.setTitulo(jTextFieldTitulo.getText());
            
            nuevoLibro.setFechaPublicacion(obtenerNuevaFecha());
            
            nuevoLibro.setPrecio(convertirFloat(jTextFieldPrecio.getText()));
            
            confirmarCambioImagen();
            
            //insertar en BD
            int n = pVisualizar.getConsultaLibros().insertBook(nuevoLibro);
            
            if(n > 0)
            {
                System.out.println("\nTodo ok en la insercion de libro");
            }
            else
            {
                System.out.println("\nAlgo ha fallado en la insercion");
            }
            
            
        }
        else
        {
            //error
            System.out.println("\nError en los datos");
        }
            
        ventanaPrincipal.cambiarAVisualizar();
    }//GEN-LAST:event_jButtonAceptarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        ventanaPrincipal.cambiarAVisualizar();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void selectImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectImageButtonActionPerformed
        
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
    }//GEN-LAST:event_selectImageButtonActionPerformed

    private void jDatePickerFechaPubliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDatePickerFechaPubliActionPerformed
        //GregorianCalendar fechaActualizada=obtenerNuevaFecha();
        
        fechaSeleccionada = true;
        //comprobar correccion de la fecha (que no sea posterior a la fecha actual)
        
        //nuevoLibro.setFechaPublicacion(fechaActualizada);
    }//GEN-LAST:event_jDatePickerFechaPubliActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel changeImagePanel;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonCancelar;
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
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelImagen;
    private javax.swing.JPanel panelMensajeVacio;
    private javax.swing.JPanel panelRelleno;
    private javax.swing.JLabel portadaImagen;
    private javax.swing.JButton selectImageButton;
    private javax.swing.JLabel tituloLabel;
    // End of variables declaration//GEN-END:variables
}
