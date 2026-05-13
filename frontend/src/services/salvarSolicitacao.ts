import { api } from "../lib/api";
import { CriarSolicitacaoDTO } from "./../dtos/CriarSolicitacaoDTO";
import { Solicitacao } from "@/types/Solicitacao";

export async function salvarSolicitacao(dto: CriarSolicitacaoDTO) {
  const response = await api.post<Solicitacao>("/solicitacoes", dto);

  return response.data;
}
