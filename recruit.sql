DROP DATABASE IF EXISTS `recruit`;
CREATE DATABASE IF NOT EXISTS `recruit` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `recruit`;

-- Table structure for table `application` --
DROP TABLE IF EXISTS `application`;
CREATE TABLE `application` (
`applicationId` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
`applicationState` int DEFAULT NULL,
`recentTime` datetime DEFAULT NULL,
`resumeId` int DEFAULT NULL,
`positionId` int DEFAULT NULL,
`hrId` int DEFAULT NULL,
`reply` varchar(255) DEFAULT NULL
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC COMMENT '';
-- Data of table `application` --
INSERT INTO `application` (`applicationId`, `applicationState`, `recentTime`, `resumeId`, `positionId`, `hrId`, `reply`) VALUES
(15, 0, '2025-01-21 15:47:48', 1, 28, 1, NULL),
(16, 2, '2025-01-21 15:47:48', 1, 30, 4, '很遗憾，您的简历不符合我们公司的要求'),
(17, 0, '2025-01-21 15:47:48', 3, 32, 15, NULL),
(18, 1, '2025-01-21 15:47:48', 3, 42, 21, '明天过来面试'),
(19, 2, '2025-01-21 15:47:48', 3, 42, 21, '不好意思'),
(20, 1, '2025-01-21 15:47:48', 5, 42, 21, '明天过来面试'),
(21, 0, '2025-01-21 15:47:48', 6, 42, 21, NULL),
(22, 0, '2025-01-21 15:47:48', 7, 41, 22, NULL),
(23, 0, '2025-01-21 15:47:48', 1, 41, 22, NULL),
(24, 0, '2025-01-21 15:47:48', 3, 40, 20, NULL),
(25, 1, '2025-01-21 15:47:48', 8, 43, 23, '我看过你的简历，明天过来面试'),
(26, 1, '2025-01-21 15:47:48', 8, 45, 25, '明天早上来公司进行初次面试'),
(27, 2, '2025-01-21 15:47:48', 3, 45, 25, '对不起，看了一下你的简历，并不是很合适'),
(28, 0, '2025-01-21 15:47:48', 11, 46, 24, NULL);

-- Table structure for table `category` --
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
`categoryId` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
`firstCategoryName` varchar(32) DEFAULT NULL,
`description` varchar(64) DEFAULT NULL,
`secondCategoryName` varchar(32) DEFAULT NULL
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC COMMENT '';
-- Data of table `category` --
INSERT INTO `category` (`categoryId`, `firstCategoryName`, `description`, `secondCategoryName`) VALUES
(1, '餐饮服务', '默认', '营业员'),
(2, '餐饮服务', '默认', '预订员'),
(3, '餐饮服务', '默认', '服务员'),
(4, '餐饮服务', '默认', '杂工'),
(5, '餐饮服务', '默认', '吧员'),
(6, '餐饮服务', '默认', '送餐员'),
(7, '餐饮服务', '默认', '收银前台'),
(8, '餐饮服务', '默认', '传菜员'),
(9, '餐饮服务', '默认', '前厅经理'),
(10, '餐饮服务', '默认', '迎宾/接待'),
(11, '餐饮服务', '默认', '保洁'),
(12, '餐饮服务', '默认', '物流采购'),
(13, '餐饮服务', '默认', '领班'),
(14, '餐饮服务', '默认', '洗碗工'),
(15, '餐饮服务', '默认', '餐饮管理'),
(16, '餐饮服务', '默认', '储备店长'),
(17, '餐饮服务', '默认', '主管店长'),
(18, '餐饮服务', '默认', '区域经理'),
(19, '餐饮服务', '默认', '市场企划'),
(20, '餐饮服务', '默认', '品控管理'),
(21, '餐饮服务', '默认', '设备工程'),
(22, '餐饮服务', '默认', '储备干部'),
(23, '餐饮服务', '默认', '管培生'),
(24, '餐饮服务', '默认', '会计'),
(25, '餐饮服务', '默认', '烤鱼厨师'),
(26, '餐饮服务', '默认', '鲁菜厨师'),
(27, '餐饮服务', '默认', '火锅厨师'),
(28, '餐饮服务', '默认', '湘菜厨师'),
(29, '餐饮服务', '默认', '日韩料理厨师'),
(30, '餐饮服务', '默认', '后厨食堂厨师'),
(31, '餐饮服务', '默认', '东北菜厨师'),
(32, '餐饮服务', '默认', '包子工厨师'),
(33, '餐饮服务', '默认', '厨师长'),
(34, '餐饮服务', '默认', '拉面师'),
(35, '餐饮服务', '默认', '川菜厨师'),
(36, '餐饮服务', '默认', '配菜打荷'),
(37, '餐饮服务', '默认', '炒锅'),
(38, '餐饮服务', '默认', '烧烤师傅'),
(39, '餐饮服务', '默认', '学徒'),
(40, '餐饮服务', '默认', '凉菜厨师'),
(41, '餐饮服务', '默认', '西餐厨师'),
(42, '餐饮服务', '默认', '裱花师'),
(43, '餐饮服务', '默认', '咖啡师'),
(44, '餐饮服务', '默认', '西点师'),
(45, '餐饮服务', '默认', '烘焙师'),
(46, '餐饮服务', '默认', '调酒师'),
(47, '餐饮服务', '默认', '茶艺师'),
(48, '餐饮服务', '默认', '面点师'),
(49, '人事/财政/财务', '默认', '行政主管'),
(50, '人事/财政/财务', '默认', '行政经理'),
(51, '人事/财政/财务', '默认', '结算税务'),
(52, '人事/财政/财务', '默认', '财务主管'),
(53, '人事/财政/财务', '默认', '法务'),
(54, '人事/财政/财务', '默认', '法务经理'),
(55, '人事/财政/财务', '默认', '文员'),
(56, '人事/财政/财务', '默认', '前台'),
(57, '人事/财政/财务', '默认', '秘书'),
(58, '人事/财政/财务', '默认', '人事专员'),
(59, '人事/财政/财务', '默认', '人事经理'),
(60, '人事/财政/财务', '默认', '行政助理'),
(61, '人事/财政/财务', '默认', '招聘专员'),
(62, '人事/财政/财务', '默认', '招聘经理'),
(63, '人事/财政/财务', '默认', '培训专员'),
(64, '人事/财政/财务', '默认', '培训经理'),
(65, '人事/财政/财务', '默认', '后勤顾问'),
(66, '人事/财政/财务', '默认', '薪酬绩效'),
(67, '人事/财政/财务', '默认', '员工关系'),
(68, '人事/财政/财务', '默认', '打字员'),
(69, '人事/财政/财务', '默认', '会计'),
(70, '人事/财政/财务', '默认', '总经理'),
(71, '人事/财政/财务', '默认', '助理'),
(72, '人事/财政/财务', '默认', '财务'),
(73, '人事/财政/财务', '默认', '出纳'),
(74, '人事/财政/财务', '默认', '审计成本管理'),
(75, '人事/财政/财务', '默认', 'HRBP'),
(76, '人事/财政/财务', '默认', '人事助理'),
(77, '人事/财政/财务', '默认', '其他'),
(78, '超市/零售', '默认', '促销员'),
(79, '超市/零售', '默认', '营业员'),
(80, '超市/零售', '默认', '收银员'),
(81, '超市/零售', '默认', '理货员'),
(82, '超市/零售', '默认', '防损员'),
(83, '超市/零售', '默认', '店长'),
(84, '超市/零售', '默认', '招商'),
(85, '超市/零售', '默认', '经理'),
(86, '超市/零售', '默认', '品类管理'),
(87, '超市/零售', '默认', '食品加工'),
(88, '超市/零售', '默认', '督导'),
(89, '超市/零售', '默认', '卖场经理'),
(90, '超市/零售', '默认', '导购员'),
(91, '超市/零售', '默认', '收货员'),
(92, '超市/零售', '默认', '陈列员'),
(93, '超市/零售', '默认', '采购员'),
(94, '超市/零售', '默认', '停车管理员'),
(95, '超市/零售', '默认', '会计'),
(96, '超市/零售', '默认', '储备店长'),
(97, '超市/零售', '默认', '管培生'),
(98, '超市/零售', '默认', '其他'),
(99, '互联网/IT', '默认', 'Java开发'),
(100, '互联网/IT', '默认', 'Web前端'),
(101, '互联网/IT', '默认', '运维'),
(102, '互联网/IT', '默认', '测试'),
(103, '互联网/IT', '默认', 'C#开发'),
(104, '互联网/IT', '默认', '人工智能'),
(105, '互联网/IT', '默认', '大数据'),
(106, '互联网/IT', '默认', '游戏开发'),
(107, '互联网/IT', '默认', '小程序'),
(108, '互联网/IT', '默认', '移动App开发'),
(109, '互联网/IT', '默认', '网络推广'),
(110, '互联网/IT', '默认', '新媒体运营'),
(112, '销售/采购', '默认', '销售专员'),
(113, '销售/采购', '默认', '大客户代表'),
(114, '销售/采购', '默认', '代理商销售'),
(115, '销售/采购', '默认', '广告销售'),
(116, '销售/采购', '默认', '营销主管'),
(117, '销售/采购', '默认', '商务总监'),
(118, '销售/采购', '默认', '区域总监'),
(119, '销售/采购', '默认', '城市经理'),
(120, '销售/采购', '默认', '采购经理'),
(121, '销售/采购', '默认', '买手'),
(122, '销售/采购', '默认', '外贸业务员'),
(123, '销售/采购', '默认', '外贸经理'),
(124, '销售/采购', '默认', '外贸专员'),
(125, '销售/采购', '默认', '销售代表'),
(126, '销售/采购', '默认', '电话销售'),
(127, '销售/采购', '默认', '销售助理'),
(128, '销售/采购', '默认', '销售支持'),
(129, '销售/采购', '默认', '汽车销售'),
(130, '销售/采购', '默认', '医药代表'),
(131, '销售/采购', '默认', '器械销售'),
(132, '销售/采购', '默认', '网络销售'),
(133, '销售/采购', '默认', '团购销售'),
(134, '销售/采购', '默认', '渠道专员'),
(135, '销售/采购', '默认', '渠道经理'),
(136, '销售/采购', '默认', '客户经理'),
(137, '销售/采购', '默认', '销售培训师'),
(138, '销售/采购', '默认', '销售分析师'),
(139, '销售/采购', '默认', '导购员'),
(140, '销售/采购', '默认', '化妆品销售'),
(141, '销售/采购', '默认', '服装销售'),
(142, '销售/采购', '默认', '零配件销售'),
(143, '销售/采购', '默认', '采购业务员'),
(144, '销售/采购', '默认', '储备经理'),
(145, '销售/采购', '默认', '销售经理'),
(146, '销售/采购', '默认', '其他'),
(147, '家政/保洁', '默认', '保洁'),
(148, '家政/保洁', '默认', '保姆'),
(149, '家政/保洁', '默认', '保安'),
(150, '家政/保洁', '默认', '月嫂'),
(151, '家政/保洁', '默认', '育婴师'),
(152, '家政/保洁', '默认', '洗衣工'),
(153, '家政/保洁', '默认', '钟点工'),
(154, '家政/保洁', '默认', '送水工'),
(155, '家政/保洁', '默认', '护工'),
(156, '家政/保洁', '默认', '催乳师'),
(157, '家政/保洁', '默认', '生活配送'),
(158, '家政/保洁', '默认', '宠物护理和美容'),
(159, '家政/保洁', '默认', '家电维修'),
(160, '家政/保洁', '默认', '其他'),
(161, '物流/仓储/司机', '默认', '快递员'),
(162, '物流/仓储/司机', '默认', '仓库管理员'),
(163, '物流/仓储/司机', '默认', '搬运工'),
(164, '物流/仓储/司机', '默认', '物流专员'),
(165, '物流/仓储/司机', '默认', '物流经理'),
(166, '物流/仓储/司机', '默认', '调度员'),
(167, '物流/仓储/司机', '默认', '仓库经理'),
(168, '物流/仓储/司机', '默认', '单证员'),
(169, '物流/仓储/司机', '默认', '国际货运分拣员'),
(170, '物流/仓储/司机', '默认', '客运司机'),
(171, '物流/仓储/司机', '默认', '货运司机'),
(172, '物流/仓储/司机', '默认', '驾校教练'),
(173, '物流/仓储/司机', '默认', '配送员'),
(174, '物流/仓储/司机', '默认', '装卸工'),
(175, '物流/仓储/司机', '默认', '叉车工'),
(176, '物流/仓储/司机', '默认', '驾驶员'),
(177, '物流/仓储/司机', '默认', '送货员'),
(178, '物流/仓储/司机', '默认', '其他'),
(179, '运动/健身', '默认', '健身'),
(180, '运动/健身', '默认', '教练瑜伽'),
(181, '运动/健身', '默认', '老师'),
(182, '运动/健身', '默认', '舞蹈师'),
(183, '运动/健身', '默认', '游泳教练'),
(184, '运动/健身', '默认', '台球教练'),
(185, '运动/健身', '默认', '高尔夫助理'),
(186, '运动/健身', '默认', '运动顾问'),
(187, '运动/健身', '默认', '前台'),
(188, '运动/健身', '默认', '店长'),
(189, '运动/健身', '默认', '瘦身顾问'),
(190, '运动/健身', '默认', '高尔夫教练'),
(191, '运动/健身', '默认', '会籍顾问'),
(192, '运动/健身', '默认', '武术教练'),
(193, '运动/健身', '默认', '球类运动教练'),
(194, '运动/健身', '默认', '跆拳道教练'),
(195, '运动/健身', '默认', '轮滑教练'),
(196, '运动/健身', '默认', '其他'),
(198, '互联网/IT', '默认', '其他');

-- Table structure for table `comment` --
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
`commentId` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
`type` int DEFAULT NULL,
`content` varchar(32) DEFAULT NULL,
`releaseTime` datetime DEFAULT NULL,
`userId` int DEFAULT NULL,
`positionId` int DEFAULT NULL
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC COMMENT '';
-- Data of table `comment` --
INSERT INTO `comment` (`commentId`, `type`, `content`, `releaseTime`, `userId`, `positionId`) VALUES
(1, 1, '这是个评论呢', '2025-01-21 15:47:48', 4, 1),
(2, 1, '这是个评论呢', '2025-01-21 15:47:48', 4, 1),
(3, 1, '这是个评论呢', '2025-01-21 15:47:48', 4, 1),
(4, 1, '这是个评论呢', '2025-01-21 15:47:48', 4, 2),
(5, 1, '这是个评论呢', '2025-01-21 15:47:48', 4, 2),
(6, 1, '这是个评论呢', '2025-01-21 15:47:48', 4, 2),
(7, 1, '这是个评论呢', '2025-01-21 15:47:48', 4, 2),
(8, 1, '这是个评论呢', '2025-01-21 15:47:48', 4, 2),
(9, 1, '这是个评论呢', '2025-01-21 15:47:48', 4, 2),
(10, 1, '这是个评论呢', '2025-01-21 15:47:48', 4, 2),
(11, 1, '这是个评论呢', '2025-01-21 15:47:48', 4, 7),
(12, 1, '这是个评论呢', '2025-01-21 15:47:48', 4, 7),
(13, 1, '这是个评论呢', '2025-01-21 15:47:48', 4, 7),
(14, 1, '这是个评论呢', '2025-01-21 15:47:48', 4, 7),
(15, 1, '这是个评论呢', '2025-01-21 15:47:48', 4, 7),
(16, 1, '111', '2025-01-21 15:47:48', 9, 15),
(17, 1, '111', '2025-01-21 15:47:48', 9, 15),
(18, 1, '我的天呢', '2025-01-21 15:47:48', 9, 15),
(19, 1, '测试', '2025-01-21 15:47:48', 9, 15),
(20, 1, '我的', '2025-01-21 15:47:48', 9, 28),
(21, 1, '我说', '2025-01-21 15:47:48', 12, 15),
(22, 1, '测试', '2025-01-21 15:47:48', 13, 32),
(23, 1, '测试', '2025-01-21 15:47:48', 15, 32),
(24, 1, '好公司', '2025-01-21 15:47:48', 17, 29),
(25, 1, '赞同', '2025-01-21 15:47:48', 17, 29),
(26, 1, '测试一下', '2025-01-21 15:47:48', 17, 41),
(27, 1, '111', '2025-01-21 15:47:48', 19, 40),
(28, 1, '测试一下评论', '2025-01-21 15:47:48', 17, 41),
(29, 1, '测试', '2025-01-21 15:47:48', 23, 41),
(30, 1, '111', '2025-01-21 15:47:48', 19, 31),
(31, 1, '11111', '2025-01-21 15:47:48', 24, 42),
(32, 1, '11111', '2025-01-21 15:47:48', 24, 43),
(33, 23, '2222222', '2025-01-21 15:47:48', 17, 29),
(34, 23, '2222222', '2025-01-21 15:47:48', 17, 29),
(35, 1, '试试看呢', '2025-01-21 15:47:48', 17, 41),
(36, 1, '测试第二下', '2025-01-21 15:47:48', 17, 41),
(37, 1, '1111', '2025-01-21 15:47:48', 19, 32),
(38, 1, '评论测试', '2025-01-21 15:47:48', 19, 44),
(39, 1, '1111', '2025-01-21 15:47:48', 24, 44),
(40, 1, '111', '2025-01-21 15:47:48', 17, 53);

-- Table structure for table `education` --
DROP TABLE IF EXISTS `education`;
CREATE TABLE `education` (
`id` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
`name` varchar(255) DEFAULT NULL,
`level` int DEFAULT NULL
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC COMMENT '';
-- Data of table `education` --
INSERT INTO `education` (`id`, `name`, `level`) VALUES
(1, '本科', 11),
(2, '高中', 7),
(5, '初中', 5),
(6, '小学及以下', 3),
(7, '硕士', 25),
(8, '博士及博士后', 30),
(10, '不限', 0);

-- Table structure for table `favor` --
DROP TABLE IF EXISTS `favor`;
CREATE TABLE `favor` (
`favorId` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
`userID` int DEFAULT NULL,
`positionId` int DEFAULT NULL
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC COMMENT '';
-- Data of table `favor` --
INSERT INTO `favor` (`favorId`, `userID`, `positionId`) VALUES
(1, 4, 2),
(10, 9, 15),
(15, 13, 15),
(20, 17, 28),
(22, 19, 42),
(23, 17, 30),
(24, 23, 41),
(27, 24, 45);

-- Table structure for table `friendurl` --
DROP TABLE IF EXISTS `friendurl`;
CREATE TABLE `friendurl` (
`urlId` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
`url` varchar(32) DEFAULT NULL,
`name` varchar(32) DEFAULT NULL
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC COMMENT '';
-- Data of table `friendurl` --
INSERT INTO `friendurl` (`urlId`, `url`, `name`) VALUES
(3, 'http://baidu.com', '百度一下，你就知道'),
(4, 'https://www.sohu.com/', '搜狐网'),
(6, 'https://www.sina.com.cn/', '新浪网'),
(7, 'http://taobao.com', '淘宝网'),
(8, 'https://www.zhihu.com/', '知乎'),
(9, 'https://blog.csdn.net/', 'CSDN网站'),
(17, 'https://www.cnblogs.com/', '博客园');

-- Table structure for table `hr` --
DROP TABLE IF EXISTS `hr`;
CREATE TABLE `hr` (
`hrId` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
`hrMobile` varchar(11) DEFAULT NULL,
`hrPassword` varchar(32) DEFAULT NULL,
`hrName` varchar(24) DEFAULT NULL,
`hrEmail` varchar(32) DEFAULT NULL,
`description` varchar(255) DEFAULT NULL,
`companyName` varchar(255) DEFAULT NULL,
`companyAddress` varchar(255) DEFAULT NULL,
`createTime` date DEFAULT NULL,
`hr` varchar(255) DEFAULT NULL
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC COMMENT '';
-- Data of table `hr` --
INSERT INTO `hr` (`hrId`, `hrMobile`, `hrPassword`, `hrName`, `hrEmail`, `description`, `companyName`, `companyAddress`, `createTime`, `hr`) VALUES
(3, '12345678901', '4QrcOUm6Wau+VuBX8g+IPg==', '张三', '2890706289@qq.com', 'estet', '饿了么', 'test', '2025-01-21', '张三'),
(4, '01234567890', '4QrcOUm6Wau+VuBX8g+IPg==', '我', 'eleba@ele.com', '你饿了么', '饿了么', '地球的某个角落', '2025-01-21', '我'),
(15, '12345678901', '4QrcOUm6Wau+VuBX8g+IPg==', 'qiye', 'qiye@test.com', '测试的公司', 'qiye', '广州市', '2025-01-21', 'qiye'),
(21, '12345678901', '4QrcOUm6Wau+VuBX8g+IPg==', '马化腾', '1005202347@qq.com', '无', '腾讯', '广东省梅州市', '2025-01-21', '马化腾'),
(22, '12345678901', '4QrcOUm6Wau+VuBX8g+IPg==', '测试', 'test@qq.com', '简介', '测试', '测试的地址', '2025-01-21', '测试'),
(24, '1361085641', '4QrcOUm6Wau+VuBX8g+IPg==', '雷军', '123456@qq.com', '小米是一家以手机、智能硬件和IoT平台为核心的互联网公司，以智能手机、智能电视、笔记本等丰富的产品与服务。致力于让全球每个人都能享受科技带来的美好生活', '小米', '广东省广州市', '2025-01-21', '雷军'),
(26, '44312358', '4QrcOUm6Wau+VuBX8g+IPg==', '李秉喆', '325692@163.com', '三星集团(SAMSUNG)是韩国最大的跨国企业集团，三星集团包括众多的国际下属企业，旗下子公司有：三星电子、三星物产、三星人寿保险等，业务涉及电子、金融、机械、化学等众多领域。三星集团成立于1938年，由李秉喆创办。三星集团是家族企业，李氏家族世袭，旗下各个三星产业均为家族产业，并由家族中的其他成员管理。 [1-2]', '三星', '韩国京畿道城南市盆唐区书岘洞263号三星广场大厦', '2025-01-21', '李秉喆'),
(27, '18888888888', '4QrcOUm6Wau+VuBX8g+IPg==', '张经理', '123@163.com', '软件开发', '科技公司', '江苏省苏州市工业园区', '2025-01-21', '张经理'),
(28, NULL, '4QrcOUm6Wau+VuBX8g+IPg==', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- Table structure for table `manager` --
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
`managerId` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
`mobile` varchar(11) DEFAULT NULL,
`password` varchar(32) DEFAULT NULL,
`managerName` varchar(11) DEFAULT NULL,
`email` varchar(32) DEFAULT NULL
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC COMMENT '';
-- Data of table `manager` --
INSERT INTO `manager` (`managerId`, `mobile`, `password`, `managerName`, `email`) VALUES
(1, 'admin', 'admin', 'admin', 'test@test.com');

-- Table structure for table `position` --
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
`positionId` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
`title` varchar(25) DEFAULT NULL,
`requirement` varchar(64) DEFAULT NULL,
`quantity` int DEFAULT NULL,
`workCity` varchar(32) DEFAULT NULL,
`salaryUp` int DEFAULT NULL,
`salaryDown` int DEFAULT NULL,
`releaseDate` datetime DEFAULT NULL,
`statePub` int DEFAULT NULL,
`hits` int DEFAULT NULL,
`categoryId` int DEFAULT NULL,
`hrIdPub` int DEFAULT NULL,
`workProvince` varchar(255) DEFAULT NULL,
`educationId` int DEFAULT NULL
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC COMMENT '';
-- Data of table `position` --
INSERT INTO `position` (`positionId`, `title`, `requirement`, `quantity`, `workCity`, `salaryUp`, `salaryDown`, `releaseDate`, `statePub`, `hits`, `categoryId`, `hrIdPub`, `workProvince`, `educationId`) VALUES
(28, '项目经理', '211/985 graduator', 5, '上海城区', 20005, 2312, NULL, 2, NULL, 1, 1, '上海市', 2),
(29, '店长', '211/985 graduator', 5, '上海城区', 20005, 2312, NULL, 2, NULL, 1, 3, '上海市', 6),
(30, '运营经理', '211/985 graduator', 5, '上海城区', 20005, 2312, NULL, 2, NULL, 1, 4, '上海市', 2),
(31, '技术顾问', '211/985 graduator', 5, '上海城区', 20005, 2312, NULL, 2, NULL, 1, 4, '上海市', 2),
(32, '服务员', '勤奋，吃苦耐劳', 10, '汕头市', 7000, 4000, '2025-01-21 15:47:48', 1, 0, 3, 15, '广东省', 5),
(33, NULL, NULL, 0, NULL, 0, 0, '2025-01-21 15:47:48', 2, 0, 0, 12, NULL, 0),
(34, NULL, NULL, 0, NULL, 0, 0, '2025-01-21 15:47:48', 2, 0, 0, 4, NULL, 0),
(35, NULL, NULL, 0, NULL, 0, 0, '2025-01-21 15:47:48', 2, 0, 0, 4, NULL, 0),
(36, NULL, NULL, 0, NULL, 0, 0, '2025-01-21 15:47:48', 2, 0, 0, 4, NULL, 0),
(37, '前端工程师', '无', 1, '汕头市', 7000, 1000, '2025-01-21 15:47:48', 2, 0, 100, 17, '广东省', 1),
(38, '前端工程师', '无', 1, '汕头市', 6000, 3000, '2025-01-21 15:47:48', 2, 0, 100, 18, '广东省', 1),
(39, '后端工程师', '俩年以上', 1, '梅州市', 6600, 3300, '2025-01-21 15:47:48', 2, 0, 99, 19, '广东省', 1),
(40, 'web前端工程师', '无', 2, '汕头市', 8000, 6000, '2025-01-21 15:47:48', 1, 0, 100, 20, '广东省', 1),
(41, '程序员', '测试来的', 10, '深圳市', 6000, 5000, '2025-01-21 15:47:48', 1, 0, 1, 22, '广东省', 6),
(42, '前端工程师', '无', 2, '汕头市', 6000, 3000, '2025-01-21 15:47:48', 2, 0, 100, 21, '广东省', 1),
(43, '人力资源部经理', '本科毕业，工作经验2年以上', 3, '武汉市', 10000, 8000, '2025-01-21 15:47:48', 1, 0, 49, 23, '湖北省', 1),
(44, '安卓开发', '大学本科，有俩年以上开发经验', 3, '广州市', 8000, 5000, '2025-01-21 15:47:48', 2, 0, 108, 24, '广东省', 1),
(45, '硬件测试工程师', '本科以上学历，俩年以上开发经验', 5, '东莞市', 9000, 6000, '2025-01-21 15:47:48', 2, 0, 102, 25, '广东省', 1),
(46, '硬件测试工程师', '俩年测试经验', 3, '深圳市', 8000, 6000, '2025-01-21 15:47:48', 1, 0, 102, 24, '广东省', 1),
(47, '测试', '要求', 2, '七台河市', 5000, 4000, '2025-01-21 15:47:48', 0, 0, 1, 4, '黑龙江省', 2),
(48, NULL, NULL, 0, NULL, 0, 0, '2025-01-21 15:47:48', 2, 0, 0, 4, NULL, 0),
(49, 'web前端工程师', '俩年以上工作经历', 3, '惠州市', 8000, 5000, '2025-01-21 15:47:48', 0, 0, 100, 21, '广东省', 2),
(50, '安卓开发', '俩年及以上工作经验', 10, '阜阳市', 9000, 6000, '2025-01-21 15:47:48', 0, 0, 101, 26, '安徽省', 1),
(51, '软件工程开发师', '熟悉Java', 5, '苏州市', 10000, 5000, '2025-01-21 15:47:48', 0, 0, 99, 27, '江苏省', 1),
(52, 'Java开发工程师', 'Java开发', 10, '广州市', 20000, 10000, '2025-01-21 15:47:48', 1, 0, 99, 3, '广东省', 1),
(53, '前端开发', '前端开发', 10, '广州市', 20000, 10000, '2025-01-21 15:47:48', 1, 0, 100, 3, '广东省', 1);

-- Table structure for table `reply` --
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
`replyId` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
`commentId` int DEFAULT NULL,
`userId` int DEFAULT NULL,
`replyUserId` int DEFAULT NULL,
`content` varchar(255) DEFAULT NULL,
`replyTime` datetime DEFAULT NULL
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC COMMENT '';
-- Data of table `reply` --
INSERT INTO `reply` (`replyId`, `commentId`, `userId`, `replyUserId`, `content`, `replyTime`) VALUES
(1, 31, 19, 24, '2222', '2025-01-21 15:47:48'),
(2, 29, 17, 23, '2222222', '2025-01-21 15:47:48'),
(3, 29, 17, 23, '试试看', '2025-01-21 15:47:48'),
(4, 29, 17, 17, '继续试试', '2025-01-21 15:47:48'),
(5, 29, 17, 17, '好啊', '2025-01-21 15:47:48'),
(6, 29, 17, 17, '我们想的一样', '2025-01-21 15:47:48'),
(7, 37, 19, 19, '1111', '2025-01-21 15:47:48'),
(8, 38, 24, 19, '你好', '2025-01-21 15:47:48');

-- Table structure for table `resume` --
DROP TABLE IF EXISTS `resume`;
CREATE TABLE `resume` (
`resumeId` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
`ability` varchar(256) DEFAULT NULL,
`workExperience` varchar(256) DEFAULT NULL,
`certificate` varchar(256) DEFAULT NULL,
`jobDesire` varchar(256) DEFAULT NULL,
`userId` int DEFAULT NULL,
`honour` varchar(256) DEFAULT NULL,
`selfAssessment` varchar(256) DEFAULT NULL,
`projectExperience` varchar(256) DEFAULT NULL,
`internshipExperience` varchar(256) DEFAULT NULL
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC COMMENT '';
-- Data of table `resume` --
INSERT INTO `resume` (`resumeId`, `ability`, `workExperience`, `certificate`, `jobDesire`, `userId`, `honour`, `selfAssessment`, `projectExperience`, `internshipExperience`) VALUES
(1, 'JAVA，PYTHON，数据挖掘', '[{"time":["2019-3-1","2019-6-6"],"company":"广州得一公司","job":"运维岗","description":"负责产品的运维部署"}]', 'CET4，计算机二级', '项目助理', 17, '[{"time":"2021-4-5","reword":"学生会长","level":"校级"}]', '具有C,JAVA等语言基础，熟练使用python，熟练的Linux操作，部署LNMP架构的web服务能力，有一定开发能力。', '[{"time":["2021-4-5","2021-4-7"],"project":"目的：建立Galera集群实例，增加实践经验\n过程：yum源配置，带补丁mysql安装配置，iptables配置，集群搭建及测试等\n项目心得：通过实践增进对Galera数据库集群的了解，认识到数据库的高可用性。","job":"实验部署","description":"部署Galera集群，提供mysql数据服务及拓展性，保障数据的安全和可用性。"}]', '2019年春   广州得一公司'),
(2, NULL, NULL, NULL, NULL, 18, NULL, NULL, NULL, NULL),
(3, NULL, NULL, NULL, NULL, 19, NULL, NULL, NULL, NULL),
(4, NULL, NULL, NULL, NULL, 20, NULL, NULL, NULL, NULL),
(5, NULL, NULL, NULL, NULL, 21, NULL, NULL, NULL, NULL),
(6, NULL, NULL, NULL, NULL, 22, NULL, NULL, NULL, NULL),
(7, NULL, NULL, NULL, NULL, 23, NULL, NULL, NULL, NULL),
(8, NULL, NULL, NULL, NULL, 24, NULL, NULL, NULL, NULL),
(9, NULL, NULL, NULL, NULL, 25, NULL, NULL, NULL, NULL),
(10, NULL, NULL, NULL, NULL, 26, NULL, NULL, NULL, NULL),
(11, NULL, NULL, NULL, NULL, 27, NULL, NULL, NULL, NULL),
(12, NULL, NULL, NULL, NULL, 27, NULL, NULL, NULL, NULL),
(13, NULL, NULL, NULL, NULL, 27, NULL, NULL, NULL, NULL),
(14, NULL, NULL, NULL, NULL, 27, NULL, NULL, NULL, NULL),
(15, NULL, NULL, NULL, NULL, 27, NULL, NULL, NULL, NULL),
(16, NULL, NULL, NULL, NULL, 27, NULL, NULL, NULL, NULL),
(17, NULL, NULL, NULL, NULL, 33, NULL, NULL, NULL, NULL);

-- Table structure for table `user` --
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
`userId` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
`user` varchar(25) DEFAULT NULL,
`mobile` varchar(11) DEFAULT NULL,
`password` varchar(25) DEFAULT NULL,
`nickname` varchar(25) DEFAULT NULL,
`email` varchar(32) DEFAULT NULL,
`gender` int DEFAULT NULL,
`birthYear` int DEFAULT NULL,
`graduation` varchar(25) DEFAULT NULL,
`eduDegree` varchar(25) DEFAULT NULL,
`major` varchar(25) DEFAULT NULL,
`graduateYear` int DEFAULT NULL,
`dirDesire` float DEFAULT NULL,
`province` varchar(25) DEFAULT NULL,
`city` varchar(25) DEFAULT NULL,
`avatar` varchar(255) DEFAULT NULL
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC COMMENT '';
-- Data of table `user` --
INSERT INTO `user` (`userId`, `user`, `mobile`, `password`, `nickname`, `email`, `gender`, `birthYear`, `graduation`, `eduDegree`, `major`, `graduateYear`, `dirDesire`, `province`, `city`, `avatar`) VALUES
(17, 'admin', '01234567891', 'ISMvKXpXpadDiUoOSoAfww==', '张三', 'test@qq.com', 0, 1995, '中山大学', '本科', '程序员', 2019, '8000.0', '广东省', '广州', '17'),
(19, 'wangwu', '12345678910', '4QrcOUm6Wau+VuBX8g+IPg==', '李四', '1005202347@163.com', 1, 1998, '韶关学院', '本科', 'web前端工程师', 2019, '5000.0', '广东省', '梅州市', '19'),
(24, 'xiaowu', '665116326', '4QrcOUm6Wau+VuBX8g+IPg==', '小吴', '136752446@163.com', 0, 1999, '广东财经大学', '本科', 'web前端工程师', 2017, '20000.0', '广东', '梅州', '24'),
(27, '1119093673', '18051757735', '4QrcOUm6Wau+VuBX8g+IPg==', '施颂扬', '1119093673@qq.com', 0, 1999, '常熟理工学院', '本科', '程序员', 2021, '5000.0', '江苏', '苏州', '27');
