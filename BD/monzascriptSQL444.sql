-- MySQL Script generated by MySQL Workbench
-- Mon Nov  4 21:08:43 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`usuario` (
  `idcuenta` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `tipo` TINYINT(1) NOT NULL,
  `Apaterno` VARCHAR(45) NOT NULL,
  `Amaterno` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `tipo_pregunta` CHAR(1) NOT NULL,
  `respuesta` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idcuenta`))
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `mydb`.`Bitacora`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Bitacora` (
  `fecha_dia` DATETIME(6) NOT NULL,
  `cantidad` DECIMAL(20) NOT NULL,
  `movimiento` VARCHAR(2) NOT NULL,
  `cuneta_usuario` VARCHAR(45) NOT NULL,
  CONSTRAINT `cuneta_usuario`
    FOREIGN KEY (`cuneta_usuario`)
    REFERENCES `mydb`.`usuario` (`idcuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`clientes` (
  `idclientes` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `APaterno` VARCHAR(45) NOT NULL,
  `Amaterno` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(55) NOT NULL,
  `telefono` INT NOT NULL,
  PRIMARY KEY (`idclientes`))
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `mydb`.`notas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`notas` (
  `total` DECIMAL(20) NOT NULL,
  `nota` VARCHAR(45) NOT NULL,
  `no_cliente` INT NOT NULL,
  PRIMARY KEY (`nota`),
  CONSTRAINT `no_cliente`
    FOREIGN KEY (`no_cliente`)
    REFERENCES `mydb`.`clientes` (`idclientes`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `mydb`.`abono`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`abono` (
  ` nota_abono` VARCHAR(45) NOT NULL,
  `abonado` DECIMAL(20) NULL,
  `numeracion` INT NOT NULL,
  PRIMARY KEY (` nota_abono`),
  CONSTRAINT `nota_abono`
    FOREIGN KEY (` nota_abono`)
    REFERENCES `mydb`.`notas` (`nota`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `mydb`.`provedores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`provedores` (
  `idprovedores` INT NOT NULL,
  `nombre_empresa` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(45) NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `mercancia` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idprovedores`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`productos` (
  `idmodelo` VARCHAR(50) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `precio` DECIMAL(20) NOT NULL,
  `existencia` INT NOT NULL,
  `clasificacion` VARCHAR(45) NOT NULL,
  `provedor` VARCHAR(45) NOT NULL,
  `imagen` MEDIUMBLOB NOT NULL,
  PRIMARY KEY (`idmodelo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`entregas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`entregas` (
  `fecha_entrega` DATETIME(6) NOT NULL,
  `referencia` VARCHAR(50) NOT NULL,
  `no_nota` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`no_nota`),
  CONSTRAINT `no_nota`
    FOREIGN KEY (`no_nota`)
    REFERENCES `mydb`.`notas` (`nota`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`productos_notas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`productos_notas` (
  `idproductos` VARCHAR(50) NOT NULL,
  `idnotas` INT NOT NULL,
  `cantidad` INT NOT NULL,
  CONSTRAINT `idnotas`
    FOREIGN KEY (`idnotas`)
    REFERENCES `mydb`.`notas` (`nota`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idproductos`
    FOREIGN KEY (`idproductos`)
    REFERENCES `mydb`.`productos` (`idmodelo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;