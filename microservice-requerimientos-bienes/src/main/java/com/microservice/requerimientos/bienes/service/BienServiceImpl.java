package com.microservice.requerimientos.bienes.service;

import com.microservice.requerimientos.bienes.dto.BienDTO;
import com.microservice.requerimientos.bienes.entities.Bien;
import com.microservice.requerimientos.bienes.mapper.BienMapper;
import com.microservice.requerimientos.bienes.repository.IBienRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BienServiceImpl implements IBienService {

    @Autowired
    private IBienRepository bienRepository;


    @Override
    public void save(BienDTO bienDTO) {
        bienRepository.save(BienMapper.toEntity(bienDTO));
    }

    @Transactional
    @Override
    public List<BienDTO> getAll() {
        List<Bien> bienes = StreamSupport
                .stream(bienRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        if (bienes != null && !bienes.isEmpty()) return BienMapper.toDTOList(bienes);
        return null;
    }

    @Transactional
    @Override
    public BienDTO getById(Long id) {
        Bien bienByID = bienRepository.findById(id).orElse(null);
        if (bienByID != null) return BienMapper.toDTO(bienByID);
        return null;
    }

    @Override
    public void update(BienDTO bienDTO, Long id) {
        if (bienRepository.existsById(id)){
            Bien bien = BienMapper.toEntity(bienDTO);
            bien.setIdBien(id);
            bienRepository.save(bien);
        }
        else throw new RuntimeException("No existe el bien con el id: " + id);
    }

    @Override
    public void deleteById(Long id) {
        if (bienRepository.existsById(id)) bienRepository.deleteById(id);
        else throw new RuntimeException("No existe el bien con el id: " + id);
    }
}
