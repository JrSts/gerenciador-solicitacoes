"use client";

import { useState } from "react";
import styles from "./modal.module.css";
import { Categoria } from "@/types/Categoria";
import { Solicitante } from "@/types/Solicitante";
import { salvarSolicitacao } from "@/services/salvarSolicitacao";

type Props = {
  aberto: boolean;
  fecharModal: () => void;
  salvar: (dados: any) => void;
  categorias: Categoria[];
  solicitantes: Solicitante[];
};

export default function Modal({
  aberto,
  fecharModal,
  salvar,
  categorias,
  solicitantes,
}: Props) {
  const [descricao, setDescricao] = useState("");
  const [valor, setValor] = useState("");
  const [categoriaId, setCategoriaId] = useState("");
  const [solicitanteId, setSolicitanteId] = useState("");

  if (!aberto) return null;

  function handleSubmit(e: React.FormEvent) {
    e.preventDefault();

    salvar({
      descricao,
      valor: Number(valor),
      categoriaId,
      solicitanteId,
    });

    fecharModal();
  }

  return (
    <div id={styles.overlay}>
      <div id={styles.modal}>
        <div id={styles.header}>
          <h2>Nova Solicitação</h2>

          <button id={styles.closeButton} onClick={fecharModal}>
            X
          </button>
        </div>

        <form onSubmit={handleSubmit} id={styles.form}>
          <div>
            <label>Descrição</label>

            <textarea
              value={descricao}
              onChange={(e) => setDescricao(e.target.value)}
            />
          </div>

          <div>
            <label>Valor</label>

            <input
              type="number"
              value={valor}
              onChange={(e) => setValor(e.target.value)}
            />
          </div>

          <div>
            <label>Categoria</label>

            <select
              value={categoriaId}
              onChange={(e) => setCategoriaId(e.target.value)}
            >
              <option value="">Selecione</option>

              {categorias.map((categoria) => (
                <option key={categoria.id} value={categoria.id}>
                  {categoria.nome}
                </option>
              ))}
            </select>
          </div>

          <div>
            <label>Solicitante</label>

            <select
              value={solicitanteId}
              onChange={(e) => setSolicitanteId(e.target.value)}
            >
              <option value="">Selecione</option>

              {solicitantes.map((solicitante) => (
                <option key={solicitante.id} value={solicitante.id}>
                  {solicitante.nome}
                </option>
              ))}
            </select>
          </div>

          <div id={styles.footer}>
            <button
              id={styles.footerButton}
              type="button"
              onClick={fecharModal}
            >
              Cancelar
            </button>

            <button
              id={styles.footerButton}
              onClick={() =>
                salvarSolicitacao({
                  solicitanteId,
                  categoriaId,
                  descricao,
                  valor: Number(valor),
                })
              }
            >
              Salvar
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
