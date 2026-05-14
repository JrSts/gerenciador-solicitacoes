"use client";

import styles from "./page.module.css";
import ListaDeSolicitacoes from "../components/ListaDeSolicitacoes/page";
import { useState } from "react";
import { FiltroSolicitacaoDTO } from "@/dtos/FiltroSolicitacoesDTO";
import Modal from "@/components/Modal/page";
import { useCategorias } from "@/hooks/useCategorias";
import { useSolicitantes } from "@/hooks/useSolicitantes";
import { useSolicitacoes } from "@/hooks/useSolicitacoes";
import Filtros from "@/components/Filtros/page";

export default function Home() {
  const [filtros, setFiltros] = useState<FiltroSolicitacaoDTO>({});
  const [modalAberto, setModalAberto] = useState(false);

  const categorias = useCategorias();
  const solicitantes = useSolicitantes();

  const { solicitacoes, setSolicitacoes, loading } = useSolicitacoes(filtros);

  return (
    <div className={styles.page}>
      <main className={styles.main}>
        <div id={styles.header}>
          <div>
            <h1>SGS</h1>
            <p>Sistema de Gerenciamento de Soluções</p>
          </div>
          <div id={styles.inputContainer}>
            <Filtros
              filtros={filtros}
              setFiltros={setFiltros}
              categorias={categorias}
            />

            <div id={styles.containerButtons}>
              <button
                disabled={JSON.stringify(filtros) === JSON.stringify({})}
                id={
                  JSON.stringify(filtros) === JSON.stringify({})
                    ? styles.buttonDisabled
                    : styles.button
                }
                type="button"
                onClick={() => setFiltros({})}
              >
                Limpar filtros
              </button>

              <Modal
                aberto={modalAberto}
                fecharModal={() => setModalAberto(false)}
                salvar={(dados) => console.log(dados)}
                categorias={categorias}
                solicitantes={solicitantes}
              />
              <button id={styles.button} onClick={() => setModalAberto(true)}>
                <span id={styles.sinal}>+</span>Nova Solicitação
              </button>
            </div>
          </div>
        </div>
        <div id={styles.container}>
          <hr />
          <div id={styles.tableContainer}>
            <h3>Lista de Solicitações</h3>
            {solicitacoes.length > 0 ? (
              <ListaDeSolicitacoes
                solicitacoes={solicitacoes}
                setSolicitacoes={setSolicitacoes}
              />
            ) : (
              "Ops! Parece que não há solicitações. Se isso for um erro, tente limpar os filtros ou criar uma nova solicitação."
            )}
          </div>
        </div>
      </main>
    </div>
  );
}
