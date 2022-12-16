/******************************************/
/*   DatabaseName = libsystem   */
/*   TableName = borrows   */
/******************************************/
CREATE TABLE `borrows` (
  `recordId` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `bookId` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `userId` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `dateTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `returned` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`recordId`),
  KEY `borrowBook` (`bookId`),
  KEY `borrowUser` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci AVG_ROW_LENGTH=8192 ROW_FORMAT=DYNAMIC
;
