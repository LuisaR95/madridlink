-- 1. Insertar Sedes
INSERT INTO sedes (id, nombre, direccion, horario, telefono, latitud, longitud) VALUES (1, 'Oficina de Extranjería - Pradillo', 'Calle de Pradillo, 40', '09:00 - 14:00', '912 72 95 00', 40.448937, -3.671362);
INSERT INTO sedes (id, nombre, direccion, horario, telefono, latitud, longitud) VALUES (2, 'Comisaría Manuel Luna', 'Calle de Manuel Luna, 29', '09:00 - 21:00', '915 82 29 00', 40.454669, -3.699779);
INSERT INTO sedes (id, nombre, direccion, horario, telefono, latitud, longitud) VALUES (3, 'Oficina de Extranjería - Aluche', 'Av. de los Poblados, 51', '09:00 - 14:00', '913 22 85 00', 40.382830, -3.757562);

-- 2. Insertar Trámites
INSERT INTO tramites (id, titulo, descripcion, completado, plazo, ubicacion, url_cita, sede_id) VALUES (1, 'NIE Inicial', 'Solicitud de número de identidad de extranjero', false, '90 días', 'Oficina Pradillo', 'https://icp.administracionelectronica.gob.es/icpplus/index.html', 1);
INSERT INTO tramites (id, titulo, descripcion, completado, plazo, ubicacion, url_cita, sede_id) VALUES (2, 'Empadronamiento', 'Inscripción en el padrón municipal de Madrid', false, 'Inmediato', 'Ayuntamiento de Madrid', 'https://www.madrid.es/citaprevia', 2);
INSERT INTO tramites (id, titulo, descripcion, completado, plazo, ubicacion, url_cita, sede_id) VALUES (3, 'Abono Transporte', 'Tarjeta transporte público zona A', true, '7-15 días', 'Metro / Estancos', 'https://tarjetatransportepublico.crtm.es/', 3);

-- 3. Insertar Documentos
INSERT INTO documentos (id, nombre, marcado, tramite_id) VALUES (1, 'Modelo EX-15', true, 1);
INSERT INTO documentos (id, nombre, marcado, tramite_id) VALUES (2, 'Hoja Padrón', false, 2);

-- 4. Insertar Consejos
INSERT INTO consejo (id, autor, categoria, contenido, tramite_id) VALUES (1, 'Beatriz', 'NIE', 'Llevad el pasaporte original siempre.', 1);
INSERT INTO consejo (id, autor, categoria, contenido, tramite_id) VALUES (2, 'Carlos', 'Padrón', 'Pedid la cita con tiempo en Manuel Luna.', 2);