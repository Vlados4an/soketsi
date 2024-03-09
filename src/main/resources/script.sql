CREATE TABLE Train (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL
);

CREATE TABLE Carriage (
                          id SERIAL PRIMARY KEY,
                          type varchar(255),
                          comfortLevel INT NOT NULL,
                          luggage_capacity INT,
                          passenger_capacity INT,
                          train_id INT,
                          FOREIGN KEY (train_id) REFERENCES Train(id)
);