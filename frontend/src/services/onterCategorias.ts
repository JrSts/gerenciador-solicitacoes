import { api } from "../lib/api";
import { Categoria } from "@/types/Categoria";

export async function obterCategorias() {
  const response = await api.get<Categoria[]>("/categorias");

  return response.data;
}
