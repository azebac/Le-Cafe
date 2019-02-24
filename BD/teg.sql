CREATE TABLE `Condition` (

`id` int(11) NOT NULL AUTO_INCREMENT,

`description` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`amount` int(11) NOT NULL,

PRIMARY KEY (`id`) 

)

ENGINE = InnoDB

AUTO_INCREMENT = 1

AVG_ROW_LENGTH = 0

DEFAULT CHARACTER SET = utf8

COLLATE = utf8_general_ci

KEY_BLOCK_SIZE = 0

MAX_ROWS = 0

MIN_ROWS = 0

ROW_FORMAT = Dynamic;



CREATE TABLE `condition_dish` (

`fk_condition` int(11) NOT NULL,

`fk_dish` int(11) NOT NULL,

`amount` int(11) NULL DEFAULT NULL,

`id` int NOT NULL AUTO_INCREMENT,

PRIMARY KEY (`id`) ,

INDEX `fk_plato_condicion` (`fk_dish` ASC) USING BTREE

)

ENGINE = InnoDB

AUTO_INCREMENT = 0

AVG_ROW_LENGTH = 0

DEFAULT CHARACTER SET = utf8

COLLATE = utf8_general_ci

KEY_BLOCK_SIZE = 0

MAX_ROWS = 0

MIN_ROWS = 0

ROW_FORMAT = Dynamic;



CREATE TABLE `Bill` (

`id` int(11) NOT NULL AUTO_INCREMENT,

`total` float NULL DEFAULT NULL,

PRIMARY KEY (`id`) 

)

ENGINE = InnoDB

AUTO_INCREMENT = 1

AVG_ROW_LENGTH = 0

DEFAULT CHARACTER SET = utf8

COLLATE = utf8_general_ci

KEY_BLOCK_SIZE = 0

MAX_ROWS = 0

MIN_ROWS = 0

ROW_FORMAT = Dynamic;



CREATE TABLE `bill_dish` (

`fk_dish` int(11) NOT NULL AUTO_INCREMENT,

`fk_bill` int(11) NOT NULL,

`id` int NOT NULL,

PRIMARY KEY (`id`) ,

INDEX `fk_plato` (`fk_dish` ASC) USING BTREE

)

ENGINE = InnoDB

AUTO_INCREMENT = 0

AVG_ROW_LENGTH = 0

DEFAULT CHARACTER SET = utf8

COLLATE = utf8_general_ci

KEY_BLOCK_SIZE = 0

MAX_ROWS = 0

MIN_ROWS = 0

ROW_FORMAT = Dynamic;



CREATE TABLE `applied_discount` (

`fk_user_reward` int(11) NULL DEFAULT NULL,

`fk_bill` int(11) NOT NULL,

`id` int NOT NULL AUTO_INCREMENT,

PRIMARY KEY (`id`) ,

INDEX `fk_usuario_recompensa` (`fk_user_reward` ASC) USING BTREE,

INDEX `fk_cuenta_descuento` (`fk_bill` ASC) USING BTREE

)

ENGINE = InnoDB

AUTO_INCREMENT = 0

AVG_ROW_LENGTH = 0

DEFAULT CHARACTER SET = utf8

COLLATE = utf8_general_ci

KEY_BLOCK_SIZE = 0

MAX_ROWS = 0

MIN_ROWS = 0

ROW_FORMAT = Dynamic;



CREATE TABLE `event_status` (

`name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`id` int(11) NOT NULL AUTO_INCREMENT,

PRIMARY KEY (`id`) 

)

ENGINE = InnoDB

AUTO_INCREMENT = 1

AVG_ROW_LENGTH = 0

DEFAULT CHARACTER SET = utf8

COLLATE = utf8_general_ci

KEY_BLOCK_SIZE = 0

MAX_ROWS = 0

MIN_ROWS = 0

ROW_FORMAT = Dynamic;



CREATE TABLE `dish_status` (

`name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`id` int(11) NOT NULL AUTO_INCREMENT,

PRIMARY KEY (`id`) 

)

ENGINE = InnoDB

AUTO_INCREMENT = 1

AVG_ROW_LENGTH = 0

DEFAULT CHARACTER SET = utf8

COLLATE = utf8_general_ci

KEY_BLOCK_SIZE = 0

MAX_ROWS = 0

MIN_ROWS = 0

ROW_FORMAT = Dynamic;



CREATE TABLE `user_status` (

`name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`id` int(11) NOT NULL AUTO_INCREMENT,

PRIMARY KEY (`id`) 

)

ENGINE = InnoDB

AUTO_INCREMENT = 1

AVG_ROW_LENGTH = 0

DEFAULT CHARACTER SET = utf8

COLLATE = utf8_general_ci

KEY_BLOCK_SIZE = 0

MAX_ROWS = 0

MIN_ROWS = 0

ROW_FORMAT = Dynamic;



CREATE TABLE `Event` (

`id` int(11) NOT NULL AUTO_INCREMENT,

`name` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`challenge` tinyint NULL DEFAULT NULL,

`start_date` date NULL DEFAULT NULL,

`end_date` date NULL DEFAULT NULL,

`fk_status` int(11) NULL DEFAULT NULL,

PRIMARY KEY (`id`) ,

INDEX `fk_estatus_evento` (`fk_status` ASC) USING BTREE

)

ENGINE = InnoDB

AUTO_INCREMENT = 1

AVG_ROW_LENGTH = 0

DEFAULT CHARACTER SET = utf8

COLLATE = utf8_general_ci

KEY_BLOCK_SIZE = 0

MAX_ROWS = 0

MIN_ROWS = 0

ROW_FORMAT = Dynamic;



CREATE TABLE `evento_condicion` (

`fk_evento_condicion` int(11) NOT NULL,

`fk_condicion_evento` int(11) NOT NULL,

PRIMARY KEY (`fk_evento_condicion`, `fk_condicion_evento`) ,

INDEX `fk_condicion_evento` (`fk_condicion_evento` ASC) USING BTREE

)

ENGINE = InnoDB

AUTO_INCREMENT = 0

AVG_ROW_LENGTH = 0

DEFAULT CHARACTER SET = utf8

COLLATE = utf8_general_ci

KEY_BLOCK_SIZE = 0

MAX_ROWS = 0

MIN_ROWS = 0

ROW_FORMAT = Dynamic;



CREATE TABLE `event_reward` (

`fk_event` int(11) NOT NULL,

`fk_reward` int(11) NOT NULL AUTO_INCREMENT,

`id` int NOT NULL,

PRIMARY KEY (`id`) ,

INDEX `fk_recompensa_evento` (`fk_reward` ASC) USING BTREE

)

ENGINE = InnoDB

AUTO_INCREMENT = 0

AVG_ROW_LENGTH = 0

DEFAULT CHARACTER SET = utf8

COLLATE = utf8_general_ci

KEY_BLOCK_SIZE = 0

MAX_ROWS = 0

MIN_ROWS = 0

ROW_FORMAT = Dynamic;



CREATE TABLE `Table` (

`id` int(11) NOT NULL AUTO_INCREMENT,

`active` binary(0) NULL DEFAULT NULL,

`number` int NULL,

PRIMARY KEY (`id`) 

)

ENGINE = InnoDB

AUTO_INCREMENT = 1

AVG_ROW_LENGTH = 0

DEFAULT CHARACTER SET = utf8

COLLATE = utf8_general_ci

KEY_BLOCK_SIZE = 0

MAX_ROWS = 0

MIN_ROWS = 0

ROW_FORMAT = Dynamic;



CREATE TABLE `user_table_bill` (

`fk_user` int(11) NOT NULL,

`fk_table` int(11) NOT NULL,

`fk_bill` int(11) NOT NULL,

`id` int NOT NULL AUTO_INCREMENT,

PRIMARY KEY (`id`) ,

INDEX `fk_cuenta` (`fk_bill` ASC) USING BTREE,

INDEX `fk_mesa` (`fk_table` ASC) USING BTREE

)

ENGINE = InnoDB

AUTO_INCREMENT = 0

AVG_ROW_LENGTH = 0

DEFAULT CHARACTER SET = utf8

COLLATE = utf8_general_ci

KEY_BLOCK_SIZE = 0

MAX_ROWS = 0

MIN_ROWS = 0

ROW_FORMAT = Dynamic;



CREATE TABLE `Dish` (

`id` int(11) NOT NULL AUTO_INCREMENT,

`name` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`price` float NULL DEFAULT NULL,

`description` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`points` int(11) NULL DEFAULT NULL,

`fk_status` int(11) NULL DEFAULT NULL,

PRIMARY KEY (`id`) ,

INDEX `fk_estatus_plato` (`fk_status` ASC) USING BTREE

)

ENGINE = InnoDB

AUTO_INCREMENT = 1

AVG_ROW_LENGTH = 0

DEFAULT CHARACTER SET = utf8

COLLATE = utf8_general_ci

KEY_BLOCK_SIZE = 0

MAX_ROWS = 0

MIN_ROWS = 0

ROW_FORMAT = Dynamic;



CREATE TABLE `Reward` (

`id` int(11) NOT NULL AUTO_INCREMENT,

`points` int(11) NULL DEFAULT NULL,

`discount` float NULL DEFAULT NULL,

`description` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

PRIMARY KEY (`id`) 

)

ENGINE = InnoDB

AUTO_INCREMENT = 1

AVG_ROW_LENGTH = 0

DEFAULT CHARACTER SET = utf8

COLLATE = utf8_general_ci

KEY_BLOCK_SIZE = 0

MAX_ROWS = 0

MIN_ROWS = 0

ROW_FORMAT = Dynamic;



CREATE TABLE `Reservation` (

`id` int(11) NOT NULL AUTO_INCREMENT,

`date` date NOT NULL,

`fk_user` int(11) NOT NULL,

`fk_table` int(11) NOT NULL,

PRIMARY KEY (`id`) ,

INDEX `fk_usuario_reserva` (`fk_user` ASC) USING BTREE,

INDEX `fk_mesa_reserva` (`fk_table` ASC) USING BTREE

)

ENGINE = InnoDB

AUTO_INCREMENT = 1

AVG_ROW_LENGTH = 0

DEFAULT CHARACTER SET = utf8

COLLATE = utf8_general_ci

KEY_BLOCK_SIZE = 0

MAX_ROWS = 0

MIN_ROWS = 0

ROW_FORMAT = Dynamic;



CREATE TABLE `Role` (

`id` int(11) NOT NULL AUTO_INCREMENT,

`name` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

PRIMARY KEY (`id`) 

)

ENGINE = InnoDB

AUTO_INCREMENT = 1

AVG_ROW_LENGTH = 0

DEFAULT CHARACTER SET = utf8

COLLATE = utf8_general_ci

KEY_BLOCK_SIZE = 0

MAX_ROWS = 0

MIN_ROWS = 0

ROW_FORMAT = Dynamic;



CREATE TABLE `User` (

`id` int(11) NOT NULL AUTO_INCREMENT,

`email` varchar(90) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`names` varchar(90) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`lastname` varchar(90) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`birthdate` date NOT NULL,

`points` int(11) NULL DEFAULT NULL,

`fk_status` int(11) NULL DEFAULT NULL,

`fk_gender` int NULL,

PRIMARY KEY (`id`) ,

INDEX `fk_estatus_usuario` (`fk_status` ASC) USING BTREE

)

ENGINE = InnoDB

AUTO_INCREMENT = 1

AVG_ROW_LENGTH = 0

DEFAULT CHARACTER SET = utf8

COLLATE = utf8_general_ci

KEY_BLOCK_SIZE = 0

MAX_ROWS = 0

MIN_ROWS = 0

ROW_FORMAT = Dynamic;



CREATE TABLE `user_reward` (

`fk_user` int(11) NOT NULL,

`fk_reward` int(11) NOT NULL,

`used` binary(0) NULL DEFAULT NULL,

`id` int(11) NOT NULL AUTO_INCREMENT,

PRIMARY KEY (`id`) ,

INDEX `fk_usuairio_recompensa` (`fk_user` ASC) USING BTREE,

INDEX `fk_recompensa_usuario` (`fk_reward` ASC) USING BTREE

)

ENGINE = InnoDB

AUTO_INCREMENT = 1

AVG_ROW_LENGTH = 0

DEFAULT CHARACTER SET = utf8

COLLATE = utf8_general_ci

KEY_BLOCK_SIZE = 0

MAX_ROWS = 0

MIN_ROWS = 0

ROW_FORMAT = Dynamic;



CREATE TABLE `user_role` (

`fk_user` int(11) NOT NULL,

`fk_role` int(11) NOT NULL,

`id` int NOT NULL AUTO_INCREMENT,

PRIMARY KEY (`id`) ,

INDEX `fk_rol_usuario` (`fk_role` ASC) USING BTREE

)

ENGINE = InnoDB

AUTO_INCREMENT = 0

AVG_ROW_LENGTH = 0

DEFAULT CHARACTER SET = utf8

COLLATE = utf8_general_ci

KEY_BLOCK_SIZE = 0

MAX_ROWS = 0

MIN_ROWS = 0

ROW_FORMAT = Dynamic;



CREATE TABLE `Gender` (

`id` int NOT NULL AUTO_INCREMENT,

`description` varchar(0) NULL,

PRIMARY KEY (`id`) 

);





ALTER TABLE `condition_dish` ADD CONSTRAINT `fk_condition` FOREIGN KEY (`fk_condition`) REFERENCES `Condition` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE `condition_dish` ADD CONSTRAINT `fk_dish_condition` FOREIGN KEY (`fk_dish`) REFERENCES `Dish` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE `bill_dish` ADD CONSTRAINT `fk_bill` FOREIGN KEY (`fk_bill`) REFERENCES `Bill` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE `bill_dish` ADD CONSTRAINT `fk_dish` FOREIGN KEY (`fk_dish`) REFERENCES `Dish` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE `applied_discount` ADD CONSTRAINT `fk_bill_discount` FOREIGN KEY (`fk_bill`) REFERENCES `Bill` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE `applied_discount` ADD CONSTRAINT `fk_user_reward_bill` FOREIGN KEY (`fk_user_reward`) REFERENCES `user_reward` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE `Event` ADD CONSTRAINT `fk_status_event` FOREIGN KEY (`fk_status`) REFERENCES `event_status` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE `evento_condicion` ADD CONSTRAINT `fk_condicion_evento` FOREIGN KEY (`fk_condicion_evento`) REFERENCES `Condition` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE `evento_condicion` ADD CONSTRAINT `fk_evento_condicion` FOREIGN KEY (`fk_evento_condicion`) REFERENCES `Event` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE `event_reward` ADD CONSTRAINT `fk_event_reward` FOREIGN KEY (`fk_event`) REFERENCES `Event` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE `event_reward` ADD CONSTRAINT `fk_reward_event` FOREIGN KEY (`fk_reward`) REFERENCES `Reward` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE `user_table_bill` ADD CONSTRAINT `fk_bill_table` FOREIGN KEY (`fk_bill`) REFERENCES `Bill` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE `user_table_bill` ADD CONSTRAINT `fk_table_bill` FOREIGN KEY (`fk_table`) REFERENCES `Table` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE `user_table_bill` ADD CONSTRAINT `fk_user_table_bill` FOREIGN KEY (`fk_user`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE `Dish` ADD CONSTRAINT `fk_status` FOREIGN KEY (`fk_status`) REFERENCES `dish_status` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE `Reservation` ADD CONSTRAINT `fk_mesa_reserva` FOREIGN KEY (`fk_table`) REFERENCES `Table` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE `Reservation` ADD CONSTRAINT `fk_usuario_reserva` FOREIGN KEY (`fk_user`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE `User` ADD CONSTRAINT `fk_status_user` FOREIGN KEY (`fk_status`) REFERENCES `user_status` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE `user_reward` ADD CONSTRAINT `fk_reward_user` FOREIGN KEY (`fk_reward`) REFERENCES `Reward` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE `user_reward` ADD CONSTRAINT `fk_user_reward` FOREIGN KEY (`fk_user`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE `user_role` ADD CONSTRAINT `fk_role_user` FOREIGN KEY (`fk_role`) REFERENCES `Role` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE `user_role` ADD CONSTRAINT `fk_user_role` FOREIGN KEY (`fk_user`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE `User` ADD CONSTRAINT `fk_gender` FOREIGN KEY (`fk_gender`) REFERENCES `Gender` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;



