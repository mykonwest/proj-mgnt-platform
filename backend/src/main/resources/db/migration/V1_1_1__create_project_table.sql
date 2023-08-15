CREATE TABLE IF NOT EXISTS project (
    "id" SERIAL PRIMARY KEY,
    "name" VARCHAR(128) NOT NULL,
    "client_id" int NOT NULL,
    FOREIGN KEY("client_id") REFERENCES client
);