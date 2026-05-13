import { StatusSolicitacao } from "../types/Solicitacao";
import { UUID } from "crypto";

export type EditarStatusDTO = {
  id: UUID;
  novoStatus: StatusSolicitacao;
};
