package com.proyectofinal.sistema_inventarios.repository;

public interface SalesRepo {
    public int validarExistencias(int id);
    public double devuelveTotal();
    public void enviarAlertaMinimodeStock(String product, double quantity);

}
