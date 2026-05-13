"use client";

import styles from "./page.module.css";
import ListaDeSolicitacoes from "../components/ListaDeSolicitacoes/page";
import { useEffect, useState } from "react";
import { obterSolicitacoes } from "../services/obterSolicitacoes";
import { Solicitacao, StatusSolicitacao } from "../types/Solicitacao";
import { obterCategorias } from "@/services/onterCategorias";
import { Categoria } from "@/types/Categoria";
import { statusList } from "@/services/obterProximosStatus";
import { FiltroSolicitacaoDTO } from "@/dtos/FiltroSolicitacoesDTO";
import Modal from "@/components/Modal/page";
import { Solicitante } from "@/types/Solicitante";
import { obterSolicitantes } from "@/services/obterSolicitantes";

export default function Home() {
  const [solicitacoes, setSolicitacoes] = useState<Solicitacao[]>([]);
  const [categorias, setCategorias] = useState<Categoria[]>([]);
  const [filtros, setFiltros] = useState<FiltroSolicitacaoDTO>({});
  const [modalAberto, setModalAberto] = useState(false);
  const [solicitantes, setSolicitantes] = useState<Solicitante[]>([]);

  useEffect(() => {
    async function carregarSolicitacoes() {
      const dados = await obterSolicitacoes(filtros);
      setSolicitacoes(dados);
    }

    async function carregarCategorias() {
      const dados = await obterCategorias();
      setCategorias(dados);
    }

    async function carregarSolicitantes() {
      setSolicitantes(await obterSolicitantes());
    }

    carregarSolicitantes();
    carregarCategorias();
    carregarSolicitacoes();
  }, [filtros]);

  return (
    <div className={styles.page}>
      <main className={styles.main}>
        <div id={styles.header}>
          <div>
            <h1>SGS</h1>
            <p>Sistema de Gerenciamento de Soluções</p>
          </div>
          <div id={styles.inputContainer}>
            <select
              name="Selecione"
              id={styles.select}
              value={filtros?.categoriaId || ""}
              onChange={(e) =>
                setFiltros({ ...filtros, categoriaId: e.target.value })
              }
            >
              <option>Categoria</option>
              {categorias.map((item) => (
                <option value={item.id} key={item.id}>
                  {item.nome}
                </option>
              ))}
            </select>

            <input
              type="date"
              id={styles.select}
              value={filtros.dataInicio || ""}
              onChange={(e) =>
                setFiltros({
                  ...filtros,
                  dataInicio: e.target.value,
                })
              }
            />
            <input
              type="date"
              id={styles.select}
              value={filtros.dataFim || ""}
              onChange={(e) =>
                setFiltros({
                  ...filtros,
                  dataFim: e.target.value,
                })
              }
            />

            <select
              name="Status"
              id={styles.select}
              value={filtros.status || ""}
              onChange={(e) =>
                setFiltros({
                  ...filtros,
                  status: e.target.value,
                })
              }
            >
              <option>Status</option>
              {statusList.map((item) => (
                <option key={item.id}>{item.status}</option>
              ))}
            </select>
            <button
              id={styles.button}
              type="button"
              onClick={() => setFiltros({})}
            >
              Limpar filtros
            </button>
            <div>
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
              "Voce ainda não cadastrou nenhuma solicitação! Clique no botão Nova Solicitação para cadastrar."
            )}
          </div>
        </div>
      </main>
    </div>
  );
}
