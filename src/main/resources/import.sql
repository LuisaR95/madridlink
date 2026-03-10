-- 1. SEDES
INSERT INTO sedes (id, nombre, direccion, horario, telefono, latitud, longitud) VALUES (1, 'Comisaria de San Felipe', 'Calle de San Felipe, 7', '09:00 - 14:00', 'No disponible - cita previa web', 40.4615747, -3.6967795);
INSERT INTO sedes (id, nombre, direccion, horario, telefono, latitud, longitud) VALUES (2, 'OAC Chamberí (Palafox)', 'Calle de Palafox, 4', '09:00 - 21:00', '914 80 00 10', 40.4307612, -3.7009174);
INSERT INTO sedes (id, nombre, direccion, horario, telefono, latitud, longitud) VALUES (3, 'Sede Central Consorcio (Ríos Rosas)', 'Plaza del Descubridor Diego de Ordás, 3', '8:00 a 20:00', '915 80 42 60', 40.4677165, -3.6919624);
INSERT INTO sedes (id, nombre, direccion, horario, telefono, latitud, longitud) VALUES (4, 'Oficina de Extranjería (Padre Piquer)', 'Avenida del Padre Piquer, 18', '9:00 a 14:00 y de 16:00 a 20:00', '912 72 95 00', 40.392971, -3.7679911);

-- 2. TRÁMITES
INSERT INTO tramites (id, titulo, descripcion, completado, plazo, ubicacion, url_cita, sede_id) VALUES (1, 'NIE Inicial', 'Solicitud de número de identidad de extranjero', false, '90 días', 'Oficina Pradillo', 'https://sede.administracionespublicas.gob.es/pagina/index/directorio/icpplus', 1);
INSERT INTO tramites (id, titulo, descripcion, completado, plazo, ubicacion, url_cita, sede_id) VALUES (2, 'Empadronamiento', 'Inscripción en el padrón municipal de Madrid', false, 'Inmediato', 'Ayuntamiento de Madrid', 'https://www.madrid.es/citaprevia', 2);
INSERT INTO tramites (id, titulo, descripcion, completado, plazo, ubicacion, url_cita, sede_id) VALUES (3, 'Abono Transporte', 'Tarjeta transporte público zona A', false, '7-15 días', 'Metro / Estancos', 'https://webttp.comunidad.madrid/CRTM-ABONOS/entrada.aspx?s=individual', 3);

-- 3. DOCUMENTOS PARA NIE (Trámite 1)
INSERT INTO documentos (id, nombre, descripcion, tipo, costo, obligatorio, marcado, tramite_id) VALUES (1, 'Modelo EX-15', 'Formulario oficial de solicitud', 'Copia', 'Gratis', true, false, 1);
INSERT INTO documentos (id, nombre, descripcion, tipo, costo, obligatorio, marcado, tramite_id) VALUES (2, 'Pasaporte Completo', 'Todas las páginas', 'original y copia', 'Variable', true, false, 1);
INSERT INTO documentos (id, nombre, descripcion, tipo, costo, obligatorio, marcado, tramite_id) VALUES (3, 'Tasa 790 012', 'Justificante de pago', 'Original', '9.84€', true, false, 1);
INSERT INTO documentos (id, nombre, descripcion, tipo, costo, obligatorio, marcado, tramite_id) VALUES (4, 'Justificante de la Cita Previa', 'Debes llevar impresa la confirmación que te llegó al correo o el PDF del sistema de citas.', 'Original', 'gratis', true, false, 1);

-- 4. DOCUMENTOS PARA EMPADRONAMIENTO (Trámite 2)
INSERT INTO documentos (id, nombre, descripcion, tipo, costo, obligatorio, marcado, tramite_id) VALUES (5, 'DNI/Pasaporte', 'Original en vigor', 'Original', 'Gratis', true, false, 2);
INSERT INTO documentos (id, nombre, descripcion, tipo, costo, obligatorio, marcado, tramite_id) VALUES (6, 'Solicitud de empadornamiento', 'Descargar y rellenar el PDF', 'Copia', 'Gratis', true, false, 2);
INSERT INTO documentos (id, nombre, descripcion, tipo, costo, obligatorio, marcado, tramite_id) VALUES (7, 'Justificante de cita previa', 'Debes realizar la cita antes de acudir', 'Copia', 'Gratis', true, false, 2);

-- 5. DOCUMENTOS PARA ABONO TRANSPORTE (Trámite 3)
INSERT INTO documentos (id, nombre, descripcion, tipo, costo, obligatorio, marcado, tramite_id) VALUES (8, 'Fotografía reciente', 'Tamaño carné color', 'Original', '5.00€', true, false, 3);
INSERT INTO documentos (id, nombre, descripcion, tipo, costo, obligatorio, marcado, tramite_id) VALUES (9, 'Solicitud Tarjeta', 'Formulario oficial', 'Original', 'Gratis', true, false, 3);
INSERT INTO documentos (id, nombre, descripcion, tipo, costo, obligatorio, marcado, tramite_id) VALUES (10, 'DNI/Pasaporte', 'Original en vigor', 'Original', 'Gratis', true, false, 3);

-- 6. CONSEJOS
INSERT INTO consejo (autor, categoria, contenido, tramite_id) VALUES ('Beatriz', 'NIE', 'Llevad el pasaporte original.', 1);
INSERT INTO consejo (autor, categoria, contenido, tramite_id) VALUES ('Carlos', 'Padrón', 'Pedir la cita con tiempo.', 2);