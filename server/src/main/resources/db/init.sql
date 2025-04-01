-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: galaxy
-- ------------------------------------------------------
-- Server version	8.0.41
use galaxy;
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `apdungvoucher`
--

DROP TABLE IF EXISTS `apdungvoucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apdungvoucher` (
  `MaApDung` int NOT NULL AUTO_INCREMENT,
  `MaHoaDon` int NOT NULL,
  `MaVoucher` int NOT NULL,
  PRIMARY KEY (`MaApDung`),
  KEY `MaHoaDon` (`MaHoaDon`),
  KEY `MaVoucher` (`MaVoucher`),
  CONSTRAINT `apdungvoucher_ibfk_1` FOREIGN KEY (`MaHoaDon`) REFERENCES `hoadon` (`MaHoaDon`),
  CONSTRAINT `apdungvoucher_ibfk_2` FOREIGN KEY (`MaVoucher`) REFERENCES `voucher` (`MaVoucher`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apdungvoucher`
--

LOCK TABLES `apdungvoucher` WRITE;
/*!40000 ALTER TABLE `apdungvoucher` DISABLE KEYS */;
/*!40000 ALTER TABLE `apdungvoucher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `combodoan`
--

DROP TABLE IF EXISTS `combodoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `combodoan` (
  `MaCombo` int NOT NULL AUTO_INCREMENT,
  `TenCombo` varchar(100) NOT NULL,
  `GiaCombo` decimal(10,2) NOT NULL,
  `MoTa` text,
  PRIMARY KEY (`MaCombo`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `combodoan`
--

LOCK TABLES `combodoan` WRITE;
/*!40000 ALTER TABLE `combodoan` DISABLE KEYS */;
INSERT INTO `combodoan` VALUES (1,'Combo 1 small',76000.00,'1 bắp rang + 1 nước 22 oz'),(2,'Combo 1 big',79000.00,'1 bắp rang + 1 nước 32 oz'),(3,'Combo 2 big',92000.00,'1 bắp rang + 2 nước 32 oz'),(4,'Combo family 1 snack',129000.00,'1 bắp rang + 3 nước 32 oz + snack'),(5,'Combo family 2 snack',179000.00,'2 bắp rang + 4 nước 32 oz + snack'),(6,'Combo family 2 food',179000.00,'2 bắp rang + 4 nước 32 oz + food');
/*!40000 ALTER TABLE `combodoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `danhgia`
--

DROP TABLE IF EXISTS `danhgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `danhgia` (
  `MaDanhGia` int NOT NULL AUTO_INCREMENT,
  `MaKhach` int NOT NULL,
  `MaPhim` int NOT NULL,
  `DiemDanhGia` float NOT NULL,
  `NgayDanhGia` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `BinhLuan` varchar(80) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`MaDanhGia`),
  KEY `MaKhach` (`MaKhach`),
  KEY `MaPhim` (`MaPhim`),
  CONSTRAINT `danhgia_ibfk_1` FOREIGN KEY (`MaKhach`) REFERENCES `khachhang` (`MaKhachHang`) ON DELETE CASCADE,
  CONSTRAINT `danhgia_ibfk_2` FOREIGN KEY (`MaPhim`) REFERENCES `phim` (`MaPhim`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `danhgia`
--

LOCK TABLES `danhgia` WRITE;
/*!40000 ALTER TABLE `danhgia` DISABLE KEYS */;
INSERT INTO `danhgia` VALUES (4,2,2,4,'2024-10-02 02:00:00',NULL),(5,2,4,2,'2024-10-02 03:00:00',NULL),(6,2,6,3,'2024-10-02 04:00:00',NULL),(10,4,3,4,'2024-10-04 07:00:00','10 deim'),(11,4,5,3,'2024-10-04 08:00:00',NULL),(12,4,8,2,'2024-10-04 09:00:00','phim dang mong doi'),(13,5,2,3,'2024-10-05 05:00:00',NULL),(14,5,4,4,'2024-10-05 06:00:00',NULL),(15,5,6,3,'2024-10-05 07:00:00','hay lam'),(17,2,8,3,'2024-10-06 04:00:00',NULL),(19,4,9,2,'2024-10-06 06:00:00',NULL),(20,5,10,4,'2024-10-06 07:00:00',NULL),(22,13,1,1,NULL,'comment'),(23,13,1,5,NULL,'hi'),(24,13,1,1,NULL,'test'),(25,13,1,4,'2025-03-15 17:00:00','test2'),(26,13,2,4,'2025-03-15 17:00:00','phim hay ');
/*!40000 ALTER TABLE `danhgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datdoan`
--

DROP TABLE IF EXISTS `datdoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datdoan` (
  `MaHoaDon` int NOT NULL,
  `MaDoAn` int DEFAULT NULL,
  `SoLuong` int NOT NULL,
  `MaCombo` int DEFAULT NULL,
  `MaDatDoAn` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`MaDatDoAn`),
  KEY `datdoan_ibfk_1` (`MaDoAn`),
  KEY `datdoan_ibfk_2` (`MaHoaDon`),
  KEY `datdoan_ibfk_3_idx` (`MaCombo`),
  CONSTRAINT `datdoan_ibfk_1` FOREIGN KEY (`MaDoAn`) REFERENCES `doan` (`MaDoAn`),
  CONSTRAINT `datdoan_ibfk_2` FOREIGN KEY (`MaHoaDon`) REFERENCES `hoadon` (`MaHoaDon`),
  CONSTRAINT `datdoan_ibfk_3` FOREIGN KEY (`MaCombo`) REFERENCES `combodoan` (`MaCombo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datdoan`
--

LOCK TABLES `datdoan` WRITE;
/*!40000 ALTER TABLE `datdoan` DISABLE KEYS */;
INSERT INTO `datdoan` VALUES (19,NULL,1,4,5),(19,NULL,2,5,6);
/*!40000 ALTER TABLE `datdoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doan`
--

DROP TABLE IF EXISTS `doan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doan` (
  `MaDoAn` int NOT NULL AUTO_INCREMENT,
  `Ten` varchar(255) NOT NULL,
  `ChiTiet` text,
  `Gia` decimal(10,2) NOT NULL,
  `LoaiDoAn` enum('snack','drink','fastfood') NOT NULL,
  `HangTon` int DEFAULT '0',
  `TrangThaiCoSan` tinyint(1) DEFAULT '1',
  `NgayTao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `NgayCapNhat` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `HinhAnh` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`MaDoAn`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doan`
--

LOCK TABLES `doan` WRITE;
/*!40000 ALTER TABLE `doan` DISABLE KEYS */;
INSERT INTO `doan` VALUES (41,'Bắp rang bơ','Bắp rang bơ thơm ngon',5.99,'snack',100,1,'2025-01-27 05:30:18','2025-01-27 05:30:18',NULL),(42,'Coca-Cola','Nước ngọt Coca-Cola',2.99,'drink',200,1,'2025-01-27 05:30:18','2025-01-27 05:30:18',NULL),(43,'xuc xich','1 bắp rang bơ và 1 Coca-Cola',7.99,'fastfood',50,1,'2025-01-27 05:30:18','2025-01-27 05:30:18',NULL),(44,'Hotdog','Xúc xích kẹp bánh mì',4.99,'fastfood',80,1,'2025-01-27 05:30:18','2025-01-27 05:30:18',NULL),(45,'Pepsi','Nước ngọt Pepsi',2.99,'drink',150,1,'2025-01-27 05:30:18','2025-01-27 05:30:18',NULL),(46,'ga vien','1 bắp rang bơ và 1 hotdog',9.99,'fastfood',30,1,'2025-01-27 05:30:18','2025-01-27 05:30:18',NULL),(47,'Khoai tây chiên','Khoai tây chiên giòn',3.99,'snack',120,1,'2025-01-27 05:30:18','2025-01-27 05:30:18',NULL),(48,'Sprite','Nước ngọt Sprite',2.99,'drink',180,1,'2025-01-27 05:30:18','2025-01-27 05:30:18',NULL),(49,'khoai tây xoan oc','1 khoai tây chiên và 1 Sprite',6.99,'fastfood',40,1,'2025-01-27 05:30:18','2025-01-27 05:30:18',NULL),(50,'Bánh mì','Bánh mì kẹp thịt bò',6.99,'snack',90,1,'2025-01-27 05:30:18','2025-01-27 05:30:18',NULL);
/*!40000 ALTER TABLE `doan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon` (
  `MaHoaDon` int NOT NULL AUTO_INCREMENT,
  `MaKhachHang` int DEFAULT NULL,
  `TongTien` decimal(10,2) NOT NULL,
  `NgayXuatHoaDon` datetime DEFAULT CURRENT_TIMESTAMP,
  `MaNhanVien` int DEFAULT NULL,
  `TrangThai` varchar(20) NOT NULL DEFAULT 'PENDING',
  PRIMARY KEY (`MaHoaDon`),
  KEY `hoadon_ibfk_3_idx` (`MaNhanVien`),
  CONSTRAINT `hoadon_ibfk_3` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
INSERT INTO `hoadon` VALUES (1,1,75000.00,'2025-01-01 10:15:00',NULL,'PENDING'),(2,2,80000.00,'2025-01-01 11:15:00',NULL,'PENDING'),(3,3,120000.00,'2025-01-02 13:15:00',NULL,'PENDING'),(4,4,170000.00,'2025-01-03 15:30:00',NULL,'PENDING'),(5,5,70000.00,'2025-01-04 17:30:00',NULL,'PENDING'),(6,6,95000.00,'2025-01-05 19:30:00',NULL,'PENDING'),(7,7,80000.00,'2025-01-06 21:00:00',NULL,'PENDING'),(8,8,60000.00,'2025-01-07 22:00:00',NULL,'PENDING'),(9,9,65000.00,'2025-01-08 22:30:00',NULL,'PENDING'),(19,1,637000.00,'2025-03-25 19:52:28',NULL,'PAID');
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khachhang` (
  `MaKhachHang` int NOT NULL AUTO_INCREMENT,
  `HoTen` varchar(100) NOT NULL,
  `SoDienThoai` varchar(15) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `NgayDangKy` date DEFAULT NULL,
  `Diem` decimal(10,2) DEFAULT NULL,
  `MatKhau` varchar(255) NOT NULL,
  PRIMARY KEY (`MaKhachHang`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES (2,'Tran Van Bao','0913000002','tvb@example.com','2024-01-12',NULL,''),(4,'Pham Van Dat','0913000004','pvd@example.com','2024-01-18',NULL,''),(5,'Hoang Thi Nga','0913000005','htn@example.com','2024-01-20',NULL,''),(6,'Nguyen Van Hai','0913000006','nvh@example.com','2024-01-22',NULL,''),(7,'Vu Thi Hang','0913000008','vth1@example.com','2024-01-25',NULL,''),(8,'Pham Minh Duan','0913000008','vth1@example.com','2024-01-27',NULL,''),(9,'Le Van Tam','0913000009','lvt@example.com','2024-01-30',NULL,''),(10,'Tran Thi Linh','0913000010','ttl@example.com','2024-02-01',NULL,''),(11,'admin','0913000010','admin',NULL,NULL,''),(13,'test','09130000012','test@example.com',NULL,NULL,'$2a$10$W89Mn.5VXJ70QtGqEN2DQe6PlCd/TDv7/.OZIbKfYtHCo5yEbdGHS'),(16,'nguyen van b','0985029721','duannguyen123@gmail.com',NULL,NULL,'$2a$10$IUglCZr4D4gQo4k2nA/7geodaTuxguj8MnasbmqzKci9Kg2VGU.nq');
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `MaNhanVien` int NOT NULL AUTO_INCREMENT,
  `HoTen` varchar(100) NOT NULL,
  `ChucVu` varchar(50) DEFAULT NULL,
  `SoDienThoai` varchar(15) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `DiaChi` text,
  PRIMARY KEY (`MaNhanVien`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (1,'Nguyen Van A','Quản lý','0912345678','nva@example.com','1985-05-01','Hà Nội'),(2,'Le Thi B','Nhân viên bán vé','0912345679','ltb@example.com','1990-03-15','Hải Phòng'),(3,'Tran Van C','Nhân viên kỹ thuật','0912345680','tvc@example.com','1992-07-20','Đà Nẵng'),(4,'Pham Thi D','Nhân viên vệ sinh','0912345681','ptd@example.com','1988-12-01','Hồ Chí Minh'),(5,'Hoang Van E','Nhân viên bảo vệ','0912345682','hve@example.com','1995-11-05','Hà Nội'),(6,'Nguyen Thi F','Nhân viên bán vé','0912345683','ntf@example.com','1991-01-15','Huế'),(7,'Vu Van G','Nhân viên kỹ thuật','0912345684','vvg@example.com','1987-06-11','Quảng Ninh'),(8,'Bui Thi H','Nhân viên vệ sinh','0912345685','bth@example.com','1993-08-19','Hải Dương'),(9,'Pham Van I','Quản lý','0912345686','pvi@example.com','1982-04-25','Thanh Hóa'),(10,'Do Thi K','Nhân viên bán vé','0912345687','dtk@example.com','1994-09-09','Cần Thơ');
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phim`
--

DROP TABLE IF EXISTS `phim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phim` (
  `MaPhim` int NOT NULL AUTO_INCREMENT,
  `TenPhim` varchar(200) NOT NULL,
  `ThoiLuong` int NOT NULL,
  `MoTa` text,
  `DaoDien` varchar(100) DEFAULT NULL,
  `DienVienChinh` varchar(200) DEFAULT NULL,
  `TheLoai` varchar(100) DEFAULT NULL,
  `NgayKhoiChieu` date DEFAULT NULL,
  `NgayKetThuc` date DEFAULT NULL,
  `HinhAnh` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `HoatDong` int DEFAULT NULL,
  `Link` varchar(100) DEFAULT NULL,
  `DoTuoi` int DEFAULT NULL,
  `QuocGia` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`MaPhim`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phim`
--

LOCK TABLES `phim` WRITE;
/*!40000 ALTER TABLE `phim` DISABLE KEYS */;
INSERT INTO `phim` VALUES (1,'avenger',180,'Siêu anh hùng chiến đấu với Thanos.','Anthony Russo','Robert Downey Jr.','Hành động','2025-01-01','2025-03-01','images/films/1739088346009_91syHT466TL.jpg',1,NULL,18,'Mỹ'),(2,'Titanic',199,'Tàu Titanic bị đắm.','James Cameron','Leonardo DiCaprio','Tình cảm','2025-01-05','2025-03-05','images/films/1736265354343_titanic.png',1,NULL,18,'Mỹ'),(3,'Captain America',150,' Sau khi gặp Tổng thống Hoa Kỳ cùng đặc nhiệm Thaddeus Ross, Sam phải đối mặt với một sự cố quy mô quốc tế... ','Christopher Nolan','Leonardo DiCaprio','Khoa học viễn tưởng','2025-01-10','2025-03-10','images/films/1734525432543_captain.png',1,NULL,18,'Mỹ'),(4,'Joker',120,'Cuộc đời của Joker.','Todd Phillips','Joaquin Phoenix','Tâm lý','2025-01-15','2025-03-15','images/films/1739090116894_joker.jpg',1,NULL,18,'Mỹ'),(5,'Parasite',132,'Sự bất bình đẳng trong xã hội.','Bong Joon-ho','Song Kang-ho','Tâm lý','2025-01-20','2025-03-20','images/films/1743170516790_para.png',1,NULL,16,'Hàn'),(6,'Avatar',162,'Thế giới Pandora.','James Cameron','Sam Worthington','Khoa học viễn tưởng','2025-01-25','2025-03-25','images/films/1739090127346_avatar2.jpg',1,NULL,16,'Mỹ'),(7,'Frozen 2',103,'Elsa và Anna khám phá bí mật.','Chris Buck','Kristen Bell','Hoạt hình','2025-02-01','2025-04-01','images/films/1743172242243_frozen.png',1,NULL,18,'Mỹ'),(8,'The Lion King',118,'Cuộc đời của Simba.','Jon Favreau','Donald Glover','Hoạt hình','2025-02-05','2025-04-05','images/films/1743170766622_lion.png',1,NULL,18,'Mỹ'),(9,'Interstellar',169,'Du hành không gian và thời gian.','Christopher Nolan','Matthew McConaughey','Khoa học viễn tưởng','2025-02-10','2025-04-10','images/films/1743160365235_inter.png',1,NULL,16,'Mỹ'),(10,'Transformers',150,'Hành trình của Harry Potter.','Johan Chua','Daniel Radcliffe','Phiêu lưu','2025-02-15','2025-04-15','images/films/1743160316729_trans.png',0,NULL,12,'Mỹ');
/*!40000 ALTER TABLE `phim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suatchieu`
--

DROP TABLE IF EXISTS `suatchieu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suatchieu` (
  `MaSuatChieu` int NOT NULL AUTO_INCREMENT,
  `MaPhim` int NOT NULL,
  `PhongChieu` int NOT NULL,
  `ThoiGianBatDau` datetime NOT NULL,
  `GiaVe` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`MaSuatChieu`),
  KEY `MaPhim` (`MaPhim`),
  CONSTRAINT `suatchieu_ibfk_1` FOREIGN KEY (`MaPhim`) REFERENCES `phim` (`MaPhim`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suatchieu`
--

LOCK TABLES `suatchieu` WRITE;
/*!40000 ALTER TABLE `suatchieu` DISABLE KEYS */;
INSERT INTO `suatchieu` VALUES (3,2,3,'2025-01-05 15:00:00',100000.00),(4,3,4,'2025-01-10 17:00:00',120000.00),(5,4,5,'2025-01-15 19:00:00',90000.00),(6,5,6,'2025-01-20 09:00:00',85000.00),(7,6,7,'2025-01-25 14:00:00',110000.00),(8,7,8,'2025-02-01 18:00:00',70000.00),(9,8,9,'2025-02-05 20:00:00',75000.00),(10,9,10,'2025-02-10 22:00:00',130000.00),(11,1,3,'2025-01-02 21:00:00',30000.00),(12,1,3,'2025-03-17 16:26:00',NULL),(13,1,3,'2025-03-22 19:06:00',NULL),(14,1,3,'2025-03-22 22:11:00',NULL),(15,1,2,'2025-03-22 09:11:00',NULL),(16,2,5,'2025-03-22 19:12:00',NULL),(17,3,1,'2025-03-20 22:13:00',NULL),(18,1,1,'2025-03-21 13:26:00',NULL),(19,1,2,'2025-03-25 18:47:00',NULL),(20,1,3,'2025-03-27 12:44:00',NULL),(21,4,3,'2025-04-01 18:29:00',NULL);
/*!40000 ALTER TABLE `suatchieu` ENABLE KEYS */;

INSERT INTO `suatchieu` (`MaPhim`, `PhongChieu`, `ThoiGianBatDau`, `GiaVe`) VALUES
-- Ngày hôm nay
(1, 1, NOW(), 120000),
(2, 2, DATE_ADD(NOW(), INTERVAL 3 HOUR), 100000),
(3, 3, DATE_ADD(NOW(), INTERVAL 6 HOUR), 150000),
-- Ngày mai
(1, 4, DATE_ADD(NOW(), INTERVAL 1 DAY), 120000),
(2, 1,DATE_ADD(DATE_ADD(NOW(), INTERVAL 1 DAY), INTERVAL 3 HOUR), 100000),
(3, 2, DATE_ADD(DATE_ADD(NOW(), INTERVAL 1 DAY), INTERVAL 3 HOUR), 150000),


-- Ngày kia
(1, 3, DATE_ADD(NOW(), INTERVAL 2 DAY), 120000),
(2, 4, DATE_ADD(DATE_ADD(NOW(), INTERVAL 1 DAY), INTERVAL 3 HOUR),100000),
(3, 1, DATE_ADD(DATE_ADD(NOW(), INTERVAL 1 DAY), INTERVAL 3 HOUR),150000),

-- 3 ngày sau
(1, 2, DATE_ADD(NOW(), INTERVAL 3 DAY), 120000),
(2, 3, DATE_ADD(DATE_ADD(NOW(), INTERVAL 1 DAY), INTERVAL 3 HOUR), 100000),
(3, 4, DATE_ADD(DATE_ADD(NOW(), INTERVAL 1 DAY), INTERVAL 3 HOUR),150000),

-- 4 ngày sau
(1, 1, DATE_ADD(NOW(), INTERVAL 4 DAY), 120000),
(2, 2, DATE_ADD(DATE_ADD(NOW(), INTERVAL 1 DAY), INTERVAL 3 HOUR), 100000),
(3, 3, DATE_ADD(DATE_ADD(NOW(), INTERVAL 1 DAY), INTERVAL 3 HOUR), 150000),

-- 5 ngày sau
(1, 4, DATE_ADD(NOW(), INTERVAL 5 DAY), 120000),
(2, 1, DATE_ADD(DATE_ADD(NOW(), INTERVAL 1 DAY), INTERVAL 3 HOUR), 100000),
(3, 2, DATE_ADD(DATE_ADD(NOW(), INTERVAL 1 DAY), INTERVAL 3 HOUR), 150000),

-- 6 ngày sau
(1, 3, DATE_ADD(DATE_ADD(NOW(), INTERVAL 1 DAY), INTERVAL 3 HOUR), 120000),
(2, 4, DATE_ADD(DATE_ADD(NOW(), INTERVAL 1 DAY), INTERVAL 3 HOUR),  100000),
(3, 1, DATE_ADD(DATE_ADD(NOW(), INTERVAL 1 DAY), INTERVAL 3 HOUR),  150000);

--
UNLOCK TABLES;

--
-- Table structure for table `vexemphim`
--

DROP TABLE IF EXISTS `vexemphim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vexemphim` (
  `MaVe` int NOT NULL AUTO_INCREMENT,
  `MaSuatChieu` int NOT NULL,
  `MaKhachHang` int DEFAULT NULL,
  `SoGhe` varchar(10) NOT NULL,
  `GiaVe` decimal(10,2) NOT NULL,
  `NgayMua` datetime DEFAULT CURRENT_TIMESTAMP,
  `LoaiVe` varchar(45) DEFAULT NULL,
  `MaHoaDon` int DEFAULT NULL,
  PRIMARY KEY (`MaVe`),
  KEY `MaSuatChieu` (`MaSuatChieu`),
  KEY `MaKhachHang` (`MaKhachHang`),
  KEY `vexemphim_ibfk_3_idx` (`MaHoaDon`),
  CONSTRAINT `vexemphim_ibfk_1` FOREIGN KEY (`MaSuatChieu`) REFERENCES `suatchieu` (`MaSuatChieu`),
  CONSTRAINT `vexemphim_ibfk_2` FOREIGN KEY (`MaKhachHang`) REFERENCES `khachhang` (`MaKhachHang`),
  CONSTRAINT `vexemphim_ibfk_3` FOREIGN KEY (`MaHoaDon`) REFERENCES `hoadon` (`MaHoaDon`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vexemphim`
--

LOCK TABLES `vexemphim` WRITE;
/*!40000 ALTER TABLE `vexemphim` DISABLE KEYS */;
INSERT INTO `vexemphim` VALUES (14,4,4,'D4',85000.00,'2025-01-03 15:00:00','u22',NULL),(15,5,5,'E5',70000.00,'2025-01-04 17:00:00','u22',NULL),(16,6,6,'F6',75000.00,'2025-01-05 19:00:00','u22',NULL),(17,7,7,'G7',80000.00,'2025-01-06 20:30:00','u22',NULL),(18,8,8,'H8',60000.00,'2025-01-07 21:00:00','u22',NULL),(19,9,9,'I9',65000.00,'2025-01-08 22:00:00','u22',NULL),(20,10,10,'J10',95000.00,'2025-01-09 23:00:00','u22',NULL),(31,20,NULL,'B7',0.00,NULL,'ADULT',19),(32,20,NULL,'B8',0.00,NULL,'ADULT',19);
/*!40000 ALTER TABLE `vexemphim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voucher`
--

DROP TABLE IF EXISTS `voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voucher` (
  `MaVoucher` int NOT NULL AUTO_INCREMENT,
  `TenVoucher` varchar(100) NOT NULL,
  `LoaiVoucher` enum('GiamGia','MienPhi') NOT NULL,
  `GiaTri` decimal(10,2) DEFAULT NULL,
  `NgayBatDau` date DEFAULT NULL,
  `NgayKetThuc` date DEFAULT NULL,
  `DieuKienApDung` text,
  PRIMARY KEY (`MaVoucher`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher`
--

LOCK TABLES `voucher` WRITE;
/*!40000 ALTER TABLE `voucher` DISABLE KEYS */;
INSERT INTO `voucher` VALUES (1,'Voucher 10%','GiamGia',10.00,'2025-01-01','2025-01-31','Áp dụng cho vé xem phim'),(2,'Voucher 20%','GiamGia',20.00,'2025-02-01','2025-02-28','Áp dụng cho combo đồ ăn'),(3,'Voucher Free Vé','MienPhi',NULL,'2025-01-15','2025-01-20','Tặng miễn phí 1 vé xem phim'),(4,'Voucher 30%','GiamGia',30.00,'2025-02-10','2025-03-01','Áp dụng cho vé xem phim'),(5,'Voucher 50%','GiamGia',50.00,'2025-02-20','2025-02-25','Áp dụng cho combo đồ ăn'),(6,'Voucher Free Combo','MienPhi',NULL,'2025-03-01','2025-03-10','Miễn phí combo nhỏ'),(7,'Voucher Tết 2025','GiamGia',15.00,'2025-01-20','2025-01-30','Áp dụng cho tất cả dịch vụ'),(8,'Voucher Valentine','GiamGia',25.00,'2025-02-14','2025-02-14','Áp dụng cho cặp đôi'),(9,'Voucher Khách VIP','MienPhi',NULL,'2025-02-01','2025-02-28','Chỉ dành cho khách hàng VIP'),(10,'Voucher Giảm 5%','GiamGia',5.00,'2025-01-01','2025-01-31','Không áp dụng với vé VIP');
/*!40000 ALTER TABLE `voucher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-01 15:27:25




