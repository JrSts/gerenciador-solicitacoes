package com.jrsts.sgs.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jrsts.sgs.dtos.SolicitacaoDTO;
import com.jrsts.sgs.model.Categoria;
import com.jrsts.sgs.model.Solicitacao;
import com.jrsts.sgs.model.Solicitante;
import com.jrsts.sgs.repository.CategoriaRepository;
import com.jrsts.sgs.repository.SolicitacaoRepository;
import com.jrsts.sgs.repository.SolicitanteRepository;

@Service
public class SolicitacaoService {

  private final SolicitacaoRepository solicitacaoRepository;
  private final SolicitanteRepository solicitanteRepository;
  private final CategoriaRepository categoriaRepository;

  public SolicitacaoService(
      SolicitacaoRepository solicitacaoRepository,
      SolicitanteRepository solicitanteRepository,
      CategoriaRepository categoriaRepository) {
    this.solicitacaoRepository = solicitacaoRepository;
    this.solicitanteRepository = solicitanteRepository;
    this.categoriaRepository = categoriaRepository;
  }

  public Solicitacao salvarSolicitacao(SolicitacaoDTO solicitacaoDTO) {
    UUID id = UUID.randomUUID();
    Solicitante solicitante = solicitanteRepository.buscarSolicitantePorId(solicitacaoDTO.solicitanteId());
    Categoria categoria = categoriaRepository.buscarCategoriaPorId(solicitacaoDTO.categoriaId());
    Solicitacao solicitacao = new Solicitacao(
        id,
        solicitante,
        categoria,
        solicitacaoDTO.descricao(),
        solicitacaoDTO.valor());
    solicitacaoRepository.salvarSolicitacao(solicitacao);
    return solicitacao;
  }
}
