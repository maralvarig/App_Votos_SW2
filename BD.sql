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
-- -----------------------------------------------------
-- Schema publicaciones
-- -----------------------------------------------------
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Localidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Localidad` (
  `idLocalidad` INT NOT NULL AUTO_INCREMENT,
  `Pais` VARCHAR(45) NOT NULL,
  `Comunidad_Autonoma` VARCHAR(45) NOT NULL,
  `Provincia` VARCHAR(45) NOT NULL,
  `Municipio` VARCHAR(45) NOT NULL,
  `Codigo_Postal` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idLocalidad`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Personas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Personas` (
  `idPersona` INT NOT NULL AUTO_INCREMENT,
  `DNI` VARCHAR(9) NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Primer_Apellido` VARCHAR(45) NOT NULL,
  `Segundo_Apellido` VARCHAR(45) NULL,
  `Pasaporte` VARCHAR(45) NULL,
  `idLocalidad` INT NOT NULL,
  PRIMARY KEY (`idPersona`),
  INDEX `fk_Personas_Localidad_idx` (`idLocalidad` ASC) VISIBLE,
  CONSTRAINT `fk_Personas_Localidad`
    FOREIGN KEY (`idLocalidad`)
    REFERENCES `mydb`.`Localidad` (`idLocalidad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Elecciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Elecciones` (
  `idElecciones` INT NOT NULL AUTO_INCREMENT,
  `Fecha` VARCHAR(45) NOT NULL,
  `Tipo` VARCHAR(45) NOT NULL,
  `Localidad_idLocalidad` INT NOT NULL,
  PRIMARY KEY (`idElecciones`, `Localidad_idLocalidad`),
  INDEX `fk_Elecciones_Localidad1_idx` (`Localidad_idLocalidad` ASC) VISIBLE,
  CONSTRAINT `fk_Elecciones_Localidad1`
    FOREIGN KEY (`Localidad_idLocalidad`)
    REFERENCES `mydb`.`Localidad` (`idLocalidad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Voto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Voto` (
  `idVoto` INT NOT NULL AUTO_INCREMENT,
  `Voto` VARCHAR(45) NOT NULL,
  `Localidad_idLocalidad` INT NOT NULL,
  `Elecciones_idElecciones` INT NOT NULL,
  PRIMARY KEY (`idVoto`),
  INDEX `fk_Voto_Localidad1_idx` (`Localidad_idLocalidad` ASC) VISIBLE,
  INDEX `fk_Voto_Elecciones1_idx` (`Elecciones_idElecciones` ASC) VISIBLE,
  CONSTRAINT `fk_Voto_Localidad1`
    FOREIGN KEY (`Localidad_idLocalidad`)
    REFERENCES `mydb`.`Localidad` (`idLocalidad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Voto_Elecciones1`
    FOREIGN KEY (`Elecciones_idElecciones`)
    REFERENCES `mydb`.`Elecciones` (`idElecciones`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Administrador` (
  `idAdministrador` INT NOT NULL,
  `Usuario` VARCHAR(45) NOT NULL,
  `Contrasenya` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAdministrador`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Escrutinio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Escrutinio` (
  `idEscrutinio` INT NOT NULL,
  `Resultados` VARCHAR(200) NULL,
  `Elecciones_idElecciones` INT NOT NULL,
  `Elecciones_Localidad_idLocalidad` INT NOT NULL,
  PRIMARY KEY (`idEscrutinio`, `Elecciones_idElecciones`, `Elecciones_Localidad_idLocalidad`),
  INDEX `fk_Escrutinio_Elecciones1_idx` (`Elecciones_idElecciones` ASC, `Elecciones_Localidad_idLocalidad` ASC) VISIBLE,
  CONSTRAINT `fk_Escrutinio_Elecciones1`
    FOREIGN KEY (`Elecciones_idElecciones` , `Elecciones_Localidad_idLocalidad`)
    REFERENCES `mydb`.`Elecciones` (`idElecciones` , `Localidad_idLocalidad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ConfirmacionVoto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ConfirmacionVoto` (
  `Personas_idPersona` INT NOT NULL,
  `Elecciones_idElecciones` INT NOT NULL,
  PRIMARY KEY (`Personas_idPersona`, `Elecciones_idElecciones`),
  INDEX `fk_Votado_Elecciones1_idx` (`Elecciones_idElecciones` ASC) VISIBLE,
  CONSTRAINT `fk_Votado_Personas1`
    FOREIGN KEY (`Personas_idPersona`)
    REFERENCES `mydb`.`Personas` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Votado_Elecciones1`
    FOREIGN KEY (`Elecciones_idElecciones`)
    REFERENCES `mydb`.`Elecciones` (`idElecciones`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Partidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Partidos` (
  `idPartidos` INT NOT NULL,
  `Nombre` VARCHAR(45) NULL,
  `Elecciones_idElecciones` INT NOT NULL,
  PRIMARY KEY (`idPartidos`),
  INDEX `fk_Partidos_Elecciones1_idx` (`Elecciones_idElecciones` ASC) VISIBLE,
  CONSTRAINT `fk_Partidos_Elecciones1`
    FOREIGN KEY (`Elecciones_idElecciones`)
    REFERENCES `mydb`.`Elecciones` (`idElecciones`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Representantes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Representantes` (
  `idRepresentantes` INT NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Primer_Apellido` VARCHAR(45) NOT NULL,
  `Segundo_Apellido` VARCHAR(45) NULL,
  `Partidos_idPartidos` INT NOT NULL,
  PRIMARY KEY (`idRepresentantes`),
  INDEX `fk_Representantes_Partidos1_idx` (`Partidos_idPartidos` ASC) VISIBLE,
  CONSTRAINT `fk_Representantes_Partidos1`
    FOREIGN KEY (`Partidos_idPartidos`)
    REFERENCES `mydb`.`Partidos` (`idPartidos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
