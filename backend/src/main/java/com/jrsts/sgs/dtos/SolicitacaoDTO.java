package com.jrsts.sgs.dtos;

import java.util.UUID;

public record SolicitacaoDTO(UUID solicitanteId, UUID categoriaId, String descricao, double valor) {
}