package com.jrsts.sgs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jrsts.sgs.dtos.FiltroSolicitacaoDTO;
import com.jrsts.sgs.dtos.SolicitacaoDTO;
import com.jrsts.sgs.enuns.StatusSolicitacao;
import com.jrsts.sgs.exception.ForbiddenException;
import com.jrsts.sgs.exception.ResourceNotFoundException;
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

  public Solicitacao salvar(SolicitacaoDTO solicitacaoDTO) {
    UUID id = UUID.randomUUID();
    Solicitante solicitante = solicitanteRepository.buscarPorId(solicitacaoDTO.solicitanteId())
        .orElseThrow(() -> new ResourceNotFoundException("Solicitante não encontrado!"));
    Categoria categoria = categoriaRepository.buscarPorId(solicitacaoDTO.categoriaId())
        .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada!"));
    Solicitacao solicitacao = new Solicitacao(
        id,
        solicitante,
        categoria,
        solicitacaoDTO.descricao(),
        solicitacaoDTO.valor());
    solicitacaoRepository.salvar(solicitacao);
    return solicitacao;
  }

  public Solicitacao atualizarStatusSolicitacao(UUID id, StatusSolicitacao novoStatus) {
    Solicitacao solicitacao = solicitacaoRepository.buscarPorId(id)
        .orElseThrow(() -> new ResourceNotFoundException("Solicitação não encontrada!"));
    if (!solicitacao.getStatus().podeAlterarPara(novoStatus)) {
      throw new ForbiddenException(
          "Não é permitido alterar o status da solicitação de " + solicitacao.getStatus() + " para " + novoStatus);
    }
    solicitacao.setStatus(novoStatus);
    solicitacaoRepository.atualizarStatus(solicitacao);
    return solicitacao;
  }

  public Solicitacao buscarPorId(UUID id) {
    Solicitacao solicitacao = solicitacaoRepository.buscarPorId(id)
        .orElseThrow(() -> new ResourceNotFoundException("Solicitação não encontrada!"));
    return solicitacao;
  }

  public List<Solicitacao> buscarComFiltro(FiltroSolicitacaoDTO filtrosDTO) {
    return solicitacaoRepository.buscarComFiltro(filtrosDTO);
  }

  public List<Solicitacao> listar() {
    return solicitacaoRepository.listar();
  }
}
