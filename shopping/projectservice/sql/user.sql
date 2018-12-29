SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL auto_increment,
  `username` varchar(50) NOT NULL ,
  `first_name` varchar(50) NOT NULL ,
  `last_name` varchar(50) NOT NULL ,
  `email` varchar(50) NOT NULL ,
  `avatar_url` varchar(255)  ,
  `encrypted_password` char(32) NOT NULL ,
  `roles` varchar(200)  NOT NULL,
  PRIMARY KEY (`user_id`),
  unique KEY `idx_username`(`username`),
  unique KEY `idx_email`(`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1;
