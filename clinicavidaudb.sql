

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


CREATE DATABASE IF NOT EXISTS `clinicavidaudb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `clinicavidaudb`;


DROP TABLE IF EXISTS `citasmedicas`;
CREATE TABLE IF NOT EXISTS `citasmedicas` (
  `codigoCita` int NOT NULL AUTO_INCREMENT,
  `codigoPaciente` int NOT NULL,
  `codigoDoctor` int NOT NULL,
  `fechaCita` date NOT NULL,
  `horaCita` time NOT NULL,
  `motivoConsulta` varchar(255) NOT NULL,
  `estadoCita` enum('Programada','Atendida','Cancelada') DEFAULT 'Programada',
  PRIMARY KEY (`codigoCita`),
  KEY `codigoPaciente` (`codigoPaciente`),
  KEY `codigoDoctor` (`codigoDoctor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `doctores`;
CREATE TABLE IF NOT EXISTS `doctores` (
  `codigoDoctor` int NOT NULL AUTO_INCREMENT,
  `nombreCompleto` varchar(100) NOT NULL,
  `especialidad` enum('Medicina General','Psicología','Nutrición','Fisioterapia') NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `correoElectronico` varchar(100) DEFAULT NULL,
  `estadoDoctor` enum('Disponible','No Disponible') DEFAULT 'Disponible',
  PRIMARY KEY (`codigoDoctor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `pacientes`;
CREATE TABLE IF NOT EXISTS `pacientes` (
  `codigoPaciente` int NOT NULL AUTO_INCREMENT,
  `nombreCompleto` varchar(100) NOT NULL,
  `edad` int NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `correoElectronico` varchar(100) DEFAULT NULL,
  `tipoPaciente` enum('Estudiante','Docente','Administrativo','Visitante') NOT NULL,
  `estadoPaciente` enum('Activo','Inactivo') DEFAULT 'Activo',
  PRIMARY KEY (`codigoPaciente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


ALTER TABLE `citasmedicas`
  ADD CONSTRAINT `citasmedicas_ibfk_1` FOREIGN KEY (`codigoPaciente`) REFERENCES `pacientes` (`codigoPaciente`) ON DELETE CASCADE,
  ADD CONSTRAINT `citasmedicas_ibfk_2` FOREIGN KEY (`codigoDoctor`) REFERENCES `doctores` (`codigoDoctor`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
