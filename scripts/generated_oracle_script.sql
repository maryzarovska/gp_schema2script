CREATE TABLE student (
    student_id INT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(100),
    enrollment_date DATE,
    CONSTRAINT student_PK PRIMARY KEY (student_id),
    CONSTRAINT student_FK1 FOREIGN KEY (student_id) REFERENCES course (course_id)
);

CREATE TABLE instructor (
    instructor_id INT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(100),
    hire_date DATE,
    CONSTRAINT instructor_PK PRIMARY KEY (instructor_id),
    CONSTRAINT instructor_FK1 FOREIGN KEY (instructor_id) REFERENCES course (course_id)
);

CREATE TABLE course (
    course_id INT,
    course_name VARCHAR(100),
    credits INT,
    instructor_id INT,
    CONSTRAINT course_PK PRIMARY KEY (course_id),
    CONSTRAINT course_FK1 FOREIGN KEY (course_id) REFERENCES student (student_id),
    CONSTRAINT course_FK2 FOREIGN KEY (course_id) REFERENCES instructor (instructor_id)
);

CREATE TABLE enrollment (
    student_id INT,
    course_id INT,
    enrollment_date DATE,
    CONSTRAINT enrollment_PK PRIMARY KEY (student_id),
    CONSTRAINT enrollment_FK1 FOREIGN KEY (student_id) REFERENCES student (student_id),
    CONSTRAINT enrollment_FK2 FOREIGN KEY (course_id) REFERENCES course (course_id)
);

