-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Host: db:3306
-- Generation Time: May 22, 2026 at 08:48 PM
-- Server version: 8.4.8
-- PHP Version: 8.3.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `appointments`
--

-- --------------------------------------------------------

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`client_id`, `created_at`, `deleted_at`, `id`, `pet_id`, `schedule_at`, `updated_at`, `status`) VALUES
(1, '2026-05-10 09:15:00.000000', NULL, 1, 1, '2026-05-12 10:00:00.000000', '2026-05-12 10:45:00.000000', 'COMPLETED'),
(2, '2026-05-11 11:30:00.000000', NULL, 2, 2, '2026-05-13 15:30:00.000000', '2026-05-13 16:10:00.000000', 'COMPLETED'),
(3, '2026-05-12 14:20:00.000000', NULL, 3, 3, '2026-05-14 09:00:00.000000', '2026-05-14 09:30:00.000000', 'COMPLETED'),
(4, '2026-05-15 10:00:00.000000', NULL, 4, 4, '2026-05-16 11:00:00.000000', '2026-05-16 11:40:00.000000', 'COMPLETED'),
(5, '2026-05-16 16:45:00.000000', NULL, 5, 5, '2026-05-18 15:00:00.000000', '2026-05-18 15:50:00.000000', 'COMPLETED'),
(6, '2026-05-18 11:30:00.000000', '2026-05-19 09:00:00.000000', 6, 6, '2026-05-20 15:30:00.000000', '2026-05-19 09:00:00.000000', 'CANCELED'),
(7, '2026-05-19 14:20:00.000000', NULL, 7, 7, '2026-05-21 09:00:00.000000', '2026-05-21 09:30:00.000000', 'MISSED'),
(8, '2026-05-22 16:00:00.000000', NULL, 8, 8, '2026-05-22 16:30:00.000000', '2026-05-22 16:35:00.000000', 'IN_PROGRESS'),
(9, '2026-05-22 16:40:00.000000', NULL, 9, 9, '2026-05-25 10:00:00.000000', NULL, 'CONFIRMED'),
(1, '2026-05-12 10:50:00.000000', NULL, 10, 1, '2026-05-19 10:00:00.000000', '2026-05-19 10:30:00.000000', 'COMPLETED');

-- --------------------------------------------------------

--
-- Dumping data for table `clinical_records`
--

INSERT INTO `clinical_records` (`appointment_id`, `client_id`, `created_at`, `id`, `pet_id`, `professional_id`, `updated_at`, `diagnosis`, `notes`, `treatment`) VALUES
(1, 1, '2026-05-12 10:40:00.000000', 1, 1, 7, NULL, 'Otitis externa leve', 'Perro quiltro rescatado. Presenta picazón constante en la oreja derecha. Se porta un 7 en la camilla.', 'Limpieza profunda. Aplicar gotas óticas cada 12 horas por 7 días.'),
(2, 2, '2026-05-13 16:05:00.000000', 2, 2, 8, NULL, 'Gastroenteritis', 'Gato doméstico. El dueño le dio empanada de pino el finde. Presenta vómitos y decaimiento.', 'Ayuno por 12 horas. Administrado antiemético inyectable. Dieta blanda por 3 días.'),
(3, 3, '2026-05-14 09:25:00.000000', 3, 3, 7, NULL, 'Sobrecrecimiento dental', 'Conejo cabeza de león. Dificultad para comer heno. Incisivos superiores muy largos.', 'Desgaste dental bajo sedación leve. Se indica aumentar la cantidad de heno en la dieta diaria.'),
(4, 4, '2026-05-16 11:35:00.000000', 4, 4, 9, NULL, 'Control sano', 'Cachorro Poodle de 3 meses. Todo en orden, temperatura normal. Bien regalón.', 'Se administra primera dosis de vacuna Óctuple y desparasitante interno.'),
(5, 5, '2026-05-18 15:45:00.000000', 5, 5, 8, NULL, 'Dermatitis alérgica', 'Pug con alergia severa en el lomo. Dueño menciona que pasearon por el cerro San Cristóbal.', 'Corticoide inyectable para el prurito. Se receta Bravecto y champú de avena.'),
(8, 8, '2026-05-22 16:40:00.000000', 6, 8, 9, NULL, 'En evaluación', 'Gato obeso. Presenta dificultad para orinar desde ayer en la tarde. Vejiga pletórica a la palpación.', 'Se procede con sondaje uretral de urgencia. A la espera de exámenes de sangre.'),
(10, 1, '2026-05-19 10:25:00.000000', 7, 1, 7, NULL, 'Control otitis externa', 'Control a los 7 días. Conducto auditivo derecho sin eritema ni secreción. Excelente evolución.', 'Alta médica. Se indica limpieza semanal preventiva.');

--
-- Indexes for dumped tables
--

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointments`
--
ALTER TABLE `appointments`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `clinical_records`
--
ALTER TABLE `clinical_records`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

