package com.jrsts.sgs.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrsts.sgs.dtos.AtualizarStatusDTO;
import com.jrsts.sgs.dtos.FiltroSolicitacaoDTO;
import com.jrsts.sgs.dtos.SolicitacaoDTO;
import com.jrsts.sgs.model.Solicitacao;
import com.jrsts.sgs.service.SolicitacaoService;

@RestController
@RequestMapping("/solicitacoes")
public class SolicitacaoController {

  private final SolicitacaoService solicitacaoService;

  public SolicitacaoController(SolicitacaoService solicitacaoService) {
    this.solicitacaoService = solicitacaoService;
  }

  @GetMapping("/filter")
  public ResponseEntity<List<Solicitacao>> buscarComFiltro(
      @ModelAttribute FiltroSolicitacaoDTO filtrosDTO) {
    return ResponseEntity.ok(solicitacaoService.buscarComFiltro(filtrosDTO));
  }

  @PostMapping
  public ResponseEntity<Solicitacao> salvar(@RequestBody SolicitacaoDTO solicitacaoDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(solicitacaoService.salvar(solicitacaoDTO));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Solicitacao> atualizarStatus(@PathVariable UUID id,
      @RequestBody AtualizarStatusDTO atualizarStatusDTO) {
    return ResponseEntity.ok(solicitacaoService.atualizarStatusSolicitacao(
        id,
        atualizarStatusDTO.getStatus()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Solicitacao> buscarPorId(@PathVariable UUID id) {
    return ResponseEntity.ok(solicitacaoService.buscarPorId(id));
  }

  @GetMapping
  public ResponseEntity<List<Solicitacao>> listar() {
    return ResponseEntity.ok(solicitacaoService.listar());
  }

}
