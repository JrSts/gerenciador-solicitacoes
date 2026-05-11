package com.jrsts.sgs.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.jrsts.sgs.enuns.StatusSolicitacao;
import com.jrsts.sgs.model.Categoria;
import com.jrsts.sgs.model.Solicitacao;
import com.jrsts.sgs.model.Solicitante;

public class SolicitacaoRowMapper implements org.springframework.jdbc.core.RowMapper<Solicitacao> {

  @Override
  public Solicitacao mapRow(ResultSet rs, int rowNum) throws SQLException {

    Solicitante solicitante = new Solicitante();
    solicitante.setId(UUID.fromString(rs.getString("solicitante_id")));
    solicitante.setNome(rs.getString("solicitante_nome"));
    solicitante.setCpfCnpj(rs.getString("cpf_cnpj"));

    Categoria categoria = new Categoria();
    categoria.setId(UUID.fromString(rs.getString("categoria_id")));
    categoria.setNome(rs.getString("categoria_nome"));

    Solicitacao solicitacao = new Solicitacao();

    solicitacao.setId(UUID.fromString(rs.getString("solicitacao_id")));
    solicitacao.setDescricao(rs.getString("descricao"));
    solicitacao.setValor(rs.getDouble("valor"));
    solicitacao.setDataSolicitacao(
        rs.getDate("data_solicitacao").toLocalDate());

    solicitacao.setStatus(
        StatusSolicitacao.valueOf(rs.getString("status")));

    solicitacao.setSolicitante(solicitante);
    solicitacao.setCategoria(categoria);

    return solicitacao;
  }
}
