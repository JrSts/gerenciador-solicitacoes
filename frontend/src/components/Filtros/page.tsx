"use client";

import { Categoria } from "@/types/Categoria";
import { FiltroSolicitacaoDTO } from "@/dtos/FiltroSolicitacoesDTO";
import styles from "./filtros.module.css";
import { statusList } from "@/types/StatusList";

type Props = {
  filtros: FiltroSolicitacaoDTO;
  setFiltros: React.Dispatch<React.SetStateAction<FiltroSolicitacaoDTO>>;
  categorias: Categoria[];
};

export default function Filtros({ filtros, setFiltros, categorias }: Props) {
  function atualizarFiltro(campo: string, valor: string) {
    setFiltros((prev) => ({
      ...prev,
      [campo]: valor,
    }));
  }

  return (
    <>
      <div id={styles.dateInputContainer}>
        <label id={styles.label}>Categoria</label>
        <select
          id={styles.select}
          value={filtros.categoriaId || ""}
          onChange={(e) => atualizarFiltro("categoriaId", e.target.value)}
        >
          <option value="">Categoria</option>

          {categorias.map((categoria) => (
            <option key={categoria.id} value={categoria.id}>
              {categoria.nome}
            </option>
          ))}
        </select>
      </div>
      <div id={styles.dateInputContainer}>
        <label id={styles.label}>Data Inicio</label>
        <input
          id={styles.input}
          type="date"
          value={filtros.dataInicio || ""}
          onChange={(e) => atualizarFiltro("dataInicio", e.target.value)}
        />
      </div>

      <div id={styles.dateInputContainer}>
        <label id={styles.label}>Data Fim</label>
        <input
          id={styles.input}
          type="date"
          value={filtros.dataFim || ""}
          onChange={(e) => atualizarFiltro("dataFim", e.target.value)}
        />
      </div>
      <div id={styles.dateInputContainer}>
        <label id={styles.label}>Status</label>
        <select
          id={styles.select}
          value={filtros.status || ""}
          onChange={(e) => atualizarFiltro("status", e.target.value)}
        >
          <option value="">Status</option>

          {statusList.map((item) => (
            <option key={item.id} value={item.status}>
              {item.status}
            </option>
          ))}
        </select>
      </div>
    </>
  );
}
