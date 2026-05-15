import { StatusSolicitacao } from "@/types/Solicitacao";

export type FiltroSolicitacaoDTO = {
  categoriaId?: string;
  status?: StatusSolicitacao;
  dataInicio?: string;
  dataFim?: string;
};
