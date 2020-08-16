package com.proyectofinal.sistema_inventarios.repository;

import com.proyectofinal.sistema_inventarios.service.TipoPago;

import java.util.Date;

public interface TipoPagoRepo {
    public int validarTarjeta(int numeroTarjeta, Date fecha, int cvv);
    public int ingresarInfoTarjeta(TipoPago tipoPago);

}
