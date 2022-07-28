/*
 Navicat Premium Data Transfer

 Source Server         : JavaFinalProject
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : javaproject

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 12/05/2022 13:41:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Address
-- ----------------------------
DROP TABLE IF EXISTS `Address`;
CREATE TABLE `Address` (
  `AddressID` int NOT NULL AUTO_INCREMENT,
  `AddressDetail` varchar(255) NOT NULL,
  `ReceiverName` varchar(255) NOT NULL,
  `ReceiverTel` bigint NOT NULL,
  PRIMARY KEY (`AddressID`),
  UNIQUE KEY `AddressID` (`AddressID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Address
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for CommodityInfo
-- ----------------------------
DROP TABLE IF EXISTS `CommodityInfo`;
CREATE TABLE `CommodityInfo` (
  `comID` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `price` int DEFAULT NULL,
  `userID` int DEFAULT NULL,
  `iconUrl` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `state` int DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteTime` varchar(255) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`comID`) USING BTREE,
  UNIQUE KEY `ComID` (`comID`),
  KEY `commodityinfo_i` (`userID`),
  CONSTRAINT `commodityinfo_i` FOREIGN KEY (`userID`) REFERENCES `UserInfo` (`userID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of CommodityInfo
-- ----------------------------
BEGIN;
INSERT INTO `CommodityInfo` (`comID`, `title`, `description`, `tag`, `price`, `userID`, `iconUrl`, `state`, `createTime`, `deleteTime`, `stock`, `status`) VALUES (1, 'Cat food', 'This is for kitten!', 'pet,food', 20, 3, '20220511-140658-FC72C2AA065D4ACBA7AB8950EB75B464.jpg', 1, '2022-05-07 14:12:20', 'Wed May 11 23:29:22 EDT 2022', 2, 1);
INSERT INTO `CommodityInfo` (`comID`, `title`, `description`, `tag`, `price`, `userID`, `iconUrl`, `state`, `createTime`, `deleteTime`, `stock`, `status`) VALUES (28, 'chips', 'cucumber flavor', '', 7, 6, '20220510-175202-488E065128364AED8684709BE354DD08.webp', 0, '2022-05-10 17:27:26', NULL, 5, 1);
INSERT INTO `CommodityInfo` (`comID`, `title`, `description`, `tag`, `price`, `userID`, `iconUrl`, `state`, `createTime`, `deleteTime`, `stock`, `status`) VALUES (29, 'battery', '#5 batter', '', 3, 5, '20220510-174755-55AB3E2E3DEA42A1A03D333C50CCDA9A.jpg', 0, '2022-05-10 17:41:34', NULL, 1, 1);
INSERT INTO `CommodityInfo` (`comID`, `title`, `description`, `tag`, `price`, `userID`, `iconUrl`, `state`, `createTime`, `deleteTime`, `stock`, `status`) VALUES (30, 'shoes', '1 pair female shoes in fine condition', '', 90, 5, '20220510-174942-5E0101CA4005470AA672AA69903FAED6.jpg', 0, '2022-05-10 17:49:16', NULL, 1, 1);
INSERT INTO `CommodityInfo` (`comID`, `title`, `description`, `tag`, `price`, `userID`, `iconUrl`, `state`, `createTime`, `deleteTime`, `stock`, `status`) VALUES (31, 'cat food', 'for diandian', '', 19, 5, '20220510-204906-A680DE0612494E7B917FF16902D31271.jpg', 0, '2022-05-10 20:48:46', NULL, 1, 1);
INSERT INTO `CommodityInfo` (`comID`, `title`, `description`, `tag`, `price`, `userID`, `iconUrl`, `state`, `createTime`, `deleteTime`, `stock`, `status`) VALUES (32, 'jeans', 'jeans for man', '', 20, 5, '20220510-214050-C0AAB493FC784C2FACEC585B34784D56.jpg', 0, '2022-05-10 21:40:38', NULL, 2, 1);
INSERT INTO `CommodityInfo` (`comID`, `title`, `description`, `tag`, `price`, `userID`, `iconUrl`, `state`, `createTime`, `deleteTime`, `stock`, `status`) VALUES (33, 'Book', 'viola davis\nfinding me', '', 10, 7, '20220510-214908-49A43CB07D9E481EBCC558A67B0EC085.jpg', 0, '2022-05-10 21:49:03', NULL, 1, 1);
INSERT INTO `CommodityInfo` (`comID`, `title`, `description`, `tag`, `price`, `userID`, `iconUrl`, `state`, `createTime`, `deleteTime`, `stock`, `status`) VALUES (51, 'mouthwash', 'from Amazon!', '', 9, 3, '20220511-145713-9527B6C47DAC4E78AC7E5A0FF5F73E62.jpg', 0, '2022-05-11 14:57:03', 'Thu May 12 00:08:32 EDT 2022', 100, 0);
INSERT INTO `CommodityInfo` (`comID`, `title`, `description`, `tag`, `price`, `userID`, `iconUrl`, `state`, `createTime`, `deleteTime`, `stock`, `status`) VALUES (63, 'Shoes', '2', '', 39, 3, '20220511-232958-57C726442A7A4C419746FF8E8024AC60.jpg', 0, '2022-05-11 23:29:47', NULL, 1, 1);
INSERT INTO `CommodityInfo` (`comID`, `title`, `description`, `tag`, `price`, `userID`, `iconUrl`, `state`, `createTime`, `deleteTime`, `stock`, `status`) VALUES (64, 'shoes', '3', '', 15, 3, '20220511-233112-F0CCCC4297AD48E6AD966B30133D55BB.jpg', 0, '2022-05-11 23:31:05', NULL, 1, 1);
INSERT INTO `CommodityInfo` (`comID`, `title`, `description`, `tag`, `price`, `userID`, `iconUrl`, `state`, `createTime`, `deleteTime`, `stock`, `status`) VALUES (65, 'socket', 'from Amazon!', '', 9, 2, '20220511-233138-B9BDCA1EBF2B49BDB0A16A0B55115223.jpg', 0, '2022-05-11 23:31:25', NULL, 3, 1);
INSERT INTO `CommodityInfo` (`comID`, `title`, `description`, `tag`, `price`, `userID`, `iconUrl`, `state`, `createTime`, `deleteTime`, `stock`, `status`) VALUES (66, 'cat toy', 'ACANA First Feast with Free-run chicken, whole herring, turkey, eggs, and quail will give your new little wildcat the nutrition they need to get the best start to their new life.\nSupporting healthy immune systems with essential vitamins & minerals.\nSupporting healthy skin & coat for your majestic feline.\nWith rich inclusions of quality animal ingredients, ACANA cat food uses meat, organs, cartilage, and bone, delivering the nutrients cats need naturally\nOur Fresh Regional Ingredients are supplied by people we know and trust, from regional farms, ranches, and waters, and delivered to our kitchen fresh or raw\nIt\'s really cheap!\nBuy it if you have a cat!', '', 6, 10, '20220512-002940-C09526DA736A4D78943A58D761D6E0DC.jpg', 0, '2022-05-12 00:29:33', '', 10, 1);
INSERT INTO `CommodityInfo` (`comID`, `title`, `description`, `tag`, `price`, `userID`, `iconUrl`, `state`, `createTime`, `deleteTime`, `stock`, `status`) VALUES (68, 'Nintendo Switch', 'What\'s included: Switch OLED Model ; Switch Dock; HDMI Cable; AC Adapter. Now with a larger 7\" 1280 x 720 OLED Touchscreen display, the Switch OLED Model system can provide a more immersive handheld gaming experience than its predecessor. In addition to the larger screen size, the OLED display provides vivid colors and crisp contrast in HD 1280 x 720 resolution. Custom Tegra Processor; 64GB Internal Storage. You can also play it in tabletop mode, thanks to its wide adjustable stand. There is also enhanced audio for both handheld and tabletop play. At home, you can drop the Switch OLED Model into its Switch Dock, which now features an Ethernet RJ45 LAN jack for a reliable wired internet connection. By simply lifting Switch from the dock at any time, the system near-instantly transitions to handheld mode, and the same great gaming experience now travels with you.', '', 375, 11, '20220512-131454-CADA38E97BC6433889277E2B8AE57855.jpg', 0, '2022-05-12 13:10:43', NULL, 2, 1);
INSERT INTO `CommodityInfo` (`comID`, `title`, `description`, `tag`, `price`, `userID`, `iconUrl`, `state`, `createTime`, `deleteTime`, `stock`, `status`) VALUES (69, 'GTX 3090 ti', 'Real Boost Clock: 1920 MHz; Memory Detail: 24576 MB GDDR6X.\nReal-Time RAY TRACING in games for cutting-edge, hyper-realistic graphics.\nVapor Chamber Cooling with Triple Fans + 9 iCX3 thermal sensors offer higher performance cooling and much quieter acoustic noise.\nAll-Metal Die-Cast Backplate, Pre-Installed & Adjustable ARGB\n3 Year Warranty & EVGA\'s Top Notch Technical Support.', '', 1999, 11, '20220512-131649-34AAFF9DCFA34CE78CF4EE740502ECFF.jpg', 0, '2022-05-12 13:15:57', NULL, 1, 1);
INSERT INTO `CommodityInfo` (`comID`, `title`, `description`, `tag`, `price`, `userID`, `iconUrl`, `state`, `createTime`, `deleteTime`, `stock`, `status`) VALUES (70, 'Switch', 'From Amazon', '', 7, 11, '20220512-133520-D296C61EBF274A588389D7CEE2421181.jpg', 0, '2022-05-12 13:35:10', NULL, 10, 1);
COMMIT;

-- ----------------------------
-- Table structure for LikeInfo
-- ----------------------------
DROP TABLE IF EXISTS `LikeInfo`;
CREATE TABLE `LikeInfo` (
  `userID` int NOT NULL,
  `comID` int NOT NULL,
  PRIMARY KEY (`userID`,`comID`) USING BTREE,
  KEY `likeinfo_ibfk_2` (`comID`),
  KEY `UserID` (`userID`) USING BTREE,
  CONSTRAINT `likeinfo_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `UserInfo` (`userID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `likeinfo_ibfk_2` FOREIGN KEY (`comID`) REFERENCES `CommodityInfo` (`comID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of LikeInfo
-- ----------------------------
BEGIN;
INSERT INTO `LikeInfo` (`userID`, `comID`) VALUES (1, 1);
INSERT INTO `LikeInfo` (`userID`, `comID`) VALUES (10, 28);
INSERT INTO `LikeInfo` (`userID`, `comID`) VALUES (11, 28);
INSERT INTO `LikeInfo` (`userID`, `comID`) VALUES (5, 30);
INSERT INTO `LikeInfo` (`userID`, `comID`) VALUES (5, 32);
INSERT INTO `LikeInfo` (`userID`, `comID`) VALUES (3, 33);
INSERT INTO `LikeInfo` (`userID`, `comID`) VALUES (11, 33);
INSERT INTO `LikeInfo` (`userID`, `comID`) VALUES (3, 51);
INSERT INTO `LikeInfo` (`userID`, `comID`) VALUES (11, 63);
INSERT INTO `LikeInfo` (`userID`, `comID`) VALUES (10, 64);
INSERT INTO `LikeInfo` (`userID`, `comID`) VALUES (11, 66);
INSERT INTO `LikeInfo` (`userID`, `comID`) VALUES (11, 70);
COMMIT;

-- ----------------------------
-- Table structure for SearchHistoryInfo
-- ----------------------------
DROP TABLE IF EXISTS `SearchHistoryInfo`;
CREATE TABLE `SearchHistoryInfo` (
  `UserID` int NOT NULL,
  `Content` varchar(255) NOT NULL,
  `CreateTime` timestamp NOT NULL,
  PRIMARY KEY (`UserID`),
  CONSTRAINT `searchhistoryinfo_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `UserInfo` (`userID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of SearchHistoryInfo
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for Student
-- ----------------------------
DROP TABLE IF EXISTS `Student`;
CREATE TABLE `Student` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `sex` tinyint(1) NOT NULL,
  `phone` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Student
-- ----------------------------
BEGIN;
INSERT INTO `Student` (`id`, `name`, `sex`, `phone`) VALUES (1001, 'ybs', 1, '123456');
INSERT INTO `Student` (`id`, `name`, `sex`, `phone`) VALUES (1002, 'zyw', 0, '654321');
INSERT INTO `Student` (`id`, `name`, `sex`, `phone`) VALUES (1003, 'fmq', 1, '678909');
INSERT INTO `Student` (`id`, `name`, `sex`, `phone`) VALUES (1004, 'xh', 0, '876543');
INSERT INTO `Student` (`id`, `name`, `sex`, `phone`) VALUES (1005, 'ykq', 1, '453261');
COMMIT;

-- ----------------------------
-- Table structure for UserAddressInfo
-- ----------------------------
DROP TABLE IF EXISTS `UserAddressInfo`;
CREATE TABLE `UserAddressInfo` (
  `UserID` int NOT NULL,
  `AddressID` int NOT NULL,
  `IsCurrent` tinyint(1) NOT NULL,
  PRIMARY KEY (`UserID`,`AddressID`),
  KEY `useraddressinfo_ibfk_2` (`AddressID`),
  CONSTRAINT `useraddressinfo_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `UserInfo` (`userID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `useraddressinfo_ibfk_2` FOREIGN KEY (`AddressID`) REFERENCES `Address` (`AddressID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of UserAddressInfo
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for UserInfo
-- ----------------------------
DROP TABLE IF EXISTS `UserInfo`;
CREATE TABLE `UserInfo` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`userID`) USING BTREE,
  UNIQUE KEY `UserID` (`userID`,`userName`,`phone`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of UserInfo
-- ----------------------------
BEGIN;
INSERT INTO `UserInfo` (`userID`, `userName`, `password`, `phone`, `email`, `avatar`) VALUES (1, 'Default', 'admin', '0', '0', '20220510-230014-8C5A66D57B1A48CEBF58C9DD23A0C954.jpg');
INSERT INTO `UserInfo` (`userID`, `userName`, `password`, `phone`, `email`, `avatar`) VALUES (2, 'Yawen Zheng', '', '7028881111', 'zyw@gmail.com', '20220511-134633-DF894B38C10C4A44933C87B6096A0260.jpg');
INSERT INTO `UserInfo` (`userID`, `userName`, `password`, `phone`, `email`, `avatar`) VALUES (3, 'Bosong Yu', 'Yubosong2021@', '7028614025', 'ybs981217@gmail.com', '20220510-235817-2481BDFC2F2C45649C5858A4722CB0B4.jpg');
INSERT INTO `UserInfo` (`userID`, `userName`, `password`, `phone`, `email`, `avatar`) VALUES (4, 'GodSandy', 'ybs981217', '7028861234', 'by2159@nyu.edu', '20220511-173815-23187680AB81414CB1C254C6EC372C55.jpeg');
INSERT INTO `UserInfo` (`userID`, `userName`, `password`, `phone`, `email`, `avatar`) VALUES (5, 'Sanchez', '19981217', '7028861233', 'xkc200@nyu.edu', NULL);
INSERT INTO `UserInfo` (`userID`, `userName`, `password`, `phone`, `email`, `avatar`) VALUES (6, 'fmq', 'fmq0083', '7028611993', 'fmq@gmail.com', NULL);
INSERT INTO `UserInfo` (`userID`, `userName`, `password`, `phone`, `email`, `avatar`) VALUES (7, 'Minqi Fang', 'fmq0083', '7028611992', 'fmq001@gmail.com', NULL);
INSERT INTO `UserInfo` (`userID`, `userName`, `password`, `phone`, `email`, `avatar`) VALUES (8, 'Huan Xu', 'xh2021', '7028611231', 'xuhuan@nyu.edu', NULL);
INSERT INTO `UserInfo` (`userID`, `userName`, `password`, `phone`, `email`, `avatar`) VALUES (9, 'wozuiniubi', 'wznb', '7028886111', 'wznb@nb.com', '20220511-160218-3129B5D8C08B4F5AA13CE54B6FD7DBD3.jpeg');
INSERT INTO `UserInfo` (`userID`, `userName`, `password`, `phone`, `email`, `avatar`) VALUES (10, 'Harry Potter', 'magic', '7028908877', 'hp@gmail.com', '20220512-003227-98FBF2CF101140D99F9AF0F0512FA107.jpeg');
INSERT INTO `UserInfo` (`userID`, `userName`, `password`, `phone`, `email`, `avatar`) VALUES (11, 'Hermione Granger', 'Emma', '6087911996', 'hermione@gmail.com', '20220512-130332-AC60ADC763DC4E8CB68EB203DB9CC0B0.jpeg');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
