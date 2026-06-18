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

INSERT INTO `appointments` (`id`, `client_id`, `pet_id`, `professional_id`, `schedule_at`, `end_schedule_at`, `status`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 1, 1, 7, '2026-05-12 10:00:00.000000','2026-05-12 10:15:00.000000', 'COMPLETED', '2026-05-10 09:15:00.000000', '2026-05-12 10:45:00.000000', NULL),
(2, 2, 2, 8, '2026-05-13 15:30:00.000000','2026-05-13 15:45:00.000000', 'COMPLETED', '2026-05-11 11:30:00.000000', '2026-05-13 16:10:00.000000', NULL),
(3, 3, 3, 7, '2026-05-14 09:00:00.000000','2026-05-14 09:15:00.000000', 'COMPLETED', '2026-05-12 14:20:00.000000', '2026-05-14 09:30:00.000000', NULL),
(4, 4, 4, 9, '2026-05-16 11:00:00.000000','2026-05-16 11:15:00.000000', 'COMPLETED', '2026-05-15 10:00:00.000000', '2026-05-16 11:40:00.000000', NULL),
(5, 5, 5, 8, '2026-05-18 15:00:00.000000','2026-05-18 15:15:00.000000', 'COMPLETED', '2026-05-16 16:45:00.000000', '2026-05-18 15:50:00.000000', NULL),
(6, 6, 6, 7, '2026-05-20 15:30:00.000000','2026-05-20 15:45:00.000000', 'CANCELED', '2026-05-18 11:30:00.000000', '2026-05-19 09:00:00.000000', '2026-05-19 09:00:00.000000'),
(7, 7, 7, 8, '2026-05-21 09:00:00.000000','2026-05-21 09:15:00.000000', 'MISSED', '2026-05-19 14:20:00.000000', '2026-05-21 09:30:00.000000', NULL),
(8, 8, 8, 9, '2026-05-22 16:30:00.000000','2026-05-22 16:45:00.000000', 'IN_PROGRESS', '2026-05-22 16:00:00.000000', '2026-05-22 16:35:00.000000', NULL),
(9, 9, 9, 7, '2026-05-25 10:00:00.000000','2026-05-25 10:15:00.000000', 'CONFIRMED', '2026-05-22 16:40:00.000000', NULL, NULL),
(10, 1, 1, 7, '2026-05-19 10:00:00.000000', '2026-05-19 10:15:00.000000', 'COMPLETED', '2026-05-12 10:50:00.000000', '2026-05-19 10:30:00.000000', NULL),
(11, 1, 1, 3, '2030-06-18 10:00:00.000000','2030-06-18 11:00:00.000000', 'CONFIRMED', '2026-05-22 16:40:00.000000', NULL, NULL);

-- --------------------------------------------------------

--
-- Dumping data for table `clinical_records`
--

INSERT INTO `clinical_records` (`id`, `appointment_id`, `client_id`, `pet_id`, `professional_id`, `visit_reason`, `diagnosis`, `treatment`, `notes`, `created_at`, `updated_at`) VALUES
(1, 1, 1, 1, 7, 'Picazón constante en la oreja derecha', 'Otitis externa leve', 'Limpieza profunda. Aplicar gotas óticas cada 12 horas por 7 días.', 'Perro quiltro rescatado. Presenta picazón constante en la oreja derecha. Se porta un 7 en la camilla.', '2026-05-12 10:40:00.000000', NULL),
(2, 2, 2, 2, 8, 'Vómitos y decaimiento', 'Gastroenteritis', 'Ayuno por 12 horas. Administrado antiemético inyectable. Dieta blanda por 3 días.', 'Gato doméstico. El dueño le dio empanada de pino el finde. Presenta vómitos y decaimiento.', '2026-05-13 16:05:00.000000', NULL),
(3, 3, 3, 3, 7, 'Dificultad para comer', 'Sobrecrecimiento dental', 'Desgaste dental bajo sedación leve. Se indica aumentar la cantidad de heno en la dieta diaria.', 'Conejo cabeza de león. Dificultad para comer heno. Incisivos superiores muy largos.', '2026-05-14 09:25:00.000000', NULL),
(4, 4, 4, 4, 9, 'Control sano cachorro', 'Control sano', 'Se administra primera dosis de vacuna Óctuple y desparasitante interno.', 'Cachorro Poodle de 3 meses. Todo en orden, temperatura normal. Bien regalón.', '2026-05-16 11:35:00.000000', NULL),
(5, 5, 5, 5, 8, 'Alergia severa en la piel', 'Dermatitis alérgica', 'Corticoide inyectable para el prurito. Se receta Bravecto y champú de avena.', 'Pug con alergia severa en el lomo. Dueño menciona que pasearon por el cerro San Cristóbal.', '2026-05-18 15:45:00.000000', NULL),
(6, 8, 8, 8, 9, 'Dificultad para orinar', 'En evaluación', 'Se procede con sondaje uretral de urgencia. A la espera de exámenes de sangre.', 'Gato obeso. Presenta dificultad para orinar desde ayer en la tarde. Vejiga pletórica a la palpación.', '2026-05-22 16:40:00.000000', NULL),
(7, 10, 1, 1, 7, 'Control tratamiento otitis', 'Control otitis externa', 'Alta médica. Se indica limpieza semanal preventiva.', 'Control a los 7 días. Conducto auditivo derecho sin eritema ni secreción. Excelente evolución.', '2026-05-19 10:25:00.000000', NULL);

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

