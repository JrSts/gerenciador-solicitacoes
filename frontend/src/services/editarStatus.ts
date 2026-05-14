import { api } from "../lib/api";
import { Solicitacao, StatusSolicitacao } from "@/types/Solicitacao";
import { UUID } from "crypto";

export async function editarStatus(id: UUID, status: StatusSolicitacao) {
  const solicitacaoAtualizada = await api.put<Solicitacao>(
    `/solicitacoes/${id}`,
    {
      status,
    },
  );

  return solicitacaoAtualizada.data;
}
