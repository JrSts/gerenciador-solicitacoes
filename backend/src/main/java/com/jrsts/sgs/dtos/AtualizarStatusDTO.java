package com.jrsts.sgs.dtos;

import com.jrsts.sgs.enuns.StatusSolicitacao;

public class AtualizarStatusDTO {
  private StatusSolicitacao status;

  public StatusSolicitacao getStatus() {
    return status;
  }

  public void setStatus(StatusSolicitacao status) {
    this.status = status;
  }
}
