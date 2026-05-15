import { FiltroSolicitacaoDTO } from "./../dtos/FiltroSolicitacoesDTO";
import { api } from "../lib/api";
import { Solicitacao } from "../types/Solicitacao";

export async function obterSolicitacoes(filtros?: FiltroSolicitacaoDTO) {
  let params = new URLSearchParams();

  const possuiFiltros =
    filtros?.categoriaId ||
    filtros?.status ||
    filtros?.dataInicio ||
    filtros?.dataFim;

  if (possuiFiltros) {
    if (filtros?.status) {
      params.append("status", filtros.status);
    }

    if (filtros?.categoriaId) {
      params.append("categoriaId", filtros.categoriaId);
    }

    if (filtros?.dataInicio && filtros?.dataFim) {
      params.append("dataInicio", filtros.dataInicio);
      params.append("dataFim", filtros.dataFim);
    }
  }

  const endPoint = possuiFiltros
    ? `/solicitacoes/filter?${params.toString()}`
    : `/solicitacoes`;

  const solicitacoes = await api.get<Solicitacao[]>(endPoint);

  return solicitacoes.data;
}
