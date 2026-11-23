package com.microservice.transacciones.service;

import com.microservice.transacciones.dto.request.PagoRequest;
import com.microservice.transacciones.dto.response.PagoResponse;
import com.microservice.transacciones.entities.Factura;
import com.microservice.transacciones.entities.Pago;
import com.microservice.transacciones.mapper.PagoMapper;
import com.microservice.transacciones.repository.IFacturaRepository;
import com.microservice.transacciones.repository.IPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PagoServiceImpl implements IPagoService {

    @Autowired
    IPagoRepository pagoRepository;

    @Autowired
    IFacturaRepository facturaRepository;

    @Autowired
    IFacturaService facturaService;

    @Override
    @Transactional
    public void save(PagoRequest pago) {
        Factura factura = facturaRepository.findById(pago.getIdFactura()).orElse(null);

        if (factura == null) {
            throw new RuntimeException("Factura no encontrada");
        }

        // Generar título del pago
        String tituloPago = generarTituloPago(factura);
        pago.setTitulo(tituloPago);

        pagoRepository.save(PagoMapper.toEntity(pago, factura));

        BigDecimal monto_actualizado = factura.getMontoPagado().add(pago.getMontoPago());
        factura.setMontoPagado(monto_actualizado);
        facturaService.updatePago(monto_actualizado, factura.getIdFactura());
    }

    private String generarTituloPago(Factura factura) {
        String tituloFactura = factura.getTitulo();
        String nombreUsuario = extraerNombreDeFactura(tituloFactura);
        String numeroFactura = extraerNumeroDeFactura(tituloFactura);

        Optional<String> ultimoTituloPago = pagoRepository.findLastTituloByFacturaLocked(factura.getIdFactura());

        int numeroPagoSecuencial = 1;
        if (ultimoTituloPago.isPresent()) {
            String[] partes = ultimoTituloPago.get().split("-");
            if (partes.length >= 4) {
                try {
                    numeroPagoSecuencial = Integer.parseInt(partes[partes.length - 1]) + 1;
                } catch (NumberFormatException e) {
                    numeroPagoSecuencial = 1;
                }
            }
        }

        return String.format("PAGO-%s-F%s-%d", nombreUsuario, numeroFactura, numeroPagoSecuencial);
    }

    private String extraerNombreDeFactura(String tituloFactura) {
        String[] partes = tituloFactura.split("-");
        if (partes.length >= 2) {
            return partes[1];
        }
        return "USUARIO";
    }

    private String extraerNumeroDeFactura(String tituloFactura) {
        String[] partes = tituloFactura.split("-");
        if (partes.length >= 3) {
            return partes[2];
        }
        return "1";
    }

    @Transactional
    @Override
    public List<PagoResponse> getAll() {
        List<Pago> pagos = StreamSupport
                .stream(pagoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        if (pagos.isEmpty()) return null;
        return PagoMapper.toResponseList(pagos);
    }

    @Transactional
    @Override
    public List<PagoResponse> getListByIdFactura(Long idFactura) {
        List<Pago> pagos = pagoRepository.findPagoByFactura_IdFactura(idFactura);
        if (pagos.isEmpty()) return null;
        return PagoMapper.toResponseList(pagos);
    }

    @Override
    public PagoResponse getById(Long id) {
        return null;
    }

    @Override
    public void update(PagoRequest pago) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
