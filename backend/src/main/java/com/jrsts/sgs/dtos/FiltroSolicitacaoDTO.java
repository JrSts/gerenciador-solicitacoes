package com.jrsts.sgs.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.jrsts.sgs.enuns.StatusSolicitacao;

public record FiltroSolicitacaoDTO(
        UUID categoriaId,
        StatusSolicitacao status,
        LocalDate dataInicio,
        LocalDate dataFim) {

}
