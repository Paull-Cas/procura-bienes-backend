package com.microservice.transacciones.client;

import com.microservice.transacciones.config.FeignClientConfig;
import com.microservice.transacciones.client.Response.CompraResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "msvc-comprasycotizaciones",
        configuration = FeignClientConfig.class
)
public interface CompraClient {

    @GetMapping("/api/compra/{id}")
    CompraResponse getById(@PathVariable("id") Long id);
}
