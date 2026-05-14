import { useEffect, useState } from "react";
import { obterSolicitantes } from "@/services/obterSolicitantes";
import { Solicitante } from "@/types/Solicitante";

export function useSolicitantes() {
  const [solicitantes, setSolicitantes] = useState<Solicitante[]>([]);

  useEffect(() => {
    async function carregar() {
      const dados = await obterSolicitantes();
      setSolicitantes(dados);
    }

    carregar();
  }, []);

  return solicitantes;
}
