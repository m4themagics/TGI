CREATE TABLE Student(
                        id INT PRIMARY KEY,
                        name VARCHAR(50),
                        age INT
);

CREATE TABLE Course(
                       id INT PRIMARY KEY,
                       name VARCHAR(50),
                       hours INT
);

CREATE TABLE StudentCourse (
                               student_id INT,
                               course_id INT,
                               FOREIGN KEY (student_id) REFERENCES Student(id),
                               FOREIGN KEY (course_id) REFERENCES Course(id),
                               PRIMARY KEY (student_id, course_id)
);

CREATE TABLE Lecturer (
                          id INT PRIMARY KEY,
                          name VARCHAR(50),
                          specialty VARCHAR(50)
);

ALTER TABLE Course
    ADD lecturer_id INT,
    ADD FOREIGN KEY (lecturer_id) REFERENCES Lecturer(id);