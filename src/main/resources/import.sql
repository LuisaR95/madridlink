INSERT INTO tramites (id, titulo, descripcion, completado, plazo, ubicacion, url_cita) VALUES (1, 'NIE Inicial', 'Solicitud de número de identidad de extranjero para residentes', false, '90 días', 'Oficina de Extranjería / Comisaría', 'https://icp.administracionelectronica.gob.es/icpplus/index.html');

INSERT INTO tramites (id, titulo, descripcion, completado, plazo, ubicacion, url_cita) VALUES (2, 'Empadronamiento', 'Inscripción en el padrón municipal de Madrid', false, 'Inmediato', 'OAC Ayuntamiento de Madrid', 'https://www.madrid.es/citaprevia');

INSERT INTO tramites (id, titulo, descripcion, completado, plazo, ubicacion, url_cita) VALUES (3, 'Abono Transporte', 'Solicitud de tarjeta transporte público zona A', true, '7-15 días', 'Estaciones de Metro / Estancos', 'https://tarjetatransportepublico.crtm.es/');

INSERT INTO documentos (nombre, marcado, tramite_id) VALUES ('Modelo EX-15', true, 1);
INSERT INTO documentos (nombre, marcado, tramite_id) VALUES ('Cita previa impresa', false, 1);
INSERT INTO documentos (nombre, marcado, tramite_id) VALUES ('Tasa 790 cod 012', false, 1);
INSERT INTO documentos (nombre, marcado, tramite_id) VALUES ('Pasaporte Original', false, 1);

INSERT INTO documentos (nombre, marcado, tramite_id) VALUES ('Hoja de inscripción padronal firmada', false, 2);
INSERT INTO documentos (nombre, marcado, tramite_id) VALUES ('DNI, Pasaporte o NIE original', false, 2);
INSERT INTO documentos (nombre, marcado, tramite_id) VALUES ('Contrato de alquiler original', false, 2);
INSERT INTO documentos (nombre, marcado, tramite_id) VALUES ('Última factura de suministros (Luz/Agua)', false, 2);

INSERT INTO documentos (nombre, marcado, tramite_id) VALUES ('Fotografía reciente en color (tipo carné)', false, 3);
INSERT INTO documentos (nombre, marcado, tramite_id) VALUES ('DNI, Pasaporte o NIE', false, 3);
INSERT INTO documentos (nombre, marcado, tramite_id) VALUES ('Justificante de pago de la tarjeta (6€)', false, 3);
INSERT INTO documentos (nombre, marcado, tramite_id) VALUES ('Título de familia numerosa (si aplica)', false, 3);