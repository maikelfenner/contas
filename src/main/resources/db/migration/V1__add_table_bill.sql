CREATE TABLE bill (
   id INT NOT NULL,
   name VARCHAR(200) NOT NULL,
   value DECIMAL(10,2) NOT NULL,
   value_corrected DECIMAL(10,2) NOT NULL,
   delayed_days INT DEFAULT 0,
   payment_date DATE,
);