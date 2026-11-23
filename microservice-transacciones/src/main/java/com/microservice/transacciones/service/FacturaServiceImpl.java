package com.microservice.transacciones.service;

import com.microservice.transacciones.client.CompraClient;
import com.microservice.transacciones.client.UserClient;
import com.microservice.transacciones.dto.request.FacturaRequest;
import com.microservice.transacciones.dto.response.FacturaResponse;
import com.microservice.transacciones.client.Response.CompraResponse;
import com.microservice.transacciones.client.Response.UsuarioResponse;
import com.microservice.transacciones.entities.Factura;
import com.microservice.transacciones.mapper.FacturaMapper;
import com.microservice.transacciones.repository.IFacturaRepository;
import com.microservice.transacciones.security.CustomAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FacturaServiceImpl implements IFacturaService {

    @Autowired
    IFacturaRepository facturaRepository;

    @Autowired
    UserClient userClient;

    @Autowired
    CompraClient compraClient;

    @Transactional
    public void save(FacturaRequest facturaRequest) {
        Long idUsuario = facturaRequest.getIdUsuario();

        validarOrdenCompra(facturaRequest.getIdOrdenOperativa(), idUsuario);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String alias = ((CustomAuthenticationToken) authentication).getAlias();

        Optional<String> ultimoTitulo = facturaRepository.findLastTituloByUsuarioLocked(idUsuario);

        int numeroSecuencial = 1;
        if (ultimoTitulo.isPresent()) {
            String[] partes = ultimoTitulo.get().split("-");
            if (partes.length >= 3) {
                try {
                    numeroSecuencial = Integer.parseInt(partes[partes.length - 1]) + 1;
                } catch (NumberFormatException e) {
                    numeroSecuencial = 1;
                }
            }
        }

        String titulo = generarTitulo(alias.toUpperCase().replaceAll("[^A-Z0-9]", ""), numeroSecuencial);

        facturaRequest.setTitulo(titulo);
        facturaRepository.save(FacturaMapper.toEntity(facturaRequest));
    }

    private String generarTitulo(String alias, int numeroSecuencial) {
        return String.format("FACT-%s-%d", alias, numeroSecuencial);
    }

    // La factura solo se puede cargar contra una orden de compra propia que ya fue aceptada por el proveedor
    private void validarOrdenCompra(Long idOrdenCompra, Long idUsuario) {
        if (idOrdenCompra == null) {
            throw new RuntimeException("La factura debe estar asociada a una orden de compra");
        }
        CompraResponse orden = compraClient.getById(idOrdenCompra);
        if (orden == null || orden.getIdCompra() == null) {
            throw new RuntimeException("La orden de compra no existe");
        }
        if (!"Aceptada".equals(orden.getEstado())) {
            throw new RuntimeException("La orden de compra aún no ha sido aceptada");
        }
        if (!orden.getIdUsuario().equals(idUsuario)) {
            throw new RuntimeException("La orden de compra no pertenece al proveedor");
        }
    }


    @Transactional
    @Override
    public List<FacturaResponse> getAll(Long idUsuario) {

        List<String> permisos = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        List<Factura> facturas = StreamSupport
                .stream(facturaRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        List<Factura> facturasFiltradas = facturas.stream()
                .filter(factura -> {
                    boolean puedeVerTodasLasFacturas = permisos.contains("Ver todas las Facturas");

                    boolean puedeVerMisFacturas = permisos.contains("Ver mis Facturas")
                            && factura.getIdUsuario().equals(idUsuario);

                    return puedeVerTodasLasFacturas || puedeVerMisFacturas;
                })
                .collect(Collectors.toList());

        List<Long> usuariosIds = facturasFiltradas.stream()
                .map(Factura :: getIdUsuario)
                .distinct()
                .collect(Collectors.toList());


        List<UsuarioResponse> usuarios = userClient.findAllUsuarios(usuariosIds);
        return FacturaMapper.toResponseList(facturasFiltradas, usuarios);
    }

    @Transactional
    @Override
    public FacturaResponse getById(Long id) {
        Factura factura = facturaRepository.findById(id).orElse(null);
        List<UsuarioResponse> usuarios = userClient.findAllUsuarios(List.of(factura.getIdUsuario()));
        if (factura == null) return null;
        return FacturaMapper.toResponse(factura, usuarios).orElse(null);
    }

    @Override
    public void update(FacturaRequest factura, Long id) {
        if(facturaRepository.existsById(id)){
            Factura ufactura = FacturaMapper.toEntity(factura);
            ufactura.setIdFactura(id);
            ufactura.setMontoPagado(BigDecimal.ZERO);
            facturaRepository.save(ufactura);
        }
        else throw new RuntimeException("Factura no encontrada, id: " + id);
    }

    @Override
    public void updatePago(BigDecimal monto_actualizado, Long id) {
        if(facturaRepository.existsById(id)){
            Factura factura = facturaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Factura no encontrada, id: " + id));

            BigDecimal diferencia = factura.getMontoTotal().subtract(monto_actualizado);

            if (diferencia.compareTo(BigDecimal.ZERO) > 0) {
                factura.setEstadoFactura("Pagando");
            } else {
                factura.setEstadoFactura("Pagada");
            }

            factura.setMontoPagado(monto_actualizado);
            facturaRepository.save(factura);
        }
        else {
            throw new RuntimeException("Factura no encontrada, id: " + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        if(facturaRepository.existsById(id))facturaRepository.deleteById(id);
        else throw new RuntimeException("Factura no encontrada, id: " + id);
    }
}
