package com.jrsts.sgs.service;

import org.springframework.stereotype.Service;
import java.util.UUID;

import com.jrsts.sgs.dtos.SolicitanteDTO;
import com.jrsts.sgs.model.Solicitante;
import com.jrsts.sgs.repository.SolicitanteRepository;

@Service
public class SolicitanteService {

  private final SolicitanteRepository solicitanteRepository;

  public SolicitanteService(SolicitanteRepository solicitanteRepository) {
    this.solicitanteRepository = solicitanteRepository;
  }

  public Solicitante salvarSolicitante(SolicitanteDTO solicitanteDTO) {
    UUID id = UUID.randomUUID();
    Solicitante solicitante = new Solicitante(id, solicitanteDTO.nome(), solicitanteDTO.cpfCnpj());
    solicitanteRepository.salvarSolicitante(solicitante);
    return solicitante;
  }
}
