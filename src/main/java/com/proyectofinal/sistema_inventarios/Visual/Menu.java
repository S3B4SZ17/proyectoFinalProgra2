package com.proyectofinal.sistema_inventarios.Visual;

import com.proyectofinal.sistema_inventarios.Config.SpringJdbcConfig;
import com.proyectofinal.sistema_inventarios.DAO.*;
import com.proyectofinal.sistema_inventarios.repository.*;
import com.proyectofinal.sistema_inventarios.service.Product;
import com.proyectofinal.sistema_inventarios.service.TipoPago;
import com.proyectofinal.sistema_inventarios.service.Users;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.EventListener;

public class Menu extends JFrame{
    @Autowired
    private static SpringJdbcConfig springJdbcConfig =new SpringJdbcConfig();
    private static UserRepo userRepo = new UserDAOimplementation(springJdbcConfig.postgresqlDataSource());
    private static ProductRepo productRepo = new ProductDAOimplementation(springJdbcConfig.postgresqlDataSource());
    private static SalesRepo salesRepo = new SalesImplementation(springJdbcConfig.postgresqlDataSource());
    private static TipoPagoRepo tipoPagoRepo= new TipoPagoImplementacion(springJdbcConfig.postgresqlDataSource());
    private static InvoiceRepo invoiceRepo = new InvoiceImplementation(springJdbcConfig.postgresqlDataSource());


    public static void main(String[] args) {
        int opcionMenu = 0;
        int segundomenu = 0;

        //iniciarSesion();
        mostrarMenu();

    }

    private static void iniciarSesion(){
        int intentos=0;
        boolean seguir = true;
        JPasswordField passwordField = new JPasswordField();
        Object[] sesion = {"Ingrese la contrasena", passwordField};
        do {
            try {
                int usuario = Integer.parseInt(JOptionPane.showInputDialog(
                        "************* INICIO SESION Quantum Electronics ***************\n" +
                                "	   Digite su cedula de Usuario\n"));
                JOptionPane.showConfirmDialog(null,sesion,"************* MENU Quantum Electronics ***************\n" +
                        "	Digite su contrasena\n",JOptionPane.OK_OPTION);
                Users users = userRepo.getUser(usuario);
                if(usuario == users.getCedula() && passwordField.getText().equals(users.getContrasena())){
                    seguir = false;
                }else{
                    intentos++;
                    JOptionPane.showMessageDialog(null, "Credenciales Invalidas, intente de nuevo");
                    passwordField.setText("");
                    if (intentos==3){
                        System.exit(0);
                    }
                }
            }catch (Exception e){

                JOptionPane.showMessageDialog(null, "Credenciales Invalidas, intente de nuevo");
            }
        }while (seguir);
        JOptionPane.showMessageDialog(null, "Validado!!");
        mostrarMenu();

    }

    private static void seleccionarProductos(){
       
        /*Object[] buscarProducto = {"Ingrese el nombre del producto que desea buscar",nombre, "Ingrese la cantida de producto que desea comprar :", cantidad,
                "Tabla de resultados",table, buscar};*/
        // JOptionPane.showConfirmDialog(null,buscarProducto,"Ingrese los productos que desea agregar a su orden",JOptionPane.OK_OPTION);

    }

    private static void mostrarMenu(){
        int opcion =0;
        do{
            opcion= Integer.parseInt(JOptionPane.showInputDialog(
                    "************* MENU Quantum Electronics ***************\n" +
                            "1)	Consultar Inventarios\n"+
                            "2)	Realizar Compras\n"+
                            "3)	Realizar Venta\n"+
                            "4)	Generar Factura\n"+
                            "5)	Ingresar Nuevos Usuarios\n" +
                            "6)	Salir\n"));
            switch(opcion){
                case 1:
                    break;
                case 2:
                    realizarCompra();
                    break;
                case 3:
                    //introducirFormaPago();
                    seleccionarProductos();
                    realizarVenta();
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        }while(opcion!=6);

    }

    private static void realizarVenta() {
        Product product = new Product();
        JTextField nombre = new JTextField();
        JTextField cantidad = new JTextField();
        JTable table = new JTable();
        JFrame frame = new JFrame("Lista Productos");
        frame.add(table);
        frame.add(cantidad);
        frame.setLayout(null);
        frame.setSize(500,600);
        table.setVisible(true);

        JButton buscar = new JButton("Presione para buscar el producto");

        buscar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

            }
        });
        Object[] buscarProducto = {"Ingrese el nombre del producto que desea buscar",nombre, "Ingrese la cantida de producto que desea comprar :", cantidad,
                "Tabla de resultados",table, buscar};
       // JOptionPane.showConfirmDialog(null,buscarProducto,"Ingrese los productos que desea agregar a su orden",JOptionPane.OK_OPTION);

    }


    private static void realizarCompra() {
        Product product = new Product();
        JTextField nombre = new JTextField();
        JTextField cantidad = new JTextField();
        JTextField precio = new JTextField();
        JTextField descripcion = new JTextField();
        Object[] ingresoProducto = {"* Ingrese el nombre del producto",nombre, "* Ingrese la cantidad de producto",cantidad,
                "* Ingrese el precio del producto",precio,"Ingrese una breve descripcion del producto",descripcion};

        boolean seguir = true;
        do{
            JOptionPane.showConfirmDialog(null,ingresoProducto,"Ingrese la informacion para agregar un nuevo producto",JOptionPane.OK_OPTION);
            if(nombre.getText().equals("") || cantidad.getText().equals("")|| precio.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Debe ingresar un valor en todos los espacios requeridos **");
            }else{
                seguir=false;
            }
        }while(seguir);

        product.setName(nombre.getText());
        product.setQuantity(Integer.parseInt(cantidad.getText()));
        product.setPrice(Double.parseDouble(precio.getText()));
        product.setDescription(descripcion.getText());
        product.setDateOfPurchase(LocalDateTime.now());
        try{
            productRepo.save(product);
            JOptionPane.showMessageDialog(null, "Producto ingresado Correctamente");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al ingresar el producto");
        }
    }

    private static void introducirFormaPago(){
        TipoPago tipoPago = new TipoPago();
        JTextField numero = new JTextField();
        JTextField fechaEx = new JTextField();
        JTextField cvv = new JTextField();
        JComboBox<String> tipoTarjeta = new JComboBox<>();
        tipoTarjeta.addItem("Debito");
        tipoTarjeta.addItem("Credito");


        Object[] ingresoFormapago = {"* Ingrese el numero de tarjeta",numero, "* Ingrese la fecha de expiracion de la forma 'MM/YY'",fechaEx,
                "* Ingrese el codigo de seguridad de la tarjeta",cvv,"Seleccione el tipo de tarjeta", tipoTarjeta};

        JOptionPane.showConfirmDialog(null,ingresoFormapago,"Ingrese la informacion para agregar la forma de Pago",JOptionPane.OK_OPTION);

        try{
            tipoPagoRepo.validarTarjeta(Integer.parseInt(numero.getText()),fechaEx.getText(), cvv.getText());

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Informacion de la Tarjeta erronea");
        }
    }
}
