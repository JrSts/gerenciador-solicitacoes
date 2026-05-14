import { api } from "../lib/api";
import { Categoria } from "@/types/Categoria";

export async function obterCategorias() {
  const categorias = await api.get<Categoria[]>("/categorias");

  return categorias.data;
}
