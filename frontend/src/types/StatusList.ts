import { StatusSolicitacao } from "./Solicitacao";

type StatusItem = {
  id: number;
  status: StatusSolicitacao;
};

export const statusList: StatusItem[] = [
  { id: 1, status: StatusSolicitacao.SOLICITADO },
  { id: 2, status: StatusSolicitacao.LIBERADO },
  { id: 3, status: StatusSolicitacao.APROVADO },
  { id: 4, status: StatusSolicitacao.REJEITADO },
  { id: 5, status: StatusSolicitacao.CANCELADO },
];
