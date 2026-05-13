package com.jrsts.sgs.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jrsts.sgs.mapper.SolicitanteRowMapper;
import com.jrsts.sgs.model.Solicitante;

@Repository
public class SolicitanteRepository {

  private final JdbcTemplate jdbcTemplate;

  public SolicitanteRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void salvar(Solicitante solicitante) {
    String sql = "INSERT INTO solicitante (id, nome, cpf_cnpj) VALUES (?, ?, ?)";
    jdbcTemplate.update(sql, solicitante.getId(), solicitante.getNome(), solicitante.getCpfCnpj());
  }

  public Optional<Solicitante> buscarPorId(UUID id) {
    String sql = "SELECT id, nome, cpf_cnpj FROM solicitante WHERE id = ?";

    try {
      Solicitante solicitante = jdbcTemplate.queryForObject(
          sql, new SolicitanteRowMapper(), id);
      return Optional.ofNullable(solicitante);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  public List<Solicitante> listar() {
    String sql = "SELECT id, nome, cpf_cnpj FROM solicitante";

    return jdbcTemplate.query(
        sql, new SolicitanteRowMapper());
  }
}
