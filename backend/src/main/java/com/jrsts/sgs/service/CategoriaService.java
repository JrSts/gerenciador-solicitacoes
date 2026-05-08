package com.jrsts.sgs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jrsts.sgs.model.Categoria;
import com.jrsts.sgs.repository.CategoriaRepository;
import com.jrsts.sgs.dtos.CategoriaDTO;

@Service
public class CategoriaService {

  private final CategoriaRepository categoriaRepository;

  public CategoriaService(CategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }

  public Categoria criarCategoria(CategoriaDTO categoriaDTO) {
    UUID id = UUID.randomUUID();
    Categoria categoria = new Categoria(id, categoriaDTO.nome());
    categoriaRepository.salvarCategoria(categoria);
    return categoria;
  }

  public Categoria buscarCategoriaPorId(UUID id) {
    return categoriaRepository.buscarCategoriaPorId(id);
  }

  public List<Categoria> buscarCategorias() {
    return categoriaRepository.buscarCategorias();
  }
}
