package com.jrsts.sgs.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jrsts.sgs.mapper.CategoriaRowMapper;
import com.jrsts.sgs.model.Categoria;

@Repository
public class CategoriaRepository {

  private final JdbcTemplate jdbcTemplate;

  public CategoriaRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void salvar(Categoria categoria) {
    String sql = "INSERT INTO categoria (id, nome) VALUES (?, ?)";
    jdbcTemplate.update(sql, categoria.getId(), categoria.getNome());
  }

  public Optional<Categoria> buscarPorId(UUID id) {

    String sql = "SELECT id, nome FROM categoria WHERE id = ?";

    try {
      Categoria categoria = jdbcTemplate.queryForObject(sql, new CategoriaRowMapper(), id);
      return Optional.ofNullable(categoria);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  public List<Categoria> listar() {

    String sql = "SELECT id, nome FROM categoria";

    return jdbcTemplate.query(
        sql,
        new CategoriaRowMapper());
  }
}
