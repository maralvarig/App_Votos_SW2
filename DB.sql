-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`administrador` (
  `idAdministrador` INT NOT NULL,
  `Usuario` VARCHAR(45) NOT NULL,
  `Contrasenya` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAdministrador`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`localidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`localidad` (
  `idLocalidad` INT NOT NULL AUTO_INCREMENT,
  `Pais` VARCHAR(45) NOT NULL,
  `Comunidad_Autonoma` VARCHAR(45) NOT NULL,
  `Provincia` VARCHAR(45) NOT NULL,
  `Municipio` VARCHAR(45) NOT NULL,
  `Codigo_Postal` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idLocalidad`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`elecciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`elecciones` (
  `idElecciones` INT NOT NULL AUTO_INCREMENT,
  `Fecha` VARCHAR(45) NOT NULL,
  `Tipo` VARCHAR(45) NOT NULL,
  `Localidad_idLocalidad` INT NOT NULL,
  PRIMARY KEY (`idElecciones`, `Localidad_idLocalidad`),
  INDEX `fk_Elecciones_Localidad1_idx` (`Localidad_idLocalidad` ASC) VISIBLE,
  CONSTRAINT `fk_Elecciones_Localidad1`
    FOREIGN KEY (`Localidad_idLocalidad`)
    REFERENCES `mydb`.`localidad` (`idLocalidad`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`personas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`personas` (
  `idPersona` INT NOT NULL AUTO_INCREMENT,
  `DNI` VARCHAR(9) NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Primer_Apellido` VARCHAR(45) NOT NULL,
  `Segundo_Apellido` VARCHAR(45) NULL DEFAULT NULL,
  `Pasaporte` VARCHAR(45) NULL DEFAULT NULL,
  `idLocalidad` INT NOT NULL,
  PRIMARY KEY (`idPersona`),
  INDEX `fk_Personas_Localidad_idx` (`idLocalidad` ASC) VISIBLE,
  CONSTRAINT `fk_Personas_Localidad`
    FOREIGN KEY (`idLocalidad`)
    REFERENCES `mydb`.`localidad` (`idLocalidad`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`confirmacionvoto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`confirmacionvoto` (
  `Personas_idPersona` INT NOT NULL,
  `Elecciones_idElecciones` INT NOT NULL,
  PRIMARY KEY (`Personas_idPersona`, `Elecciones_idElecciones`),
  INDEX `fk_Votado_Elecciones1_idx` (`Elecciones_idElecciones` ASC) VISIBLE,
  CONSTRAINT `fk_Votado_Elecciones1`
    FOREIGN KEY (`Elecciones_idElecciones`)
    REFERENCES `mydb`.`elecciones` (`idElecciones`),
  CONSTRAINT `fk_Votado_Personas1`
    FOREIGN KEY (`Personas_idPersona`)
    REFERENCES `mydb`.`personas` (`idPersona`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`escrutinio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`escrutinio` (
  `idEscrutinio` INT NOT NULL,
  `Resultados` VARCHAR(200) NULL DEFAULT NULL,
  `Elecciones_idElecciones` INT NOT NULL,
  `Elecciones_Localidad_idLocalidad` INT NOT NULL,
  PRIMARY KEY (`idEscrutinio`, `Elecciones_idElecciones`, `Elecciones_Localidad_idLocalidad`),
  INDEX `fk_Escrutinio_Elecciones1_idx` (`Elecciones_idElecciones` ASC, `Elecciones_Localidad_idLocalidad` ASC) VISIBLE,
  CONSTRAINT `fk_Escrutinio_Elecciones1`
    FOREIGN KEY (`Elecciones_idElecciones` , `Elecciones_Localidad_idLocalidad`)
    REFERENCES `mydb`.`elecciones` (`idElecciones` , `Localidad_idLocalidad`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`partidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`partidos` (
  `idPartidos` INT NOT NULL,
  `Nombre` VARCHAR(45) NULL DEFAULT NULL,
  `Elecciones_idElecciones` INT NOT NULL,
  PRIMARY KEY (`idPartidos`),
  INDEX `fk_Partidos_Elecciones1_idx` (`Elecciones_idElecciones` ASC) VISIBLE,
  CONSTRAINT `fk_Partidos_Elecciones1`
    FOREIGN KEY (`Elecciones_idElecciones`)
    REFERENCES `mydb`.`elecciones` (`idElecciones`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`representantes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`representantes` (
  `idRepresentantes` INT NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Primer_Apellido` VARCHAR(45) NOT NULL,
  `Segundo_Apellido` VARCHAR(45) NULL DEFAULT NULL,
  `Partidos_idPartidos` INT NOT NULL,
  `Numero_Lista` INT NULL,
  PRIMARY KEY (`idRepresentantes`),
  INDEX `fk_Representantes_Partidos1_idx` (`Partidos_idPartidos` ASC) VISIBLE,
  CONSTRAINT `fk_Representantes_Partidos1`
    FOREIGN KEY (`Partidos_idPartidos`)
    REFERENCES `mydb`.`partidos` (`idPartidos`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`voto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`voto` (
  `idVoto` INT NOT NULL AUTO_INCREMENT,
  `Voto` VARCHAR(45) NOT NULL,
  `Localidad_idLocalidad` INT NOT NULL,
  `Elecciones_idElecciones` INT NOT NULL,
  PRIMARY KEY (`idVoto`),
  INDEX `fk_Voto_Localidad1_idx` (`Localidad_idLocalidad` ASC) VISIBLE,
  INDEX `fk_Voto_Elecciones1_idx` (`Elecciones_idElecciones` ASC) VISIBLE,
  CONSTRAINT `fk_Voto_Elecciones1`
    FOREIGN KEY (`Elecciones_idElecciones`)
    REFERENCES `mydb`.`elecciones` (`idElecciones`),
  CONSTRAINT `fk_Voto_Localidad1`
    FOREIGN KEY (`Localidad_idLocalidad`)
    REFERENCES `mydb`.`localidad` (`idLocalidad`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
