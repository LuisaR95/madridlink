-- 1. SEDES
INSERT INTO sedes (id, nombre, direccion, horario, telefono, latitud, longitud) VALUES (1, 'Oficina de Extranjería - Pradillo', 'Calle de Pradillo, 40', '09:00 - 14:00', '912 72 95 00', 40.448937, -3.671362);
INSERT INTO sedes (id, nombre, direccion, horario, telefono, latitud, longitud) VALUES (2, 'Comisaría Manuel Luna', 'Calle de Manuel Luna, 29', '09:00 - 21:00', '915 82 29 00', 40.454669, -3.699779);
INSERT INTO sedes (id, nombre, direccion, horario, telefono, latitud, longitud) VALUES (3, 'Oficina de Extranjería - Aluche', 'Av. de los Poblados, 51', '09:00 - 14:00', '913 22 85 00', 40.382830, -3.757562);

-- 2. TRÁMITES
INSERT INTO tramites (id, titulo, descripcion, completado, plazo, ubicacion, url_cita, sede_id) VALUES (1, 'NIE Inicial', 'Solicitud de número de identidad de extranjero', false, '90 días', 'Oficina Pradillo', 'https://sede.administracionespublicas.gob.es/pagina/index/directorio/icpplus', 1);
INSERT INTO tramites (id, titulo, descripcion, completado, plazo, ubicacion, url_cita, sede_id) VALUES (2, 'Empadronamiento', 'Inscripción en el padrón municipal de Madrid', false, 'Inmediato', 'Ayuntamiento de Madrid', 'https://www.madrid.es/citaprevia', 2);
INSERT INTO tramites (id, titulo, descripcion, completado, plazo, ubicacion, url_cita, sede_id) VALUES (3, 'Abono Transporte', 'Tarjeta transporte público zona A', false, '7-15 días', 'Metro / Estancos', 'https://tarjetatransportepublico.crtm.es/', 3);

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

-- 6. CONSEJOS
INSERT INTO consejo (autor, categoria, contenido, tramite_id) VALUES ('Beatriz', 'NIE', 'Llevad el pasaporte original.', 1);
INSERT INTO consejo (autor, categoria, contenido, tramite_id) VALUES ('Carlos', 'Padrón', 'Pedir la cita con tiempo.', 2);