package com.jrsts.sgs.enuns;

public enum StatusSolicitacao {
  SOLICITADO,
  LIBERADO,
  APROVADO,
  REJEITADO,
  CANCELADO;

  public boolean podeAlterarPara(StatusSolicitacao novoStatus) {

    return switch (this) {

      case SOLICITADO ->
        novoStatus == LIBERADO
            || novoStatus == REJEITADO;

      case LIBERADO ->
        novoStatus == APROVADO
            || novoStatus == REJEITADO;

      case APROVADO ->
        novoStatus == CANCELADO;

      case REJEITADO,
          CANCELADO ->
        false;
    };
  }
}
