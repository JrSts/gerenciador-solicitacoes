package com.jrsts.sgs.repository;

import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jrsts.sgs.model.Solicitante;

@Repository
public class SolicitanteRepository {

  private final JdbcTemplate jdbcTemplate;

  public SolicitanteRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void salvarSolicitante(Solicitante solicitante) {
    String sql = "INSERT INTO solicitante (id, nome, cpf_cnpj) VALUES (?, ?, ?)";
    jdbcTemplate.update(sql, solicitante.getId(), solicitante.getNome(), solicitante.getCpfCnpj());
  }

  public Solicitante buscarSolicitantePorId(UUID id) {
    String sql = "SELECT id, nome, cpf_cnpj FROM solicitante WHERE id = ?";

    return jdbcTemplate.queryForObject(
        sql, (rs, rowNum) -> new Solicitante(
            UUID.fromString(rs.getString("id")),
            rs.getString("nome"),
            rs.getString("cpf_cnpj")),
        id);
  }
}
