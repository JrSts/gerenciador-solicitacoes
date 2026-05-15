package com.jrsts.sgs.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "solicitante")
public class Solicitante {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String nome;

  @Column(unique = true, name = "cpf_cnpj", nullable = false)
  private String cpfCnpj;

  @OneToMany(mappedBy = "solicitante")
  private List<Solicitacao> solicitacoes;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpfCnpj() {
    return cpfCnpj;
  }

  public void setCpfCnpj(String cpfCnpj) {
    this.cpfCnpj = cpfCnpj;
  }

  public Solicitante(String nome, String cpfCnpj) {
    this.nome = nome;
    this.cpfCnpj = cpfCnpj;
  }

  public Solicitante(UUID id, String nome, String cpfCnpj) {
    this.id = id;
    this.nome = nome;
    this.cpfCnpj = cpfCnpj;
  }

  public Solicitante() {
  }

}
