package com.jrsts.sgs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jrsts.sgs.service.SolicitacaoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.jrsts.sgs.dtos.SolicitacaoDTO;
import com.jrsts.sgs.model.Solicitacao;

@RestController
@RequestMapping("/solicitacoes")
public class SolicitacaoController {

  private final SolicitacaoService solicitacaoService;

  public SolicitacaoController(SolicitacaoService solicitacaoService) {
    this.solicitacaoService = solicitacaoService;
  }

  @PostMapping
  public ResponseEntity<Solicitacao> salvarSolicitacao(@RequestBody SolicitacaoDTO solicitacaoDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(solicitacaoService.salvarSolicitacao(solicitacaoDTO));
  }

}
