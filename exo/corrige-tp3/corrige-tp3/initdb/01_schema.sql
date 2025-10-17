USE jdbc_things;

DROP TABLE IF EXISTS person;

CREATE TABLE person (
  email VARCHAR(255) PRIMARY KEY,
  name  VARCHAR(255) NOT NULL,
  age   INT NOT NULL
);