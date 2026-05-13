package com.jrsts.sgs.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrsts.sgs.dtos.CategoriaDTO;
import com.jrsts.sgs.model.Categoria;
import com.jrsts.sgs.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

  private final CategoriaService categoriaService;

  public CategoriaController(CategoriaService categoriaService) {
    this.categoriaService = categoriaService;
  }

  @PostMapping
  public ResponseEntity<Categoria> salvar(@RequestBody CategoriaDTO categoriaDTO) {
    Categoria novaCategoria = categoriaService.salvar(categoriaDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Categoria> buscarPorId(@PathVariable UUID id) {
    Categoria categoria = categoriaService.buscarPorId(id);
    return ResponseEntity.ok(categoria);
  }

  @GetMapping
  public ResponseEntity<List<Categoria>> listar() {
    return ResponseEntity.ok(categoriaService.listar());
  }

}
