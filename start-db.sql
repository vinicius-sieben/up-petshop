CREATE DATABASE petshop;
USE petshop;

-- Criando a tabela servico no banco de dados e alimentando a mesma com os servicos disponibilizados no petshop. 
CREATE TABLE servico (
	id int NOT NULL PRIMARY KEY,
    descricao varchar(255) NOT NULL,
    valor double NOT NULL
);

-- Inserindo os dados na tabela de serviços.
INSERT INTO servico VALUES
(1, 'Banho', 40.00),
(2, 'Hidratação', 49.00),
(3, 'Tosa na máquina', 70.00),
(4, 'Tosa na tesoura', 50.00),
(5, 'Tosa higiênica', 45.00),
(6, 'Escovação de dentes', 30.00),
(7, 'Limpeza de ouvidos', 20.00),
(8, 'Corte de unhas', 20.00);

SELECT * FROM servico;