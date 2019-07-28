CREATE TABLE bill (
   id INT AUTO_INCREMENT,
   name VARCHAR(200) NOT NULL,
   value DECIMAL(10,2) NOT NULL,
   value_corrected DECIMAL(10,2) NOT NULL,
   delayed_days INT DEFAULT 0,
   due_date DATE NOT NULL,
   payment_date DATE NOT NULL,
);