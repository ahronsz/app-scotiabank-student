DROP TABLE IF EXISTS Students;
CREATE TABLE Students(
    student_id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    condition BOOLEAN NOT NULL,
    age TINYINT NOT NULL,
    creation_date_time TIMESTAMP NOT NULL
)