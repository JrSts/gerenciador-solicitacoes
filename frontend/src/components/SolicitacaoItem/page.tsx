"use client";

import { Solicitacao, StatusSolicitacao } from "../../types/Solicitacao";
import { formatarData, formatarMoeda } from "@/lib/util";
import styles from "./solicitacaoItem.module.css";
import { obterProximosStatus } from "../../services/obterProximosStatus";
import { editarStatus } from "@/services/editarStatus";

type Props = {
  solicitacao: Solicitacao;
  setSolicitacoes: React.Dispatch<React.SetStateAction<Solicitacao[]>>;
};

export default function SolicitacaoItem({
  solicitacao,
  setSolicitacoes,
}: Props) {
  const proximosStatus = obterProximosStatus(solicitacao.status);

  return (
    <tr id={styles.tableRow}>
      <td id={styles.tableData}>{solicitacao.id}</td>
      <td id={styles.tableData}>{solicitacao.descricao}</td>
      <td id={styles.tableData}>{solicitacao.categoria.nome}</td>
      <td id={styles.tableData}>{solicitacao.solicitante.nome}</td>
      <td id={styles.tableData}>{formatarMoeda(solicitacao.valor)}</td>
      <td id={styles.tableData}>{formatarData(solicitacao.dataSolicitacao)}</td>
      <td id={styles.tableData} align="center">
        <select
          id={styles.select}
          value={solicitacao.status}
          onChange={(e) => alterarStatus(e.target.value as StatusSolicitacao)}
        >
          <option value={solicitacao.status}>{solicitacao.status}</option>

          {proximosStatus.map((status) => (
            <option key={status} value={status}>
              {status}
            </option>
          ))}
        </select>
      </td>
    </tr>
  );

  async function alterarStatus(novoStatus: StatusSolicitacao) {
    const solicitacaoAtualizada = await editarStatus(
      solicitacao.id,
      novoStatus,
    );

    setSolicitacoes((estadoAnterior) =>
      estadoAnterior.map((item) =>
        item.id === solicitacao.id ? solicitacaoAtualizada : item,
      ),
    );
  }
}
