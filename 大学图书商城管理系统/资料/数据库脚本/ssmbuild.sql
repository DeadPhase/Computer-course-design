/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50721
Source Host           : 127.0.0.1:3306
Source Database       : ssmbuild

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2021-11-27 20:10:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `ahId` varchar(50) NOT NULL COMMENT '权限表主键',
  `authName` varchar(30) NOT NULL COMMENT '权限名称',
  PRIMARY KEY (`ahId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES ('AH001', 'shop:buybook');
INSERT INTO `authority` VALUES ('AH002', 'shop:buybook');
INSERT INTO `authority` VALUES ('AH003', 'checkInfo');
INSERT INTO `authority` VALUES ('AH004', 'customer:info');
INSERT INTO `authority` VALUES ('AH005', 'customer:paylog');
INSERT INTO `authority` VALUES ('AH006', 'cusomer:toUpdatePwd');
INSERT INTO `authority` VALUES ('AH007', 'shop:buylog');
INSERT INTO `authority` VALUES ('AH008', 'shop:goodsList');
INSERT INTO `authority` VALUES ('AH009', 'customer:wallet');
INSERT INTO `authority` VALUES ('AH010', 'shop:shopList');
INSERT INTO `authority` VALUES ('AH011', 'customer:torecharge');
INSERT INTO `authority` VALUES ('AH012', 'customer:address');
INSERT INTO `authority` VALUES ('AH013', 'customer:toAddAddress');
INSERT INTO `authority` VALUES ('AH014', 'shop:status');

-- ----------------------------
-- Table structure for bkb
-- ----------------------------
DROP TABLE IF EXISTS `bkb`;
CREATE TABLE `bkb` (
  `bbId` varchar(50) NOT NULL COMMENT '联合主键',
  `bkId` varchar(50) NOT NULL COMMENT '书籍种类主键',
  `bookId` varchar(50) NOT NULL COMMENT '书籍主键',
  PRIMARY KEY (`bbId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='书籍种类表';

-- ----------------------------
-- Records of bkb
-- ----------------------------
INSERT INTO `bkb` VALUES ('016f2b29-12bd-4908-8545-8df19ef0479d', 'BK002', '8d4d1396-3793-4cd1-a538-353a0c787491');
INSERT INTO `bkb` VALUES ('040a1a10-9a87-42cc-93e5-da31a9b351d9', 'BK002', 'dff1004a-da5d-4a83-82c1-8a40a83e7cd7');
INSERT INTO `bkb` VALUES ('1fe789e5-86a5-4124-a35b-5bc8fe10f57d', 'BK002', '4cb7a466-46d7-42d3-9236-02bffecdbeb5');
INSERT INTO `bkb` VALUES ('32badc1f-96dc-4a9b-8f10-b4c36cfaff81', 'BK003', 'af043521-c37b-42b8-b9d4-da7a17a6d15e');
INSERT INTO `bkb` VALUES ('419ac9c8-c168-45c5-af82-2371965f4727', 'BK003', '041419cf-98ef-42e7-a67d-c5f9261af7fc');
INSERT INTO `bkb` VALUES ('73059e05-787e-45a6-aaec-8830df3b1118', 'BK003', '1a49d5b6-d7e2-4937-a190-1aa507c1ae19');
INSERT INTO `bkb` VALUES ('83d088b9-ea98-47a2-89d2-c16e38b4f15a', 'BK002', '35ebcbaa-e73d-4d75-a299-f4f342965e96');
INSERT INTO `bkb` VALUES ('9cad13b3-0133-46cd-bbf1-0fd718e32464', 'BK002', '4eb3ff5a-e930-4844-8231-17485ad76fd5');
INSERT INTO `bkb` VALUES ('b818fbb9-14bd-4537-bc20-273b9eeb080a', 'BK002', 'dc7b26e5-3e06-4e79-a359-fbff8cac94aa');
INSERT INTO `bkb` VALUES ('BB01', 'BK001', 'A001');
INSERT INTO `bkb` VALUES ('BB02', 'BK001', 'A002');
INSERT INTO `bkb` VALUES ('BB03', 'BK001', 'A003');
INSERT INTO `bkb` VALUES ('BB04', 'BK002', 'A004');
INSERT INTO `bkb` VALUES ('BB05', 'BK002', 'A005');
INSERT INTO `bkb` VALUES ('c48362e4-22cf-4602-9b78-7db8a1eb6f43', 'BK003', 'e115c9fc-0df2-43ce-a088-508275810e1d');
INSERT INTO `bkb` VALUES ('da76ab30-8869-47d8-880f-e47753943834', 'BK002', '370dfa78-c60d-43f3-a287-7b289eacbd13');

-- ----------------------------
-- Table structure for bookind
-- ----------------------------
DROP TABLE IF EXISTS `bookind`;
CREATE TABLE `bookind` (
  `bkId` varchar(50) NOT NULL COMMENT '书籍种类主键',
  `kind` varchar(20) NOT NULL COMMENT '书籍种类',
  PRIMARY KEY (`bkId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='书籍种类表';

-- ----------------------------
-- Records of bookind
-- ----------------------------
INSERT INTO `bookind` VALUES ('BK001', '科幻类');
INSERT INTO `bookind` VALUES ('BK002', 'IT类');
INSERT INTO `bookind` VALUES ('BK003', '文学类');
INSERT INTO `bookind` VALUES ('BK004', '艺术类');

-- ----------------------------
-- Table structure for bookss
-- ----------------------------
DROP TABLE IF EXISTS `bookss`;
CREATE TABLE `bookss` (
  `bookId` varchar(100) NOT NULL COMMENT '书籍编号',
  `bookName` varchar(50) NOT NULL COMMENT '书名',
  `bookAuthor` varchar(30) NOT NULL COMMENT '作者',
  `price` double NOT NULL COMMENT '价格',
  `address` varchar(30) NOT NULL COMMENT '出版社',
  `impression` varchar(50) NOT NULL COMMENT '印次',
  `introduce` varchar(200) NOT NULL COMMENT '内容简介',
  PRIMARY KEY (`bookId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='书籍表';

-- ----------------------------
-- Records of bookss
-- ----------------------------
INSERT INTO `bookss` VALUES ('041419cf-98ef-42e7-a67d-c5f9261af7fc', '三国演义(上下)', '罗贯中', '39.5', '人民文学出版社', '第六版', '《三国演义》又名《三国志演义》、《三国志通俗演义》，是我国小说史上**杰出的长篇章回体历史小说');
INSERT INTO `bookss` VALUES ('1a49d5b6-d7e2-4937-a190-1aa507c1ae19', '水浒传', '施耐庵', '75.8', '人民教育出版社', '第四版次', '主要描写的是北宋末年，以宋江为首的一百零八条好汉在山东梁山泊聚义的故事');
INSERT INTO `bookss` VALUES ('35ebcbaa-e73d-4d75-a299-f4f342965e96', 'C语言从入门到精通', '明日科技', '79.8', '清华大学出版社', '第五版', '从初学者的角度出发、通俗易懂的语言风格、内容包括：C语言概述、算法、数据类型、运算符与表达式......');
INSERT INTO `bookss` VALUES ('370dfa78-c60d-43f3-a287-7b289eacbd13', 'HTML5网页设计案例课堂', '刘玉红、蒲娟', '65', '清华大学出版社', '第二版次', '本书以零基础讲解为宗旨，用实例引导读者深入学习，采取\'基础知识→核心技术→应用→项目案例实战\'的讲解模式，深入浅出地讲解HTML5的各项技术及实战技能。');
INSERT INTO `bookss` VALUES ('4cb7a466-46d7-42d3-9236-02bffecdbeb5', 'Java从入门到精通', '明日科技', '79.8', '清华大学出版社', '第六版', '从初学者的角度出发、通过通俗易懂的语言、丰富多彩的实例、详细讲解了使用Java语言进行程序开发所需要的知识。');
INSERT INTO `bookss` VALUES ('4eb3ff5a-e930-4844-8231-17485ad76fd5', 'JavaScript高级程序设计', '[美]马特·弗里斯比', '129', '人民邮电出版社', '第二版', 'JavaScript是Web开发中zui重要的一门语言，它强大而优美。无论是桌面开发，还是移动应用。JavaScript都是 须掌握的技术');
INSERT INTO `bookss` VALUES ('8d4d1396-3793-4cd1-a538-353a0c787491', 'CSS 揭秘', '[希]韦鲁(Lea Verou)', '99', '人民邮电出版社', '第五版', '《CSS揭秘(全彩印刷)》是一本注重实践的教程，作者为我们揭示了47个鲜为人知的CSS技巧，主要内容包括背景与边框、形状、视觉效果、字体排印、用户体验、结构与布局、过渡与动画等');
INSERT INTO `bookss` VALUES ('A001', '三体', '刘慈欣', '41.39', '新华书店出版社', '第四版次', '一部让人在平凡生活中抬头仰望星空的科幻小说');
INSERT INTO `bookss` VALUES ('A002', '伟大的沙丘六部曲', '弗兰克赫伯特', '167.45', '新华书店出版社', '第一版次', '哥白尼提出了“日心说”，我们才知道这个世界不是宇宙的中心、哈勃用望眼镜发现宇宙中还有千亿个银河系...');
INSERT INTO `bookss` VALUES ('A003', '吞噬地球', '张翀', '53', '湖南文艺出版社', '第四版次', '外星人驾驶巨型宇宙飞船“江波号”飞临地球，要求全人类臣服于他们的面前。人类不甘受屈服......');
INSERT INTO `bookss` VALUES ('A004', 'Java编程思想', '布鲁斯', '108', '机械工业出版社', '第7版次', '从Java的基础语法到最高级特性（深入的面向对象概念、多线程、自动项目构建），本书都能逐步指导你轻松掌握');
INSERT INTO `bookss` VALUES ('A005', 'Python从入门到精通', '明日科技', '72', '清华大学出版社', '第2版次', '从初学者的角度、通过通俗易懂的语言、丰富多彩的实例，详细介绍了使用python进行程序开发应掌握的各方面技术');
INSERT INTO `bookss` VALUES ('A01', 'java', '老李', '32.45', '北京出版社', '第三版次', '有关Java基础的资料学习');
INSERT INTO `bookss` VALUES ('A02', 'C++', '老王', '34.56', '清华大学出版社', '第二版次', 'C语言学习爱好者可以参考');
INSERT INTO `bookss` VALUES ('A03', 'PHP', '老黑', '34.5', '北京大学', '第二版次', '真好的号');
INSERT INTO `bookss` VALUES ('A04', 'C语言', '老王', '34.56', '清华大学出版社', '第二版次', 'C语言学习爱好者可以参考');
INSERT INTO `bookss` VALUES ('A05', 'go语言', '老铁', '34.56', '清华大学出版社', '第二版次', 'go语言学习爱好者可以参考');
INSERT INTO `bookss` VALUES ('A06', 'C++', '老王', '34.56', '清华大学出版社', '第二版次', 'C语言学习爱好者可以参考');
INSERT INTO `bookss` VALUES ('A07', 'C++', '老王', '34.56', '清华大学出版社', '第二版次', 'C语言学习爱好者可以参考');
INSERT INTO `bookss` VALUES ('A08', 'C++', '老王', '34.56', '清华大学出版社', '第二版次', 'C语言学习爱好者可以参考');
INSERT INTO `bookss` VALUES ('A09', 'C++', '老王', '34.56', '清华大学出版社', '第二版次', 'C语言学习爱好者可以参考');
INSERT INTO `bookss` VALUES ('A10', 'C++', '老王', '34.56', '清华大学出版社', '第二版次', 'C语言学习爱好者可以参考');
INSERT INTO `bookss` VALUES ('A11', 'java', '老李', '32.45', '北京出版社', '第三版次', '有关Java基础的资料学习');
INSERT INTO `bookss` VALUES ('A12', 'java', '老李', '32.45', '北京出版社', '第三版次', '有关Java基础的资料学习');
INSERT INTO `bookss` VALUES ('A13', 'java', '老李', '32.45', '北京出版社', '第三版次', '有关Java基础的资料学习');
INSERT INTO `bookss` VALUES ('af043521-c37b-42b8-b9d4-da7a17a6d15e', '红楼梦(上下)', '曹雪芹', '59.7', '人民文学出版社', '第五版', '故事讲述的是发生在一个未知朝代的封建大家庭中的人事物，其中以贾宝玉，林黛玉，薛宝钗三个人之间的感情纠葛为主线，通过对一些日常事件的描述，体现了在贾府的大观园中以金陵十二钗为主体的众女子的爱恨情愁。');
INSERT INTO `bookss` VALUES ('dc7b26e5-3e06-4e79-a359-fbff8cac94aa', 'jQuery开发指南', '车云月', '49.8', '清华大学出版社', '第四版次', 'jQuery 是Web 前端及开发的专业核心技术，本书主要讲解了使用jQuery 框架制作网页特效、jQuery 操作DOM、表单验证、AJAX 调取数据、用jQuery 编写插件等。');
INSERT INTO `bookss` VALUES ('dff1004a-da5d-4a83-82c1-8a40a83e7cd7', 'C++程序设计', '刘媛媛', '15.9', '中国工信出版设', '第四版次', '零基础自学C语言从入门到精通');
INSERT INTO `bookss` VALUES ('e115c9fc-0df2-43ce-a088-508275810e1d', '西游记（上/下）', '吴承恩', '47.2', '人民文学出版社', '第六版', '《西游记》主要描写的是孙悟空保唐僧西天取经，历经九九八十一难的故事');

-- ----------------------------
-- Table structure for book_cart_relation
-- ----------------------------
DROP TABLE IF EXISTS `book_cart_relation`;
CREATE TABLE `book_cart_relation` (
  `bcrId` varchar(50) NOT NULL COMMENT '联合主键',
  `bookId` varchar(50) NOT NULL COMMENT '书籍主键',
  `scId` varchar(50) NOT NULL COMMENT '购物车主键',
  PRIMARY KEY (`bcrId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车和书籍表联合用来评论书籍';

-- ----------------------------
-- Records of book_cart_relation
-- ----------------------------
INSERT INTO `book_cart_relation` VALUES ('02796f09-1e83-400b-8622-8797916be032', 'A003', '68f69023-e5e6-455e-9619-31b1d105f00b');
INSERT INTO `book_cart_relation` VALUES ('08fa996f-d2fb-4863-820d-2c19932bd09f', '041419cf-98ef-42e7-a67d-c5f9261af7fc', 'dab6a1fe-324f-4bfd-a6b4-f70b2e659f61');
INSERT INTO `book_cart_relation` VALUES ('19baf15f-7f96-4fb5-a9a1-cc3c4ff10648', 'A001', '766a2cd0-a772-4756-8842-e0faaab7f100');
INSERT INTO `book_cart_relation` VALUES ('1df5f309-9d61-468e-88b9-6f81fb1e4005', 'A001', '65feed96-25b9-4dbe-ae6b-840480a6f53e');
INSERT INTO `book_cart_relation` VALUES ('28f747ff-be22-4dd7-af73-f8e3d735dbe9', '041419cf-98ef-42e7-a67d-c5f9261af7fc', 'acbc8d54-e97f-4b92-9909-17605a58152c');
INSERT INTO `book_cart_relation` VALUES ('2944cd93-575a-47f9-abb9-637b6c239e5d', '041419cf-98ef-42e7-a67d-c5f9261af7fc', '263138b3-ef3d-470c-ac87-acdcdfffab7d');
INSERT INTO `book_cart_relation` VALUES ('2be6db46-7222-418a-9d88-d1aa8ac2d260', 'A001', '75453ab8-b93e-4cb2-9313-ea84501fd2fc');
INSERT INTO `book_cart_relation` VALUES ('2e0a1f5a-0013-41f2-bcd9-369f64988b5f', '4eb3ff5a-e930-4844-8231-17485ad76fd5', 'a85cc318-f58e-4add-8178-953ffe649373');
INSERT INTO `book_cart_relation` VALUES ('30066648-ef0b-493f-b9e9-b082978b327f', '1a49d5b6-d7e2-4937-a190-1aa507c1ae19', '2341ad63-dd46-45ed-90fb-5ff5ce0904f5');
INSERT INTO `book_cart_relation` VALUES ('3f620029-b25d-4ec8-8841-634a21d2624b', '1a49d5b6-d7e2-4937-a190-1aa507c1ae19', 'd042d8d6-60fe-4413-8c57-2f1d96fda940');
INSERT INTO `book_cart_relation` VALUES ('473b36e3-86c5-4109-9631-2b781241912e', '041419cf-98ef-42e7-a67d-c5f9261af7fc', '43418758-90a6-4002-8f10-8c1e3841a574');
INSERT INTO `book_cart_relation` VALUES ('4af217b1-2c16-4323-9589-f5b944d3d233', '041419cf-98ef-42e7-a67d-c5f9261af7fc', 'e114c584-b03a-4f15-91ae-06c97351816a');
INSERT INTO `book_cart_relation` VALUES ('5c0c2a11-f7bd-4aef-a5cd-45b6ba2a130b', '370dfa78-c60d-43f3-a287-7b289eacbd13', '7958f47b-e79f-4e45-a3d6-fdbd9f89cf1d');
INSERT INTO `book_cart_relation` VALUES ('651a024f-e396-4ae6-b812-ff33fef39b40', '041419cf-98ef-42e7-a67d-c5f9261af7fc', 'ac90761a-3cec-43df-9543-d54333ca6768');
INSERT INTO `book_cart_relation` VALUES ('6d4e164c-0a65-4ddc-8d70-5470f71e11b6', '041419cf-98ef-42e7-a67d-c5f9261af7fc', 'bdb3a8f9-3077-4f36-9dce-c1ec4f708e37');
INSERT INTO `book_cart_relation` VALUES ('7a74fa34-aff1-4e44-803a-169a51e2c6e8', 'A003', 'c0994463-f3ed-4868-a8e2-fdbdd77aa402');
INSERT INTO `book_cart_relation` VALUES ('7cd82147-4c45-43a7-8ed7-5a582571b9db', '041419cf-98ef-42e7-a67d-c5f9261af7fc', 'd12f933f-451a-4ed1-a38a-b3fdf2162651');
INSERT INTO `book_cart_relation` VALUES ('80573d6a-23a5-4f95-8bec-7fa5ad4171a9', '041419cf-98ef-42e7-a67d-c5f9261af7fc', '92aa496f-6be3-485c-8403-84723802f516');
INSERT INTO `book_cart_relation` VALUES ('83112518-2f66-4d3b-abd3-ac5626002923', '041419cf-98ef-42e7-a67d-c5f9261af7fc', '5991c4cd-f1b2-4494-b6e2-dac333fb9a5b');
INSERT INTO `book_cart_relation` VALUES ('86ef433f-3de6-43e1-8f69-3333ed2f3daa', '041419cf-98ef-42e7-a67d-c5f9261af7fc', 'dcfff5b0-001f-43a8-afd3-43ac517cb061');
INSERT INTO `book_cart_relation` VALUES ('88011bc5-c0fb-4433-8444-5f0956bd779e', 'af043521-c37b-42b8-b9d4-da7a17a6d15e', 'cfcf461a-c4f1-420d-846c-9c4e52b63f70');
INSERT INTO `book_cart_relation` VALUES ('8a316f67-842c-4f26-a77e-c26c0564dc94', '041419cf-98ef-42e7-a67d-c5f9261af7fc', 'd49a572e-325f-47a3-b762-50f043711178');
INSERT INTO `book_cart_relation` VALUES ('937f4860-f1fa-4c8d-9740-d2994c33f986', '041419cf-98ef-42e7-a67d-c5f9261af7fc', 'a9e579cc-ae9a-44dd-adea-cf9b4b1c8e5a');
INSERT INTO `book_cart_relation` VALUES ('96c538ac-0f99-407f-bd9f-4bc24e9ba1d7', 'A001', 'b163b1a4-b5f5-4e89-89d0-01fbbdb6ab98');
INSERT INTO `book_cart_relation` VALUES ('987f2773-7403-4cfb-8afc-70147ddc4ac7', 'A002', '7f2e5bd0-70e6-456f-b8f4-ad366aa77862');
INSERT INTO `book_cart_relation` VALUES ('9b58f1c7-3ecf-48d1-b771-4171bcfd6556', '1a49d5b6-d7e2-4937-a190-1aa507c1ae19', '50edb762-d462-424d-8dc9-a13d99b50bb7');
INSERT INTO `book_cart_relation` VALUES ('9c6075ff-2ba8-44de-99de-6e9bd9bb78d0', '041419cf-98ef-42e7-a67d-c5f9261af7fc', 'aac311f6-b2aa-4fd9-b7d3-91f63b90deaa');
INSERT INTO `book_cart_relation` VALUES ('a47b9ee6-529e-46f5-ab21-9cd1a508f6cc', '1a49d5b6-d7e2-4937-a190-1aa507c1ae19', '1a948a32-689e-4177-ac51-86e9a4c2bacb');
INSERT INTO `book_cart_relation` VALUES ('b64df894-8e53-4810-b884-a02a3fd88d20', '35ebcbaa-e73d-4d75-a299-f4f342965e96', '82a73a26-6602-49b5-a93c-222c102821d3');
INSERT INTO `book_cart_relation` VALUES ('bb63f8ac-7c3c-40b3-a240-0b72ff20878a', '041419cf-98ef-42e7-a67d-c5f9261af7fc', 'f73c71b0-7510-44d3-9c60-4a5fb8e4c267');
INSERT INTO `book_cart_relation` VALUES ('bca68d53-f414-4544-909e-d28dae38ede7', '041419cf-98ef-42e7-a67d-c5f9261af7fc', '4b211a71-8dbf-4e91-8610-3bde03db7c0f');
INSERT INTO `book_cart_relation` VALUES ('c036deff-6ec2-494c-9d12-f912f7aa12c0', '041419cf-98ef-42e7-a67d-c5f9261af7fc', '7bf15ed5-ad8b-4dbe-9cfe-7b016182ac4b');
INSERT INTO `book_cart_relation` VALUES ('c97ddb0b-b410-4ef2-93cf-8ab2a1d34506', '041419cf-98ef-42e7-a67d-c5f9261af7fc', '720c2d9d-5f3b-40b6-ae23-447fee35e2f8');
INSERT INTO `book_cart_relation` VALUES ('cb5c4da6-993b-4960-8e33-f2130e298918', '041419cf-98ef-42e7-a67d-c5f9261af7fc', '452c58a0-ecbc-4cc0-a685-f071b6cff385');
INSERT INTO `book_cart_relation` VALUES ('cf58a725-9da9-471b-ba54-d9e983437833', '041419cf-98ef-42e7-a67d-c5f9261af7fc', 'af0f3770-0a28-4058-b55b-9d81d36f3c6a');
INSERT INTO `book_cart_relation` VALUES ('d04fd72b-19a4-4ec8-aafe-9f0243d4244d', 'af043521-c37b-42b8-b9d4-da7a17a6d15e', '3fc9a1a2-2595-44e0-a4e4-96f358b0e5fd');
INSERT INTO `book_cart_relation` VALUES ('d4e16624-bcd4-45da-98a4-68f969a30fe5', '8d4d1396-3793-4cd1-a538-353a0c787491', 'f218ee05-39ca-4ed8-8c73-0b7bfeb60357');
INSERT INTO `book_cart_relation` VALUES ('d6154d61-897f-47ab-86c7-5e6980b618fc', 'A001', 'c3a9e3f8-7c2e-40f9-8c98-3912db26ec75');
INSERT INTO `book_cart_relation` VALUES ('eb7992d6-e4b3-4a3b-934d-9e04c796d519', '1a49d5b6-d7e2-4937-a190-1aa507c1ae19', '409bfe5c-1874-4de4-bbe0-2fc2fb6f8232');
INSERT INTO `book_cart_relation` VALUES ('fb25dbc1-56ba-4a2f-8d8c-ce0dd2192e08', '35ebcbaa-e73d-4d75-a299-f4f342965e96', '5d31729f-a4a8-491b-ba2d-6dc4689b68e8');

-- ----------------------------
-- Table structure for buglog
-- ----------------------------
DROP TABLE IF EXISTS `buglog`;
CREATE TABLE `buglog` (
  `bgId` varchar(50) NOT NULL COMMENT '主键',
  `bookName` varchar(20) NOT NULL COMMENT '书籍名称',
  `count` int(11) DEFAULT NULL COMMENT '数量',
  `price` double DEFAULT NULL COMMENT '价格',
  `usId` varchar(50) DEFAULT NULL COMMENT '购物单主键',
  PRIMARY KEY (`bgId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物单日志';

-- ----------------------------
-- Records of buglog
-- ----------------------------
INSERT INTO `buglog` VALUES ('0352448b-ee1d-4d3f-876a-0d0cdb6567da', 'CSS 揭秘', '1', '99', 'e62eae79-3ba1-4779-838e-efcff7df87b8');
INSERT INTO `buglog` VALUES ('1f1a828f-7952-42bb-af51-bc2be370d32c', '水浒传', '1', '75.8', '59eeae88-77fe-4e62-a993-44c7ca08a89e');
INSERT INTO `buglog` VALUES ('21c8f4dc-c87a-48e9-9df6-8162b43381b7', '三国演义(上下)', '1', '39.5', 'f0adabbb-17d3-4fed-a359-a5d2abf35ce5');
INSERT INTO `buglog` VALUES ('24def75d-c878-4a62-a673-07019b0fb0cf', '伟大的沙丘六部曲', '1', '167.45', 'd29b0f25-b0d8-4ab2-9afa-1c2974e93120');
INSERT INTO `buglog` VALUES ('31d2a4b0-b61a-4f1f-9bfc-932af6aca0d4', '三国演义(上下)', '1', '39.5', 'b76f3126-002e-418a-8965-acda37f8190a');
INSERT INTO `buglog` VALUES ('36f25730-ba2c-4676-8db5-ca19175e71f1', '三国演义(上下)', '1', '39.5', 'fbfe1859-8750-49ef-a208-1a0dcbe6106e');
INSERT INTO `buglog` VALUES ('41fde0b2-daf6-4205-80da-39a2b0941c68', '三体', '1', '41.39', 'a5364a22-7a6d-4302-b8f4-67238a3b5d83');
INSERT INTO `buglog` VALUES ('4546eff0-5306-4bc8-bd5a-2a4e0a9c3f14', '三体', '1', '41.39', 'd0f8e92c-9222-46cd-a5b3-fa82de340816');
INSERT INTO `buglog` VALUES ('597e0aff-d5ed-404a-a6aa-c92825e7b172', 'C语言从入门到精通', '1', '79.8', 'f2e0b3f5-20f7-4b57-bfad-037a906e54f6');
INSERT INTO `buglog` VALUES ('5fa99645-0123-4d25-8e9a-c5fd7922de97', '三国演义(上下)', '1', '39.5', 'aa56a609-033e-4740-8f71-2c689e7e3e61');
INSERT INTO `buglog` VALUES ('605cc01c-2a59-4886-a8bd-aa0de14eaff7', '水浒传', '1', '75.8', 'e8531bcc-352c-4009-a58f-1b8876f07642');
INSERT INTO `buglog` VALUES ('68a0920f-5995-4dd7-8fba-17004a674d2d', '三国演义(上下)', '1', '39.5', '6535a286-66d1-4844-8be8-79b4c1387867');
INSERT INTO `buglog` VALUES ('6a824132-34bf-45d8-9da0-d5b990c06b61', '三体', '1', '41.39', '70f56298-6b26-40d3-96fd-5f0819a6a954');
INSERT INTO `buglog` VALUES ('6b9d7476-7d04-4bf3-b83d-76f547dafef1', '三国演义(上下)', '1', '39.5', '4b358ca3-5a07-4204-8c4e-d52389688b8b');
INSERT INTO `buglog` VALUES ('7a2046fd-ffc2-42ce-9483-93dc85b18887', '水浒传', '1', '75.8', '23756c70-a8a3-48cf-ac52-320b5457082c');
INSERT INTO `buglog` VALUES ('7c369ef5-4f4c-4c90-b20e-50937d67e676', '三国演义(上下)', '1', '39.5', '23756c70-a8a3-48cf-ac52-320b5457082c');
INSERT INTO `buglog` VALUES ('7f3c7540-63e0-4c4e-b519-087071b86def', '三国演义(上下)', '1', '39.5', 'f31f57d6-3a98-478c-a0db-22769d6c14af');
INSERT INTO `buglog` VALUES ('8a5419ae-ae39-4e1a-9ad0-3c5d04dbf9a3', '三体', '1', '41.39', '85e7777e-fdcb-4968-aacc-8b15afabd3ab');
INSERT INTO `buglog` VALUES ('96367a17-b918-4228-8bb2-032f733eb5f9', '三国演义(上下)', '1', '39.5', '4377e141-c6db-4fb9-8caf-098c25d62795');
INSERT INTO `buglog` VALUES ('a6d89128-6109-46e9-bd36-314b5ccada1a', '三国演义(上下)', '1', '39.5', '174b06a2-d272-49c1-8ed6-69025b8d0076');
INSERT INTO `buglog` VALUES ('a8d6618a-448d-4b32-83dd-1cc8d56c84e8', '三国演义(上下)', '1', '39.5', 'bffcea77-5d7a-41ea-ba1b-60496703dfa7');
INSERT INTO `buglog` VALUES ('aaf57aa9-55ec-4378-aaa1-c56ce90d2ee6', '红楼梦(上下)', '1', '59.7', '70f56298-6b26-40d3-96fd-5f0819a6a954');
INSERT INTO `buglog` VALUES ('abe8d8ee-566b-4638-a855-9484c1e1061a', '三国演义(上下)', '1', '39.5', 'bffcea77-5d7a-41ea-ba1b-60496703dfa7');
INSERT INTO `buglog` VALUES ('afe2dd20-8a10-4433-8616-0afdd143d488', '红楼梦(上下)', '1', '59.7', 'a42b2876-7acd-43cf-a9e9-e938414decc7');
INSERT INTO `buglog` VALUES ('b89c4389-36c6-440b-a0d2-a82292d496c4', '三国演义(上下)', '1', '39.5', 'acb380fd-6cc6-4f0c-b6c1-e2c75bff5545');
INSERT INTO `buglog` VALUES ('bf2404e2-8c50-4dc2-8bc9-a798e06a0d32', '三国演义(上下)', '1', '39.5', '541c9b52-6715-4b7e-8ca6-185c0f5e4657');
INSERT INTO `buglog` VALUES ('c21540c5-b6be-4a7e-bb95-acc3f12879d9', 'C语言从入门到精通', '1', '79.8', 'e8531bcc-352c-4009-a58f-1b8876f07642');
INSERT INTO `buglog` VALUES ('cb1717dd-1f34-419b-9095-24ebd07e6c8c', 'JavaScript高级程序设计', '1', '129', '70f56298-6b26-40d3-96fd-5f0819a6a954');
INSERT INTO `buglog` VALUES ('ce97f850-d8a8-4515-8248-e70eb4644b39', '水浒传', '1', '75.8', '40d7d22e-031f-458e-93c2-e1430adb219d');
INSERT INTO `buglog` VALUES ('d7c28e37-badc-4fd8-9678-c2dbe56acac8', '三体', '1', '41.39', '40d7d22e-031f-458e-93c2-e1430adb219d');
INSERT INTO `buglog` VALUES ('df27527b-0e08-4a52-8ee6-450ae7d5e84b', '三国演义(上下)', '1', '39.5', '2ee37ae8-ba70-4dc5-85a0-2570d17b356e');
INSERT INTO `buglog` VALUES ('e4682f1b-c3fe-4924-8de3-1ea03f458e18', '水浒传', '1', '75.8', 'f0adabbb-17d3-4fed-a359-a5d2abf35ce5');
INSERT INTO `buglog` VALUES ('ee2ec445-0857-44e8-934f-76c4344349af', '三国演义(上下)', '1', '39.5', '04d165f0-4a04-438c-abc1-2501f05df9d7');
INSERT INTO `buglog` VALUES ('f62422da-dabf-4665-8089-9a1408580948', '三国演义(上下)', '1', '39.5', '4b358ca3-5a07-4204-8c4e-d52389688b8b');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `cId` varchar(50) NOT NULL COMMENT '评论表主键',
  `discuss` varchar(100) DEFAULT NULL COMMENT '评论',
  `usercount` varchar(20) NOT NULL COMMENT '用户',
  `bookId` varchar(50) DEFAULT NULL,
  `times` varchar(50) NOT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品评论表';

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('0396b3a3-05d5-46fa-abc5-eecee12c41eb', '商家真是良心、不错、值得购买', 'admin', 'A001', '2021-11-25 09:09:49');
INSERT INTO `comment` VALUES ('0cbc21bc-b45d-45e4-bfe6-fa3573f44946', '读三国、品历史、不错', 'xio', '041419cf-98ef-42e7-a67d-c5f9261af7fc', '2021-11-22 17:01:09');
INSERT INTO `comment` VALUES ('0fada6b3-fa99-4955-8f78-353f1732f0b4', '很好看呐', 'xio', '1a49d5b6-d7e2-4937-a190-1aa507c1ae19', '2021-11-22 15:53:06');
INSERT INTO `comment` VALUES ('27164daa-a47c-4226-9b4f-0aa517b22934', '我看了三体、感觉可以', 'admin', 'A001', '2021-11-25 17:16:35');
INSERT INTO `comment` VALUES ('5187ffd0-d16f-4a27-a6c7-f42d98b3202b', '很好看呐', 'admin', '041419cf-98ef-42e7-a67d-c5f9261af7fc', '2021-11-27 18:46:09');
INSERT INTO `comment` VALUES ('6de027be-37c1-4f6d-9725-fcc79f0f217f', '商家真是良心、不错、值得购买', 'xio', '041419cf-98ef-42e7-a67d-c5f9261af7fc', '2021-11-22 14:44:07');
INSERT INTO `comment` VALUES ('879f4c66-12ad-417a-9b8b-6bba0219a555', '商家真是良心、不错、值得购买', 'admin', 'af043521-c37b-42b8-b9d4-da7a17a6d15e', '2021-11-27 16:12:33');
INSERT INTO `comment` VALUES ('9b8d580a-ffec-4e96-9198-575ecc09d639', '很好看呐', 'xio', '041419cf-98ef-42e7-a67d-c5f9261af7fc', '2021-11-20 17:05:35');
INSERT INTO `comment` VALUES ('C001', '这本书非常好的、值得购买', 'xio', 'A01', '2021/11/3');
INSERT INTO `comment` VALUES ('C002', '非常好的书籍、', 'lisi', 'A01', '2020/5/6');
INSERT INTO `comment` VALUES ('c4ac590f-95ba-4baa-b974-d6d5c51a2bb6', '很好看呐', 'xio', 'A001', '2021-11-25 09:10:35');
INSERT INTO `comment` VALUES ('d52396b2-5afb-49dd-9beb-8397958f4811', '很好', 'xio', '1a49d5b6-d7e2-4937-a190-1aa507c1ae19', '2021-11-20 16:58:47');
INSERT INTO `comment` VALUES ('d9fdd344-94b0-4a02-b025-2deeb2e8d04f', 'C语言从入门到入狱', 'xio', '35ebcbaa-e73d-4d75-a299-f4f342965e96', '2021-11-23 10:24:18');
INSERT INTO `comment` VALUES ('e7450659-b561-411c-9265-3b00bf46a148', '商家真是良心、不错、值得购买', 'xio', '041419cf-98ef-42e7-a67d-c5f9261af7fc', '2021-11-20 17:04:50');
INSERT INTO `comment` VALUES ('f6edf516-0b2c-4593-941f-47cb8b3f06e1', '商家真是良心、不错、值得购买', 'admin', 'A001', '2021-11-24 17:56:55');
INSERT INTO `comment` VALUES ('fa327a10-a941-43fb-89ed-c71267987f43', '很好看呐', 'xio', '041419cf-98ef-42e7-a67d-c5f9261af7fc', '2021-11-22 17:11:57');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `usercount` varchar(50) NOT NULL COMMENT '账号',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `sex` varchar(10) NOT NULL COMMENT '性别',
  `age` int(11) NOT NULL COMMENT '年龄',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `tel` varchar(50) DEFAULT NULL COMMENT '电话',
  `address` varchar(50) DEFAULT NULL COMMENT '地址',
  `password` varchar(30) NOT NULL,
  `is_delete` int(11) NOT NULL,
  PRIMARY KEY (`usercount`) COMMENT '账号为主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('admin', '郑玉柱', '男', '21', '2977429967@qq.com', '18348375641', '河南省周口市沈丘县范营乡郑营村', '123456', '0');
INSERT INTO `customer` VALUES ('liuer', '刘二', '男', '18', 'lisi@qq.com', '18739786547', '西安', '111', '1');
INSERT INTO `customer` VALUES ('Liuyong', '刘涌', '男', '21', 'liu@qq.com', '14567834567', '湖南岳阳', '111', '1');
INSERT INTO `customer` VALUES ('Long', '龙龙', '男', '21', 'long@qq.com', '14567834567', '湖南省长沙市开福区', '111', '1');
INSERT INTO `customer` VALUES ('Xiaohong', '小红', '女', '21', 'xiao@qq.com', '18739786547', '湖南长沙', '111', '0');
INSERT INTO `customer` VALUES ('xio', '小孩', '男', '18', '2977429967@qq.com', '18348375656', '河南周口', '123456', '1');
INSERT INTO `customer` VALUES ('zzz', '张张张', '男', '23', '343@qq.com', '12343456543', '海南岛', '111', '0');

-- ----------------------------
-- Table structure for customer_r
-- ----------------------------
DROP TABLE IF EXISTS `customer_r`;
CREATE TABLE `customer_r` (
  `crId` varchar(50) NOT NULL COMMENT '联合表主键',
  `usercount` varchar(50) NOT NULL COMMENT '用户主键',
  `roleId` varchar(50) NOT NULL COMMENT '角色主键',
  PRIMARY KEY (`crId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色联合表';

-- ----------------------------
-- Records of customer_r
-- ----------------------------
INSERT INTO `customer_r` VALUES ('3ecdc5dd-2b0a-42d9-abcb-817d522327d6', 'admin', 'R003');
INSERT INTO `customer_r` VALUES ('CR001', 'xio', 'R003');
INSERT INTO `customer_r` VALUES ('CR002', 'xio', 'R001');

-- ----------------------------
-- Table structure for hvaddress
-- ----------------------------
DROP TABLE IF EXISTS `hvaddress`;
CREATE TABLE `hvaddress` (
  `haId` varchar(50) NOT NULL COMMENT '主键',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `address` varchar(30) NOT NULL COMMENT '所在地区',
  `tel` varchar(50) NOT NULL COMMENT '电话',
  `usercount` varchar(30) NOT NULL,
  PRIMARY KEY (`haId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收获地址表';

-- ----------------------------
-- Records of hvaddress
-- ----------------------------
INSERT INTO `hvaddress` VALUES ('4e0f7ec9-3c9b-4c48-872d-a31cdf6abe97', '刘磊', '湖南省开福区长沙大学洪山', '18348375645', 'Liulei');
INSERT INTO `hvaddress` VALUES ('7e8745d5-14b6-48a9-8121-3044faca90db', '小明', '湖南省开福区长沙大学洪山', '18348375642', 'xiaoming');
INSERT INTO `hvaddress` VALUES ('a86def7f-8938-4ff3-bcd8-24544dd81c46', '郑玉柱', '湖南省开福区长沙大学洪山', '18348375641', 'admin');
INSERT INTO `hvaddress` VALUES ('HA001', '郑玉柱', '湖南省长沙市开福区长沙大学菜鸟裹裹', '18348375641', 'xio');
INSERT INTO `hvaddress` VALUES ('HA002', '郑成功', '河南省周口市沈丘县范营乡郑营村', '18439875743', 'xio');

-- ----------------------------
-- Table structure for order_address
-- ----------------------------
DROP TABLE IF EXISTS `order_address`;
CREATE TABLE `order_address` (
  `oaId` varchar(50) NOT NULL COMMENT '主键',
  `usId` varchar(50) NOT NULL COMMENT '订单主键',
  `haId` varchar(50) NOT NULL COMMENT '地址主键',
  PRIMARY KEY (`oaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单和地址联合';

-- ----------------------------
-- Records of order_address
-- ----------------------------
INSERT INTO `order_address` VALUES ('17eb06f1-5082-4896-843d-a7d9bc93ca06', 'f31f57d6-3a98-478c-a0db-22769d6c14af', 'HA001');
INSERT INTO `order_address` VALUES ('4bcb587e-4ca2-417f-9bdf-3fa92f513d48', 'a5364a22-7a6d-4302-b8f4-67238a3b5d83', 'HA002');
INSERT INTO `order_address` VALUES ('4dcc0ade-fa1e-42c8-a6f1-bb51abd6cdc1', 'acb380fd-6cc6-4f0c-b6c1-e2c75bff5545', 'a86def7f-8938-4ff3-bcd8-24544dd81c46');
INSERT INTO `order_address` VALUES ('54b8e91f-a7b2-4358-a2fb-f0499371bc43', 'bffcea77-5d7a-41ea-ba1b-60496703dfa7', 'HA001');
INSERT INTO `order_address` VALUES ('7a47fa4e-f942-4da5-b9a6-8efb2fd3a26a', 'e62eae79-3ba1-4779-838e-efcff7df87b8', 'HA001');
INSERT INTO `order_address` VALUES ('b2158857-6d5e-4b56-9f39-e64688d3ed67', 'd29b0f25-b0d8-4ab2-9afa-1c2974e93120', 'a86def7f-8938-4ff3-bcd8-24544dd81c46');
INSERT INTO `order_address` VALUES ('cb520f1f-a6ef-4c49-8578-2d1ad21d6c61', '174b06a2-d272-49c1-8ed6-69025b8d0076', 'a86def7f-8938-4ff3-bcd8-24544dd81c46');

-- ----------------------------
-- Table structure for paylog
-- ----------------------------
DROP TABLE IF EXISTS `paylog`;
CREATE TABLE `paylog` (
  `pId` varchar(50) NOT NULL COMMENT '主键',
  `usercount` varchar(50) NOT NULL COMMENT '用户',
  `money` int(11) NOT NULL COMMENT '充值金额',
  `pay_time` varchar(50) NOT NULL COMMENT '充值时间',
  PRIMARY KEY (`pId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充值记录';

-- ----------------------------
-- Records of paylog
-- ----------------------------
INSERT INTO `paylog` VALUES ('32b61b3d-4b1b-44a4-aab2-a90b58ce2399', 'admin', '100', '2021-11-27 19:12:48');
INSERT INTO `paylog` VALUES ('52f3a91a-45e7-421e-97b8-50854238fc36', 'xio', '1000', '2021-11-17 14:11:31');
INSERT INTO `paylog` VALUES ('64eee2c6-4f85-4c81-8e0a-099e46704bd8', 'xio', '1000', '2021-11-17 16:58:12');
INSERT INTO `paylog` VALUES ('84211787-4340-41d8-8ffb-71e071865e87', 'xio', '144', '2021-11-22 17:55:13');
INSERT INTO `paylog` VALUES ('99a13e53-6ff8-454f-b4fa-d0c709165c8c', 'xio', '1000', '2021-11-22 15:53:47');
INSERT INTO `paylog` VALUES ('a2f42d37-5576-47af-bc2c-0640df063f05', 'xio', '1000', '2021-11-17 16:57:59');
INSERT INTO `paylog` VALUES ('bdb1f27d-01b9-4b24-8af2-53ba8f94c412', 'xio', '12', '2021-11-22 17:55:04');
INSERT INTO `paylog` VALUES ('c91da940-a031-442c-8323-5263907e0eed', 'admin', '1000', '2021-11-24 17:55:40');
INSERT INTO `paylog` VALUES ('dcd7930a-c779-4bc0-97bb-102b817795d6', 'xio', '1000', '2021-11-17 14:33:39');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `roleId` varchar(50) NOT NULL COMMENT '角色主键',
  `rname` varchar(20) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('R001', '管理员');
INSERT INTO `roles` VALUES ('R002', '游客');
INSERT INTO `roles` VALUES ('R003', '用户');

-- ----------------------------
-- Table structure for role_ah
-- ----------------------------
DROP TABLE IF EXISTS `role_ah`;
CREATE TABLE `role_ah` (
  `raId` varchar(50) NOT NULL COMMENT '联合主键',
  `roleId` varchar(50) NOT NULL COMMENT '角色表主键',
  `ahId` varchar(50) NOT NULL COMMENT '权限表主建',
  PRIMARY KEY (`raId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限联合表';

-- ----------------------------
-- Records of role_ah
-- ----------------------------
INSERT INTO `role_ah` VALUES ('AH010', 'R003', 'AH010');
INSERT INTO `role_ah` VALUES ('AH011', 'R003', 'AH011');
INSERT INTO `role_ah` VALUES ('RA001', 'R003', 'AH002');
INSERT INTO `role_ah` VALUES ('RA002', 'R003', 'AH003');
INSERT INTO `role_ah` VALUES ('RA003', 'R003', 'AH004');
INSERT INTO `role_ah` VALUES ('RA004', 'R003', 'AH005');
INSERT INTO `role_ah` VALUES ('RA005', 'R003', 'AH006');
INSERT INTO `role_ah` VALUES ('RA006', 'R003', 'AH007');
INSERT INTO `role_ah` VALUES ('RA007', 'R003', 'AH008');
INSERT INTO `role_ah` VALUES ('RA008', 'R003', 'AH009');
INSERT INTO `role_ah` VALUES ('RA009', 'R003', 'AH010');
INSERT INTO `role_ah` VALUES ('RA010', 'R003', 'AH011');
INSERT INTO `role_ah` VALUES ('RA011', 'R003', 'AH012');
INSERT INTO `role_ah` VALUES ('RA012', 'R003', 'AH013');
INSERT INTO `role_ah` VALUES ('RA013', 'R003', 'AH014');

-- ----------------------------
-- Table structure for shopcart
-- ----------------------------
DROP TABLE IF EXISTS `shopcart`;
CREATE TABLE `shopcart` (
  `scId` varchar(50) NOT NULL COMMENT '购物车主键',
  `usercount` varchar(50) NOT NULL COMMENT '购物车主人',
  `bookName` varchar(50) NOT NULL COMMENT '商品名称',
  `count` int(11) NOT NULL COMMENT '商品数量',
  `price` double NOT NULL COMMENT '商品价格',
  `store` varchar(30) NOT NULL COMMENT '店铺',
  `is_buyshop` int(11) NOT NULL,
  PRIMARY KEY (`scId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车表';

-- ----------------------------
-- Records of shopcart
-- ----------------------------
INSERT INTO `shopcart` VALUES ('68f69023-e5e6-455e-9619-31b1d105f00b', 'xio', '吞噬地球', '2', '53', '北航邮电', '0');
INSERT INTO `shopcart` VALUES ('7958f47b-e79f-4e45-a3d6-fdbd9f89cf1d', 'xio', 'HTML5网页设计案例课堂', '1', '65', '北航邮电', '0');
INSERT INTO `shopcart` VALUES ('c0994463-f3ed-4868-a8e2-fdbdd77aa402', 'admin', '吞噬地球', '1', '53', '北航邮电', '0');

-- ----------------------------
-- Table structure for shoplist
-- ----------------------------
DROP TABLE IF EXISTS `shoplist`;
CREATE TABLE `shoplist` (
  `slId` varchar(50) NOT NULL COMMENT '购物清单主键',
  `bookName` varchar(20) NOT NULL COMMENT '书名',
  `count` int(11) NOT NULL COMMENT '数量',
  `price` double NOT NULL COMMENT '总价格',
  `is_buy` int(11) NOT NULL,
  PRIMARY KEY (`slId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物单';

-- ----------------------------
-- Records of shoplist
-- ----------------------------
INSERT INTO `shoplist` VALUES ('2341ad63-dd46-45ed-90fb-5ff5ce0904f5', '水浒传', '1', '75.8', '1');
INSERT INTO `shoplist` VALUES ('3fc9a1a2-2595-44e0-a4e4-96f358b0e5fd', '红楼梦(上下)', '1', '59.7', '1');
INSERT INTO `shoplist` VALUES ('409bfe5c-1874-4de4-bbe0-2fc2fb6f8232', '水浒传', '1', '75.8', '1');
INSERT INTO `shoplist` VALUES ('5991c4cd-f1b2-4494-b6e2-dac333fb9a5b', '三国演义(上下)', '1', '39.5', '1');
INSERT INTO `shoplist` VALUES ('65feed96-25b9-4dbe-ae6b-840480a6f53e', '三体', '1', '41.39', '1');
INSERT INTO `shoplist` VALUES ('720c2d9d-5f3b-40b6-ae23-447fee35e2f8', '三国演义(上下)', '1', '39.5', '1');
INSERT INTO `shoplist` VALUES ('75453ab8-b93e-4cb2-9313-ea84501fd2fc', '三体', '1', '41.39', '1');
INSERT INTO `shoplist` VALUES ('766a2cd0-a772-4756-8842-e0faaab7f100', '三体', '1', '41.39', '1');
INSERT INTO `shoplist` VALUES ('7bf15ed5-ad8b-4dbe-9cfe-7b016182ac4b', '三国演义(上下)', '1', '39.5', '1');
INSERT INTO `shoplist` VALUES ('7f2e5bd0-70e6-456f-b8f4-ad366aa77862', '伟大的沙丘六部曲', '1', '167.45', '1');
INSERT INTO `shoplist` VALUES ('82a73a26-6602-49b5-a93c-222c102821d3', 'C语言从入门到精通', '1', '79.8', '1');
INSERT INTO `shoplist` VALUES ('a85cc318-f58e-4add-8178-953ffe649373', 'JavaScript高级程序设计', '1', '129', '1');
INSERT INTO `shoplist` VALUES ('a9e579cc-ae9a-44dd-adea-cf9b4b1c8e5a', '三国演义(上下)', '1', '39.5', '1');
INSERT INTO `shoplist` VALUES ('acbc8d54-e97f-4b92-9909-17605a58152c', '三国演义(上下)', '1', '39.5', '1');
INSERT INTO `shoplist` VALUES ('b163b1a4-b5f5-4e89-89d0-01fbbdb6ab98', '三体', '1', '41.39', '1');
INSERT INTO `shoplist` VALUES ('bdb3a8f9-3077-4f36-9dce-c1ec4f708e37', '三国演义(上下)', '1', '39.5', '1');
INSERT INTO `shoplist` VALUES ('c3a9e3f8-7c2e-40f9-8c98-3912db26ec75', '三体', '1', '41.39', '1');
INSERT INTO `shoplist` VALUES ('cfcf461a-c4f1-420d-846c-9c4e52b63f70', '红楼梦(上下)', '1', '59.7', '1');
INSERT INTO `shoplist` VALUES ('d042d8d6-60fe-4413-8c57-2f1d96fda940', '水浒传', '1', '75.8', '1');
INSERT INTO `shoplist` VALUES ('d12f933f-451a-4ed1-a38a-b3fdf2162651', '三国演义(上下)', '1', '39.5', '1');
INSERT INTO `shoplist` VALUES ('d49a572e-325f-47a3-b762-50f043711178', '三国演义(上下)', '1', '39.5', '1');
INSERT INTO `shoplist` VALUES ('dab6a1fe-324f-4bfd-a6b4-f70b2e659f61', '三国演义(上下)', '1', '39.5', '1');
INSERT INTO `shoplist` VALUES ('dcfff5b0-001f-43a8-afd3-43ac517cb061', '三国演义(上下)', '1', '39.5', '1');
INSERT INTO `shoplist` VALUES ('e114c584-b03a-4f15-91ae-06c97351816a', '三国演义(上下)', '1', '39.5', '1');
INSERT INTO `shoplist` VALUES ('f218ee05-39ca-4ed8-8c73-0b7bfeb60357', 'CSS 揭秘', '1', '99', '1');

-- ----------------------------
-- Table structure for shop_list_buylog
-- ----------------------------
DROP TABLE IF EXISTS `shop_list_buylog`;
CREATE TABLE `shop_list_buylog` (
  `slbId` varchar(50) NOT NULL COMMENT '主键',
  `slId` varchar(50) NOT NULL COMMENT '清单主键',
  `bgId` varchar(50) NOT NULL COMMENT '日志主键',
  PRIMARY KEY (`slbId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购买记录和商品清单主键';

-- ----------------------------
-- Records of shop_list_buylog
-- ----------------------------
INSERT INTO `shop_list_buylog` VALUES ('061d40dc-7d46-48dd-ace4-84bf6c60aece', 'd49a572e-325f-47a3-b762-50f043711178', 'df27527b-0e08-4a52-8ee6-450ae7d5e84b');
INSERT INTO `shop_list_buylog` VALUES ('1639c0c7-e4dd-4fe0-b649-8285cc4d8340', 'a9e579cc-ae9a-44dd-adea-cf9b4b1c8e5a', 'bf2404e2-8c50-4dc2-8bc9-a798e06a0d32');
INSERT INTO `shop_list_buylog` VALUES ('1b64be90-e817-401a-9ca0-7941ec953bf7', 'f73c71b0-7510-44d3-9c60-4a5fb8e4c267', '3b619a11-ef5b-456f-a45e-9bdd837ad3e6');
INSERT INTO `shop_list_buylog` VALUES ('1d755cd4-6513-452e-bda0-990d74317d6a', 'acbc8d54-e97f-4b92-9909-17605a58152c', '36f25730-ba2c-4676-8db5-ca19175e71f1');
INSERT INTO `shop_list_buylog` VALUES ('225b17f7-3fdd-4ddd-81b1-9ae8aa5ea114', '7bf15ed5-ad8b-4dbe-9cfe-7b016182ac4b', '7c369ef5-4f4c-4c90-b20e-50937d67e676');
INSERT INTO `shop_list_buylog` VALUES ('2a868d5e-caad-4773-ba43-aa401f0d3b12', '766a2cd0-a772-4756-8842-e0faaab7f100', '6a824132-34bf-45d8-9da0-d5b990c06b61');
INSERT INTO `shop_list_buylog` VALUES ('377474ac-3cb9-4227-bcde-12e8d66880a7', 'd12f933f-451a-4ed1-a38a-b3fdf2162651', '68a0920f-5995-4dd7-8fba-17004a674d2d');
INSERT INTO `shop_list_buylog` VALUES ('3a5c01e9-b587-4c75-b574-68d288264b4c', '409bfe5c-1874-4de4-bbe0-2fc2fb6f8232', '1f1a828f-7952-42bb-af51-bc2be370d32c');
INSERT INTO `shop_list_buylog` VALUES ('419d21ec-7642-4c27-89c9-d9eac4e32c72', 'f218ee05-39ca-4ed8-8c73-0b7bfeb60357', '0352448b-ee1d-4d3f-876a-0d0cdb6567da');
INSERT INTO `shop_list_buylog` VALUES ('490421cd-1379-4ccc-b8d5-70a4e295926b', 'acbc8d54-e97f-4b92-9909-17605a58152c', 'ee2ec445-0857-44e8-934f-76c4344349af');
INSERT INTO `shop_list_buylog` VALUES ('4e6a6208-5f6b-4d02-bd30-d09ad489f1e4', 'cfcf461a-c4f1-420d-846c-9c4e52b63f70', 'afe2dd20-8a10-4433-8616-0afdd143d488');
INSERT INTO `shop_list_buylog` VALUES ('4e92d7f1-c6db-4835-88f5-96426192b99f', '82a73a26-6602-49b5-a93c-222c102821d3', '597e0aff-d5ed-404a-a6aa-c92825e7b172');
INSERT INTO `shop_list_buylog` VALUES ('547efeaf-9844-49b2-ab9b-abb5e5ef6c76', '7f2e5bd0-70e6-456f-b8f4-ad366aa77862', '24def75d-c878-4a62-a673-07019b0fb0cf');
INSERT INTO `shop_list_buylog` VALUES ('56b928bd-64f1-4685-83bc-ada5b3f94f1e', 'd49a572e-325f-47a3-b762-50f043711178', '96367a17-b918-4228-8bb2-032f733eb5f9');
INSERT INTO `shop_list_buylog` VALUES ('654d5ac6-4fe6-45ac-87dc-01db63120720', '409bfe5c-1874-4de4-bbe0-2fc2fb6f8232', '605cc01c-2a59-4886-a8bd-aa0de14eaff7');
INSERT INTO `shop_list_buylog` VALUES ('6a4996ed-038f-4fd3-85b4-3e76e4bc04e9', '5991c4cd-f1b2-4494-b6e2-dac333fb9a5b', '6b9d7476-7d04-4bf3-b83d-76f547dafef1');
INSERT INTO `shop_list_buylog` VALUES ('7dee24df-6dfb-4860-8de0-23bc39f716f9', 'd042d8d6-60fe-4413-8c57-2f1d96fda940', 'ce97f850-d8a8-4515-8248-e70eb4644b39');
INSERT INTO `shop_list_buylog` VALUES ('832577e0-9619-4e14-ba30-233b11ff2fa6', 'd12f933f-451a-4ed1-a38a-b3fdf2162651', '31d2a4b0-b61a-4f1f-9bfc-932af6aca0d4');
INSERT INTO `shop_list_buylog` VALUES ('8562b061-8043-48ae-a0e4-a924a0462230', 'dab6a1fe-324f-4bfd-a6b4-f70b2e659f61', 'f62422da-dabf-4665-8089-9a1408580948');
INSERT INTO `shop_list_buylog` VALUES ('85eaa65b-1698-4499-bae7-6bbca68dfa98', 'bdb3a8f9-3077-4f36-9dce-c1ec4f708e37', 'abe8d8ee-566b-4638-a855-9484c1e1061a');
INSERT INTO `shop_list_buylog` VALUES ('87472570-7bd7-4562-a25d-bf38eca1f3e8', '2341ad63-dd46-45ed-90fb-5ff5ce0904f5', '7a2046fd-ffc2-42ce-9483-93dc85b18887');
INSERT INTO `shop_list_buylog` VALUES ('89dfb89b-4438-4b79-ab58-578ea74cd593', '65feed96-25b9-4dbe-ae6b-840480a6f53e', 'd7c28e37-badc-4fd8-9678-c2dbe56acac8');
INSERT INTO `shop_list_buylog` VALUES ('9300f3c6-50c7-4aaf-ae0c-5167d0175b30', '720c2d9d-5f3b-40b6-ae23-447fee35e2f8', 'b89c4389-36c6-440b-a0d2-a82292d496c4');
INSERT INTO `shop_list_buylog` VALUES ('9639b785-dc04-4d9d-b209-9b3a8137d201', 'acbc8d54-e97f-4b92-9909-17605a58152c', '5fa99645-0123-4d25-8e9a-c5fd7922de97');
INSERT INTO `shop_list_buylog` VALUES ('9affe132-75bc-4be9-b982-b774ec4432c5', 'c3a9e3f8-7c2e-40f9-8c98-3912db26ec75', '4546eff0-5306-4bc8-bd5a-2a4e0a9c3f14');
INSERT INTO `shop_list_buylog` VALUES ('9effd8a0-783d-4949-9abb-cfb1acd01ac3', 'd12f933f-451a-4ed1-a38a-b3fdf2162651', 'a6d89128-6109-46e9-bd36-314b5ccada1a');
INSERT INTO `shop_list_buylog` VALUES ('a7170e6a-a6cd-49b4-a7fa-165531bc42b2', 'b163b1a4-b5f5-4e89-89d0-01fbbdb6ab98', '41fde0b2-daf6-4205-80da-39a2b0941c68');
INSERT INTO `shop_list_buylog` VALUES ('ad8b5b5a-f7ec-4ec8-b0fa-fab65dd92451', 'f73c71b0-7510-44d3-9c60-4a5fb8e4c267', '6eae9824-780f-4c8e-a94a-17514029ff0f');
INSERT INTO `shop_list_buylog` VALUES ('c2309b8d-3c2d-461e-97af-5b39acf6bfbe', '7bf15ed5-ad8b-4dbe-9cfe-7b016182ac4b', '21c8f4dc-c87a-48e9-9df6-8162b43381b7');
INSERT INTO `shop_list_buylog` VALUES ('c64aae19-7d14-42db-9e08-3a7d96d9a115', 'e114c584-b03a-4f15-91ae-06c97351816a', '7f3c7540-63e0-4c4e-b519-087071b86def');
INSERT INTO `shop_list_buylog` VALUES ('ca4c462e-5a50-42d2-a3f4-9874a2720be4', '75453ab8-b93e-4cb2-9313-ea84501fd2fc', '8a5419ae-ae39-4e1a-9ad0-3c5d04dbf9a3');
INSERT INTO `shop_list_buylog` VALUES ('cb6b72a8-4e26-4aee-afe6-fd4de4610bc2', '3fc9a1a2-2595-44e0-a4e4-96f358b0e5fd', 'aaf57aa9-55ec-4378-aaa1-c56ce90d2ee6');
INSERT INTO `shop_list_buylog` VALUES ('d03bd5f6-bef4-441f-969c-2c67eb38ab68', 'a85cc318-f58e-4add-8178-953ffe649373', 'cb1717dd-1f34-419b-9095-24ebd07e6c8c');
INSERT INTO `shop_list_buylog` VALUES ('d737d136-9f6a-4ec5-bb0e-946884b5225d', '2341ad63-dd46-45ed-90fb-5ff5ce0904f5', 'e4682f1b-c3fe-4924-8de3-1ea03f458e18');
INSERT INTO `shop_list_buylog` VALUES ('e96f1b13-1471-43a3-8eb9-f700f1b4d57b', '82a73a26-6602-49b5-a93c-222c102821d3', 'c21540c5-b6be-4a7e-bb95-acc3f12879d9');
INSERT INTO `shop_list_buylog` VALUES ('ed9c271a-9249-4081-a651-605e7dd63dea', 'dcfff5b0-001f-43a8-afd3-43ac517cb061', 'a8d6618a-448d-4b32-83dd-1cc8d56c84e8');

-- ----------------------------
-- Table structure for user_shop_list
-- ----------------------------
DROP TABLE IF EXISTS `user_shop_list`;
CREATE TABLE `user_shop_list` (
  `usId` varchar(50) NOT NULL COMMENT '一次购买记录主键',
  `usercount` varchar(20) NOT NULL COMMENT '用户账号',
  `is_status` int(11) NOT NULL COMMENT '购买的状态',
  `is_time` varchar(50) NOT NULL,
  `is_pay` int(11) DEFAULT NULL,
  `is_fahuo` int(11) DEFAULT NULL,
  `is_gain` int(11) DEFAULT NULL,
  `is_comment` int(11) DEFAULT NULL,
  `is_all` int(11) DEFAULT NULL,
  PRIMARY KEY (`usId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物记录';

-- ----------------------------
-- Records of user_shop_list
-- ----------------------------
INSERT INTO `user_shop_list` VALUES ('23756c70-a8a3-48cf-ac52-320b5457082c', 'xio', '1', '2021-11-22 17:14:53', '1', '0', '0', '0', null);
INSERT INTO `user_shop_list` VALUES ('2ee37ae8-ba70-4dc5-85a0-2570d17b356e', 'xio', '1', '2021-11-22 17:11:29', '1', '1', '1', '0', null);
INSERT INTO `user_shop_list` VALUES ('40d7d22e-031f-458e-93c2-e1430adb219d', 'admin', '1', '2021-11-27 15:51:16', '1', '0', '0', '0', null);
INSERT INTO `user_shop_list` VALUES ('4b358ca3-5a07-4204-8c4e-d52389688b8b', 'admin', '1', '2021-11-27 15:36:08', '1', '0', '0', '0', null);
INSERT INTO `user_shop_list` VALUES ('541c9b52-6715-4b7e-8ca6-185c0f5e4657', 'xio', '1', '2021-11-22 17:15:50', '1', '0', '0', '0', null);
INSERT INTO `user_shop_list` VALUES ('59eeae88-77fe-4e62-a993-44c7ca08a89e', 'xio', '1', '2021-11-22 17:43:55', '1', '0', '0', '0', null);
INSERT INTO `user_shop_list` VALUES ('6535a286-66d1-4844-8be8-79b4c1387867', 'admin', '1', '2021-11-24 17:56:16', '1', '1', '0', '0', null);
INSERT INTO `user_shop_list` VALUES ('70f56298-6b26-40d3-96fd-5f0819a6a954', 'admin', '1', '2021-11-25 17:15:40', '1', '1', '1', '0', null);
INSERT INTO `user_shop_list` VALUES ('85e7777e-fdcb-4968-aacc-8b15afabd3ab', 'xio', '1', '2021-11-25 09:10:14', '1', '0', '0', '0', null);
INSERT INTO `user_shop_list` VALUES ('a42b2876-7acd-43cf-a9e9-e938414decc7', 'admin', '1', '2021-11-27 16:10:50', '1', '1', '0', '0', null);
INSERT INTO `user_shop_list` VALUES ('a5364a22-7a6d-4302-b8f4-67238a3b5d83', 'xio', '1', '2021-11-23 15:01:11', '1', '0', '0', '0', null);
INSERT INTO `user_shop_list` VALUES ('aa56a609-033e-4740-8f71-2c689e7e3e61', 'xio', '1', '2021-11-22 17:00:13', '1', '1', '1', '0', null);
INSERT INTO `user_shop_list` VALUES ('acb380fd-6cc6-4f0c-b6c1-e2c75bff5545', 'admin', '1', '2021-11-27 18:25:13', '1', '1', '1', '0', null);
INSERT INTO `user_shop_list` VALUES ('bffcea77-5d7a-41ea-ba1b-60496703dfa7', 'xio', '1', '2021-11-23 17:44:10', '1', '1', '1', '0', null);
INSERT INTO `user_shop_list` VALUES ('d0f8e92c-9222-46cd-a5b3-fa82de340816', 'admin', '1', '2021-11-24 17:56:35', '1', '0', '0', '0', null);
INSERT INTO `user_shop_list` VALUES ('d29b0f25-b0d8-4ab2-9afa-1c2974e93120', 'admin', '1', '2021-11-27 19:13:12', '1', '0', '0', '0', '0');
INSERT INTO `user_shop_list` VALUES ('e62eae79-3ba1-4779-838e-efcff7df87b8', 'xio', '1', '2021-11-23 14:10:58', '1', '0', '0', '0', null);
INSERT INTO `user_shop_list` VALUES ('f2e0b3f5-20f7-4b57-bfad-037a906e54f6', 'xio', '1', '2021-11-23 10:23:35', '1', '0', '0', '0', null);
INSERT INTO `user_shop_list` VALUES ('f31f57d6-3a98-478c-a0db-22769d6c14af', 'xio', '1', '2021-11-23 15:27:57', '1', '0', '0', '0', null);

-- ----------------------------
-- Table structure for user_shop_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_shop_relation`;
CREATE TABLE `user_shop_relation` (
  `usrId` varchar(50) NOT NULL COMMENT '联合主键',
  `usercount` varchar(20) NOT NULL COMMENT '用户',
  `slId` varchar(50) NOT NULL COMMENT '临时购物单表',
  PRIMARY KEY (`usrId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='临时订单和购买商品表';

-- ----------------------------
-- Records of user_shop_relation
-- ----------------------------
INSERT INTO `user_shop_relation` VALUES ('08907311-56c0-46b2-99ad-b7887f6686c0', 'admin', 'c3a9e3f8-7c2e-40f9-8c98-3912db26ec75');
INSERT INTO `user_shop_relation` VALUES ('0dc56c35-ba3d-4c28-be8c-5f9e02eacb06', 'admin', '7f2e5bd0-70e6-456f-b8f4-ad366aa77862');
INSERT INTO `user_shop_relation` VALUES ('0facad55-8558-4f10-98a1-dc79e1070002', 'xio', '2341ad63-dd46-45ed-90fb-5ff5ce0904f5');
INSERT INTO `user_shop_relation` VALUES ('2bade8b8-4d39-4321-a4a9-16b91dcb4d3e', 'xio', 'bdb3a8f9-3077-4f36-9dce-c1ec4f708e37');
INSERT INTO `user_shop_relation` VALUES ('3516dbdd-b7bb-43bf-a40f-d2fef3953c15', 'admin', '720c2d9d-5f3b-40b6-ae23-447fee35e2f8');
INSERT INTO `user_shop_relation` VALUES ('3a32027e-2228-42a9-947d-a2f37f94f924', 'xio', '82a73a26-6602-49b5-a93c-222c102821d3');
INSERT INTO `user_shop_relation` VALUES ('3d289939-76a2-4ad3-9ca5-be1b42c69bb8', 'admin', '3fc9a1a2-2595-44e0-a4e4-96f358b0e5fd');
INSERT INTO `user_shop_relation` VALUES ('440a3bc7-eb14-450f-86ca-6f978a6bf691', 'xio', 'd49a572e-325f-47a3-b762-50f043711178');
INSERT INTO `user_shop_relation` VALUES ('478b0470-74d8-461e-91eb-3e82ff6b7f9b', 'xio', '7bf15ed5-ad8b-4dbe-9cfe-7b016182ac4b');
INSERT INTO `user_shop_relation` VALUES ('4a335908-3ace-440f-b52a-f766fbcd90e4', 'admin', 'd12f933f-451a-4ed1-a38a-b3fdf2162651');
INSERT INTO `user_shop_relation` VALUES ('4ab3cb40-b7d5-464f-8930-b90218335bc2', 'admin', 'cfcf461a-c4f1-420d-846c-9c4e52b63f70');
INSERT INTO `user_shop_relation` VALUES ('8381d149-21b1-4f77-aaf9-c8dfd4cb44f0', 'xio', 'e114c584-b03a-4f15-91ae-06c97351816a');
INSERT INTO `user_shop_relation` VALUES ('887224b2-c9cc-4639-a3c9-d34de0951e8c', 'xio', 'f218ee05-39ca-4ed8-8c73-0b7bfeb60357');
INSERT INTO `user_shop_relation` VALUES ('8db46211-ae9c-4a75-9174-62c8fdf5a889', 'admin', '5991c4cd-f1b2-4494-b6e2-dac333fb9a5b');
INSERT INTO `user_shop_relation` VALUES ('957d9d57-6283-4100-b92f-0de733a034eb', 'xio', 'acbc8d54-e97f-4b92-9909-17605a58152c');
INSERT INTO `user_shop_relation` VALUES ('a6179cfe-0b77-4a3a-961e-f52b037cc99d', 'admin', 'dab6a1fe-324f-4bfd-a6b4-f70b2e659f61');
INSERT INTO `user_shop_relation` VALUES ('a6477ddb-e303-46b8-b116-14cd246e1ece', 'xio', 'dcfff5b0-001f-43a8-afd3-43ac517cb061');
INSERT INTO `user_shop_relation` VALUES ('b1de0907-cbba-47a8-a192-534ed9c2be17', 'admin', 'd042d8d6-60fe-4413-8c57-2f1d96fda940');
INSERT INTO `user_shop_relation` VALUES ('b6856e31-1c9a-4fd8-8806-54d2eaa28854', 'admin', 'd12f933f-451a-4ed1-a38a-b3fdf2162651');
INSERT INTO `user_shop_relation` VALUES ('b72b1f7f-3d82-4e83-9ef5-9493de25dc81', 'xio', 'a9e579cc-ae9a-44dd-adea-cf9b4b1c8e5a');
INSERT INTO `user_shop_relation` VALUES ('c8871330-ca4f-4420-ae5e-a594970143b0', 'admin', '766a2cd0-a772-4756-8842-e0faaab7f100');
INSERT INTO `user_shop_relation` VALUES ('df93fc89-0457-4d32-ae96-346ce569f77b', 'xio', '75453ab8-b93e-4cb2-9313-ea84501fd2fc');
INSERT INTO `user_shop_relation` VALUES ('e093a781-0da0-496f-92af-bcdc979cbf9b', 'admin', 'a85cc318-f58e-4add-8178-953ffe649373');
INSERT INTO `user_shop_relation` VALUES ('f1a6c416-b14d-47dd-97ef-9cc7379ee3a3', 'xio', 'b163b1a4-b5f5-4e89-89d0-01fbbdb6ab98');
INSERT INTO `user_shop_relation` VALUES ('f64f159a-404e-45b9-8fae-45442691f985', 'admin', 'd12f933f-451a-4ed1-a38a-b3fdf2162651');
INSERT INTO `user_shop_relation` VALUES ('f78980eb-b09a-44c7-ad40-2284e829dd10', 'admin', '65feed96-25b9-4dbe-ae6b-840480a6f53e');
INSERT INTO `user_shop_relation` VALUES ('fe97cc30-61fd-4dc5-b745-e39f3562a43f', 'xio', '409bfe5c-1874-4de4-bbe0-2fc2fb6f8232');

-- ----------------------------
-- Table structure for wallet
-- ----------------------------
DROP TABLE IF EXISTS `wallet`;
CREATE TABLE `wallet` (
  `wId` varchar(50) NOT NULL COMMENT '主键',
  `balance` double DEFAULT NULL COMMENT '余额',
  `usercount` varchar(50) NOT NULL COMMENT '用户',
  PRIMARY KEY (`wId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='钱包表';

-- ----------------------------
-- Records of wallet
-- ----------------------------
INSERT INTO `wallet` VALUES ('7f3ce1b3-3941-44de-8d6c-265b59e31c9e', '1247.1799999999998', 'admin');
INSERT INTO `wallet` VALUES ('W001', '378.51000000000147', 'xio');
