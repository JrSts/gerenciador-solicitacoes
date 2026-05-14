import { useEffect, useState } from "react";
import { obterSolicitacoes } from "@/services/obterSolicitacoes";
import { Solicitacao } from "@/types/Solicitacao";
import { FiltroSolicitacaoDTO } from "@/dtos/FiltroSolicitacoesDTO";

export function useSolicitacoes(filtros: FiltroSolicitacaoDTO) {
  const [solicitacoes, setSolicitacoes] = useState<Solicitacao[]>([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    async function carregar() {
      setLoading(true);

      try {
        const dados = await obterSolicitacoes(filtros);
        setSolicitacoes(dados);
      } finally {
        setLoading(false);
      }
    }

    carregar();
  }, [filtros]);

  return {
    solicitacoes,
    setSolicitacoes,
    loading,
  };
}
