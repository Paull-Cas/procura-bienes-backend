package com.microservice.comprasycotizaciones.service;

public class GenerarTitulo {

    public static String toCotizacion(String codigoProcura, String alias) {
        String titulo = "COT-" + alias.toUpperCase().replaceAll("[^A-Z0-9]", "") + "-" + codigoProcura;
        return titulo;
    }

    public static String toCompra(String codigoProcura, Long nroSecuencial) {
        String titulo = "COMP" + nroSecuencial + "-" + codigoProcura;
        return titulo;
    }
}
