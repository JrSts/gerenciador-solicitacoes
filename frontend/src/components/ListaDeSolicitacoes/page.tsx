import SolicitacaoItem from "../SolicitacaoItem/page";
import { Solicitacao } from "../../types/Solicitacao";
import styles from "./listaDeSolicitacoes.module.css";

type Props = {
  solicitacoes: Solicitacao[];
  setSolicitacoes: React.Dispatch<React.SetStateAction<Solicitacao[]>>;
};

export default function ListaDeSolicitacoes({
  solicitacoes,
  setSolicitacoes,
}: Props) {
  return (
    <table id={styles.tabela} border={1}>
      <thead id={styles.tableHead}>
        <tr>
          <th id={styles.cellHeadId}>ID</th>
          <th id={styles.cellHeadId}>Descrição</th>
          <th id={styles.cellHead}>Categoria</th>
          <th id={styles.cellHead}>Solicitante</th>
          <th id={styles.cellHead}>Valor</th>
          <th id={styles.cellHead}>Data</th>
          <th id={styles.cellHead}>Status</th>
        </tr>
      </thead>
      <tbody>
        {solicitacoes.map((item: Solicitacao) => (
          <SolicitacaoItem
            key={item.id}
            solicitacao={item}
            setSolicitacoes={setSolicitacoes}
          />
        ))}
      </tbody>
    </table>
  );
}
