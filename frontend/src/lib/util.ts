export function formatarData(data: string) {
  const oldData = new Date(data);
  oldData.setDate(oldData.getDate() + 1);
  return new Date(oldData).toLocaleDateString("pt-BR");
}

export function formatarMoeda(valor: number) {
  return valor.toLocaleString("pt-BR", {
    style: "currency",
    currency: "BRL",
  });
}
