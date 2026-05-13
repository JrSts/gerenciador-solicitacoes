package com.jrsts.sgs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrsts.sgs.dtos.SolicitanteDTO;
import com.jrsts.sgs.model.Solicitante;
import com.jrsts.sgs.service.SolicitanteService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/solicitantes")
public class SolicitanteController {

  private final SolicitanteService solicitanteService;

  public SolicitanteController(SolicitanteService solicitanteService) {
    this.solicitanteService = solicitanteService;
  }

  @GetMapping
  public ResponseEntity<List<Solicitante>> buscarSolicitantes() {
    List<Solicitante> solicitantes = solicitanteService.buscarSolicitantes();
    return ResponseEntity.ok().body(solicitantes);
  }

  @PostMapping
  public ResponseEntity<Solicitante> criarSolicitante(@RequestBody SolicitanteDTO solicitanteDTO) {
    System.out.println(solicitanteDTO.nome() + " | " + solicitanteDTO.cpfCnpj());
    return ResponseEntity.status(HttpStatus.CREATED).body(solicitanteService.salvarSolicitante(solicitanteDTO));
  }
}
