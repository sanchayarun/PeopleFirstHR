DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
		`id` BIGINT AUTO_INCREMENT,
		`first_name` VARCHAR(255) NOT NULL,
		`last_name` VARCHAR(255) NOT NULL,
		`email` VARCHAR(255) NOT NULL,
		`mobile` VARCHAR(255) NOT NULL,
		PRIMARY KEY (`id`)
	);