import { useEffect, useState } from "react";
import { obterCategorias } from "@/services/onterCategorias";
import { Categoria } from "@/types/Categoria";

export function useCategorias() {
  const [categorias, setCategorias] = useState<Categoria[]>([]);

  useEffect(() => {
    async function carregar() {
      const dados = await obterCategorias();
      setCategorias(dados);
    }

    carregar();
  }, []);

  return categorias;
}
