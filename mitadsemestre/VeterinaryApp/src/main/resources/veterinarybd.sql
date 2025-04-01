-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-04-2025 a las 07:38:08
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `veterinarybd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `invoice`
--

CREATE TABLE `invoice` (
  `invoice_id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `amount` double NOT NULL,
  `medication_quantity` bigint(20) NOT NULL,
  `item` varchar(500) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medical_report`
--

CREATE TABLE `medical_report` (
  `medical_Report_Id` bigint(20) NOT NULL,
  `date` date NOT NULL,
  `consultation` varchar(1000) NOT NULL,
  `syntomatology` varchar(1000) NOT NULL,
  `diagnostic` varchar(1000) NOT NULL,
  `treatment` varchar(1000) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `vaccination_History` varchar(1000) NOT NULL,
  `allergies` varchar(1000) NOT NULL,
  `detailProcedure` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `order`
--

CREATE TABLE `order` (
  `order_id` bigint(20) NOT NULL,
  `pet_id` bigint(20) NOT NULL,
  `person_Document` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `medication_name` varchar(250) NOT NULL,
  `medication_dosis` double NOT NULL,
  `order_status` varchar(250) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `person`
--

CREATE TABLE `person` (
  `document` bigint(20) NOT NULL,
  `name` varchar(250) NOT NULL,
  `age` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `person`
--

INSERT INTO `person` (`document`, `name`, `age`) VALUES
(123, 'Ramiro', 23);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pet`
--

CREATE TABLE `pet` (
  `document` bigint(20) NOT NULL,
  `pet_id` int(11) NOT NULL,
  `pet_name` varchar(250) NOT NULL,
  `pet_age` bigint(20) NOT NULL,
  `species` varchar(250) NOT NULL,
  `pet_race` varchar(250) NOT NULL,
  `pet_weight` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pet`
--

INSERT INTO `pet` (`document`, `pet_id`, `pet_name`, `pet_age`, `species`, `pet_race`, `pet_weight`) VALUES
(123, 1, 'Rocky', 2, 'Perro', 'Golden', 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL,
  `document` bigint(20) NOT NULL,
  `user_name` varchar(250) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`user_id`, `document`, `user_name`, `password`, `role`) VALUES
(1, 123, 'admin', '1234', 'admin');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`invoice_id`);

--
-- Indices de la tabla `medical_report`
--
ALTER TABLE `medical_report`
  ADD PRIMARY KEY (`medical_Report_Id`);

--
-- Indices de la tabla `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`order_id`);

--
-- Indices de la tabla `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`document`);

--
-- Indices de la tabla `pet`
--
ALTER TABLE `pet`
  ADD PRIMARY KEY (`pet_id`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `invoice`
--
ALTER TABLE `invoice`
  MODIFY `invoice_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `medical_report`
--
ALTER TABLE `medical_report`
  MODIFY `medical_Report_Id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `order`
--
ALTER TABLE `order`
  MODIFY `order_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pet`
--
ALTER TABLE `pet`
  MODIFY `pet_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
