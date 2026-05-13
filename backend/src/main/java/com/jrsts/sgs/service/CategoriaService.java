package com.jrsts.sgs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jrsts.sgs.dtos.CategoriaDTO;
import com.jrsts.sgs.exception.ResourceNotFoundException;
import com.jrsts.sgs.model.Categoria;
import com.jrsts.sgs.repository.CategoriaRepository;

@Service
public class CategoriaService {

  private final CategoriaRepository categoriaRepository;

  public CategoriaService(CategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }

  public Categoria salvar(CategoriaDTO categoriaDTO) {
    UUID id = UUID.randomUUID();
    Categoria categoria = new Categoria(id, categoriaDTO.nome());
    categoriaRepository.salvar(categoria);
    return categoria;
  }

  public Categoria buscarPorId(UUID id) {
    return categoriaRepository.buscarPorId(id)
        .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
  }

  public List<Categoria> listar() {
    return categoriaRepository.listar();
  }
}
