CREATE TABLE Student
(
    id   INT PRIMARY KEY,
    name VARCHAR(50),
    age  INT
);

CREATE TABLE Course
(
    id    INT PRIMARY KEY,
    name  VARCHAR(50),
    hours INT
);

CREATE TABLE StudentCourse
(
    student_id INT,
    course_id  INT,
    FOREIGN KEY (student_id) REFERENCES Student (student_id),
    FOREIGN KEY (course_id) REFERENCES Course (course_id),
    PRIMARY KEY (student_id, course_id)
);

CREATE TABLE Lecturer
(
    id        INT PRIMARY KEY,
    name      VARCHAR(50),
    specialty VARCHAR(50)
);

ALTER TABLE Course
    ADD lecturer_id INT,
    ADD FOREIGN KEY (lecturer_id) REFERENCES Lecturer (lecturer_id);

DO
$$
    DECLARE
        rec RECORD;
    BEGIN
        FOR rec IN (SELECT tablename FROM pg_tables WHERE schemaname = 'public' AND tablename <> 'studentcourse')
            LOOP
                EXECUTE 'ALTER TABLE ' || rec.tablename || ' ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY;';
            END LOOP;
    END
$$;