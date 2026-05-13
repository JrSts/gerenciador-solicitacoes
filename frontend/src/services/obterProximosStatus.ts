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

type StatusItem = {
  id: number;
  status: StatusSolicitacao;
};

type StatusList = StatusItem[];

export const statusList: StatusList = [
  { id: 1, status: StatusSolicitacao.SOLICITADO },
  { id: 2, status: StatusSolicitacao.LIBERADO },
  { id: 3, status: StatusSolicitacao.APROVADO },
  { id: 4, status: StatusSolicitacao.REJEITADO },
  { id: 5, status: StatusSolicitacao.CANCELADO },
];
