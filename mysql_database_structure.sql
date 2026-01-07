-- Estructura de base de datos MySQL para el sistema de logística

-- Crear base de datos
CREATE DATABASE IF NOT EXISTS logistica_db;
USE logistica_db;

-- Tabla para clientes
CREATE TABLE clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    identificacion VARCHAR(100) NOT NULL UNIQUE,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla para vehículos (tabla padre para camiones y drones)
CREATE TABLE vehiculos (
    id_vehiculo VARCHAR(50) PRIMARY KEY,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    capacidad_maxima INT NOT NULL,
    estado ENUM('DISPONIBLE', 'ALQUILADO', 'MANTENIMIENTO') DEFAULT 'DISPONIBLE',
    tipo_vehiculo ENUM('CAMION', 'DRON') NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla específica para camiones
CREATE TABLE camiones (
    id_vehiculo VARCHAR(50) PRIMARY KEY,
    numero_ejes INT NOT NULL,
    volumen_carga DECIMAL(10, 2) NOT NULL,
    tipo_motor ENUM('DIESEL', 'ELECTRICO', 'HIBRIDO', 'GASOLINA') NOT NULL,
    kilometraje DECIMAL(12, 2) NOT NULL,
    FOREIGN KEY (id_vehiculo) REFERENCES vehiculos(id_vehiculo) ON DELETE CASCADE
);

-- Tabla específica para drones
CREATE TABLE drones (
    id_vehiculo VARCHAR(50) PRIMARY KEY,
    altitud_maxima DECIMAL(10, 2) NOT NULL,
    autonomia_maxima DECIMAL(10, 2) NOT NULL,
    tiene_camara BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (id_vehiculo) REFERENCES vehiculos(id_vehiculo) ON DELETE CASCADE
);

-- Tabla para orígenes de rutas (sucursales y almacén central)
CREATE TABLE origenes (
    id_origen INT AUTO_INCREMENT PRIMARY KEY,
    nombre_origen VARCHAR(100) NOT NULL UNIQUE,
    descripcion TEXT
);

-- Insertar valores posibles de origen
INSERT INTO origenes (nombre_origen, descripcion) VALUES
('SUCURSAL1', 'Sucursal número 1'),
('SUCURSAL2', 'Sucursal número 2'),
('ALMACEN_CENTRAL', 'Almacén central de la empresa');

-- Tabla para rutas
CREATE TABLE rutas (
    id_ruta INT AUTO_INCREMENT PRIMARY KEY,
    id_origen INT NOT NULL,
    destino VARCHAR(255) NOT NULL,
    distancia_km DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_origen) REFERENCES origenes(id_origen)
);

-- Tabla para envíos
CREATE TABLE envios (
    id_envio VARCHAR(50) PRIMARY KEY,
    codigo_envio VARCHAR(100) NOT NULL UNIQUE,
    id_cliente INT NOT NULL,
    id_vehiculo VARCHAR(50) NOT NULL,
    id_ruta INT NOT NULL,
    peso DECIMAL(10, 2) NOT NULL,
    distancia VARCHAR(100),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),
    FOREIGN KEY (id_vehiculo) REFERENCES vehiculos(id_vehiculo),
    FOREIGN KEY (id_ruta) REFERENCES rutas(id_ruta)
);

-- Índices para optimizar búsquedas comunes
CREATE INDEX idx_cliente_identificacion ON clientes(identificacion);
CREATE INDEX idx_vehiculo_estado ON vehiculos(estado);
CREATE INDEX idx_envio_codigo ON envios(codigo_envio);
CREATE INDEX idx_envio_fecha ON envios(fecha_registro);

-- Vista para obtener información completa de envíos
CREATE VIEW vista_envios_completa AS
SELECT 
    e.id_envio,
    e.codigo_envio,
    c.nombre AS cliente_nombre,
    c.identificacion AS cliente_identificacion,
    e.id_vehiculo,
    v.tipo_vehiculo,
    v.estado AS estado_vehiculo,
    e.peso,
    e.distancia,
    r.destino,
    o.nombre_origen AS origen,
    r.distancia_km,
    e.fecha_registro
FROM envios e
JOIN clientes c ON e.id_cliente = c.id_cliente
JOIN vehiculos v ON e.id_vehiculo = v.id_vehiculo
JOIN rutas r ON e.id_ruta = r.id_ruta
JOIN origenes o ON r.id_origen = o.id_origen;

-- Procedimiento para calcular costo de envío
DELIMITER //
CREATE PROCEDURE CalcularCostoEnvio(
    IN p_id_envio VARCHAR(50),
    OUT p_costo_base DECIMAL(10,2),
    OUT p_costo_con_iva DECIMAL(10,2)
)
BEGIN
    DECLARE v_tipo_vehiculo VARCHAR(20);
    DECLARE v_distancia_km DECIMAL(10,2);
    DECLARE v_costo_km DECIMAL(10,2) DEFAULT 0;
    
    -- Obtener tipo de vehículo y distancia
    SELECT v.tipo_vehiculo, r.distancia_km
    INTO v_tipo_vehiculo, v_distancia_km
    FROM envios e
    JOIN vehiculos v ON e.id_vehiculo = v.id_vehiculo
    JOIN rutas r ON e.id_ruta = r.id_ruta
    WHERE e.id_envio = p_id_envio;
    
    -- Determinar costo por km según tipo de vehículo
    IF v_tipo_vehiculo = 'CAMION' THEN
        SET v_costo_km = 3.8; -- COSTO_KM_CAMION
    ELSEIF v_tipo_vehiculo = 'DRON' THEN
        SET v_costo_km = 1.9; -- COSTO_KM_DRON
    END IF;
    
    -- Calcular costos
    SET p_costo_base = v_distancia_km * v_costo_km;
    SET p_costo_con_iva = p_costo_base * 1.13; -- IVA del 13%
END //
DELIMITER ;

-- Insertar algunos datos de ejemplo
INSERT INTO clientes (nombre, identificacion) VALUES
('Juan Pérez', 'CI001'),
('María García', 'CI002'),
('Empresa ABC', 'RUC001');

INSERT INTO vehiculos (id_vehiculo, marca, modelo, capacidad_maxima, estado, tipo_vehiculo) VALUES
('ABC123', 'Volvo', 'FH16', 20000, 'DISPONIBLE', 'CAMION'),
('DRN001', 'DJI', 'Phantom 4', 5, 'DISPONIBLE', 'DRON');

INSERT INTO camiones (id_vehiculo, numero_ejes, volumen_carga, tipo_motor, kilometraje) VALUES
('ABC123', 4, 60.0, 'DIESEL', 150000.0);

INSERT INTO drones (id_vehiculo, altitud_maxima, autonomia_maxima, tiene_camara) VALUES
('DRN001', 500.0, 30.0, TRUE);

INSERT INTO rutas (id_origen, destino, distancia_km) VALUES
(1, 'Ciudad A', 120.5),
(2, 'Ciudad B', 85.3),
(3, 'Ciudad C', 210.0);