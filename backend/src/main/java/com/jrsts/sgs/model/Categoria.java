package com.jrsts.sgs.model;

import java.util.UUID;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "categoria")
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String nome;

  @OneToMany(mappedBy = "categoria")
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

  public Categoria(UUID id, String nome) {
    this.id = id;
    this.nome = nome;
  }

  public Categoria(String nome) {
    this.nome = nome;
  }

  public Categoria() {
  }
}
