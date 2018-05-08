/*
SQLyog Community v12.12 (64 bit)
MySQL - 5.6.24 : Database - digitour
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`digitour` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `digitour`;

/*Table structure for table `migration` */

DROP TABLE IF EXISTS `migration`;

CREATE TABLE `migration` (
  `version` varchar(180) NOT NULL,
  `apply_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`version`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `migration` */

insert  into `migration`(`version`,`apply_time`) values ('m000000_000000_base',1491557849),('m130524_201442_init',1491557853);

/*Table structure for table `t_about` */

DROP TABLE IF EXISTS `t_about`;

CREATE TABLE `t_about` (
  `about_id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `nim` int(12) DEFAULT NULL,
  `prodi` varchar(50) DEFAULT NULL,
  `path_gambar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`about_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `t_about` */

insert  into `t_about`(`about_id`,`name`,`nim`,`prodi`,`path_gambar`) values (1,'Sandy S. Ambarita',11414001,'D43TI','sandy.png'),(2,'Reka Panggabean',11414005,'D43TI','reka.jpg'),(3,'Kemas Rouf',11414026,'D43TI','kemas.png'),(4,'Monalisa Pasaribu',11414020,'D43TI','monalisa.jpg');

/*Table structure for table `t_achievement` */

DROP TABLE IF EXISTS `t_achievement`;

CREATE TABLE `t_achievement` (
  `achievement_id` int(12) NOT NULL AUTO_INCREMENT,
  `challenge_type_id` int(12) DEFAULT NULL,
  `checkpoint_id` int(12) DEFAULT NULL,
  `hadiah` varchar(30) DEFAULT NULL,
  `path_gambar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`achievement_id`),
  KEY `kk` (`checkpoint_id`),
  KEY `tt` (`challenge_type_id`),
  CONSTRAINT `kk` FOREIGN KEY (`checkpoint_id`) REFERENCES `t_checkpoint` (`checkpoint_id`),
  CONSTRAINT `tt` FOREIGN KEY (`challenge_type_id`) REFERENCES `t_challenge_type` (`challenge_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `t_achievement` */

insert  into `t_achievement`(`achievement_id`,`challenge_type_id`,`checkpoint_id`,`hadiah`,`path_gambar`) values (1,1,1,'S.Tr.Kom','achiev.png'),(2,1,2,'Pangeran Balige','achiev.png'),(3,1,3,'Wakil Raja','achiev.png'),(4,1,4,'Marga Siallagan','achiev.png'),(5,2,1,'AMd','achiev.png');

/*Table structure for table `t_achievement_user` */

DROP TABLE IF EXISTS `t_achievement_user`;

CREATE TABLE `t_achievement_user` (
  `achievement_user_id` int(12) NOT NULL AUTO_INCREMENT,
  `challenge_type_name` varchar(64) DEFAULT NULL,
  `user` varchar(30) DEFAULT NULL,
  `checkpoint_name` varchar(64) DEFAULT NULL,
  `hadiah` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`achievement_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=latin1;

/*Data for the table `t_achievement_user` */

insert  into `t_achievement_user`(`achievement_user_id`,`challenge_type_name`,`user`,`checkpoint_name`,`hadiah`) values (55,'True False','Monalisa Pasaribu','ITDel','AMd'),(56,'True False','Monalisa Pasaribu','ITDel','AMd');

/*Table structure for table `t_challenge_type` */

DROP TABLE IF EXISTS `t_challenge_type`;

CREATE TABLE `t_challenge_type` (
  `challenge_type_id` int(12) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(30) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`challenge_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `t_challenge_type` */

insert  into `t_challenge_type`(`challenge_type_id`,`type_name`,`description`) values (1,'Pilihan Ganda','Ini pilihan ganda'),(2,'True False','Ini true false'),(3,'Augmented Reality','Ini Ar');

/*Table structure for table `t_checkpoint` */

DROP TABLE IF EXISTS `t_checkpoint`;

CREATE TABLE `t_checkpoint` (
  `checkpoint_id` int(12) NOT NULL AUTO_INCREMENT,
  `location_id` int(12) DEFAULT NULL,
  `checkpoint_name` varchar(255) DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `path_gambarpoint` varchar(255) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`checkpoint_id`),
  KEY `location_id` (`location_id`),
  CONSTRAINT `t_checkpoint_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `t_location` (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `t_checkpoint` */

insert  into `t_checkpoint`(`checkpoint_id`,`location_id`,`checkpoint_name`,`latitude`,`longitude`,`path_gambarpoint`,`description`) values (1,1,'ITDel',2.38582,99.148,'itdel.jpg','Institut teknologi del adalah sebuah perguruan tinggi yang terletak di laguboti.\r\nDel terkenal dengan asramanya yang membuat disiplin mahasiswa/i yang kuliah di tempat tersebut.\r\n\r\n'),(2,1,'TB Silalahi',2.3337,99.0485,'tbsilalahi.png','Museum Pribadi TB. Silalahi atau yang diberi nama Museum Jejak Langkah dan Sejarah TB. Silalahi yang dibangun sebagai wadah untuk memotivasi generasi muda untuk terus meraih cita-cita dengan melihat pengalaman TB. Silalahi mulai dari kecil sebagai anak pengembala kerbau sampai menjadi seorang Jenderal.\r\nMuseum TB. Silalahi adalah yayasan nirlaba yang didirikan oleh Letjen TNI (Purn) Dr. Tiopan Bernhard Silalahi. Yayasan ini didirikan dengan tujuan untuk melestarikan budaya Batak dan membentuk karakter masyarakat Batak. Yayasan ini dirikan berdasarkan akta nomor 10 tanggal 7 Agustus 2006 dan akta nomor 7 tanggal 8 Januari 2007, dan TB. Silalahi pernah menjadi Menteri PAN 1993-1998.'),(3,2,'Kuburan Raja Sidabutar',2.6513,98.8607,'makamsidabutar.jpg','Sebelum masuk ke lokasi makam pengunjung diharuskan memakai kain ulos (Kain adat Batak Toba), dan anda akan ditemani seorang pemandu wisata yang akan menemani anda selama di situs tersebut sambil menceritakan sejarah raja-raja yang di kuburkan di area makam ini.\r\n\r\nDidalam kepercayaan masyarkat Batak, kesaktian Raja Sidabutar terdapat pada rambut gimbalnya, rambut tidak boleh dipotong, kalau rambut itu lepas, bukan di buang tapi diikat diujung tombak, namanya Tunggal Pangaluan dan disimpan dalam Museum Rumah Batak.\r\n\r\nDiatas makam, anda akan melihat melihat sebuah selendang dengan warna putih, merah dan hitam. Ketiga warna menjadi simbol spiritual. Putih, warna surga (kesucian). Merah, warna bumi (ketenangan). Hitam, warna bawah tanah (kematian).'),(4,3,'Batu kursi',2.67911,98.8364,'batukursi.jpg','Objek wisata baru Batu Kursi  atau yang biasa disebut dengan Batu Parsidangan adalah salah satu tempat wisata yang ada di sekitar rumah tinggalku di Pulau Samosir, tepatnya di Desa Siallagan, Sumatera Utara. Aku menghabiskan masa kecilku di kampung ini. Batu Kursi Siallagan menjadi salah satu destinasi wisata yang wajib kamu kunjungi jika berkunjung ke Pulau Samosir. Objek wisata ini sendiri erat kaitannya dengan sejarah Kampung Siallagan.');

/*Table structure for table `t_leaderboard` */

DROP TABLE IF EXISTS `t_leaderboard`;

CREATE TABLE `t_leaderboard` (
  `leaderboard_id` int(12) NOT NULL AUTO_INCREMENT,
  `challenge_type_name` varchar(64) DEFAULT NULL,
  `checkpoint_name` varchar(64) DEFAULT NULL,
  `user` varchar(30) DEFAULT NULL,
  `score` int(30) DEFAULT NULL,
  `date_time` varchar(255) DEFAULT NULL,
  KEY `leaderboard_id` (`leaderboard_id`)
) ENGINE=InnoDB AUTO_INCREMENT=192 DEFAULT CHARSET=latin1;

/*Data for the table `t_leaderboard` */

insert  into `t_leaderboard`(`leaderboard_id`,`challenge_type_name`,`checkpoint_name`,`user`,`score`,`date_time`) values (189,'True False','ITDel','Monalisa Pasaribu',60,'2017-06-14 19:41:29'),(190,'True False','ITDel','Monalisa Pasaribu',80,'2017-06-14 19:41:52'),(191,'True False','ITDel','Monalisa Pasaribu',100,'2017-06-14 19:42:13');

/*Table structure for table `t_location` */

DROP TABLE IF EXISTS `t_location`;

CREATE TABLE `t_location` (
  `location_id` int(12) NOT NULL AUTO_INCREMENT,
  `location_name` varchar(30) DEFAULT NULL,
  `city_name` varchar(30) DEFAULT NULL,
  `path_gambar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `t_location` */

insert  into `t_location`(`location_id`,`location_name`,`city_name`,`path_gambar`) values (1,'Balige','Toba','balige.jpg'),(2,'Tomok','Samosir','tomok.jpg'),(3,'Siallagan','Samosir','siallagan.jpg');

/*Table structure for table `t_question` */

DROP TABLE IF EXISTS `t_question`;

CREATE TABLE `t_question` (
  `question_id` int(12) NOT NULL AUTO_INCREMENT,
  `checkpoint_id` int(12) DEFAULT NULL,
  `challenge_type_id` int(12) DEFAULT NULL,
  `no_soal` int(12) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `option1` varchar(64) DEFAULT NULL,
  `option2` varchar(64) DEFAULT NULL,
  `option3` varchar(64) DEFAULT NULL,
  `option4` varchar(64) DEFAULT NULL,
  `answer` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `challange_type` (`challenge_type_id`),
  KEY `checkpoinWquestion` (`checkpoint_id`),
  CONSTRAINT `checkpoinWquestion` FOREIGN KEY (`checkpoint_id`) REFERENCES `t_checkpoint` (`checkpoint_id`),
  CONSTRAINT `t_question_ibfk_3` FOREIGN KEY (`challenge_type_id`) REFERENCES `t_challenge_type` (`challenge_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

/*Data for the table `t_question` */

insert  into `t_question`(`question_id`,`checkpoint_id`,`challenge_type_id`,`no_soal`,`question`,`option1`,`option2`,`option3`,`option4`,`answer`) values (1,1,1,1,'Apa tiga pilar utama ITDEL ,kecuali?','Martuhan','Marroha','Martoppak','Marbisuk','3'),(2,1,1,2,'Ada berapa asrama laki-laki di ITDEL di dalam del?','2','3','5','4','4'),(3,2,1,1,'Harga masuk ke museum TB.Silalahi saat hari libur?','Rp.15000','Rp.1500','Rp.10000','Rp.5000','1'),(4,2,1,2,'Hal yang pertama yang disediakan setelah masuk ke museum','Helikopter','Patung Kerbau','Tank','Patung TB.Silalahi','1'),(5,1,2,1,'Disediakannya tempat futsal.','TRUE','FALSE','','','TRUE'),(6,1,2,2,'Tidak adanya ruang santai.','TRUE','FALSE',NULL,NULL,'FALSE'),(7,2,2,1,'Adanya Baju-baju batak di museum','TRUE','FALSE',NULL,NULL,'TRUE'),(8,2,2,2,'Hanya menyediakan benda perang yang dipakai oleh TB.Silalahi','TRUE','FALSE',NULL,NULL,'FALSE'),(9,3,2,1,'Kuburan batu raja sidabutan ada 2.','TRUE','FALSE',NULL,NULL,'FALSE'),(10,3,2,2,'Ada museum batak disediakan.','TRUE','FALSE',NULL,NULL,'TRUE'),(11,3,1,1,'Masuk ke Sigale-gale , Apa yang harus dipakai?','Topi','Sortali','Ulos','Tandok','3'),(13,4,1,1,'Yang tersedia di wisata batu kursi, kecuali?','Batu kursi','Patung Sigale-gale','Toko Cendramata','Pasir putih','4'),(14,4,2,1,'Terdapat rumah adat batak','TRUE','FALSE','','','TRUE'),(15,1,1,3,'Jurusan yang tidak ada di ITDEL?','Teknik Informatika','Elektro','Manajemen Rekayasa','Teknik Mesin','4'),(16,1,1,4,'Tempat santai meamandang di ITDEL?','Open Theater','Gedung Kelas','Asrama','Ruang BAAK','1'),(17,1,1,5,'Gedung berapa Kelas Teknik Komputer tingkat akhir?','523','513','526','913','2'),(18,1,2,3,'Bisa merokok di ITDEL','TRUE','FALSE',NULL,NULL,'FALSE'),(19,1,2,4,'Mahasiswa harus mengurus surat keluar pada jam akademik.','TRUE','FALSE',NULL,NULL,'TRUE'),(20,1,2,5,'Mahasiswa ITDEL lebih dari 1000','TRUE','FALSE',NULL,'','TRUE'),(21,1,3,1,'Carilah alat yang wajib dipakai mahasiswa/i del setiap harinya',NULL,NULL,NULL,NULL,NULL),(22,2,3,1,'Carilah patung TB.silalahi',NULL,NULL,NULL,NULL,NULL),(23,3,3,1,'Carilah kepala kuburan raja sidabutar',NULL,NULL,NULL,NULL,NULL),(24,4,3,1,'Carilah alat pasung huta siallagan',NULL,NULL,NULL,NULL,NULL),(25,3,1,2,'Apa yang disediakan museum yang ada di tomok?\r\n','Beragam ulos','Beragam makanan','Beragam kendaraan','Beragam minuman','1'),(26,3,1,3,'Mengapa masuk ke kuburan raja sidabutar harus menggunakan Ulos?','Gaya','Menghormati adat','Karma','Selaras','2'),(27,3,1,4,'Barang yang paling unik dan diburu wisatawan luar negri di Tomok?','Gelang','Ulos','Baju','Tunggal Panaluan','4'),(28,3,1,5,'Warna bendera batak?','Merah-Putih-Hitam','Putih-Merah-Kuning','Merah-Hitam','Hitam-Putih','1'),(29,3,2,3,'Adanya kapal wisata?','TRUE','FALSE','','','TRUE'),(30,3,2,4,'Banyak toko baju dan perhiasan adat batak.','TRUE','FALSE','','','TRUE'),(31,3,2,5,'Wisata buka senin-jumat.','TRUE','FALSE','','','FALSE'),(32,4,1,2,'Masuk ke wisata seharga?','Rp.2000','Rp.20000','Rp.3000','Rp.10000','1'),(33,4,1,3,'Jenis batu unik di tembat wisata?','Batu parsidangan','Batu Cinta','Batu Makan','Batu Kendaraan','1'),(34,4,2,2,'Kendaraan bisa masuk ke wisata','TRUE','FALSE','','','FALSE'),(35,4,1,4,'Batu kursi tersebut kebanyakan berbentuk?','Kursi','Kendaraan','Meja','Piriing','1'),(36,4,1,5,'Mengapa terdapat batu di tempat tersebut?','Sejarah Raja Siallagan','Dibuat kenangan','Dipahat masyarakat','Agar ada wisata','1'),(37,4,2,3,'Harus memakai ulos Masuk wisata.','TRUE','FALSE','','','FALSE'),(38,4,2,4,'Adanya batu bentuk pedang','TRUE','FALSE','','','FALSE'),(39,4,2,5,'Ada guide yang memandu.','TRUE','FALSE','','','TRUE');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `auth_key` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `password_hash` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password_reset_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` smallint(6) NOT NULL DEFAULT '10',
  `created_at` int(11) NOT NULL,
  `updated_at` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `password_reset_token` (`password_reset_token`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`auth_key`,`password_hash`,`password_reset_token`,`email`,`status`,`created_at`,`updated_at`) values (1,'admin','0eAj18PsPCqwogcuL2FoNUasmPR5X2HR','$2y$13$iQSAHrSIX6ap6fcig4xinO7.vYT69EgB6PuvOAEuXU75NySTKt5ia',NULL,'admin@gmail.com',10,1491557893,1491557893);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
