CREATE TABLE IF NOT EXISTS task (
    "id" SERIAL PRIMARY KEY,
    "name" VARCHAR(128) NOT NULL,
    "project_id" int NOT NULL,
    FOREIGN KEY("project_id") REFERENCES project
);
