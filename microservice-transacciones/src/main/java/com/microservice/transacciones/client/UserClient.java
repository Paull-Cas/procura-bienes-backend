package com.microservice.transacciones.client;

import com.microservice.transacciones.config.FeignClientConfig;
import com.microservice.transacciones.client.Response.UsuarioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "msvc-authusuarios",
        configuration = FeignClientConfig.class
)
public interface UserClient {

    @GetMapping("/api/usuario/datos-by-id")
    List<UsuarioResponse> findAllUsuarios(@RequestParam("idList") List<Long> idList);
}

