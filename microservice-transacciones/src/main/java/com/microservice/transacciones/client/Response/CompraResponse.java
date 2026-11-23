package com.microservice.transacciones.client.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompraResponse {

    private Long idCompra;

    private Long idUsuario;

    private String codigoOrdenCompra;

    private String estado;

}
