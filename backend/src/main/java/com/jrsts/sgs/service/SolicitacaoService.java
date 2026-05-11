package com.jrsts.sgs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jrsts.sgs.dtos.FiltroSolicitacaoDTO;
import com.jrsts.sgs.dtos.SolicitacaoDTO;
import com.jrsts.sgs.enuns.StatusSolicitacao;
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

  public Solicitacao atualizarStatusSolicitacao(UUID id, StatusSolicitacao novoStatus) {
    Solicitacao solicitacao = solicitacaoRepository.buscarSolicitacaoPorId(id).orElse(null);
    if (solicitacao == null)
      throw new RuntimeException("Solicitação nao encontrada!");
    if (!solicitacao.getStatus().podeAlterarPara(novoStatus)) {
      throw new RuntimeException(
          "Não é permitido alterar o status da solicitação de " + solicitacao.getStatus() + " para " + novoStatus);
    }
    solicitacao.setStatus(novoStatus);
    solicitacaoRepository.atualizarStatusSolicitacao(solicitacao);
    return solicitacao;
  }

  public Solicitacao buscarSolicitacaoPorId(UUID id) {
    Solicitacao solicitacao = solicitacaoRepository.buscarSolicitacaoPorId(id).orElse(null);
    if (solicitacao == null)
      throw new RuntimeException("Solicitação não encontrada!");
    return solicitacao;
  }

  public List<Solicitacao> buscarSolicitacoesComFiltro(FiltroSolicitacaoDTO filtrosDTO) {
    return solicitacaoRepository.buscarSolicitacoesComFiltro(filtrosDTO);
  }

  public List<Solicitacao> buscarSolicitacoes() {
    return solicitacaoRepository.buscarSolicitacoes();
  }
}
