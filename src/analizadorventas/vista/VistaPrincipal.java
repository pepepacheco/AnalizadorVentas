/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorventas.vista;

import analizadorventas.controlador.Controlador;
import analizadorventas.controlador.ControladorDb;
import analizadorventas.controlador.ControladorPdf;
import analizadorventas.modelo.Transaccion;
import javax.swing.JFileChooser;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;



/**
 *
 * @author root
 */
public class VistaPrincipal extends javax.swing.JFrame {
    //Lista en la que guardaremos nuestros objetos
    List<Transaccion> lista = new ArrayList<>();
    //Cabecero de la tabla
    String[] header = {"Nombre del comprador","Producto","Precio", "Fecha de Transaccion", "Ciudad"};
    //Variable que nos permite llevar el control de si queremos que el programa actualice el formulario al movernos de registro
    boolean actualizaFormulario =true;
    //Variable para controlar si estamos borrando registros, para que no haga ciertas comprobaciones si resulta que si estamos borrando
    boolean borrando = false;
    //Variable para que no nos pregunte si queremos guardar los datos si ya estamos guardandolos
    boolean actualizando = false;
    //Variable que guarda el registro del que venimos
    int registro;
    //Variable que guarda el registro al que vamos
    int destino;
    
    /**
     * Creates new form VistaPrincipal
     */
    public VistaPrincipal() {
        initComponents();
        //Si la base de datos no tiene ninguna tupla permitimos al usuario cargar su csv
        //Si tenemos una base de datos funcional, deshabilitamos el boton y cargamos la base
        if (ControladorDb.verNumeroRegistros(ControladorDb.getConexiondb())>0){
            MAbrir.setEnabled(false);
            lblEstado.setText("Abriendo archivo");
            actualizaFormulario=false;
            lista = ControladorDb.devolverLista(ControladorDb.getConexiondb());
            jTable1.setModel(Controlador.InsertarRegistros(header, lista));
            actualizaFormulario=true;
            lblEstado.setText("Archivo abierto");
        }
        jTable1.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            //Aqui comprobamos que el sistema no este borrando archivos y que no haya ningun campo que este vacio
                if (!borrando && !fieldNombre.getText().matches("") && !fieldProducto.getText().matches("")
                        && !fieldPrecio.getText().matches("") && !fieldCiudad.getText().matches("") && !fieldFecha.getText().matches("")){
                    //Si la condiciones son las correctas creamos un objeto del Modelo con los datos de los campos del formulario
                    Transaccion t = new Transaccion(fieldNombre.getText(), fieldProducto.getText(),
                            Integer.parseInt(fieldPrecio.getText()), fieldFecha.getText(), fieldCiudad.getText());
                    //Comprobamos que el objeto creado antes es distinto del de la lista
                    //y que es la primera vez que entra, asegurandonos de que ha entrado por 
                    //una orden del usuario y no por un proceso del sistema
                    if (!lista.get(registro).equals(t) && !actualizando ){
                        if (jTable1.getSelectedRow() != -1){
                            //Guardamos el registro al que ibamos a ir,de modo que, podamos llevar al usuario a su destino
                            destino = jTable1.getSelectedRow();
                        }
                        //llamamos al dialogo
                        int valor = JOptionPane.showConfirmDialog(rootPane, "¿Guardar registros?","Guardar",JOptionPane.YES_NO_OPTION);
                        if(valor == JOptionPane.YES_OPTION){
                            //si el usuario decide guardar los datos, hacemos la modificacion
                            //del objeto transaccion de la lista
                            actualizaFormulario = false;
                            lista.get(registro).setTransaccion(new Transaccion(fieldNombre.getText(), fieldProducto.getText(), Integer.parseInt(fieldPrecio.getText()),fieldFecha.getText(), fieldCiudad.getText()));
                            ControladorDb.actualizarRegistro(ControladorDb.getConexiondb(), lista.get(registro), registro+1);
                            jTable1.setModel(Controlador.InsertarRegistros(header, lista));
                            actualizaFormulario = true;
                            //Colocamos al usuario en el registro al que iba en un principio
                            jTable1.setRowSelectionInterval(destino, destino);
                        }
                    }
                }
            if (jTable1.getSelectedRow() != -1){
                //Guardamos el registro en el que estamos, para usarlo en otros metodos como una especie de historial
                registro = jTable1.getSelectedRow();
                //actualizamos la pestaña de estado
                lblEstado.setText("Registro " + (jTable1.getSelectedRow()+1) + " de " + jTable1.getRowCount());
            }
            if (actualizaFormulario){
                //Si el usuario se ha movido de registro, cargamos los datos del nuevo objeto en el formulario
                fieldNombre.setText(lista.get(jTable1.getSelectedRow()).getNombreCliente());
                fieldProducto.setText(lista.get(jTable1.getSelectedRow()).getProductoComprado());
                fieldPrecio.setText(lista.get(jTable1.getSelectedRow()).getPrecio()+"");
                fieldFecha.setText(lista.get(jTable1.getSelectedRow()).getFecha());
                fieldCiudad.setText(lista.get(jTable1.getSelectedRow()).getCiudad());
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

        jFileChooser1 = new javax.swing.JFileChooser();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        fieldNombre = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        lblProducto = new javax.swing.JLabel();
        fieldProducto = new javax.swing.JTextField();
        lblPrecio = new javax.swing.JLabel();
        fieldPrecio = new javax.swing.JTextField();
        labelFecha = new javax.swing.JLabel();
        fieldFecha = new javax.swing.JTextField();
        lblCiudad = new javax.swing.JLabel();
        fieldCiudad = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        lblEstado = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MAbrir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mGenerar = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        mAcerca = new javax.swing.JMenuItem();

        jFormattedTextField1.setText("jFormattedTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(800, 600));

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(0.8);
        jSplitPane1.setPreferredSize(new java.awt.Dimension(800, 600));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre de comprador", "Producto", "Precio", "Fecha Transaccion", "Ciudad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jSplitPane1.setTopComponent(jScrollPane1);

        fieldNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldNombreActionPerformed(evt);
            }
        });

        lblNombre.setText("Nombre de usuario:");

        lblProducto.setText("Producto:");

        fieldProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldProductoActionPerformed(evt);
            }
        });

        lblPrecio.setText("Precio:");

        fieldPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldPrecioActionPerformed(evt);
            }
        });

        labelFecha.setText("Fecha Transaccion:");

        fieldFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldFechaActionPerformed(evt);
            }
        });

        lblCiudad.setText("Ciudad:");

        fieldCiudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldCiudadActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.setToolTipText("");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnAtras.setText("Atras");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        lblEstado.setText("Estado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCiudad)
                            .addComponent(labelFecha)
                            .addComponent(lblPrecio)
                            .addComponent(lblNombre)
                            .addComponent(lblProducto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fieldNombre)
                            .addComponent(fieldProducto)
                            .addComponent(fieldPrecio)
                            .addComponent(fieldFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                            .addComponent(fieldCiudad))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
                        .addComponent(btnAtras)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSiguiente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblEstado)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(fieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProducto)
                    .addComponent(fieldProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecio)
                    .addComponent(fieldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFecha)
                    .addComponent(fieldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCiudad)
                    .addComponent(fieldCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar)
                    .addComponent(btnNuevo)
                    .addComponent(btnSiguiente)
                    .addComponent(btnAtras))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(lblEstado)
                .addContainerGap())
        );

        jSplitPane1.setBottomComponent(jPanel1);

        jMenu1.setText("File");

        MAbrir.setText("Abrir");
        MAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MAbrirActionPerformed(evt);
            }
        });
        jMenu1.add(MAbrir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Info");

        mGenerar.setText("Generar PDF");
        mGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mGenerarActionPerformed(evt);
            }
        });
        jMenu2.add(mGenerar);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Acerca de");
        jMenu3.setToolTipText("");

        mAcerca.setText("Acerca de");
        mAcerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mAcercaActionPerformed(evt);
            }
        });
        jMenu3.add(mAcerca);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
 * 
 * @param evt 
 */
    private void MAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MAbrirActionPerformed
        //Cuando el usuario pulsa la opcion Abrir en el menu superior abrimos 
        //el JFileChooser para cargar un archivo
        lblEstado.setText("Abriendo archivo");
        int selection = jFileChooser1.showOpenDialog(jMenu1);
        if (selection == JFileChooser.APPROVE_OPTION){
            actualizaFormulario=false;
            //Creamos la lista de objetos con el archivo que nos han dado
            lista = Controlador.crearColeccionRegistros(jFileChooser1.getSelectedFile());
            //y la vocamos en la tabla
            jTable1.setModel(Controlador.InsertarRegistros(header, lista));
            actualizaFormulario=true;
            lblEstado.setText("Archivo abierto");
        }
    }//GEN-LAST:event_MAbrirActionPerformed

    // Si el usuario pulsa enter despues de modificar algun campo del formulario hacemos que la tabla se actualice
    /**
     * 
     * @param evt 
     */
    private void fieldNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldNombreActionPerformed
        lista.get(jTable1.getSelectedRow()).setNombreCliente(fieldNombre.getText());
        ControladorDb.actualizarRegistro(ControladorDb.getConexiondb(), lista.get(registro), registro+1);
        int fila = jTable1.getSelectedRow();
        actualizaFormulario=false;
        actualizando=true;
        jTable1.setModel(Controlador.InsertarRegistros(header, lista));
        actualizaFormulario=true;
        jTable1.setRowSelectionInterval(fila, fila);
        actualizando=false;
        lblEstado.setText("Registro actualizado");
    }//GEN-LAST:event_fieldNombreActionPerformed
/**
 * 
 * @param evt 
 */
    private void fieldProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldProductoActionPerformed
        lista.get(jTable1.getSelectedRow()).setProductoComprado(fieldProducto.getText());
        ControladorDb.actualizarRegistro(ControladorDb.getConexiondb(), lista.get(registro), registro+1);
        int fila = jTable1.getSelectedRow();
        actualizaFormulario=false;
        actualizando=true;
        jTable1.setModel(Controlador.InsertarRegistros(header, lista));
        actualizaFormulario=true;
        jTable1.setRowSelectionInterval(fila, fila);
        actualizando=false;
        lblEstado.setText("Registro actualizado");
    }//GEN-LAST:event_fieldProductoActionPerformed

    private void fieldPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldPrecioActionPerformed
        lista.get(jTable1.getSelectedRow()).setPrecio(Integer.parseInt(fieldPrecio.getText()));
        ControladorDb.actualizarRegistro(ControladorDb.getConexiondb(), lista.get(registro), registro+1);
        int fila = jTable1.getSelectedRow();
        actualizaFormulario=false;
        actualizando=true;
        jTable1.setModel(Controlador.InsertarRegistros(header, lista));
        actualizaFormulario=true;
        jTable1.setRowSelectionInterval(fila, fila);
        actualizando = false;
        lblEstado.setText("Registro actualizado");
    }//GEN-LAST:event_fieldPrecioActionPerformed
/**
 * @param evt 
 */
    private void fieldFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldFechaActionPerformed
        if (fieldFecha.getText().matches("\\d\\d\\d\\d-\\d\\d-\\d\\d")){
            lista.get(jTable1.getSelectedRow()).setFecha(fieldFecha.getText());
            ControladorDb.actualizarRegistro(ControladorDb.getConexiondb(), lista.get(registro), registro+1);
            int fila = jTable1.getSelectedRow();
            actualizaFormulario=false;
            actualizando = true;
            jTable1.setModel(Controlador.InsertarRegistros(header, lista));
            actualizaFormulario=true;
            jTable1.setRowSelectionInterval(fila, fila);
            actualizando =false;
            lblEstado.setText("Registro actualizado");
        }
        else
            lblEstado.setText("Fecha incorrecta: usa el formato YYYY-MM-DD");
    }//GEN-LAST:event_fieldFechaActionPerformed
/**
 * @param evt 
 */
    private void fieldCiudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldCiudadActionPerformed
        lista.get(jTable1.getSelectedRow()).setCiudad(fieldCiudad.getText());
        ControladorDb.actualizarRegistro(ControladorDb.getConexiondb(), lista.get(registro), registro+1);
        int fila = jTable1.getSelectedRow();
        actualizaFormulario=false;
        actualizando = true;
        jTable1.setModel(Controlador.InsertarRegistros(header, lista));
        actualizaFormulario=true;
        jTable1.setRowSelectionInterval(fila, fila);
        actualizando=false;
        lblEstado.setText("Registro actualizado");
    }//GEN-LAST:event_fieldCiudadActionPerformed

    //Tanto el boton siguiente como el boton atras son capaces de saltar del primer registro al ultimo
    //y viceversa
    /**
     * @param evt 
     */
    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        if (jTable1.getSelectedRow()!=0 && jTable1.getSelectedRow()!=-1)
            jTable1.setRowSelectionInterval(jTable1.getSelectedRow()-1, jTable1.getSelectedRow()-1);
        else if (jTable1.getSelectedRow()==0){
            jTable1.setRowSelectionInterval(jTable1.getRowCount()-1, jTable1.getRowCount()-1);
            jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());
        }
    }//GEN-LAST:event_btnAtrasActionPerformed
/**
 * @param evt 
 */
    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        if (jTable1.getSelectedRow()!=jTable1.getRowCount()-1 && jTable1.getSelectedRow()!=-1)
            jTable1.setRowSelectionInterval(jTable1.getSelectedRow()+1, jTable1.getSelectedRow()+1);
        else if(jTable1.getSelectedRow()==jTable1.getRowCount()-1 && jTable1.getSelectedRow()!=-1){
            jTable1.setRowSelectionInterval(0, 0);
            jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMinimum());        
        }
    }//GEN-LAST:event_btnSiguienteActionPerformed
    //Creamos un nuevo registro vacio, con la excepcion de la fecha, a la que le damos el dia actual por defecto
    /**
     * @param evt 
     */
    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        actualizaFormulario =false;
        jTable1.setModel(Controlador.nuevoRegistroVacio(header, lista));
        jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());
        actualizaFormulario=true;
        jTable1.setRowSelectionInterval(jTable1.getRowCount()-1, jTable1.getRowCount()-1);
        lblEstado.setText("Registro insertado");
    }//GEN-LAST:event_btnNuevoActionPerformed
    // Eliminamos el registro de la tabla en el que estamos actualmente, tambien lo borramos de la base de datos
    /**
     * @param evt 
     */
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        //usamos la variable borrando para que el listener de movimiento de la tabla no salte mientras borramos
        borrando = true;
        actualizaFormulario=false;
        jTable1.setModel(Controlador.borrarRegistro(header, lista,jTable1.getSelectedRow()));
        actualizaFormulario=true;
        if (jTable1.getRowCount()!=0){
            if (registro == jTable1.getRowCount())
                registro-=1;
            jTable1.setRowSelectionInterval(registro, registro);
        }
        borrando=false;
        lblEstado.setText("Registro borrado");
    }//GEN-LAST:event_btnEliminarActionPerformed
    //Generamos el pdf con todos los registros de la tabla
    /**
    * @param evt 
    */
    private void mGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mGenerarActionPerformed
        ControladorPdf.CrearPdf(lista);
    }//GEN-LAST:event_mGenerarActionPerformed
    //Lanzamos el JDialog con la informacion del proyecto y de su creador
    /**
    * @param evt 
    */
    private void mAcercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mAcercaActionPerformed
        JOptionPane.showConfirmDialog(rootPane, "Proyecto: Analizador de ventas \nAutor: Tomás Amate  ", "Acerca de", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_mAcercaActionPerformed
    
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
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MAbrir;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JTextField fieldCiudad;
    private javax.swing.JTextField fieldFecha;
    private javax.swing.JTextField fieldNombre;
    private javax.swing.JTextField fieldPrecio;
    private javax.swing.JTextField fieldProducto;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JLabel lblCiudad;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JMenuItem mAcerca;
    private javax.swing.JMenuItem mGenerar;
    // End of variables declaration//GEN-END:variables
}
