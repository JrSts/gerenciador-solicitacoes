package com.jrsts.sgs.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jrsts.sgs.dtos.FiltroSolicitacaoDTO;
import com.jrsts.sgs.enuns.StatusSolicitacao;
import com.jrsts.sgs.mapper.SolicitacaoRowMapper;
import com.jrsts.sgs.model.Categoria;
import com.jrsts.sgs.model.Solicitacao;
import com.jrsts.sgs.model.Solicitante;

@Repository
public class SolicitacaoRepository {

  private final JdbcTemplate jdbcTemplate;

  public SolicitacaoRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Solicitacao> buscarSolicitacoesPorCategoria(String nomeCategoria) {

    String sql = """
            SELECT s.id, s.descricao, s.valor, s.data_solicitacao, s.status,
                   s.solicitante_id, s.categoria_id
            FROM solicitacao s
            JOIN categoria c ON s.categoria_id = c.id
            WHERE c.nome = ?
        """;

    return jdbcTemplate.query(sql, (rs, rowNum) -> {
      Solicitacao s = new Solicitacao();
      s.setId(rs.getObject("id", UUID.class));
      s.setDescricao(rs.getString("descricao"));
      s.setValor(rs.getDouble("valor"));
      s.setDataSolicitacao(rs.getDate("data_solicitacao").toLocalDate());
      s.setStatus(StatusSolicitacao.valueOf(rs.getString("status")));

      return s;
    }, nomeCategoria);
  }

  public void salvarSolicitacao(Solicitacao solicitacao) {
    UUID id = UUID.randomUUID();
    String sql = """
            INSERT INTO solicitacao
            (
                id,
                solicitante_id,
                categoria_id,
                descricao,
                valor,
                data_solicitacao,
                status
            )
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

    jdbcTemplate.update(
        sql,
        id,
        solicitacao.getSolicitante().getId(),
        solicitacao.getCategoria().getId(),
        solicitacao.getDescricao(),
        solicitacao.getValor(),
        solicitacao.getDataSolicitacao(),
        solicitacao.getStatus().name());
  }

  public void atualizarStatusSolicitacao(Solicitacao solicitacao) {
    String sql = "UPDATE solicitacao SET status = ? WHERE id = ?";
    jdbcTemplate.update(sql, solicitacao.getStatus().name(), solicitacao.getId());
  }

  public Optional<Solicitacao> buscarSolicitacaoPorId(UUID id) {

    String sql = """
            SELECT
                s.id as solicitacao_id,
                s.descricao,
                s.valor,
                s.data_solicitacao,
                s.status,

                so.id as solicitante_id,
                so.nome as solicitante_nome,
                so.cpf_cnpj,

                c.id as categoria_id,
                c.nome as categoria_nome

            FROM solicitacao s

            INNER JOIN solicitante so
                ON so.id = s.solicitante_id

            INNER JOIN categoria c
                ON c.id = s.categoria_id

            WHERE s.id = ?
        """;

    List<Solicitacao> solicitacoes = jdbcTemplate.query(sql, new SolicitacaoRowMapper(), id);
    return solicitacoes.stream().findFirst();
  }

  public List<Solicitacao> buscarSolicitacoesComFiltro(FiltroSolicitacaoDTO filtro) {

    StringBuilder sql = new StringBuilder("""
            SELECT
                s.id as solicitacao_id,
                s.descricao,
                s.valor,
                s.data_solicitacao,
                s.status,

                so.id as solicitante_id,
                so.nome as solicitante_nome,
                so.cpf_cnpj,

                c.id as categoria_id,
                c.nome as categoria_nome

            FROM solicitacao s

            INNER JOIN solicitante so
                ON so.id = s.solicitante_id

            INNER JOIN categoria c
                ON c.id = s.categoria_id

            WHERE 1 = 1
        """);

    List<Object> params = new ArrayList<>();

    if (filtro.status() != null) {
      sql.append(" AND s.status = ?");
      params.add(filtro.status().name());
    }

    if (filtro.categoriaId() != null) {
      sql.append(" AND s.categoria_id = ?");
      params.add(filtro.categoriaId());
    }

    if (filtro.dataInicio() != null && filtro.dataFim() != null) {
      sql.append(" AND s.data_solicitacao BETWEEN ? AND ?");
      params.add(Date.valueOf(filtro.dataInicio()));
      params.add(Date.valueOf(filtro.dataFim()));
    }

    return jdbcTemplate.query(
        sql.toString(),
        (rs, rowNum) -> {

          Solicitacao s = new Solicitacao();

          s.setId(rs.getObject("solicitacao_id", UUID.class));
          s.setDescricao(rs.getString("descricao"));
          s.setValor(rs.getDouble("valor"));
          s.setDataSolicitacao(
              rs.getDate("data_solicitacao").toLocalDate());

          s.setStatus(
              StatusSolicitacao.valueOf(rs.getString("status")));

          Solicitante so = new Solicitante();
          so.setId(rs.getObject("solicitante_id", UUID.class));
          so.setNome(rs.getString("solicitante_nome"));
          so.setCpfCnpj(rs.getString("cpf_cnpj"));

          Categoria c = new Categoria();
          c.setId(rs.getObject("categoria_id", UUID.class));
          c.setNome(rs.getString("categoria_nome"));

          s.setSolicitante(so);
          s.setCategoria(c);

          return s;
        },
        params.toArray());
  }

  public List<Solicitacao> buscarSolicitacoes() {

    String sql = """
            SELECT
                s.id as solicitacao_id,
                s.descricao,
                s.valor,
                s.data_solicitacao,
                s.status,

                so.id as solicitante_id,
                so.nome as solicitante_nome,
                so.cpf_cnpj,

                c.id as categoria_id,
                c.nome as categoria_nome

            FROM solicitacao s

            INNER JOIN solicitante so
                ON so.id = s.solicitante_id

            INNER JOIN categoria c
                ON c.id = s.categoria_id
        """;

    return jdbcTemplate.query(sql,
        (rs, rowNum) -> {

          Solicitacao s = new Solicitacao();

          s.setId(rs.getObject("solicitacao_id", UUID.class));
          s.setDescricao(rs.getString("descricao"));
          s.setValor(rs.getDouble("valor"));
          s.setDataSolicitacao(
              rs.getDate("data_solicitacao").toLocalDate());

          s.setStatus(
              StatusSolicitacao.valueOf(rs.getString("status")));

          Solicitante so = new Solicitante();
          so.setId(rs.getObject("solicitante_id", UUID.class));
          so.setNome(rs.getString("solicitante_nome"));
          so.setCpfCnpj(rs.getString("cpf_cnpj"));

          Categoria c = new Categoria();
          c.setId(rs.getObject("categoria_id", UUID.class));
          c.setNome(rs.getString("categoria_nome"));

          s.setSolicitante(so);
          s.setCategoria(c);

          return s;
        });
  }
}
