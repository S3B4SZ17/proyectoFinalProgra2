package com.proyectofinal.sistema_inventarios.Visual;

import javax.swing.*;

public class Menu {
    public static void main(String[] args) {
        String opcion = JOptionPane.showInputDialog(null,"");
        int opcionMenu = 0;
        int segundomenu = 0;
        /*
        do{
            opcionMenu = Integer.parseInt(JOptionPane.showInputDialog(
                    "************* MENU Quantum Electronics ***************\n"+
                            "	Seleccione (1) para iniciar sesion\n"));

            if(opcionMenu==17){
                do{
                    segundomenu = Integer.parseInt(JOptionPane.showInputDialog(
                            "************* MENU Soda VUENA ANVRE ***************\n"+

                                    "1.	Introducir informacion Personal de Pago\n" +
                                    "2.	Imprimir factura\n" +
                                    "3.	Nueva Orden\n" +
                                    "4.	Finalizar Programa\n" +


                                    "Seleccione una opcion"));

                    switch(segundomenu){

                        case 1:

                            usuario.setNombre(JOptionPane.showInputDialog("Digite su nombre :"));
                            usuario.setNúmero_teléfono(JOptionPane.showInputDialog("Digite su numero de Telefono :"));
                            JOptionPane.showConfirmDialog(null,forma_pago,"Sleccione el metodo de Pago", JOptionPane.OK_OPTION);

                            boolean seguir =true;

                            do{
                                if(forma_pago.getSelectedItem().toString().equals("Contado")){
                                    monto = Integer.parseInt(JOptionPane.showInputDialog(
                                            "Con cuanto va a pagar?"));
                                    if(producto.validarMonto(monto)){

                                        seguir = false;
                                    }
                                }else{
                                    break;
                                }


                            }while(seguir);




                            break;

                        case 2:

                            mensaje = JOptionPane.showInputDialog(null,"Desea agregar un comentario a tomar en cuenta por el chef?  ");
                            producto.imprimirFactura(usuario, mensaje, forma_pago.getSelectedItem().toString(), monto);



                            break;

                        case 3:
                            producto.borrarFactura();
                            opcionMenu =0;

                            break;
                        case 4:
                            System.exit(0);

                            break;



                    }



                }
                while(segundomenu != 3);


            }else{
                JOptionPane.showMessageDialog(null,"El alimento "+producto.lista.get(opcionMenu).getName()+" se ha agregado a la orden.");

                producto.seleccionarProducto(opcionMenu);
            }



        }
        while(opcionMenu != 17);
        */
    }
}
