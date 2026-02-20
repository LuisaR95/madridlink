-- Insertar Trámites iniciales para Madrid Link
INSERT INTO tramites (id, titulo, descripcion, completado) VALUES (1, 'NIE Inicial', 'Solicitud de número de identidad de extranjero para residentes', false);
INSERT INTO tramites (id, titulo, descripcion, completado) VALUES (2, 'Empadronamiento', 'Inscripción en el padrón municipal de Madrid', false);
INSERT INTO tramites (id, titulo, descripcion, completado) VALUES (3, 'Abono Transporte', 'Solicitud de tarjeta transporte público zona A', true);

-- Insertar Documentos para el NIE (Relacionados con el tramite 1)
INSERT INTO documentos (nombre, marcado, tramite_id) VALUES ('Modelo EX-15', true, 1);
INSERT INTO documentos (nombre, marcado, tramite_id) VALUES ('Cita previa impresa', false, 1);
INSERT INTO documentos (nombre, marcado, tramite_id) VALUES ('Tasa 790 cod 012', false, 1);