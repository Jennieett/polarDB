/******************************************/
/*   DatabaseName = libsystem   */
/*   TableName = users   */
/******************************************/
CREATE TABLE `users` (
  `id` varchar(30) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `type` tinyint(1) DEFAULT NULL COMMENT '管理员：0；学生：1；游客：2',
  `password` varchar(20) DEFAULT NULL,
  `dept` varchar(20) DEFAULT NULL COMMENT '学院',
  `tel` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
;
