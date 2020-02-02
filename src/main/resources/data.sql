DROP TABLE IF EXISTS moneda;
 
CREATE TABLE moneda (
  idmoneda INT AUTO_INCREMENT  PRIMARY KEY,
  monedaorigen VARCHAR(50) NOT NULL,
  monedadestino VARCHAR(50) NOT NULL,
  tipocambio NUMBER NOT NULL
);
 
INSERT INTO moneda (monedaorigen, monedadestino, tipocambio) VALUES
  ('soles', 'dolares', 0.299),
  ('soles', 'euros', 0.27),
  ('dolares', 'soles', 3.336),
  ('dolares', 'euros', 0.9),
  ('euros', 'soles', 3.750),
  ('euros', 'dolares', 1.11);
