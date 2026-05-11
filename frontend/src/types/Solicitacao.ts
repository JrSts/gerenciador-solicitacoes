import { UUID } from "crypto";
import { Solicitante } from "./Solicitante";
import { Categoria } from "./Categoria";

export enum StatusSolicitacao {
  SOLICITADO = "SOLICITADO",
  LIBERADO = "LIBERADO",
  APROVADO = "APROVADO",
  REJEITADO = "REJEITADO",
  CANCELADO = "CANCELADO",
}

export type Solicitacao = {
  id: UUID;
  solicitante: Solicitante;
  categoria: Categoria;
  descricao: string;
  valor: number;
  dataSolicitacao: string;
  status: StatusSolicitacao;
};
