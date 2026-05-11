export function formatarData(data: string) {
  return new Date(data).toLocaleDateString("pt-BR");
}

export function formatarMoeda(valor: number) {
  return valor.toLocaleString("pt-BR", {
    style: "currency",
    currency: "BRL",
  });
}
