/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectofinal.sistema_inventarios.Visual;

import com.proyectofinal.sistema_inventarios.Config.SpringJdbcConfig;
import com.proyectofinal.sistema_inventarios.DAO.InvoiceImplementation;
import com.proyectofinal.sistema_inventarios.DAO.ProductDAOimplementation;
import com.proyectofinal.sistema_inventarios.DAO.SalesImplementation;
import com.proyectofinal.sistema_inventarios.DAO.TipoPagoImplementacion;
import com.proyectofinal.sistema_inventarios.repository.InvoiceRepo;
import com.proyectofinal.sistema_inventarios.repository.ProductRepo;
import com.proyectofinal.sistema_inventarios.repository.SalesRepo;
import com.proyectofinal.sistema_inventarios.repository.TipoPagoRepo;
import com.proyectofinal.sistema_inventarios.service.Invoices;
import com.proyectofinal.sistema_inventarios.service.Product;
import com.proyectofinal.sistema_inventarios.service.Users;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sebastianzumbado
 */
public class GenerarFactura extends javax.swing.JFrame {

    /**
     * Creates new form GenerarFactura
     */
    @Autowired
    private SpringJdbcConfig springJdbcConfig =new SpringJdbcConfig();
    private ProductRepo productRepo = new ProductDAOimplementation(springJdbcConfig.postgresqlDataSource());
    private SalesRepo salesRepo = new SalesImplementation(springJdbcConfig.postgresqlDataSource());
    private TipoPagoRepo tipoPagoRepo= new TipoPagoImplementacion(springJdbcConfig.postgresqlDataSource());
    private InvoiceRepo invoiceRepo = new InvoiceImplementation(springJdbcConfig.postgresqlDataSource());
    private FileWriter fileWriter;
    Users user = new Users();
    public GenerarFactura() {
        
        initComponents();
        llenarTablaProductos();
        setLocationRelativeTo(null);
    }
    
    public GenerarFactura(Users user) {
        
        initComponents();
        llenarTablaProductos();
        setLocationRelativeTo(null);
        this.user = user;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        idfactura = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDFactura", "Cedula del Usuario", "Fecha de la Factura", "Total Factura"
            }
        ));
        jScrollPane1.setViewportView(table);

        jButton1.setText("Generar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Introduzca el ID de la factura y presione el boton para enviar un correo con la factura adjunta");

        jButton2.setText("Menu");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idfactura, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addContainerGap(37, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(72, 72, 72))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(idfactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        imprimirfactura();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Menu1 menu = new Menu1(user);
                menu.setVisible(true);
                dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    
    public void llenarTablaProductos(){
        DefaultTableModel model = (DefaultTableModel) table.getModel(); 
        List<Invoices> lista = new LinkedList<>();
       
        lista = invoiceRepo.list();
        Object[] row = new Object[4];
        
        for(int i = 0; i<lista.toArray().length;i++){
            row[0]=lista.get(i).getIdFactura();
            row[1]=lista.get(i).getUsers().getCedula();
            row[2]=lista.get(i).getInvoiceDate();
            row[3]=lista.get(i).getTotal();
            
            model.addRow(row);
        }   
       
    }
    
    private void imprimirfactura(){
        List<Invoices> invoices= invoiceRepo.getFactura(Integer.parseInt(idfactura.getText()));
        double total = invoiceRepo.getTotal(Integer.parseInt(idfactura.getText()));
        
        
         try {
            fileWriter = new FileWriter("Factura-"+idfactura.getText()+".txt");

            fileWriter.write("Factura-"+invoices.get(0).getIdFactura()+"\n\n\n");
            fileWriter.write("Usuario : "+invoices.get(0).getUsers().getName()+" "+invoices.get(0).getUsers().getLastName()+"\n\n");
            for(int i = 0; i< invoices.size();i++){
                fileWriter.write(invoices.get(i).getProducts().get(i).toString()+"\n");
            }
            fileWriter.write("\n\nLa fecha de la factura es : "+invoices.get(0).getInvoiceDate().toString()+"\n\n");
            fileWriter.write("El total de la factura es : "+total);
            
            fileWriter.close();
            invoiceRepo.enviarCorreo(invoices.get(0),"Factura-"+idfactura.getText()+".txt");
            JOptionPane.showMessageDialog(null, "Se ha enviado el correo electronico correctamente a "+invoices.get(0).getUsers().getEmail());
            
        } catch (IOException ex) {
            Logger.getLogger(Invoices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    
    }
    
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
            java.util.logging.Logger.getLogger(GenerarFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GenerarFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenerarFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenerarFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GenerarFactura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField idfactura;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
