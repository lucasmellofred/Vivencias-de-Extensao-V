-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 04/05/2024 às 13:37
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
-- Banco de dados: `projeto_vivencias_paroquia`
--

-- --------------------------------------------------------



--
-- Estrutura para tabela `eventos`
--

CREATE TABLE `eventos` (
  `id_evento` int(11) NOT NULL,
  `nome_evento` varchar(255) NOT NULL,
  `endereco` varchar(300) NOT NULL,
  `data_inicial` date NOT NULL,
  `data_final` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Despejando dados para a tabela `eventos`
--

INSERT INTO `eventos` (`id_evento`, `nome_evento`, `endereco`, `data_inicial`, `data_final`) VALUES
(1, 'festa junina', 'rua machado de assis 409', '2018-12-03', '2018-12-08'),
(2, 'batismo', 'catedral de joinville', '2019-09-13', '2019-09-13');

--
-- Estrutura para tabela `itens`
--

CREATE TABLE `itens` (
  `id_item` int(11) NOT NULL,
  `nome_item` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


--
-- Despejando dados para a tabela `itens`
--

INSERT INTO `itens` (`id_item`, `nome_item`) VALUES
(1, 'caneta'),
(2, 'lapis'),
(3, 'garrafa de agua');

-- --------------------------------------------------------

--
-- Estrutura para tabela `organograma`
--

CREATE TABLE IF NOT EXISTS `organograma` (
  `id_organograma` int(11) NOT NULL AUTO_INCREMENT,
  `quantidade_faltante` int(11) NOT NULL,
  `quantidade_pronta` int(11) NOT NULL,
  `id_item_fk` int(11) NOT NULL,
  `id_evento_fk` int(11) NOT NULL,
  PRIMARY KEY (`id_organograma`),
  KEY `id_item_fk` (`id_item_fk`),
  KEY `id_evento_fk` (`id_evento_fk`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Despejando dados para a tabela `organograma`
--

INSERT INTO `organograma` (`id_organograma`, `quantidade_faltante`, `quantidade_pronta`, `id_item_fk`, `id_evento_fk`) VALUES
(1, 10, 8, 1, 1);

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `organograma`
--
ALTER TABLE `organograma`
  ADD CONSTRAINT `organograma_ibfk_1` FOREIGN KEY (`id_item_fk`) REFERENCES `itens` (`id_item`),
  ADD CONSTRAINT `organograma_ibfk_2` FOREIGN KEY (`id_evento_fk`) REFERENCES `eventos` (`id_evento`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

DELIMITER //

CREATE PROCEDURE GetAllEevents()
BEGIN
	SELECT *  FROM eventos;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE GetAllOrganograma()
BEGIN
	SELECT *  FROM organograma;
END //

DELIMITER ;

CREATE PROCEDURE GetAllItens()
BEGIN
	SELECT *  FROM itens;
END //

DELIMITER ;

DELIMITER $$
CREATE PROCEDURE InserirEvento(In InomeEvento Varchar(255), In Iendereco Varchar(255), In IdataFinal DATE, In IdataInicial Date)
BEGIN
	INSERT INTO eventos(nome_evento, endereco, data_final, data_inicial)
    VALUES 
    (InomeEvento,Iendereco,IdataFinal,IdataInicial);
END $$

DELIMITER ;

DELIMITER $$
CREATE PROCEDURE InserirItens(In InomeItem Varchar(255)) BEGIN INSERT INTO itens(nome_item) VALUES (InomeItem); END;
DELIMITER ;

create procedure alterar_os_eventos(IN Inome_evento VARCHAR(255),
 IN Iendereco VARCHAR(255), IN Idata_inicial DATE, IN Idata_final DATE, 
 IN I_id_evento INT) BEGIN UPDATE eventos SET nome_evento = Inome_evento, 
 endereco = Iendereco, data_final = Idata_final, data_inicial = Idata_inicial 
 WHERE id_evento = I_id_evento;
END;

DELIMITER $$;
create procedure alterar_os_organogramas(IN I_id_organograma INT,
 IN I_id_evento_fk INT, IN I_id_item_fk INT, IN Iquantidade_faltante INT, 
 IN Iquantidade_pronta INT) BEGIN UPDATE organograma SET id_evento_fk = I_id_evento_fk, 
 id_item_fk = I_id_item_fk, quantidade_pronta = Iquantidade_pronta, quantidade_faltante = Iquantidade_faltante 
 WHERE id_organograma = I_id_organograma;
END;

DELIMITER &&
  create procedure alterar_os_itens(IN I_id_item INT, IN I_item Varchar(255)) BEGIN UPDATE itens SET nome_item = I_item
  WHERE id_item = I_id_item; 
END;

DELIMITER $$
CREATE PROCEDURE InserirOrganograma(In I_id_evento_fk int, In I_id_item_fk Varchar(255), In Iquantidade_faltante INT, In Iquantidade_pronta INT)
BEGIN
	INSERT INTO organograma(id_evento_fk, id_item_fk, quantidade_faltante, quantidade_pronta)
    VALUES 
    (I_id_evento_fk,I_id_item_fk,Iquantidade_faltante,Iquantidade_pronta);
END $$