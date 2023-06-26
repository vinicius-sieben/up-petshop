CREATE DATABASE petshop;
USE petshop;

-- Criando a tabela servico no banco de dados e alimentando a mesma com os servicos disponibilizados no petshop. 
CREATE TABLE servico (
	id INT PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(255) NOT NULL,
    valor DOUBLE NOT NULL
);

-- Inserindo os dados na tabela de serviços.
INSERT INTO servico (descricao, valor) VALUES
('Banho', 40.00),
('Hidratação', 49.00),
('Tosa na máquina', 70.00),
('Tosa na tesoura', 50.00),
('Tosa higiênica', 45.00),
('Escovação de dentes', 30.00),
('Limpeza de ouvidos', 20.00),
('Corte de unhas', 20.00);

SELECT * FROM servico;