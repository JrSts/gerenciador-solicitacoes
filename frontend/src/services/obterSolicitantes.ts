import { api } from "../lib/api";
import { Solicitante } from "@/types/Solicitante";

export async function obterSolicitantes() {
  const solicitantes = await api.get<Solicitante[]>("/solicitantes");

  return solicitantes.data;
}
