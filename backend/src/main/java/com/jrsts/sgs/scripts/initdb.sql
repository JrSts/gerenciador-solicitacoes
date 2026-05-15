CREATE TABLE solicitante (
    id UUID PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf_cnpj VARCHAR(20) NOT NULL,

    CONSTRAINT uk_solicitante_cpf_cnpj
        UNIQUE (cpf_cnpj)
);


CREATE TABLE categoria (
    id UUID PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TYPE status_solicitacao_check AS ENUM (
    'SOLICITADO',
    'LIBERADO',
    'APROVADO',
    'REJEITADO',
    'CANCELADO'
);

CREATE TABLE solicitacao (
    id UUID PRIMARY KEY,

    solicitante_id UUID NOT NULL,
    categoria_id UUID NOT NULL,

    descricao TEXT NOT NULL,

    valor DECIMAL(10,2) NOT NULL,

    data_solicitacao DATE NOT NULL,

    status status_solicitacao_check NOT NULL DEFAULT 'SOLICITADO',

    CONSTRAINT fk_solicitacao_solicitante
        FOREIGN KEY (solicitante_id)
        REFERENCES solicitante(id),

    CONSTRAINT fk_solicitacao_categoria
        FOREIGN KEY (categoria_id)
        REFERENCES categoria(id)
);

INSERT INTO solicitante (id, nome, cpf_cnpj) VALUES
('11111111-1111-1111-1111-111111111111', 'João Silva', '11111111111'),
('22222222-2222-2222-2222-222222222222', 'Maria Oliveira', '22222222222'),
('33333333-3333-3333-3333-333333333333', 'Carlos Souza', '33333333333'),
('44444444-4444-4444-4444-444444444444', 'Ana Costa', '44444444444'),
('55555555-5555-5555-5555-555555555555', 'Pedro Santos', '55555555555'),
('66666666-6666-6666-6666-666666666666', 'Fernanda Lima', '66666666666'),
('77777777-7777-7777-7777-777777777777', 'Lucas Almeida', '77777777777'),
('88888888-8888-8888-8888-888888888888', 'Juliana Rocha', '88888888888'),
('99999999-9999-9999-9999-999999999999', 'Ricardo Mendes', '99999999999'),
('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Patricia Gomes', '10101010101');

INSERT INTO categoria (id, nome) VALUES
('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb1', 'Serviços'),
('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb2', 'Material'),
('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb3', 'Transporte'),
('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb4', 'Tecnologia'),
('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb5', 'Alimentação'),
('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb6', 'Manutenção'),
('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb7', 'Marketing'),
('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb8', 'Consultoria'),
('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb9', 'Treinamento'),
('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbb10', 'Logística');


INSERT INTO solicitacao (
    id,
    solicitante_id,
    categoria_id,
    descricao,
    valor,
    data_solicitacao,
    status
) VALUES

(
    'c1111111-1111-1111-1111-111111111111',
    '11111111-1111-1111-1111-111111111111',
    'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb1',
    'Pagamento de serviço de limpeza',
    1500.00,
    '2026-05-01',
    'SOLICITADO'
),

(
    'c2222222-2222-2222-2222-222222222222',
    '22222222-2222-2222-2222-222222222222',
    'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb2',
    'Compra de materiais de escritório',
    850.50,
    '2026-05-02',
    'LIBERADO'
),

(
    'c3333333-3333-3333-3333-333333333333',
    '33333333-3333-3333-3333-333333333333',
    'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb3',
    'Despesa com transporte corporativo',
    420.00,
    '2026-05-03',
    'APROVADO'
),

(
    'c4444444-4444-4444-4444-444444444444',
    '44444444-4444-4444-4444-444444444444',
    'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb4',
    'Aquisição de notebooks',
    7500.00,
    '2026-05-04',
    'REJEITADO'
),

(
    'c5555555-5555-5555-5555-555555555555',
    '55555555-5555-5555-5555-555555555555',
    'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb5',
    'Coffee break para evento',
    1200.00,
    '2026-05-05',
    'CANCELADO'
),

(
    'c6666666-6666-6666-6666-666666666666',
    '66666666-6666-6666-6666-666666666666',
    'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb6',
    'Manutenção de ar-condicionado',
    980.00,
    '2026-05-06',
    'SOLICITADO'
),

(
    'c7777777-7777-7777-7777-777777777777',
    '77777777-7777-7777-7777-777777777777',
    'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb7',
    'Campanha de marketing digital',
    3500.00,
    '2026-05-07',
    'LIBERADO'
),

(
    'c8888888-8888-8888-8888-888888888888',
    '88888888-8888-8888-8888-888888888888',
    'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb8',
    'Consultoria financeira',
    5000.00,
    '2026-05-08',
    'APROVADO'
),

(
    'c9999999-9999-9999-9999-999999999999',
    '99999999-9999-9999-9999-999999999999',
    'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb9',
    'Treinamento de equipe',
    2700.00,
    '2026-05-09',
    'REJEITADO'
),

(
    'cccccccc-cccc-cccc-cccc-cccccccccccc',
    'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
    'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbb10',
    'Serviço de logística',
    4100.00,
    '2026-05-10',
    'SOLICITADO'
);