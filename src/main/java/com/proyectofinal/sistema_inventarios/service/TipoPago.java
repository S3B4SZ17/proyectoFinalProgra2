package com.proyectofinal.sistema_inventarios.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoPago {
    private FormaPago tipo;
    private int numeroTarjeta;
    private String fechaExpiracion;
    private String CVV;


}
