package com.jrsts.sgs.controller;

import com.jrsts.sgs.service.CategoriaService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jrsts.sgs.dtos.CategoriaDTO;
import com.jrsts.sgs.model.Categoria;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

  private final CategoriaService categoriaService;

  public CategoriaController(CategoriaService categoriaService) {
    this.categoriaService = categoriaService;
  }

  @PostMapping
  public ResponseEntity<Categoria> criarCategoria(@RequestBody CategoriaDTO categoria) {
    Categoria novaCategoria = categoriaService.criarCategoria(categoria);
    return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable UUID id) {
    return ResponseEntity.ok().body(categoriaService.buscarCategoriaPorId(id));
  }

  @GetMapping
  public ResponseEntity<List<Categoria>> buscarCategorias() {
    return ResponseEntity.ok().body(categoriaService.buscarCategorias());
  }

}
