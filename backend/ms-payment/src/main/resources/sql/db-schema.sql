DROP DATABASE `system-payment` ;
-- -----------------------------------------------------
-- Schema system-payment
-- -----------------------------------------------------
CREATE DATABASE `system-payment` ;

USE `system-payment` ;

CREATE SCHEMA IF NOT EXISTS `system-payment` DEFAULT CHARACTER SET utf8 ;

-- -----------------------------------------------------
-- Table `bank`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `system-payment`.`bank` ;

CREATE TABLE IF NOT EXISTS `system-payment`.`bank` (
  `bank_id` INT NOT NULL AUTO_INCREMENT,
  `bank_name` VARCHAR(150) NULL,
  `bank_code` VARCHAR(45) NULL,
  `bin_number` VARCHAR(45) NULL,
  PRIMARY KEY (`bank_id`))
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `system-payment`.`ecommerce`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `system-payment`.`ecommerce` ;

CREATE TABLE IF NOT EXISTS `system-payment`.`ecommerce` (
  `ecommerce_id` BIGINT NOT NULL AUTO_INCREMENT,
  `ecommerce_code` VARCHAR(255) NOT NULL,
  `enabled` BIT(1) NULL DEFAULT 1,
  `name` VARCHAR(255) NOT NULL,
  `commission_percentage` INTEGER NOT NULL,
  PRIMARY KEY (`ecommerce_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `system-payment`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `system-payment`.`user` ;

CREATE TABLE IF NOT EXISTS `system-payment`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(16) NOT NULL,
  `email` VARCHAR(255) NULL,
  `password` VARCHAR(32) NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `ecommerce_id` BIGINT NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_Usuario_ecommerce1`
    FOREIGN KEY (`ecommerce_id`)
    REFERENCES `system-payment`.`ecommerce` (`ecommerce_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `fk_Usuario_ecommerce1_idx` ON `system-payment`.`user` (`ecommerce_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `system-payment`.`customer_card`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `system-payment`.`customer_card` ;

CREATE TABLE IF NOT EXISTS `system-payment`.`customer_card` (
  `customer_id` BIGINT NOT NULL AUTO_INCREMENT,
  `account_number` VARCHAR(255) NULL DEFAULT NULL,
  `document_number` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `expiration_date` DATE NULL DEFAULT NULL,
  `full_name` VARCHAR(255) NULL DEFAULT NULL,
  `identification_code` INT NULL DEFAULT NULL,
  `telephone_number` VARCHAR(255) NULL DEFAULT NULL,
  `bank_id` INT NOT NULL,
  PRIMARY KEY (`customer_id`),
  CONSTRAINT `fk_customer_card_bank1`
    FOREIGN KEY (`bank_id`)
    REFERENCES `system-payment`.`bank` (`bank_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `fk_customer_card_bank1_idx` ON `system-payment`.`customer_card` (`bank_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `system-payment`.`payment_transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `system-payment`.`payment_transaction` ;

CREATE TABLE IF NOT EXISTS `system-payment`.`payment_transaction` (
  `payment_id` BIGINT NOT NULL AUTO_INCREMENT,
  `invoice_number` VARCHAR(255) NULL DEFAULT NULL,
  `payment_amount` DECIMAL(19,2) NULL DEFAULT NULL,
  `payment_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `process_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `quota` INT NULL DEFAULT NULL,
  `rejected_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `state_payment` VARCHAR(255) NULL DEFAULT NULL,
  `commission_amount` DECIMAL(19,2) NULL DEFAULT NULL,
  `transaction_number` VARCHAR(255) NULL DEFAULT NULL,
  `update_user` VARCHAR(255) NULL DEFAULT NULL,
  `verification_number` VARCHAR(255) NULL DEFAULT NULL,
  `customer_id` BIGINT NOT NULL,
  `ecommerce_id` BIGINT NOT NULL,
  PRIMARY KEY (`payment_id`),
  CONSTRAINT `FK4h3h7fg3jll24km8y7samarr5`
    FOREIGN KEY (`ecommerce_id`)
    REFERENCES `system-payment`.`ecommerce` (`ecommerce_id`)
    ON DELETE CASCADE,
  CONSTRAINT `FKiw1ungnd9feskyaid8s56egj0`
    FOREIGN KEY (`customer_id`)
    REFERENCES `system-payment`.`customer_card` (`customer_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `FKiw1ungnd9feskyaid8s56egj0` ON `system-payment`.`payment_transaction` (`customer_id` ASC) VISIBLE;

CREATE INDEX `FK4h3h7fg3jll24km8y7samarr5` ON `system-payment`.`payment_transaction` (`ecommerce_id` ASC) VISIBLE;

USE `system-payment`;

INSERT INTO bank (bank_id, bank_name, bank_code, bin_number) VALUES (1,'BCP','1234124','455788');
INSERT INTO ecommerce (ecommerce_id, ecommerce_code, enabled, name, commission_percentage) VALUES (1,'SAGA',1,'SAGA FALABELLA',2);
INSERT INTO customer_card (customer_id, account_number, document_number, email, expiration_date, full_name, identification_code, telephone_number, bank_id) VALUES (1,'4557883133341115','5646899','alvaro92a18@gmail.com','2023-12-01','Alvro Aguinaga',912,NULL,1);
INSERT INTO payment_transaction (payment_id, invoice_number, payment_amount, payment_date, process_date, quota, rejected_date, state_payment, commission_amount, transaction_number, update_user, verification_number, customer_id, ecommerce_id) VALUES (1,'20470929031',459.00,'2020-09-19 02:28:40','2020-09-19 02:28:40',2,'2020-09-19 02:28:40','IN_PROCESS',4.59,'799bc9a8-4493-46b3-b4c0-344daca03bc2','ADMIN','CAE12a',1,1);
