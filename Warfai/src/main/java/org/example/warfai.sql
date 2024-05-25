-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 25/05/2024 às 03:14
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `warfai`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `batalhas`
--

CREATE TABLE `batalhas` (
  `cod_batalha` int(11) NOT NULL,
  `turnos` int(11) NOT NULL DEFAULT 1,
  `cod_equipe1` int(11) NOT NULL,
  `cod_equipe2` int(11) NOT NULL,
  `cod_equipe_vitoriosa` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `batalhas`
--

INSERT INTO `batalhas` (`cod_batalha`, `turnos`, `cod_equipe1`, `cod_equipe2`, `cod_equipe_vitoriosa`) VALUES
(2, 1, 11, 0, NULL),
(3, 1, 12, 0, NULL),
(4, 1, 13, 0, NULL);

-- --------------------------------------------------------

--
-- Estrutura para tabela `classe`
--

CREATE TABLE `classe` (
  `cod_classe` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `ataque` int(11) NOT NULL,
  `defesa` int(11) NOT NULL,
  `vida` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `classe`
--

INSERT INTO `classe` (`cod_classe`, `nome`, `ataque`, `defesa`, `vida`) VALUES
(0, 'Guerreiro', 0, 0, 0),
(1, 'Arqueiro', 0, 0, 0),
(2, 'Mago', 0, 0, 0);

-- --------------------------------------------------------

--
-- Estrutura para tabela `equipe`
--

CREATE TABLE `equipe` (
  `cod_equipe` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `vitoria` int(11) NOT NULL DEFAULT 0,
  `derrota` int(11) NOT NULL DEFAULT 0,
  `descricao` text DEFAULT NULL,
  `cod_usuario_humano` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `equipe`
--

INSERT INTO `equipe` (`cod_equipe`, `nome`, `vitoria`, `derrota`, `descricao`, `cod_usuario_humano`) VALUES
(0, 'Felipe', 0, 0, NULL, 6),
(2, 'Batata', 0, 0, NULL, 7),
(3, '�uoj', 0, 0, NULL, 8),
(4, 'Maycon', 0, 0, NULL, 9),
(5, 'ahaha', 0, 0, NULL, 10),
(6, 'a', 0, 0, NULL, 11),
(7, 'Teste1', 0, 0, NULL, 12),
(8, 'FernandoTeam', 0, 0, NULL, 13),
(9, 'FernandoTeam', 0, 0, NULL, 14),
(10, 'FernandoTeam', 0, 0, NULL, 15),
(11, 'FernandoTeam', 0, 0, NULL, 16),
(12, 'Team', 0, 0, NULL, 17),
(13, 'Team', 0, 0, NULL, 18);

-- --------------------------------------------------------

--
-- Estrutura para tabela `player`
--

CREATE TABLE `player` (
  `cod_player` int(11) NOT NULL,
  `nickname` varchar(50) NOT NULL,
  `nivel` int(11) NOT NULL DEFAULT 1,
  `exp` int(11) NOT NULL DEFAULT 0,
  `cod_classe` int(11) NOT NULL,
  `cod_equipe` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `player`
--

INSERT INTO `player` (`cod_player`, `nickname`, `nivel`, `exp`, `cod_classe`, `cod_equipe`) VALUES
(4, 'b', 1, 0, 0, 6),
(5, 'c', 1, 0, 1, 6),
(6, 'd', 1, 0, 2, 6),
(7, 'Test1', 1, 0, 0, 7),
(8, 'Test2', 1, 0, 0, 7),
(9, 'Test3', 1, 0, 0, 7),
(10, 'T1', 1, 0, 0, 8),
(11, 'T2', 1, 0, 0, 8),
(12, 'T3', 1, 0, 0, 8),
(13, 'T1', 1, 0, 0, 9),
(14, 'T2', 1, 0, 0, 9),
(15, 'T3', 1, 0, 0, 9),
(16, 'T1', 1, 0, 0, 10),
(17, 'T2', 1, 0, 0, 10),
(18, 'T3', 1, 0, 0, 10),
(19, 'T1', 1, 0, 0, 11),
(20, 'T2', 1, 0, 0, 11),
(21, 'T3', 1, 0, 0, 11),
(22, 'T1', 1, 0, 0, 12),
(23, 'T2', 1, 0, 1, 12),
(24, 'T1', 1, 0, 0, 13),
(25, 'T2', 1, 0, 2, 13),
(26, 'T3', 1, 0, 2, 13);

-- --------------------------------------------------------

--
-- Estrutura para tabela `turno`
--

CREATE TABLE `turno` (
  `cod_turno` int(11) NOT NULL,
  `cod_batalha` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario_humano`
--

CREATE TABLE `usuario_humano` (
  `cod_usuario` int(11) NOT NULL,
  `nome_usuario` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `usuario_humano`
--

INSERT INTO `usuario_humano` (`cod_usuario`, `nome_usuario`) VALUES
(1, 'anderson'),
(2, 'gabriel'),
(3, 'joao'),
(4, 'pedro'),
(5, 'maria'),
(6, 'Felipe'),
(7, 'Batata'),
(8, '�uoj'),
(9, 'Maycon'),
(10, 'ahaha'),
(11, 'a'),
(12, 'Teste1'),
(13, 'FernandoTeam'),
(14, 'FernandoTeam'),
(15, 'FernandoTeam'),
(16, 'FernandoTeam'),
(17, 'Team'),
(18, 'Team');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `batalhas`
--
ALTER TABLE `batalhas`
  ADD PRIMARY KEY (`cod_batalha`),
  ADD KEY `fk_equipe1` (`cod_equipe1`),
  ADD KEY `fk_equipe2` (`cod_equipe2`),
  ADD KEY `fk_equipe_vitoriosa` (`cod_equipe_vitoriosa`);

--
-- Índices de tabela `classe`
--
ALTER TABLE `classe`
  ADD PRIMARY KEY (`cod_classe`);

--
-- Índices de tabela `equipe`
--
ALTER TABLE `equipe`
  ADD PRIMARY KEY (`cod_equipe`);

--
-- Índices de tabela `player`
--
ALTER TABLE `player`
  ADD PRIMARY KEY (`cod_player`),
  ADD KEY `fk_classe` (`cod_classe`),
  ADD KEY `fk_equipe` (`cod_equipe`);

--
-- Índices de tabela `turno`
--
ALTER TABLE `turno`
  ADD PRIMARY KEY (`cod_turno`),
  ADD KEY `fk_batalha` (`cod_batalha`);

--
-- Índices de tabela `usuario_humano`
--
ALTER TABLE `usuario_humano`
  ADD PRIMARY KEY (`cod_usuario`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `batalhas`
--
ALTER TABLE `batalhas`
  MODIFY `cod_batalha` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `classe`
--
ALTER TABLE `classe`
  MODIFY `cod_classe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de tabela `equipe`
--
ALTER TABLE `equipe`
  MODIFY `cod_equipe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de tabela `player`
--
ALTER TABLE `player`
  MODIFY `cod_player` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de tabela `usuario_humano`
--
ALTER TABLE `usuario_humano`
  MODIFY `cod_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `batalhas`
--
ALTER TABLE `batalhas`
  ADD CONSTRAINT `fk_equipe1` FOREIGN KEY (`cod_equipe1`) REFERENCES `equipe` (`cod_equipe`),
  ADD CONSTRAINT `fk_equipe2` FOREIGN KEY (`cod_equipe2`) REFERENCES `equipe` (`cod_equipe`),
  ADD CONSTRAINT `fk_equipe_vitoriosa` FOREIGN KEY (`cod_equipe_vitoriosa`) REFERENCES `equipe` (`cod_equipe`);

--
-- Restrições para tabelas `player`
--
ALTER TABLE `player`
  ADD CONSTRAINT `fk_classe` FOREIGN KEY (`cod_classe`) REFERENCES `classe` (`cod_classe`),
  ADD CONSTRAINT `fk_equipe` FOREIGN KEY (`cod_equipe`) REFERENCES `equipe` (`cod_equipe`);

--
-- Restrições para tabelas `turno`
--
ALTER TABLE `turno`
  ADD CONSTRAINT `fk_batalha` FOREIGN KEY (`cod_batalha`) REFERENCES `batalhas` (`cod_batalha`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
