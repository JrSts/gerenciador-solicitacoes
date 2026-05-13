import { api } from "../lib/api";
import { Solicitante } from "@/types/Solicitante";

export async function obterSolicitantes() {
  const response = await api.get<Solicitante[]>("/solicitantes");

  return response.data;
}
