package com.proyectofinal.sistema_inventarios.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private int cedula;
    private String name;
    private String lastName;
    private String phone;
    private FormaPago tipoPago;


}
