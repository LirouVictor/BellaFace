INSERT INTO tb_produto (nome, descricao, preco, data_criacao) VALUES
('Hidratante Facial Bella', 'Hidrata profundamente a pele do rosto', 49.90, '2025-08-04'),
('Sabonete Líquido Suave', 'Limpeza delicada para todos os tipos de pele', 19.90, '2025-08-05'),
('Batom Matte Elegance', 'Batom com acabamento matte de longa duração', 29.90, '2025-08-06'),
('Perfume Bella Essence', 'Fragrância floral fresca e sofisticada', 89.90, '2025-08-07'),
('Creme Anti-Idade', 'Reduz linhas de expressão e hidrata a pele', 120.00, '2025-08-08'),
('Shampoo Nutritivo', 'Nutrição intensa para cabelos secos e danificados', 34.90, '2025-08-09'),
('Condicionador Suavizante', 'Cabelos macios e desembaraçados', 32.50, '2025-08-10'),
('Óleo Corporal Relaxante', 'Hidratação com aroma suave de lavanda', 55.00, '2025-08-11'),
('Protetor Solar FPS 50', 'Alta proteção contra raios UVA e UVB', 69.90, '2025-08-12');

INSERT INTO tb_cliente (id, login, senha, nome) VALUES
(1, 'cliente1', 'senha123', 'Cliente Exemplo SA'),
(2, 'cliente2', 'senha456', 'Companhia Teste Ltda')
ON CONFLICT (id) DO NOTHING;