-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 25, 2024 at 07:16 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electricity_db;`
--

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

CREATE TABLE `bills` (
  `bill_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `billing_month` varchar(255) NOT NULL,
  `consumed_units` int(11) NOT NULL,
  `total_charge` double NOT NULL,
  `bill_date` date NOT NULL,
  `due_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bills`
--

INSERT INTO `bills` (`bill_id`, `customer_id`, `billing_month`, `consumed_units`, `total_charge`, `bill_date`, `due_date`) VALUES
(39, 111, 'May', 300, 532, '2024-05-25', '2024-06-24'),
(41, 12345, 'January', 12345, 24098.2, '2024-05-25', '2024-06-24'),
(42, 123456, 'January', 1234, 2320.64, '2024-05-25', '2024-06-24'),
(43, 123456789, 'January', 5434, 10552.64, '2024-05-25', '2024-06-24'),
(44, 987654321, 'December', 76567, 149973.32, '2024-05-25', '2024-06-24'),
(45, 987654321, 'November', 654, 1183.84, '2024-05-25', '2024-06-24'),
(46, 567876, 'January', 654, 1183.84, '2024-05-25', '2024-06-24'),
(47, 987654321, 'January', 45654, 89383.84, '2024-05-25', '2024-06-24'),
(48, 987654321, 'January', 9898, 19302.079999999998, '2024-05-25', '2024-06-24'),
(49, 987654321, 'April', 67, 0, '2024-05-25', '2024-06-24'),
(50, 654321, 'January', 500, 882, '2024-05-25', '2024-06-24'),
(51, 654321, 'April', 343, 607.25, '2024-05-25', '2024-06-24'),
(52, 123456, 'January', 4565, 8849.4, '2024-05-25', '2024-06-24'),
(53, 1234567, 'January', 4565456, 8948195.76, '2024-05-25', '2024-06-24'),
(54, 677656765, 'February', 23454, 45871.84, '2024-05-25', '2024-06-24'),
(55, 12345678, 'January', 23454, 45871.84, '2024-05-25', '2024-06-24'),
(56, 12345678, 'May', 5676, 11026.96, '2024-05-25', '2024-06-24'),
(57, 12345678, 'June', 123, 61.18000000000001, '2024-05-25', '2024-06-24'),
(58, 123454321, 'April', 87978, 172338.88, '2024-05-25', '2024-06-24'),
(59, 123454321, 'May', 66678, 130590.88, '2024-05-25', '2024-06-24'),
(60, 123454321, 'April', 3443, 6650.28, '2024-05-25', '2024-06-24'),
(61, 123454321, 'January', 8987, 17516.52, '2024-05-25', '2024-06-24'),
(62, 123454321, 'March', 895, 1656.1999999999998, '2024-05-25', '2024-06-24'),
(63, 123454321, 'January', 677, 1228.92, '2024-05-25', '2024-06-24'),
(64, 123454321, 'January', 676, 1226.96, '2024-05-25', '2024-06-24'),
(65, 123454321, 'January', 343, 607.25, '2024-05-25', '2024-06-24'),
(66, 123454321, 'January', 343, 607.25, '2024-05-25', '2024-06-24'),
(67, 456, 'January', 567, 1013.3199999999999, '2024-05-25', '2024-06-24'),
(68, 123454321, 'August', 1232, 2316.7200000000003, '2024-05-25', '2024-06-24'),
(69, 12345321, 'March', 5676, 11026.96, '2024-05-25', '2024-06-24'),
(70, 123454321, 'January', 567, 1013.3199999999999, '2024-05-25', '2024-06-24'),
(71, 1212, 'March', 8686, 16926.559999999998, '2024-05-25', '2024-06-24');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `customer_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `consumer_number` varchar(255) NOT NULL,
  `billing_unit` varchar(255) NOT NULL,
  `meter_number` varchar(255) NOT NULL,
  `current_reading` int(11) NOT NULL,
  `last_reading` int(11) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`customer_id`, `name`, `consumer_number`, `billing_unit`, `meter_number`, `current_reading`, `last_reading`, `password`) VALUES
(111, 'Karma Wangchuk Titung', '111', '0', '', 0, 0, '123'),
(12345, 'prem  Bhai', '12345', '0', '', 0, 0, 'password'),
(123456, 'p p p', '123456', '0', '', 0, 0, '123456'),
(123456789, 'phub  dorji', '123456789', '0', '', 0, 0, '123456789'),
(987654321, 'q q q', '987654321', '0', '', 0, 0, '987654321'),
(654321, 's  d', '654321', '0', '', 0, 0, '654321'),
(12345678, 'q q q', '12345678', '0', '', 0, 0, '12345678'),
(123454321, 'dorji phub phub', '123454321', '0', '', 0, 0, '123454321'),
(1212, 'e e e', '1212', '0', '', 0, 0, '1212');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`bill_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bills`
--
ALTER TABLE `bills`
  MODIFY `bill_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
