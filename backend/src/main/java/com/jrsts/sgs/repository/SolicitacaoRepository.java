package com.jrsts.sgs.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.jrsts.sgs.enuns.StatusSolicitacao;
import com.jrsts.sgs.model.Solicitacao;

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

}
