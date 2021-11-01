-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-11-2021 a las 23:03:31
-- Versión del servidor: 10.4.16-MariaDB
-- Versión de PHP: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bbddproyectopabloquehago`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `failed_jobs`
--

CREATE TABLE `failed_jobs` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `uuid` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `connection` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `queue` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `payload` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `exception` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `failed_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `guardar`
--

CREATE TABLE `guardar` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `tituloManana` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `descriptionManana` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fechaManana` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `localizacionManana` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `precioManana` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nombreComida` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `localizacionComida` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `precioComida` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `distritoComida` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `productosComida` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `puntuacionComida` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tituloTarde` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `descriptionTarde` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fechaTarde` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `localizacionTarde` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `precioTarde` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nombreCena` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `localizacionCena` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `precioCena` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `distritoCena` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `productosCena` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `puntuacionCena` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `guardars`
--

CREATE TABLE `guardars` (
  `id` int(20) UNSIGNED NOT NULL,
  `tituloManana` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `descriptionManana` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `fechaManana` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `localizacionManana` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `precioManana` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `nombreComida` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `localizacionComida` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `precioComida` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `distritoComida` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `productosComida` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `puntuacionComida` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `tituloTarde` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `descriptionTarde` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `fechaTarde` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `localizacionTarde` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `precioTarde` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `nombreCena` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `localizacionCena` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `precioCena` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `distritoCena` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `productosCena` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `puntuacionCena` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `guardars`
--

INSERT INTO `guardars` (`id`, `tituloManana`, `descriptionManana`, `fechaManana`, `localizacionManana`, `precioManana`, `nombreComida`, `localizacionComida`, `precioComida`, `distritoComida`, `productosComida`, `puntuacionComida`, `tituloTarde`, `descriptionTarde`, `fechaTarde`, `localizacionTarde`, `precioTarde`, `nombreCena`, `localizacionCena`, `precioCena`, `distritoCena`, `productosCena`, `puntuacionCena`, `created_at`, `updated_at`) VALUES
(1, 'uno', 'dos', 'tres', 'cuatro', 'cinco', 'seis', 'siete', 'ocho', 'nueve', 'diez', 'once', 'doce', 'trece', 'catorce', 'quince', 'dieciseis', 'diecisiete', 'dieciocho', 'diecinueve', 'veinte', 'veintiuno', 'veinti2', '2021-10-29 15:52:28', '2021-10-29 15:52:28'),
(2, 'Contra_la_raza___SOUTH_X_SOUTH_EAST,_una_performance_de_Belinda_Zhawi', 'Sabado_30_de_octubre_a_las_20_h._Apertura_puerta_a_las_19.30_h._Domingo_31_de_octubre_a_las_12_h._Apertura_puerta_a_las_19.30_h_Entradas_gratuitas_hasta_completar_aforo', '2021-10-30_00:00:00.0', 'Intermediae_Matadero_Madrid', 'nada', 'La_Esquina_Del_Real', 'Sol', '45-60e', 'Centro', 'foieen_salada_pato_solomillo_tarta', 'foieen_salada_pato_solomillo_tarta', 'Creacion_de_titeres_y_objetos', 'nada', '2021-10-30_11:00:00.0', 'Centro_Sociocultural_Santa_Petronila_Villaverde', 'GRATIS', 'La_Esquina_Del_Real', 'Sol', 'foieen_salada_pato_solomillo_tarta', 'Centro', 'nada', 'foieen_salada_pato_solomillo_tarta', '2021-10-30 13:37:14', '2021-10-30 13:37:14'),
(3, 'Abrazoa_de_tango__SUSPENDIDA_POR_LLUVIA_', 'Lugar:_Explanada_del_Ensanche_Compania:_Grupo_Clavis._El_Tango_Argentino_hoy_vuela_por_Madrid_en_toda_su_naturaleza_con_los_bailarines_y_actores_argentinos_Silvina_Mananes_y_Rodrigo_Mendiola_da_Rocha,_en_la_intimidad_de_un_concierto_coloquial_y_visual,_que_recorre_tangos_e_historias_emblematicas_e_invita_a_disfrutar_de_lo_mas_autentico_de_Buenos_Aires.', '2021-10-30_18:00:00.0', '2021-10-30_18:00:00.0', 'GRATIS', 'Acuarela_Bistro_Bar', 'Palacio', 'nada', 'Centro', 'tartacevichequesocarne', 'tartacevichequesocarne', 'Abrazoa_de_tango__SUSPENDIDA_POR_LLUVIA_', 'Lugar:_Explanada_del_Ensanche_Compania:_Grupo_Clavis._El_Tango_Argentino_hoy_vuela_por_Madrid_en_toda_su_naturaleza_con_los_bailarines_y_actores_argentinos_Silvina_Mananes_y_Rodrigo_Mendiola_da_Rocha,_en_la_intimidad_de_un_concierto_coloquial_y_visual,_que_recorre_tangos_e_historias_emblematicas_e_invita_a_disfrutar_de_lo_mas_autentico_de_Buenos_Aires.', '2021-10-30_18:00:00.0', '2021-10-30_18:00:00.0', 'GRATIS', 'Acuarela_Bistro_Bar', 'Palacio', 'nada', 'Centro', 'tartacevichequesocarne', 'tartacevichequesocarne', '2021-10-30 15:48:39', '2021-10-30 15:48:39'),
(4, 'El_porque_de_los_seres_y_las_cosas', 'Cuentacuentos_infantil,_Nelson_Calderon._Historias_que_de_manera_sencilla_y_divertida_nos_dicen_por_que_los_seres_y_las_cosas_han_llegado_a_ser_como_son_ahora._Por_ejemplo,_por_que_la_tortuga_es_lenta,_los_pajaros_vuelan,_el_conejo_tiene_las_orejas_tan_largas,_el_sapo_es_aplanadito_y_tiene_los_ojos_saltones,_etc,_mil_preguntas_y_mil_respuestas_para_aquellos_ninos_y_adultos_que_se_quieran_aventurar_a_escuchar,_leer_y_sonar._No_nos_olvidaremos_en_esta_sesion_de_que_esvispera_de_Halloweeny_se_incluiran_historias_para_la_ocasion._Edad_recomendada:_a_partir_de_4_anos', '2021-10-30_12:00:00.0', '2021-10-30_12:00:00.0', 'GRATIS', 'Sake_Bar_Shuwa_Shuwa', 'Justicia', '30-45_euros', 'Centro', 'sake_sushi_carne_pescado_arroz', 'sake_sushi_carne_pescado_arroz', 'Amelia_la_aviadora', 'nada', '2021-10-30_12:30:00.0', '2021-10-30_12:30:00.0', 'GRATIS', 'Barganzo', 'Justicia', 'nada', 'Centro', 'pita_ensalada_setas_berenjena_queso', 'pita_ensalada_setas_berenjena_queso', '2021-10-30 17:58:35', '2021-10-30 17:58:35'),
(5, 'Viaje_al_futuro', 'nada', '2021-10-30_11:30:00.0', '2021-10-30_11:30:00.0', '4_euros__tanto_ninos_como_adultos_', 'Acuarela_Bistro_Bar', 'Palacio', 'nada', 'Centro', 'tartacevichequesocarne', 'tartacevichequesocarne', 'Isaac_et_Nora_y_Feten_Feten', 'nada', '2022-01-30_12:30:00.0', '2022-01-30_12:30:00.0', 'Platea:_24__Preferente_frontal:_24__Preferente_escenario:_24_', 'Pandino', 'Justicia', 'nada', 'Centro', 'carne_queso_yogur_frutas_maracuya', 'carne_queso_yogur_frutas_maracuya', '2021-10-31 09:32:23', '2021-10-31 09:32:23'),
(6, 'Fui_sobre_agua_edificada._Los_viajes_de_agua_de_Amaniel', 'La_visita_a_otras_instalaciones_relacionadas_con_el_Medio_Ambiente_permitira_conocer_el_patrimonio_biologico_y_cultural_de_la_Comunidad_de_Madrid._Itinerario_historico_siguiendo_los_viajes_de_agua_por_la_Dehesa_de_la_Villa_Numero_de_plazas:_20_Horario_de_11_a_13_horas_Dirigido_a_publico_adulto_Es_necesario_la_reserva_previa_mediante_preinscripcion_para_participar_en_esta_actividad._Desplazamiento_a_cargo_del_participante_Informacion_y_reserva_en_el_CIN_Montecarmelo,_en_el_telefono:_917359634_y_en_el_correo_electronico_cinmontecarmelo@yahoo.com', '2021-11-27_11:00:00.0', '2021-11-27_11:00:00.0', 'GRATIS', 'Livin_Japan', 'Embajadores', 'nada', 'Centro', 'ramen_curry_tarta_pollo_queso', 'ramen_curry_tarta_pollo_queso', 'Nas_Teatro:_“Esos_monstruos_me_hacen_reir”', 'nada', '2021-11-27_12:30:00.0', '2021-11-27_12:30:00.0', 'nada', 'Sake_Bar_Shuwa_Shuwa', 'Justicia', '30-45_euros', 'Centro', 'sake_sushi_carne_pescado_arroz', 'sake_sushi_carne_pescado_arroz', '2021-11-01 09:03:56', '2021-11-01 09:03:56'),
(7, 'ArteMujer._Inauguracion_Exposicion_de_fotografia__Sanando_en_red_', 'Con_motivo_del_25N,_Dia_Internacional_de_la_Eliminacion_de_la_Violencia_contra_las_Mujeres,_la_red_municipal_de_Espacios_de_Igualdad_nos_unimos_para_crear_una_exposicion_fotografica_en_el_marco_del_proyecto_transversal_“Empoderar-T”._A_traves_de_ella,_queremos_sensibilizar_en_las_diferentes_expresiones_de_la_violencia_machista_a_traves_del_testimonio_y_trabajo_colectivo_de_mujeres_del_distrito._Pero_sobre_todo,_queremos_dar_a_conocer_el_papel_transformador_y_sanador_de_las_redes_de_apoyo_a_la_hora_de_ayudarnos_a_identificar,_reparar_y_fortalecer_las_capacidades_que_toda_mujer_tenemos_para_afrontar_la_violencia_machista._Os_invitamos_a_visitar_esta_exposicion_y_a_compartir_vuestras_impresiones.', '2021-11-23_17:30:00.0', '2021-11-23_17:30:00.0', 'GRATIS', 'El_Tormo', 'Palacio', '30-45_euros', 'Centro', 'gazpacho_pisto_codornices_bacalao_chocolate', 'gazpacho_pisto_codornices_bacalao_chocolate', 'Madrid_tiene_historia._El_relojero,_y_el_reloj_de_la_Puerta_del_Sol', 'nada', '2021-11-23_19:00:00.0', '2021-11-23_19:00:00.0', 'GRATIS', 'El_Tormo', 'Palacio', '30-45_euros', 'Centro', 'gazpacho_pisto_codornices_bacalao_chocolate', 'gazpacho_pisto_codornices_bacalao_chocolate', '2021-11-01 16:02:49', '2021-11-01 16:02:49'),
(8, 'Taller_de_Magia_Infantil', 'nada', '2021-10-23_11:00:00.0', '2021-10-23_11:00:00.0', 'GRATIS', 'Sake_Bar_Shuwa_Shuwa', 'Justicia', '30-45_euros', 'Centro', 'sake_sushi_carne_pescado_arroz', 'sake_sushi_carne_pescado_arroz', 'Taller_de_Magia_Infantil', 'nada', '2021-10-23_11:00:00.0', '2021-10-23_11:00:00.0', 'GRATIS', 'El_Tormo', 'Palacio', '30-45_euros', 'Centro', 'gazpacho_pisto_codornices_bacalao_chocolate', 'gazpacho_pisto_codornices_bacalao_chocolate', '2021-11-01 16:03:26', '2021-11-01 16:03:26'),
(9, 'Visita_guiada_a_Madrid_Rio', 'nada', '2021-11-24_10:30:00.0', '2021-11-24_10:30:00.0', 'GRATIS', 'Pandino', 'Justicia', 'nada', 'Centro', 'carne_queso_yogur_frutas_maracuya', 'carne_queso_yogur_frutas_maracuya', 'Jornada_interdisciplinar_para_profesionales._Haciendo_visible_lo_invisible', 'nada', '2021-11-24_09:00:00.0', '2021-11-24_09:00:00.0', 'GRATIS', 'La_Esquina_Del_Real', 'Sol', '45-60_euros', 'Centro', 'foieen_salada_pato_solomillo_tarta', 'foieen_salada_pato_solomillo_tarta', '2021-11-01 18:37:40', '2021-11-01 18:37:40'),
(10, 'Encuentro_25N:_conmemorando,_concienciando_y_transformando', 'Como_todos_los_anos,_os_invitamos_a_conmemorar_el_25_de_noviembre,_Dia_Internacional_de_la_Eliminacion_de_la_Violencia_contra_las_Mujeres,_con_el_Espacio_de_Igualdad._En_esta_ocasion,_inauguramos_la_exposicion_fotografica_elaborada_por_mujeres_participantes_en_el_taller_Sanando_en_Red_._A_traves_de_ella,_visibilizamos_el_papel_transformador_y_sanador_de_las_mujeres_frente_a_la_violencia_machista._Tambien_contaremos_con_las_elaboraciones_de_los_grupos_de_mujeres_del_centro_para_decorar_el_mismo_y_alrededores_conmemorando_a_las_victimas_mortales_de_violencia_de_genero_de_este_ano_y_repartiremos_llaveros_para_concienciar_por_el_fin_de_la_violencia_de_genero.', '2021-11-25_11:00:00.0', '2021-11-25_11:00:00.0', 'GRATIS', 'Livin_Japan', 'Embajadores', 'nada', 'Centro', 'ramen_curry_tarta_pollo_queso', 'ramen_curry_tarta_pollo_queso', 'Conmemoracion_Dia_Internacional_de_la_Eliminacion_de_la_Violencia_contra_las_Mujeres._La_jaula_de_cisrtal', 'nada', '2021-11-25_19:00:00.0', '2021-11-25_19:00:00.0', 'GRATIS', 'Acuarela_Bistro_Bar', 'Palacio', 'nada', 'Centro', 'tartacevichequesocarne', 'tartacevichequesocarne', '2021-11-01 18:40:35', '2021-11-01 18:40:35'),
(11, 'Encuentro_25N:_conmemorando,_concienciando_y_transformando', 'Como_todos_los_anos,_os_invitamos_a_conmemorar_el_25_de_noviembre,_Dia_Internacional_de_la_Eliminacion_de_la_Violencia_contra_las_Mujeres,_con_el_Espacio_de_Igualdad._En_esta_ocasion,_inauguramos_la_exposicion_fotografica_elaborada_por_mujeres_participantes_en_el_taller_Sanando_en_Red_._A_traves_de_ella,_visibilizamos_el_papel_transformador_y_sanador_de_las_mujeres_frente_a_la_violencia_machista._Tambien_contaremos_con_las_elaboraciones_de_los_grupos_de_mujeres_del_centro_para_decorar_el_mismo_y_alrededores_conmemorando_a_las_victimas_mortales_de_violencia_de_genero_de_este_ano_y_repartiremos_llaveros_para_concienciar_por_el_fin_de_la_violencia_de_genero.', '2021-11-25_11:00:00.0', '2021-11-25_11:00:00.0', 'GRATIS', 'Pandino', 'Justicia', 'nada', 'Centro', 'carne_queso_yogur_frutas_maracuya', 'carne_queso_yogur_frutas_maracuya', 'Violencia_machista._Una_mirada_hacia_las_mujeres_con_discapacidad', 'nada', '2021-11-25_11:30:00.0', '2021-11-25_11:30:00.0', 'GRATIS', 'Chila', 'Palacio', '20-30_euros', 'Centro', 'picante_arroz_panceta_coliflor_carne', 'picante_arroz_panceta_coliflor_carne', '2021-11-01 18:41:03', '2021-11-01 18:41:03');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `migrations`
--

CREATE TABLE `migrations` (
  `id` int(10) UNSIGNED NOT NULL,
  `migration` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(1, '2014_10_12_000000_create_users_table', 1),
(2, '2014_10_12_100000_create_password_resets_table', 1),
(3, '2019_08_19_000000_create_failed_jobs_table', 1),
(4, '2019_12_14_000001_create_personal_access_tokens_table', 1),
(5, '2021_09_27_171018_usuario', 2),
(6, '2021_09_27_171649_usuario', 3),
(7, '2021_09_27_172040_usuario', 4),
(8, '2021_10_29_173512_guardar_tabla', 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `password_resets`
--

CREATE TABLE `password_resets` (
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personal_access_tokens`
--

CREATE TABLE `personal_access_tokens` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `tokenable_type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tokenable_id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `abilities` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_used_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email_verified_at` timestamp NULL DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `nombreyapellido` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nombreusuario` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `contraseña` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fechanac` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombreyapellido`, `nombreusuario`, `contraseña`, `email`, `fechanac`, `created_at`, `updated_at`) VALUES
(1, 'Pablo Blázquez Bautista', 'Pablobb97', '1234', 'pablobbmt@gmaiil.com', '2021-11-25', '2021-09-27 17:21:03', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `nombreyapellido` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nombreusuario` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `contraseña` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fechanac` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombreyapellido`, `nombreusuario`, `contraseña`, `email`, `fechanac`, `created_at`, `updated_at`) VALUES
(1, 'Pablo Blázquez Bautista', 'Pablobb97', '1234', 'pablobbmt@gmaiil.com', '2021-11-25', '2021-09-27 17:21:03', NULL),
(2, 'JoseJoseJose', 'Jose01', '1234', 'josegmailcom', '20000310', '2021-09-27 19:39:09', '2021-09-27 19:39:09'),
(3, 'Jose Jose Jose', 'Jose01', '1234', 'jose@gmailcom', '2000-03-10', '2021-09-27 19:39:43', '2021-09-27 19:39:43'),
(4, 'm', 'm', 'm', 'm', '2000-01-01', '2021-09-28 19:05:52', '2021-09-28 19:05:52'),
(5, 'uno', 'dos', 'tres', 'cuatro', 'cinco', '2021-10-29 15:48:08', '2021-10-29 15:48:08'),
(6, 'uno', 'dos', 'tres', 'cuatro', 'cinco', '2021-10-29 15:48:23', '2021-10-29 15:48:23'),
(7, 'p', 'p', 'p', 'p', '25-11-1997', '2021-11-01 08:48:28', '2021-11-01 08:48:28'),
(8, 'ManuelBlázquezBautista', 'mbb', 'mbb', 'mbb', '16-05-1996', '2021-11-01 16:00:27', '2021-11-01 16:00:27'),
(9, 'ManuelBlázquezBautista', 'mbb', 'mbb', 'mbb', '16-05-1996', '2021-11-01 16:00:30', '2021-11-01 16:00:30'),
(10, 'ManuelBlázquezBautista', 'mbb', 'mbb', 'mbb', '16-05-1996', '2021-11-01 16:00:34', '2021-11-01 16:00:34'),
(11, 'Prueba', 'Prueba', '1234', 'prueba@gmail.com', '25-11-1997', '2021-11-01 18:33:40', '2021-11-01 18:33:40'),
(12, 'Prueba', 'Prueba', '1234', 'prueba@gmail.com', '25-11-1997', '2021-11-01 18:34:39', '2021-11-01 18:34:39'),
(13, 'p', 'p', 'p', 'p', '25-11-1997', '2021-11-01 18:36:22', '2021-11-01 18:36:22');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `failed_jobs`
--
ALTER TABLE `failed_jobs`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `failed_jobs_uuid_unique` (`uuid`);

--
-- Indices de la tabla `guardar`
--
ALTER TABLE `guardar`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `guardars`
--
ALTER TABLE `guardars`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `password_resets`
--
ALTER TABLE `password_resets`
  ADD KEY `password_resets_email_index` (`email`);

--
-- Indices de la tabla `personal_access_tokens`
--
ALTER TABLE `personal_access_tokens`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `personal_access_tokens_token_unique` (`token`),
  ADD KEY `personal_access_tokens_tokenable_type_tokenable_id_index` (`tokenable_type`,`tokenable_id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_email_unique` (`email`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `failed_jobs`
--
ALTER TABLE `failed_jobs`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `guardar`
--
ALTER TABLE `guardar`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `guardars`
--
ALTER TABLE `guardars`
  MODIFY `id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `personal_access_tokens`
--
ALTER TABLE `personal_access_tokens`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
