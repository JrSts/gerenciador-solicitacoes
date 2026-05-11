import { api } from "../lib/api";
import { Solicitacao } from "../types/Solicitacao";

export async function obterSolicitacoes() {
  const response = await api.get<Solicitacao[]>("/solicitacoes");

  return response.data;
}
