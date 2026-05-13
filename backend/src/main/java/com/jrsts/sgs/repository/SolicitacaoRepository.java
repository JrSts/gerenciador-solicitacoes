package com.jrsts.sgs.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jrsts.sgs.dtos.FiltroSolicitacaoDTO;
import com.jrsts.sgs.mapper.SolicitacaoRowMapper;
import com.jrsts.sgs.model.Solicitacao;

@Repository
public class SolicitacaoRepository {

    private final JdbcTemplate jdbcTemplate;

    public SolicitacaoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void salvar(Solicitacao solicitacao) {
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

    public void atualizarStatus(Solicitacao solicitacao) {
        String sql = "UPDATE solicitacao SET status = ? WHERE id = ?";
        jdbcTemplate.update(sql, solicitacao.getStatus().name(), solicitacao.getId());
    }

    public Optional<Solicitacao> buscarPorId(UUID id) {

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

        try {
            Solicitacao solicitacao = jdbcTemplate.queryForObject(
                    sql,
                    new SolicitacaoRowMapper(),
                    id);
            return Optional.ofNullable(solicitacao);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Solicitacao> buscarComFiltro(FiltroSolicitacaoDTO filtro) {

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
                new SolicitacaoRowMapper(),
                params.toArray());
    }

    public List<Solicitacao> listar() {

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
                new SolicitacaoRowMapper());
    }
}
