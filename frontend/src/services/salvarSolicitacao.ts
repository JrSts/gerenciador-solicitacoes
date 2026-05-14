import { api } from "../lib/api";
import { CriarSolicitacaoDTO } from "./../dtos/CriarSolicitacaoDTO";
import { Solicitacao } from "@/types/Solicitacao";

export async function salvarSolicitacao(dto: CriarSolicitacaoDTO) {
  const solicitacao = await api.post<Solicitacao>("/solicitacoes", dto);

  return solicitacao.data;
}
