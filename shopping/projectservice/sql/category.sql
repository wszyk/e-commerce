SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `categoryid` int(11) NOT NULL auto_increment,
  `categoryname` varchar(50) NOT NULL ,
  `parentid` int(11) NOT NULL ,
  `level` int(11) NOT NULL ,
  PRIMARY KEY (`category_id`),
  unique KEY `idxparentid`(`categoryid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1;
