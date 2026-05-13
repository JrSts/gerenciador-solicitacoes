import { StatusSolicitacao } from "@/types/Solicitacao";

export type FiltroSolicitacaoDTO = {
  categoriaId?: string;
  status?: string;
  dataInicio?: string;
  dataFim?: string;
};
