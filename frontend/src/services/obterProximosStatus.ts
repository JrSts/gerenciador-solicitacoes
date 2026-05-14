import { StatusSolicitacao } from "../types/Solicitacao";

export const transicoesStatus = {
  [StatusSolicitacao.SOLICITADO]: [
    StatusSolicitacao.LIBERADO,
    StatusSolicitacao.REJEITADO,
  ],

  [StatusSolicitacao.LIBERADO]: [
    StatusSolicitacao.APROVADO,
    StatusSolicitacao.REJEITADO,
  ],

  [StatusSolicitacao.APROVADO]: [StatusSolicitacao.CANCELADO],

  [StatusSolicitacao.REJEITADO]: [],

  [StatusSolicitacao.CANCELADO]: [],
};

export function obterProximosStatus(statusAtual: StatusSolicitacao) {
  return transicoesStatus[statusAtual] || [];
}
