import { FiltroSolicitacaoDTO } from "./../dtos/FiltroSolicitacoesDTO";
import { api } from "../lib/api";
import { Solicitacao } from "../types/Solicitacao";

export async function obterSolicitacoes(filtros?: FiltroSolicitacaoDTO) {
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

  const possuiFiltros = params.toString().length > 0;

  const endPoint = possuiFiltros
    ? `/solicitacoes?${params.toString()}`
    : `/solicitacoes`;
  const solicitacoes = await api.get<Solicitacao[]>(endPoint);

  return solicitacoes.data;
}
