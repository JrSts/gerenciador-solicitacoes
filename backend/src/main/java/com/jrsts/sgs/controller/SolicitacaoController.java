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
import com.jrsts.sgs.exception.ResourceNotFoundException;
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
  public ResponseEntity<List<Solicitacao>> buscarSolicitacoesComFiltro(
      @ModelAttribute FiltroSolicitacaoDTO filtrosDTO) {
    return ResponseEntity.ok().body(solicitacaoService.buscarSolicitacoesComFiltro(filtrosDTO));
  }

  @PostMapping
  public ResponseEntity<Solicitacao> salvarSolicitacao(@RequestBody SolicitacaoDTO solicitacaoDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(solicitacaoService.salvarSolicitacao(solicitacaoDTO));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Solicitacao> atualizarStatusSolicitacao(@PathVariable UUID id,
      @RequestBody AtualizarStatusDTO atualizarStatusDTO) {
    Solicitacao solicitacao = solicitacaoService.buscarSolicitacaoPorId(id);

    if (solicitacao == null) {
      throw new ResourceNotFoundException("Solicitação não encontrada");
    }

    return ResponseEntity.ok(solicitacaoService.atualizarStatusSolicitacao(
        id,
        atualizarStatusDTO.getStatus()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Solicitacao> buscarSolicitacaoPorId(@PathVariable UUID id) {
    Solicitacao solicitacao = solicitacaoService.buscarSolicitacaoPorId(id);

    if (solicitacao == null) {
      throw new ResourceNotFoundException("Solicitação não encontrada");
    }
    return ResponseEntity.ok().body(solicitacao);
  }

  @GetMapping
  public ResponseEntity<List<Solicitacao>> buscarSolicitacoes() {
    return ResponseEntity.ok().body(solicitacaoService.buscarSolicitacoes());
  }

}
