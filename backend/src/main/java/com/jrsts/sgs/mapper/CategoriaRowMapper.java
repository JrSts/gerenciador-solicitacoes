package com.jrsts.sgs.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.jrsts.sgs.model.Categoria;

public class CategoriaRowMapper implements org.springframework.jdbc.core.RowMapper<Categoria> {

  @Override
  public Categoria mapRow(ResultSet rs, int rowNum) throws SQLException {
    Categoria categoria = new Categoria();
    categoria.setId(UUID.fromString(rs.getString("id")));
    categoria.setNome(rs.getString("nome"));
    return categoria;
  }
}
