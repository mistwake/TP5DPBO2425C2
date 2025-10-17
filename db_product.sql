-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 17, 2025 at 06:27 AM
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
-- Database: `db_product`
--

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `harga` double NOT NULL,
  `kategori` varchar(255) NOT NULL,
  `stok` int(11) DEFAULT 0,
  `merk` varchar(255) DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `nama`, `harga`, `kategori`, `stok`, `merk`) VALUES
('P001', 'Laptop Asus', 8500000, 'Elektronik', 15, 'Asus'),
('P002', 'Mouse Logitech', 350000, 'Elektronik', 50, 'Logitech'),
('P003', 'Keyboard Mechanical', 750000, 'Elektronik', 30, 'Keychron'),
('P004', 'Roti Tawar', 15000, 'Makanan', 100, 'Sari Roti'),
('P005', 'Susu UHT', 12000, 'Minuman', 200, 'Ultra Milk'),
('P006', 'Kemeja Putih', 125000, 'Pakaian', 45, 'Uniqlo'),
('P007', 'Celana Jeans', 200000, 'Pakaian', 35, 'Levi\'s'),
('P008', 'Pensil 2B', 3000, 'Alat Tulis', 500, 'Faber-Castell'),
('P009', 'Buku Tulis', 8000, 'Alat Tulis', 300, 'Sinar Dunia'),
('P010', 'Air Mineral', 5000, 'Minuman', 400, 'Aqua'),
('P011', 'Smartphone Samsung', 4500000, 'Elektronik', 22, 'Samsung'),
('P012', 'Kue Brownies', 25000, 'Makanan', 60, 'Amanda'),
('P013', 'Jaket Hoodie', 180000, 'Pakaian', 28, 'H&M'),
('P014', 'Pulpen Gel', 5000, 'Alat Tulis', 250, 'Pilot'),
('P015', 'Teh Botol', 8000, 'Minuman', 350, 'Sosro'),
('P016', 'Teh Kotak', 4000, 'Minuman', 200, 'Sosro');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
