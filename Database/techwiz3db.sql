-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Aug 15, 2022 at 04:45 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.0.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Database: `techwiz3db`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `mail` varchar(50) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `dob` date DEFAULT current_timestamp(),
  `gender` varchar(20) DEFAULT NULL,
  `role` varchar(50) NOT NULL,
  `avatar` varchar(250) DEFAULT 'images/Website/user1.png',
  `code` varchar(255) NOT NULL,
  `password` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `mail`, `name`, `phone`, `dob`, `gender`, `role`, `avatar`, `code`, `password`) VALUES
(1, 'thiennnts2008038@fpt.edu.vn', 'Thien Nguyen', '+84989096119', '1992-01-28', 'Male', 'Admin', 'images/Website/user1.png', 'SmartStudy001', '$2a$10$741hsE4nveWq7d1I3e.1FeZJGwvR2xCyYRPcdPztzQ/N5UOKT0w/2'),
(2, 'j.thien1551@gmail.com', 'Thien Nguyen', '+84989099632', '2022-01-11', 'Male', 'Admin', 'images/Website/user1.png', 'SmartStudy002', '$2a$10$mo7FXIx/VOrF9UDxwAaw8eXRBsCg0iZMI8DycIcwwWz3VwZTAWjD2'),
(3, 'vuongpham@gmail.com', 'Viet Vuong', '+84373627828', '2022-01-01', 'Male', 'Admin', 'images/Website/user1.png', 'SmartStudy003', '$2a$10$GeRGpaVGOzd6NEPFbcAQ0.6UjLrbm4xvmfNtlWFMgrblyElkn5pCq'),
(4, 'vlogcuocsongvacongnghe@gmail.com', 'Lê Nhân', '+84375515819', '2022-01-16', 'Male', 'Admin', 'images/Website/user1.png', 'SmartStudy004', '$2a$10$MRtC0vg5a.yxKOhaFC342uJHVSPyUPYlGRXrOM1oKS9/xl6eCzOa2'),
(5, 'tamlhts2008005@fpt.edu.vn', 'Huu Tam', '+84912345678', '2002-06-01', 'Male', 'Admin', 'images/Website/user1.png', 'SmartStudy005', '$2a$10$DrEv22d94dQtRb/I47m/kei/AKppycMgcBnkcWI0de0xHDqriRqjS'),
(6, 'admin@smartstudy.com', 'Admin', '+84989096911', '2022-01-01', 'Male', 'Admin', 'images/Website/user1.png', 'SmartStudy006', '$2a$10$sgjgfFpuVNN7Dyp.oSQ8ruML8ZGB.XQqqEExuBXNDzerqBpvsKxwK'),
(9, 'easter2015@fpt.edu.vn', 'Easter', '+84326024953', '0183-06-08', 'Male', 'Student', 'images/Website/icon3.png', 'SmartStudy233978', '$2a$10$6kTtjjndV.Yi61XGGWaFyOzeUYZw1dAd4af9.BdQVV7EsUTuHTFFG'),
(10, 'sienna1990@fpt.edu.vn', 'Sienna', '+84353362394', '1990-08-02', 'Female', 'Teacher', 'images/Website/icon0.png', 'SmartStudy426937', '$2a$10$pf8rHy1kvJfeV25GvkHgL.EG7NPjqHF0izndQVYvCvV/AHaZ9445q'),
(11, 'jonas2002@fpt.edu.vn', 'Jonas', '+84987654321', '2002-02-16', 'Female', 'Student', 'images/Website/user1.png', 'SmartStudy220461', '$2a$10$2zeVRcd2OwcWwmVWx1kDQe3/VuL3j/HmsdSnZIr5vE88p/7TBxJIm'),
(12, 'cordie2000@fpt.edu.vn', 'Cordie', '+84123456789', '2000-10-06', 'Female', 'Teacher', 'images/Website/user1.png', 'SmartStudy832732', '$2a$10$LuA5ldvPcpaxrdgnheloUOa1yFD0x99V4UHEQiw7cH6sXd1EgO6b6'),
(13, 'lenhannhan123@gmail.com', 'Le Nhan', '+84645391817', '2000-01-08', 'Male', 'Student', 'images/Website/user1.png', 'SmartStudy198014', '$2a$10$/V7ZePI.Avsf8S7rZVJPBuBSm74PxLnFZx2lD8UTiv51Q/IkZDNB2'),
(14, '', '', NULL, '2000-04-10', NULL, 'Teacher', 'images/Website/user1.png', 'SmartStudy522837', '$2a$10$/V7ZePI.Avsf8S7rZVJPBuBSm74PxLnFZx2lD8UTiv51Q/IkZDNB2'),
(15, 'thanhnhan123@gmail.com', 'Nhân Lê', '+84428792380', '1995-01-08', 'Male', 'Student', 'images/Website/user1.png', 'SmartStudy659948', '$2a$10$SptiNDiMv66kOSLgtF1FBebYIb6gw81/dKgAX89EKFp3zc5nrevGW'),
(17, 'reba1998@fpt.edu.vn', 'Willie', '+84962804039', '1998-11-20', 'Male', 'Student', 'images/Website/user1.png', 'SmartStudy238519', '$2a$10$6hLgo6SMkGSIzL/9A.OECuQ/0nHwWLgw7skA5uPTYMZrYuCAmfJAS'),
(18, 'jarrod9@fpt.edu.vn', 'Phyllis ', '+84375628419', '1996-08-20', 'Male', 'Student', 'images/Website/user1.png', 'SmartStudy746926', '$2a$10$geeAFEVXCyhIlYqknEemcuBMzj974Af9VFIjLpZ7ov9uWZfjFDaFe'),
(19, 'clement125@fpt.edu.vn', 'Clement', '+84538906863', '1995-06-14', 'Male', 'Student', 'images/Website/user1.png', 'SmartStudy295230', '$2a$10$Ej43d/hTuKOJwIlAZys6D.h3HGc9HME5VzOqcJvUj1SMbaxSsLjje'),
(21, 'kanewalke8@fpt.edu.vn', 'Sarah', '+84437794136', '1982-11-04', 'Male', 'Teacher', 'images/Website/user1.png', 'SmartStudy941752', '$2a$10$15A3M70JopHdOOO7qOf7/.GVNyHEq7xObiP0OfQJf8AL18j0bQtvm'),
(25, 'parenteaster@gmail.com', 'parent Easter', NULL, '0172-06-08', NULL, 'Parent', 'images/Website/user1.png', '9', '$2a$10$cLkTMNqhvv0OPO0ct2qiPu5RhsYfyjP96HiUZGYR6Wgz2fqqa8lZe');

-- --------------------------------------------------------

--
-- Table structure for table `account_token`
--

CREATE TABLE `account_token` (
  `token` varchar(255) NOT NULL,
  `id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `account_token`
--

INSERT INTO `account_token` (`token`, `id`) VALUES
('213', NULL),
('111', 1),
('eF0SKzwQR3qxRwVTQcVxVd:APA91bHlYWjVOcvl9hoVfDE4GIARA4AT-_cC-Wbj7Oj9g8lwelV93vBSkFvmobdPjbyp_jtygaxHFM5PXRkkE_BEIodHVkDKygyTFHD3PN3gtxb2X2uRwZwBPO3wvGmVGg1Cski-D4YB', 10);

-- --------------------------------------------------------

--
-- Table structure for table `classroom`
--

CREATE TABLE `classroom` (
  `id` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  `duration` varchar(30) NOT NULL,
  `id_semester` int(11) NOT NULL,
  `image` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `classroom`
--

INSERT INTO `classroom` (`id`, `name`, `duration`, `id_semester`, `image`) VALUES
(7, 'T12008A0', '24', 2, 'images/Website/icon34.png'),
(8, 'A12002F1', '18', 1, 'images/Website/icon2.png');

-- --------------------------------------------------------

--
-- Table structure for table `classroom_semester`
--

CREATE TABLE `classroom_semester` (
  `id` int(11) NOT NULL,
  `id_classroom` int(11) NOT NULL,
  `id_semester` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `classroom_semester`
--

INSERT INTO `classroom_semester` (`id`, `id_classroom`, `id_semester`) VALUES
(5, 7, 12),
(6, 7, 13);

-- --------------------------------------------------------

--
-- Table structure for table `classroom_user`
--

CREATE TABLE `classroom_user` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_classroom` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `classroom_user`
--

INSERT INTO `classroom_user` (`id`, `id_user`, `id_classroom`) VALUES
(10, 17, 7),
(11, 18, 7),
(12, 15, 7),
(13, 11, 7),
(14, 13, 7),
(15, 19, 7),
(16, 5, 7),
(17, 5, 8),
(18, 9, 8),
(19, 9, 7);

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(150) NOT NULL,
  `duration` int(11) NOT NULL,
  `image` varchar(200) NOT NULL,
  `teacher` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`id`, `name`, `description`, `duration`, `image`, `teacher`) VALUES
(1, 'Programming with Python', 'Learning python', 2, 'images/Website/icon21.png', 10),
(2, 'Tester ', 'Writing test for programming', 2, 'images/Website/icon20.png', 21),
(3, 'Mobile Android', 'Developing mobile apps for Android', 2, 'images/Website/icon18.png', 12),
(4, 'Relational Database', 'Database programming', 1, 'images/Website/icon24.png', 21),
(5, 'Front End programming', 'Website development', 3, 'images/Website/icon37.png', 21),
(6, 'Unity game development', 'Create game in c#', 1, 'images/Website/icon34.png', 10),
(7, 'Data Structures & Design Pattern', 'General programming and software architecture', 3, 'images/Website/icon30.png', 12);

-- --------------------------------------------------------

--
-- Table structure for table `document`
--

CREATE TABLE `document` (
  `id` int(11) NOT NULL,
  `link` varchar(200) NOT NULL,
  `id_course` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `document`
--

INSERT INTO `document` (`id`, `link`, `id_course`) VALUES
(1, 'https://drive.google.com/file/d/1LkU-X9dshlaJ6fSRUSHZ-6g_uemSAZYG/view?usp=sharing', 7),
(2, 'https://drive.google.com/file/d/1hxGeQYz-Zn5puawtSCZCOaVMhw_DR3Gy/view?usp=sharing', 5),
(3, 'https://drive.google.com/file/d/1NaaHVf6gmYX4aY-HZV_TcNodRaHdx_gZ/view?usp=sharing', 3),
(4, 'https://drive.google.com/file/d/1IpcRnBHnLBxSbLuIHnH-qNjtnDiLOLyG/view?usp=sharing', 1),
(5, 'https://drive.google.com/file/d/1JQZcPoSpOMtAe_brArP5jubuy4-4OIt8/view?usp=sharing', 4),
(6, 'link: https://drive.google.com/file/d/1IpcRnBHnLBxSbLuIHnH-qNjtnDiLOLyG/view?usp=sharing', 2),
(7, 'https://drive.google.com/file/d/1IpcRnBHnLBxSbLuIHnH-qNjtnDiLOLyG/view?usp=sharing', 6);

-- --------------------------------------------------------

--
-- Table structure for table `exam`
--

CREATE TABLE `exam` (
  `id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `id_course` int(11) NOT NULL,
  `id_classroom` int(11) NOT NULL,
  `image` varchar(200) NOT NULL,
  `Name` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `exam`
--

INSERT INTO `exam` (`id`, `start_date`, `id_course`, `id_classroom`, `image`, `Name`) VALUES
(5, '2022-08-02', 1, 7, 'images/Website/icon0.png', 'Test Python'),
(7, '2022-08-03', 6, 7, 'images/Website/icon11.png', 'Unit Test Writing'),
(9, '2022-08-15', 6, 8, 'images/Website/icon13.png', 'Unit Test 2 Writing'),
(10, '0182-06-08', 6, 7, 'images/Website/icon19.png', 'Exam 1'),
(11, '2022-06-08', 1, 7, 'images/Website/icon8.png', 'Test Python 1');

-- --------------------------------------------------------

--
-- Table structure for table `libraryimage`
--

CREATE TABLE `libraryimage` (
  `id` int(11) NOT NULL,
  `image` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `libraryimage`
--

INSERT INTO `libraryimage` (`id`, `image`) VALUES
(1, 'images/Website/icon0.png'),
(2, 'images/Website/icon1.png'),
(3, 'images/Website/icon2.png'),
(4, 'images/Website/icon3.png'),
(5, 'images/Website/icon4.png'),
(6, 'images/Website/icon5.png'),
(7, 'images/Website/icon6.png'),
(8, 'images/Website/icon7.png'),
(9, 'images/Website/icon8.png'),
(10, 'images/Website/icon9.png'),
(11, 'images/Website/icon10.png'),
(12, 'images/Website/icon11.png'),
(13, 'images/Website/icon12.png'),
(14, 'images/Website/icon13.png'),
(15, 'images/Website/icon14.png'),
(16, 'images/Website/icon15.png'),
(17, 'images/Website/icon16.png'),
(18, 'images/Website/icon17.png'),
(19, 'images/Website/icon18.png'),
(20, 'images/Website/icon19.png'),
(21, 'images/Website/icon20.png'),
(22, 'images/Website/icon21.png'),
(23, 'images/Website/icon22.png'),
(24, 'images/Website/icon23.png'),
(25, 'images/Website/icon24.png'),
(26, 'images/Website/icon25.png'),
(27, 'images/Website/icon26.png'),
(28, 'images/Website/icon27.png'),
(29, 'images/Website/icon28.png'),
(30, 'images/Website/icon29.png'),
(31, 'images/Website/icon30.png'),
(32, 'images/Website/icon31.png'),
(33, 'images/Website/icon32.png'),
(34, 'images/Website/icon33.png'),
(35, 'images/Website/icon34.png'),
(36, 'images/Website/icon35.png'),
(37, 'images/Website/icon36.png'),
(38, 'images/Website/icon37.png'),
(39, 'images/Website/icon38.png');

-- --------------------------------------------------------

--
-- Table structure for table `mark`
--

CREATE TABLE `mark` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_course` int(11) NOT NULL,
  `id_exam` int(11) NOT NULL,
  `mark` int(11) NOT NULL,
  `remark` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mark`
--

INSERT INTO `mark` (`id`, `id_user`, `id_course`, `id_exam`, `mark`, `remark`) VALUES
(2, 5, 1, 5, 10, 'Very Good '),
(6, 5, 2, 7, 8, ''),
(7, 9, 1, 5, 9, 'Very Good'),
(56, 17, 6, 10, 7, ' Good'),
(57, 18, 6, 10, 10, ' Very Good'),
(58, 15, 6, 10, 10, ' Very Good'),
(59, 11, 6, 10, 10, ' Very Good'),
(60, 13, 6, 10, 10, ' Very Good'),
(61, 19, 6, 10, 9, ' Good'),
(62, 5, 6, 10, 8, ' Good'),
(63, 9, 6, 10, 8, ' Good'),
(64, 5, 6, 9, 10, ' Good'),
(65, 9, 6, 9, 8, ' Good'),
(66, 17, 6, 7, 5, ' Good'),
(67, 18, 6, 7, 6, ' Good'),
(68, 15, 6, 7, 8, ' Good'),
(69, 11, 6, 7, 10, ' Good'),
(70, 13, 6, 7, 7, ' Good'),
(71, 19, 6, 7, 6, ' Not Good'),
(72, 5, 6, 7, 4, ' Bad'),
(73, 9, 6, 7, 4, ' Bad'),
(74, 17, 1, 11, 8, ' Good'),
(75, 18, 1, 11, 9, ' Good Job'),
(76, 15, 1, 11, 10, ' Very Good'),
(77, 11, 1, 11, 5, ' Bad'),
(78, 13, 1, 11, 7, ' Good'),
(79, 19, 1, 11, 4, ' Bad'),
(80, 5, 1, 11, 3, ' Very Bad'),
(81, 9, 1, 11, 6, ' Normal');

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `content` text NOT NULL,
  `isAll` bit(1) NOT NULL,
  `create_date` date NOT NULL DEFAULT current_timestamp(),
  `is_all` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `notification`
--

INSERT INTO `notification` (`id`, `name`, `content`, `isAll`, `create_date`, `is_all`) VALUES
(1, 'Admission Notice', 'Students think December 10, 11, and 12', b'0', '2022-08-13', b'0'),
(2, 'Students think December 10, 11, and 12', 'Admission December 10', b'0', '2022-08-13', b'0'),
(3, 'Admission Notice', 'Admission December 10', b'1', '2022-08-01', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `notification_user`
--

CREATE TABLE `notification_user` (
  `id` int(11) NOT NULL,
  `id_notification` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `create_date` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `notification_user`
--

INSERT INTO `notification_user` (`id`, `id_notification`, `id_user`, `create_date`) VALUES
(1, 1, 1, '2022-08-13'),
(2, 2, 1, '2022-08-13'),
(3, 3, 1, '2022-08-13'),
(4, 1, 5, '2022-08-09'),
(5, 1, 9, '2022-08-15');

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE `schedule` (
  `id` int(11) NOT NULL,
  `end_date` date NOT NULL,
  `name` varchar(100) NOT NULL,
  `start_date` date NOT NULL,
  `id_classroom` int(11) NOT NULL,
  `id_course` int(11) NOT NULL,
  `id_teacher` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `semester`
--

CREATE TABLE `semester` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `description` varchar(250) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `semester`
--

INSERT INTO `semester` (`id`, `name`, `description`, `start_date`, `end_date`) VALUES
(12, 'Beginer Code', 'Beginer Code Content', '0181-06-08', '0199-06-08'),
(13, 'Master Code', 'Master Code Content', '0169-06-09', '0198-06-09');

-- --------------------------------------------------------

--
-- Table structure for table `semester_course`
--

CREATE TABLE `semester_course` (
  `id` int(11) NOT NULL,
  `id_semester` int(11) NOT NULL,
  `id_course` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `semester_course`
--

INSERT INTO `semester_course` (`id`, `id_semester`, `id_course`) VALUES
(14, 12, 1),
(15, 12, 2),
(16, 13, 4),
(17, 13, 6);

-- --------------------------------------------------------

--
-- Table structure for table `student_parent`
--

CREATE TABLE `student_parent` (
  `id` int(11) NOT NULL,
  `student_id` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `account_token`
--
ALTER TABLE `account_token`
  ADD PRIMARY KEY (`token`),
  ADD KEY `fk_accout_token` (`id`);

--
-- Indexes for table `classroom`
--
ALTER TABLE `classroom`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKc60dwl7a3r01al0mxma1isga` (`id_semester`);

--
-- Indexes for table `classroom_semester`
--
ALTER TABLE `classroom_semester`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_classroom` (`id_classroom`),
  ADD KEY `id_semester` (`id_semester`);

--
-- Indexes for table `classroom_user`
--
ALTER TABLE `classroom_user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_classroom` (`id_classroom`),
  ADD KEY `classroom_user_ibfk_2` (`id_user`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_teacher` (`teacher`);

--
-- Indexes for table `document`
--
ALTER TABLE `document`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_course` (`id_course`);

--
-- Indexes for table `exam`
--
ALTER TABLE `exam`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_classroom` (`id_classroom`),
  ADD KEY `id_course` (`id_course`);

--
-- Indexes for table `libraryimage`
--
ALTER TABLE `libraryimage`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mark`
--
ALTER TABLE `mark`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_course` (`id_course`),
  ADD KEY `id_exam` (`id_exam`),
  ADD KEY `mark_ibfk_1` (`id_user`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notification_user`
--
ALTER TABLE `notification_user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_notification` (`id_notification`),
  ADD KEY `notification_user_ibfk_2` (`id_user`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKf99924n5rgkyfmh3p6u4lye51` (`id_classroom`),
  ADD KEY `FKrmvag562fnoc08wxh64bv58e5` (`id_course`),
  ADD KEY `FKaeelqxd4ge4n9uhoj74v829l3` (`id_teacher`);

--
-- Indexes for table `semester`
--
ALTER TABLE `semester`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `semester_course`
--
ALTER TABLE `semester_course`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_semester` (`id_semester`),
  ADD KEY `id_course` (`id_course`);

--
-- Indexes for table `student_parent`
--
ALTER TABLE `student_parent`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_accout_student` (`student_id`),
  ADD KEY `fk_accout_parent` (`parent_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `classroom`
--
ALTER TABLE `classroom`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `classroom_semester`
--
ALTER TABLE `classroom_semester`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `classroom_user`
--
ALTER TABLE `classroom_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `document`
--
ALTER TABLE `document`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `exam`
--
ALTER TABLE `exam`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `libraryimage`
--
ALTER TABLE `libraryimage`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT for table `mark`
--
ALTER TABLE `mark`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=82;

--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `notification_user`
--
ALTER TABLE `notification_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `semester`
--
ALTER TABLE `semester`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `semester_course`
--
ALTER TABLE `semester_course`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `student_parent`
--
ALTER TABLE `student_parent`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account_token`
--
ALTER TABLE `account_token`
  ADD CONSTRAINT `fk_accout_token` FOREIGN KEY (`id`) REFERENCES `account` (`id`);

--
-- Constraints for table `classroom_semester`
--
ALTER TABLE `classroom_semester`
  ADD CONSTRAINT `classroom_semester_ibfk_1` FOREIGN KEY (`id_classroom`) REFERENCES `classroom` (`id`),
  ADD CONSTRAINT `classroom_semester_ibfk_2` FOREIGN KEY (`id_semester`) REFERENCES `semester` (`id`);

--
-- Constraints for table `classroom_user`
--
ALTER TABLE `classroom_user`
  ADD CONSTRAINT `classroom_user_ibfk_1` FOREIGN KEY (`id_classroom`) REFERENCES `classroom` (`id`),
  ADD CONSTRAINT `classroom_user_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `account` (`id`);

--
-- Constraints for table `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `FK_teacher` FOREIGN KEY (`teacher`) REFERENCES `account` (`id`);

--
-- Constraints for table `document`
--
ALTER TABLE `document`
  ADD CONSTRAINT `document_ibfk_1` FOREIGN KEY (`id_course`) REFERENCES `course` (`id`);

--
-- Constraints for table `exam`
--
ALTER TABLE `exam`
  ADD CONSTRAINT `exam_ibfk_1` FOREIGN KEY (`id_classroom`) REFERENCES `classroom` (`id`),
  ADD CONSTRAINT `exam_ibfk_2` FOREIGN KEY (`id_course`) REFERENCES `course` (`id`);

--
-- Constraints for table `mark`
--
ALTER TABLE `mark`
  ADD CONSTRAINT `mark_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `mark_ibfk_2` FOREIGN KEY (`id_course`) REFERENCES `course` (`id`),
  ADD CONSTRAINT `mark_ibfk_3` FOREIGN KEY (`id_exam`) REFERENCES `exam` (`id`);

--
-- Constraints for table `notification_user`
--
ALTER TABLE `notification_user`
  ADD CONSTRAINT `notification_user_ibfk_1` FOREIGN KEY (`id_notification`) REFERENCES `notification` (`id`),
  ADD CONSTRAINT `notification_user_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `account` (`id`);

--
-- Constraints for table `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `FKaeelqxd4ge4n9uhoj74v829l3` FOREIGN KEY (`id_teacher`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `FKf99924n5rgkyfmh3p6u4lye51` FOREIGN KEY (`id_classroom`) REFERENCES `classroom` (`id`),
  ADD CONSTRAINT `FKrmvag562fnoc08wxh64bv58e5` FOREIGN KEY (`id_course`) REFERENCES `course` (`id`);

--
-- Constraints for table `semester_course`
--
ALTER TABLE `semester_course`
  ADD CONSTRAINT `semester_course_ibfk_1` FOREIGN KEY (`id_semester`) REFERENCES `semester` (`id`),
  ADD CONSTRAINT `semester_course_ibfk_2` FOREIGN KEY (`id_course`) REFERENCES `course` (`id`);

--
-- Constraints for table `student_parent`
--
ALTER TABLE `student_parent`
  ADD CONSTRAINT `fk_accout_parent` FOREIGN KEY (`parent_id`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `fk_accout_student` FOREIGN KEY (`student_id`) REFERENCES `account` (`id`);
COMMIT;
