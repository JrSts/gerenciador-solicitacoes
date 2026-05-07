package com.jrsts.sgs.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jrsts.sgs.model.Categoria;

@Repository
public class CategoriaRepository {

  private final JdbcTemplate jdbcTemplate;

  public CategoriaRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void salvarCategoria(Categoria categoria) {
    String sql = "INSERT INTO categoria (id, nome) VALUES (?, ?)";
    jdbcTemplate.update(sql, categoria.getId(), categoria.getNome());
  }

  public Categoria buscarCategoriaPorId(UUID id) {

    String sql = "SELECT id, nome FROM categoria WHERE id = ?";

    return jdbcTemplate.queryForObject(
        sql,
        (rs, rowNum) -> new Categoria(
            UUID.fromString(rs.getString("id")),
            rs.getString("nome")),
        id);
  }

  public List<Categoria> buscarCategorias() {

    String sql = "SELECT id, nome FROM categoria";

    return jdbcTemplate.query(
        sql,
        (rs, rowNum) -> new Categoria(
            UUID.fromString(rs.getString("id")),
            rs.getString("nome")));
  }
}
