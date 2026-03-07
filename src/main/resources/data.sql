-- ============================================================
-- Dados iniciais de cadastro
-- Usando INSERT IGNORE para evitar duplicação a cada restart
-- ============================================================

-- 1. Tipos de usuário
INSERT IGNORE INTO tipos_usuario (id, nome) VALUES (1, 'Dono de Restaurante');
INSERT IGNORE INTO tipos_usuario (id, nome) VALUES (2, 'Cliente');

-- 2. Usuários
INSERT IGNORE INTO usuarios (id, nome, email, senha, tipo_usuario_id)
VALUES (1, 'João Silva', 'joao.silva@email.com', 'senha123', 1);

INSERT IGNORE INTO usuarios (id, nome, email, senha, tipo_usuario_id)
VALUES (2, 'Maria Souza', 'maria.souza@email.com', 'senha123', 2);

-- 3. Restaurante (associado ao usuário Dono de Restaurante)
INSERT IGNORE INTO restaurantes (id, nome, tipo_cozinha, endereco, horario_funcionamento, dono_restaurante_id)
VALUES (1, 'Restaurante do João', 'Brasileira', 'Rua das Flores, 123, São Paulo - SP', 'Seg a Sex: 11h às 22h', 1);

-- 4. Itens de cardápio (associados ao restaurante)
INSERT IGNORE INTO itens_cardapio (id, nome, descricao, preco, categoria, restaurante_id)
VALUES (1, 'Coxinha de Frango', 'Coxinha de frango com catupiry empanada e frita', 8.50, 'ENTRADA', 1);

INSERT IGNORE INTO itens_cardapio (id, nome, descricao, preco, categoria, restaurante_id)
VALUES (2, 'Feijoada Completa', 'Feijoada com arroz, couve refogada, farofa e laranja', 45.90, 'PRATO_PRINCIPAL', 1);

INSERT IGNORE INTO itens_cardapio (id, nome, descricao, preco, categoria, restaurante_id)
VALUES (3, 'Pudim de Leite', 'Pudim de leite condensado com calda de caramelo', 15.00, 'SOBREMESA', 1);
