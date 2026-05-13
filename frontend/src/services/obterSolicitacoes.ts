import { FiltroSolicitacaoDTO } from "./../dtos/FiltroSolicitacoesDTO";
import { api } from "../lib/api";
import { Solicitacao } from "../types/Solicitacao";

export async function obterSolicitacoes(filtros?: FiltroSolicitacaoDTO) {
  let endPoint = "/solicitacoes";

  let response = await api.get<Solicitacao[]>(endPoint);

  const temFiltros =
    filtros?.status ||
    filtros?.categoriaId ||
    filtros?.dataInicio ||
    filtros?.dataFim;

  if (temFiltros) {
    const params = new URLSearchParams();

    if (filtros?.status) {
      params.append("status", filtros.status);
    }

    if (filtros?.categoriaId) {
      params.append("categoriaId", filtros.categoriaId);
    }

    if (filtros?.dataInicio) {
      params.append("dataInicio", filtros.dataInicio);
    }

    if (filtros?.dataFim) {
      params.append("dataFim", filtros.dataFim);
    }
    endPoint = `/solicitacoes/filter?${params.toString()}`;
  }

  response = await api.get<Solicitacao[]>(endPoint);

  if (!response) {
    throw new Error("Erro ao buscar solicitações");
  }

  return response.data;
}
