package com.microservice.requerimientos.bienes.service;

import com.microservice.requerimientos.bienes.client.UserClient;
import com.microservice.requerimientos.bienes.dto.DetalleRequerimientoDTO;
import com.microservice.requerimientos.bienes.dto.RequerimientoDTO;
import com.microservice.requerimientos.bienes.dto.request.RequerimientoConBienesRequest;
import com.microservice.requerimientos.bienes.dto.request.RequerimientoRequest;
import com.microservice.requerimientos.bienes.dto.response.TargetResponse;
import com.microservice.requerimientos.bienes.client.Response.UsuarioResponse;
import com.microservice.requerimientos.bienes.entities.Bien;
import com.microservice.requerimientos.bienes.entities.DetalleRequerimiento;
import com.microservice.requerimientos.bienes.entities.Requerimiento;
import com.microservice.requerimientos.bienes.mapper.DetalleRequerimientoMapper;
import com.microservice.requerimientos.bienes.mapper.RequerimientoMapper;
import com.microservice.requerimientos.bienes.mapper.TargetMapper;
import com.microservice.requerimientos.bienes.repository.IBienRepository;
import com.microservice.requerimientos.bienes.repository.IDetalleRequerimientoRepository;
import com.microservice.requerimientos.bienes.repository.IRequerimientoRepository;
import com.microservice.requerimientos.bienes.security.CustomAuthenticationToken;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RequerimientoServiceImpl implements IRequerimientoService {

    @Autowired
    IRequerimientoRepository requerimientoRepository;

    @Autowired
    IBienRepository bienRepository;

    @Autowired
    IDetalleRequerimientoRepository detalleRequerimientoRepository;

    @Autowired
    UserClient userClient;

    @Transactional
    public List<String> save(RequerimientoConBienesRequest request) {
        Map<String, List<DetalleRequerimientoDTO>> categorias = clasificarBienes(request);
        List<String> titulosGenerados = new ArrayList<>();
        Long idUsuario = request.getRequerimiento().getIdUsuario();
        String obra = request.getRequerimiento().getObra();

        categorias.forEach((categoria, detalles) -> {
            String tipo = obtenerTipoRequerimiento(categoria);
            String titulo = generarYGuardarRequerimiento(request, idUsuario, obra, tipo, detalles);
            titulosGenerados.add(titulo);
        });

        return titulosGenerados;
    }

    private Map<String, List<DetalleRequerimientoDTO>> clasificarBienes(RequerimientoConBienesRequest request) {
        return request.getBienes().stream()
                .collect(Collectors.groupingBy(dto -> {
                    Bien bien = bienRepository.findById(dto.getIdBien())
                            .orElseThrow(() -> new RuntimeException("Bien no encontrado: " + dto.getIdBien()));
                    return bien.getCategoria();
                }));
    }

    private String obtenerTipoRequerimiento(String categoria) {
        return "Materiales".equals(categoria) ? "Obra" : "Operación";
    }

    private String generarYGuardarRequerimiento(RequerimientoConBienesRequest request,
                                                Long idUsuario,
                                                String obra,
                                                String tipo,
                                                List<DetalleRequerimientoDTO> detalles) {

        int numeroSecuencial = requerimientoRepository.countByUsuarioAndTipoLocked(idUsuario, tipo) + 1;
        String titulo = generarTitulo(numeroSecuencial, tipo, obra);

        Requerimiento req = new Requerimiento();
        req.setIdUsuario(idUsuario);
        req.setObra(obra);
        req.setTipoRequerimiento(tipo);
        req.setTitulo(titulo);
        req.setDescripcion(request.getRequerimiento().getDescripcion());
        req.setFechaMaximaAbastecimiento(request.getRequerimiento().getFechaMaximaAbastecimiento());
        req.setEstado("Pendiente");

        Requerimiento savedReq = requerimientoRepository.save(req);

        guardarDetalles(detalles, savedReq);

        return titulo;
    }

    private String generarTitulo(int numeroSecuencial, String tipo, String obra) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String alias = ((CustomAuthenticationToken) authentication).getAlias();
        String sufijo = "Obra".equals(tipo) ? "MAT" : "HERR";
        String obraFormateada = obra.replaceAll("\\s+", "");
        return String.format("REQ%d-%s-%s-%s", numeroSecuencial, sufijo, obraFormateada, alias.toUpperCase().replaceAll("[^A-Z0-9]", ""));
    }

    private void guardarDetalles(List<DetalleRequerimientoDTO> detalles, Requerimiento req) {
        List<DetalleRequerimiento> entidades = detalles.stream()
                .map(dto -> mapearDetalle(dto, req))
                .collect(Collectors.toList());
        detalleRequerimientoRepository.saveAll(entidades);
    }

    private DetalleRequerimiento mapearDetalle(DetalleRequerimientoDTO dto, Requerimiento req) {
        Bien bien = bienRepository.findById(dto.getIdBien())
                .orElseThrow(() -> new RuntimeException("Bien no encontrado"));
        return DetalleRequerimientoMapper.toEntity(dto, req, bien);
    }

    @Transactional
    @Override
    public List<TargetResponse> getAll(Long idUsuario) {

        List<Requerimiento> requerimientosFiltrados = RequerimientosPorPermiso.getRequerimientos(StreamSupport
                .stream(requerimientoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList()), idUsuario);

        List<Long> usuariosIds = requerimientosFiltrados.stream()
                .map(Requerimiento::getIdUsuario)
                .distinct()
                .collect(Collectors.toList());

        List<UsuarioResponse> usuarios = userClient.findAllUsuarios(usuariosIds);

        return TargetMapper.toListTarget(requerimientosFiltrados, usuarios);
    }

    @Transactional
    @Override
    public RequerimientoDTO getById(Long id) {
        Requerimiento requerimientoByID = requerimientoRepository.findById(id).orElse(null);
        if (requerimientoByID != null) return RequerimientoMapper.toDTO(requerimientoByID);
        else return null;
    }

    @Override
    public RequerimientoDTO getByTitulo(String titulo) {
        Requerimiento requerimientoByTitulo = requerimientoRepository.getByTitulo(titulo);
        return RequerimientoMapper.toDTO(requerimientoByTitulo);
    }

    @Override
    public void update(RequerimientoRequest requerimientoDTO, Long id) {
        if (requerimientoRepository.existsById(id)){
            Requerimiento requerimiento = RequerimientoMapper.toEntity(requerimientoDTO);
            requerimiento.setIdRequerimiento(id);
            requerimiento.setFechaRequerimiento(requerimientoRepository.findById(id).get().getFechaRequerimiento());
            requerimientoRepository.save(requerimiento);
        }
        else throw new RuntimeException("No existe el requerimiento con el id: " + id);
    }

    @Override
    public void deleteById(Long id) {
        if (requerimientoRepository.existsById(id)) {
            requerimientoRepository.deleteById(id);
        } else {
            throw new RuntimeException("No existe el requerimiento con el id: " + id);
        }
    }
}
