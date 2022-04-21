START TRANSACTION;

DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS cakes;
DROP TABLE IF EXISTS cake_options;
DROP TABLE IF EXISTS business_hours;

CREATE TABLE cakes(
	cake_id serial,
	flavor varchar(25),
    icing varchar(25),
	size varchar(25),
	num_of_tiers int,
	shape varchar(25),
	filling varchar(25),
	is_standard boolean,
	image_url varchar(250),
	price money,
	available boolean,
	CONSTRAINT PK_cakes PRIMARY KEY (cake_id)
);

CREATE TABLE orders(
    order_id serial,
    cake_id int,
	message varchar(25),
    customer_name varchar(25),
    customer_address varchar(100),
    customer_city varchar(25),
    customer_state varchar(2),
    customer_zip int,
    customer_phone varchar(15),
    customer_email varchar(50),
    order_date timestamp,
    pickup_date timestamp,
	price money,
	status varchar(10),
	completed_date date,
    CONSTRAINT PK_order PRIMARY KEY (order_id),
    CONSTRAINT FK_order FOREIGN KEY (cake_id) REFERENCES cakes(cake_id)
);

CREATE TABLE business_hours (
	weekday_id serial,
	weekday_name varchar(15),
	open_time time,
	close_time time,
	CONSTRAINT PK_business_hours PRIMARY KEY (weekday_id)
	);
	
CREATE TABLE cake_options(
	option_id serial,
	option_name varchar(25),
	option_type varchar(25),
	additional_price money,
	CONSTRAINT PK_options PRIMARY KEY (option_id)
);

INSERT INTO cakes (flavor, icing, size, num_of_tiers, shape, filling, is_standard, image_url, price, available)
VALUES ('Chocolate', 'Chocolate', '9 inch', 1, 'Round', 'Raspberry', true, 'https://thefirstyearblog.com/wp-content/uploads/2015/11/chocolate-chocolate-cake-1.png', 25.00, true);

INSERT INTO cakes (flavor, icing, size, num_of_tiers, shape, filling, is_standard, image_url, price, available)
VALUES ('Marble', 'Vanilla', '13 inch', 2, 'Round', 'Lemon Curd', true, 'https://domesticgothess.com/wp-content/uploads/2020/06/vegan-chocolate-vanilla-marble-cake.jpg', 40.00, true);

INSERT INTO cakes (flavor, icing, size, num_of_tiers, shape, filling, is_standard, image_url, price, available)
VALUES ('Carrot', 'Cream Cheese', '9 inch', 1, 'Round', 'None', true, 'https://www.cookingclassy.com/wp-content/uploads/2020/04/carrot-cake-20-1.jpg', 35.00, false);

INSERT INTO cake_options (option_name, option_type, additional_price)
VALUES ('Chocolate', 'Flavor', 0.00);

INSERT INTO cake_options (option_name, option_type, additional_price)
VALUES ('Chocolate Buttercream', 'Icing', 0.00);

INSERT INTO cake_options (option_name, option_type, additional_price)
VALUES ('Chocolate Ganache', 'Filling', 3.00);

INSERT INTO cake_options (option_name, option_type, additional_price)
VALUES ('9 inch', 'Size', 0.00);

INSERT INTO cake_options (option_name, option_type, additional_price)
VALUES ('Round', 'Shape', 0.00);

INSERT INTO cake_options (option_name, option_type, additional_price)
VALUES ('Vanilla', 'Flavor', 0.00);

INSERT INTO cake_options (option_name, option_type, additional_price)
VALUES ('Vanilla Buttercream', 'Icing', 0.00);

INSERT INTO cake_options (option_name, option_type, additional_price)
VALUES ('Lemon Curd', 'Filling', 3.00);

INSERT INTO cake_options (option_name, option_type, additional_price)
VALUES ('Cupcake', 'Size', 0.00);

INSERT INTO cake_options (option_name, option_type, additional_price)
VALUES ('Cupcake', 'Shape', 0.00);

INSERT INTO cake_options (option_name, option_type, additional_price)
VALUES ('Carrot', 'Flavor', 0.00);

INSERT INTO cake_options (option_name, option_type, additional_price)
VALUES ('Cream Cheese', 'Icing', 0.00);

INSERT INTO cake_options (option_name, option_type, additional_price)
VALUES ('Raspberry', 'Filling', 2.00);

INSERT INTO cake_options (option_name, option_type, additional_price)
VALUES ('13 inch', 'Size', 0.00);

INSERT INTO cake_options (option_name, option_type, additional_price)
VALUES ('Sheet', 'Shape', 0.00);

INSERT INTO orders (cake_id, customer_name, message, customer_address, customer_city, customer_state, customer_zip, customer_phone, customer_email, order_date, pickup_date, price, status)
VALUES(1, 'John Smith', '', '123 Main Street', 'Buffalo', 'NY', '14123', '(716)555-1234', 'johnsmith@fakeemail.com', '2022-04-19 09:48:45.434528', '2022-04-26 12:48:00', 25.00, 'pending');

INSERT INTO orders (cake_id, customer_name, message, customer_address, customer_city, customer_state, customer_zip, customer_phone, customer_email, order_date, pickup_date, price, status, completed_date)
VALUES(1, 'Jane Smith', '', '345 Main Street', 'Boston', 'MA', '14123', '(716)555-5678', 'janesmith@fakeemail.com', '2022-04-19 09:48:45.434528', '2022-04-26 12:48:00', 25.00, 'Completed', '2021-10-15');

INSERT INTO orders (cake_id, customer_name, message, customer_address, customer_city, customer_state, customer_zip, customer_phone, customer_email, order_date, pickup_date, price, status, completed_date)
VALUES(1, 'John Doe', '', '596 Oak Drive', 'Washington', 'DC', '14123', '(716)555-1111', 'johndoe@aol.com', '2022-04-19 09:48:45.434528', '2022-05-04 12:48:00', 25.00, 'Completed', '2022-04-15');

INSERT INTO orders (cake_id, customer_name, message, customer_address, customer_city, customer_state, customer_zip, customer_phone, customer_email, order_date, pickup_date, price, status, completed_date)
VALUES(1, 'John Smith', '', '123 Main Street', 'Buffalo', 'NY', '14123', '(716)555-1234', 'johnsmith@fakeemail.com', '2022-04-19 09:48:45.434528', '2022-05-04 12:48:00', 25.00, 'Completed', '2021-12-23');

INSERT INTO orders (cake_id, customer_name, message, customer_address, customer_city, customer_state, customer_zip, customer_phone, customer_email, order_date, pickup_date, price, status)
VALUES(1, 'Jane Smith', '', '345 Main Street', 'Boston', 'MA', '14123', '(716)555-5678', 'janesmith@gmail.com', '2021-10-11 09:48:45.434528', '2022-04-26 12:48:00', 25.00, 'Ready');

INSERT INTO orders (cake_id, customer_name, message, customer_address, customer_city, customer_state, customer_zip, customer_phone, customer_email, order_date, pickup_date, price, status)
VALUES(1, 'John Doe', '', '596 Oak Drive', 'Washington', 'DC', '14123', '(716)555-1111', 'johndoe@aol.com', '2022-04-19 09:48:45.434528', '2022-05-04 12:48:00', 25.00, 'Ready');

INSERT INTO orders (cake_id, customer_name, message, customer_address, customer_city, customer_state, customer_zip, customer_phone, customer_email, order_date, pickup_date, price, status)
VALUES(1, 'John Smith', '', '123 Main Street', 'Buffalo', 'NY', '14123', '(716)555-1234', 'johnsmith@fakeemail.com', '2022-04-19 09:48:45.434528', '2022-05-04 12:48:00', 25.00, 'Cancelled');

INSERT INTO orders (cake_id, customer_name, message, customer_address, customer_city, customer_state, customer_zip, customer_phone, customer_email, order_date, pickup_date, price, status)
VALUES(1, 'Jane Smith', '', '345 Main Street', 'Boston', 'MA', '14123', '(716)555-5678', 'janesmith@fakeemail.com', '2022-04-19 09:48:45.434528', '2022-05-04 12:48:00', 25.00, 'Cancelled');

INSERT INTO orders (cake_id, customer_name, message, customer_address, customer_city, customer_state, customer_zip, customer_phone, customer_email, order_date, pickup_date, price, status)
VALUES(1, 'John Doe', '', '596 Oak Drive', 'Washington', 'DC', '14123', '(716)555-1111', 'johndoe@aol.com', '2022-04-19 09:48:45.434528', '2022-05-04 12:48:00', 25.00, 'pending');

INSERT INTO orders (cake_id, customer_name, message, customer_address, customer_city, customer_state, customer_zip, customer_phone, customer_email, order_date, pickup_date, price, status)
VALUES(1, 'Joe Johnson', '', '55 Spruce Street', 'Buffalo', 'NY', '14123', '(716)555-1234', 'joejohnson@outlook.com', '2022-04-19 09:48:45.434528', '2022-05-04 12:48:00', 25.00, 'pending');

INSERT INTO business_hours (weekday_name, open_time, close_time)
VALUES ('Sunday','00:00:00', '00:00:00');

INSERT INTO business_hours (weekday_name, open_time, close_time)
VALUES ('Monday','09:00:00', '18:00:00');

INSERT INTO business_hours (weekday_name, open_time, close_time)
VALUES ('Tuesday','09:00:00', '18:00:00');

INSERT INTO business_hours (weekday_name, open_time, close_time)
VALUES ('Wednesday','09:00:00', '18:00:00');

INSERT INTO business_hours (weekday_name, open_time, close_time)
VALUES ('Thursday','09:00:00', '18:00:00');

INSERT INTO business_hours (weekday_name, open_time, close_time)
VALUES ('Friday','09:00:00', '22:00:00');

INSERT INTO business_hours (weekday_name, open_time, close_time)
VALUES ('Saturday','09:00:00', '22:00:00');

COMMIT;