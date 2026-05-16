package com.jrsts.sgs.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

import com.jrsts.sgs.enuns.StatusSolicitacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "solicitacao")
public class Solicitacao {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "solicitante_id", nullable = false, foreignKey = @ForeignKey(name = "fk_solicitacao_solicitante"))
  private Solicitante solicitante;

  @ManyToOne
  @JoinColumn(name = "categoria_id", nullable = false, foreignKey = @ForeignKey(name = "fk_solicitacao_categoria"))
  private Categoria categoria;

  @Column(nullable = false)
  private String descricao;

  @Column(nullable = false)
  private double valor;

  @Column(name = "data_solicitacao", nullable = false)
  private LocalDate dataSolicitacao;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private StatusSolicitacao status;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Solicitante getSolicitante() {
    return solicitante;
  }

  public void setSolicitante(Solicitante solicitante) {
    this.solicitante = solicitante;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public double getValor() {
    return valor;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }

  public StatusSolicitacao getStatus() {
    return status;
  }

  public void setStatus(StatusSolicitacao status) {
    this.status = status;
  }

  public LocalDate getDataSolicitacao() {
    return dataSolicitacao;
  }

  public void setDataSolicitacao(LocalDate dataSolicitacao) {
    this.dataSolicitacao = dataSolicitacao;
  }

  public Solicitacao(UUID id, Solicitante solicitante, Categoria categoria, String descricao, double valor) {
    this.id = id;
    this.solicitante = solicitante;
    this.categoria = categoria;
    this.descricao = descricao;
    this.valor = valor;
    this.status = StatusSolicitacao.SOLICITADO;
    this.dataSolicitacao = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
  }

  public Solicitacao() {

  }
}
