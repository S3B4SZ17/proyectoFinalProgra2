package com.proyectofinal.sistema_inventarios.repository;

public interface SalesRepo {
    public double validarExistencias(int id);
    public double devuelveTotal();
    public double ventaProductos();
    public void enviarAlertaMinimodeStock(String product, double quantity);

}
