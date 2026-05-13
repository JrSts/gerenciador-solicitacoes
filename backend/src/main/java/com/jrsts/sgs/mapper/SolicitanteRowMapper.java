package com.jrsts.sgs.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.jrsts.sgs.model.Solicitante;

public class SolicitanteRowMapper implements org.springframework.jdbc.core.RowMapper<Solicitante> {

  @Override
  public Solicitante mapRow(ResultSet rs, int rowNum) throws SQLException {
    Solicitante solicitante = new Solicitante();
    solicitante.setId(UUID.fromString(rs.getString("id")));
    solicitante.setNome(rs.getString("nome"));
    solicitante.setCpfCnpj(rs.getString("cpf_cnpj"));
    return solicitante;
  }
}